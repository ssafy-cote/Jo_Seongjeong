package Jo_Seongjeong.Study_19주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 9465 스티커
 *
 * 조건
 * 테스트 케이스 개수 t
 * n : 1 ~ 100000
 * 스티커 개수 2n
 * 스티커 모양 2행 n열
 * 스티커를 떼면 변을 공유하는 스티커는 모두 찢어짐
 * 스티커 점수 : 0 ~ 100
 *
 * 문제에서 구하고자 하는 것
 * 스티커 점수가 최대가 되게 스티커를 떼는 경우
 *
 * 문제 해결 프로세스
 * 그리디로 해볼까? 될 거 같은데 => 실패
 * 1. 우선순위 큐에 행, 열, 점수의 1차원 배열 형태로 넣자
 * 2. 뽑으면 해당 좌표와 상하좌우를 방문체크하자
 * 3. 다음 우선순위에서 뽑았는데 해당 좌표가 방문처리가 되어 있다면 넘김
 * 3. 다음 우선순위에서 뽑았는데 해당 좌표가 처음 방문하는 거라면 점수 합산
 * 4. 큐가 빌 때까지 반복
 *
 * DP로 해보자
 * dp 배열을 하나 만들어서 누적합 형식으로 가자.
 * dp[1][n] = max(dp[2][n-1] + map[1][n], dp[1][n-2] + map[1][n], dp[2][n-2] + map[1][n])
 * dp[2][n] = max(dp[1][n-1] + map[2][n], dp[2][n-2] + map[2][n], dp[1][n-2] + map[2][n])
 *
 * 고려한 시간 복잡도
 * 200000
 *
 * */

public class BJ9465 { // 메모리 : 124444kb, 시간 : 668ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[2][n];
            int[][] dp = new int[2][n];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];

            for(int i = 1; i < n; i++) {
                if(i == 1) {
                    dp[0][i] = dp[1][i-1] + map[0][i];
                    dp[1][i] = dp[0][i-1] + map[1][i];
                }
                else {
                    dp[0][i] = Math.max(Math.max(dp[1][i-1] + map[0][i], dp[0][i-2] + map[0][i]), dp[1][i-2] + map[0][i]);
                    dp[1][i] = Math.max(Math.max(dp[0][i-1] + map[1][i], dp[1][i-2] + map[1][i]), dp[0][i-2] + map[1][i]);
                }
            }

            sb.append(Math.max(dp[0][n-1], dp[1][n-1]) + "\n");
        }

        System.out.println(sb);
    }
}
