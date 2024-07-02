package Jo_Seongjeong.Study_9주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 9084 동전
 *
 * 조건
 * 동전 좋류의 개수 N :1 ~ 20
 * 동전 금액 : 1 ~ 1000
 * 목표 금액 M : 1 ~ 10000
 * 해당 동전들로 목표 금액을 만드는 방법은 여러가지
 * testCase 개수 t : 1 ~ 10
 * 방법의 수 최대 : 2^31 - 1 = 약 20억 가지치기로 도전..!
 *
 * 문제에서 구하고자 하는 것
 * 주어진 동전 금액을 활용한 목표 금액이 되는 경우의 수
 *
 * 문제 해결 프로세스
 * m+1 * n 배열 생성
 * m : 목표 금액으로 자라는 행
 * n : coin[i]
 * m : 1부터 m+1까지
 * n : n-1부터 0까지
 * 원소 채우기
 * coin[j] - m < 0 --> 0
 * coin[j] - m = 0 --> 1
 * coin[j] - m > 0 --> dp[i][j-1] + dp[i - coin[j]][j]
 * 결국 coin[m][0] -> 답!
 *
 * 고려한 시간 복잡도
 * 10000 * 20 * 10 = 2,000,000
 * */

public class BJ9084 { // 메모리 : 13144kb, 시간 : 84ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            int[][] dp = new int[m+1][n];

            for(int i = 1; i < m+1; i++) {
                for(int j = n-1; j >= 0; j--) {
                    if (i - coin[j] < 0) continue;
                    else if (i - coin[j] == 0) dp[i][j] = 1;
                    else {
                        if(j == n-1) dp[i][j] = dp[i-coin[j]][j];
                        else dp[i][j] = dp[i][j + 1] + dp[i - coin[j]][j];
                    }
                }
            }

            sb.append(dp[m][0]).append("\n");

        }
        System.out.println(sb);
    }

}
