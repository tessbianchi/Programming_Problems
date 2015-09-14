import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Tries{

	Node myRoot;
	String currentWord;

	
	public class Node{
		char value;
		boolean isLast = false;
		ArrayList<Node> nodes = new ArrayList<Node>();
		public Node addChild(char value, boolean isLast){
			Node n = new Node();
			n.value = value;
			n.isLast = isLast;
			this.nodes.add(n);
			return n;
		}

	}

	public Tries(){
		this.myRoot = new Node();
	}

	public boolean testWord(String word){
		this.currentWord = word;
		return addWord(word, this.myRoot);
	}
	public boolean addWord(String word, Node root){
		for(Node n: root.nodes){
			if(n.value == word.charAt(0)){
				if(n.isLast){
					fail();
					return true;
				}
				else if(word.length() == 1){
					fail();
					return true;
				}
				else{
					return addWord(word.substring(1), n);
				}
			}
		}
		addRestOfWord(word,root);
		return false;
	}

	public void addRestOfWord(String word, Node _root){
		for(int i =0; i!= word.length(); ++i){
			if(i == word.length() - 1){
				_root.addChild(word.charAt(i),true);
			}else{
				Node n = _root.addChild(word.charAt(i),false);
				_root = n;
			}
		}
	}

	public void fail(){
		System.out.println("BAD SET");
		System.out.println(currentWord);
	}
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int numWords = s.nextInt();
		Tries t = new Tries();
		ArrayList<String> a = new ArrayList<String>();
		for(int i = 0; i != numWords; ++i){
			String w = s.next();
			a.add(w);
		}

		boolean b = false;
		boolean hasGone = false;
		for(String w: a){
			hasGone = true;
			b = t.testWord(w);
			if(b){
				break;
			}
		}
		if(!b && hasGone){
			System.out.println("GOOD SET");
		}
	}

}