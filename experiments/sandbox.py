from glob import glob
import os, string, json, pickle, sys
import torch, random, torch.nn as nn, numpy as np
from torch import optim
from transformers import BertTokenizer, BertModel
from random import shuffle
from sklearn.metrics import accuracy_score, f1_score

random.seed(0)
torch.manual_seed(1)

DEVICE = 'cuda' if torch.cuda.is_available() else 'cpu'
print(DEVICE,flush=True)
if len(sys.argv) > 1:
    print('without dialogue')
else:
    print('with dialogue')

def onehot(x,n):
    retval = np.zeros(n)
    if x > 0:
        retval[x-1] = 1
    return retval

class GameParser:
    def __init__(self, game_path):
        self.tokenizer = None
        self.model = None
        # print(game_path)
        self.game_path = game_path
        self.dialogue_file = glob(os.path.join(game_path,'mcc*log'))[0]
        self.questions_file = glob(os.path.join(game_path,'web*log'))[0]
        self.plan_file = glob(os.path.join(game_path,'plan*json'))[0]
        
        self.plan = json.load(open(self.plan_file))
        
        self.__parse_dialogue()
        self.__parse_questions()
        self.__parse_start_end()
        self.__parse_question_pairs()
        
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
            d = [x for x in self.dialogue_events if x[0] == self.__iter_ts]
            d = d if d else None
            # q = [x for x in self.question_pairs if (x[0][0] < self.__iter_ts) and (x[0][1] > self.__iter_ts)]
            q = [x for x in self.question_pairs if (x[0][1] == self.__iter_ts)]
            q = q[0] if q else None
            retval = (self.__iter_ts,d,q)
            self.__iter_ts += 1
            return retval
        self.__iter_ts = self.start_ts
        raise StopIteration()
    
    def __iter__(self):
        return self
    
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
        
        self.question_pairs = [((a[0], b[0], a[1], b[1], a[2], b[2]), (a[3], b[3])) for a,b in self.question_pairs]
                
        
    def __parse_dialogue(self):
        save_path = os.path.join(self.game_path,f'dialogue_{self.game_path.split("/")[1]}.pkl')
        if os.path.isfile(save_path):
            self.dialogue_events = pickle.load(open( save_path, "rb" ))
            return
        self.dialogue_events = []
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
            self.end_ts = min(self.end_ts, self.questions[-1][0])
        except Exception as e:
            pass


class Model(nn.Module):
    def __init__(self):
        super(Model, self).__init__()
        
        self.plan_embedder0 = nn.GRU(81,32)
        self.plan_embedder1= nn.GRU(81,32)
        self.plan_embedder2= nn.GRU(81,32)
        
        self.dialogue_listener = nn.GRU(1126,768,batch_first=True)
        
        self.q01 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,2),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q02 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,2),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q03 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,2),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )
        
        self.q11 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,3),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q12 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,3),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q13 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,22),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )
        
        self.q21 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,3),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q22 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,3),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )        
        self.q23 = nn.Sequential(
            nn.Linear(96+768,512),
            nn.Dropout(0.5),
            nn.GELU(),
            nn.Dropout(0.5),
            nn.Linear(512,22),
            nn.Dropout(0.5),
            nn.Softmax(-1)
        )
    
    def forward(self,game):
        retval = []
        plan_emb = torch.cat([
            self.plan_embedder0(torch.stack(list(map(torch.tensor,game.global_plan))).reshape(-1,1,81).float().to(DEVICE))[0][-1][0],
            self.plan_embedder1(torch.stack(list(map(torch.tensor,game.player1_plan))).reshape(-1,1,81).float().to(DEVICE))[0][-1][0],
            self.plan_embedder2(torch.stack(list(map(torch.tensor,game.player2_plan))).reshape(-1,1,81).float().to(DEVICE))[0][-1][0]
        ])
        if len(sys.argv) == 1:
            plan_emb = 0*plan_emb
        h = None
        for _, d, q in game:
            if not d is None:
                d = np.concatenate(([int(d[0][1]==2),int(d[0][1]==1)],d[0][-1]))
            else:
                d = np.zeros(1026)
            if len(sys.argv) > 1:
                d = np.zeros(1026)
            if not q is None:
                q ,l = q
                q = np.concatenate([
                    onehot(q[2],2),
                    onehot(q[3],2),
                    onehot(q[4][0][0]+1,2),
                    onehot(game.materials_dict[q[4][0][1]],len(game.materials_dict)),
                    onehot(q[4][1][0]+1,2),
                    onehot(game.materials_dict[q[4][1][1]],len(game.materials_dict)),
                    onehot(q[4][2]+1,2),
                    onehot(q[5][0][0]+1,2),
                    onehot(game.materials_dict[q[5][0][1]],len(game.materials_dict)),
                    onehot(q[5][1][0]+1,2),
                    onehot(game.materials_dict[q[5][1][1]],len(game.materials_dict)),
                    onehot(q[5][2]+1,2)
                    ])
            else:
                q = np.zeros(100)
                l = None
            x = torch.tensor(np.concatenate((d,q))).float().reshape(1,1,-1).to(DEVICE)
            y,h = self.dialogue_listener(x,h)
            
            if not l is None:
                retval.append( (
                    l, (
                        self.q01(torch.cat((plan_emb,y[0][0]))),
                        self.q02(torch.cat((plan_emb,y[0][0]))),
                        self.q03(torch.cat((plan_emb,y[0][0]))),
                        self.q11(torch.cat((plan_emb,y[0][0]))),
                        self.q12(torch.cat((plan_emb,y[0][0]))),
                        self.q13(torch.cat((plan_emb,y[0][0]))),
                        self.q21(torch.cat((plan_emb,y[0][0]))),
                        self.q22(torch.cat((plan_emb,y[0][0]))),
                        self.q23(torch.cat((plan_emb,y[0][0])))
                    )
                ) )



        return retval

