from game_parser import GameParser
import json
from glob import glob
from sklearn.metrics import accuracy_score, f1_score
import matplotlib.pyplot as plt
import numpy as np


plt.rcParams.update({'font.size': 20})

materials = glob('../mean/dist/img/materials/*.png') + glob('mean/dist/img/materials/*.png')
materials = sorted([m.split('/')[-1].split('.')[0] for m in materials])
materials = [m for m in materials if not 'NULL' in m]

dataset_splits = json.load(open('dataset_splits.json'))

games = [x for x in sum(dataset_splits.values(),[]) if 'saved' in x]
# games = sum(dataset_splits.values(),[])


game_dirs = sorted(glob('*_logs/*'))
# print('\n'.join(game_dirs))
game_nb = []
for g in game_dirs:
    date, time = g.split('_')[-2:]
    start_time = 0
    start_time += sum([31,28,31,30,31,30,31,31,30,31,30,31][:int(date[4:6])])*24*60
    start_time += int(date[6:8])*24*60
    start_time += int(time[:2])*60 + int(time[2:4]) #+ int(time[4:6])/60
    if not game_nb:
        game_nb.append((g,1,start_time))
    else:
        if start_time-game_nb[-1][2]>45 or  start_time-game_nb[-1][2]<0:
            game_nb.append((g,1,start_time))
        else:
            game_nb.append((g,game_nb[-1][1]+1,start_time))
    # print(g,date,time,game_nb[-1])
game_nb = dict([(g[0],g[1]) for g in game_nb])
# for k,v in game_nb.items():
#     print(k,v)
        
# exit()

game_types = {}
for x in games:
    game = GameParser(x)
    q_pairs = [q for _,_,q,_ in game if not q is None]
    dlg = [d for _,d,_,_ in game if not d is None]
    dts = (game.end_ts - game.start_ts)
    plan = json.load(open(glob(x+'/plan*')[0]))
    skills = '   Shared' if (sum([x['tools'][0] for x in plan['full']]))==0 else 'Disparate'
    knowledge = 'Disparate' if -1 in sum([sum(x['make'],[]) for x in plan['player1']+plan['player2']],[]) else '   Shared'
    key = f'{skills} Skills & {knowledge} Knowledge'
    if not key in game_types:
        game_types[key] = []
    game_types[key].append((q_pairs,len(dlg),dts))
    # print(q_pairs)
    # exit()


for k,v in sorted(game_types.items(),key=lambda x: x[0]):
    v,d,dts = zip(*v)
    g,p = list(zip(*[x[1] for x in sum(v,[]) if not x[0][2]==x[0][3]]))
    g = list(zip(*g))
    p = list(zip(*p))
    print(k,sep=' & \t',end=' & \t')
    print(min(d),f'{np.mean(d):0.0f}({np.std(d):2.0f})',max(d),sep=' & \t',end=' & \t')
    print(f'{min(dts)//60}:{min(dts)%60:02d}',f'{int(np.mean(dts))//60:2d}:{int(np.mean(dts))%60:02d}({int(np.std(dts))//60:2d}:{int(np.std(dts))%60:02d})',f'{max(dts)//60}:{max(dts)%60:02d}',sep=' & \t',end=' & \t')
    for x,y in zip(g,p):
        print(f'{accuracy_score(x,y):0.3f}', f'{f1_score(x,y,average="weighted"):0.3f}',sep=' & \t',end=' & \t')
    # print(g,p)
    g = sum(g,())
    p = sum(p,())
    # print(g,p)
    a = [int(x==y) for x,y in zip(g,p)]
    print(f'{sum(a)/len(a):0.3f}','\\\\',sep=' & \t')
    # print(v[0][0])
exit()
dataset_splits = json.load(open('dataset_splits.json'))

games = sum(dataset_splits.values(),[])

