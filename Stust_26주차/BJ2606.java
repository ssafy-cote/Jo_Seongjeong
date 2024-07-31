package Jo_Seongjeong.Stust_26주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2606 바이러스
 *
 * 조건
 * 컴퓨터의 수 : 1 ~ 100
 * 한 컴퓨터가 바이러스에 걸리면 그 컴퓨터와 연결된 모든 컴퓨터가 감염
 *
 * 문제에서 구하고자 하는 것
 * 1번 컴퓨터에 의해 바이러스에 걸리게 되는 컴퓨터 수
 *
 * 문제 해결 프로세스
 * flood fill
 * 1. 인접 행렬 만들기 (양방향 가중치x)
 * 2. 1번부터 bfs visited체크 해서 새로 감염된 놈 있으면 수++
 *
 * 고려한 시간 복잡도
 * 100 * 100 = 10000
 * */

public class BJ2606 { // 메모리 : 11740kb, 시간 : 80ms
    static int n;
    static int m;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjMatrix = new int[n+1][n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        int num = bfs(1);

        System.out.println(num);
    }

    private static int bfs(int start) {
        int num = 0;
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i = 1; i < n+1; i++) {
                if(i == cur) continue;
                if(adjMatrix[cur][i] == 0) continue;
                if(visited[i]) continue;
                visited[i] = true;
                queue.add(i);
                num++;
            }
        }

        return num;
    }
}
