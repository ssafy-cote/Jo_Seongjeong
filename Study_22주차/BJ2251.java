package Jo_Seongjeong.Study_22주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2251 물통
 *
 * 조건
 * 물통 A B C : 1 ~ 200
 * A B는 비어있음
 * C는 가득 차있음
 * 물은 다른 물통으로 부울 수 있는 데, 이 때 한 물통이 비거나 다른 한 물통이 가득 찰 때까지 가능
 * 손실되는 물은 없음
 *
 * 문제에서 구하고자 하는 것
 * 물을 옮기는 동안 A 물통이 비어 있을 때, C 물통 안에 들어있는 물의 양 각각 (오름차순)
 *
 * 문제 해결 프로세스
 *
 *
 * 고려한 시간 복잡도
 *
 * */

public class BJ2251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());


    }
}
