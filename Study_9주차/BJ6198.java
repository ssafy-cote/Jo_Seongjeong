package Jo_Seongjeong.Study_9주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 백준 6198 옥상 정원 꾸미기
 *
 * 조건
 * 빌딩 개수 N : 1 ~ 80000
 * 빌딩 높이 h : 1 ~ 1000000000(10억 연산 -> long으로)
 * 빌딩 옥상은 오른쪽으로만 볼 수 있음
 * 본인보다 높거나 같은 빌딩이 있으면 그 다음부턴 아예 못 봄
 *
 * 문제에서 구하고자 하는 것
 * 각 건물에서 볼 수 있는 빌딩 옥상의 총 합
 *
 * 문제 해결 프로세스
 * 배별을 처음부터 순회하자
 * stack을 만들어 인덱스 번호와 높이를 배열로 저장하자
 * 현재 스택 상단의 높이가 다음 건물보다 낮거나 같으면 pop 그 후 cnt++
 * -> pop을 함과 동시에 새로운 배열의 해당 인덱스 번호에 cnt값 넣기
 * 모든 인덱스를 다 돌면 stack 빌 때까지 pop 그후 cnt해서 값 넣기
 *
 * 고려한 시간 복잡도
 * 80000 + 80000 + 80000 = 240000
 * */

public class BJ6198 { // 메모리 : 23520kb, 시간 : 216ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        long[] look = new long[n];
        Deque<int[]> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        stack.push(new int[] {0, array[0]});

        int i = 1;
        while(i < n || !stack.isEmpty()) {
            if(i < n) {
                while(!stack.isEmpty() &&stack.peek()[1] <= array[i]) {
                    int[] temp = stack.poll();
                    look[temp[0]] = i - temp[0] - 1;
                }
                stack.push(new int[] {i, array[i]});

            }

            if(i == n) {
                while(!stack.isEmpty()) {
                    int[] temp = stack.poll();
                    look[temp[0]] = i - temp[0] - 1;
                }
            }
            i++;
        }


        long result = 0;
        for(long idx : look) {
            result += idx;
        }

        System.out.println(result);

    }
}
