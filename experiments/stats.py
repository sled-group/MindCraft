#%%
from sklearn.metrics import accuracy_score, f1_score
from sklearn.utils import validation
from statsmodels.stats.contingency_tables import mcnemar,cochrans_q
from scipy.stats import chisquare
from statsmodels.stats.api import SquareTable
from glob import glob
import numpy as np
import matplotlib.pyplot as plt
import json
from game_parser import GameParser

models = ['LSTM', 'Transformer', 'GRU']
exps = list(range(9))
dialogue = ['Yes', 'No']
point_of_view = ['None', 'Third', 'First']

folder = 'out_without_plan'

# for model in models:
#     for exp in exps:
#         files = glob(f'{folder}/*{model}_{exp}.log')
#         print(files)


#         data = {}
#         for f in files:
#             g, p = zip(*[x[0]for x in eval(open(f).readlines()[-2])])
#             data[f] = (g,p)
            
#         keys = data.keys()


#         for i,k1 in enumerate(sorted(list(data.keys()))):
#             for k2 in sorted(list(data.keys()))[i:]:
#                 if k1 == k2:
#                     continue
                
#                 g1 = data[k1][0]
#                 g2 = data[k2][0]
#                 p1 = data[k1][1]
#                 p2 = data[k2][1]
#                 if len(p1) < len(p2):
#                     p1 = p1+p1
#                     g1 = g1+g1
#                 elif len(p1) > len(p2):
#                     p2 = p2+p2
#                     g2 = g2+g2
#                 else:
#                     pass
                
#                 a1 = accuracy_score(g1, p1)
#                 a2 = accuracy_score(g2, p2)
                
#                 f1 = f1_score(g1,p1,average='weighted')
#                 f2 = f1_score(g2,p2,average='weighted')
                
#                 m1 = [int(x==y) for x,y in zip(p1, g1)]
#                 m2 = [int(x==y) for x,y in zip(p2, g2)]
                
#                 # match = [(x,y) for x,y in zip(p1,p2)]
#                 match = [(x,y) for x,y in zip(m1,m2)]
                
#                 # dim = max(map(max, [g1,g2,p1,p2])) + 1
#                 dim = 2
#                 table = np.zeros((dim,dim))#[[0,0],[0,0]]
#                 for x,y in match:
#                     table[x][y] += 1
                    
#                 # if dim == 2:
#                 #     pval = mcnemar(table).pvalue
#                 # else:
#                 #     pval=SquareTable(table, shift_zeros=False).symmetry().pvalue
#                 #     # pval = cochrans_q(list(zip(p1,p2))).pvalue
#                 pval = mcnemar(table).pvalue
#                 print(f'{k1.split("_")[4]},\t{k1[4:-4].split("_")[5]}', end=',\t')
#                 print(f'{k1.split("_")[1]},\t{k1.split("_")[3]}', end=',\t')
#                 print(f'{k2.split("_")[1]},\t{k2.split("_")[3]}', end=',\t')
#                 print(f'{dim:2d},\t{a1:0.3f},\t{f1:0.3f},\t{a2:0.3f},\t{f2:0.3f},\t{pval:0.3f}', end=',\t')
#                 if pval < 0.05:
#                     print('^')
#                 else:
#                     print()
                
#         print() 
# print() 
# print()

# for dlg in dialogue:
#     for pov in point_of_view:
#         for exp in exps:
#             files = glob(f'{folder}/*{dlg}*{pov}*{exp}.log')

#             if not files:
#                 continue
#             data = {}
#             for f in files:
#                 g, p = zip(*[x[0]for x in eval(open(f).readlines()[-2])])
#                 data[f] = (g,p)
                
#             keys = data.keys()


#             for i,k1 in enumerate(sorted(list(data.keys()))):
#                 for k2 in sorted(list(data.keys()))[i:]:
#                     if k1 == k2:
#                         continue
                    
#                     g1 = data[k1][0]
#                     g2 = data[k2][0]
#                     p1 = data[k1][1]
#                     p2 = data[k2][1]
                    
#                     a1 = accuracy_score(g1, p1)
#                     a2 = accuracy_score(g2, p2)
                    
