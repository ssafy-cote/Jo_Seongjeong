package Jo_Seongjeong.Study_13주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 16234 인구 이동
 *
 * 조건
 * 땅 크기 N * N : 1 ~ 50
 * L, R : 1 ~ 100
 * 모든 나라는 1 * 1 크기 -> 모든 국경선은 정사각형 형태
 * 인구이동 과정
 * 1. 국경선을 공유하는 두 나라의 인구차가 L 이상, R 이하이면, 국경선을 연다
 * 2. 국경선이 모두 열렸다면 인구 이동 시작
 * 3. 국경선이 열려있어 인접한 칸을 이용해 이동 가능하면 그 나라를 하루 동안 연합
 * 4. 연합을 이루고 있는 각 칸의 인구수는 (연합 인구수) / (연합한 칸의 개수) -> 소수점은 버림
 * 5. 연합 해체 및 국경선 닫음
 * 인구 이동이 발생하는 일수가 2000번보다 작거나 같은 입력만 주어짐
 *
 * 문제에서 구하고자 하는 것
 * 인구 이동이 며칠 발생하는지
 *
 * 문제 해결 프로세스
 * bfs로 풀어보자
 * 0,0부터 탐색 시작해서 4방탐색으로 flod - fill 해보자
 * 이동 가능한 국가면 방문배열 몇번째 연합인지 정수로 check, 큐에 담기, 칸 수++, 인구수++
 * 탐색 끝나면 리스트에 갱신할 인구수 저장
 * 모두 탐색이 끝나면 해당 칸의 연합 번호에 따라 리스트의 인구수로 갱신
 * 날짜++
 * 끝날때까지 반복
 *
 * 고려한 시간 복잡도
 * 50 * 50 * 4 * 2000 = 20,000,000
 * */

public class BJ16234 { // 메모리 : 293800kb, 시간 : 508ms
    static int n;
    static int l;
    static int r;
    static int[][] map;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int[][] union;
    static int unionNum;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int day = 0;
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];


        for(int i  = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            unionNum = 1;
            union = new int[n][n];
            list = new ArrayList<>();
            list.add(0);

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(union[i][j] != 0) continue;
                    bfs(i, j);
                }
            }

            if(unionNum == n*n+1) break;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {

                    map[i][j] = list.get(union[i][j]);
                }
            }
            day++;
        }

        System.out.println(day);
    }

    private static void bfs(int sr, int sc) {
        Queue<int[]> queue = new ArrayDeque<>();

        int popul = map[sr][sc];
        int count = 1;
        union[sr][sc] = unionNum;
        queue.offer(new int[] {sr, sc});

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if(union[nr][nc] != 0) continue;
                if(Math.abs(map[loc[0]][loc[1]] - map[nr][nc]) < l || Math.abs(map[loc[0]][loc[1]] - map[nr][nc]) > r) continue;

                popul += map[nr][nc];
                count++;
                union[nr][nc] = unionNum;
                queue.offer(new int[] {nr, nc});
            }
        }

        list.add(popul/count);
        unionNum++;

    }
}
