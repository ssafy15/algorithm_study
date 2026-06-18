package main.java.study.a15_hash.d02.2236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_조은진 {

    static long start;
    static HashMap<Long, Integer> map;
    static int n;
    static int[] parent;
    static List<List<Integer>> tree;


    static void bfs() {
        Queue<Integer> queue=new LinkedList<>();

        boolean[] visited=new boolean[n];
        parent=new int[n];
        Arrays.fill(parent, -1);

        queue.offer(0);
        visited[0]=true;

        while(!queue.isEmpty()) {
            int curnode=queue.poll();
            for(int i=0; i<tree.get(curnode).size(); i++) {
                if(!visited[tree.get(curnode).get(i)]) {
                    visited[tree.get(curnode).get(i)]=true;
                    parent[tree.get(curnode).get(i)]=curnode;
                    queue.offer(tree.get(curnode).get(i));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());


        n=Integer.parseInt(st.nextToken());


        int k=Integer.parseInt(st.nextToken());

        String[] inputs1=new String[n];
        long[] nums=new long[n];

        map=new HashMap<>();

        inputs1[0]=br.readLine();
        long res=0;
        for(int j=0; j<k; j++) {
            if(inputs1[0].charAt(j)=='1') res+=(1<<k-j-1);
        }
        start=res;
        map.put(res, 0);
        nums[0]=res;

        for(int i=1; i<n; i++) {
            inputs1[i]=br.readLine();
            res=0;
            for(int j=0; j<k; j++) {
                if(inputs1[i].charAt(j)=='1') res+=(1<<k-j-1);
            }
            nums[i]=res;
            map.put(res, i);
        }


        tree=new ArrayList<>();
        for(int i=0; i<n; i++) {tree.add(new ArrayList<>());}

        for(int i=0; i<n; i++) {
            long cur=nums[i];
            for(int j=0; j<k; j++) {
                long curfindnum=cur^(1L<<j);
                if(map.containsKey(curfindnum)) {
                    int curindex=map.get(curfindnum);
                    tree.get(i).add(curindex);
                }
            }
        }

        bfs();

        int m=Integer.parseInt(br.readLine());

        List<Integer> path=new ArrayList<>();

        StringBuilder sb=new StringBuilder();
        for(int i=0; i<m; i++) {
            int findnum=Integer.parseInt(br.readLine())-1;
            boolean flag=true;
            path.clear();
            path.add(findnum);
            while(findnum!=0) {
                if(parent[findnum]==-1) {
                    flag=false;
                    break;
                }
                path.add(parent[findnum]);
                findnum=parent[findnum];
            }
            if(flag) {
                for(int j=path.size()-1; j>=0; j--) sb.append((path.get(j)+1)).append(" ");
            }else {
                sb.append(-1);
            }
            sb.append("\n");
        }
        System.out.println(sb);

        br.close();
    }



}

