package main.java.study.a11_bs.d03.p1561;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_정우재 {
    static class AttractionInfo implements Comparable<AttractionInfo>{
        int index;
        long boardingTime;

        public AttractionInfo(int index, long boardingTime) {
            this.index = index;
            this.boardingTime = boardingTime;
        }

        @Override
        public int compareTo(AttractionInfo o) {
            if(this.boardingTime == o.boardingTime)
                return Integer.compare(this.index, o.index);
            return Long.compare(this.boardingTime, o.boardingTime);
        }
    }
    
    static int N, M;
    static int[] attraction;
    static AttractionInfo[] info;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        attraction = new int[M];
        info = new AttractionInfo[M];

        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < M ; i++){
            attraction[i] = Integer.parseInt(st.nextToken());
        }

        long minTime = parametricSearch();
        int index = findLastAttraction(minTime);
        System.out.println(index);

    }

    private static int findLastAttraction(long minTime) {
        long sum =0;
        ArrayList<Integer> canBoarding = new ArrayList<>();
        for(int i = 0 ; i < M; i++){
            sum += (minTime / attraction[i] +1);
            if(minTime % attraction[i] == 0)
                canBoarding.add(i);
        }

        Collections.reverse(canBoarding);
        if(N == sum)
            return canBoarding.get(0)+1;
        else{
            int diff = (int)sum - N;
            return canBoarding.get(diff)+1;
        }
    }

    private static long parametricSearch() {
        long s = 0;
        long e = 30L * 2_000_000_000;
        while(s < e){
            long mid = (s+e)/2;
            long sum =0;
            for(int i = 0 ; i < M ; i++){
                sum += (mid / attraction[i] + 1) ;
            }

            if(sum < N)
                s = mid+1;
            else
                e = mid;
        }
        return e;
    }
}
