package Jo_Seongjeong.Study_33주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 5014 스타트링크
 *
 * 조건
 * 건물 높이 F : 1 ~ 1000000
 * 현재 위치 S : 1 ~ 1000000
 * 목적지 G : 1 ~ 1000000
 * 위층 버튼 U : 위로 이동하는 층 수
 * 아래 버튼 D : 아래로 이동하는 층 수
 *
 * 문제에서 구하고자 하는 것
 * S에서 G층으로 가기 위해 눌러야 하는 버튼의 수
 * 갈 수 없으면 use the stairs 출력
 *
 * 문제 해결 프로세스
 * level 별 BFS
 * 1차원 배열에 대한 BFS로 돌리자
 *
 * 고려한 시간 복잡도
 * 1000000 * 2
 * */

public class BJ5014 {
    static int f;
    static int s;
    static int g;
    static int u;
    static int d;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws Exception { // 메모리 : 29132kb, 시간 : 148ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        visited = new boolean[f+1];

        if(bfs()) System.out.println(count);
        else System.out.println("use the stairs");

    }

    private static boolean bfs() {
        int[] delta = new int[]{1, -1};
        Queue<Integer> queue = new ArrayDeque<>();
        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int cur = queue.poll();

                if(cur == g)  {
                    return true;
                }

                for(int j = 0; j < 2; j++) {
                    int next;

                    if(j == 0) next = cur + delta[j] * u;
                    else next = cur + delta[j] * d;
                    if(next < 1 || next > f) continue;
                    if(visited[next]) continue;

                    visited[next] = true;
                    queue.add(next);
                }
            }

            count++;
        }

        return false;
    }
}