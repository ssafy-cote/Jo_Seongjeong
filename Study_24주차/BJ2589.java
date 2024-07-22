package Jo_Seongjeong.Study_24주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2589 보물섬
 *
 * 조건
 * 가로 : 50
 * 세로 : 50
 * L : 육지 (이동가능)
 * W : 바다 (이동불가)
 * 이동 조건 : 상하좌우
 * 두 지점 사이의 최단 거리 : 같은 곳을 두 번 이상 지나가거나, 멀리 돌아서는 안 됨
 * 입력 시, 각 문자 사이에는 빈 칸 X
 * 보물이 묻혀 있는 곳 : 두 곳 사이의 최단 거리가 가장 긴 두 곳
 *
 * 문제에서 구하고자 하는 것
 * 보물이 묻혀 있는 두 곳 사이의 최단 거리
 *
 * 문제 해결 프로세스
 * level 별 BFS
 * 한 지점에서 BFS 진행하면서 레벨을 구한다
 *
 * 고려한 시간 복잡도
 * 2500 * 4 * 2500 = 25,000,000
 * */

public class BJ2589 { // 메모리 : 170780kb, 시간 : 420ms
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static Queue<int[]> queue;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'W') continue;
                bfs(i, j);
            }
        }

        System.out.println(max);
    }

    private static void bfs(int r, int c) {
        int level = 0;
        visited = new boolean[n][m];
        queue = new ArrayDeque<>();

        visited[r][c] = true;
        queue.add(new int[]{r, c});

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(nr < 0 || nr >=n || nc < 0 || nc >=m) continue;
                    if(map[nr][nc] == 'W') continue;
                    if(visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
            level++;
        }

        max = Math.max(max, level-1);
    }
}
