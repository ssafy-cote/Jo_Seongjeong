package Jo_Seongjeong.Study_5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2573 빙산
 *
 * 조건
 * N x M 행렬 : 각 3 ~ 300
 * 0 : 바다
 * 1~9 : 빙산의 높이
 * 최대 빙산의 개수 : 1000
 * 처음과 마지막 행과 열은 모두 0
 * 각 좌표의 빙산이 1년에 줄어드는 수 : 빙산의 4면 중 바다의 개수
 *  -> 만약 4면이 모두 빙산이면 줄어들지 않음
 * 빙산의 4면이 모두 바다인 경우 빙산은 분리된 것으로 간주
 * 제일 처음 주어지는 빙산은 한 덩어리
 *
 * 문제에서 구하고자 하는 것
 * 빙산이 분리되는 최초의 시간(년)
 * 만일 빙산이 다 녹을 때까지 분리되지 않으면 0
 *
 * 문제 해결 프로세스
 * 입력을 받을 때 빙산으로 표시된 좌표 개수도 같이 받음
 * 빙산의 사면을 탐색 할 델타 생성
 * 1 ~ n-1, 1 ~ m-1 반복문을 통해 각 빙산의 4면을 탐색해 바다 개수만큼 해당 좌표에서 빼줌
 * 만약 0이 되면 빙산 개수--
 * 시간++
 * 덩어리 체크
 * 제일 첫 빙산 좌표 하나를 찾다가 발견하면 bfs 진행
 * 만약 빙산 개수만큼 탐색이 안됐다면 분리된 것이므로 종료
 * 반복하다가 만약 빙산 개수가 0개가 된다면 0 출력 후 종료
 *
 * 고려한 시간 복잡도
 * (1000 * 4  + 1000) * 9 = 45,000
 * */

public class BJ2573 { // 메모리 : 98168kb, 시간 : 504ms
    static int n;
    static int m;
    static int[][] map;
    static Queue<int[]> queue;
    static boolean[][] isChecked;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int iceberg;
    static int year;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0) iceberg++;
            }
        }
        boolean flag = true;
        while(flag) {

            isChecked = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0) melt(i, j);
                }
            }
            year++;
            if (iceberg == 0) {
                year = 0;
                break;
            }
            flag = bfs();
        }

        System.out.println(year);

    }
    private static void melt(int r, int c) {
        isChecked[r][c] = true;
        int count = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if(isChecked[nr][nc]) continue;
            if(map[nr][nc] > 0) continue;

            count++;
        }

        map[r][c] -= count;
        if(map[r][c] < 0) map[r][c] = 0;
        if(map[r][c] == 0) iceberg--;
    }

    private static boolean bfs() {
        queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        A: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                    break A;
                }
            }
        }
        int temp = 1;

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nr = loc[0] + delta[i][0];
                int nc = loc[1] + delta[i][1];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[] {nr, nc});
                temp++;
            }
        }

        if(temp == iceberg) return true;
        else return false;
    }
}