data = []
for x in games:
    game = GameParser(x)
    q_pairs = [q for _,_,q,_ in game if not q is None]
    plan = json.load(open(glob(x+'/plan*')[0]))
    skills = 'Disparate' if (sum([x['tools'][0] for x in plan['full']]))==0 else '   Shared'
    knowledge = 'Disparate' if -1 in sum([sum(x['make'],[]) for x in plan['player1']+plan['player2']],[]) else '   Shared'
    key = f'{skills} Skills {knowledge} Knowledge'
    dts = (game.end_ts - game.start_ts)/60
    if q_pairs:
        g,p = zip(*[x[1] for x in q_pairs if not x[0][2]==x[0][3]])
        g = zip(*g)
        p = zip(*p)
        tpl = []
        for x,y in zip(g,p):
            tpl += [accuracy_score(x,y), f1_score(x,y,average="weighted")]
        data.append((dts, tpl))
dts, x = zip(*data)

x = zip(*x)

for v,a in zip(x,['a1','f1','a2','f2','a3','f3']):
    p1, p2 = dts, v
    
    plt.cla(); plt.clf();plt.close()
    plt.scatter(p1,p2)
    mp1 = np.mean(p1)
    mp2 = np.mean(p2)
    sp1 = np.std(p1)
    sp2 = np.std(p2)
    c = np.mean((np.array(p1)-mp1)*(np.array(p2)-mp2))/(sp1*sp2)
    plt.plot(np.unique(p1), np.poly1d(np.polyfit(p1, p2, 1))(np.unique(p1)), color='red')
    plt.xlabel('Accuracy' if a[0]=='a' else 'F1 Score')
    plt.ylabel('Game length (minutes)')
    
    plt.savefig(f'scatter_{a}.png')
    # print(c,np.linalg.det(np.corrcoef(p1,p2)),np.arctan(list(np.poly1d(np.polyfit(p1, p2, 1)))[0]))
    print(f'{c:0.3f}')



dataset_splits = json.load(open('dataset_splits.json'))

games = sum(dataset_splits.values(),[])

data = []
for gm in games:
    game = GameParser(gm)
    try:
        ts,q_pairs = zip(*[(t,q) for t,_,q,_ in game if not q is None])
    except Exception:
        continue
    plan = json.load(open(glob(gm+'/plan*')[0]))
    skills = 'Disparate' if (sum([x['tools'][0] for x in plan['full']]))==0 else '   Shared'
    knowledge = 'Disparate' if -1 in sum([sum(x['make'],[]) for x in plan['player1']+plan['player2']],[]) else '   Shared'
    key = f'{skills} Skills {knowledge} Knowledge'
    dts = (game.end_ts - game.start_ts)
    if q_pairs:
        g,p,ts = zip(*[x[0][1]+(x[1],) for x in zip(q_pairs,ts) if not x[0][0][2]==x[0][0][3]])
        for x,y,t in zip(g,p,ts):
            # tpl += [accuracy_score(x,y), f1_score(x,y,average="weighted")]
            
            data.append((max(0.1001,(t-game.start_ts)/dts), min(5,game_nb[gm]), x[0]==y[0], x[1]==y[1], x[2]==y[2]))

