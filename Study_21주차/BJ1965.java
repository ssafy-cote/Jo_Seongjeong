package Jo_Seongjeong.Study_21주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1965 상자넣기
 *
 * 조건
 * 상자 모양 : 정육면체
 * 앞에 있는 상자의 크기 < 뒤에 있는 상자 크기  -> 앞 상자를 뒤 상자에 넣을 수 있음
 * 상자의 개수 n : 1 ~1000
 * 상자 크기 : 1 ~ 999
 *
 * 문제에서 구하고자 하는 것
 * 한 번에 넣을 수 있는 최대 상자 개수
 *
 * 문제 해결 프로세스
 * 2중 for문으로 누적 시키자
 * dp 배열 하나 만들기
 * 0번째 부터 본인까지 상자 중, 본인보다 작은 상자의 dp값 중 최대값+1해서 저장하기
 *
 * 고려한 시간 복잡도
 * 1000 * 1000 = 1000000
 * */

public class BJ1965 { // 메모리 : 11952kb, 시간 : 104ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] box = new int[n];
        int[] dp = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = 0; j < i; j++) {
                if(box[i] <= box[j]) continue;
                max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
        }

        int max = 0;
        for(int num : dp) max = Math.max(max, num);

        System.out.println(max);
    }
}
