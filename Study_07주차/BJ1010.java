package Jo_Seongjeong.Study_7주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1010 다리놓기
 *
 * 조건
 * 사이트 : 다리를 놓을 수 있는 곳
 * 서쪽 사이트 개수 N : 0 ~ 30
 * 동쪽 사이트 개수 M (N <= M) : 0 ~ 30
 * 한 사이트에는 한 개의 다리만 연결 가능
 * 서쪽 사이트 개수만큼 다리를 지을 것
 * 다리는 서로 겹쳐질 수 없음
 *
 * 문제에서 구하고자 하는 것
 * 다리를 지을 수 있는 경우의 수
 *
 * 문제 해결 프로세스
 * mCn 조합의 수를 구한다
 * 
 * 고려한 시간 복잡도
 * 30C15 * T = 155,117,520 * T? 0.5초는...
 * -> dp로? : 30 * 30 = 900
 * */

public class BJ1010 { // 메모리 : 11836kb, 시간 : 88ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        long[][] combi = new long[31][31];

        for(int i = 0; i < 31; i++) {
            combi[i][0] = 1;
        }

        for(int i = 1; i < 31; i++) {
            for (int j = 1; j <= i; j++) {
                combi[i][j] = combi[i - 1][j - 1] + combi[i - 1][j];
            }
        }


        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(combi[m][n]).append("\n");
        }
        System.out.println(sb);
    }
}
