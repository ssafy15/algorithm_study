package main.java.study.a10_pr.d01.p1715;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        pq = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++){
            pq.add(Integer.parseInt(in.readLine()));
        }

        System.out.println(solve());
    }

    public static int solve(){
        int sum = 0;
        while(pq.size() != 1){
            int c1 = pq.poll();
            int c2 = pq.poll();
            sum += (c1+c2);

            pq.add(c1+c2);
        }
        return sum;
    }
}
