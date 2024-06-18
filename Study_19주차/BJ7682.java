package Jo_Seongjeong.Study_19주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 7682 틱택토
 *
 * 조건
 * 게임판 : 3 * 3
 * 두 명의 사람이 번갈아겨 말을 놓음 : O, X
 * X : 첫 번째 사람이 놓는 말
 * O : 두 번째 사람이 놓는 말
 * . : 빈 칸
 * 둘 중 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는 데 성공하면 게임 종료
 * 게임판이 가득차면 게임 종료
 *
 * 문제에서 구하고자 하는 것
 * 게임판의 상태가 주어졌을 때, 게임에서 발생 가능한 최종 상태인지 판별 ( valid, invalid )
 *
 * 문제 해결 프로세스
 * 개임판을 입력받자
 * 1. 승리 조건이 2개가 있는지 확인 -> 있으면 불가
 * 2. 2개인 경우, 가운데 좌표를 포함해 X인지 확인 -> 아니면 불가
 * 3. 0개인 경우, 다 찬 것이므로, X가 5개 - O가 4개인지 확인 -> 아니면 불가 / 빈칸이 하나도 없는 경우
 * 4. 1개인 경우, X가 이겼으면 O보다 1개 더 많고, O가 이겼으면 두 돌의 수가 같은지 확인 -> 아니면 불가
 *
 * 고려한 시간 복잡도
 * 9 * 4 = 36
 *
 * */

public class BJ7682 {
    static char[][] map;
    static int[][] delta = new int[][] {{1, 0}, {0, 1}, {1, 1}, {1, -1}}; // 가로, 세로, 우하, 좌하
    static int[][] isStart = new int[][] {{-1, 0}, {0, -1}, {-1, -1}, {-1, 1}}; //가로, 세로, 좌상, 우상
    static int xWin;
    static int oWin;
    public static void main(String[] args) throws Exception { // 메모리 : 11512kb, 시간 : 72ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();

            if(str.equals("end")) break;

            xWin = 0;
            oWin = 0;

            map = new char[3][3];
            int xCount = 0;
            int oCount = 0;
            int blank = 0;

            int idx = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    map[i][j] = str.charAt(idx++);

                    if(map[i][j] == 'X') xCount++;
                    else if(map[i][j] == 'O') oCount++;
                    else blank++;
                }
            }

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(map[i][j] == '.') continue;

                    startCheck(i, j);
                }
            }

            if(blank == 0) { // 판이 꽉찬 경우
                if (xCount == 5 && oCount == 4) { // X와 O의 개수는 고정
                    if(oWin == 0) { // O 빙고가 없을 때
                        if(xWin == 0) sb.append("valid"); // 둘 다 빙고 없으면 가능
                        else sb.append("valid"); // X가 1개 이상 빙고면 가능
                    }
                    else sb.append("invalid"); // O 빙고가 생길 수가 없음
                }
                else sb.append("invalid"); // 개수가 틀린 경우
            }

            else { // 판이 꽉 차지 않은 경우, 이제 2빙고는 나올 수 없음
                if(xWin == 1) { // X가 이긴 경우
                    if(oWin == 0) { // O가 진 경우
                        if(xCount == oCount+1) sb.append("valid"); // X가 선이므로 1개 더 많아야 함
                        else sb.append("invalid");
                    }
                    else sb.append("invalid"); // 둘 다 이길 수 없음
                }
                else if(oWin == 1) { // O가 이긴 경우
                    if(xWin == 0) { // X가 진 경우
                        if(xCount == oCount) sb.append("valid"); // X가 선이므로 개수 같아야 함
                        else sb.append("invalid");
                    }
                    else sb.append("invalid"); // 둘 다 이길 수 없음
                }
                else sb.append("invalid"); // 아직 누구도 이기지 않았는데 빈 칸이 있으면 안됨
            }

            sb.append("\n");
        }


        System.out.println(sb);
    }

    private static void startCheck(int r, int c) {
        for(int d = 0; d < 4; d++) {
            int sr = r + isStart[d][0];
            int sc = c + isStart[d][1];

            if (!(sr < 0 || sr >= 3 || sc < 0 || sc >= 3)) continue;

            dfs(r, c, d, 1);
        }
    }

    private static void dfs(int r, int c, int dir, int depth) {
        char ch = map[r][c];
        int nr = r + delta[dir][0];
        int nc = c + delta[dir][1];

        if(nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {
            if(ch == map[nr][nc]) dfs(nr, nc, dir, depth+1);
        }

        if(depth == 3) {
            if(ch == 'X') xWin++;
            else oWin++;
        }
    }
}