plt.rcParams.update({'font.size': 14})
ts, nb, e1,e2,e3 = zip(*data)
for i,(ex,ttl) in enumerate(zip([e1,e2,e3],['Completed Task Status', 'Other Player\'s Knowledge', 'Other Player\'s Current Task'])):
    plt.cla(); plt.clf();plt.close()
    fig, ax1 = plt.subplots(figsize=(7, 5))
    # bins = np.linspace(0, max(ts), int(max(ts)/(5*15)))
    bins = np.linspace(0.1, 1, 10)
    # bins[0]=0
    # bins = np.array(range(15))*75
    
    # bins = np.array([75,150,225,300,375,525,750,1100])
    h1 = np.histogram([t for t,e in zip(ts,ex) if e], bins)[0]
    h2 = np.histogram([t for t,e in zip(ts,ex) if not e], bins)[0]
    ax1.hist([t for t,e in zip(ts,ex) if e], bins, alpha=0.5, label='True')
    ax1.hist([t for t,e in zip(ts,ex) if not e], bins, alpha=0.5, label='False')
    ax1.set_xlabel('Time of Question Being Asked Relative to Game Length')
    ax1.set_ylabel('Question Count')
    # ax1.set_xticks(bins)
    ax1.legend(['Matches', 'Missmatches'])
    for x in bins:
        ax1.plot([x,x],[0,80],color='grey',linewidth=0.5,linestyle=':')
    
    ax2 = ax1.twinx()
    p1 = [np.mean([bins[i],bins[i+1]])for i in range(len(bins)-1)] #bins[1:]-bins[1]/2
    p2 = h1/(h2+h1)
    p1 = p1#[1:-1]
    p2 = p2#[1:-1]
    ax2.scatter(p1, p2, color='red', marker='x')
    ax2.tick_params(axis='y', labelcolor='red')
    print(np.poly1d(np.polyfit(p1, p2, 3)))
    ax1.set_ylim((0,45))
    # if i==0:
    #     ax2.set_ylim((0.4,0.9))
    # elif 1==1:
    #     ax2.set_ylim((0.45,0.75))
    # else:
    #     ax2.set_ylim((0.2,0.7))
    ax2.set_ylim((0,1))
    ax2.plot(np.unique(p1), np.poly1d(np.polyfit(p1, p2, 3))(np.unique(p1)), color='red')#,marker='x')
    ax2.set_ylabel('Ratio of matches to total')
    plt.title(ttl)
    # ax1.set_xticks([f'{b:0.1f}' for b in bins])
    # ax2.set_xticks([np.round(b) for b in bins])
    ax1.set_xticks(bins)
    ax2.set_xticks(bins)
    # plt.xticks(bins,rotation='vertical')
    plt.savefig(f'hist_{i}_game_len.png')
    # plt.cla(); plt.clf();plt.close()
    # fig, ax1 = plt.subplots(figsize=(6, 6))
    # # bins = np.linspace(0, max(ts), int(max(ts)/(5*15)))
    # bins = np.linspace(0.5, 5.5, 6)
    # # bins[0]=0
    # # bins = np.array(range(15))*75
    
    # # bins = np.array([75,150,225,300,375,525,750,1100])
    # h1 = np.histogram([t for t,e in zip(nb,ex) if e], bins)[0]
    # h2 = np.histogram([t for t,e in zip(nb,ex) if not e], bins)[0]
    # ax1.hist([t for t,e in zip(nb,ex) if e], bins, alpha=0.5, label='True')
    # ax1.hist([t for t,e in zip(nb,ex) if not e], bins, alpha=0.5, label='False')
    # ax1.set_xlabel('Time of Question Being Asked Relative to Game Length')
    # ax1.set_ylabel('Question Count')
    # # ax1.set_xticks(bins)
    # ax1.legend(['Matches', 'Missmatches'])
    # for x in bins:
    #     ax1.plot([x,x],[0,80],color='grey',linewidth=0.5,linestyle=':')
    
    # ax2 = ax1.twinx()
    # p1 = [np.mean([bins[i],bins[i+1]])for i in range(len(bins)-1)] #bins[1:]-bins[1]/2
    # p2 = h1/(h2+h1)
    # p1 = p1#[1:-1]
    # p2 = p2#[1:-1]
    # ax2.scatter(p1, p2, color='red', marker='x')
    # ax2.tick_params(axis='y', labelcolor='red')
    # print(np.poly1d(np.polyfit(p1, p2, 2)))
    # ax1.set_ylim((0,100))
    # # if i==0:
    # #     ax2.set_ylim((0.4,0.9))
    # # elif 1==1:
    # #     ax2.set_ylim((0.45,0.75))
    # # else:
    # #     ax2.set_ylim((0.2,0.7))
    # ax2.set_ylim((0,1))
    # ax2.plot(np.unique(p1), np.poly1d(np.polyfit(p1, p2, 2))(np.unique(p1)), color='red')#,marker='x')
    # ax2.set_ylabel('Ratio of matches to total')
    # plt.title(ttl)
    # # ax1.set_xticks([f'{b:0.1f}' for b in bins])
    # # ax2.set_xticks([np.round(b) for b in bins])
    # ax1.set_xticks(bins)
    # ax2.set_xticks(bins)
    # # plt.xticks(bins,rotation='vertical')
    # plt.savefig(f'hist_{i}_game_nb.png')

