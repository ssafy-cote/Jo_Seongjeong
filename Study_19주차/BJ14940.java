package Jo_Seongjeong.Study_19주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14940 쉬운 최단거리
 *
 * 조건
 * 지도 크기 n(가로) * m(세로) : 2 ~ 1000 => 1000000
 * 0 : 갈 수 없는 땅
 * 1 : 갈 수 있는 땅
 * 2 : 목표 지점 (한 개)
 * 움직이는 법 : 가로와 세로
 *
 * 문제에서 구하고자 하는 것
 * 2를 제외한 각 지점에서 목표지점까지의 거리
 * 원래 갈 수 없는 땅이라면 0(2인 경우도)
 * 갈 수 있는 땅인데 도달 불가면 -1 출력
 *
 * 문제 해결 프로세스
 * 레벨별 bfs로 해보자
 * 도착지점에서부터 bfs
 * 이후로 level별로 답 배열에 누적 저장
 * 처음에 0이면 0
 * 첫 지점이 0이 아닌데 답배열이 0이면 -1
 *
 * 고려한 시간 복잡도
 * 1000000 * 4
 *
 * */

public class BJ14940 { // 메모리 : 77904kb, 시간 : 640ms
    static int n;
    static int m;
    static int[] finish;
    static int[][] map;
    static int[][] answer;
    static boolean[][] visited;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        finish = new int[2];
        answer = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) {
                    finish[0] = i;
                    finish[1] = j;
                }
            }
        }

        answer[finish[0]][finish[1]] = 0;
        bfs(finish[0], finish[1]);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(answer[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int sr, int sc) {

        visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        int distance = 1;

        visited[sr][sc] = true;
        queue.offer(new int[] {sr, sc});

        while (!queue.isEmpty()) {

            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int r = loc[0];
                int c = loc[1];

                for(int d = 0; d < 4; d++) {
                    int nr = r + delta[d][0];
                    int nc = c + delta[d][1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if(visited[nr][nc]) continue;
                    if(map[nr][nc] == 0) continue;

                    visited[nr][nc] = true;
                    answer[nr][nc] = distance;
                    queue.offer(new int[] {nr, nc});
                }
            }
            distance++;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == finish[0] && j == finish[1]) continue;
                if(answer[i][j] == 0 && map[i][j] != 0) answer[i][j] = -1;
            }
        }
    }
}
