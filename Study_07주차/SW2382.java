package Jo_Seongjeong.Study_07주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 2382 미생물 격리
 *
 * 조건
 * 구역 크기 N * N : 5 ~ 100
 * 격리 시간 M : 1 ~ 1000
 * 미생물 군집 수 K : 5 ~ 1000
 * 바깥쪽 가장자리부분은 약품이 칠해져 있음
 * 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동
 * 이동방향 : 상하좌우 (1, 2, 3, 4)
 * 약품에 도착하면 군집 내 미생물 사망 + 이동방향 반대
 * 홀수인 경우 : 버림 처리
 * 이 외, 한 셀에 모이는 경우 군집들이 합쳐짐
 * -> 이동방향은 가장 수가 많은 군집의 이동방향
 *
 * 문제에서 구하고자 하는 것
 * M 시간 후 남아 있는 미생물 수의 총 합
 *
 * 문제 해결 프로세스
 * 1. 큐에 r, c, num, dir 순으로 배열을 만들어 입력을 저장한다
 * 2. 0 ~ m-1번 이동을 반복한다
 *  2.1. 큐에서 하나를 뽑는다
 *  2.2. 다음 칸의 수를 맵 배열에 값을 넣는다
 *  2.3.가장자리의 경우, 방향을 갱신해 방향 배열에 값을 넣는다
 *  2.4. 가장자리가 아닌 경우, 맵 배열의 값과 더해 값을 넣는다
 *  2.5. 이때, 최대값 배열에 값을 갱신해 넣고 이에 따라 방향 배열에도 값을 갱신해 넣는다
 *  2.6. 맵을 돌며 다음 순서에서 반복할 수 있도록, 큐에 똑같이 넣는다
 * 3. 답을 구한다
 *  3.1. 큐를 끝까지 poll하며 값을 누적시킨다
 *
 * 고려한 시간 복잡도
 * 400000000 -> 5초 가능
 *
 * */

public class SW2382 { // 메모리 : 108708kb, 시간 : 719ms
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static int[][] dir;
    static int[][] max;
    static int[][] delta = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#" + t + " ");
            st = new StringTokenizer(br.readLine());

            int sum = 0;
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            queue = new ArrayDeque<>();

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                queue.offer(new int[] {r, c, num, d});
            }

            for(int i = 0; i < m; i++) {
                map = new int[n][n];
                dir = new int[n][n];
                max = new int[n][n];
                check();

            }

            while(!queue.isEmpty()) {
                int[] loc = queue.poll();
                sum += loc[2];
            }

            sb.append(sum + "\n");
        }

        System.out.println(sb);
    }

    private static void check() {
        int size = queue.size();

        for(int i = 0; i < size; i++) {
            int[] loc = queue.poll();

            int nr = loc[0] + delta[loc[3]-1][0];
            int nc = loc[1] + delta[loc[3]-1][1];

            if(nr == 0 || nr == n-1 || nc == 0 || nc == n-1 ) {
                int d;
                if(loc[3] == 1) d = 2;
                else if(loc[3] == 2) d = 1;
                else if(loc[3] == 3) d = 4;
                else d = 3;

                map[nr][nc] = loc[2]/2;
                dir[nr][nc] = d;
            }

            else if(nr > 0 || nr < n-1 || nc > 0 || nc < n-1 ) {
                if(map[nr][nc] == 0) {
                    map[nr][nc] = loc[2];
                    max[nr][nc] = loc[2];
                    dir[nr][nc] = loc[3];
                }
                else {
                    if(max[nr][nc] < loc[2]) {
                        max[nr][nc] = loc[2];
                        dir[nr][nc] = loc[3];
                    }
                    map[nr][nc] += loc[2];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) continue;
                queue.offer(new int[] {i, j, map[i][j], dir[i][j]});
            }
        }
    }
}
