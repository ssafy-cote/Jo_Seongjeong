package Jo_Seongjeong.Study_31주차;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 프로그래머스 숫자 변환하기
 *
 * 조건
 * 변환할 수 x : 1 ~ 1000000
 * 목적 수 y : 1 ~ 1000000
 * 도구 수 n : 1 ~ 999999
 * 연산 종류
 * x + n
 * x * 2
 * x * 3
 *
 * 문제에서 구하고자 하는 것
 * 주어진 연산을 통해 x -> y로 변환 할 때, 최소 연산 횟수 / 만들 수 없으면 -1
 *
 * 문제 해결 프로세스
 * 재귀를 통한 완탐 -> stack over flow, 시간초과 (덧셈 연산을 미리 하는 최적화 시도했지만, 2개 테스트 케이스 실패)
 * return 조건
 * 1. 연산 횟수가 최소값을 넘어갈 때
 * 2. 이미 y를 넘어갈 때
 *
 * level별 bfs로 생각해보자
 * visited 배열을 통해 관리한다면? -> 성공!
 *
 * 고려한 시간 복잡도
 * 3000000
 */

public class ProNumberChange {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int x = 10;
        int y = 40;
        int n = 5;

        int answer = solution(x, y, n);

        System.out.println(answer);
    }

    public static int solution(int x, int y, int n) {
        int answer = 0;

        bfs(x, y, n);

        answer = (min == Integer.MAX_VALUE ? -1 : min);

        return answer;
    }

    private static void bfs(int x, int y, int n) {
        int level = 0;
        int[] visited = new int[1000001];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[x] = 1;
        queue.offer(x);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int cur = queue.poll();

                if(cur == y) {
                    min = level;
                    return;
                }

                for(int j = 0; j < 3; j++) {
                    int next;
                    if(j == 0) next = cur * 3;
                    else if(j == 1) next = cur * 2;
                    else next = cur + n;

                    if(next >= visited.length) continue;
                    if(visited[next] == 1) continue;
                    visited[next] = 1;
                    queue.offer(next);
                }
            }

            level++;
        }
    }
}
