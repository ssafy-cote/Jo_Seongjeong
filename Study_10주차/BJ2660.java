package Jo_Seongjeong.Study_10주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2660 회장뽑기
 *
 * 조건
 * 회원수 : 1 ~ 49
 * 회원번호 : 1 ~ 49, 마지막 줄은 -1 -1로 끝
 * 두 개의 회원번호(입력) : 두 회원은 친구사이
 * 점수
 * 1점 : 어떤 회원이 다른 모든 회원과 친구 -> 그 회원은 모든 사람과 간선 1개로 연결되어 있음
 * 2점 : 다른 모든 회원이 친구이거나 친구의 친구 -> 그 회원 기준 나머지 회원으로 가는 간선이 최대 2개임
 * 3점 : 그 회원 기준 나머지 회원으로 가는 간선이 3임
 * 회장 후보 : 점수가 가장 작은 사람
 *
 * 문제에서 구하고자 하는 것
 * 회장 후보의 점수와 후보의 수, 후보 번호의 오름차순으로 모두 출력
 *
 * 문제 해결 프로세스
 * 인접행렬 생성 후 bfs 진행
 * 모든 정점이 탐색이면 bfs 종료, 횟수가 점수!
 * 각 회원별로 다 실행
 * 고려한 시간 복잡도
 * 50 * 50 * 50 = 125,000 1초 충분
 * */

public class BJ2660 { // 메모리 11648kb, 시간 : 80ms
    static int n;
    static int[][] adjMatrix;
    static int[] scores;
    static int min = Integer.MAX_VALUE;
    static int minCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        adjMatrix = new int[n+1][n+1];
        scores = new int[n+1];

        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        for(int i = 1; i < n+1; i++) {
            int score = bfs(i);
            scores[i] = score;
            if(min > score) {
                minCnt = 1;
                min = score;
            }
            else if(min == score) minCnt++;
        }
        sb.append(min).append(" ").append(minCnt).append("\n");

        for(int i = 1; i < n+1; i++) {
            if(min == scores[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static int bfs(int start) {
        int score = 0;
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int current = queue.poll();
                for(int j = 1; j < n+1; j++) {
                    if(visited[j]) continue;
                    if(adjMatrix[current][j] == 0) continue;

                    visited[j] = true;
                    queue.offer(j);
                }
            }
            score++;
        }
        return score-1;
    }
}
