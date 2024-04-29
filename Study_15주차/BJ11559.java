package Jo_Seongjeong.Study_15주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 백준 11559 puyo puyo
 *
 * 조건
 * 뿌요는 중력의 영향을 받아 바닥이나, 다른 뿌요가 나올 때까지 아래로 떨어짐
 * 같은 색 뿌요가 4개 이상 상하좌우로 연결 -> 한번에 없어지며 연쇄 시작
 * 뿌요가 없어지고 아래로 떨어짐
 * 다시 같은 색의 뿌요가 4개 이상 모이면 또 터짐 -> 이때마다 1연쇄씩 증가
 * 터질 수 있는 뿌요가 여러 그룹이라면 동시에 터짐 -> 1연쇄로 간주
 * 필드 : 12 * 6
 * . : 빈 공간
 * 나머지 : 뿌요
 * R, G, B, P, Y -> 뿌요 색의 종류
 * 입력으로 주어지는 필드는 뿌요가 모두 아래로 떨어진 상태
 *
 * 문제에서 구하고자 하는 것
 * 연쇄의 최대 횟수
 *
 * 문제 해결 프로세스
 * BFS
 * 1. 터뜨릴 뿌요를 찾음
 * bfs로 같은 색의 뿌요면 큐에 담으면서, 터뜨릴 좌표값도 담자(stack)
 * 만약 현재 담긴 좌표값이 4 미만이면 담겼던 숫자만큼 pop
 * 모두 탐색 끝났을 때, 4개 이상이면 해당 좌표를 .으로 만들며 터뜨림
 * 이때 1연쇄
 * 1연쇄가 끝나고 맵을 가꾸자
 * 제일 끝행부터 위로 올라갔을 때, .을 발견하면 그 위의 .이 아닌 것을 발견해 swap
 * 아래로 당김
 * 반복
 *
 * 고려한 시간 복잡도
 * 12 * 6  * 4 * 12 * 6 * 12 * 6 = 1,492,992
 *
 * */

public class BJ11559 { // 메모리 : 11456kb, 시간 : 80ms
    static int n = 12;
    static int m = 6;
    static char[][] map;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static boolean[][] visited;
    static Deque<int[]> stack; // 터뜨릴 뿌요들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        map = new char[n][m];
        stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        while(true) {
            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') continue;
                    if (visited[i][j]) continue;

                    bfs(i, j, map[i][j]);
                }
            }
            if (stack.isEmpty()) break;
            count++;
            bomb();
            setting();

        }

        System.out.println(count);
    }

    private static void bfs(int r, int c, char color) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        stack.push(new int[]{r, c});
        count++;

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == '.') continue;
                if(map[nr][nc] != color) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
                stack.push(new int[]{nr, nc});
                count++;
            }
        }

        if(stack.isEmpty()) return;
        if(count >= 4) return;

        while(count > 0) {
            stack.pop();
            count--;
        }
    }

    private static void bomb() {
        while(!stack.isEmpty()) {
            int[] loc = stack.pop();
            map[loc[0]][loc[1]] = '.';
        }
    }

    private static void setting() {
        for(int i = 0; i < m; i++) { // 열
            for(int j = n-1; j >= 1; j--) { // 행
                if(map[j][i] != '.') continue;

                for(int k = j-1; k >= 0; k--) {
                    if(map[k][i] == '.') continue;

                    map[j][i] = map[k][i];
                    map[k][i] = '.';
                    break;
                }
            }
        }
    }
}
