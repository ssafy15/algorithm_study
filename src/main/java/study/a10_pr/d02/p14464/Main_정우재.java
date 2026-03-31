package main.java.study.a10_pr.d02.p14464;

import java.io.*;
import java.util.*;

/*
닭을 기준으로 닭이 데려갈 수 있는 소들 중에서 종료가 가장 임박한 소부터 데려간다.
(닭이 데려갈 수 있는 소는 start 시간이 t보다 작은 모든 소들이다. 그 중에서 end 가 지난 소들은 데려갈 수 없으니 버리고, 남은 소중에서 end가 가장 작은 소를 데려간다.)


- 각 닭마다 출발 시간을 기준으로 정렬된 소들 중 본인이 데려갈 수 있는 소들을 queue에 넣는다.
- queue에 들어있는 소들 중에서 end값이 현재 닭의 t시간 보다 작은 소들은 데려갈 수 없으므로 버린다
- 만약 queue가 비어있지 않았다면 소를 pop 해서 닭이 데려간다
 */

public class Main_정우재 {
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
    static int C,N;
    static Cow[] cows;
    static int[] chickens;
    static Queue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cows = new Cow[N];
        chickens = new int[C];

        for(int i = 0 ; i < C ; i++){
            chickens[i] = Integer.parseInt(in.readLine());
        }

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(start, end);
        }

        int result = solve();
        System.out.println(result);
    }

    public static int solve(){
        Arrays.sort(chickens); //T시간 순서대로 닭 정렬
        Arrays.sort(cows); //출발시간으로 정렬

        int cnt = 0;
        int cowIdx = 0;

        //각 닭마다 본인이 데려갈 수 있는 소를 확인하고, 가장 end시간이 급한 소부터 데려감
        for(int i = 0 ; i < C ;i++){
            int chicken = chickens[i];

            //소의 출발 시간이 chicken의 시간보다 작거나 같은경우 데려갈 수 있는 소
            while(cowIdx < N && cows[cowIdx].start <= chicken)
                pq.add(cows[cowIdx++].end);

            //end 시간이 급한 소부터 정렬이 된 상태에서, 만약 end가 이미 닭의 시간보다 작은경우는 해당 소는 데려갈 수 없으므로 제거
            while(!pq.isEmpty() && pq.peek() < chicken) //
                pq.poll();

            //데려갈 있는 소를 찾으면 데려감
            if(!pq.isEmpty()){
                cnt++;
                pq.poll();
            }
        }

        return cnt;
    }
}

