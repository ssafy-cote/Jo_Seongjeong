package Jo_Seongjeong.Study_17주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14503 로봇 청소기
 *
 * 조건
 * 방 n * m : 3 ~ 50 (0, 0부터)
 * 각 칸
 * 0 : 빈 칸
 * 1 : 벽
 * 로봇 청소기의 초기위치는 항상 빈 칸 -> 청소 함
 * 로봇청소기가 바로보고 있는 방향 d
 * 0 : 북
 * 1 : 동
 * 2 : 남
 * 3 : 서
 * 청소 과정
 * 현재 칸 청소
 * 주변 4칸(동서남북)이 청소되지 않은 빈칸이 없는 경우
 *  방향 유지한 채 한 칸 후진할 수 있다면 후진
 *  후진할 수 없으면 작동 멈춤
 * 청소되지 않은 빈칸이 있는 경우
 *  반시계 방향으로 회전
 *  앞 칸이 청소되지 않은 빈 칸의 경우 전진
 *
 * 문제에서 구하고자 하는 것
 * 로봇청소기가 한번 작동해서 청소한 칸의 개수
 *
 * 문제 해결 프로세스
 * dfs로 풀어야 할듯?
 *
 * 1. 4방향에 청소 안 한 빈 칸 있는지 확인
 * 2. 있으면 반시계방향으로 돌다가 청소 안 한 빈 칸이면 이동
 *  count++
 * 3. 없으면 현재 방향 기준으로 후진
 * 4. 모두 안되면 종료
 *
 * delta를 dir에 따라 재선언해서 나누자 (반시계 방향 기준)
 * 북
 * d : 0 1 2 3
 * dir : 3 2 1 0
 *
 * 동
 * d : 0 1 2 3
 * dir : 0 3 2 1
 *
 * 남
 * d : 0 1 2 3
 * dir : 1 0 3 2
 *
 * 서
 * d : 0 1 2 3
 * dir : 2 1 0 3
 *
 * 고려한 시간 복잡도
 * 50 * 50 * 4 = 10000
 * */

public class BJ14503 { // 메모리 : 11860kb, 시간 : 88ms
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    // 현재 방향 기준 후진하는 경우로 기준 잡기
    static int[][] delta = new int[][] {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    static int count = 1;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[r][c] = true;
        dfs(r, c, dir);

        System.out.println(count);

    }

    private static void dfs(int r, int c, int dir) {

        boolean flag = false;
        for(int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if(map[nr][nc] == 1) continue;
            if(visited[nr][nc]) continue;

            flag = true;
            break;
        }

        if(flag) {
            switch (dir) {
                case 0 : // 북
                    int[][] delta = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

                    for(int d = 0; d < 4; d++) {
                        int nr = r + delta[d][0];
                        int nc = c + delta[d][1];

                        if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if(map[nr][nc] == 1) continue;
                        if(visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        count++;
                        dfs(nr, nc, 3-d);
                        break;
                    }
                    break;
                case 1 : // 동
                    delta = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

                    for(int d = 0; d < 4; d++) {
                        int nr = r + delta[d][0];
                        int nc = c + delta[d][1];

                        if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if(map[nr][nc] == 1) continue;
                        if(visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        count++;
                        if(d % 2 == 0) dfs(nr, nc, d);
                        else dfs(nr, nc, 4-d);
                        break;
                    }
                    break;
                case 2 : // 남
                    delta = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

                    for(int d = 0; d < 4; d++) {
                        int nr = r + delta[d][0];
                        int nc = c + delta[d][1];

                        if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if(map[nr][nc] == 1) continue;
                        if(visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        count++;
                        if(d % 2 == 0) dfs(nr, nc, d+1);
                        else dfs(nr, nc, d-1);
                        break;
                    }
                    break;
                case 3 : // 서
                    delta = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

                    for(int d = 0; d < 4; d++) {
                        int nr = r + delta[d][0];
                        int nc = c + delta[d][1];

                        if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if(map[nr][nc] == 1) continue;
                        if(visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        count++;
                        if(d % 2 != 0) dfs(nr, nc, d);
                        else dfs(nr, nc, 2-d);
                        break;
                    }
                    break;
            }
        }
        else {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= m) return;
            if(map[nr][nc] == 1) return;
            dfs(nr, nc, dir);
        }
    }
}
