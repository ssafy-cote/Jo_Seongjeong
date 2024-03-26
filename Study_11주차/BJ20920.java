package Jo_Seongjeong.Study_11주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 20920 영단어 암기는 괴로워
 *
 * 조건
 * 단어의 개수 N : 1 ~ 100000
 * 단어의 길이 M : 1 ~ 10
 * 단어는 반드시 1개 이상 존재
 * 영어 단어장 순서
 * 1. 자주 나오는 단어일수록 앞
 * 2. 해당 단어의 길이가 길수록 앞
 * 3. 알파벳 사전 순 앞
 * 길이가 M이상인 단어만 외움
 * 문제에서 구하고자 하는 것
 *
 * 문제 해결 프로세스
 * 단어 입력을 받을 때 길이가 M이상인 것만 배열에 저장
 * 해당 배열을 오름차순으로 정렬해보자
 * 배열은 한바퀴 돌아, 리스트에 단어와, 해당 개수를 세면서 중복제거하자
 * 이후 조건에 맞게 정렬
 *
 * 고려한 시간 복잡도
 * 100000 + (100000log100000) * 2 100000 + 10log2(5)
 * */

public class BJ20920 { // 메모리 : 69036kb, 시간 : 972ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] array = new String[n];

        for(int i = 0; i < n; i++) {
            array[i] = br.readLine();
        }

        Arrays.sort(array);

        List<String[]> list = new ArrayList<>();
        int idx = 0;
        int cnt = 1;
        for(int i = 0; i < n; i++) {
            if(array[i].length() < m) continue;
            if(list.isEmpty()) {
                list.add(new String[]{array[i], "1"});
            }
            else {
                if (list.get(idx)[0].equals(array[i])) cnt++;

                else {
                    list.get(idx)[1] = cnt + "";
                    list.add(new String[]{array[i], "1"});
                    idx++;
                    cnt = 1;
                }

                if(i == n-1) list.get(idx)[1] = cnt + "";
            }
        }

        Collections.sort(list, (a, b) -> {
            if(Integer.parseInt(a[1]) == Integer.parseInt(b[1])) {
                if(a[0].length() == b[0].length()) {
                    return a[0].compareTo(b[0]);
                }
                return b[0].length() - a[0].length();
            }
            return Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
        });

        for(String[] temp : list) {
            sb.append(temp[0] + "\n");
        }
        System.out.println(sb);
    }
}
