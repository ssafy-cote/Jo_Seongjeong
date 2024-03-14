package Jo_Seongjeong.Study_9주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2805 나무 자르기
 *
 * 조건
 * 나무의 수 N : 1 ~ 1000000
 * 필요한 나무 길이 M :2000000000
 * 목재 절단기
 * 절단기 지정 높이 H : 0 ~ 1000000000 -> long
 * H보다 높은 나무만 절단, 잘린 윗부분이 가져갈 수 있는 나무
 *
 * 문제에서 구하고자 하는 것
 * 적어도 M미터의 나무를 집에 가져가기 위해 절단할 수 있는 높이의 최댓값
 *
 * 문제 해결 프로세스
 * 이진탐색으로 해보자
 * 제일 처음 기준 -> 0, 제일 긴 값
 * 중간값에서 나무가 아직 부족하면 더 낮게 잘라야 할 것
 * 중간값에서 나무가 m보다 길면 더 높게 잘라야 할것
 * 이진탐색으로 범위 찾기
 *
 * 고려한 시간 복잡도
 * 1000000 * log(2000000000) = 10,000,000 + 9,000,000log(5) -> 가능할듯
 * */

public class BJ2805 { // 메모리 : 172324kb, 시간 : 496ms
    static int n;
    static long m;
    static long[] tree;
    static long max;
    static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        tree = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            if(max < tree[i]) max = tree[i];
        }
        binarySearch(0, max);

        System.out.println(result);


    }

    static void binarySearch(long start, long end) {
        if(start > end) return;

        long mid = (start+end)/2;
        long h = 0;

        for(int i = 0; i < n; i++) {
            if(tree[i] - mid > 0) h += tree[i] - mid;
        }

        if(h < m) {
            binarySearch(start, mid-1);
        }
        else {
            if(result < mid) result = mid;

            binarySearch(mid+1, end);
        }
    }
}
