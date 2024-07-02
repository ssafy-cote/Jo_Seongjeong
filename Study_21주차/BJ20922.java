package Jo_Seongjeong.Study_21주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 20922 겹치는 건 싫어
 *
 * 조건
 * 수열 길이 N : 1 ~ 200000
 * 수 범위 : 1 ~ 100000
 * 같은 원소가 가능한 최대 개수 K : 1 ~ 100
 * 최장 연속 부분 수열 : 같은 원소가 K개 이하로 들어 있는 연속된 수열
 *
 * 문제에서 구하고자 하는 것
 * 최장 연속 부분 수열의 최대 길이
 *
 * 문제 해결 프로세스
 * 2중 반복문 -> 투포인터
 * 1. 겹치는 수가 K개를 넘어갈 때까지 내부 반복문 증가해서 길이 구하기
 * 2. 외부 반복문의 인덱스를 내부 반복문 인덱스까지 이동
 * 3. 반복해서 최대 길이 구하기
 *
 * 고려한 시간 복잡도
 * 100000 * 2
 *
 * */

public class BJ20922 { // 메모리 : 37924kb, 시간 : 280ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] array = new int[n];
        int[] num = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = 0;
        int max = 0;
        while(i < n) {
            while(j < n && num[array[j]] < k) {
                num[array[j]]++;
                j++;
            }
            max = Math.max(max, jgit a - i);
            num[array[i]]--;
            i++;
        }

        System.out.println(max);
    }
}
