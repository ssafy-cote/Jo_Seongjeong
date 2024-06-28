package Jo_Seongjeong.Study_21주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1026 보물
 *
 * 조건
 * 정수 배열 길이 N : 1 ~ 50
 * 두 배열 A, B
 * 각 원소 : 0 ~ 100
 * S = A[0] * B[0] + ... + A[n-1]*B[n-1]
 * S의 값을 가장 작게 만들기 위해 A 수 재배열 하기
 * B는 건들 수 없음
 *
 * 문제에서 구하고자 하는 것
 * S의 최소값
 *
 * 문제 해결 프로세스
 * 그리디
 * 어차피 0과 양의 정수만 나오기 때문에, B의 가장 큰값 * A의 가장 작은 값을 곱하면서 더하면 최소값이 나옴
 * 반대도 가능
 * A 오름차순 정렬
 * B 내림차순 정렬
 * S 구하기
 *
 * 고려한 시간 복잡도
 * 50!(순열은 불가)
 * 50log50 * 2 + 150
 * */

public class BJ1026 { // 메모리 : 18396kb, 시간 : 228ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        Integer[] B = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B, (a, b) -> b - a);

        int min = 0;
        for(int i = 0; i < n; i++) {
            min += A[i] * B[i];
        }
        System.out.println(min);
    }
}
