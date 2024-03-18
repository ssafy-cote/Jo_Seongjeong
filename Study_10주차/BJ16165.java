package Jo_Seongjeong.Study_10주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 16165 걸그룹 마스터 준석이
 *
 * 조건
 * 걸그룹의 수 N : 1 ~ 99
 * 맞혀야 할 문제의 수 M : 1 ~ 99
 * 팀과 멤버의 이름 : 1 ~ 100글자, 알파벳 소문자
 * 하나의 걸그룹이나 다른 두 걸그룹에 이름이 같은 두 멤버가 있는 경우는 없음
 * 퀴즈의 종류
 *  0 -> 팀의 이름이 주어짐
 *  1 -> 멤버의 이름이 주어짐
 *
 * 문제에서 구하고자 하는 것
 * 퀴즈의 종류가 0일 경우 해당 팀에 속한 멤버의 이름을 사전순으로 한 줄에 한명씩 출력
 * 퀴즈의 종류가 1인 경우 해당 멤버가 속한 팀의 이름을 출력
 *
 * 문제 해결 프로세스
 * 이름 첫글자가 나온 횟수를 cnt해 줄 0 ~ 26(각 인덱스는 알파벳 소문자 순서) 배열을 생성
 * 입력을 저장할 리스트를 만들자
 * 1. 이름을 모두 나열 할 리스트, 리스트 형태는 배열, 0은 걸그룹 이름, 1은 멤버이름
 * 이 때, 첫글자에 해당하는 배열++
 * 2. 걸그룹 배열 리스트
 * 길이는 n, 각 배열의 첫번째 원소는 그룹명, 나머지는 멤버
 * 퀴즈의 종류가 0이면, 해당 걸그룹 리스트 찾기, 찾은 후 2번째 원소부터 임시 리스트에 저장, 정렬 후 출력
 * 퀴즈의 종류가 1이면, 리스트[1]이 일치하면 리스트[0] 출력 후 종료
 * 문제 횟수만큼 반복
 *
 * 고려한 시간 복잡도
 * 99(문제 수) * 99(배열 길이)  = 9,801 2초는 충분하지 않을까
 * */

public class BJ16165 { // 메모리 : 11516kb, 시간 : 80ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<String[]> name = new ArrayList<>();
        List<String>[] group = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            group[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            String gName = br.readLine();
            int num = Integer.parseInt(br.readLine());

            group[i].add(gName);

            for(int j = 0; j < num; j++) {
                String member = br.readLine();
                group[i].add(member);
                name.add(new String[]{gName, member});
            }
        }

        for(int i = 0; i < m; i++) {
            String quiz = br.readLine();
            int type = Integer.parseInt(br.readLine());

            if(type == 0) {
                for(int j = 0; j < n; j++) {
                    if(!quiz.equals(group[j].get(0))) continue;

                    List<String> temp = new ArrayList<>();
                    for(String str : group[j]) {
                        if(quiz.equals(str)) continue;
                        temp.add(str);
                    }
                    Collections.sort(temp);

                    for(String str : temp) {
                        sb.append(str).append("\n");
                    }
                    break;
                }
            }
            else {
                for(String[] arr : name) {
                    if(!arr[1].equals(quiz)) continue;
                    sb.append(arr[0]).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
