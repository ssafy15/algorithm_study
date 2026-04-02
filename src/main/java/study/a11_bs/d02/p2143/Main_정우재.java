package main.java.study.a11_bs.d02.p2143;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static class PartSum{
        int value;
        int count;

        public PartSum(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static int N, M, T;
    static ArrayList<PartSum> aList, bList;
    static TreeMap<Integer, Integer> aTreeMap, bTreeMap;
    static int[][] aDp, bDp;
    public static void main(String[] args) throws IOException {
        BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(in.readLine());
        N = Integer.parseInt(in.readLine());
        aDp = new int[N][N];
        aTreeMap = new TreeMap<>();
        bTreeMap = new TreeMap<>();

        st = new StringTokenizer(in.readLine(), " ");
        int sum = 0;
        for(int i = 0 ; i < N ;i++){
            sum += Integer.parseInt(st.nextToken());
            aDp[0][i] = sum;
            if(!aTreeMap.containsKey(sum))
                aTreeMap.put(sum, 1);
            else
                aTreeMap.put(sum, aTreeMap.get(sum)+1);
        }

        M = Integer.parseInt(in.readLine());
        bDp = new int[M][M];
        st = new StringTokenizer(in.readLine(), " ");
        sum = 0;
        for(int i = 0 ; i < M ;i++){
            sum += Integer.parseInt(st.nextToken());
            bDp[0][i] = sum;
            if(!bTreeMap.containsKey(sum))
                bTreeMap.put(sum, 1);
            else
                bTreeMap.put(sum, bTreeMap.get(sum)+1);
        }

        makeDp(aDp, N, aTreeMap);
        makeDp(bDp, M, bTreeMap);

        aList = makeList(aTreeMap);
        bList = makeList(bTreeMap);

        long result = binarySearch();
        System.out.println(result);
    }

    private static long binarySearch(){
        long result = 0;
        for(PartSum ps : aList){
            int a = ps.value;
            int target = T - a;

            int s = 0;
            int e = bList.size();
            int index = -1;
            while(s < e){
                int mid = (s+e)/2 ;
                int b = bList.get(mid).value;

                if(b == target){
                    index = mid;
                    break;
                }

                if(b > target)
                    e = mid;
                else
                    s = mid+1;
            }

            if(index == -1) continue;

            result += ((long)ps.count * bList.get(index).count);
        }
        return result;
    }

    private static ArrayList<PartSum> makeList(TreeMap<Integer, Integer> treeMap) {
        Iterator<Integer> keys = treeMap.keySet().iterator();
        ArrayList<PartSum> list = new ArrayList<>();
        while(keys.hasNext()){
            int key = keys.next();
            int count = treeMap.get(key);
            list.add(new PartSum(key, count));
        }
        return list;
    }

    private static void makeDp(int[][] dp, int size, TreeMap<Integer, Integer> treeMap) {
        for(int i = 1 ; i < size; i++){
            for(int j = i; j < size ; j++){
                dp[i][j] = dp[0][j] - dp[0][i-1];
                if(!treeMap.containsKey(dp[i][j]))
                    treeMap.put(dp[i][j], 1);
                else
                    treeMap.put(dp[i][j], treeMap.get(dp[i][j])+1);
            }
        }
    }
}