#                     f1 = f1_score(g1,p1,average='weighted')
#                     f2 = f1_score(g2,p2,average='weighted')
                    
#                     m1 = [int(x==y) for x,y in zip(p1, g1)]
#                     m2 = [int(x==y) for x,y in zip(p2, g2)]
                    
#                     # match = [(x,y) for x,y in zip(p1,p2)]
#                     match = [(x,y) for x,y in zip(m1,m2)]
                    
#                     # dim = max(map(max, [g1,g2,p1,p2])) + 1
#                     dim = 2
#                     table = np.zeros((dim,dim))
#                     for x,y in match:
#                         table[x][y] += 1
                        
#                     # if dim == 2:
#                     #     pval = mcnemar(table).pvalue
#                     # else:
#                     #     # continue
#                     #     if len(p1) < len(p2):
#                     #         p1 = p1+p1
#                     #     elif len(p1) > len(p2):
#                     #         p2 = p2+p2
#                     #     else:
#                     #         pass
#                     #     # pval = cochrans_q(list(zip(p1,p2))).pvalue
#                     #     pval=SquareTable(table, shift_zeros=False).symmetry().pvalue
#                     pval = mcnemar(table).pvalue
#                     print(f'{k1.split("_")[1]},\t{k1.split("_")[3]},\t{k1[4:-4].split("_")[5]}', end=',\t')
#                     print(f'{k1.split("_")[4]:12s}', end=',\t')
#                     print(f'{k2.split("_")[4]:12s}', end=',\t')
#                     print(f'{dim:2d},\t{a1:0.3f},\t{f1:0.3f},\t{a2:0.3f},\t{f2:0.3f},\t{pval:0.3f}', end=',\t')
#                     if pval < 0.05:
#                         print('^')
#                     else:
#                         print()
                    
#             print()

# for dlg in dialogue:
#     for pov in point_of_view:
#         for m in models[:2]:
#             for exp in [3,4,5]:
#                 files = glob(f'{folder}/*{dlg}*{pov}*{m}*{[exp,exp+3]}.log')
#                 if not files:
#                     continue
#                 data = {}
#                 for f in files:
#                     g, p = zip(*[x[0]for x in eval(open(f).readlines()[-2])])
#                     data[f] = (g,p)
                    
#                 keys = data.keys()
#                 for i,k1 in enumerate(sorted(list(data.keys()))):
#                     for k2 in sorted(list(data.keys()))[i:]:
#                         if k1 == k2:
#                             continue
                        
#                         g1 = data[k1][0]
#                         g2 = data[k2][0]
                        
#                         print(k1,k2, f1_score(g1,g2, average='weighted'))


# folder = 'out_without_plan'
# for exp in [0,1,2]:

plt.rcParams.update({'font.size': 20})

dataset_splits = json.load(open('dataset_splits.json'))['test']
plans = dict([(f,json.load(open(glob(f+'/plan*')[0]))) for f in dataset_splits])
games  = [(f,GameParser(f,False,0)) for f in dataset_splits]
games += [(f,GameParser(f,False,4)) for f in dataset_splits]

materials = glob('../mean/dist/img/materials/*.png') + glob('mean/dist/img/materials/*.png')
materials = sorted([m.split('/')[-1].split('.')[0] for m in materials])
materials = [m for m in materials if not 'NULL' in m]
mines = [m for m in materials if 'PLANK' in m]
materials = [m for m in materials if not 'PLANK' in m]
tools = glob('../mean/dist/img/tools/*.png') + glob('mean/dist/img/tools/*.png')
tools = sorted([t.split('/')[-1].split('.')[0] for t in tools])
tools = [t for t in tools if not 'NULL' in t]
materials_dict = {x:i+1 for i,x in enumerate(materials)}
mines_dict = {x:i+1 for i,x in enumerate(mines)}
tools_dict = {x:i+1 for i,x in enumerate(tools)}

exts = ["a","b","c","d","e","f","g","h","i","j"]
for folder in ['out_with_plan']:#, 'out_without_plan']:
    for exp in [6,7,8]:

