package Jo_Seongjeong.Study_28주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2156 포도주 시식
 *
 * 조건
 * 잔의 개수 n : 1 ~ 10000
 * 각 포도주 양 : 0 ~ 1000
 * 규칙1. 포도주 잔을 선택하면, 그 잔에 들어있는 포도주 모두 마시고 원위치
 * 규칙2. 연속으로 놓인 3잔을 모두 마실 수 없음
 *
 * 문제에서 구하고자 하는 것
 * 최대로 마실 수 있는 포도주의 양
 *
 * 문제 해결 프로세스
 * dp
 * 몇번째 안 먹었는지에 따라 다르게 구해보자
 * dp[1] = array[1]
 * dp[2] = array[1] + array[2]
 * dp[3] = max(dp[0] + array[2] + array[3], dp[1] + array[3], dp[2]) -> 안먹 먹 먹 / 먹 안먹 먹 / 먹 먹 안먹
 *
 * 고려한 시간 복잡도
 * 10000
 * */

public class BJ2156 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n+1];
        dp[1] = array[1];

        for(int i = 2; i < n+1; i++) {
            if(i == 2) dp[i] = dp[i-1] + array[i];
            else {
                dp[i] = Math.max(dp[i-3] + array[i-1] + array[i], Math.max(dp[i-2] + array[i], dp[i-1]));
            }
        }

        System.out.println(dp[n]);
    }
}
