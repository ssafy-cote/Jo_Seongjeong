package Jo_Seongjeong.Study_26주차;

import java.util.HashMap;
import java.util.Map;

/**
 * 프로그래머스 할인행사
 *
 * 조건
 * 일정 금액 지불 시 10일 동안 회원 자격 생김
 * 회원 대상으로 매일 한 가지 제품 할인
 * 할인 제품은 하루에 하나씩만 구매 가능
 * 정현 : 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치하는 경우에만 회원가입
 * 정현이가 원하는 제품 배열 want : 1 ~ 10
 * 원하는 제품 수량 배열 number :1 ~ 10
 * 할인 제품 배열 discount : 10 ~ 100000
 *
 * 문제에서 구하고자 하는 것
 * 회원등록 시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원 등록 날짜의 총 일수
 *
 * 문제 해결 프로세스
 * 완탐으로 10일씩 돌려도 될듯
 * 1. Map을 만들자, Key는 want, value는 number 형태
 * 2. 시작일수를  discount 배열 인덱스 기준으로 반복
 * 3. Map 복사해서 진행
 * 4. 가능 일수 count
 *
 * 고려한 시간 복잡도
 * 100000 * 10 = 1000000
 */

public class ProDiscountEvent {
    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        int answer = solution(want, number, discount);

        System.out.println(answer);
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        for(int i = 0; i < discount.length; i++) {
            if(isPossible(i, new HashMap<>(map), discount)) answer++;
        }

        return answer;
    }

    private static boolean isPossible(int start, Map<String, Integer> map, String[] discount) {
        for(int i = start; i < start + 10; i++) {
            if(i == discount.length) break;

            if(!map.containsKey(discount[i])) continue;

            if(map.get(discount[i]) == 1) {
                map.remove(discount[i]);
                continue;
            }
            map.put(discount[i], map.get(discount[i])-1);
        }

        if(map.isEmpty()) return true;

        return false;
    }
}
