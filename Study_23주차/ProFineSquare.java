package Jo_Seongjeong.Study_23주차;

import java.util.*;

/**
 * 프로그래머스 멀쩡한 사각형
 *
 * 조건
 * 가로길이 w
 * 세로길이 h
 * 격자칸 1*1
 * 양 대각 꼭짓점 방향(1부분)으로 선이 있음
 *  -> 2개의 직각 삼각형
 *
 * 문제에서 구하고자 하는 것
 * 1*1 정사각형으로 사용할 수 있는 사각형 개수
 *
 * 문제 해결 프로세스
 * w와 h의 최대 공약수를 구함
 * 최대공약수로 각각 나눈 몫을 더하고 -1
 * 최대공약수로 곱함 => 사용 불가한 사각형
 * 전체 사각형 개수 - 사용 불가한 사각형
 *
 * 고려한 시간 복잡도
 * 최악 1억
 */

class ProFineSquare {
    public static void main(String[] args) {
        int w = 12;
        int h = 8;

        long result = solution(w, h);

        System.out.println(result);
    }

    public static long solution(int w, int h) {
        long answer = 1;
        long wl = (long)w;
        long hl = (long)h;

        // 최대 공약수 구하기
        long a = Math.max(wl, hl);
        long b = Math.min(wl, hl);

        while(b > 0) {
            long c = a % b;
            a = b;
            b = c;
        } // a => 최대 공약수

        // 최대 공약수로 w h 나누기 - 1
        long invalid =  wl/a + hl/a - 1;

        // 계산
        answer =  wl * hl - invalid*a;

        return answer;
    }
}
