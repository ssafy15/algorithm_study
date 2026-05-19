package main.java.study.a14_segtree.d01.p1726;

import java.io.*;
import java.util.*;

public class Main_조은진 {

	static int[] tree;//힙과 같은 원리로 배열 인덱스 자체가 트리 구조를 표현
	static int n;
	
	static void build(int[] arr, int node, int start, int end) {
		if(start==end) {
			tree[node]=arr[start];//다 좁혀졌을 때
		} else {
			int mid=(start+end)/2;
			build(arr, node*2, start, mid);//왼쪽
			build(arr, node*2+1, mid+1, end);//오른쪽
			tree[node]=Math.max(tree[node*2], tree[node*2+1]);//자식들 중에 가장 큰 것을 자신의 값으로 한다.
		}
	}
	
	static int query(int node, int start, int end, int l, int r) {
		if(r<start||end<l) return 0;//범위에 벗어났을 경우
		if(l<=start&&end<=r) return tree[node];//범위랑 딱 맞는 경우
		int mid=(start+end)/2;
		return Math.max(query(node*2, start, mid, l, r), query(node*2+1, mid+1, end, l, r));
		//자신의 오른쪽 자식과 왼쪽 자식을 본다.
	}//node, start, end: 지금 내가 보고 있는 트리 노드가 담당하는 범위
	//l, r->실제로 알고 싶은 구간
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[] arr=new int[n];
		tree=new int[4*n];
		
		
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		build(arr, 1, 0, n-1);
		
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int res=query(1, 0, n-1, a-1, b-1);
			System.out.println(res);
		}
		
		
		br.close();
	}

}
