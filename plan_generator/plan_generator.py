#! /usr/bin/env python3
import argparse, os, sys, random, json
from copy import deepcopy
from random import randint, shuffle
from glob import glob

class Node():
    def __init__(self,make=[], tools=None):
        self.make = make
        self.tools = tools

    def setMake(self,make):
        self.make = [make]

    def addMake(self,make):
        self.make.append(make)

    def setTools(self,tools):
        self.tools = tools

    def __str__(self):
        return str(self.__dict__)

    def __repr__(self):
        return json.dumps(self.__dict__)

class GraphGenerator():
    def __init__(self,args):
        self.complexity_limit = random.randint(*sorted([args.lower_complex_lim, args.upper_complex_lim]))
        self.num_tools = args.num_tools
        if args.max_tools_per_mat is None:
            self.max_tools_per_mat = args.num_tools
        else:
            self.max_tools_per_mat = args.max_tools_per_mat
        self.num_final_mat = args.num_final_mat
        self.graph = []

    def depends_on(self,cand,target):
        node = self.graph[cand]
        if len(node.make) == 0:
            return False
        retval = False
        for c in node.make[0]:
            if retval:
                break
            if c == target:
                return True
            retval = retval or self.depends_on(c,target)
        return retval

    def filter_fun(self, edge):
        candidates = []
        for i, node in enumerate(self.graph):
            # Candidate can not be member of current edge
            if i in edge:
                continue

            # Candidate can not be a final material (root)
            if i < self.num_final_mat:
                continue

            # Candidate can not create cycles in graph
            if not self.depends_on(i,edge[0]):
                candidates.append(i)
        return candidates

    def make_graph(self):
        # add final nodes (roots) and their mine sources (leaf nodes)
        self.graph = [Node() for _ in range(3*self.num_final_mat)]
        for i in range(self.num_final_mat):
            self.graph[i].setMake([self.num_final_mat+i,2*self.num_final_mat+i])

        # Successively replace random edge with new node
        for _ in range(self.complexity_limit):
            # Randomly pic a non leaf node
            nodes = list(filter(lambda x: len(x.make) > 0, self.graph))
            random.shuffle(nodes)
            node = nodes[0]

            # Randomly pick left or right edge
            choice = random.randint(0,1)
            edge = (self.graph.index(node), node.make[0][choice])

            # generate valid candidates for second source for new node
            candidates = self.filter_fun(edge)

            # Randomly pick new source from candidates
            random.shuffle(candidates)

            # Add new node to graph
            self.graph[edge[0]].make[0][choice] = len(self.graph)
            self.graph.append(Node())

            # randomly pick if second necessary material is candidate or new
            if random.randint(0,len(candidates)) == len(candidates):
                self.graph[-1].make = [[edge[1],len(self.graph)]]
                self.graph.append(Node())
            else:
                cand_make = [[edge[1],candidates[0]]]
                found = False
                for x in self.graph:
                    if x.make == cand_make:
                        found = True
                        break
                if not found:
                    self.graph[-1].make = [[edge[1],candidates[0]]]
                else:
                    print('!!!!!!')
                    # exit()
                    self.graph[-1].make = [[edge[1],len(self.graph)]]
                    self.graph.append(Node())

        # assign tools to nodes
        tools = list(range(self.num_tools))
        for node in self.graph:
            random.shuffle(tools)
            node.tools = sorted(tools[0:min(self.max_tools_per_mat,random.randint(1,len(tools)))])
            
        depth_fun = lambda n: 1 if not n.make else 1 + depth_fun(self.graph[n.make[0][0]]) + depth_fun(self.graph[n.make[0][1]])

        queue = [0]
        visited = []
        leafs = []
        order = []
        graph = []
        while queue:
            idx = queue.pop(0)
            if idx in visited:
                continue
            visited.append(idx)
            x = self.graph[idx]
            if x.make:
                queue.append(x.make[0][0])
                queue.append(x.make[0][1])
                if not graph or depth_fun(x) <= depth_fun(graph[-1]):
                    graph.append(x)
                    order.append(idx)
                else:
                    graph.insert(-1,x)
                    order.insert(-1,idx)
            else:
                leafs.append((idx,x))

        for idx, x in leafs:
            order.append(idx)
            graph.append(x)

        for i, _ in enumerate(graph):
            if graph[i].make:
                graph[i].make[0][0] = order.index(graph[i].make[0][0])
                graph[i].make[0][1] = order.index(graph[i].make[0][1])
                graph[i].make[0] = sorted(graph[i].make[0])
            
        self.graph = graph
                
        # for i, x in enumerate(self.graph):
        #     print(i,x,end=' ')
        #     print(depth_fun(x))

        return self.graph

    def __str__(self):
        return str(self.__dict__)

    def __repr__(self):
        return str(self.__dict__)

