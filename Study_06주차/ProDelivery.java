package Jo_Seongjeong.Study_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 프로그래머스 배달
 *
 * 조건
 * 마을 개수(정점) N : 1 ~ 50
 * 도로 정보의 개수(간선) M : 1 ~ 2000
 * 양방향 통행(무향 그래프)
 * 입력 방식 : 정점 a 정점 b 가중치 c
 * c : 1 ~ 10000
 *
 * 문제에서 구하고자 하는 것
 * 음식 주문을 받을 수 있는 마을의 개수 (1번 마을 기준 <- 시작 정점)
 *
 * 문제 해결 프로세스
 * 인접 행렬 생성
 * 1번 탐색으로 시작해 dfs, 가중치가 k를 넘지 않는 경우만
 *
 * 고려한 시간 복잡도
 * 50 * 50 = 2500
 *
 * */

public class ProDelivery {
    static List<int[]>[] adjList;
    static boolean[] isChecked;
    static boolean[] visited;
    static int count = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] road = new int[][]{{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};

        System.out.println(solution(n, road, k));

    }

    private static int solution(int N, int[][] road, int K) {
        adjList = new List[N+1];
        for(int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        isChecked = new boolean[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            adjList[a].add(new int[]{b, c});
            adjList[b].add(new int[]{a, c});
        }
        isChecked[1] = true;
        visited[1] = true;

        dfs(1, K);

        int answer = count;
        return answer;
    }

    private static void dfs(int current, int K) {
        if(K == 0) return;

        for(int[] i : adjList[current]) {
            if(i[1] > K) continue;
            if(visited[i[0]]) continue;

            if(!isChecked[i[0]]) count++;
            isChecked[i[0]] = true;
            visited[i[0]] = true;
            dfs(i[0], K - i[1]);
            visited[i[0]] = false;
        }
    }
}
