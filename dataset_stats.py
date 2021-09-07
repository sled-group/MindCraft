# %%
import json
from glob import glob
import numpy as np


files = glob('main_logs/*/mcc*.log') + glob('saved_logs/*/mcc*.log')



x = [sum(int('[]<sledmcc' in l) for l in open(file)) for file in files]

print(np.mean(x))
print(sum(x))

x = []
for file in files:
    lines = list(open(file))
    start = [l for l in lines if 'Successfully started recording' in l][-1]
    end = [l for l in lines if 'Stopping the server' in l]
    if not end:
        end = [l for l in lines if 'Stopping server' in l]
    if not end:
        end = [l for l in lines if 'Server thread' in l]
    end = end[-1]
        
    start = list(map(int,start[1:9].split(':')))
    start = start[0]*3600+start[1]*60+start[2]
    end = list(map(int,end[1:9].split(':')))
    end = end[0]*3600+end[1]*60+end[2]
    if end > start:
        x.append(end-start)
    # break

fun = lambda x: f'{x//3600}:{x%3600//60}:{x%60}'
print(fun(np.mean(x)))
print(fun(max(x)))
print(fun(min(x)))
print(fun(sum(x)))
print(len(x))


files = glob('main_logs/*/web*.log') + glob('saved_logs/*/web*.log')


