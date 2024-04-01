package Jo_Seongjeong.Study_12주차;

/**
 * 백준 10943 랜덤 게임~
 *
 * 조건
 * 1 ~ 10까지 정수 중 하나 출력
 *
 * 문제에서 구하고자 하는 것
 * 1 ~ 10까지 정수 랜덤 생성
 *
 * 문제 해결 프로세스
 * Math.random -> 0.1 ~ 1.0까지 만드는 수이므로 *10한 값을 출력하자
 *
 * 고려한 시간복잡도
 * 1
 * */


public class BJ10943 { // 메모리 : 11408 시간 : 72ms
    public static void main(String[] args) {
        int n = (int) (Math.random()*10);

        System.out.println(n);
    }

}