package Jo_Seongjeong.Study_13주차;

import java.util.HashMap;
import java.util.Map;

/**
 * 프로그래머스 뉴스 클러스터링
 *
 * 조건
 * 자카드 유사도 : 집합 간의 유사도를 검사하는 방법, 두 집합의 교집합 크기 / 두 집삽희 합집합 크기, 공집합의 경우 1
 * 원소의 중복을 허용하는 다중집합에 대해서 확장 가능
 * 문자열 사이의 유사도 계산 가능 -> 두글자씩 끊어서 집합 만들기
 *
 * 문자열의 길이 : 2 ~ 1000
 * 다중집합의 원소 조건
 * 영문자로된 글자 쌍만 유효
 * 기타 공백이나 숫자, 특수문자가 들어있는 경우 그 글자 쌍은 버림
 * 대소문자 차이는 무시한다
 * 두 문자열의 유사도 = 자카드 유사도(0 ~ 1 사이의 실수)
 *
 * 문제에서 구하고자 하는 것
 * 자카드 * 65536의 정수부분
 *
 * 문제 해결 프로세스
 * 문자열 집합을 2개 만들자
 * 맵을 통해 만들어볼까?
 * 해당 맵을 통해 교집합, 합집합을 생성해 크기를 구하자 -> 이를 통해 자카드 유사도 구할 수 있음
 *
 * 고려한 시간 복잡도
 *
 * */

public class ProNewsClustering {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";

        int answer = solution(str1, str2);

        System.out.println(answer);
    }

    private static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int answer = 65536;
        int unionSize = 0;
        int intersectionSize= 0;

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for(int i = 0; i < str1.length()-1; i++) {
            char a = str1.charAt(i);
            char b = str1.charAt(i+1);
            if(a < 97 || a > 122) continue;
            if(b < 97 || b > 122) continue;


            String temp = a+""+b;
            if(map1.containsKey(temp)) map1.put(temp, map1.get(temp)+1);
            else map1.put(temp, 1);
            unionSize++;
        }

        for(int i = 0; i < str2.length()-1; i++) {
            char a = str2.charAt(i);
            char b = str2.charAt(i+1);
            if(a < 97 || a > 122) continue;
            if(b < 97 || b > 122) continue;
            String temp = a+""+b;
            if(map2.containsKey(temp)) map2.put(temp, map2.get(temp)+1);
            else map2.put(temp, 1);
            unionSize++;
        }

        if(map1.isEmpty() && map2.isEmpty()) return answer;

        for(String str : map2.keySet()) {
            if(map1.containsKey(str)) intersectionSize += Math.min(map1.get(str), map2.get(str));
        }

        unionSize -= intersectionSize;

        System.out.println(intersectionSize);
        System.out.println(unionSize);


        answer *= (double) intersectionSize/unionSize;

        return answer;
    }
}
