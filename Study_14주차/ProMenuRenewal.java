package Jo_Seongjeong.Study_14주차;

import java.util.*;

/**
 * 프로그래머스 메뉴 리뉴얼
 *
 * 조건
 * 코스요리 메뉴 : 이전에 가장 많이 함께 주문한 단품메뉴들
 * 코스요리 메뉴는 최소 2가지 이상의 단품메뉴
 * 코스요리 메뉴 후보 : 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합
 * 손님이 주문한 단품메뉴 조합: orders[] : 2 ~ 20
 * 원소 크기 : 길이가 2 ~ 10인 문자열
 * 알파벳 대문자, 중복된 경우는 X
 * 추가하고자 하는 코스 요리의 단품메뉴 개수 course[] : 1 ~ 10
 * 원소 크기 : 2 ~ 10(오름차순 정렬)
 *
 * 문제에서 구하고자 하는 것
 * 코스메뉴의 조합
 *
 * 문제 해결 프로세스
 * Map에 가능한 조합을 담을 것(course 길이에 맞게 다 뽑기)
 * 조합으로 뽑은 배열을 정렬하기 위해, 배열 복사
 * 문자열로 만들고 맵에 넣자
 * 이미 있는 키값이면 +1
 * 없으면 추가
 * key는 코스 요리 구성메뉴, value는 나온 횟수
 * value 기준 오름차순 정렬 후, 제일 높은 값 정답에 넣자(동률이면 다 넣기)
 * 반복
 * 정답 배열 오름차순 정렬 후 종료
 *
 * 고려한 시간 복잡도
 * 조합 최악 : (10C2+... + 10C10) * 10 = 10,020
 * */

public class ProMenuRenewal {
    static int n;
    static char[] picked;
    static Map<String, Integer> map;
    static List<String> ans;

    public static void main(String[] args) {
        String[] orders = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = new int[] {2, 3, 4};

        String[] answer = solution(orders, course);

        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer;
        ans = new ArrayList<>();
        for(int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            for(int j = 0; j < orders.length; j++) {
                if(orders[j].length() < course[i]) continue;
                String str = orders[j];
                n = course[i];
                picked = new char[n];
                combi(0, 0, str);
            }
            chooseCourse();
        }
        System.out.println(ans);
        answer = ans.toArray(new String[ans.size()]);
        return answer;
    }

    private static void combi(int depth, int index, String str) {
        if(depth == n) {
            String candidate = "";
            char[] sortCandidate = picked.clone();
            Arrays.sort(sortCandidate);

            for(int i = 0; i < n; i++) {
                candidate += sortCandidate[i];
            }
            System.out.println(candidate);

            if(map.containsKey(candidate)) {
                int count = map.get(candidate);
                map.put(candidate, count+1);
            }
            else map.put(candidate, 1);
            return;
        }

        for(int i = index; i < str.length(); i++) {
            picked[depth] = str.charAt(i);
            combi(depth+1, i+1, str);
        }
    }

    public static void chooseCourse() {
        if(map.isEmpty()) return;

        List<String> list = new ArrayList<>(map.keySet());

        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));

        int max = map.get(list.get(0));
        if(max == 1) return;

        for (String key : list) {
            System.out.print("Key : " + key);
            System.out.println(", Val : " + map.get(key));
        }

        for(String str : list) {
            if(max > map.get(str)) break;
            ans.add(str);
        }
    }
}
