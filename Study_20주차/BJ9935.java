package Jo_Seongjeong.Study_20주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 9935 문자열 폭발
 *
 * 조건
 * 폭발 문자열이 폭발하면, 해당 문자 사라지고 남은 문자열은 합쳐짐
 * 1. 문자열이 폭발 문자열을 포함하고 있는 경우
 *  모든 폭발 문자열이 폭발, 남은 문자열을 순서대로 이어 붙여 새로운 문자열 만듦
 * 2. 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수 있음
 * 3. 폭발 문자열이 문자열에 없을 때까지 반복
 * 폭발 문자열은 같은 문자를 두 개 이상 포함X
 * 문자열의 길이 : 1 ~ 1000000
 * 폭발 문자열 길이 : 1 ~ 36
 * 문자열 구성 : 알파벳 대소문자, 0 ~ 9
 *
 * 문제에서 구하고자 하는 것
 * 끝에 남은 문자열, 없는 경우 FRULA 출력
 *
 * 문제 해결 프로세스
 * 스택으로 풀자
 * 스택 size >= 폭발물 문자 일 때
 * stack의 get함수를 활용하자!!!!!!! -> Deque은 안되지만 Stack은 가능!
 * 길이만큼 문자열이 모두 같으면 다 pop하기
 *
 * 고려한 시간 복잡도
 * 36000000 -> 충분
 *
 * */

public class BJ9935 { // 메모리 : 88940kb, 시간 : 468ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String bomb = br.readLine();
        int size = bomb.length();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if(stack.size() < size) continue;

            boolean flag = false;

            for(int j = 0; j < size; j++) {
                if(stack.get(stack.size() - size + j) == bomb.charAt(j)) continue;
                flag = true;
                break;

            }

            if(flag) continue;

            for(int j = 0; j < size; j++) {
                stack.pop();
            }
        }

        // Deque이 아닌 Stack은 가능하다..
        for(Character c : stack) {
            sb.append(c);
        }

//        이건 안됨
//        while(!stack.isEmpty()) {
//            sb.insert(0,stack.pop());
//        }

        System.out.println(sb.length()==0 ? "FRULA" : sb.toString());
    }
}
