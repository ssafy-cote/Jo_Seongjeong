package Jo_Seongjeong.Study_11주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 9655 돌 게임
 *
 * 조건
 * 돌의 개수 N : 1 ~ 1000
 * 턴을 번걸아가며 돌을 가져감
 * 돌은 1개 / 3개 가져갈 수 있음
 * 마지막 돌을 가져가면 이김
 * 게임은 상근이가 먼저 시작
 *
 * 문제에서 구하고자 하는 것
 * 상근이가 이기면 SK, 창영이가 이기면 CY 출력
 *
 * 문제 해결 프로세스
 * 어차피 1 아니면 3으로 가져가기 때문에, 홀수면 상근, 짝수면 창영이가 이기지 않나?
 *  -> 1, 3 뭐로 가져가던 결과는 같음
 *
 * 고려한 시간 복잡도
 * 1
 * */

public class BJ9655 { // 메모리 : 11516kb, 시간 : 84ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n%2 == 0) System.out.println("CY");
        else System.out.println("SK");
    }
}
