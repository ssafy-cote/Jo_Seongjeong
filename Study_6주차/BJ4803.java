package Jo_Seongjeong.Study_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 4803 트리
 *
 * 조건
 * 연결 : 두 정점 사이에 경로가 있는 경우
 * 연결 요소 : 모든 정점이 서로 연결되오 있는 정점의 부분 집합
 * 트리 : 사이클이 없는 연결 요소
 *  정점 n개, 간선 n-1, 두 정점에 대해 경로가 유일
 * 정점 개수 n : 1 ~ 500
 * 간선 개수 m : ~ n(n-1)/2
 *
 * 문제에서 구하고자 하는 것
 * 그래프에서 트리의 개수
 *
 * 문제 해결 프로세스
 * 입력을 받을 때, 해당 정점의 개수만큼으로 트리 개수를 초기화 한다
 * union-find 알고리즘을 통해 트리 개수를 찾는다
 * 만약 union 시, 이미 같은 root를 가진 정점이였다면 합치지 않음 -> 트리 개수가 줄어들지 않는다
 * 만약 union이 된다면, 하나의 트리로 합쳐진 것이기 때문에 트리 개수를 뺀다
 *
 * 고려한 시간 복잡도
 * 500 * 499 / 2 = 124,750
 * */

public class BJ4803 { // 메모리 : 55856kb, 시간 : 364ms
    static int n;
    static int m;
    static int count;
    static int[] parents;
    static int[] cycleRoot;
    static int index;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            count = n;
            index = 0;

            if(n == 0 && m == 0) break;

            parents = new int[n+1];
            cycleRoot = new int[n+1];
            for(int i = 1; i < n+1; i++) {
                parents[i] = i;
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(union(a, b)) count--;

            }

            sb.append("Case" + " " + testCase + ": ");
            if(count == 0) {
                sb.append("No trees.");
            }
            else if(count == 1) {
                sb.append("There is one tree.");
            }
            else {
                sb.append("A forest of " + count + " trees.");
            }
            sb.append("\n");
            testCase++;
        }
        System.out.println(sb);
    }

    private static int find(int a) {
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            boolean flag = false;
            for (int i = 0; i < index; i++) {
                if (cycleRoot[i] == aRoot) {
                    flag = true;
                    break;
                }
            }
            if (flag) return false;

            cycleRoot[index++] = aRoot;
            count--;
            return false;
        }

        boolean flag1 = false;
        boolean flag2 = false;
        for(int i  = 0; i < index; i++) {
            if(cycleRoot[i] == aRoot) flag1 = true;
            if(cycleRoot[i] == bRoot) {
                flag2 = true;
                cycleRoot[i] = aRoot;
            }

        }
        if(flag1 && flag2) count++;
        parents[bRoot] = aRoot;
        return true;
    }
}
