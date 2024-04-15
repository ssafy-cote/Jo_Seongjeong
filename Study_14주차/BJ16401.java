package Jo_Seongjeong.Study_14주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 16401 과자 나눠주기
 *
 * 조건
 * 조카 수 M : 1 ~ 1000000
 * 과자 수 N : 1 ~ 1000000
 * 막대 과자의 길이 : 1 ~ 1000000000
 * 조카에게 같은 길이의 막대 과자를 나눠주어야 함
 * 막대 과자는 길이와 상관없이 여러 조각으로 나눠질 수 있음
 * 막대과자를 하나로 합칠 수는 없음
 * 막대 과자의 길이는 양의 정수여야 함
 * 입력으로 주어지는 과자의 길이는 오름차순
 *
 * 문제에서 구하고자 하는 것
 * 조카 1명에게 줄 수 있는 막대 과자의 최대 길이
 * 만약 같은 길이를 나눠줄 수 없으면 0 출력
 *
 * 문제 해결 프로세스
 * 이진탐색
 * pivot을 나눠 줄 과자의 길이로 보자(1~10억 이진탐색)
 * pivot이 정해졌을 때, top부터 조카의 인원수+1까지 for 돌려보자
 * 만약 해당 인원수만큼 과자를 나누지 못 하면 탐색 범위 --
 * 반대의 경우 탐색 범위 ++
 *
 * 고려한 시간 복잡도
 * log(1000000) = 6 + 6log5
 * */

public class BJ16401 { // 메모리 : 162720kb, 시간 : 540ms
    static int n;
    static int m;
    static int[] cookie;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cookie = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            cookie[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch(1, 1000000000);

        System.out.println(max);
    }

    private static void binarySearch(int start, int end) {
        if(start > end) return;

        int mid = (start + end) / 2;

        int count = 0;
        int idx = m-1;
        while(idx >= 0) {

            count += cookie[idx]/mid;
            idx--;
        }

        if(count < n) binarySearch(start, mid-1);
        else {
            max = mid;
            binarySearch(mid+1, end);
        }
    }
}
