package Jo_Seongjeong.Study_29주차;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 프로그래머스 전력망을 둘로 나누기
 *
 * 조건
 * 송전탑 개수 n : 2 ~ 100
 * 트리형태 연결
 * 하나를 끊어 2개로 분할
 * 전선 정보 wires : 2 ~ 200
 * wires 형태 -> [v1, v2] : v1과 v2 연결
 * 하나의 트리형태가 아닌 경우 고려 x
 *
 * 문제 에서 구하고자 하는 것
 * 전선을 하나 잘랐을 때, 두개로 나뉜 그룹의 송전탑 수 차가 가장 작은 값
 *
 * 문제 해결 프로세스
 * bfs (flod fill)
 * 1. adjList처럼 만들자
 * 2. wires 인덱스 순서에 따라 0으로 만들고 bfs 한번 진행
 * 3. 원래 n개이므로, n-값 해서 차이 구하기
 * 4. 최소값 갱신 및 0이면 바로 종료
 *
 * 고려한 시간 복잡도
 * 98 * 98 약 200 * 100번 20000
 */

public class ProDivideInTwo {
    static List<Integer>[] adjList;

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        int answer = solution(n, wires);
        System.out.println(answer);

    }

    public static int solution(int n, int[][] wires) {
        int answer = -1;
        int min = Integer.MAX_VALUE;

        adjList = new List[n+1];

        for(int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int[] row : wires) {
            adjList[row[0]].add(row[1]);
            adjList[row[1]].add(row[0]);
        }

        for(int i = 0; i < n-1; i++) {
            int num = bfs(wires[i][0], wires[i][1], n);

            min = Math.min(min, Math.abs(num - (n-num)));

            if(min == 0) break;
        }

        answer = min;
        return answer;
    }

    private static int bfs(int a, int b, int n) {
        int num = 1;
        boolean[] isVisited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        isVisited[1] = true;
        queue.offer(1);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int temp : adjList[cur]) {
                if(isVisited[temp]) continue;
                if(cur == a && temp == b) continue;
                if(cur == b && temp == a) continue;
                isVisited[temp] = true;
                queue.offer(temp);
                num++;
            }
        }

        return num;
    }
}