# print(data)
games = sum(dataset_splits.values(),[])
data = []
for x in games:
    data.append([])
    game = GameParser(x,True,0)
    for t,d,q,_ in game:
        if not q is None:
            data[-1].append((q,[]))
        if not d is None:
            if data[-1]:
                data[-1][-1][1].append(d)
    game = GameParser(x,True,4)
    for t,d,q,_ in game:
        if not q is None:
            data[-1].append((q,[]))
        if not d is None:
            if data[-1]:
                data[-1][-1][1].append(d)
hdata1 = [[],[],[]]
hdata2 = [[],[],[]]
hdata3 = [[],[],[]]
hdata4 = [[],[],[]]
for game in data:
    if not game:
        continue
    q, n = zip(*game)
    ans1, ans2 = zip(*[x[-1] for x in q])
    ans1 = list(zip(*ans1))
    ans2 = list(zip(*ans2))
    for i in range(3):
        for a,b,c in zip(n,ans1[i],ans2[i]):
            hdata1[i].append((len(a),b==c))
    for i in range(3):
        for a,b,c in zip(n,ans1[i][1:],ans2[i][1:]):
            hdata2[i].append((len(a),b==c))
    for i in range(3):
        for a,b,c in zip(n[1:],ans1[i],ans2[i]):
            hdata3[i].append((len(a),b==c))
    for i in range(3):
        for a,b,c in zip(n,ans1[i][2:],ans2[i][2:]):
            hdata4[i].append((len(a),b==c))
hdata5 = [h1+h2 for h1,h2 in zip(hdata1,hdata3)]
hdata6 = [h1+h2 for h1,h2 in zip(hdata2,hdata4)]

for i,ttl in enumerate(['Completed Task Status', 'Other Player\'s Knowledge', 'Other Player\'s Current Task']):
    for j,hdata in enumerate([hdata1,hdata2,hdata3,hdata4,hdata5,hdata6]):
        plt.cla(); plt.clf();plt.close()
        fig, ax1 = plt.subplots(figsize=(7, 5))
        # print(max([x[0] for x in hdata]))
        # bins = np.linspace(0, max([x[0] for x in hdata]), max([x[0] for x in hdata])//2)
        bins = np.array([1,2,3,4,5,6,7,8,9,11,14])

        h1 = np.histogram([t for t,e in hdata[i] if e], bins)[0]
        h2 = np.histogram([t for t,e in hdata[i] if not e], bins)[0]
        ax1.hist([t for t,e in hdata[i] if e], bins, alpha=0.5, label='True')
        ax1.hist([t for t,e in hdata[i] if not e], bins, alpha=0.5, label='False')
        ax1.set_xlabel('number of dialogue exchanges')
        ax1.set_ylabel('Question Count')
        # ax1.set_xticks(bins)
        ax1.legend(['Matches', 'Missmatches'])
        for x in bins:
            ax1.plot([x,x],[0,max(max(h1),max(h2))+10],color='grey',linewidth=0.5,linestyle=':')

        ax2 = ax1.twinx()
        p1 = [np.mean([bins[i],bins[i+1]])for i in range(len(bins)-1)] #bins[1:]-bins[1]/2
        p2 = h1/(h2+h1)
        p1 = p1#[1:-1]
        p2 = p2#[1:-1]
        ax2.scatter(p1, p2, color='red', marker='x')
        ax2.tick_params(axis='y', labelcolor='red')
        # print(np.poly1d(np.polyfit(p1, p2, 3)))
        ax1.set_ylim((0,max(max(h1),max(h2))+10))
        # if i==0:
        #     ax2.set_ylim((0.4,0.9))
        # elif 1==1:
        #     ax2.set_ylim((0.45,0.75))
        # else:
        #     ax2.set_ylim((0.2,0.7))
        ax2.set_ylim((0,1))
        ax2.plot(np.unique(p1), np.poly1d(np.polyfit(p1, p2, 3))(np.unique(p1)), color='red')#,marker='x')
        ax2.set_ylabel('Ratio of matches to total')
        plt.title(ttl)
        # ax1.set_xticks([f'{b:0.1f}' for b in bins])
        # ax2.set_xticks([np.round(b) for b in bins])
        ax1.set_xticks(bins)
        ax2.set_xticks(bins)
        # plt.xticks(bins,rotation='vertical')
        plt.savefig(f'hist_dlg_count_{i}_{j}.png')


