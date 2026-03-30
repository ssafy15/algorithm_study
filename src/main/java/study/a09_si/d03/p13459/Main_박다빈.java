package main.java.study.a09_si.d03.p13459;

import java.io.*;
import java.util.*;

/*
 *
 <인사이트..>
 혼자 힘으로 해결하지 못했다.
 : 전반적으로 어디서 막혔는지 어떻게 해결했는지 스토리 형식으로 적어본다.

일단 미로 속에서 길을 찾는 로직이니, 전체 알고리즘은 bfs가 맞다.

1. ball 관리
 처음에는 blue ball이랑 red ball을 따로 관리했다.
 그런데 로직이 꼬이더라.
 따라서 Node를 하나 만들어서 해결했다.

 2. 이동 & 탈출 로직
 그리고 나서 이동시킬 때 조건인, 쭉 이동한다는 것을 구현할때,
 언제 O를 만났고 언제 while loop을 탈출할 수 있을지에 대한 로직을 못짰다.
 여기서는 move 조건에 현재 O라면을 걸어두어서 해결했다고한다.

 3. 공이 겹칠 경우 (공이 같이 이동할 경우)
 그리고 나서 A,B공이 겹칠수 없다는 로직을 짤때 무한하게 코드를 다량으로 생성했는데
 일단 움직이고 겹칠경우 각각의 공이 이동한거리를 비교해서 한발자국 물러나주는 로직으로 해결을 했다.

 4. 이전 문제 회상
 이전에 이런 류의 문제를 한번 풀어본 적이 있는데 힘들게 푼 기억이 있다.
 당시 공이 1개인 문제였는데, 2개가 되니 정말 로직이 어려워지더라..
 https://www.acmicpc.net/problem/20926


 거의 블로그를 참고해서 구현을 했고, 어디서 막혔는지 정리해두는 정도만 하려한다.

 전반적인 코드 스타일을 아래 블로그를 참고함.

<참고한 블로그>
 https://data-make.tistory.com/592

 */



public class Main_박다빈 {
    static int N;
    static int M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[][] movePolicy= {{-1,0},{1,0},{0,1},{0,-1}};
    static Queue<Turn> queue;
    static class Turn{
        int rx, ry, bx, by, time;

        public Turn(int rx, int ry, int bx, int by, int time) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.time= time;
        }
    }



    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        queue = new ArrayDeque<>();


        int[] blue = {-1,-1};
        int[] red= {-1,-1};

        for(int i=0;i<N;i++) {
            String com = br.readLine();
            map[i] = com.toCharArray();
            for(int j=0;j<M;j++) {
                if (map[i][j]=='B') {
                    blue = new int[] {i,j};
                } else if (map[i][j]=='R') {
                    red= new int[] {i,j};
                }

            }
        }


        queue.add(new Turn(red[0],red[1],blue[0],blue[1], 0));
        visited[red[0]][red[1]][blue[0]][blue[1]] = false;


        int result = process();
        if (result==0 || result>10) System.out.println(0);
        else System.out.println(1);


    }

    static class Marble{
        int x,y, dist;
        public Marble(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist= dist;
        }
    }

    public static Marble move(int x, int y, int dist, int[] m) {

        //멈추는 포인트를 O에도 주자
        while(map[x+m[0]][y+m[1]]!='#' && map[x][y]!='O') {
            x+=m[0];
            y+=m[1];
            dist++;
        }

        return new Marble(x, y, dist);
    }


    public static int process() {
        while(!queue.isEmpty()) {
            Turn now = queue.poll();
            int time = now.time;
            for(int[] m : movePolicy) {
                //빨간 구슬 이동
                Marble redM = move(now.rx, now.ry, 0, m);

                //파란 구술 이동
                Marble blueM = move(now.bx, now.by, 0, m);

                if(map[blueM.x][blueM.y]=='O') continue;
                //time
                if(map[redM.x][redM.y]=='O') return time+1;

                //빨간 구슬과 파란구슬 칸이 같을 경우
                if(redM.x == blueM.x && redM.y == blueM.y) {
                    //만약 빨간 구슬이 더 많이 이동했을 경우
                    if(redM.dist > blueM.dist) {
                        redM.x -=m[0];
                        redM.y -=m[1];
                    } else {
                        blueM.x -=m[0];
                        blueM.y -=m[1];
                    }
                }

                //이미 시도한 상태라면 pass
                if(visited[redM.x][redM.y][blueM.x][blueM.y]) continue;
                visited[redM.x][redM.y][blueM.x][blueM.y] = true;
                //Queue에 추가
                queue.add(new Turn(redM.x, redM.y, blueM.x, blueM.y, time+1));


            }
        }

        return 0;

    }


}
