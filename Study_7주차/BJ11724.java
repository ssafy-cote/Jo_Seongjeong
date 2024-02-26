package Jo_Seongjeong.Study_7주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11724 연결 요소의 개수
 *
 * 조건
 *
 * 정점 개수 N : 1 ~ 1000
 * 간선 개수 M : 0 ~ 499,500
 *
 * 문제에서 구하고자 하는 것
 * 그래프의 개수(정점 연결된 뭉탱이가 1개)
 *
 * 문제 해결 프로세스
 * 유니온 파인드로 풀어보자
 * 유니온 파인드 진행한 뒤, 마지막에 루트의 개수만 구하기
 * */

public class BJ11724 {
    static int n;
    static int m;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n+1];
        int count = n;
        for(int i = 1; i < n+1; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a, b)) {
                count--;
            }
        }

        System.out.println(count);
    }

    private static int find(int a) {
        if(a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
