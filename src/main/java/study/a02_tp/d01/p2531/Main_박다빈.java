package main.java.study.a02_tp.d01.p2531;

/*
 * 백준 2531번
 * - Sliding window 방식
 * 1. ArrayList에 window를 이동해가며 추가 + 삭제 (초밥)
 * 2. ArrayList를 set으로 변환 + 쿠폰 초밥 set에 추가 -> size check
 * 	제일 다양하게 먹을 수 있는 경우의 수 찾음
 *
 * 문제를 처음 봤을 때, (1) K개를 연속해서 먹는것 -> sliding window 방식
 * (2) 얼마나 다양한 초밥을 먹었는지 확인 -> Set
 * 을 떠올렸다.
 * 다만 (2) 가 N이 기하급수적으로 set으로 변환하는데 오버헤드가 걸리기 때문에 커지면 좋은 방법은 아닐것이라 생각이든다.
 *
 * 따라서 다른 블로그에서 인사이트를 얻어보았다.
 * 찾아보니 아래 블로그에서는
 * https://chatgpt.com/c/698149df-fe64-83a6-9828-90d1f9a0931a
 * 카운팅 배열을 사용하더라
 * 만약 다시 푼다면 카운팅 배열 형식으로 푸는것이 시간복잡도 면에서 조금더 안전할 것 같다.
 *
 * */
import java.io.*;
import java.util.*;

public class Main_박다빈 {
  public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        List<Integer> window = new ArrayList<>();
        int maxi = 0;
        for(int i=0;i<k;i++) window.add(arr[i]);

        Set<Integer> check = new HashSet<>(window);
        check.add(c);
        maxi = check.size();

        for(int i=1;i<N;i++) {

            window.remove((Integer)arr[i-1]);
            window.add((Integer)arr[(k+i-1)%N]);

            check = new HashSet<>(window);
            check.add(c);
            maxi = Math.max(check.size(),maxi);

        }

        System.out.println(maxi);


    }
}