def main(args):
    materials = glob('../mean/dist/img/materials/*.png') + glob('mean/dist/img/materials/*.png')
    materials = sorted([m.split('/')[-1].split('.')[0] for m in materials])
    materials = [m for m in materials if not 'NULL' in m]
    materials = [m for m in materials if not 'OBSIDIAN' in m]
    mines = [m for m in materials if 'PLANK' in m]
    materials = [m for m in materials if not 'PLANK' in m]
    shuffle(materials)
    shuffle(mines)
    # print(materials)
    # print(mines)

    tools = glob('../mean/dist/img/tools/*.png') + glob('mean/dist/img/tools/*.png')
    tools = sorted([t.split('/')[-1].split('.')[0] for t in tools])
    tools = [t for t in tools if not 'NULL' in t]
    shuffle(tools)
    # print(tools)
    
    if args.output_path is None:
        output_file = sys.stdout
    else:
        output_file = open(args.output_path,'w')

    graph_gen = GraphGenerator(args)
    graph = graph_gen.make_graph()
    print(graph)
        
    # graph = json.load(open(args.output_path))
    graph = json.loads(str(graph))
    ng = len(graph)
    nm = sum([int(not x['make']) for x in graph])
    nn = ng - nm - 1
    
    print(ng, nm, nn)
    decision = []
    if (nn % 2) == 1 or nn > 2:
        decision.append(0)
    decision += [1]*(nn//2)
    decision += [2]*(nn//2)
    
    # print(decision)
    shuffle(decision)
    # print(decision)
    decision = decision[:nn]
    print(decision)
    
    player1_graph = deepcopy(graph)
    player2_graph = deepcopy(graph)
    
    if args.disparate_knowledge:
        for i in range(1,len(graph)):
            if graph[i]['make']:
                n = decision.pop(0)
                if n==1:
                    player1_graph[i]['make'] = [[-1,-1]]
                if n==2:
                    player2_graph[i]['make'] = [[-1,-1]]
                
    output = {
        'materials' : materials,
        'mines'     : mines,
        'tools'     : tools,
        'full'      : graph, 
        'player1'   : player1_graph, 
        'player2'   : player2_graph
        }
    for i,d in enumerate(zip(graph,player1_graph,player2_graph)):
        print(i,d)
        
    
    output_file.write( '{\n')
    output_file.write(',\n'.join([f'\t"{key}": {json.dumps(val)}' for key, val in output.items()]) + '\n')
    output_file.write( '}\n')
    # output_file.write(json.dumps(output))

    if output_file is not sys.stdout:
        output_file.close()
        output = json.load(open(args.output_path))
    print(json.dumps(output))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Minecraft plan graph generator.')
    parser.add_argument('--num_final_mat', type=int, default=1, help='Number of final materials necesary.')
    parser.add_argument('--upper_complex_lim', type=int, default=5, help='Maximum graph complexity limit')
    parser.add_argument('--lower_complex_lim', type=int, default=5, help='Minimum graph complexity limit')
    parser.add_argument('--num_tools', type=int, default=1, help='Number of tools available in the game.')
    parser.add_argument('--max_tools_per_mat', type=int, default=1, help='Maximum number of tools assignable to a material.')
    parser.add_argument('--output_path', type=str, default='../spigot/plan.json', help='Path to output file.')
    parser.add_argument('--disparate_knowledge', action='store_true', help='Players will se different subsets of the whole graph if set')
    parser.add_argument('--disparate_skils', action='store_true', help='Players will se different subsets of the whole graph if set')
    args = parser.parse_args()
    main(args)
