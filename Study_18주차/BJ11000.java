package Jo_Seongjeong.Study_18주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 11000 강의실 배정
 *
 * 조건
 * 수업 개수 N : 1 ~ 200000
 * 수업 시간이 끝난 직후에 다음 수업 시작 가능
 *
 * 문제에서 구하고자 하는 것
 * 모든 수업이 가능한 최소 강의실 수
 * 문제 해결 프로세스
 * 1. 수업시간이 빨리 끝나는 순으로 정렬 (우선 순위 큐)
 * 2. 크기가 n인 배열 생성
 * 3. 사용할 수 있는 강의실을 앞부터 채우기
 * 4. 이 때 최대 인덱스 번호가 최소값
 * 5. 종료하면 false로 바꾸기
 *
 * 고려한 시간 복잡도
 * 200000
 * */

public class BJ11000 { // 메모리 : 76272kb, 시간 : 720ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        pq.offer(array[0][1]);
        for(int i = 1; i < n; i++) {
            if(pq.peek() <= array[i][0]) pq.poll();
            pq.offer(array[i][1]);
        }
        System.out.println(pq.size());

    }
}
