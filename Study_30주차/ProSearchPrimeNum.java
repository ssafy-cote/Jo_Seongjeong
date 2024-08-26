package Jo_Seongjeong.Study_30주차;

import java.util.HashSet;
import java.util.Set;

/**
 * 프로그래머스 소수찾기
 *
 * 조건
 * 종이 조각에 적힌 숫자가 적힌 문자열 numbers : 1 ~ 7
 * numbers 구성 수 : 0 ~ 9
 * ex. 013 => 0, 1, 3
 *
 * 문제에서 구하고자 하는 것
 * 종이 조각으로 만들 수 있는 소수가 몇개인가
 *
 * 문제 해결 프로세스
 * 1. 부분집합을 구하자 -> 순서가 중요한데, 완전히 탐색 불가
 * 1. 순열을 구하자! 단 1개부터 7개까지 뽑아보자
 * 2. 부분집합으로 수를 만들자
 * 3. 소수인지 판별하자
 *
 * 고려한 시간 복잡도
 * 1! + 2! + ... + 7! = 5913
 */

public class ProSearchPrimeNum {
    static int n;
    static String[] picked;
    static boolean[] isChecked;
    static Set<Integer> set;

    public static void main(String[] args) {
        String numbers = "011";

        int answer = solution(numbers);

        System.out.println(answer);
    }

    public static int solution(String numbers) {
        int answer = 0;
        set = new HashSet<>();

        n = numbers.length();

        // 길이가 1인 수부터 n까지인 수 만들기
        for(int i = 1; i <= n; i++) {
            isChecked = new boolean[n];
            picked = new String[i];
            permu(0, i, numbers);
        }

        // set 크기가 정답 소수 개수
        answer = set.size();

        return answer;
    }

    private static void permu(int index, int len, String numbers) {
        if(index == len) {
            String str = "";

            // 문자열 수로 만들기, 제일 앞자리 0인 경우 고려
            for(int i = 0; i < len; i++) {
                if(str.equals("") && picked[i].equals("0")) continue;
                str += picked[i];
            }

            // 빈 문자열 제외
            if(str.equals("")) return;

            int num = Integer.parseInt(str);

            // 1은 소수가 아님
            if(num < 2) return;

            // 중복 제거
            if(set.contains(num)) return;

            // 소수 확인
            for(int i = 2; i <= Math.sqrt(num); i++) {
                if(num % i == 0) return;
            }

            set.add(num);

            return;

        }

        // 순열 뽑기
        for(int i = 0; i < n; i++) {
            if(isChecked[i]) continue;
            isChecked[i] = true;
            picked[index] = numbers.charAt(i) +"";

            permu(index+1, len, numbers);

            isChecked[i] = false;

        }
    }
}
