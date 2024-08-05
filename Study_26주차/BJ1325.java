package Jo_Seongjeong.Study_26주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1325 효율적인 해킹
 *
 * 조건
 * 컴퓨터 개수 n :1 ~ 10000
 * 관계 수 m : 1 ~ 100000
 * 신뢰 관계 : A-B -> B를 해킹하면 A도 해킹할 수 있다
 *
 * 문제에서 구하고자 하는 것
 * 하나의 컴퓨터를 해킹했을 때 가장 많이 연쇄반응이 일어나는 컴퓨터 수
 * 여러개의 경우 오름차순
 *
 * 문제 해결 프로세스
 * BFS -> adjList로 풀어보자
 * 단방향, 무가중치 그래프
 *
 * 고려한 시간 복잡도
 * 10000 * (10000 + 100000) = 11억..? -> 11초면 가능할지도
 *
 * ----- 문제로 인해 알게 된 것 -----
 * 1. boolean visited 보다 int visited의 처리 속도가 더 빠르다
 * 2. 함수호출을 하는 것보다 main문에 작성하는 것이 처리 속도가 더 빠르다
 * 3. 최대값에 대해 답을 도출할 때, 배열의 길이가 길다면, 큐 연산이 더 빠르다
 * */

public class BJ1325 { // 메모리 : 308096kb, 시간 : 9484ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int max = 0;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new List[n+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
        }

        for(int i = 1; i < n+1; i++) {
            int num = 0;
            int[] visited = new int[n+1];
            Queue<Integer> queue = new ArrayDeque<>();

            visited[i] = 1;
            queue.add(i);

            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for(int j : adjList[cur]) {
                    if(visited[j] == 1) continue;
                    visited[j] = 1;
                    queue.add(j);
                    num++;
                }
            }

            if(max == num) pq.offer(i);
            else if(max < num) {
                max = num;
                pq.clear();
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            sb.append(pq.poll() + " ");
        }

        System.out.println(sb);
    }
}
