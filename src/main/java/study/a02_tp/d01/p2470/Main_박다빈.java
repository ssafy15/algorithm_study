package main.java.study.a02_tp.d01.p2470;

/*
 * 백준 2470번
 * - two pointer 방식
 * 1. 용액을 오름 차순으로 sort 시킨다.
 * 2. 양 끝점에 start pointer 와 end pointer를 둔다.
 * 3. start pointer와 end pointer의 값들을 서로 더하여 절대값이 현재 최적값인지 확인한다.
 * 4. 더한 값이 양수일 경우 end pointer를 앞으로 보내고, 더한 값이 음수일 경우 start pointer를 뒤로 보낸다. 그리고 더한 값이 0일 경우 답을 바로 출력할 수 있도록 한다.
 *
 * 다른 블로그들의 후기도 보니 비슷하게 푼 것 같다.
 *
 *
 * */
import java.io.*;
import java.util.*;
public class Main_박다빈 {
  public static void main(String[] args)throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 0;
        int end = N-1;
        long result = Long.MAX_VALUE;
        int[] result2 = new int[2];
        while(start<end) {
            long tmp =arr[start] + arr[end];
            if(Math.abs(tmp)<Math.abs(result)) {
                result2[0] = start;
                result2[1] = end;
                result = tmp;
            }
            if (tmp<0) start = start+1;
            else if(tmp>0) end = end-1;
            else if (tmp==0) break;

        }
        System.out.println(arr[result2[0]] + " "+ arr[result2[1]]);

    }
}
