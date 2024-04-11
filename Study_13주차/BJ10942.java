package Jo_Seongjeong.Study_13주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 10942 팰린드롬?
 *
 * 조건
 * 수열의 크기 N : 1 ~ 2000
 * 숫자 범위  : 1 ~ 100000
 * 질문의 개수 M : 1 ~ 1000000
 * 수열의 시작 위치 S
 * 수열의 끝 위치 E
 *
 * 문제에서 구하고자 하는 것
 * 질문이 회문이면 1, 아니면 0
 *
 * 문제 해결 프로세스
 * dp로 풀자
 * 수열 길이 * 수열 길이 이차원 배열
 * 행 = 시작 번호, 열 = 끝 번호
 *시작 = 끝 인경우 회문, 그 앞열은 다 0
 * 그 다음열 -> 전 문자와 현 문자가 같은지 비교 -> 같으면 회문, 아니면 아님
 * 그다음부터 각각의 1열씩 채우자 -> 제일 끝과 제일 앞 이 같고, 나머지 dp배열이 회문이라고 나와있다면 1 아니면 0
 *
 * 고려한 시간 복잡도
 * 1000000번 2000 범위로 해보면 무조건 터짐 -> 2,000,000,000
 * 2000 * 2000 = 4000000
 * */

public class BJ10942 { // 메모리 : 317188kb, 시간 : 1040ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(i == j ) dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }

        for(int i = 0; i < n-1; i++) {
            if(array[i] == array[i+1]) dp[i][i+1] = 1;
            else dp[i][i+1] = 0;
        }

        for(int i = n-3; i >= 0; i--) {
            for(int j = n-1; j > i+1; j--) {
                if(array[i] == array[j] && dp[i+1][j-1] == 1) dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sb.append(dp[r-1][c-1] + "\n");
        }

        System.out.println(sb);
    }

}
