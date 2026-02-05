package main.java.study.a03_gt.d01.p2667;
import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] v;
    static int N, ans = 0;

    static boolean inRange(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < N);
    }

    static int dfs (int r, int c) {
        int cnt = 1;
        v[r][c] = true;

        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr,nc) && map[nr][nc] == 1 && !v[nr][nc]) cnt += dfs(nr,nc);
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        map = new int[N ][N];
        v = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        ArrayList<Integer> al = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !v[i][j]) {
                    al.add(dfs(i,j));
                    ans++;
                }
            }
        }

        Collections.sort(al);

        System.out.println(ans);

        for(int n: al) System.out.println(n);
    }
}