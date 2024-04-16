package Jo_Seongjeong.Study_14주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 14267 회사 문화 1
 *
 * 조건
 * 회사의 직원 수 n : 2 ~ 100000
 * 최초의 칭찬횟수 m : 2 ~ 100000
 * 칭찬 수치 w : 1 ~ 1000
 * 문화 : 상사가 한 직속 부하를 칭찬하면, 해당 부하의 모든 부하들이 칭찬을 받은
 * 칭찬 수치도 동일함
 * 직속 상사의 번호는 자신의 번호보다 작으며 1은 사장
 *
 * 문제에서 구하고자 하는 것
 * 각각의 직원까지 칭찬을 받은 정도
 *
 * 문제 해결 프로세스
 * DFS
 * 인접리스트로 풀자
 * 단방향 그래프
 * 입력을 받을 때 연결된 노드의 번호를 받으며 재귀호출 -> 터진다
 * 한번만 DFS하면서 누적시켜보자 -> 터진다
 *
 * DP로 풀어보자
 * 직속 상사를 저장할 배열 생성
 * 칭찬점수를 누적시킬 배열 생성 -> 해당 번호가 직접 받는 칭찬 점수 누적
 * 본인 칭찬 점수 = 상사의 창찬점수 + 본인의 누적 칭찬점수
 *
 * 고려한 시간 복잡도
 * 10,000,000,000 터지겠군
 * 100000
 * */

public class BJ14267 { // 메모리 : 64488kb, 시간 : 512ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] superior = new int[n+1];
        int[] scores = new int[n+1];
        int[] rs = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++) {
            superior[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            scores[num] += score;
        }

        for(int i = 1; i < n+1; i++) {
            if(superior[i] == -1) continue;
            rs[i] = rs[superior[i]] + scores[i];
        }

        for(int i = 1; i < n+1; i++) {
            sb.append(rs[i] + " ");
        }

        System.out.println(sb);
    }
}
