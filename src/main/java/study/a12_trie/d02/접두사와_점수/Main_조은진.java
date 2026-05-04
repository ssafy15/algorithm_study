package main.java.study.a12_trie.d02.접두사와_점수;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	static class TrieNode{
		TrieNode[] children=new TrieNode[26];
		long len; long count;
		
		TrieNode(long len) {
			this.len=len;
			this.count=0;
			for(int i=0; i<26; i++) children[i]=null;
		}
	}
	
	static TrieNode root;
	static long max=0;
	
	public static void findMax(TrieNode cur) {
		for(int i=0; i<26; i++) {
			if(cur.children[i]!=null) findMax(cur.children[i]);
		}
		max=Math.max(max, cur.count*cur.len);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		root=new TrieNode(0);
		for(int i=0; i<N; i++) {
			String s=st.nextToken();
			TrieNode t=root;
			for(int j=0; j<s.length(); j++) {
				int index=s.charAt(j)-'a';
				if(t.children[index]==null) {
					t.children[index]=new TrieNode(t.len+1);
				}
				++t.children[index].count;
				t=t.children[index];
			}
		}
		findMax(root);
		System.out.println(max);
		br.close();
	}
}
