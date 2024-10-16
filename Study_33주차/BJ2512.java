package Jo_Seongjeong.Study_33주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2512 예산
 *
 * 조건
 * 국가 예산 분배 방식
 * 1. 예상요청의 합이 국가 예산을 초과하지 않으면 그대로 배정
 * 2. 아니면, 특정 상한을 걸고 상한 이하 요청은 그대로, 넘으면 상한으로 주기
 * 지방의 수 n : 3 ~ 10000
 * 예산 범위 : 1 ~ 100000
 * 총 예산 m : 3 ~ 1000000000
 *
 * 문제에서 구하고자 하는 것
 * 배정 예산의 최대값
 *
 * 문제 해결 프로세스
 * 이진탐색
 * 1. 처음 합산을 해서 넘지 않으면 그냥 최대값 주기
 * 2. 아니면 이진탐색으로 최대값 찾기
 *
 * 고려한 시간 복잡도
 * 10000log10000
 * */

public class BJ2512 { // 메모리 : 13000kb, 시간 : 100ms
    static int n;
    static int m;
    static int[] array;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        array = new int[n];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            sum += array[i];
            max = Math.max(max, array[i]);
        }

        m = Integer.parseInt(br.readLine());

        if(sum <= m) System.out.println(max);

        else {
            int right = max;
            max = 0;

            binarySearch(1, right);
            System.out.println(max);
        }
    }

    private static void binarySearch(int left, int right) {
        if(left > right) return;

        int mid = left + (right - left) / 2; // overflow 방지
        int sum = 0;

        for(int i = 0; i < n; i++) {
            if(array[i] <= mid) sum += array[i];
            else sum += mid;
        }

        if(sum == m) max = mid;
        else if(sum > m) binarySearch(left, mid-1);
        else {
            max = Math.max(max, mid);
            binarySearch(mid+1, right);
        }
    }
}
