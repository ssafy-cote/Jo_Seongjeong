package Jo_Seongjeong.Study_5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 13305 주유소
 *
 * 조건
 * 1km 이동 -> 1L 필요
 * 도시 개수 N : 2 ~ 100,000
 * 도시 간 거리, 기름 가격 : 1 ~ 1,000,000,000
 *  => 최대의 경우 : 10000 * (1000000000 * 1000000000) 100,000,000,000,000,000,000,000 -> int는 불가
 *
 * 문제에서 구하고자 하는 것
 * 제일 왼쪽에서 오른쪽으로 이동할 때, 최소로 소요한 기름 값
 *
 * 문제 해결 프로세스
 * 각 지역 기름값 배열 0 ~ n
 * 지역 이동 거리 1 ~ n까지 만든다 (0은 사용x)
 * 최소 주유비는 0번째 도시로 설정
 * 지역 기름값 비교
 * 만약 다음 인덱스 지역의 기름값이 큰 경우 주유비 += 이동거리 * 최소 주유비
 * 만약 다음 인덱스 지역의 기름값이 작은 경우 주유비 += 이동거리 * 다음 지역 기름값, 최소 주유비 갱신
 * 반복
 *
 * 고려한 시간 복잡도
 * n
 * */

public class BJ13305 { // 메모리 : 40624kb, 시간 : 360ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] distance = new int[n];
        int[] oil = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            oil[i] = Integer.parseInt(st.nextToken());
        }

        long min = oil[0];
        long cost = 0;
        for(int i = 1; i < n; i++) {
            cost += min * distance[i];
            if(min > oil[i])
                min = oil[i];
        }

        System.out.println(cost);
    }
}
