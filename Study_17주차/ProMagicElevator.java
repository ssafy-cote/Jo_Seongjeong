package Jo_Seongjeong.Study_17주차;

/**
 프로그래머스 마법의 엘리베이터

 조건
 현재 층 storey : 1 ~ 100000000 -> int
 한번에 이동할 수 있는 층 수 : 10^c
 내려가는 경우 0층 밑으로 내려갈 수 없음

 문제에서 구하고자 하는 것
 0층으로 가는 최소 이동 횟수

 문제 해결 프로세스
 그리디?
 0이될 때까지 1의 자리부터 마지막 자리까지 횟수로 더하기
 5 기준, 5보다 크면 +, 작으면 -로 나누자
 5인 경우만 더 생각하기
 다음 수가 5보다 작으면 내리는 게 이득
 아니면 올리는 게 이득

 고려한 시간 복잡도
 1억 -> 9자리 -> 9

 */

public class ProMagicElevator {
    public static void main(String[] args) {
        int storey = 2554;
        int result = solution(storey);
        System.out.println(result);
    }

    public static int solution(int storey) {
        int answer = 0;

        int pre = 0;
        while(storey > 0) {
            int num = (storey % 10) + pre;
            storey /= 10;

            if(num == 10)  continue;

            if(num == 5) {
                if(storey % 10 > 4) {
                    answer += (10-num);
                    pre = 1;
                }
                else {
                    answer += num;
                    pre = 0;
                }
            }
            else if(num > 5) {
                answer += (10-num);
                pre = 1;
            }
            else {
                answer += num;
                pre = 0;
            }
        }
        if(pre == 1) answer++;

        return answer;
    }
}
