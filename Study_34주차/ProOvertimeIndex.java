package Jo_Seongjeong.Study_34주차;

import java.util.PriorityQueue;

/**
 * 프로그래머스 야근 지수
 *
 * 조건
 * 일에 대한 작업량 : works (1 ~ 20000)
 * 남은 시간 : n (1 ~ 1000000)
 * 야근 피로도
 * 각 남은 작업량의 제곱
 *
 * 문제에서 구하고자 하는 것
 * 최소 야근 피로도
 *
 * 문제 해결 프로세스
 * 우선순위 큐, 내림차순 이용해서 연산
 * 연산은 -1씩 반복
 * 연산 끝나면 제곱해 야근 피로도 구하기
 *
 * 고려한 시간 복잡도
 * 1000000
 */

public class ProOvertimeIndex {
    public static void main(String[] args) {
        long answer = solution(4, new int[]{4, 3, 3});
        System.out.println(answer);
    }

    public static long solution(int n, int[] works) {
        long answer = 0;

        int sum = 0;
        for(int i : works) {
            sum += i;
        }

        if(sum - n < 1) return answer;

        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(b, a));

        for(long i : works) pq.offer(i);

        while(n > 0) {
            long cur = pq.poll() -1;
            if(cur > 0) pq.offer(cur);
            n--;
        }

        while(!pq.isEmpty()) {
            long cur = pq.poll();

            answer += Math.pow(cur, 2);
        }
        return answer;
    }
}
