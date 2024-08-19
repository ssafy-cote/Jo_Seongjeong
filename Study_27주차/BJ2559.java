package Jo_Seongjeong.Study_27주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2599 수열
 *
 * 조건
 * 온도 측정한 전체 날짜 수 n : 2 ~ 100000
 * 연속적인 일 k : 1 ~ 100000
 * 온도 범위 : -100 ~ 100
 *
 * 문제에서 구하고자 하는 것
 * 연속적인 k일 동안의 온도 합이 가장 큰 값
 *
 * 문제 해결 프로세스
 * dp
 * dp[0] = 온도[0]
 * dp[1] = dp[0] + 온도[1]
 * ...
 * 다 구한다
 * k-1번째부터, 연속 온도 합 의 max 구하기
 * dp2[k-1] = dp[k-1]
 * dp2[k] = dp[k] - dp[1]
 * ...
 * max 구하기
 *
 * 고려한 시간 복잡도
 * 200000
 * */

public class BJ2559 { // 메모리 : 22252kb, 시간 : 168ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] array = new int[n];

        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = array[0];

        for(int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + array[i];
        }

        int j = 0;
        int max = dp[k-1];
        for(int i = k; i < n; i++) {
            max = Math.max(max, dp[i] - dp[j]);
            j++;
        }

        System.out.println(max);
    }
}
