#! /usr/bin/env python3
import argparse, os, sys, random, json

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
                self.graph[-1].make = [[edge[1],candidates[0]]]

        # assign tools to nodes
        tools = list(range(self.num_tools))
        for node in self.graph:
            random.shuffle(tools)
            node.tools = sorted(tools[0:min(self.max_tools_per_mat,random.randint(1,len(tools)))])

        return self.graph

    def __str__(self):
        return str(self.__dict__)

    def __repr__(self):
        return str(self.__dict__)

def main(args):
    if args.output_path is None:
        output_file = sys.stdout
    else:
        output_file = open(args.output_path,'w')

    graph_gen = GraphGenerator(args)
    graph = graph_gen.make_graph()
    print(graph)
    output_file.writelines(str(graph)+'\n\r')

    if output_file is not sys.stdout:
        output_file.close()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Minecraft plan graph generator.')
    parser.add_argument('--num_final_mat', type=int, default=1, help='Number of final materials necesary.')
    parser.add_argument('--upper_complex_lim', type=int, default=15, help='Maximum graph complexity limit')
    parser.add_argument('--lower_complex_lim', type=int, default=5, help='Minimum graph complexity limit')
    parser.add_argument('--num_tools', type=int, default=1, help='Number of tools available in the game.')
    parser.add_argument('--max_tools_per_mat', type=int, default=1, help='Maximum number of tools assignable to a material.')
    parser.add_argument('--output_path', type=str, default='../spigot/plan.json', help='Path to output file.')
    args = parser.parse_args()
    main(args)