package Jo_Seongjeong.Study_22주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2251 물통
 *
 * 조건
 * 물통 A, B, C : 1 ~ 200
 * A, B는 비어있고, C는 가득 차 있음
 * 다른 물통으로 쏟아 부을 수 있음 -> 한 물통이 비거나, 가득 찰 때까지
 * 손실되는 물은 없음
 *
 * 문제에서 구하고자 하는 것
 * A가 비어있을 때, C에 담겨 있는 모든 물의 양
 * 오름차순
 *
 * 문제 해결 프로세스
 * BFS
 * 1. 처음 상태에서 큐에 담고, C를 답에 넣기
 * 2. 3차원 방문배열 만들어서 해보자 -> [a][b][c]
 * 3. 방문체크 하면서, 큐에 넣어 연산 해보기
 *
 * 고려한 시간 복잡도
 * 200 * 200 * 200 = 8,000,000
 * */

public class BJ2251 { // 메모리 : 20416kb, 시간 : 64ms
    static int a;
    static int b;
    static int c;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bfs(a, b, c);

        while(!pq.isEmpty()) {
            sb.append(pq.poll() + " ");
        }
        System.out.println(sb);
    }

    private static void bfs(int a, int b, int c) throws Exception {
        boolean[][][] visited = new boolean[a+1][b+1][c+1];
        Queue<int[]> queue = new ArrayDeque<>();
        pq = new PriorityQueue<>();

        visited[0][0][c] = true;
        queue.offer(new int[]{0, 0, c});
        pq.offer(c);

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();

            if(temp[0] != 0) {
                int currentA = temp[0];
                int currentB = temp[1];
                int currentC = temp[2];

                if(currentA < b - currentB) {
                    currentB += currentA;
                    currentA = 0;
                }
                else {
                    currentA -= b - currentB;
                    currentB = b;
                }

                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[] {currentA, currentB, currentC});
                    if(currentA == 0) pq.offer(currentC);
                }

                currentA = temp[0];
                currentB = temp[1];
                currentC = temp[2];

                if(currentA < c - currentC) {
                    currentC += currentA;
                    currentA = 0;
                }
                else {
                    currentA -= c - currentC;
                    currentC = c;
                }
                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[]{currentA, currentB, currentC});
                    if (currentA == 0) pq.offer(currentC);
                }

            }

            if(temp[1] != 0) {
                int currentA = temp[0];
                int currentB = temp[1];
                int currentC = temp[2];

                if(currentB < a - currentA) {
                    currentA += currentB;
                    currentB = 0;
                }
                else {
                    currentB -= a - currentA;
                    currentA = a;
                }

                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[] {currentA, currentB, currentC});
                    if(currentA == 0) pq.offer(currentC);
                }

                currentA = temp[0];
                currentB = temp[1];
                currentC = temp[2];

                if(currentB < c - currentC) {
                    currentC += currentB;
                    currentB = 0;
                }
                else {
                    currentB -= c - currentC;
                    currentC = c;
                }

                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[] {currentA, currentB, currentC});
                    if(currentA == 0) pq.offer(currentC);
                }

            }

            if(temp[2] != 0) {
                int currentA = temp[0];
                int currentB = temp[1];
                int currentC = temp[2];

                if(currentC < b - currentB) {
                    currentB += currentC;
                    currentC = 0;
                }
                else {
                    currentC -= b - currentB;
                    currentB = b;
                }

                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[] {currentA, currentB, currentC});
                    if(currentA == 0) pq.offer(currentC);
                }

                currentA = temp[0];
                currentB = temp[1];
                currentC = temp[2];

                if(currentC < a - currentA) {
                    currentA += currentC;
                    currentC = 0;
                }
                else {
                    currentC -= a - currentA;
                    currentA = a;
                }

                if(!visited[currentA][currentB][currentC]) {
                    visited[currentA][currentB][currentC] = true;
                    queue.offer(new int[] {currentA, currentB, currentC});
                    if(currentA == 0) pq.offer(currentC);
                }
            }
        }

    }
}
