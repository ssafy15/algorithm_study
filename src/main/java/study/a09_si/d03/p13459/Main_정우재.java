package main.java.study.a09_si.d03.p13459;

import java.io.*;
import java.util.*;

/*

-빨간 구슬을 최단 시간으로 뺄 필요 없이 10회 안으로만 뺄 수 있는지 체크하면 된다
   -  각 방향별로 DFS를 수행하여 10회 안으로 나올 수 있는지 확인한다
- 구슬을 한 쪽으로 움직이면 빨간 구슬과 파란 구슬이 빠지거나 벽에 부딪힐 때까지 또는 다른 구슬을 부딪힐 때까지 이동
- 방문처리는 어떻게 할 것인가?
    별도로 방문처리를 하지 않음 => 최대 dfs가 1024번 수행. 시간복잡도 계산 시 20만번으로 괜찮다.

시간복잡도 계산
진행해온 방향 기준 갈 수 있는 방향은 두 곳 (상또는 하인 경우 다음으로 갈 수 있는 방향은 좌우, 좌또는 우인 경우 다음으로 갈 수 있는 방향은 상하)
=>  2^10(dfs 총 경우의 수) * 100(10번 기울 시 이동하는 최대 횟수) * 2(파란 볼, 빨간 볼) = 1024 * 100 * 2 => 약 20만번 (OK)

기능
1. 움직이기
    - 움직일 때 파란공이 빨간 공보다 먼저 움직이는 경우
        - '상'으로 이동할 때 : blue의 row값 < red의 row 값
        - '하'로 이동할 때 : blue의 row 값 > red의 row 값
        - '좌'로 이동할 때 : blue의 col 값 < red의 col 값
        - '우'로 이동할 때 : blue의 col 값 > red의 col 값
        나머지의 경우는 모두 red가 먼저 움직인다

    - 이동 방향으로 #, O, 또는 다른 공을 만날 때까지 전진

2. 공이 구멍으로 나갔는지 판단
    - 파란 공이 나간 경우 return false
    - 빨간 공이 나간 경우 return true

3. 기울이기 (아직 공이 탈출하지 못한 상태)
    - 움직인 방향이 상 또는 하인 경우 : 다음으로 갈 수 있는 방향은 좌, 우
    - 움직인 방향이 좌 또는 우인 경우 : 다음으로 갈 수 있는 방향은 상, 하
 */

public class Main_정우재 {
    static int N,M;
    static final int RED = 1;
    static final int BLUE = 0;
    static int[][] delta = {
            {-1,0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        int[] red = null;
        int[] blue = null;

        for(int i = 0 ; i < N ;i++){
            map[i] = in.readLine().toCharArray();
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 'R'){
                    red = new int[]{i, j};
                    map[i][j] = '.';
                }
                if(map[i][j] == 'B') {
                    blue = new int[]{i, j};
                    map[i][j] = '.';
                }
            }
        }

        int result =0;
        for(int i = 0 ; i < 4 ; i++){ //초기에 기울일 수 있는 방향은 4방향이므로, 4방향에 대한 dfs 수행
            if(dfs(0, red, blue, i, map)) {
                result = 1;
                break;
            }
        }

        System.out.println(result);
    }

    public static boolean dfs(int cnt, int[] red, int[] blue, int dir, char[][] map){
        if(cnt == 10)
            return false;

        char[][] newMap = new char[N][M]; //맵 깊은 복사 (맵을 복사하여 활용함으로써 별도로 방문 처리와 각 케이스별 방문 처리 롤백을 하지 않음)
        for(int i = 0 ; i < N ; i++){
            newMap[i] = map[i].clone();
        }

        int[] newBlue; //이동 후 새로운 파란 공의 좌표
        int[] newRed; //이동 후 새로운 빨간 공의 좌표
        //이동하기
        if (dir == 0 || dir ==1){ //상, 하로 이동하는 경우
            if((dir == 0 && blue[0] < red[0]) || (dir == 1 && blue[0] > red[0])) { //파란 공이 먼저 움직이는 경우
                newBlue = move(blue, 'B', 'R', dir, BLUE, newMap);
                newRed = move(red, 'R', 'B', dir, RED, newMap);
            }
            else{
                newRed = move(red, 'R', 'B', dir, RED, newMap);
                newBlue = move(blue, 'B', 'R', dir, BLUE, newMap);
            }
        }
        else{ //좌, 우로 이동하는 경우
            if((dir == 2 && blue[1] < red[1]) || (dir == 3 && blue[1] > red[1])) { //파란 공이 먼저 움직이는 경우
                newBlue = move(blue, 'B', 'R', dir, BLUE, newMap);
                newRed = move(red, 'R', 'B', dir, RED, newMap);
            }
            else{
                newRed = move(red, 'R', 'B', dir, RED, newMap);
                newBlue = move(blue, 'B', 'R', dir, BLUE, newMap);
            }
        }

        //나갔는지 판단
        if( newMap[newBlue[0]][newBlue[1]] == 'O')
            return false;
        if(newMap[newRed[0]][newRed[1]] == 'O')
            return true;

        //다음 기울이기 진행
        if( dir == 0 || dir == 1) { //상, 하로 움직였던 경우 다음 턴은 좌, 우로만 기울이기
            for (int i = 2; i < 4; i++){
                if (dfs(cnt + 1, newRed, newBlue, i, newMap))
                    return true;
                }
        }
        else { //좌, 우로 움직였던 경우 다음 턴은 상, 하로만 기울이기
            for (int i = 0; i < 2; i++) {
                if (dfs(cnt + 1, newRed, newBlue, i, newMap))
                    return true;
            }
        }
        return false;
    }

    public static int[] move(int[] pos, char target, char another, int dir, int color, char[][] map) { //공을 벽을 만나거나 구멍을 만나거나 다른 구슬을 만날 때까지 이동하는 함수 (최종 공의 위치를 반환)
        int r = pos[0];
        int c = pos[1];

        while (true) {
            map[r][c] = target; //현재 위치에 공을 표기
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];

            if (map[nr][nc] == '#') //다음이 벽인 경우
                return new int[]{r, c};

            if (map[nr][nc] == another) //다음이 다른 색 구슬인 경우
                return new int[]{r, c};

            if (map[nr][nc] == 'O') { //다음이 탈출 구역인 경우
                map[r][c] = '.'; //탈출하면서 구슬이 사라지므로 .으로 표기
                return new int[]{nr, nc};
            }

            //공이 더 움직일 수 있는 경우
            map[r][c] = '.';
            r = nr;
            c = nc;
        }
    }
}
