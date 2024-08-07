package Jo_Seongjeong.Study_27주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 5972 택배 배송
 *
 * 조건
 * 헛간 개수 n : 1 ~ 50000
 * 소들의 길 개수 m : 1 ~ 50000
 * 각 소의 마리 수 c : 1 ~ 1000
 * 가는 길에 만나는 모든 소들에게 여물을 줘야함
 * 헛간 간 연결된 길은 하나 이상의 길로 연결되어 있을 수 있음
 *
 * 문제에서 구하고자 하는 것
 * 헛간 1 - n으로 갈 때, 소에게 줘야할 여물의 최소 비용
 * 가는 길의 길이는 고려x
 *
 * 문제 해결 프로세스
 * 양방향, 가중치 그래프 -> 여물의 개수를 거리라고 생각하면 될 듯
 * 다익스트라
 * 1. adjList 생성
 * 2. 최소값 담을 distance배열 생성 및 초기화(Integer.MAX_VALUE)
 * 3.시작점, 가중치 0으로 다익스트라 시작
 * 4. pq 생성(우선 순위, 가중치)
 * 5. 시작점 기준, 가능한 모든 경로 탐색
 * 6. 현재 나온 가중치 + 현재까지 경로에 대한 최단경로 < 기존 최단경로이면, 갱신 / pq에 담음(해당 정점, 갱신된 최단경로)
 * 7. 반복
 * --> temp[1] + distance[cur[0]] < distance[temp[0]]
 *
 * 고려한 시간 복잡도
 * 50000log50000 = 200000+250000log5 => 대략 270000
 * */

public class BJ5972 { // 메모리 : 42488kb, 시간 : 532ms
    static int v;
    static int e;
    static List<int[]>[] adjList;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        adjList = new List[v+1];
        distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for(int i = 1; i < v+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[] {b, c});
            adjList[b].add(new int[] {a, c});

        }

        dijkstra(1, 0);

        System.out.println(distance[v]);

    }

    private static void dijkstra(int start, int weight) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        distance[start] = weight;
        pq.offer(new int[] {start, weight});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int[] temp : adjList[cur[0]]) {
                if(temp[1] + distance[cur[0]] < distance[temp[0]]) {
                    distance[temp[0]] = temp[1] + distance[cur[0]];
                    pq.offer(new int[] {temp[0], distance[temp[0]]});
                }
            }
        }
    }
}
