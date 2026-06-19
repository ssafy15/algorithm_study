package main.java.study.a14_segtree.d03.p2615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main_조은진 {
	static int[] segtree;
	static int n;
	static long res;
	static List<int[]> inputs2;
	
	
	//현재 자신이 뻗는 것보다 많이 뻗어있는 것을 세면 된다. 
	// 3 1 4 2 5
	// 1 2 3 4 5 이런 식으로 좌표를 압축
	// seg tree를 통해 현재번호-n까지 뻗어 있는 선 수를 센다. 그걸 더해주면 된다.
	// 그리고 갔을 때마다 더해 줘야 함.
	
	static int binarysearch(int num, int start, int end) {
		if(start>end) return -1;
		int mid=(start+end)/2;
		int compareNum=inputs2.get(mid)[0];
		if(compareNum==num) return mid;
		else if(compareNum>num) return binarysearch(num, start, mid-1);
		else return  binarysearch(num, mid+1, end);
	}
	
	
	static int query(int n, int start, int end, int qs, int qe) {
		if(qe<start||end<qs) return 0;
		if(qs<=start&&end<=qe) return segtree[n];
		int l=2*n, r=l+1, m=(start+end)/2;
		return query(l,  start, m, qs, qe)+query(r, m+1, end, qs, qe);
	}
	
	
	//전파 지연을 쓸까 했다가 의미가 없을 거 같아 그냥 함
	static void update(int node, int start, int end, int idx) {
		if(start==end) {
			segtree[node]+=1;
			return;
		}
		int mid=(start+end)/2;
		if(idx<=mid) update(node*2, start, mid, idx);
		else update(node*2+1, mid+1, end, idx);
		
		segtree[node]=segtree[node*2]+segtree[node*2+1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		n=(int)st.nval;
		
		res=0;
		
		int[] inputs1=new int[n];
		int[] orders=new int[n];
		inputs2=new ArrayList<>();
		
		 segtree=new int [4*n+1];
		
		//1. 일단 압축한다. 
		// 위에 걸 압축 필요-> input1과 2를 받고 index를 표시한다.
		//2를 정렬한 것을 만든 다음, binary search를 통해 찾는다. 
		//해당하는 인덱스들을 하나씩 쓴다.
		for(int i=0; i<n; i++) {
			st.nextToken();
			inputs1[i]=(int)st.nval;
		}
		for(int i=0; i<n; i++) {
			st.nextToken();
			int[] temp=new int[2];
			temp[0]=(int)st.nval;
			temp[1]=i+1;
			inputs2.add(temp);
		}
		
		inputs2.sort((o1, o2)->Integer.compare(o1[0], o2[0]));
		
		for(int i=0; i<n; i++) {
			int findnum=binarysearch(inputs1[i], 0, n-1);
			orders[i]=inputs2.get(findnum)[1];
		}
		
		for(int i=0; i<n; i++) {
			res+=query(1, 1, n, orders[i]+1, n);
			update(1, 1, n, orders[i]);
		}
		
		System.out.println(res);
		br.close();
	}
}
