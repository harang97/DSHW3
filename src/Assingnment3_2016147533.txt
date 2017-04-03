package hw3;

import java.io.*;

import hw3.Huffman;

public class Assignment3_2016147533 {
	
	static String res;
	static int inputsize;
	
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
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException
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
		
		rd.close();
		wr.close();
	}
}
