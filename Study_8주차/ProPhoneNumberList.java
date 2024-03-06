package Jo_Seongjeong.Study_8주차;

import java.util.Arrays;

/**
 * 프로그래머스 전화번호 목록
 *
 * 조건
 * 어떤 전화번호가 다른 전화번호의 접두어인 경우 : 다른 전화번호는 어떤 전화번호로 시작해야 함
 * 전화번호 개수 : 1 ~ 1000000
 * 각 전화번호의 길이 : 1 ~ 20
 * 중복 전화번호는 x
 *
 * 문제에서 구하고자 하는 것
 * 접두어가 있는지 확인 있으면 fasle, 없으면 true
 *
 * 문제 해결 프로세스
 * 정렬 -> 1. 한자리씩 보았을 때 숫자가 작은 것부터  / 2. 길이가 짧은 것부터
 * 다음꺼와 비교
 * 어차피 정렬되어 있음
 *
 * 고려한 시간 복잡도
 * 1000000log1000000 + 1000000
 *
 * */

public class ProPhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = new String[]{"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for(int i = 1; i < phone_book.length; i++) {
            if(phone_book[i].startsWith(phone_book[i-1])) {
                answer = false;
                break;
            };
        }
        return answer;
    }
}
