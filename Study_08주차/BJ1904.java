package Jo_Seongjeong.Study_8주차;

import java.util.Scanner;

/**
 * 백준 1904 01타일
 *
 * 조건
 * N : 타일의 길이 (1 ~ 1000000)
 * 타일 종류 : 00, 1
 *
 * 문제에서 구하고자 하는 것
 * 길이가 N인 모든 2진 수열의 개수 % 15746
 *
 * 문제 해결 프로세스
 * dp로 풀어보자
 * f(1) = 1
 * f(2) = 2
 * f(3) = 3
 * f(4) = 5
 * 피보나치..!
 *
 * 고려한 시간 복잡도
 * 1000000(N)
 * */

public class BJ1904 { // 메모리 : 16872kb, 시간 : 128ms
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        dp[1] = 1;

        for(int i = 2; i < n+1; i++) {
            if(i==2) dp[i] = i;
            else dp[i] = (dp[i-1] + dp[i-2]) % 15746;
        }

        System.out.println(dp[n]);
    }
}
