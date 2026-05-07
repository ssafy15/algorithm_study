package main.java.study.a13_kmp.d03.문자열을최대로채워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_정우재 {
    static ArrayList<Integer>[] patternEndList;
    static int[] dp;
    static int[] table;
    static int m;
    static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        target = st.nextToken();
        m = Integer.parseInt(st.nextToken());
        patternEndList = new ArrayList[target.length()];

        for(int i = 0 ; i < patternEndList.length ; i++){
            patternEndList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < m ; i++){
            String pattern = st.nextToken();
            KMP(pattern);
        }

        int result = findResult();
        System.out.println(result);
    }

    private static int findResult() {
        dp = new int[target.length()+1];
        for(int i = 1 ; i < dp.length; i++){
            dp[i] = dp[i-1];
            for(int j = 0; j < patternEndList[i-1].size(); j++){
                int patternSize = patternEndList[i-1].get(j);
                dp[i] = Math.max(dp[i], dp[i - patternSize] + patternSize);
            }
        }
        return dp[target.length()];
    }

    public static void KMP(String pattern){
        makeTable(pattern);

        int patternLength = pattern.length();
        int idx = 0;
        for(int i = 0 ; i < target.length() ; i++){
            while(idx > 0 && target.charAt(i) != pattern.charAt(idx))
                idx = table[idx-1];

            if(target.charAt(i) == pattern.charAt(idx)){
                if(idx == pattern.length() -1){
                    patternEndList[i].add(patternLength);
                    idx = table[idx-1];
                }
                else {
                    idx++;
                }
            }
        }
    }

    public static void makeTable(String pattern){
        table = new int[pattern.length()];

        int idx = 0;
        for(int i = 1 ; i < pattern.length(); i++){
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx))
                idx = table[idx-1];

            if(pattern.charAt(i) == pattern.charAt(idx))
                table[i] = ++idx;
        }
    }
}
