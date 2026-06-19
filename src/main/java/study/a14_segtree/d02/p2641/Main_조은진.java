package main.java.study.a14_segtree.d02.p2641;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		int n=(int)st.nval;
		
		st.nextToken();
		int c=(int)st.nval;
		
		st.nextToken();
		int k=(int)st.nval;
		
		List<int[]> inputs=new ArrayList<>();
		
		for(int i=0; i<k; i++) {
			int[] oneload=new int[3];
			st.nextToken();
			oneload[0]=(int)st.nval;
			st.nextToken();
			oneload[1]=(int)st.nval;
			st.nextToken();
			oneload[2]=(int)st.nval;
			inputs.add(oneload);
		}
		
		inputs.sort((o1,o2)->{
			if(o1[1]==o2[1]) {
				return Integer.compare(o1[0], o2[0]);
			}else {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		int[] currenttmp=new int[n+1];
		int total=0;
		for(int i=0; i<k; i++) {
			int[] cur=inputs.get(i);
			boolean flag=false;
			int curcart=cur[2];
			for(int j=cur[0]; j<cur[1]; j++) {
				if(currenttmp[j]>=c) {
					flag=true;
					break;
				}
				curcart=Math.min(curcart, c-currenttmp[j]);
			}
			if(!flag) {
				for(int j=cur[0]; j<cur[1]; j++) {
					currenttmp[j]+=curcart;
				}
				total+=curcart;
			}
		}
		
		System.out.println(total);
		
		br.close();
	}

}

