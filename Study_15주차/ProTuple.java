package Jo_Seongjeong.Study_15주차;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 프로그래머스 튜플

 조건
 튜플 특징
 1. 중복된 원소가 있을 수 있음
 2. 원소에는 순서가 있으며, 순서가 다르면 다른 튜플
 3. 튜플 원소 개수는 유한 n개
 중복된 원소가 없는 튜플 -> 집합기호 {}로 표현 가능
 순서를 지키며, 원소의 개수가 1개부터 n개까지의 집합으로 표현 가능
 문자열 s(집합 표현) : 5 ~ 1000000
 s : 숫자 { } ,로만 이루어짐
 0으로 시작하는 경우 X
 s는 중복 원소 X
 튜플의 원소는 1 ~ 100000
 정답 배열 길이 : 1 ~ 500
 문제에서 구하고자 하는 것
 튜플 표현 집합 -> 중복되는 원소가 없는 튜플

 문제 해결 프로세스
 1. 문자열의 {}을 기준으로 들어갈 list 형식의 배열 생성
 배열 길이는 { 의 길이
 2. 문자열의 } 기준으로 리스트 배열 i번째에 값 넣기
 3. 정렬, 정렬 기준은 리스트 배열의 크기를 기준으로 오름차순
 4. 튜플 만들기 -> 해당 원소가 나온 순서대로

 고려한 시간 복잡도
 100000 + 100000

 */

public class ProTuple {
    public static void main(String[] args) {
        String str = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        int[] arr = solution(str);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] solution(String s) {
        int size = -1;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '{') size++;
        }

        List<Integer>[] list = new List[size];
        for(int i = 0; i < size; i++) {
            list[i] = new ArrayList<>();
        }

        int index = 0;
        int i = 0;
        String str = "";
        while(i < s.length()) {
            char ch = s.charAt(i);

            if(ch != ',' && ch != '{' && ch != '}') str += ch;

            else if(ch == ',' || ch == '}') {
                if(str.length() > 0) list[index].add(Integer.parseInt(str));
                str = "";
            }

            if(ch == '}') index++;

            i++;
        }

        Arrays.sort(list, (a, b) -> a.size() - b.size());


        List<Integer> temp = new ArrayList<>();
        boolean[] isChecked = new boolean[100001];

        for(int j = 0; j < size; j++) {
            for(int k = 0; k < list[j].size(); k++) {
                if(isChecked[list[j].get(k)]) continue;

                isChecked[list[j].get(k)] = true;
                temp.add(list[j].get(k));
            }
        }

        int[] answer = new int[temp.size()];
        for(int j = 0; j < answer.length; j++) {
            answer[j] = temp.get(j);
        }

        return answer;
    }
}