# exts = ["a","b","c","d","e","f","g"]
# for folder in ['out_with_plan', 'out_without_plan']:
#     for exp in [0,1,2]:
        data2 = []
        for m in models[:2]:
            for dlg in dialogue:
                for pov in point_of_view:
                
                
                    files = sorted(glob(f'{folder}/*{dlg}*{pov}*{m}*{exp}.log') + \
                            glob(f'{folder}/*{dlg}*{pov}*{m}*{exp}_{exts}.log'))
                    # print('\n'.join(files))
                    # files = sorted(glob(f'{folder}/*{dlg}*{pov}*{m}*{exp}_{["a","b","c","d","e"]}.log'))
                    
                    try:
                        data = []
                        for file in files:
                            g, p = zip(*[x[0]for x in eval(open(file).readlines()[-2])])
                            
                            # print(file)
                            validation = [x.strip()for x in open(file).readlines() if len(x)>1 and x[-2]=='^']
                            if validation:
                                validation = validation[-1]
                            else:
                                print('no val')
                            # print('asdf',file,float(validation.split(',')[-2]))
                            # print(validation.split(',')[-2])
                            # print(validation.split(',')[-3].split('(')[-1])
                            fv = float(validation.split(',')[-2])
                            av = float(validation.split(',')[-3].split('(')[-1])
                            # exit()
                            
                            asdf = [sum([1 for x in g if x==y]) for y in range(max(max(g)+1,3))]
                            # print('>',asdf, sum(asdf), max(asdf)/sum(asdf))
                            asdf = [sum([1 for x in p if x==y]) for y in range(22 if exp==8 else 3)]
                            # print('<',asdf, sum(asdf), max(asdf)/sum(asdf))
                            # print()
                            a = accuracy_score(g,p)
                            f = f1_score(g,p,average="weighted")
                            tss = [(x[0]-game.start_ts)/(game.end_ts-game.start_ts) for folder, game in games for x in game if not x[2] is None]
                            if exp in [6,7]:
                                g2, p2 = zip(*[(a,b if not b==1 else 0) for a,b in zip(g,p) if not g==1])
                                a2 = accuracy_score(g2,p2)
                                f2 = f1_score(g2,p2,average="weighted")
                            elif not exp in [0,1,2]:
                                # print(dataset_splits.__len__())
                                # exit()
                                g2,p2,a2,f2 = [],[],0,0
                                qwer = [plans[folder] for folder, game in games for x in game if not x[2] is None]
                                
                                # print(tss)
                                # exit()
                                for gg,pp,pl in zip(g,p,qwer):
                                    # print(gg,pp,pl)
                                    cand = [gg]
                                    for mat_idx,mat in enumerate(pl['full']):
                                        if materials_dict[pl['materials'][mat_idx]]==gg:
                                            if mat['make']:
                                                for z in mat['make'][0]:
                                                    cand.append(materials_dict[pl['materials'][z]])
                                                    # print('child added',cand)
                                        if mat['make']:
                                            if gg in [materials_dict[pl['materials'][midx]] for midx in mat['make'][0]]:
                                                cand.append(materials_dict[pl['materials'][mat_idx]])
                                                # print('parent added',cand)
                                            # print(gg,mat_idx,mat['make'],materials_dict[pl['materials'][mat_idx]])#,materials[mat['make'][0]])
                                    g2.append(gg)
                                    if pp in cand:
                                        p2.append(gg)
                                    else:
                                        p2.append(pp)
                                a2 = accuracy_score(g2,p2)
                                f2 = f1_score(g2,p2,average="weighted")
                                    # exit()
                            else:
                                g2,p2,a2,f2 = [],[],0,0
                            data.append((g,p,a,f,g2,p2,a2,f2,av,fv,tss))
                        g,p,a,f,g2,p2,a2,f2,av,fv,tss = zip(*data)
                        
                        # _pov = '1st' if pov == "First" else ('3rd' if pov == 'Third' else 'No')
                        # _pov = 'No' if pov == 'None' else 'Yes'
                        if dlg=='Yes':
                            if pov=='None':
                                xtick_str = 'D'
                            else:
                                xtick_str = 'D+V'
                        else:
                            xtick_str = 'V'
                        xtick_str = '\n'.join([xtick_str,f'{m if m=="LSTM" else "Tran."}'])
                        data2.append((g,p,a,f,g2,p2,a2,f2,av,fv,tss,xtick_str))
                        
                        if int(files[0][-5])==6:
                            print('Predicting Completed Task Status & ', end=' ')
                        elif int(files[0][-5])==7:
                            print('Predicting Other Player\'s Knowledge & ', end=' ')
                        elif int(files[0][-5])==8:
                            print('Predicting Other Player\'s Current Task & ', end=' ')
                        
                        if 'Yes' in files[0]:
                            if 'First' in files[0]:
                                print('D+V & ',  end=' ')
                            else:
                                print('D   & ',end=' ')
                        else:
                            print('  V & ', end=' ')
                        print(f'{files[0].split("_")[-2]} & {np.mean(f):0.3f}({1.96*np.std(f)/np.sqrt(len(f)):0.3f})', end=' ')
                        print(f' {np.mean(f):0.3f}({1.96*np.std(fv)/np.sqrt(len(fv)):0.3f}) \\\\')
                    except Exception as e:
                        # print(e, file)
                        pass
            print()
            # data2. append(([],[],[],[],''))
        # print('--------------------------------------------------------------------------------')
        print()
        # continue

        # g,p,a,f,xlbls = zip(*data2[:-1])
        g1,p1,a1,f1,g2,p2,a2,f2,av,fv,tss,xlbls = zip(*data2)
        
        print(len(tss))
        print(len(tss[0]))
        print(len(tss[0][0]))
        
        for idx,(_g,_p,_ts) in enumerate(zip(g2,p2,tss)):
            if exp == 0:
                exp_str = 'Predicting Agreement on Completed Task Status'
                exp_str2 = 'Predicting Agreement on Completed Task Status'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 1:
                exp_str = 'Predicting Agreement on Partner\'s Knowledge'
                exp_str2 = 'Predicting Agreement on Partner\'s Knowledge'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 2:
                exp_str = 'Predicting Agreement on Partner\'s Current Task with Strict Matching'
                exp_str2 = 'Predicting Agreement on Partner\'s Current Task with Strict Matching'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 6:
                exp_str = 'Predicting Completed Task Status'
                exp_str2 = 'Predicting Completed Task Status'
                human = 0.662
                rnd = 1/3
                lmy = (0.3,0.7)
            elif exp == 7:
                exp_str = 'Predicting Partner\'s Knowledge'
                exp_str2 = 'Predicting Partner\'s Knowledge'
                human = 0.613
                rnd = 1/3
                lmy = (0.3,0.7)
            else:
                exp_str = 'Predicting Partner\'s Current Task with Strict Matching'
                # exp_str2 = 'Predicting Partner\'s Current Task with Loose Matching'
                exp_str2 = 'Predicting Partner\'s Current Task'
                human = 0.362
                rnd = 1/22
                lmy = (0,0.5)
            ts = sum(_ts,[])
            gg = sum([list(xx) for xx in _g],[])
            pp = sum([list(xx) for xx in _p],[])
            plt.cla(); plt.clf();plt.close()
            fig, ax1 = plt.subplots(figsize=(9, 6))
            bins = np.linspace(0.1, 1, 10)
            ay = [t for t,p,g in zip(ts,pp,gg) if p==g]
            an = [t for t,p,g in zip(ts,pp,gg) if not p==g]
            h1 = np.histogram(ay, bins)[0]
            h2 = np.histogram(an, bins)[0]
            ax1.hist(ay, bins, alpha=0.5, label='True')
            ax1.hist(an, bins, alpha=0.5, label='False')
            ax1.set_xlabel('Time of Question Being Asked Relative to Game Length')
            ax1.set_ylabel('Question Count')
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
            ax1.set_ylim((0,245))
            # if i==0:
            #     ax2.set_ylim((0.4,0.9))
            # elif 1==1:
            #     ax2.set_ylim((0.45,0.75))
            # else:
            #     ax2.set_ylim((0.2,0.7))
            ax2.set_ylim((0,1))
            ax2.plot(np.unique(p1), np.poly1d(np.polyfit(p1, p2, 3))(np.unique(p1)), color='red')#,marker='x')
            ax2.set_ylabel('Ratio of matches to total')
            
            plt.title(f'{exp_str if idx==0 else exp_str2}')
            # ax1.set_xticks([f'{b:0.1f}' for b in bins])
            # ax2.set_xticks([np.round(b) for b in bins])
            ax1.set_xticks(bins)
            ax2.set_xticks(bins)
            # plt.xticks(bins,rotation='vertical')
            has_plan = folder.split('_',1)[1]
            plt.savefig(f'hist_pred__{exp}_{idx}_{has_plan}_game_len.png')
        # exit()
        for idx,(g,p,a,f) in enumerate([(g1,p1,a1,f1),(g2,p2,a2,f2)]):
            if exp == 0:
                exp_str = 'Predicting Agreement on Completed Task Status'
                exp_str2 = 'Predicting Agreement on Completed Task Status'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 1:
                exp_str = 'Predicting Agreement on Partner\'s Knowledge'
                exp_str2 = 'Predicting Agreement on Partner\'s Knowledge'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 2:
                exp_str = 'Predicting Agreement on Partner\'s Current Task with Strict Matching'
                exp_str2 = 'Predicting Agreement on Partner\'s Current Task with Strict Matching'
                human = None
                rnd = 0.5
                lmy=(0,1)
            elif exp == 6:
                exp_str = 'Predicting Completed Task Status'
                exp_str2 = 'Predicting Completed Task Status'
                human = 0.662
                rnd = 1/3
                lmy = (0.3,0.7)
            elif exp == 7:
                exp_str = 'Predicting Partner\'s Knowledge'
                exp_str2 = 'Predicting Partner\'s Knowledge'
                human = 0.613
                rnd = 1/3
                lmy = (0.3,0.7)
            else:
                exp_str = 'Predicting Partner\'s Current Task with Strict Matching'
                # exp_str2 = 'Predicting Partner\'s Current Task with Loose Matching'
                exp_str2 = 'Predicting Partner\'s Current Task'
                human = 0.362
                rnd = 1/22
                lmy = (0,0.5)
            
            plt.cla(); plt.clf();plt.close()
            fig, ax1 = plt.subplots(figsize=(9, 6))
            ax1.set_title(f'{exp_str if idx==0 else exp_str2}')
            boxprops = dict(linestyle='-', linewidth=1, color='black')
            flierprops = dict(marker='o', markerfacecolor='black', markersize=12,
                            markeredgecolor='none')
            medianprops = dict(linestyle='-', linewidth=2.5, color='black')
            meanpointprops = dict(marker='.', markeredgecolor='black',
                                markerfacecolor='firebrick')
            meanprops = dict(linestyle=':', linewidth=2.5, color='red')
            ax1.boxplot(a, showfliers=True, patch_artist=False, showmeans=True, meanline=True, 
                        boxprops=boxprops, flierprops=flierprops, medianprops=medianprops, meanprops=meanprops)
            # ax1.bar(list(range(len(a))),list(map(np.mean,a)))
            stds = [np.std(x) for x in a]
            means = [np.mean(x) for x in a]
            for i,(m,s) in enumerate(zip(means,stds)):
                ax1.plot([i+1,i+1],[m-s,m+s],linewidth=2.5,color='red')
            if not human is None:
                ax1.plot([0.5,6.5],[human]*2)
            ax1.plot([0.5,6.5],[rnd]*2,color='orange')
            # ax1.set_aspect((2/3)*7/0.5)
            
            plt.xticks(range(1,len(xlbls)+1),xlbls)
            if not human is None:
                plt.text(1,human+0.001,'Human Performance')
            plt.text(1,rnd+0.001,'Random Chance')
            # plt.legend([None]*10+['asdf','qwer'])
            plt.ylabel('Accuracy')
            plt.grid(linestyle=':')
            ax1.set_ylim(lmy)
            has_plan = folder.split('_',1)[1]
            plt.savefig(f'Bar_Accuracy_score_{exp}_{idx}_{has_plan}.png')
            
            plt.cla(); plt.clf();plt.close()
            fig, ax1 = plt.subplots(figsize=(9, 6))
            ax1.set_title(f'{exp_str if idx==0 else exp_str2}')
            boxprops = dict(linestyle='-', linewidth=1, color='black')
            flierprops = dict(marker='o', markerfacecolor='black', markersize=12,
                            markeredgecolor='none')
            medianprops = dict(linestyle='-', linewidth=2.5, color='black')
            meanpointprops = dict(marker='.', markeredgecolor='black',
                                markerfacecolor='firebrick')
            meanprops = dict(linestyle=':', linewidth=2.5, color='red')
            # ax1.boxplot(a, showfliers=True, patch_artist=False, showmeans=True, meanline=True, 
            #             boxprops=boxprops, flierprops=flierprops, medianprops=medianprops, meanprops=meanprops)
            ax1.bar(list(range(1,len(a)+1)),list(map(np.mean,a)))
            stds = [np.std(x) for x in a]
            means = [np.mean(x) for x in a]
            for i,(m,s) in enumerate(zip(means,stds)):
                ax1.plot([i+1,i+1],[m-1.96*s/np.sqrt(10),m+1.96*s/np.sqrt(10)],linewidth=1,color='black')
                ax1.plot([i+0.75,i+1.25],[m-1.96*s/np.sqrt(10),m-1.96*s/np.sqrt(10)],linewidth=1,color='black')
                ax1.plot([i+0.75,i+1.25],[m+1.96*s/np.sqrt(10),m+1.96*s/np.sqrt(10)],linewidth=1,color='black')
            if not human is None:
                ax1.plot([0.5,6.5],[human]*2)
            ax1.plot([0.5,6.5],[rnd]*2,color='orange')
            # ax1.set_aspect((2/3)*7/0.5)
            
            plt.xticks(range(1,len(xlbls)+1),xlbls)
            if not human is None:
                plt.text(1,human+0.001,'Human Performance')
            plt.text(1,rnd+0.001,'Random Chance')
            # plt.legend([None]*10+['asdf','qwer'])
            plt.ylabel('Accuracy')
            plt.grid(linestyle=':')
            ax1.set_ylim(lmy)
            has_plan = folder.split('_',1)[1]
            plt.savefig(f'Bar_Accuracy_score_{exp}_{idx}_{has_plan}.png')

        g1,p1,a1,f1,g2,p2,a2,f2,av,fv,tss,xlbls = zip(*data2)
        for idx,(g,p,a,f) in enumerate([(g1,p1,a1,f1),(g2,p2,a2,f2)]):
            if exp == 0:
                exp_str = 'Predicting Completed Task Status'
                human = None
                rnd = 0.5
            elif exp == 1:
                exp_str = 'Predicting Partner\'s Knowledge'
                human = None
                rnd = 0.5
            elif exp == 2:
                exp_str = 'Predicting Partner\'s Current Task with Strict Matching'
                human = None
                rnd = 0.5
            elif exp == 6:
                exp_str = 'Predicting Completed Task Status'
                exp_str2 = 'Predicting Completed Task Status'
                human = 0.672
                rnd = 0.327
                lmy = (0.3,0.7)
            elif exp == 7:
                exp_str = 'Predicting Partner\'s Knowledge'
                exp_str2 = 'Predicting Partner\'s Knowledge'
                human = 0.587
                rnd = 0.327
                lmy = (0.3,0.7)
            else:
                exp_str = 'Predicting Partner\'s Current Task with Strict Matching'
                exp_str2 = 'Predicting Partner\'s Current Task with Loose Matching'
                exp_str2 = 'Predicting Partner\'s Current Task'
                human = 0.452
                rnd = 0.01 if idx==0 else 0.04
                lmy = (0,0.5)
                
            plt.cla(); plt.clf();plt.close()
            fig, ax1 = plt.subplots(figsize=(9, 6))
            ax1.set_title(f'{exp_str if idx==0 else exp_str2}')
            # g,p,a,f,xlbls = zip(*data2[:-1])
            # g,p,a,f,xlbls = zip(*data2)
            boxprops = dict(linestyle='-', linewidth=1, color='black')
            flierprops = dict(marker='o', markerfacecolor='black', markersize=12,
                            markeredgecolor='none')
            medianprops = dict(linestyle='-', linewidth=2.5, color='black')
            meanpointprops = dict(marker='.', markeredgecolor='black',
                                markerfacecolor='firebrick')
            meanprops = dict(linestyle=':', linewidth=2.5, color='red')
            ax1.boxplot(f, showfliers=True, patch_artist=False, showmeans=True, meanline=True, 
                        boxprops=boxprops, flierprops=flierprops, medianprops=medianprops, meanprops=meanprops)
            stds = [np.std(x) for x in f]
            means = [np.mean(x) for x in f]
            for i,(m,s) in enumerate(zip(means,stds)):
                ax1.plot([i+1,i+1],[m-s,m+s],linewidth=2.5,color='red')
            if not human is None:
                ax1.plot([0.5,6.5],[human]*2)
            ax1.plot([0.5,6.5],[rnd]*2,color='orange')
            plt.grid()
            # ax1.set_aspect((2/3)*7/0.5)
            plt.xticks(range(1,len(xlbls)+1),xlbls)
            if not human is None:
                plt.text(1,human,'Human Performance')
            plt.text(1,rnd-0.01225,'Random Chance')
            plt.ylabel('F1 Score')
            plt.grid(linestyle=':')
            ax1.set_ylim(lmy)
            has_plan = folder.split('_',1)[1]
            plt.savefig(f'Bar_F1_score_{exp}_{idx}_{has_plan}.png')
            
            plt.cla(); plt.clf();plt.close()
            fig, ax1 = plt.subplots(figsize=(9, 6))
            ax1.set_title(f'{exp_str if idx==0 else exp_str2}')
            # g,p,a,f,xlbls = zip(*data2[:-1])
            # g,p,a,f,xlbls = zip(*data2)
            boxprops = dict(linestyle='-', linewidth=1, color='black')
            flierprops = dict(marker='o', markerfacecolor='black', markersize=12,
                            markeredgecolor='none')
            medianprops = dict(linestyle='-', linewidth=2.5, color='black')
            meanpointprops = dict(marker='.', markeredgecolor='black',
                                markerfacecolor='firebrick')
            meanprops = dict(linestyle=':', linewidth=2.5, color='red')
            # ax1.boxplot(f, showfliers=True, patch_artist=False, showmeans=True, meanline=True, 
            #             boxprops=boxprops, flierprops=flierprops, medianprops=medianprops, meanprops=meanprops)
            ax1.bar(list(range(1,len(f)+1)),list(map(np.mean,f)))
            stds = [np.std(x) for x in f]
            means = [np.mean(x) for x in f]
            for i,(m,s) in enumerate(zip(means,stds)):
                ax1.plot([i+1,i+1],[m-1.96*s/np.sqrt(10),m+1.96*s/np.sqrt(10)],linewidth=1,color='black')
                ax1.plot([i+0.75,i+1.25],[m-1.96*s/np.sqrt(10),m-1.96*s/np.sqrt(10)],linewidth=1,color='black')
                ax1.plot([i+0.75,i+1.25],[m+1.96*s/np.sqrt(10),m+1.96*s/np.sqrt(10)],linewidth=1,color='black')
            if not human is None:
                ax1.plot([0.5,6.5],[human]*2)
            ax1.plot([0.5,6.5],[rnd]*2,color='orange')
            plt.grid()
            # ax1.set_aspect((2/3)*7/0.5)
            plt.xticks(range(1,len(xlbls)+1),xlbls)
            if not human is None:
                plt.text(1,human,'Human Performance')
            plt.text(1,rnd-0.01225,'Random Chance')
            plt.ylabel('F1 Score')
            plt.grid(linestyle=':')
            ax1.set_ylim(lmy)
            has_plan = folder.split('_',1)[1]
            plt.savefig(f'Bar_F1_score_{exp}_{idx}_{has_plan}.png')