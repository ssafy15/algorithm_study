package main.java.study.a12_trie.d01.중복되는_순열;


import java.util.*;
import java.io.*;

public class Main_조은진 {
    static class TrieNode{
		TrieNode[] children=new TrieNode[10];
		boolean isEnd;
		
		TrieNode(){
			for(int i=0; i<9; i++) children[i]=null;
			isEnd=false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		TrieNode root=new TrieNode();
		boolean flag=false;
		for(int i=0; i<N&&!flag; i++) {
			String s=br.readLine();
			TrieNode t=root;
			for(int j=0; j<s.length(); j++) {
				int index=s.charAt(j)-'0';
				if(t.children[index]==null) {
					t.children[index]=new TrieNode();
				}
				if(t.children[index].isEnd&&j<s.length()-1) {
					flag=true;
					break;
				}
				t=t.children[index];
			}
			t.isEnd=true;
		}
		if(flag) System.out.println(0);
		else System.out.println(1);
		br.close();
	}
}

