//javac ExampleProgram.java
//java ExampleProgram
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BFS{

	int length = 6;

	public BFS(){

	}


	public static void startProblem(Graph g){
		Node start = g.start;
		Search s = new Search();
		ArrayList<Integer> answers = new ArrayList<Integer>();

		for(int i = 0; i != g.nodes.size(); i++){
			Node n = g.nodes.get(i);
			if(n != g.start){
				//System.out.println("trying" + n.value);
				int answer = s.bfs(g.start,0,n);
				s.clear();
				System.out.print(answer + " ");
			}
		}

	}

	public static class Graph{
		Node start;
		ArrayList<Node> nodes = new ArrayList<Node>();
		public Graph(int numberOfNodes){
			for(int i = 1; i!= (numberOfNodes + 1); ++i){
				Node node = new Node(i);
				nodes.add(node);
			}
			
		}
		public void addStart(int _start){
			start = nodes.get(_start-1);
		}

		public void addEdge(int a, int b){
			Node n1 = nodes.get(a-1);
			Node n2 = nodes.get(b-1);
			n1.addNeighbor(n2);
			n2.addNeighbor(n1);
		}
	}


	public static class Node{
		ArrayList<Node> edges = new ArrayList<Node>();
		int value;
		int level = 0;
		public Node(int value){
			this.value = value;
		}

		public void addNeighbor(Node n){
			edges.add(n);
		}

	}

	public static class Search{
		HashSet<Node> visited = new HashSet<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		public Search(){
		
		}

		public int bfs(Node curr, int length,  Node end){
			visit(curr);
			//System.out.println("visit" + curr.value);
			if(curr == end){
				return curr.level;
			}
			for(int i = 0; i!= curr.edges.size(); ++i){
				Node n = curr.edges.get(i);
				if(!visited.contains(n)){
					//System.out.println("queue" + n.value);
					queue.add(n);
					if(n.level == 0){
						n.level = curr.level + 6;
					}
				}
			}
			if(queue.size() != 0){
				return bfs(queue.remove(), length, end);
			}else{
				return -1;
			}
			
		}

		public void clear(){
			visited.clear();
			queue.clear();
		}

		public void visit(Node n){
			visited.add(n);
		}
	}




public static void main(String[] args){
	Scanner s = new Scanner(System.in);
	int numTestCases = s.nextInt();

	for(int i = 0; i != numTestCases; ++i){
		int numberOfNodes = s.nextInt();
		int numberOfEdges= s.nextInt();
		Graph g = new Graph(numberOfNodes);
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int j = 0; j != numberOfEdges; ++j){
			int x = s.nextInt();
			int y= s.nextInt();
			g.addEdge(x,y);
			a.add(x);
			a.add(y);
		}
		int start = s.nextInt();
		g.addStart(start);
		startProblem(g);
		System.out.println();
	}
}
	
}





