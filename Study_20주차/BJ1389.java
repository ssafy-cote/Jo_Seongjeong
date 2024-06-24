package Jo_Seongjeong.Study_20주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1389 케빈 베이컨의 6단계 법칙
 *
 * 조건
 * 뜻 : 지구에 있는 모든 사람들은 최대 6단계 이내에서 서로 아는 사람으로 연결
 * -> 임의의 두 사람이 최소 몇 단계 만에 이어질 수 있는지 계산
 * 케빈 베이컨 : 나오는 단계의 총 합이 가장 적은 사람
 * 케빈 베이컨 수 : 모든 사람과 케빈 베이컨 게임을 했을 때 나오는 단계의 합
 *
 * 유저의 수 N : 2 ~ 100
 * 친구 관계의 수 M : 1 ~ 5000
 * 양방향 그래프, A와 B가 같은 경우는 없음
 * 관계는 중복되어 들어올 수도 있음
 * 친구가 한 명도 없는 사람은 없음
 * 모든 사람은 친구 관계로 연결되어져 있음
 * 가중치 없는 양방향 그래프임!
 *
 * 문제에서 구하고자 하는 것
 * 케빈 베이컨 수가 가장 작은 사람
 * 여러 명일 경우에는 번호가 가장 작은 사람
 *
 * 문제 해결 프로세스
 * 레벨별 bfs를 하면서, 시작 기준, 레벨이 가장 낮은 사람으로 하면 될듯
 * 플러드필처럼!
 * 조건에 따라 큐에서 빠져 나오면 무조건 그것이 케빈 베이컨 수가 됨
 *
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 6 * 100 = 6000000
 *
 * */

public class BJ1389 { // 메모리 : 12428kb, 시간 : 116ms
    static int n;
    static int m;
    static int[][] adjMatrix;
    static int min = Integer.MAX_VALUE;
    static int node;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjMatrix = new int[n+1][n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        for(int i = 1; i < n+1; i++) {
            bfs(i);
        }

        System.out.println(node);
    }

    private static void bfs(int start) {
        int level = 1;
        int num = 0;
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int current = queue.poll();

                for(int j = 1; j < n+1; j++) {
                    if(visited[j]) continue;
                    if(adjMatrix[current][j] == 0) continue;
                    visited[j] = true;
                    queue.offer(j);
                    num += level;
                }

            }
            level++;
        }
//        System.out.println(start + "  " + num);

        if(num < min) {
            min = num;
            node = start;
        }

    }
}
