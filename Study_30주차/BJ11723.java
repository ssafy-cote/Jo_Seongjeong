package Jo_Seongjeong.Study_30주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 11723 집합
 *
 * 조건
 * 공집합 s
 * 연산의 수 m : 1 ~ 3000000
 * 연산 종류
 * add x: s에 x 추가, x가 이미 있으면 무시
 * remove x : s에서 x 제거, x가 없으면 무시
 * check x : s에 x가 있으면 1, 없으면 0 출력
 * toggle x : s에 x가 있으면 제거, 없으면 x 추가
 * add : s를 1, .... , 20으로 바꿈
 * empty : s를 공집합으로 바꿈
 *
 * 문제에서 구하고자 하는 것
 * 연산 후, check가 주어질 때마다 결과 출력
 *
 * 문제 해결 프로세스
 * set 사용해서 놀기
 *
 * 고려한 시간 복잡도
 * 3000000
 * */

public class BJ11723 { // 메모리 : 325560kb, 시간 : 1484ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int s = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            switch (str) {
                case "add" :
                    int x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)) break;
                    set.add(x);
                    break;
                case "remove" :
                    x = Integer.parseInt(st.nextToken());
                    if(!set.contains(x)) break;
                    set.remove(x);
                    break;
                case "check" :
                    x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
                    break;
                case "toggle" :
                    x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)) set.remove(x);
                    else set.add(x);
                    break;
                case "all" :
                    set.clear();
                    for(int j = 1; j <= 20; j++) {
                        set.add(j);
                    }
                    break;
                case "empty" :
                    set.clear();
                    break;
            }
        }

        System.out.println(sb);
    }
}
