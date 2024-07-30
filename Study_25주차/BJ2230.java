package Jo_Seongjeong.Study_25주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2230 수 고르기
 *
 * 조건
 * 배열 크기 n : 1 ~ 100000
 * 원소 크기 a[n] : 1 ~ 1000000000 -> int
 * 두 수의 차 m : 1 ~ 2000000000 -> int
 *
 * 문제에서 구하고자 하는 것
 * 수열에서 두 수를 골랐을 때, 그 차이가 M 이상이면서 제일 작은 수
 *
 * 문제 해결 프로세스
 * 1. 정렬
 * 2. 투포인터로 이동시키기
 * 2.1. 시작점 동일(start, end)
 * 2.2. 차가 m 미만 -> end+1
 * 2.3. 차가 m 초과 -> start+1
 * 2.4. 차가 m -> 종료
 * 고려한 시간 복잡도
 * 100000log100000 = 3000000 (정렬) + 100000 * 2 = 3200000
 * */

public class BJ2230 { // 메모리 : 27464kb, 시간 : 304ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());;

        int[] a = new int[n];

        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(a);

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while(end < n) {
            if(a[end] - a[start] < m) end++;
            else if(a[end] - a[start] > m) {
                min = Math.min(min, a[end] - a[start]);
                start++;
            }
            else {
                min = a[end] - a[start];
                break;
            }
        }
        System.out.println(min);

    }
}
