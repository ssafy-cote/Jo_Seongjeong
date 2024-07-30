package Jo_Seongjeong.Study_25주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1138 한 줄로 서기
 *
 * 조건
 * 사람 인원 수 n : 1 ~ 10
 * 자신보다 큰 사람이 왼쪽에 몇명 있었는지만 기억함
 *
 * 문제에서 구하고자 하는 것
 * 줄을 선 순서
 *
 * 문제 해결 프로세스
 * stack으로 풀기
 * 1. 거꾸로 시작
 * 2. stack.size() == array[i] -> stack.push(i)
 * 3. stack.pop후, list에 담기
 * 4. stack.isEmpty가 되면 stack.push(i)
 * 5. list에 담겨있는 값 거꾸로 push
 * 6. 다 끝나면 pop + 정답 배열에 값 넣기
 * 7. 출력
 *
 * 고려한 시간 복잡도
 * 10 * 10 = 100
 *
 * */

public class BJ1138 { // 메모리 : 11640kb, 시간 : 80ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(n);

        for(int i = n-2; i >= 0; i--) {
            List<Integer> list = new ArrayList<>();
            while(!stack.isEmpty()) {
                if(stack.size() == array[i])  {
                    stack.push(i+1);
                    break;
                }
                list.add(stack.pop());

            }

            if(stack.isEmpty()) stack.push(i+1);

            for(int j = list.size()-1; j >= 0; j--) {
                stack.push(list.get(j));
            }
        }

        int[] result = new int[n];
        for(int i = n-1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        for(int i: result) sb.append(i + " ");

        System.out.println(sb);
    }
}
