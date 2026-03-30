package main.java.study.a10_pr.d01.p1715;


import java.io.*;
import java.util.*;

public class Main_조은진 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		N=(int)st.nval;
		
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st.nextToken();
			pq.add((int)st.nval);
		}
		
		long res=0;
		
		while(pq.size()>1) {
			int a=pq.poll();
			int b=pq.poll();
			res+=a; res+=b;
			pq.add(a+b);
		}
		
		System.out.println(res);
		
		br.close();
	}

}
