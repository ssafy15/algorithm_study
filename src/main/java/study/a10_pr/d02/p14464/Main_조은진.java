package main.java.study.a10_pr.d02.p14464;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_조은진 {
    //1. 일단 끝나는 시간을 기준으로 Priority Queue를 만듬. T도 정렬
	//2. T를 순회하면서 다음의 것들을 실행
	//2.1 가장 끝나는 시간이 빠른 걸 본다. T보다 끝나는 시간이 작으면 continue
	//2.2  T보다 시작하는 시간이 클 경우->keep 해둔다.
	//2.3 T보다 시작하는 시간이 작은 애가 나옴->다음 닭이 존재할 경우 다음 닭의 시간보다 끝나는 시간이 작은 애들을 priority queue에 넣어준다.
	//2.4 T보다 시작하는 시간이 작은 애가 안 나옴->해당 닭은 너무 빨리 나왔거나, 이전 닭들이 성실하게 다 안내해서 할게 없음
	//->priority queue에 모든 소들을 다 넣어준다.
	//상각 계산 방법 N번 연산의 총 비용을 구해서 N으로 나누기
	
	static class Cow implements Comparable<Cow>{
		int start;
		int end;
		
		public Cow(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Cow o) {
			return Integer.compare(this.start, o.start);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		int C=(int)st.nval;
		
		int[] chicken=new int[C];
		
		st.nextToken();
		int N=(int)st.nval;
		
		
		
		for(int i=0; i<C; i++) {
			st.nextToken();
			chicken[i]=(int)st.nval;
		}
		
		Cow[] cows=new Cow[N];
		for(int i=0; i<N; i++) {
			st.nextToken();
			int start=(int)st.nval;
			st.nextToken();
			int end=(int)st.nval;
			cows[i]=new Cow(start, end);
		}
		
		int res=0;
		Arrays.sort(chicken);
		Arrays.sort(cows);
		
		int curindex=0;
		PriorityQueue<Cow> pq=new PriorityQueue<>((o1,o2)
				->Integer.compare(o1.end, o2.end));
		
		for(int i=0; i<C; i++) {
			while(curindex<N&&chicken[i]>=cows[curindex].start) {
				pq.add(cows[curindex++]);
			}
			
			while(!pq.isEmpty()) {
				Cow c=pq.poll();
				if(c.end<chicken[i]) continue;
				else {
					++res;
					break;
				}
			}
		}
		
		System.out.println(res);
		
		br.close();
	}
}
