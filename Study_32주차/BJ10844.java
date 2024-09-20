package Jo_Seongjeong.Study_32주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 10844 쉬운 계단 수
 *
 * 조건
 * 계단 수 : 인접한 모든 자리의 차이가 1, 0으로 시작하는 수는 아님
 * 수의 길이 n : 1 ~ 100
 *
 * 문제에서 구하고자 하는 것
 * 길이가 n인 계단 수의 개수 % 1000000000
 *
 * 문제 해결 프로세스
 * DP
 * 1 2 3 4 5 6 7 8 9 -> 9개
 *
 * 10 12
 * 21 23
 * 32 34
 * 43 45
 * 54 56
 * 65 67
 * 76 78
 * 87 89
 * 98
 * -> 17개
 *
 * 101 121 123
 * 210 212 232 234
 * 3, 4, 5, 6, 7 동일
 * 876 878 898
 * 987 989
 *
 * dp[2][1] -> 2개
 * dp[2][2] -> 2개 이런식으로?
 *
 * 0과 9는 각각 1개밖에 안되는데 나머지는 2개씩 증가 가능함
 * 0과 9가 나오는 경우를 봐보자
 * 0인 경우, 점화식을 0도 세워야 하나? -> 그전 1번 수와 같을듯! (마찬가지)
 * 9인 경우, 그전 8번째와 같은 수가 될듯 (계속 하나씩 밀리니까)
 * 나머지 경우,
 * 101 121 123 -> 1010 1012 1210 1212 1232 1234 => 6개
 * => 010 012 210 212 232 234이 두번째부터 다 똑같음
 * dp[i-1][j-1] + dp[i-1][j+1]
 *
 * 가정
 * 0
 * 01
 * 010 012
 * 0101 0121 0123
 *
 * 고려한 시간 복잡도
 * 100 * 10 = 1000
 * */

public class BJ10844 { // 메모리 : 11512kb, 시간 : 68ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;
        long[][] dp = new long[n+1][10];

        for(int i = 1; i < n+1; i++) {
            for(int j = 0; j < 10; j++) {
                if(i == 1) dp[i][j] = 1;
                else {
                    if(j == 0) dp[i][j] = dp[i-1][j+1];
                    else if(j == 9) dp[i][j] = dp[i-1][j-1];
                    else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
                }

                if(i == n && j != 0) answer = (answer + dp[i][j]) % 1000000000;
            }
        }

        System.out.println(answer);

    }
}
