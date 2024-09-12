package Jo_Seongjeong.Study_32주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 18428 감시 피하기
 *
 * 조건
 * 복도 크기 n *n : 9 ~ 36
 * 한 칸 : 1 * 1
 * 선생님 T : 1 ~5
 * 학생 S : 1 ~ 30
 * 장애물 O : 3
 * 빈 칸 X :3 ~
 * 선생님 감시 방향 : 상하좌우
 * 복도에 장애물이 있는 경우, 장애물 뒤 학생은 볼 수 없음
 * 장애물로 막히기 전까지의 학생들은 모두 볼 수 있음 -> 장애물이 아니면 뒤에 있어도 가능
 * 장애물 개수 : 3
 *
 * 문제에서 구하고자 하는 것
 * 장애물을 3개 설치했을 때 모든 학생이 감시로부터 피할 수 있는지 여부
 * 가능 : YES
 * 불가능 : NO
 *
 * 문제 해결 프로세스
 * 완탐
 * DFS로 돌려보자 (백트레킹)
 * 모든 X에 장애물을 설치해보면서 가능한지 확인해도 될듯!
 * 고려한 시간 복잡도
 * 36 * 6 * 4 = 864
 * */

public class BJ18428 { // 메모리 : 12112kb, 시간 : 76ms
    static int n;
    static char[][] map;
    static int[][] delta = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        dfs(0, 0, 0);

        System.out.println("NO");

    }

    private static void dfs(int r, int c, int count) {

        if(count == 3) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] != 'T') continue;

                    A : for(int d = 0; d < 4; d++) {
                        for(int k = 1; k < n; k++) {
                            int nr = i + delta[d][0] * k;
                            int nc = j + delta[d][1] * k;

                            if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                            if(map[nr][nc] == 'O') continue A;

                            if(map[nr][nc] == 'S') return;
                        }
                    }
                }
            }

            System.out.println("YES");
            System.exit(0);
        }

        if(c == n) {
            r = r+1;
            c = 0;
        }

        for(int i = r; i < n; i++) {
            for (int j = (i == r) ? c : 0; j < n; j++) {
                if (map[i][j] != 'X') continue;

                map[i][j] = 'O';
                dfs(i, j + 1, count + 1);
                map[i][j] = 'X';
            }
        }
    }
}
