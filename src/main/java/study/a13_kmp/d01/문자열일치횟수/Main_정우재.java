package main.java.study.a13_kmp.d01.문자열일치횟수;

import java.io.*;
import java.util.*;

/*

완탐 : 10만 * 10만 => 100초 이므로 시간 초과
KMP
- P의 문자열을 오른쪽으로 N번 한 칸씩 shift를 하게 되면, 나오는 경우의 수가 만족되도록 P의 문자열 뒤에 P의 부분 문자열을 붙이자
- 이어 붙여진 새로운 P의 문자열에 대해서 T의 문자열이 몇번 일치하는지 횟수를 세면 된다.
- N번 미룬다고 하더라도 0~N-1번 shift 했을 때와 비교를 하므로 P 문자열 뒤에 붙이는 P의 부분 문자열은 마지막 문자를 제외한 부분 문자열을 붙인다 (예제를 통해 확인)
시간 복잡도 : O(N + M ) => 10만 + 20만 => 30만 (ok)


로직
- T와 P 문자열을 입력받고, P의 문자열에는 마지막 문자를 제외한 부분 문자열을 뒤에 붙여준다
- 부분일치 테이블을 만든다
- T를 P와 앞에서부터 비교하며 KMP 알고리즘을 수행한다

 */
public class Main_정우재 {
    static int N;
    static int[] table;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        String T = in.readLine();
        String P = in.readLine();

        String subP = P.substring(0, N-1);
        P += subP;

        KMP(T, P);
        System.out.println(count);
    }

    private static void KMP(String pattern, String target) {
        makeTable(pattern);

        int pIdx = 0;
        for(int i = 0 ; i < target.length() ; i++){
            while(pIdx > 0 && pattern.charAt(pIdx) != target.charAt(i))
                pIdx = table[pIdx-1];

            if(pattern.charAt(pIdx) == target.charAt(i)){
                if(pIdx == pattern.length() - 1){
                    count++;
                    pIdx = table[pIdx];
                }
                else {
                    pIdx++;
                }
            }
        }
    }

    private static void makeTable(String pattern) {
        table = new int[N];

        int idx = 0;
        for(int i = 1 ; i < pattern.length(); i++){
            while(idx > 0 && pattern.charAt(idx) != pattern.charAt(i))
                idx = table[idx-1];

            if(pattern.charAt(idx) == pattern.charAt(i))
                table[i] = ++idx;
        }
    }
}
