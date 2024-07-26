package Jo_Seongjeong.Study_25주차;

import java.util.*;

/**
 * 프로그래머스 뒤에 있는 큰 수 찾기
 *
 * 조건
 * 정수 배열 numbers : 4 ~ 1000000
 * 원소 크기 : 1 ~ 1000000
 * 뒷 큰수 : 자신보다 뒤에 있는 숫자 중, 가장 가까이 있는 수
 *
 * 문제에서 구하고자 하는 것
 * 각 원소의 뒷 큰수 (순서대로), 뒷 큰수 존재하지 않는 경우 -1
 *
 * 문제 해결 프로세스
 * stack으로 풀자
 * 1. stack이 비어있지 않고, numbers[stack.peek()] < numbers[i] 인 경우,
 answer[stack.poll()] = numbers[i] -> 반복
 * 2. i를 push (배열 인덱스)
 *
 * 고려한 시간 복잡도
 * 2000000
 */

class ProBackBigNumber {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};

        int[] answer = solution(numbers);

        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.poll()] = numbers[i];
            }

            stack.push(i);
        }

        return answer;
    }
}