hdata1 = [[],[],[]]
for game in data:
    if not game:
        continue
    q, n = zip(*game)
    ans1, ans2 = zip(*[x[-1] for x in q])
    ans1 = list(zip(*ans1))
    ans2 = list(zip(*ans2))
    for i in range(3):
        for a,b,c,d,e in zip(n,ans1[i],ans2[i],ans1[i][1:],ans2[i][1:]):
            hdata1[i].append((0,len(a),b==c,d==e))
    for i in range(3):
        for a,b,c,d,e in zip(n,ans1[i][1:],ans2[i][1:],ans1[i][2:],ans2[i][2:]):
            hdata1[i].append((1,len(a),b==c,d==e))
    # for i in range(3):
    #     for a,b,c in zip(n,ans1[i][2:],ans2[i][2:]):
    #         hdata1[i].append((2,len(a),b==c))
    for i in range(3):
        for a,b,c,d,e in zip(n[1:],ans1[i],ans2[i],ans1[i][1:],ans2[i][1:]):
            hdata1[i].append((-1,len(a),b==c,d==e))
    # for i in range(3):
    #     for a,b,c in zip(n[2:],ans1[i],ans2[i]):
    #        hdata1[i].append((-2,len(a),b==c))
for i,ttl in enumerate(['Completed Task Status', 'Other Player\'s Knowledge', 'Other Player\'s Current Task']):
    for j,hdata in enumerate([hdata1]):
        plt.cla(); plt.clf();plt.close()
        fig, ax1 = plt.subplots(figsize=(7, 5))
        # print(max([x[0] for x in hdata]))
        # bins = np.linspace(0, max([x[0] for x in hdata]), max([x[0] for x in hdata])//2)
        bins = np.array([1,2])
        
        x1 = [[b for a,b,c,_ in hdata1[i] if c and a==tp] for tp in range(-1,1)]
        x2 = [[b for a,b,c,_ in hdata1[i] if not c and a==tp] for tp in range(-1,1)]
        s1 = np.array(list(map(np.std,x1)))
        s2 = np.array(list(map(np.std,x2)))
        l1 = np.array(list(map(len,x1)))
        l2 = np.array(list(map(len,x2)))
        x1 = np.array(list(map(np.mean,x1)))
        x2 = np.array(list(map(np.mean,x2)))
        c1 = [1.96*s/np.sqrt(l) for s,l in zip(s1,l1)]
        c2 = [1.96*s/np.sqrt(l) for s,l in zip(s2,l2)]
        width = 2/5
        ax1.bar(bins-width/2, x1, width, color='blue', yerr=c1, capsize=7, alpha=0.5, label='Match')#, marker='x')
        ax1.bar(bins+width/2, x2, width, color='orange', yerr=c2, capsize=7, alpha=0.5, label='Miss')#, marker='x')
        ax1.set_ylabel('Average Number of Dialogue Utternaces')
        ax1.set_ylim(0,7)
        ax1.set_xticks(bins)
        ax1.set_xticklabels(['Before\nQuestion','After\nQuestion',])
        plt.title(ttl)
        plt.legend()
        ax1.set_xticks(bins)
        # exit()
        plt.savefig(f'avg_dlg_count_a_{i}_{j}.png')
        
        plt.cla(); plt.clf();plt.close()
        fig, ax1 = plt.subplots(figsize=(7, 5))
        x1,x2 = zip(x1,x2)
        s1,s2 = zip(s1,s2)
        c1,c2 = zip(c1,c2)
        # print(x1)
        # print(x2)
        width = 2/5
        ax1.bar(bins-width/2, x1, width, color='blue', yerr=c1, capsize=7, alpha=0.5, label='Before Question')#, marker='x')
        ax1.bar(bins+width/2, x2, width, color='orange', yerr=c2, capsize=7, alpha=0.5, label='After Question')#, marker='x')
        ax1.set_ylabel('Average Number of Dialogue Utternaces')
        ax1.set_ylim(0,7)
        ax1.set_xticks(bins)
        ax1.set_xticklabels(['Match','Miss',])
        plt.title(ttl)
        plt.legend()
        
        
        # plt.plot(bins,x1,color='blue')
        # plt.plot(bins,x2,color='orange')
        # ax2 = ax1.twinx()
        # ax2.bar(bins,x1/(x1+x2), color='red' , alpha=0.5)#, marker='x')
        ax1.set_xticks(bins)
        # exit()
        plt.savefig(f'avg_dlg_count_{i}_{j}.png')
        
        plt.cla(); plt.clf();plt.close()
        fig, ax1 = plt.subplots(figsize=(7, 5))
        bins = np.array([1,2,3,4])
        
        x1 = [[b for a,b,c,d in hdata1[i] if     c and     d and a==tp] for tp in range(-1,2)]
        x2 = [[b for a,b,c,d in hdata1[i] if not c and     d and a==tp] for tp in range(-1,2)]
        x3 = [[b for a,b,c,d in hdata1[i] if     c and not d and a==tp] for tp in range(-1,2)]
        x4 = [[b for a,b,c,d in hdata1[i] if not c and not d and a==tp] for tp in range(-1,2)]
        tr_mat = np.array([[len(x1[1]), len(x2[1])],[len(x3[1]),len(x4[1])]])
        # print(ttl,tr_mat/tr_mat.sum(axis=-1).reshape(2,1),sep='\n')
        print(ttl,tr_mat,sep='\n')
        s1 = np.array(list(map(np.std,x1)))
        s2 = np.array(list(map(np.std,x2)))
        s3 = np.array(list(map(np.std,x3)))
        s4 = np.array(list(map(np.std,x4)))
        l1 = np.array(list(map(len,x1)))
        l2 = np.array(list(map(len,x2)))
        l3 = np.array(list(map(len,x3)))
        l4 = np.array(list(map(len,x4)))
        x1 = np.array(list(map(np.mean,x1)))
        x2 = np.array(list(map(np.mean,x2)))
        x3 = np.array(list(map(np.mean,x3)))
        x4 = np.array(list(map(np.mean,x4)))
        c1 = [1.96*s/np.sqrt(l) for s,l in zip(s1,l1)]
        c2 = [1.96*s/np.sqrt(l) for s,l in zip(s2,l2)]
        c3 = [1.96*s/np.sqrt(l) for s,l in zip(s3,l3)]
        c4 = [1.96*s/np.sqrt(l) for s,l in zip(s4,l4)]
        x1,x2,x3 = zip(x1,x2,x3,x4)
        s1,s2,s3 = zip(s1,s2,s3,s4)
        c1,c2,c3 = zip(c1,c2,c3,c4)
        # print(x1)
        # print(x2)
        # print(x3)
        # print(x4)
        width = 1/4
        ax1.bar(bins-  width, x1, width, color='blue', alpha=0.5, yerr=c1, capsize=7, label='Before first question')#, marker='x')
        # for i,m,s,l in zip(bins-width,x1,s1,l1):
        #     c = 1.96*s/np.sqrt(l)
        #     ax1.plot([i,i],[m-c,m+c],linewidth=1,color='black')
        #     # ax1.plot([i+0.75,i+1.25],[m-c,m+c],linewidth=1,color='black')
        #     # ax1.plot([i+0.75,i+1.25],[m-c,m+c],linewidth=1,color='black')
        ax1.bar(bins-0*width, x2, width, color='orange' , alpha=0.5, yerr=c2, capsize=7, label='In Between Questions')#, marker='x')
        ax1.bar(bins+  width, x3, width, color='green', alpha=0.5, yerr=c3, capsize=7, label='After second Question')#, marker='x')
        # ax1.bar(bins+3*width/2, x4, width, color='red' , alpha=0.5)#, marker='x')
        ax1.set_ylabel('Average Number of Dialogue Utternaces')
        ax1.set_ylim(0,9)
        ax1.set_xticks(bins)
        ax1.set_xticklabels(['Q1. match\nQ2. match','Q1. miss\nQ2. match','Q1. match\nQ2. miss','Q1. miss\nQ2. miss'])
        plt.title(ttl)
        plt.legend()
        plt.savefig(f'avg_dlg_count_2nd_{i}_{j}.png')
        
        continue
        
        
