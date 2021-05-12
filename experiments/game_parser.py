from glob import glob
import os, string, json, pickle
import torch, random, numpy as np
from transformers import BertTokenizer, BertModel
import cv2
import imageio

random.seed(0)
torch.manual_seed(1)

DEVICE = 'cuda' if torch.cuda.is_available() else 'cpu'

def onehot(x,n):
    retval = np.zeros(n)
    if x > 0:
        retval[x-1] = 1
    return retval

class GameParser:
    def __init__(self, game_path, load_dialogue=True, pov=0):
        # print(game_path,end = ' ')
        self.load_dialogue = load_dialogue
        if pov not in (0,1,2,3,4):
            print('Point of view must be in (0,1,2,3,4), but got ', pov)
            exit()
        self.pov = pov
        self.load_player1 = pov==1
        self.load_player2 = pov==2
        self.load_third_person = pov==3
        self.game_path = game_path
        self.tokenizer = None
        self.model = None
        # print(game_path)
        self.game_path = game_path
        self.dialogue_file = glob(os.path.join(game_path,'mcc*log'))[0]
        self.questions_file = glob(os.path.join(game_path,'web*log'))[0]
        self.plan_file = glob(os.path.join(game_path,'plan*json'))[0]
        self.plan = json.load(open(self.plan_file))
        self.img_w = 96
        self.img_h = 96
        
        self.__parse_dialogue()
        self.__parse_questions()
        self.__parse_start_end()
        self.__parse_question_pairs()
        self.__load_videos()
        
        materials = glob('../mean/dist/img/materials/*.png') + glob('mean/dist/img/materials/*.png')
        materials = sorted([m.split('/')[-1].split('.')[0] for m in materials])
        materials = [m for m in materials if not 'NULL' in m]
        # materials = [m for m in materials if not 'OBSIDIAN' in m]
        mines = [m for m in materials if 'PLANK' in m]
        materials = [m for m in materials if not 'PLANK' in m]

        tools = glob('../mean/dist/img/tools/*.png') + glob('mean/dist/img/tools/*.png')
        tools = sorted([t.split('/')[-1].split('.')[0] for t in tools])
        tools = [t for t in tools if not 'NULL' in t]
        
        self.materials_dict = {x:i+1 for i,x in enumerate(materials)}
        self.mines_dict = {x:i+1 for i,x in enumerate(mines)}
        self.tools_dict = {x:i+1 for i,x in enumerate(tools)}
        
        # print(len(self.materials_dict))
        
        self.global_plan = []
        mine_counter = 0
        for n,v in zip(self.plan['materials'],self.plan['full']):
            if v['make']:
                mine = 0
                m1 = self.materials_dict[self.plan['materials'][v['make'][0][0]]]
                m2 = self.materials_dict[self.plan['materials'][v['make'][0][1]]]
            else:
                mine = self.mines_dict[self.plan['mines'][mine_counter]]
                mine_counter += 1
                m1 = 0
                m2 = 0
            mine = onehot(mine, len(self.mines_dict))
            m1 = onehot(m1,len(self.materials_dict))
            m2 = onehot(m2,len(self.materials_dict))
            mat = onehot(self.materials_dict[n],len(self.materials_dict))
            t = onehot(self.tools_dict[self.plan['tools'][v['tools'][0]]],len(self.tools_dict))
            step = np.concatenate((mat,m1,m2,mine,t))
            self.global_plan.append(step)
        
        self.player1_plan = []
        mine_counter = 0
        for n,v in zip(self.plan['materials'],self.plan['player1']):
            if v['make']:
                mine = 0
                if v['make'][0][0] < 0:
                    m1 = 0
                    m2 = 0
                else:
                    m1 = self.materials_dict[self.plan['materials'][v['make'][0][0]]]
                    m2 = self.materials_dict[self.plan['materials'][v['make'][0][1]]]
            else:
                mine = self.mines_dict[self.plan['mines'][mine_counter]]
                mine_counter += 1
                m1 = 0
                m2 = 0
            mine = onehot(mine, len(self.mines_dict))
            m1 = onehot(m1,len(self.materials_dict))
            m2 = onehot(m2,len(self.materials_dict))
            mat = onehot(self.materials_dict[n],len(self.materials_dict))
            t = onehot(self.tools_dict[self.plan['tools'][v['tools'][0]]],len(self.tools_dict))
            step = np.concatenate((mat,m1,m2,mine,t))
            self.player1_plan.append(step)
        
        self.player2_plan = []
        mine_counter = 0
        for n,v in zip(self.plan['materials'],self.plan['player2']):
            if v['make']:
                mine = 0
                if v['make'][0][0] < 0:
                    m1 = 0
                    m2 = 0
                else:
                    m1 = self.materials_dict[self.plan['materials'][v['make'][0][0]]]
                    m2 = self.materials_dict[self.plan['materials'][v['make'][0][1]]]
            else:
                mine = self.mines_dict[self.plan['mines'][mine_counter]]
                mine_counter += 1
                m1 = 0
                m2 = 0
            mine = onehot(mine, len(self.mines_dict))
            m1 = onehot(m1,len(self.materials_dict))
            m2 = onehot(m2,len(self.materials_dict))
            mat = onehot(self.materials_dict[n],len(self.materials_dict))
            t = onehot(self.tools_dict[self.plan['tools'][v['tools'][0]]],len(self.tools_dict))
            step = np.concatenate((mat,m1,m2,mine,t))
            self.player2_plan.append(step)
        
        self.__iter_ts = self.start_ts
            
    def __next__(self):
        if self.__iter_ts < self.end_ts:
            if self.load_dialogue:
                d = [x for x in self.dialogue_events if x[0] == self.__iter_ts]
                d = d if d else None
            else:
                d = None
            
            # q = [x for x in self.question_pairs if (x[0][0] < self.__iter_ts) and (x[0][1] > self.__iter_ts)]
            q = [x for x in self.question_pairs if (x[0][1] == self.__iter_ts)]
            q = q[0] if q else None
            frame_idx = self.__iter_ts - self.start_ts
            if self.load_third_person:
                frames = self.third_pers_frames
            elif self.load_player1:
                frames = self.player1_pov_frames
            elif self.load_player2:
                frames = self.player2_pov_frames
            else:
                frames = np.array([0])
            if len(frames) == 1:
                f = np.zeros((self.img_h,self.img_w,3))
            else:
                if frame_idx < frames.shape[0]:
                    f = frames[frame_idx]
                else:
                    f = np.zeros((self.img_h,self.img_w,3))
            retval = (self.__iter_ts,d,q,f)
            self.__iter_ts += 1
            return retval
        self.__iter_ts = self.start_ts
        raise StopIteration()
    
    def __iter__(self):
        return self
    
    def __load_videos(self):
        d = self.end_ts - self.start_ts
        
        if self.load_third_person:
            try:
                self.third_pers_file = glob(os.path.join(self.game_path,'third*gif'))[0]
                np_file = self.third_pers_file[:-3]+'npz'
                if os.path.isfile(np_file):
                    self.third_pers_frames = np.load(np_file)['data']
                else:
                    frames = imageio.get_reader(self.third_pers_file, '.gif')
                    reshaper  = lambda x: cv2.resize(x,(self.img_h,self.img_w))
                    if 'main' in self.game_path:
                        self.third_pers_frames = np.array([reshaper(f[95:4*95,250:-249,2::-1]) for f in frames])
                    else:
                        self.third_pers_frames = np.array([reshaper(f[-3*95:,250:-249,2::-1]) for f in frames])
                    print(np_file,end=' ')
                    np.savez_compressed(open(np_file,'wb'), data=self.third_pers_frames)
                    print('saved')
            except Exception as e:
                self.third_pers_frames = np.array([0])
                
            if self.third_pers_frames.shape[0]//d < 10:
                self.third_pov_frame_rate = 6
            else:
                if self.third_pers_frames.shape[0]//d < 20:
                    self.third_pov_frame_rate = 12
                else:
                    if self.third_pers_frames.shape[0]//d < 45:
                        self.third_pov_frame_rate = 30
                    else:
                        self.third_pov_frame_rate = 60
            self.third_pers_frames = self.third_pers_frames[::self.third_pov_frame_rate]
        else:
            self.third_pers_frames = np.array([0])
            
        if self.load_player1:
            try:
                self.player1_pov_file = glob(os.path.join(self.game_path,'play1*gif'))[0]
                np_file = self.player1_pov_file[:-3]+'npz'
                if os.path.isfile(np_file):
                    self.player1_pov_frames = np.load(np_file)['data']
                else:                
                    frames = imageio.get_reader(self.player1_pov_file, '.gif')
                    reshaper  = lambda x: cv2.resize(x,(self.img_h,self.img_w))
                    self.player1_pov_frames = np.array([reshaper(f[:,:,2::-1]) for f in frames])
                    print(np_file,end=' ')
                    np.savez_compressed(open(np_file,'wb'), data=self.player1_pov_frames)
                    print('saved')
            except Exception as e:
                self.player1_pov_frames = np.array([0])
            
            if self.player1_pov_frames.shape[0]//d < 10:
                self.player1_pov_frame_rate = 6
            else:
                if self.player1_pov_frames.shape[0]//d < 20:
                    self.player1_pov_frame_rate = 12
                else:
                    if self.player1_pov_frames.shape[0]//d < 45:
                        self.player1_pov_frame_rate = 30
                    else:
                        self.player1_pov_frame_rate = 60
            self.player1_pov_frames = self.player1_pov_frames[::self.player1_pov_frame_rate]
        else:
            self.player1_pov_frames = np.array([0])
            
        if self.load_player2:
            try:
                self.player2_pov_file = glob(os.path.join(self.game_path,'play2*gif'))[0]
                np_file = self.player2_pov_file[:-3]+'npz'
                if os.path.isfile(np_file):
                    self.player2_pov_frames = np.load(np_file)['data']
                else:
                    frames = imageio.get_reader(self.player2_pov_file, '.gif')
                    reshaper  = lambda x: cv2.resize(x,(self.img_h,self.img_w))
                    self.player2_pov_frames = np.array([reshaper(f[:,:,2::-1]) for f in frames])
                    print(np_file,end=' ')
                    np.savez_compressed(open(np_file,'wb'), data=self.player2_pov_frames)
                    print('saved')
            except Exception as e:
                self.player2_pov_frames = np.array([0])
                
            if self.player2_pov_frames.shape[0]//d < 10:
                self.player2_pov_frame_rate = 6
            else:
                if self.player2_pov_frames.shape[0]//d < 20:
                    self.player2_pov_frame_rate = 12
                else:
                    if self.player2_pov_frames.shape[0]//d < 45:
                        self.player2_pov_frame_rate = 30
                    else:
                        self.player2_pov_frame_rate = 60
            self.player2_pov_frames = self.player2_pov_frames[::self.player2_pov_frame_rate]
        else:
            self.player2_pov_frames = np.array([0])

    def __parse_question_pairs(self):
        question_dict = {}        
        for q in self.questions:
            k = q[2][0][1] + q[2][1][1]
            if not k in question_dict:
                question_dict[k] = []
            question_dict[k].append(q)
        
        self.question_pairs = []
        for k,v in question_dict.items():
            if len(v) == 2:
                if v[0][1]+v[1][1] == 3:
                    self.question_pairs.append(v)
            else:
                while len(v) > 1:
                    pair = []
                    pair.append(v.pop(0))
                    pair.append(v.pop(0))
                    while not pair[0][1]+pair[1][1] == 3:
                        # print(game_path,pair)
                        pair.append(v.pop(0))
                        pair.pop(0)
                        if not v:
                            break
                    self.question_pairs.append(pair)
        self.question_pairs = sorted(self.question_pairs, key=lambda x: x[0][0])
        if self.load_player2 or self.pov==4:
            self.question_pairs = [sorted(q, key=lambda x: x[1],reverse=True) for q in self.question_pairs]
        else:
            self.question_pairs = [sorted(q, key=lambda x: x[1]) for q in self.question_pairs]
        
        
        self.question_pairs = [((a[0], b[0], a[1], b[1], a[2], b[2]), (a[3], b[3])) for a,b in self.question_pairs]

    def __parse_dialogue(self):
        self.dialogue_events = []
        # if not self.load_dialogue:
        #     return 
        save_path = os.path.join(self.game_path,f'dialogue_{self.game_path.split("/")[1]}.pkl')
        if os.path.isfile(save_path):
            self.dialogue_events = pickle.load(open( save_path, "rb" ))
            return
        for x in open(self.dialogue_file):
            if '[Async Chat Thread' in x:
                ts = list(map(int,x.split(' [')[0].strip('[]').split(':')))
                ts = 3600*ts[0] + 60*ts[1] + ts[2]
                player, event = x.strip().split('/INFO]: []<sledmcc')[1].split('> ',1)
                event = event.lower()
                event = ''.join([x if x in string.ascii_lowercase else f' {x} ' for x in event]).strip()
                event = event.replace('  ',' ').replace('  ',' ')
                player = int(player)
                if self.tokenizer is None:
                    self.tokenizer = BertTokenizer.from_pretrained('bert-large-uncased', do_lower_case=True)
                if self.model is None:
                    self.model = BertModel.from_pretrained('bert-large-uncased', output_hidden_states=True).to(DEVICE)
                encoded_dict = self.tokenizer.encode_plus(
                    event,  # Sentence to encode.
                    add_special_tokens=True,  # Add '[CLS]' and '[SEP]'
                    return_tensors='pt',  # Return pytorch tensors.
                )
                token_ids = encoded_dict['input_ids'].to(DEVICE)
                segment_ids = torch.ones(token_ids.size()).long().to(DEVICE)
                self.model.eval()
                with torch.no_grad():
                    outputs = self.model(input_ids=token_ids, token_type_ids=segment_ids)
                outputs = outputs[1][0].cpu().data.numpy()
                self.dialogue_events.append((ts,player,event,outputs))
        pickle.dump(self.dialogue_events, open( save_path, "wb" ))
        print(f'Saved to {save_path}',flush=True)
        
    def __parse_questions(self):
        self.questions = []
        for x in open(self.questions_file):
            if x[0] == '#':
                ts, qs = x.strip().split(' Number of records inserted: 1 # player')
                # print(ts,qs)
                
                ts = list(map(int,ts.split(' ')[5].split(':')))
                ts = 3600*ts[0] + 60*ts[1] + ts[2]
                
                player = int(qs[0])
                questions = qs[2:].split(';')
                answers =[x[7:] for x in questions[3:]]
                questions = [x[9:].split(' ') for x in questions[:3]]
                questions[0] = (int(questions[0][0] == 'Have'), questions[0][-3])
                questions[1] = (int(questions[1][2] == 'know'), questions[1][-1])
                questions[2] = int(questions[2][1] == 'are')
                
                self.questions.append((ts,player,questions,answers))
    def __parse_start_end(self):        
        self.start_ts = [x.strip() for x in open(self.dialogue_file) if 'THEY ARE PLAYER' in x][1]
        self.start_ts = list(map(int,self.start_ts.split('] [')[0][1:].split(':')))
        self.start_ts = 3600*self.start_ts[0] + 60*self.start_ts[1] + self.start_ts[2]
        try:
            self.start_ts = max(self.start_ts, self.questions[0][0]-75)
        except Exception as e:
            pass
        
        self.end_ts = [x.strip() for x in open(self.dialogue_file) if 'Stopping' in x]
        if self.end_ts:
            self.end_ts = self.end_ts[0]
            self.end_ts = list(map(int,self.end_ts.split('] [')[0][1:].split(':')))
            self.end_ts = 3600*self.end_ts[0] + 60*self.end_ts[1] + self.end_ts[2]
        else:
            self.end_ts = self.dialogue_events[-1][0]
        try:
            self.end_ts = max(self.end_ts, self.questions[-1][0]) + 1
        except Exception as e:
            pass
