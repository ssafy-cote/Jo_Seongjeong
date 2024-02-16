package Jo_Seongjeong.Study_4주차;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 프로그래머스 게임 맵 최단거리
 * 
 * 조건
 * 5 * 5 행렬
 * 1 : 이동할 수 있는 칸
 * 0 : 이동할 수 없는 칸
 * 칸을 셀 때, 시작 위치와 도착 위치 포함
 *
 * 문제에서 구하고자 하는 것
 * 1,1에서 출발하여 n,m의 위치로 도착할 때까지 지나야 하는 최소 칸 수 구하기
 *
 * 문제 해결 프로세스
 * 1,1 위치부터 n,m까지가는 경로를 탐색한다
 * 거리 변수는 1로 초기화 한다
 * bfs로 queue를 이용해 탐색한다
 * queue에 담겨있는 정보는 map의 행, 열, 간선(경로)이다
 * 탐색한 칸 은 방문 처리한다 또한 해당 행, 열, 간선+1을 저장한다
 * 도착지를 발견한다면 간선 수를 return하여 답을 구한다
 * 혹시 뒤에 방문된 칸을 가더라도 최소 거리는 아니므로 고려하지 않아도 된다
 * 만약 도착지를 갈 수 없다면 -1을 리턴한다
 *
 * 고려한 시간 복잡도
 * 5 * 5 * 4 = 100
 *
 */

public class ProGameMapShortestDistance {
    public static void main(String[] args) {
        int[][]maps = new int[][]{{1,0,1,1,1}, {1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};


        int answer = solution(maps);
        System.out.println(answer);
    }

    public static int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] isChecked = new boolean[n][m];
        int[][] delta = new int[][] {{0,-1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();

        int[] loc = new int[3];
        loc[0] = 0;
        loc[1] = 0;
        loc[2] = 1;

        queue.offer(loc);

        isChecked[0][0] = true;

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int r = temp[0];
            int c = temp[1];

            if(r == n-1 && c == m-1)  {
                answer = temp[2];
                return answer;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r +delta[i][0];
                int nc = c + delta[i][1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !isChecked[nr][nc] && maps[nr][nc] == 1) {
                    int[] visit = new int[3];
                    visit[0] = nr;
                    visit[1] = nc;
                    visit[2] = temp[2]+1;
                    isChecked[nr][nc] = true;
                    queue.offer(visit);
                }

            }
        }
        return -1;

    }

}

