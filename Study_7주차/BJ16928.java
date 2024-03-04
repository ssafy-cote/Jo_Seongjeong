package Jo_Seongjeong.Study_7주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16928 뱀과 사다리 게임
 *
 * 조건
 * 정육면체 주사위 1 ~ 6
 * 보드판 : 10x10, 100개의 칸(1 ~ 100까지)
 * 주사위를 굴려 나온 수만큼 이동해야 함
 * 만약 주사위를 굴린 결과가 100번 칸을 넘어간 경우 이동 불가
 * 도착한 칸이 사다리인 경우, 사다리를 타고 위로 올라감
 * 뱀이 있는 칸에 도착한 경우, 뱀을 따라 내려감
 * -> 사다리를 이용해 이동한 칸의 번호 > 원래 있던 칸의 번호
 * -> 뱀을 이용해 이동한 칸의 번호 < 원래 있던 칸의 번호
 * 사다리 수 N : 1 ~ 15
 * 뱀의 수 M : 1 ~ 15
 * 사다리의 정보 x y -> x번 칸에 도착하면 y번 칸으로 이동
 * 뱀의 정보 u v -> u번 칸에 도착하면 v번 칸으로 이동
 * 동시에 둘 다 가지고 있는 경우는 없음
 * 항상 100번 칸에 도착할 수 있는 입력만 주어짐
 * 1번과 100번에는 둘 다 없음
 *
 * 문제에서 구하고자 하는 것
 * 100번 칸에 도착하기 위한 최소 주사위 사용 횟수
 *
 * 문제 해결 프로세스
 * 1차원 배열 101에 대한 bfs 진행
 * 다음 행에 대한 bfs 진행
 * 도착하면 종료
 * visit 배열을 다차원으로 만들어 진행 해보자
 *
 * 고려한 시간 복잡도
 * 100 * 15 * 15 = 22,500 1초 가능
 * */

public class BJ16928 { // 메모리 : 11652kb, 시간 : 80ms
    static int n;
    static int m;
    static boolean[] map = new boolean[101];
    static int[][] ladder;
    static int[][] snake;
    static boolean[] ladderCheck;
    static boolean[] snakeCheck;
    static int count;
    static Queue<Integer> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ladder = new int[n][2];
        ladderCheck = new boolean[101];

        snake = new int[m][2];
        snakeCheck = new boolean[101];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                ladder[i][j] = Integer.parseInt(st.nextToken());
            }
            ladderCheck[ladder[i][0]] = true;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                snake[i][j] = Integer.parseInt(st.nextToken());
            }
            snakeCheck[snake[i][0]] = true;
        }

        bfs(1);

        System.out.println(count);
    }

    private static void bfs(int index) {
        queue = new ArrayDeque<>();
        map[index] = true;
        queue.offer(index);

        A : while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int idx = queue.poll();

                if(idx == 100)
                    break A;

                for(int d = 1; d < 7; d++) {
                    int nIdx = idx + d;

                    if(nIdx >= 101) continue;
                    if(map[nIdx]) continue;

                    map[nIdx] = true;
                    if(ladderCheck[nIdx]) {
                        for(int j = 0; j < n; j++) {
                            if(ladder[j][0] == nIdx) {
                                nIdx = ladder[j][1];
                                map[nIdx] = true;
                                break;
                            }
                        }
                    }

                    if(snakeCheck[nIdx]) {
                        for(int j = 0; j < m; j++) {
                            if(snake[j][0] == nIdx) {
                                nIdx = snake[j][1];
                                map[nIdx] = true;
                                break;
                            }
                        }
                    }
                    queue.offer(nIdx);
                }
            }
            count++;
        }
    }
}
