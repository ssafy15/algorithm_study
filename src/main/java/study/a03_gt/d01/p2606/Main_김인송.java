package main.java.study.a03_gt.d01.p2606;

import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int[][] adj;
    static boolean[] v;
    static int N, E, ans = 0;

    static void dfs(int node) {
        v[node] = true;

        for(int i = 1; i <= N; i++) {
            if(adj[node][i] == 1 && !v[i]) dfs(i);
        }

        ans++;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adj = new int[N + 1][N + 1];
        v = new boolean[N + 1];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from][to] = 1;
            adj[to][from] = 1;
        }

        dfs(1);

        System.out.print(ans - 1);


    }
}