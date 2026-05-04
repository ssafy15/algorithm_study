package main.java.study.a12_trie.d03.xor중최대;

import java.util.*;
import java.io.*;

public class Main_조은진 {

	static class TrieNode{
		TrieNode[] children;
		
		TrieNode(){
			children=new TrieNode[2];
			for(int i=0; i<2; i++) children[i]=null;
		}
	}
	
	static TrieNode root;
	static int maxNum;
	
	static void makeTree(int num) {
		int divnum=(int) Math.pow(2, 30);
		int curnum=num;
		TrieNode t=root;
		for(int i=0; i<31; i++) {
			int res=curnum/divnum;
			if(t.children[res]==null) t.children[res]=new TrieNode();
			t=t.children[res];
			curnum-=res*divnum;
			divnum/=2;
		}
	}
	
	static void updateMax(int num) {
		int curnum=num; int curres=0;
		int divnum=(int) Math.pow(2, 30);
		TrieNode t=root;
		for(int i=0; i<31; i++) {
			int res=curnum/divnum;
			int opp=(res==0)?1:0;
			if(t.children[opp]==null) {
				t=t.children[res];
			}else {
				curres+=divnum;
				t=t.children[opp];
			}
			curnum-=res*divnum;
			divnum/=2;
		}
		maxNum=Math.max(maxNum, curres);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		int N=(int)st.nval;
		
		int[] numbers=new int[N];
		root=new TrieNode();
		maxNum=0;
		
		for(int i=0; i<N; i++) {
			st.nextToken();
			numbers[i]=(int)st.nval;
		}
		
		for(int i=0; i<N; i++) makeTree(numbers[i]);
		for(int i=0; i<N; i++) updateMax(numbers[i]);
		System.out.println(maxNum);
		
		br.close();
	}

}
