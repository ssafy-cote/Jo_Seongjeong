package Jo_Seongjeong.Study_23주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/**
 * 백준 2583 영역 구하기
 *
 * 조건
 * 모눈종이 크기 M * N : 1 ~ 100
 * 직사각형 개수 K : 1 ~ 100
 * 분리된 영역 : 사방이 막혀있는 영역
 * 왼쪽 아래 꼭짓점, 오른쪽 위 꼭짓점 좌표 주어짐
 * -> 왼쪽 아래 꼭짓점 좌표가 0,0
 * -> 배열은 0, n -> 행은 뒤집혀있음 (x-y 순서도 다름)
 * => 입력 x 값 -> n-1값임!
 *
 * 문제에서 구하고자 하는 것
 * 분리되어 나누어지는 영역과 영역의 넓이(오름차순)
 *
 * 문제 해결 프로세스
 * 입력을 배열 형태의 행렬로 치환
 * bfs
 * 영역 개수 ++
 * 영역 넓이 구하기
 * 반복
 *
 * 고려한 시간 복잡도
 * 40000
 * */

public class BJ2583 { // 메모리 : 12148kb, 시간 : 80ms
    static int m;
    static int n;
    static int k;
    static int num;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static List<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[m][n];
        visited = new boolean[m][n];
        list = new ArrayList<>();

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // x 좌표
            int b = m - Integer.parseInt(st.nextToken()); // y 좌표
            int c = Integer.parseInt(st.nextToken());
            int d = m - Integer.parseInt(st.nextToken());

            for(int j = d; j < b; j++) {
                for(int k = a; k < c; k++) {
                    map[j][k] = true;
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j]) continue;
                if(visited[i][j]) continue;

                bfs(i, j);
            }
        }
        Collections.sort(list);

        System.out.println(num);
        for(int i : list) {
            System.out.print(i + " ");
        }
//        for(boolean[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }

    }

    private static void bfs(int row, int col) {
        int area = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        visited[row][col] = true;
        queue.add(new int[]{row, col});

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
                if(map[nr][nc]) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
                area++;
            }
        }
        num++;
        list.add(area);
    }
}
