package Jo_Seongjeong.Study_34주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 16918 봄버맨
 *
 * 조건
 * 시간 n : 1 ~ 200
 * 격자판 R * C : 1 ~ 200
 * 비어있거나, 폭탄이 있음 -> O, .
 * 폭탄이 있으면 3초 후 폭발 -> 빈 칸
 * 인접칸은 연쇄 폭발(1번만, 폭발x, 파괴)
 * 봄버맨 이동
 * 초기, 폭탄 설치 (시간은 동일)
 * 다음 1초 동안 대기
 * 다음 폭탄 없는 모든 칸에 폭탄 설치 (시간은 동일)
 * 다음 초기 설치 폭탄 폭발
 * 반복
 *
 * 문제에서 구하고자 하는 것
 * 초기상태 주어진 경우, n초 후 격자판 상태
 *
 * 문제 해결 프로세스
 * 사방탐색, 반복
 *
 * 고려한 시간 복잡도
 * 200 * 200 * 200 * 4 = 32000000
 * */

public class BJ16918 { // 메모리 : 85236kb, 시간 : 272ms
    static int r;
    static int c;
    static int n;
    static char[][] map;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static Queue<int[]> queue;
    static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new int[r][c];
        queue = new ArrayDeque<>();

        for(int i = 0; i < r; i++) Arrays.fill(visited[i], -1);

        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            for(int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == '.') continue;
                visited[i][j] = 0;
                queue.offer(new int[]{i, j, 0});
            }
        }

        int time = 1;
        boolean flag = false;

        while(time <= n) {
            flag = !flag;
            currentMap(time, flag);

            time++;
        }

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void currentMap(int time, boolean flag) {
        if(!flag) {
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    if(map[i][j] == 'O') continue;
                    map[i][j] = 'O';
                    visited[i][j] = time;
                    queue.offer(new int[]{i, j, time});
                }
            }
        }

        while(!queue.isEmpty()) {
            if(queue.peek()[2] + 3 > time) break;
            int[] cur = queue.poll();

            if(visited[cur[0]][cur[1]] != cur[2]) continue;
            map[cur[0]][cur[1]] = '.';

            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + delta[d][0];
                int nc = cur[1] + delta[d][1];

                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                map[nr][nc] = '.';
            }

        }
    }
}
