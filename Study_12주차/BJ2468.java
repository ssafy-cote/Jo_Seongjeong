package Jo_Seongjeong.Study_12주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2468 안전 영역
 *
 * 조건
 * 지역의 높이 정보 N * N : 2 ~ 100
 * 원소 : 해당 지점의 높이(자연수) : 1 ~ 100
 * 물에 잠기지 않는 영역 -> 비의 양 보다 큰 수의 지점
 * 안전 영역 : 물에 잠기지 않는 지점들이 상하좌우로 인접해있으며 그 크기가 최대인 영역
 * 인접 : 대각선은 취급x
 *
 * 문제에서 구하고자 하는 것
 * 비에 양에 따른 안전 영역 중, 최대 개수
 *
 * 문제 해결 프로세스
 * 1. 비의 양에 따라 잠기는 지역 설정 -> 새로운 배열로 0(잠기는 곳)/1(잠기지 않는 곳)로 만들자
 * 2. 해당 배열로 bfs 및 안전영역 개수 구하기 -> 반복문 통해 1인 경우 bfs 시작, 탐색 끝나면 개수 cnt(방문 배열로 중복 방문x)
 * 3. 반복(입력 받을 때 최대 높이-1 까지)
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 100 * 4 + 100 * 100 * 100 = 5000000
 * */

public class BJ2468 { // 메모리 : 43760kb, 시간 : 264ms
    static int n;
    static int max;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int limit = 0;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                limit = Math.max(limit, map[i][j]);
            }
        }

        for(int i = 0; i < limit; i++) {
            int[][] safety = findBySafe(i);
            visited = new boolean[n][n];
            int count = 0;
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(visited[j][k]) continue;
                    if(safety[j][k] == 0) continue;
                    bfs(j, k, safety);
                    count++;
                }
            }

            max = Math.max(max, count);
        }

        System.out.println(max);
    }

    private static int[][] findBySafe(int water) {
        int[][] safety = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] > water) safety[i][j] = 1;
            }
        }

        return safety;

    }

    private static void bfs(int sr, int sc, int[][] safety) {
        int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();

        visited[sr][sc] = true;
        queue.offer(new int[] {sr, sc});

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if(visited[nr][nc]) continue;
                if(safety[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[] {nr, nc});
            }
        }
    }
}
