package Jo_Seongjeong.Study_16주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1976 여행가자
 *
 * 조건
 * 도시의 수 N : 1 ~ 200
 * 여행 계획에 속한 도시 수 M : 1 ~ 1000 (중복 가능)
 * 입력 형식 : 인접행렬!, 양방향 그래프
 * A->B 갈 때 중간에 다른 도시 경유해서 가도 상관없음
 * 정점 번호 = 도시 번호 -> 1번부터
 *
 * 문제에서 구하고자 하는 것
 * 여행 계획을 지킬 수 있는지 여부
 * 출력 :YES / NO
 *
 * 문제 해결 프로세스
 * union find - 크루스칼
 * 1. 부모 노드 배열 만들기
 * 2. union, find 함수 만들기
 * 3. 모두 union-find 해보기
 * 4. 시작 점 기준의 루트 배열에 여행 계획 요소가 있는지 확인해서 y/n 출력
 *
 * 고려한 시간 복잡도
 * 200 * 200 = 40000 + 1000 = 5000
 * */

public class BJ1976 { // 메모리 : 16244kb, 시간 : 140ms
    static int n;
    static int m;
    static int[] parents;
    static int[][] adjMatrix;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adjMatrix = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] plan = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < m+1; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        setParents();

        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                if(adjMatrix[i][j] == 0) continue;
                union(i, j);
            }
        }
//        System.out.println(Arrays.toString(parents));

        int start = parents[plan[1]];
        for(int i = 2; i < m+1; i++) {
            if (parents[plan[i]] == start) continue;
            System.out.println("NO");
            return;
        }
        System.out.println("YES");

    }
    private static void setParents() {
        parents = new int[n+1];

        for(int i = 1; i < n+1; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(rootA < rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
        return true;
    }
}
