package Jo_Seongjeong.Study_22주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 13398 연속합2
 *
 * 조건
 * 수열 n개 정수 : 1 ~ 100000
 * 범위 : -1000 ~ 1000
 * 수는 한개 이상 선택해야 함
 * 수열에서 수를 하나 제거할 수 있음(안해도 됨)
 *
 * 문제에서 구하고자 하는 것
 * 연속된 수를 더했을 때 최대값
 *
 * 문제 해결 프로세스
 * dp[i][0] 안지우는 연속합
 * dp[i][1] 지우는 연속합
 * dp[i][0] = max(array[i], dp[i-1][0] + array[i]) -> 연속합1과 같음
 * dp[i][1] = max(dp[i-1][0], dp[i-1][1] + array[i]) -> 이전까지 다 더했을 때 하나 제거하는 과정임
 * max값 찾기
 *
 * 고려한 시간 복잡도
 * 200000
 * */

public class BJ13398 { // 메모리 : 22588kb, 시간 : 212ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][2];
        dp[0][0] = array[0];

        int max = dp[0][0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0] + array[i], array[i]);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + array[i]);

            max = Math.max(max,(Math.max(dp[i][0], dp[i][1])));
        }

        System.out.println(max);

    }
}
