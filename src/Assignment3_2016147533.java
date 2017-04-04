package hw3;

import java.io.*;

import hw3.Huffman;
import hw3.ArrayQueue;

public class Assignment3_2016147533 {
	
	static String res;
	static int inputsize;
	static int currxpos;
	
	public static String printTree(Entry node) throws EmptyQueueException {
		String totalres = "";
		String res1 = "";
		String res2 = "";
		String res3 = "";
		String res4 = "";
		String res5 = "";
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayQueue<Entry> Q = new ArrayQueue();
		int tlen = 0;
		int cursor = 0;
		Entry tmp;
		Q.enqueue(node);
		while(!Q.isEmpty()) {
			tlen = Q.size();
			cursor = 0;
			res1 = "";
			res2 = "";
			res3 = "";
			res4 = "";
			res5 = "";
			for (int i=0; i<tlen; i++) {
				tmp = Q.dequeue();
				
				while (cursor<tmp.getlxpos()) {
					cursor++;
					res1 += " ";
					res2 += " ";
					res3 += " ";
					res4 += " ";
					res5 += " ";
				}
				res4 = res4.substring(0, cursor);
				res5 = res5.substring(0, cursor);
				
				res1 += "+-";
				if (tmp.getLeft() != null || tmp.getRight() != null)
					res2 += "| "+tmp.getCode();
				else {
					if (tmp.getValue() == "\\n")
						res2 += "| "+tmp.getValue()+": "+tmp.getCode();
					else
						res2 += "| "+tmp.getValue()+" : "+tmp.getCode();
				}
				res3 += "+-";
				if (tmp.getLeft() != null) {
					res4 = res4.substring(0, res4.length()-1) + "/";
					res5 = res5.substring(0, res5.length()-2) + "/ ";
				}
				res4 += "  ";
				res5 += "  ";
				
				if (tmp.getRight() != null || tmp.getLeft() != null) {
					for (int j=0; j<tmp.getCode().length(); j++) {
						res1 += "-";
						res3 += "-";
						res4 += " ";
						res5 += " ";
					}
				}
				else {
					for (int j=0; j<tmp.getCode().length()+4; j++) {
						res1 += "-";
						res3 += "-";
						res4 += " ";
						res5 += " ";
					}
				}
				
				res1 += "-+";
				res2 += " |";
				res3 += "-+";
				res4 += "  ";
				res5 += "  ";
				
				if (tmp.getRight() != null) {
					res4 += "\\";
					System.out.println(Integer.toString(tmp.getRight().getlxpos() - tmp.getrxpos()));
					for (int j=0; j<tmp.getRight().getlxpos()-tmp.getlxpos()-tmp.getCode().length()-6; j++) {
						res4 += "_";
						res5 += " ";
					}
					res5 += " \\";
				}
				
				if (tmp.getRight() != null || tmp.getLeft() != null) 
					cursor += 4 + tmp.getCode().length();
				else
					cursor += 8 + tmp.getCode().length();
				
				if (tmp.getLeft() != null)
					Q.enqueue(tmp.getLeft());
				if (tmp.getRight() != null)
					Q.enqueue(tmp.getRight());
			}
			
			totalres += res1 + "\r\n" + res2 + "\r\n" + res3 + "\r\n" + res4 + "\r\n" + res5 + "\r\n";
		}
		
		return totalres;
	}
	
	public static void preorder(Entry node) { // traverse by preorder and set every leaf node's code
		if (node.getLeft() != null) {
			node.getLeft().setCode(node.getCode()+"0");
			preorder(node.getLeft());
		}
		if (node.getRight() != null) {
			node.getRight().setCode(node.getCode()+"1");
			preorder(node.getRight());
		}
		else if (node.getLeft() == null && node.getRight() == null) { // if leaf node is encountered, print result
			res += node.getValue()+"="+node.getCode()+"; ";
			inputsize += node.getKey() * node.getCode().length();
		}
	}
	
	public static int max(int a, int b) { return ( a > b ? a : b);	}
	