dirs = sorted(glob('*_logs/*'))

games = list(map(GameParser, dirs))

# list(map(print,games[0].dialogue_events))
# list(map(print,games[0].questions))
# list(map(print,games[0].question_pairs))
# print(len(games),games[0].start_ts,games[0].end_ts)
# print(len([x for x in games if x.question_pairs]))
# print(games[0].materials_dict)
# # print(games[1].materials_dict)
# print(games[0].mines_dict)
# # print(games[1].mines_dict)
# print(games[0].tools_dict)
# print(games[1].tools_dict)

model = Model().to(DEVICE)
model.train()

optimizer = optim.Adam(model.parameters(), lr=0.0001)
criterion = nn.CrossEntropyLoss()

shuffle(games)
train = games[len(games)//5:]
test = games[:len(games)//5]

for epoch in range(200):
    data = []
    avg_loss = 0
    model.train()
    shuffle(train)
    for i, g in enumerate(train):
        loss = criterion(1000*torch.tensor([[-1.,1.]],requires_grad=True),torch.tensor([1])).to(DEVICE)
        # print(loss)
        l = model(g)
        for gt, prd in l:
            lbls = []
            for a,b in zip(gt[0],gt[1]):
                lbls.append(int(a==b))
            # lbls += [['NO', 'MAYBE', 'YES'].index(gt[0][1]),['NO', 'MAYBE', 'YES'].index(gt[0][1])]
            # if gt[0][2] in g.materials_dict:
            #     lbls.append(g.materials_dict[gt[0][2]])
            # else:
            #     lbls.append(0)
            # lbls += [['NO', 'MAYBE', 'YES'].index(gt[1][1]),['NO', 'MAYBE', 'YES'].index(gt[1][1])]
            # if gt[1][2] in g.materials_dict:
            #     lbls.append(g.materials_dict[gt[1][2]])
            # else:
            #     lbls.append(0)
            data.append([])
            for p,l in zip(prd,lbls):
                data[-1].append((torch.argmax(p).item(),l))
                loss += criterion(p.reshape(1,-1),torch.tensor([l]).to(DEVICE))
            
        try:
            avg_loss += loss.item()
        except Exception as e:
            pass
        loss.backward()
        nn.utils.clip_grad_norm_(model.parameters(), 1)
        optimizer.step()
    #     if i % 10 == 0:
    #         print('#', end='', flush=True)
    # print('# ', end='', flush=True)
    print(f'{os.getpid():6d}, {epoch+1:4d}, {avg_loss/len(train):9.4f}',end='; ',flush=True)
    data = list(zip(*data))
    for x in data[:3]:
        a, b = list(zip(*x))
        print(f'({accuracy_score(a,b):5.3f},{f1_score(a,b,average="weighted"):5.3f},{sum(b)/len(b):5.3f})', end=' ',flush=True)
    for x in data[3:]:
        a, b = list(zip(*x))
        print(f'({accuracy_score(a,b):5.3f},{f1_score(a,b,average="weighted"):5.3f})', end=' ',flush=True)
    print('', end='; ',flush=True)
    # print(len(data),len(data[0]),len(data[0][0]))
    data = []
    avg_loss = 0
    model.eval()
    for g in test:
        loss = criterion(1000*torch.tensor([[-1.,1.]],requires_grad=True),torch.tensor([1])).to(DEVICE)
        l = model(g)
        for gt, prd in l:
            lbls = []
            for a,b in zip(gt[0],gt[1]):
                lbls.append(int(a==b))
            # lbls += [['NO', 'MAYBE', 'YES'].index(gt[0][1]),['NO', 'MAYBE', 'YES'].index(gt[0][1])]
            # if gt[0][2] in g.materials_dict:
            #     lbls.append(g.materials_dict[gt[0][2]])
            # else:
            #     lbls.append(0)
            # lbls += [['NO', 'MAYBE', 'YES'].index(gt[1][1]),['NO', 'MAYBE', 'YES'].index(gt[1][1])]
            # if gt[1][2] in g.materials_dict:
            #     lbls.append(g.materials_dict[gt[1][2]])
            # else:
            #     lbls.append(0)
                
            data.append([])
            for p,l in zip(prd,lbls):
                data[-1].append((torch.argmax(p).item(),l))
                loss += criterion(p.reshape(1,-1),torch.tensor([l]).to(DEVICE))
                
        try:
            avg_loss += loss.item()
        except Exception as e:
            pass
        
    # print(flush=True)
    print(f'                , {avg_loss/len(train):9.4f}',end='; ',flush=True)
    data = list(zip(*data))
    for x in data[:3]:
        a, b = list(zip(*x))
        print(f'({accuracy_score(a,b):5.3f},{f1_score(a,b,average="weighted"):5.3f},{sum(b)/len(b):5.3f})', end=' ',flush=True)
    for x in data[3:]:
        a, b = list(zip(*x))
        print(f'({accuracy_score(a,b):5.3f},{f1_score(a,b,average="weighted"):5.3f}', end=' ',flush=True)
    print(flush=True)
    # break

