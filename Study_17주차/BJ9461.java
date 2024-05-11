package Jo_Seongjeong.Study_17주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 9461 파도반 수열
 *
 * 조건
 * 나선으로 정삼각형을 추가함
 * 가장 긴 변의 길이의 정삼각형을 추가함
 * 파도반 수열 P(n) : 정삼각형의 변의 길이
 * n : 1 ~ 100
 *
 * 문제에서 구하고자 하는 것
 * p(n)
 *
 * 문제 해결 프로세스
 * p(1) = 1
 * p(2) = 1
 * p(3) = 1
 * p(4) = 2 = p(1) + p(2)
 * p(5) = 2 = p(2) + p(3)
 * p(n) = p(n-3) + p(n-2)
 *
 * 고려한 시간 복잡도
 * 100
 * */

public class BJ9461 { // 메모리 : 11484kb, 시간 : 76ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            long[] dp = new long[n+1];

            for(int i = 1; i < n+1; i++) {
                if(i < 4) dp[i] = 1;
                else dp[i] = dp[i-3] + dp[i-2];
            }

            sb.append(dp[n] + "\n");
        }
        System.out.println(sb);
    }
}
