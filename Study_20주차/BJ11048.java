package Jo_Seongjeong.Study_20주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11048 이동하기
 *
 * 조건
 * 미로 N * M : 1 ~ 1000
 * 시작위치 = 가장 윗 방 : 1, 1
 * 이동 가능 경로 : 우, 하, 우하대각
 * 방에 방문할 때마다 사탕을 가져갈 수 있음
 * 미로 밖으로 나갈 수 없음
 * 도착지점 = N, M
 * 사탕 개수 : 0 ~ 100
 * 가능한 누적 사탕값 -> 100 * 1000 * 1000 = 100000000 -> int
 *
 * 문제에서 구하고자 하는 것
 * 가져올 수 있는 사탕 개수의 최대값
 *
 * 문제 해결 프로세스
 * dp로 해볼까
 * 1. 1,1 ~ 1,n -> 경우의 수는 하나
 * 2. 1,1 ~ n,1 -> 경우의 수는 하나
 * 3. 나머지는 왼쪽, 위쪽, 왼위대각에서 가장 큰 값으로 누적시키자
 *
 * 고려한 시간 복잡도
 * 1000000
 *
 * */

public class BJ11048 { // 메모리 : 80704kb, 시간 : 424ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < m+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                if(i == 1 && j == 1) dp[i][j] = map[i][j];
                else if(i == 1) dp[i][j] = dp[i][j-1] + map[i][j];
                else if(j == 1) dp[i][j] = dp[i-1][j] + map[i][j];
                else dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1])) + map[i][j];
            }
        }

        System.out.println(dp[n][m]);
    }
}
