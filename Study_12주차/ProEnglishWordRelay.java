package Jo_Seongjeong.Study_12주차;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 프로그래머스 영어 끝말잇기
 *
 * 조건
 * 사람 인원 n : 2 ~ 10
 * 사람들이 순서대로 말한 단어 배열 words : n ~ 100
 * 단어의 길이 : 2 ~ 50
 * 모든 단어는 알파벳 소문자
 *
 * 규칙
 * 1. 1번부터 번호 순서대로 한 사람씩 단어 말함
 * 2. 마지막 사람이 단어 말하면 다시 1번부터
 * 3. 앞사람이 말한 단어의 마지막 문자로 시작해야함
 * 4. 이전 등장했던 단어는 안됨
 * 5. 한글자는 인정X
 *
 * 문제에서 구하고자 하는 것
 * [탈락자 번호, 해당 번호의 차례(턴)], 탈락자 없으면 [0, 0]
 *
 * 문제 해결 프로세스
 * words의 처음부터 반복을 돌면서
 * set 이용해서 중복 검사
 * 현재 첫글자와 이전 마지막 글자가 같은지 검사
 * 통과하지 못하면 정답 갱신 후 종료
 * 통과하면 순서, 턴, 마지막 단어 갱신
 *
 * 고려한 시간 복잡도
 * 100
 * */

public class ProEnglishWordRelay {
    public static void main(String[] args) {
        int n = 3;
        String[] words = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] answer = solution(n, words);
        System.out.println(Arrays.toString(answer));

    }

    public static int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Set<String> set = new HashSet<>();
        char end = words[0].charAt(words[0].length()-1);
        set.add(words[0]);
        int turn = 1;
        int fail = 2;
        for(int i = 1; i < words.length; i++) {
            String str = words[i];
            char start = str.charAt(0);

            if(end != start || set.contains(str)) {
                answer[0] = fail;
                answer[1] = turn;
                break;
            }

            set.add(str);
            end = str.charAt(str.length()-1);
            fail++;

            if(fail == n+1) {
                turn++;
                fail = 1;
            }

        }


        return answer;
    }
}
