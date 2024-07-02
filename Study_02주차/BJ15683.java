package Jo_Seongjeong.Study_2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15683 감시
 *
 * 조건
 * 맵 크기 N x M : 각 1 ~ 8
 * CCTV 개수 K : <= 8
 * CCTV 종류 1 ~ 5
 * 1. 한쪽방향
 * 2. 양쪽방향(서로 반대)
 * 3. 직각방향
 * 4. 세방향
 * 5. 네방향
 * CCTV는 90도로 회전할 수 있다
 * 0 : 빈칸
 * 6 : 벽
 * 1 ~ 5 : CCTV 번호
 * CCTV는 벽을 통과할 수 없다
 * CCTV는 CCTV를 통과할 수 있다
 *
 * 문제에서 구하고자 하는 것
 * 사각 지대의 최소 크기
 *
 * 문제 해결 프로세스
 * CCTV 번호에 따라 기능 구현
 * 맵에 대해 탐색
 * 1, 2, 3, 4번 CCTV는 4번 회전 켜보면서 반복
 * 최소값 찾기
 *
 * 고려한 시간 복잡도
 * 8 * 8 * 4 * 4 * 8 * 8 = 65,536
 * */

public class BJ15683 { // 메모리 : 46936kb, 시간 : 384ms
    static int n;
    static int m;
    static int[][] map;
    static int[][] temp;
    static List<int[]> cctv;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static int[] turn;
    static int[] picked;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cctv = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0 && map[i][j] < 6) cctv.add(new int[] {i, j});
            }
        }

        for (int i = cctv.size() - 1; i >= 0; i--) {
            int[] index = cctv.get(i);
            if (map[index[0]][index[1]] == 5) {
                five(index[0], index[1]);
                cctv.remove(i);
            }
        }

        turn = new int[4];
        for(int i = 0 ; i < 4; i++) {
            turn[i] = i;
        }

        picked = new int[cctv.size()];
        permu(0);

        System.out.println(min);
    }


    private static void permu(int index) {
        if(index == cctv.size()) {
            temp = new int[n][m];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            for(int i = 0; i < cctv.size(); i++) {
                switch(temp[cctv.get(i)[0]][cctv.get(i)[1]]) {
                    case 1:
                        one(cctv.get(i)[0], cctv.get(i)[1], picked[i]);
                        break;
                    case 2:
                        two(cctv.get(i)[0], cctv.get(i)[1], picked[i]);
                        break;
                    case 3:
                        three(cctv.get(i)[0], cctv.get(i)[1], picked[i]);
                        break;
                    case 4 :
                        four(cctv.get(i)[0], cctv.get(i)[1], picked[i]);
                        break;
                }
            }

            int count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(temp[i][j] == 0) count++;
                }
            }
            min = Math.min(min, count);

            return;
        }


        for(int i = 0; i < 4; i++) {
            picked[index] = i;
            permu(index+1);
        }

    }


    private static void one(int row, int col, int dir) {
        for(int i = 1; i <= Math.max(n, m); i++) {
            int nr = row + delta[dir][0] * i;
            int nc = col + delta[dir][1] * i;

            if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
            if(temp[nr][nc] == 6) break;
            if(temp[nr][nc] == 0) temp[nr][nc] = 7;
        }
    }

    private static void two(int row, int col, int dir) {
        if(dir == 1 || dir == 3) {
            dir = 2;
        }
        else {
            dir = 0;
        }
        for(int d = dir; d < dir+2; d++) {
            for(int i = 1; i <= Math.max(n, m); i++) {
                int nr = row + delta[d][0] * i;
                int nc = col + delta[d][1] * i;

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                if(temp[nr][nc] == 6) break;
                if(temp[nr][nc] == 0) temp[nr][nc] = 7;
            }
        }

    }

    private static void three(int row, int col, int dir) {
        if(dir == 0 || dir == 1) {
            for (int d = dir; d < 4; d += 2) {
                for (int i = 1; i <= Math.max(n, m); i++) {
                    int nr = row + delta[d][0] * i;
                    int nc = col + delta[d][1] * i;

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                    if (temp[nr][nc] == 6) break;
                    if (temp[nr][nc] == 0) temp[nr][nc] = 7;
                }
            }
        }

        if(dir == 2) {
            dir = 1;
            for (int d = dir; d < dir+2; d ++) {
                for (int i = 1; i <= Math.max(n, m); i++) {
                    int nr = row + delta[d][0] * i;
                    int nc = col + delta[d][1] * i;

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                    if (temp[nr][nc] == 6) break;
                    if (temp[nr][nc] == 0) temp[nr][nc] = 7;
                }
            }

        }
        else if(dir == 3) {
            dir = 0;
            for (int d = dir; d < 4; d += 3) {
                for (int i = 1; i <= Math.max(n, m); i++) {
                    int nr = row + delta[d][0] * i;
                    int nc = col + delta[d][1] * i;

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                    if (temp[nr][nc] == 6) break;
                    if (temp[nr][nc] == 0) temp[nr][nc] = 7;
                }
            }
        }

    }

    private static void four(int row, int col, int dir) {

        for(int d = 0; d < 4; d++) {
            if(d == dir) continue;

            for(int i = 1; i <= Math.max(n, m); i++) {
                int nr = row + delta[d][0] * i;
                int nc = col + delta[d][1] * i;

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                if(temp[nr][nc] == 6) break;
                if(temp[nr][nc] == 0) temp[nr][nc] = 7;
            }
        }

    }

    private static void five(int row, int col) {
        for(int d = 0; d < 4; d++) {
            for(int i = 1; i <= Math.max(n, m); i++) {
                int nr = row + delta[d][0] * i;
                int nc = col + delta[d][1] * i;

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) break;
                if(map[nr][nc] == 6) break;
                if(map[nr][nc] == 0) map[nr][nc] = 7;
            }
        }
    }
}