	public static void inorder(Entry node) {
		if (node.getLeft() != null) {
			inorder(node.getLeft());
			if (node.getLeft().getLeft() != null || node.getLeft().getRight() != null) {
				node.setlxpos(node.getLeft().getlxpos()+node.getLeft().getCode().length()+6);
				node.setrxpos(max(node.getLeft().getrxpos(), node.getlxpos()+node.getCode().length()+3));
			}
			else {
				node.setrxpos(max(node.getLeft().getrxpos(), node.getlxpos()+node.getCode().length()+7));
				node.setlxpos(node.getLeft().getlxpos()+node.getLeft().getCode().length()+10);
			}
			currxpos = node.getrxpos();
		}
		else { // if node doesn't have left node
			node.setlxpos(currxpos+3);
			if (node.getRight() != null)
				node.setrxpos(node.getlxpos()+node.getCode().length()+3);
			else
				node.setrxpos(node.getlxpos()+node.getCode().length()+7);
			currxpos = node.getrxpos();
		}
		if (node.getRight() != null) {
			inorder(node.getRight());
			node.setrxpos(node.getRight().getrxpos());
			currxpos = node.getrxpos();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException, EmptyQueueException
	{
		String s = "";
		int testcaseNum = 0;
		int[] freqCount = new int [130]; // 'z' is 122 in ASCII
		Entry[] entryList = new Entry[127];
		int entryNum = 0;
		Huffman huffman;
		inputsize = 0;
		int enternum = 0;
		Entry root;
		
		BufferedReader rd = new BufferedReader(new FileReader("C:\\hw3\\input.txt"));
		BufferedWriter wr = new BufferedWriter(new FileWriter("C:\\hw3\\2016147533.txt"));
		
		s = rd.readLine();
		enternum++;
		while(s != null) { // Run if string is not null(eof)
			if (s.length() != 0 && s.charAt(0) == '#') { // if s[0] is #, testcaseNum++ and start huffman coding
				if (testcaseNum != 0) { // print result
					wr.write("#" + Integer.toString(testcaseNum)+"\r\n");
					huffman = new Huffman(entryList, entryNum);
					freqCount[10] = enternum-1;
					for (int i=0; i<130; i++) {
						if (i == 10 && freqCount[i] != 0)
							huffman.insert(new Entry(freqCount[i], "\\n", null, null, null));
						else if (freqCount[i] != 0) {
							huffman.insert(new Entry(freqCount[i], Character.toString((char)i), null, null, null));
						}
					}
					huffman.createHeap(); // make min heap
					root = huffman.createTree(); // make huffman tree
					preorder(root);
					wr.write(res+"\r\n"+Integer.toString(inputsize)+"\r\n");
					
					//print binary tree
					currxpos = 0;
					inorder(root);
					wr.write(printTree(root));
				}
				testcaseNum++;
				entryNum = 0;
				inputsize = 0;
				enternum = 0;
				res = "";
				for (int i=0; i<130; i++) freqCount[i] = 0; // reset Array freqCount
			}
			else {
				for (int i=0; i<s.length(); i++) freqCount[s.charAt(i)]++;
			}
			s = rd.readLine();
			enternum++;
		}
		
		// print result
		wr.write("#" + Integer.toString(testcaseNum)+"\r\n");
		huffman = new Huffman(entryList, entryNum);
		freqCount[10] = enternum-1;
		for (int i=0; i<130; i++) {
			if (i == 10 && freqCount[i] != 0)
				huffman.insert(new Entry(freqCount[i], "\\n", null, null, null));
			else if (freqCount[i] != 0) {
				huffman.insert(new Entry(freqCount[i], Character.toString((char)i), null, null, null));
			}
		}
		huffman.createHeap();
		root = huffman.createTree();
		preorder(root);
		wr.write(res+"\r\n"+Integer.toString(inputsize)+"\r\n");
		
		//print binary tree
		currxpos = 0;
		inorder(root);
		wr.write(printTree(root));
		
		rd.close();
		wr.close();
	}
}
