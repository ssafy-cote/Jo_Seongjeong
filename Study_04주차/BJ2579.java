package Jo_Seongjeong.Study_4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2579 계단 오르기
 *
 * 조건
 *
 * 시간 1초, 메모리 128MB
 *계단은 한 번에 한 계단 ~ 두 계단 오를 수 있음
 *  한번 + 한번 or 두칸(한번)
 * 연속된 세 개의 계단은 밟을 수 없음
 * 마지막 계단은 밟아야 함
 * 계단 개수 -> 300 이하
 * 각 계단 점수 -> 10000 이하
 *
 * 문제에서 구하고자 하는 것
 *
 * 조건에 맞는 계단 오르기 방법 중 점수가 가장 높은 방법 찾기
 *
 * 문제 해결 프로세스
 *
 * 계단의 개수 입력 받기
 * 배열에 각 계단 점수 입력 받아 저장하기
 * dp 배열 생성하기
 * 1 ~ 3까진 최대 값 따로 저장
 * 4부터, 해당 계단 값 + dp[i-2] / 해당 계단 값 + dp[i-3] + 계단[i-1] 중 최대값 찾기
 *
 * 고려한 시간 복잡도
 * n
 *
 * */

public class BJ2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stair = new int[n+1];
        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }



        for(int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = stair[i];

            } else if (i == 2) {
                dp[i] = stair[i-1] + stair[i];

            } else if (i == 3) {
                dp[i] = Math.max(stair[i-2] + stair[i], stair[i-1] + stair[i]);
            } else {
                dp[i] = Math.max(dp[i - 2] + stair[i], dp[i - 3] + stair[i - 1] + stair[i]);
            }
        }

        System.out.println(dp[n]);
    }
}

// 메모리 : 11488kb, 시간 : 76ms