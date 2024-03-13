package Jo_Seongjeong.Study_9주차;

import java.util.Arrays;

/**
 * 프로그래머스 2개 이하로 다른 비트
 *
 * 조건
 * x : 양의 정수, 1 ~ 10^15
 * f(x) : x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
 * 배열의 길이 : 1 ~ 100000
 *
 * 문제에서 구하고자 하는 것
 * 조건에 맞는 f(x)값 구하기
 *
 * 문제 해결 프로세스
 * numbers 배열의 처음부터 끝까지 반복
 * 배열 원소를 2진수로 바꿔 변수에 저장
 * f(x) 구하기
 * 짝수인 경우 -> +1
 * 홀수인 경우 -> 0이나오면 바로뒤꺼랑 뒤집기
 * 10진수로 변환 후 answer 배열에 저장
 *
 * 고려한 시간 복잡도
 * 100000 * 47(10^15의 2진수 비트 길이) = 4700000
 * */

public class ProTwoDifferentBit { // 최대 메모리 : 129mb, 최대 시간 :98.20ms
    public static void main(String[] args) {
        long[] numbers = new long[]{2, 7};
        System.out.println(Arrays.toString(solution(numbers)));
    }
    static public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            if(num % 2 == 0){
                num++;
            }
            else {
                String temp = Long.toBinaryString(num);
                temp = "0" + temp;
                for(int j = temp.length()-1; j >=0; j--) {
                    char ch = temp.charAt(j);

                    if(ch == '1') continue;

                    if(j == 0)
                        temp = "10"+temp.substring(j+2);
                    else if(j == temp.length()-2)
                        temp = temp.substring(0, j) + "10";
                    else
                        temp = temp.substring(0, j) + "10" + temp.substring(j+2);
                    break;
                }
                num = Long.parseLong(temp, 2);
            }
            answer[i] = num;
        }
        return answer;
    }
}