x = [sum(int('Number of records inserted' in l) for l in open(file))//2 for file in files]

print(np.mean(x), min(x), max(x))
print(sum(x))


files = glob('main_logs/*/plan*.json') + glob('saved_logs/*/plan*.json')

materials = []
objects = []
steps = []
for file in files:
    plan = json.load(open(file))
    materials.append(plan['materials'].__len__())
    objects.append(plan['full'].__len__())
    steps.append(len(sum([sum(x['make'],[]) for x in plan['full']],[]))+1)


print(np.mean(materials))
print(np.mean(objects), min(objects), max(objects))
print(np.mean(steps), min(steps), max(steps))

files = glob('main_logs/*/mcc*.log') + glob('saved_logs/*/mcc*.log')



x = [sum(int('[]<sledmcc' in l) for l in open(file)) for file in files]

print(np.mean(x),min(x), max(x))
print(sum(x))

replacements = {
    '_' : ' ',
    '  ' : ' ',
    ' u ' : ' you ',
    '.' : ' . ',
    ',' : ' , ',
    '?' : ' ? ',
    '+' : ' + ',
    '=' : ' = ',
    '!' : ' ! ',
    '*' : ' * ',
    'lets' : 'let\'s',
    'let us' : 'let\'s',
    'pout' : 'put',
    'cyn' : 'cyan',
    'dont' : 'don\'t',
    'don t' : 'don\'t',
    'cant' : 'can\'t',
    'can t' : 'can\'t',
    'im' : 'i\'m',
    'i m' : 'i\'m',
    'ontop' : 'on top',
    'mmhmm' : 'mhm',
    'thats' : 'that\'s',
    'isnt' : 'isn\'t',
    'hi\'m' : 'him',
    'ti\'me' : 'time',
    'soryy' : 'sorry',
    'invisable' : 'invisible',
    'i\'ma' : 'I',
    'sry' : 'sorry',
    'okayi' : 'okay i',
    'li\'me' : 'lime',
    'whatdo' : 'what do',
    'habve' : 'have',
    'drak' : 'dark',
    'yyes': 'yes',
    'rneed' : 'need',
    'dia ' : 'diamond ',
    'diamind' : 'diamond',
    'diamind pick ' : 'diamond pickaxe ',
    'diamond pick ' : 'diamond pickaxe ',
    'invisibile' : 'invisible',
    'tkes' : 'takes',
    'gray rool' : 'grey wool',
    'gray' : 'grey',
    'ornage' : 'orange',
}

material_names = [x.split('/')[-1].split('.')[0].replace('_',' ').lower() for x in glob('mean/src/public/img/materials/*')]
material_names = material_names + [x.split('/')[-1].split('.')[0].replace('_','').lower() for x in glob('mean/src/public/img/materials/*')]
material_names = material_names + sum([x.split('/')[-1].split('.')[0].lower().split('_') for x in glob('mean/src/public/img/materials/*')],[])
material_names = material_names + ['orange', 'grey']
# print(material_names)
tool_names = [x.split('/')[-1].split('.')[0].replace('_',' ').lower() for x in glob('mean/src/public/img/tools/*')]
tool_names = tool_names + [x.split('/')[-1].split('.')[0].replace('_','').lower() for x in glob('mean/src/public/img/tools/*')]
tool_names = tool_names + sum([x.split('/')[-1].split('.')[0].lower().split('_') for x in glob('mean/src/public/img/tools/*')],[])
# print(tool_names)
# print('diamond pickaxe' in tool_names + material_names
# )
# exit()


categories = {
    'Greeting' : ([],['hi', 'hello','let\'s go','i\'m out','escape time','let\'s gooooooo','bye','gg','hiii','hey']),
    'other' : ([],[]),
    'Game Spec.' : ([],['knowledge graph', 'right click','can you let him','him','invisible','click','web page','graph','it sais','it says','hit t','chat','mine just shows','on my screen','recipe','popup','press','separated','did that interrupt','need to read',
                            'usually hold it like','arrow','arrows','time to die','it doesn\'t show anything','in an intersection','it doesnt show anything for me']),
    'Question' : ([],['where','should i','?','do we','which', 'whats', 'what', 'what\'s', 'how', 'why','what','the goal right','do you','is your',]),
    'Info' : ([],[  'to make','i\'m making','next','i can','is mine','not working','we combine','related to','tried both','it\'s possible','its me','it\'s me','i cannot','i also can\'t','that\'s a','i used the','the order we','hoe get emerald','iron pick',
                    'I\'m trying','I need a','it isn\'t','i need','i needed','going to make','the other is','can\'t make','if you add','i do','you must know','need your','mine says','seems like','seems to','for the','ide gold','second from right','righrt',
                    'we both have','we have','i would','mine is','I got','I have','I only have','i\'m confused','didn\'t work','made','is you','both of those are','tried it','we got the','seems to require','that\'s','these blocks require','does not matter','the birch planks',
                    'I have','I also have','I think','we need','goal is','I can\'t','has an arrow','I have a', 'i need','we\'ll make','btw','taht was','you can','it requires','directly from','put in','that\s soulsand','this one','cause the','use gold pick on orange','can',
                    'I also have a','don\'t have','also don\'t have','dont have','also dont have','don t have','also don t have','it needs','of wood','that was a','this is a','redstone was for','then we cna','in the back','here','place from the top','placed from the top',]
                    + [f'{x} {y}' for x in ['break','alright','got a','comes from','plus','have','then','from','just','make','made','need','makes','is','for','is for','of','on top of','to','to get','i need','place','to create',] for y in material_names]
                    + [f'{y} {x}' for x in ['is','goal','it needs','the',] for y in material_names]
                    + [f'{x} {z} {y}' for x in material_names for y in material_names for z in ['on','get','with','to','above the','+','->','needs to be placed on','becomes']]
                        + [f'{y} {x}' for x in tool_names for y in ['have the','uses a','needs','using','your','with']]
                        + [f'{x} {y}' for x in tool_names for y in ['to','on']]),
    'Suggestion' : ([],['can you','maybe try','that\'s an ingredient','i need','flip it','there','other way','you break','place yours','can you try','let\'s do','let\'s try','on the','and','placing them',
                        'are combined to','break the','with your','placed it','place it','on top','let\'s combine','maybe','lime here','let me','try','hit one','change','get the','make cobble','here\'s a',
                        'place on','try breaking', 'try making', 'then put', 'gotta', 'have to','combine','another','try other','i will','place','keep hacking','the blue one',
                        'so', 'now', '+', '=', 'put it','place it', 'is to', 'is to make','step at a time']
                        + [f'{y} {x}' for x in tool_names for y in ['use', 'use your','with the','get your','equip your']]
                        + [f'{x} {z} {y}' for x in material_names for y in material_names for z in ['under the']]
                        + [f'{x} {y}' for x in ['also this','come break this'] for y in material_names]
                         ),
    'Correction' : ([],['oh wait','nvm','never mind','nevermind','*','fuck','no wait','sorry','excuse','i\'m stupid','or wait','i thought','mistake','mistaken','or not','mb','wait','oops','however','hold on']),
    'Ack.' : ([],[   'done','done!', 'ok','yeah','yep','yes','nice','woo','wooh','!','i can see that', 'yea','just saw','indeed','one sec','success','ye','got it','niicee','it is','gr8','great job','ayaaaa','over here',
                                'hmm','hmmm','hmmmm','hmmmmm','ah','no','oh right','yup','might as well','same','ez', 'gotcha','ayyyy','one sec','congrats','nope','ayyyyyy','that should be that','idk','tada','because we already have it',
                                'I\'m not sure','same here','okay','aha','combined','ta dah','ta da','k','oh','good','realized','thx','kk','check','neat','ay','bam','niiiiicccce','sure','np','goal block created','hooray','i don\'t',
                                'oh you have','cool','awesome','i agree','yeehaw','mhm','complete','sweet','rip','we did it','yay','i see']),
    'Statement' : ([],[ 'speedrun','pretty sure', 'dont know', 'don\'t know','uhm','ope','uh','hmm','hm','time to run around','jk','ow','umm','these colors are','loll','umm','whoa','haha','ol','ayye','one min','you hoe',
                        'take your time',':(','o','.','joking','lucky','lol','pls','stairs','huh','aww','look at us','or if','not sure','sheeeeesh','yata','i was just trying','lool',"fk'askdfoi",'i\'mean','wing it','unlucky',
                        'that was fast','am helping','a while','holy cow','huge','sheeeesh','god','numbers','that is bigger','getting used','t','was just about to ask','it takes forever','some things around','interesting','coool','should be faster',
                        ]),
}

num_exchanges = 0
dep_lst = []
for file in files:
    lines = list(open(file))
    prev = None
    for l in lines: 
        if '<sledmcc' in l:
            num_exchanges += 1
            line = l.split('<')[1].strip().split('>',1)
            for word, replacement in replacements.items():
                line[1] = (' '+line[1]+' ').lower().replace(word,replacement).strip()
            for word, replacement in replacements.items():
                line[1] = (' '+line[1]+' ').lower().replace(word,replacement).strip()
            ok = False
            for category, (_,keyPhrases) in categories.items():
                if [elem for elem in keyPhrases if ' '+elem.lower()+' ' in ' '+line[1].lower()+' ']:    
                    # print(category, keyPhrases, line)
                    categories[category][0].append(line)
                    ok = True
                    if not prev is None:
                        dep_lst.append((prev,category))
                    prev = category
                    break
            if not ok:
                if line[1].lower().strip() in (material_names + tool_names):
                    category = 'Info'
                    categories['Info'][0].append(line)
                    if not prev is None:
                        dep_lst.append((prev,category))
                    prev = category
                else:
                    # print(line)
                    category = 'other'
                    categories['other'][0].append(line)
                    if not prev is None:
                        dep_lst.append((prev,category))
                    prev = category
            
print(categories['other'])
print(num_exchanges)
print(num_exchanges - len(categories['other'][0]))
categories = sorted(categories.items(),key=lambda x: len(x[1][0]),reverse=True)
# print(categories[0])
# print(dep_lst[0])
cand_cats = [k for k,_ in categories]
for i,(k,v) in enumerate(categories):
    if k=='other':
        continue
    cand_lst = [x[0] for x in dep_lst if x[1]==k]
    numstrs = []
    for cand in cand_cats:
        if cand=='other':
            continue
        cand_count = len([x[1] for x in cand_lst if x==cand])/len(cand_lst)
        numstrs.append(f'{100*cand_count:5.1f}')
    numstrs = ' & '.join(numstrs)
    # print(len(cand))
    print(f'{i+1} & {k:20s} & {len(v[0]):5d} & {100*len(v[0])/(num_exchanges - len(categories[-1])):6.2f} & {numstrs} \\\\')
# print(other)
# print('other', other.__len__())
# print('instructions', instructions.__len__())
# print('question', question.__len__())
# print('acknowledgement', acknowledgement.__len__())
# print('greetings', greetings.__len__())