package Jo_Seongjeong.Study_31주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 7662 이중 우선순위 큐
 *
 * 조건
 * 이중 우선순위 큐 : 우선순위큐와 같은 구조로 데이터 삽입 삭제
 * 데이터 삭제 연산 : 우선순위가 가장 높은 / 낮은 데이터 중 하나 삭제
 * 데이터 연산
 *  데이터 삽입
 *  우선순위 가장 높은 것 삭제
 *  우손순위 가장 낮은 것 삭제
 * 큐 q :
 * 우선순위 : 각 정수의 값 자체 (오름차순)
 * 범위 : -21억 ~ 21억 (int 최대 범위) *주의
 * 연산 개수 k : 0 ~ 1000000
 * 삽입 연산 I, 동일 정수 삽입 가능
 * 삭제연산 D
 *  1 : 최대값 삭제 (우선순위 낮음)
 *  -1 : 최소값 삭제 (우선순위 높음)
 *  삭제 후보가 2개 이상인 경우, 하나만 삭제 됨
 *  빈 큐인 경우, 연산 무시
 *
 * 문제에서 구하고자 하는 것
 * 연산 처리 후 최대값과 최소값, 빈 경우 EMPTY
 *
 * 문제 해결 프로세스
 * 큐 두개 만들어 해결 해보자
 * 그냥 큐 두개, 왔다갔다하면 while문으로 인해 시간초과
 * 최대힙 최소힙!!! -> 시간 줄이기
 *  완전 이진트리로 스위치하기
 *
 * 고려한 시간 복잡도
 * 1000000 * 자동 정렬 시간 정도
 * */

public class BJ7662 { // 메모리 : 453820kb, 시간 : 3216ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minPq = new PriorityQueue<>();
            // b-a는 연산이라 overflow
            PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> b.compareTo(a));
            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char cal = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());
                if (cal == 'I') {
                    minPq.add(n);
                    maxPq.add(n);
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else {
                    if (n == -1) removeN(minPq, map);
                    else removeN(maxPq, map);
                }
            }

            Integer min = removeN(minPq, map);
            Integer max = removeN(maxPq, map);

            if(min == null && max == null) sb.append("EMPTY\n");
            else if(min == null) sb.append(max + " " + max + "\n");
            else if(max == null) sb.append(min + " " + min + "\n");
            else sb.append(max + " " + min + "\n");
        }

        System.out.println(sb);
    }

    private static Integer removeN(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while(!pq.isEmpty()) {
            int cur = pq.poll();

            if(map.get(cur) == 0) continue;

            map.put(cur, map.get(cur) - 1);

            return cur;
        }

        return null;
    }
}
