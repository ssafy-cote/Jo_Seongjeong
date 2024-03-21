package Jo_Seongjeong.Study_10주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 9095 1, 2, 3 더하기
 *
 * 조건
 * 정수 n : 1 ~ 10
 *
 * 문제에서 구하고자 하는 것
 * n을 1, 2, 3을 활용한 합으로 나타내는 방법의 수 구하기
 *
 * 문제 해결 프로세스
 * dp로 풀어보자
 * dp배열은 방법의 수
 * dp[1] = 1
 * dp[2] = 2
 * dp[3] = 4
 * ----
 * dp[4] = 7
 * dp[n] = dp[n-3] + dp[n-2] + dp[n-1]
 *
 * 고려한 시간 복잡도
 * 10
 * */

public class BJ9095 { // 메모리 : 11480kb, 시간 : 80ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n + 1];
            dp[1] = 1;

            for (int i = 2; i < n + 1; i++) {
                if (i == 2) dp[i] = 2;
                else if (i == 3) dp[i] = 4;
                else dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }
        sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
    }
}
