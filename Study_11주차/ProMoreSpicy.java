package Jo_Seongjeong.Study_11주차;

import java.util.PriorityQueue;

/**프로그래머스 더 맵게
 *
 * 조건
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식 + 두번째로 맵지 않은 스코빌 지수 * 2
 * 배열 길이 : 2 ~ 1000000
 * 스코빌 지수 기준 k : 0 ~ 1000000000
 * 배열 원소 : 0 ~ 1000000
 *
 * 문제에서 구하고자 하는 것
 * 모든 음식이 스코빌 지수 k가 넘을 때 까지의 섞는 횟수, 안되면 -1 출력
 *
 * 문제 해결 프로세스
 * 우선순위 큐(오름차순)
 *
 * 고려한 시간 복잡도
 * 1000000
 *
 * */

public class ProMoreSpicy {
    public static void main(String[] args) {
        int[] scoville = new int[] {1, 2, 3, 9, 10, 12};
        int k = 7;

        int answer = solution(scoville, k);

        System.out.println(answer);
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++) {
            queue.offer(scoville[i]);
        }

        while(queue.size() > 1) {
            if(queue.peek() >= K) break;

            int first = queue.poll();
            int second = queue.poll();

            int food = first + second*2;

            queue.offer(food);
            answer++;

        }

        if(queue.size() == 1) {
            int last = queue.poll();
            if(last < K) answer = -1;
        }

        return answer;
    }
}
