package main.java.study.a13_kmp.d02.가장긴접두사;

/*
사고의 확장

        kmp 완탐 : 10만 * 10만 => 시간 초과
        접두사의 뒤집은 형태가 부분 문자열 형태로 존재할 때 최장 접두사 길이
        본문을 T의 뒤집은 문자열 형태, pattern을 원본 T로 해서 매칭이 될 때 마다 j의 값이 max값보다 큰 경우에는 업데이트를 수행한다
        결국 어떤 접두사를 뒤집든지 간에 끝나는 것은 원본의 맨 앞글자로 종료되어야 한다. 그렇기에 결국 접두사가 존재하는지 확인하기 위해서는 원본의 맨 앞글자로 시작이 되는지를 확인하는것이 편리하므로 이를 위해 본문을 뒤집는다
        시간 복잡도 : 10만 + 10만 => 20만 (OK)
        로직

        원본 T 를 뒤집어서 T에 다시 대입
        패턴 T(= 원본 T) 에 대한 부분 일치 테이블 작성
        KMP를 수행하면서 j가 늘어날 때마다 max값을 해당 j로 업데이트한다
*/

import java.io.*;
import java.util.*;


public class Main_정우재 {
    static String target;
    static String pattern;
    static int max;
    static int[] table;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        pattern = in.readLine();

        StringBuffer sb = new StringBuffer(pattern);
        target = sb.reverse().toString();

        max = 0;
        KMP();
        System.out.println(max);
    }
    private static void KMP() {
        makeTable();

        int idx = 0;
        for(int i = 0 ; i < target.length(); i++) {
            while(idx > 0 && target.charAt(i) != pattern.charAt(idx))
                idx = table[idx-1];

            if(target.charAt(i) == pattern.charAt(idx)) {
                max = Math.max(max, ++idx);
            }
        }
    }
    private static void makeTable() {
        table = new int[pattern.length()];

        int idx = 0;
        for (int i = 1; i < pattern.length() ;i++) {
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx))
                idx = table[idx-1];

            if(pattern.charAt(i) == pattern.charAt(idx))
                table[i] = ++idx;
        }
    }
}