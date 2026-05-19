package main.java.study.a13_kmp.d02.가장긴접두사;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		String s=br.readLine();
		StringBuilder sb=new StringBuilder(s);
		String r=sb.reverse().toString();
		s="#"+s;
		r="#"+r;
		
		int maxlen=0;
		int n=s.length();
		int[] f=new int[n+1];
		f[0]=-1;
		for(int i=1; i<n; i++) {
			int j=f[i-1];
			while(j>=0&&s.charAt(j+1)!=s.charAt(i))
				j=f[j];
			f[i]=j+1;
		}
		
		int j=0;
		for(int i=1; i<n; i++) {
			while(j>=0&&s.charAt(j+1)!=r.charAt(i))
				j=f[j];
			maxlen=Math.max(++j, maxlen);
		}
		
		System.out.println(maxlen);
		
		br.close();
		
	}
}
