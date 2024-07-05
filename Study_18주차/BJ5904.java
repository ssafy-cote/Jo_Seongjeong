package Jo_Seongjeong.Study_18주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 5904 Moo 게임
 *
 * 조건
 * S(0) = moo
 * S(k) = s(k-1) + m + o(k+2개) + s(k-1)
 * 구하고 싶은 글자 위치 n : 1 ~ 10억
 *
 * 문제에서 구하고자 하는 것
 * n번째 글자
 *
 * 문제 해결 프로세스
 * 1. 최초 n의 범위 귀하기
 * 2. 재귀 시작
 * 3. Moo 수열의 위취가 시작, 중간, 끝인지 구학기
 *
 * 고려한 시간 복잡도
 * s(n)의 길이가 10억인 수열까지 -> 3, 10, .. 금방일듯
 * */

public class BJ5904 { // 메모리 : 11476kb, 시간 : 80ms
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int pre = 3;
        int len  = 3;
        int k = 0;

        while(len < n) {
            k++;
            pre = len;
            len = pre * 2 + (1 +2 + k);
        }
        playGame(k, len);

    }

    private static void playGame(int k, int len) {
        int pre = (len - (1 + 2 + k)) / 2;

        if(k == 0) {
            if(n == 1) {
                System.out.println("m");
                return;
            }
            else {
                System.out.println("o");
                return;
            }
        }

        if(n <= pre) playGame(k-1, pre);
        else if(pre + 1 <= n && n < pre + (1 + 2 + k)) {
            if(pre + 1 == n) System.out.println("m");
            else System.out.println("o");
        }
        else {
            n -= (pre + (1 + 2 + k));
            playGame(k-1, pre);
        }
    }
}
