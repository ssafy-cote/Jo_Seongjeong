package Jo_Seongjeong.Study_12주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2467 용액
 *
 * 조건
 * 전체 용액 수 N : 2 ~ 100000
 * 용액의 특성 값 :  -1000000000 ~ 1000000000
 * 산성 용액 : 양수
 * 알칼리성 용액 : 음수
 * 혼합 방법 : 두 용액의 값을 더한다
 * 산성, 알칼리성끼리도 혼합 가능
 *
 * 문제에서 구하고자 하는 것
 * 혼합했을 때, 0에 가장 가까운 값이 되는 용액의 값들
 *
 * 문제 해결 프로세스
 * 이진탐색으로 해보자
 * pivot을 2개로 놓아볼까
 * pivot 1 = (n-1)/2
 * pivot 2 = (n-1)/2 + 1
 * pivot1 + pivot2의 결과에 따라 pivot 옮기기
 * pivot1 > pivot1 + pivot2 -> pivot1 이진
 * pivot2 < pivot1 + pivot2 -> pivot2 이진
 *
 * 고려한 시간 복잡도
 * 100000log100000 = 50000 + 50000log5
 * */

public class BJ2467 { // 메모리 : 31420kb, 시간 : 304ms
    static int n;
    static int[] array;
    static int a;
    static int b;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        array = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            binarySearch(i+1, n-1, i);
        }

        System.out.println(a<b? a +" " + b : b + " " + a);
    }

    static void binarySearch(int low, int high, int i) {
        if(low > high) return;

        int mid = (low+high)/2;

        int sum = array[mid] + array[i];

        if(sum == 0) {
            a = array[mid];
            b = array[i];
            return;
        }

        if(min > Math.abs(sum)) {
            a = array[mid];
            b = array[i];
            min = Math.abs(sum);
        }

        if(sum < 0) binarySearch(mid + 1, high, i);
        else binarySearch(low, mid - 1, i);
    }
}
