package main.java.study.a12_trie.d04.두개를이어붙여서만들어진팰린드롬;


import java.io.*;
import java.util.*;

public class Main_조은진 {
	static class TrieNode {
		boolean isEnd;
		TrieNode[] children;
		
		TrieNode(){
			children=new TrieNode[26];
			for(int i=0; i<26; i++) children[i]=null;
			isEnd=false;
		}
		
	}
	
	static TrieNode root2;//반
	static int res=0;
	
	static void makeTree(String word, int wordindex) {

		int n=word.length()-1;
		TrieNode curt=root2;
		for(int i=0; i<word.length(); i++) {
			int index=word.charAt(n-i)-'a';
			if(curt.children[index]==null) {
				curt.children[index]=new TrieNode();
			}
			curt=curt.children[index];
		}
		curt.isEnd=true;
	}//나무 만들기
	
	static boolean isPalindrome(String word, int left, int right) {
		while(left<right) {
			if(word.charAt(left)!=word.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	} // left 시작 right까지 팰린드롬인지 확인
	
	static boolean findWord(String word, int start, int end) {
		
		TrieNode curt=root2;
		
		for(int i=start; i<=end; i++) {
			int index=word.charAt(i)-'a';
			if(curt.children[index]==null) {
				curt=null;
				break;
			}
			curt=curt.children[index];
		}
		
		if(curt==null||!curt.isEnd) return false;
		return true;
	}
	
	static void startCom(String word) {
		
		int n=word.length();
		
		StringBuffer sb=new StringBuffer(word);
		String re=sb.reverse().toString();
		
		// 반대의 애들이 결합해서 팰린드롬이 되는 경우
		if(!re.equals(word)&&findWord(word, 0, n-1)) {
			res=Math.max(res, n*2);
		}
		
		for(int i=0; i<n-1; i++) {
			if(isPalindrome(word, 0, i)&&findWord(word, i+1, n-1)) {
				res=Math.max(res, (n-i-1)*2+i+1);
			} // 새단어+ 팰린드롬+ 이전단어 끝부분
		}
		
		for(int i=n-1; i>0; i--) {
			if(isPalindrome(word, i, n-1)&&findWord(word, 0, i-1)) {
				res=Math.max(res, (n-i)+(i)*2);
			}
		}// 이전 단어 앞부분+ 팰린드롬+ 새단어
	
	}//주어진 단어 붙였을 때 가능한 애들의 subtree 구하기
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		String[] words=new String[N];
		
		root2=new TrieNode();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) words[i]= st.nextToken();
		
		for(int i=0; i<N; i++) makeTree(words[i], i);
		for(int i=0; i<N; i++) startCom(words[i]);
		
		System.out.println(res);
		
		br.close();
	}
}
