package Jo_Seongjeong.Study_29주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 10815 숫자 카드
 *
 * 조건
 * 카드 개수 n : 1 ~ 500000
 * 상근이가 가지고 있는 카드인지 판별할 카드 개수 m : 1 ~500000
 * 카드에 적혀 있는 수 : -10000000 ~ 10000000
 * 같은 수가 적혀 있는 경우 X -> 중복 X
 *
 * 문제에서 구하고자 하는 것
 * M개의 수에 대한 각 숫자카드를 상근이가 가지고 있는지 판별 -> 1 없으면 0
 *
 * 문제 해결 프로세스
 * set
 *
 * 고려한 시간 복잡도
 * 500000
 * */

public class BJ10815 { // 메모리 : 174560kb, 시간 : 752ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        int[] array = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++) {
            if(set.contains(array[i])) sb.append(1 + " ");
            else sb.append(0 + " ");
        }

        System.out.println(sb);
    }
}
