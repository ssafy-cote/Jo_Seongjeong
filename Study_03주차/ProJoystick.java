package Jo_Seongjeong.Study_03주차;

/**
 * 프로그래머스 조이스틱
 *
 * 조건
 * 가장 처음, 각 자리의 문자열은 모두 A
 * 위(다음 알파벳), 아래(이전, A는 Z), 왼(커서 왼쪽, 가장 왼쪽이면 오른쪽), 오(커서 오른쪽, 가장 오른쪽이면 왼쪽)
 * name 길이 : 1 ~ 20
 *
 * 문제에서 구하고자 하는 것
 * 주어진 name을 완성하기 위한 최소 조이스틱 이동 횟수
 *
 * 문제 해결 프로세스
 * 아스키 코드값으로 구하자
 * 상하 이동 고려
 * name.cahrAt(i) - 65 <= 14 인 경우, 위 아니면 아래 -> 해당 결과값이 이동값 위 이동이면 96 - name.charAt(i) + 1이 이동값
 * 좌우 이동 고려
 * 양 옆(마지막이면 파싱)으로 한칸씩 이동하는데, A가 아닌쪽으로 이동 다음칸 이동을 우선 고려 -> + i가 이동값
 * 횟수 구하기
 *
 * 고려한 시간 복잡도
 * 13 * 20 * 10 = 2600
 */

public class ProJoystick {
    public static void main(String[] args) {
        String name = "JEROEN";

        int answer = solution(name);

        System.out.println(answer);
    }

    public static int solution(String name) {
        int answer = 0;

        // 위
        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) - 65 < 14) answer += name.charAt(i) - 65;
            else answer += 90 - name.charAt(i) + 1;
        }

        //양 옆
        // A가 있으면 되돌아가는 로직 -> 순방향으로 가다가 A를 만나면 우회하는 경우(이전 왕복), 역방향으로 가다가 A를 만나면 우회하는 경우(뒤 왕복), 한번에 가는경우 비교
        int min = name.length()-1;
        for(int i = 0; i < name.length()-1; i++) {
            int index = i+1;

            while(index < name.length() && name.charAt(index) == 'A') index++;

            min = Math.min(min, Math.min(i*2 + name.length() - index, (name.length()-index) * 2 + i));
        }

        answer += min;

        return answer;
    }
}
