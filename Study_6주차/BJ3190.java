package Jo_Seongjeong.Study_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 3190 뱀
 *
 * 조건
 * 보드의 크기 N x N : 2 ~100
 * 보드의 테두리는 벽
 * 벽 또는 자신의 몸과 부딪히면 게임 종료
 * 사과를 먹으면 길이가 늘어남
 * 초기 : 뱀은 1,1의 위치에서 오른쪽으로 간다
 * 뱀 이동 규칙
 *  몸 길이를 늘려 머리를 다음칸에 위치
 *  벽이나 자기자신의 몸과 부딪히면 게임 종료
 *  이동한 칸에 사과가 있다면, 사과가 없어짐
 *  없다면, 꼬리 칸이 사라짐(길이가 줆)
 *  사과의 개수 K : 0 ~ 100, 1행 1열에는 사과 X
 *  뱀 방향 전환 횟수 L : 1 ~ 100
 *  x : x초 뒤에 방향 전환, 1 ~ 10000
 *  C : 전환 방향 L(왼쪽) D(오른쪽)
 *
 * 문제에서 구하고자 하는 것
 * 뱀의 생존 시간
 *
 * 문제 해결 프로세스
 * int 맵을 만든다
 * queue로 좌표값을 저장함(뱀)
 * 뱀이 있는 곳 1, 없으면 0, 사과는 2로 해놓자
 * 다음 좌표값이 1이거나, 범위 밖이면 종료
 *
 *
 * 고려한 시간 복잡도
 *
 * */

public class BJ3190 { // 메모리 11912kb, 시간 : 84ms
    static int n;
    static int k;
    static int l;
    static int time;
    static int d;
    static int[][] map;
    static int[][] delta = new int[][]{{0, 1}, {1, 0},{0, -1}, {-1, 0}};
    static Queue<Integer> timer;
    static Queue<Character> dir;
    static Queue<int[]> snake;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        map[1][1] = 1;
        timer = new ArrayDeque<>();
        dir = new ArrayDeque<>();
        time = 0;
        d = 0;

        k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }

        l = Integer.parseInt(br.readLine());


        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            timer.offer(t);
            dir.offer(ch);
        }

        play(1, 1);

        System.out.println(time);
    }

    private static void play(int r, int c) {
        snake = new ArrayDeque<>();
        snake.offer(new int[]{r, c});

        while(true) {
            time++;
            r += delta[d][0];
            c += delta[d][1];

            if (r < 1 || r >= n + 1 || c < 1 || c >= n + 1) break;
            if(map[r][c] == 1) break;

            if(map[r][c] == 0) {
                int[] loc = snake.poll();
                map[loc[0]][loc[1]] = 0;
            }

            map[r][c] = 1;
            snake.offer(new int[]{r, c});

            if(timer.isEmpty()) continue;

            if(timer.peek() == time) {
                timer.poll();
                char ch = dir.poll();

                if(ch == 'L') {
                    d--;
                    if(d == -1) d = 3;
                }
                else {
                    d++;
                    if(d == 4) d = 0;
                }
            }
        }
    }
}
