package Jo_Seongjeong.Study_28주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2667 단지번호붙이기
 *
 * 조건
 * 지도 크기 N * N : 25 ~ 625
 * 집이 있는 곳 : 1
 * 집이 없는 곳 : 0
 * 단지 : 집이 연결된 곳들 => 상하좌우로 다른 집이 있는 곳들, 대각은x
 *
 * 문제에서 구하고자 하는 것
 * 총 단지수, 각 단지내 집의 수 오름차순
 *
 * 문제 해결 프로세스
 * bfs
 *
 * 고려한 시간 복잡도
 * 625 * 4 = 2500
 * */

public class BJ2667 { // 메모리 : 11660kb, 시간 : 64ms
    static int n;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        int num = 0;
        map = new int[n][n];
        visited = new boolean[n][n];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 0) continue;
                if(visited[i][j]) continue;
                pq.offer(bfs(i, j));
                num++;
            }
        }

        sb.append(num).append("\n");

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);


    }

    private static int bfs(int r, int c) {
        int num = 1;
        int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();

        visited[r][c] = true;
        queue.add(new int[]{r, c});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + delta[d][0];
                int nc = cur[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
                num++;
            }
        }

        return num;
    }
}
