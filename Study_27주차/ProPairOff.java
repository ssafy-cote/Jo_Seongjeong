package Jo_Seongjeong.Study_27주차;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 프로그래머스 짝지어 제거하기
 *
 * 조건
 * 문자열 길이 : 1 ~ 1,000,000
 * 짝지어 제거하기
 * 1. 알파벳 소문자로 이루어진 문자열 가지고 시작
 * 2. 같은 알파벳이 붙어 있는 짝 발견함녀 제거 -> 앞뒤로 문자열 이어붙임
 * 3. 과정 반복, 문자열 모두 제거시 종료
 *
 * 문제에서 구하고자 하는 것
 * 짝지어 제거하기 성공여부, 성공: 1, 실패: 0
 *
 * 문제 해결 프로세스
 * stack
 * 1. 0번째 문자 push
 * 2. 1부터 끝까지 반복, 비어있으면 무조건 push, peek()과 현재 문자가 같으면 pop(), 다르면 push
 * 3. stack 비어있으면 1, 아니면 0
 *
 * 고려한 시간 복잡도
 * 1000000
 * */

public class ProPairOff {
    public static void main(String[] args) {
        String s = "bbaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        int answer = solution(s);

        System.out.println(answer);
    }

    public static int solution(String s) {
        int answer = -1;

        Deque<Character> stack = new ArrayDeque<>();
        stack.push(s.charAt(0));

        for(int i = 1; i < s.length(); i++) {
            if(stack.isEmpty()) stack.push(s.charAt(i));

            else if(stack.peek() == s.charAt(i)) stack.pop();

            else stack.push(s.charAt(i));
        }

        if(stack.isEmpty()) answer = 1;
        else answer = 0;

        return answer;
    }
}
