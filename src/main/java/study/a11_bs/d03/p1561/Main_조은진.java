package main.java.study.a11_bs.d03.p1561;

import java.io.*;
import java.util.*;


public class Main_조은진 {
	
	static int[] inputs;
	static int N, M;
	
	public static long isPosible(long curnum) {
		long cnt=0;
		for(int i=0; i<M; i++) {
			cnt+=curnum/inputs[i];
			if(cnt>N-M)return cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		N=(int)st.nval;
		
		st.nextToken();
		M=(int)st.nval;
		
		if(M>=N) {
			System.out.println(N);
			return;
		}
		
		inputs=new int[M];
		int[] original=new int[M];
		for(int i=0; i<M; i++) {
			st.nextToken();
			inputs[i]=(int)st.nval;
			original[i]=inputs[i];
		}
		Arrays.sort(inputs);
		
		long start=0; long end=6_000_000_000_000_00L;
		
		long maxStartTime=0;
		
		while(start<=end) {
			long mid=(start+end)/2;
			
			if(isPosible(mid)<N-M) {
				start=mid+1;
			}else {
				maxStartTime=mid;
				end=mid-1;
			}
		}
		
		List<Integer> maxStartIndex=new ArrayList<>();
		long cnt=0;
		for(int i=0; i<M; i++) {
			if(maxStartTime%original[i]==0) {
				cnt+=maxStartTime/original[i]-1;
				maxStartIndex.add(i);
			}else {
				cnt+=maxStartTime/original[i];
			}
		}
		
		System.out.println(maxStartIndex.get((int)(N-(cnt+M+1)))+1);
		
		br.close();
	}

}
