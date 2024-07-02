package Jo_Seongjeong.Study_8주차;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 프로그래머스 귤 고르기
 *
 * 조건
 * 판매하기 위해 고른 귤의 개수 K, 귤의 개수 : 1 ~ 100000
 * 귤의 크기 : 1 ~ 10000000
 * 배열 원소 : 귤의 크기
 *
 * 문제에서 구하고자 하는 것
 * k개 중 귤 크기에 따른 서로 다른 종류의 최소 값
 *
 * 문제 해결 프로세스
 * 정렬
 * 리스트 생성
 * 0번 인덱스부터 돌면서 같은 원소가 있는지 보며 cnt
 * 다른 수가 나오면 리스트에 넣고, 다시 cnt
 * 리스트 내림차순
 * k보다 클때까지 더하면서 종류 세기
 *
 * 고려한 시간 복잡도
 * 200000
 * */

public class ProMandarinChoice {
    public static void main(String[] args) {
        int k = 6;
        int[] mandarin = new int[]{1, 3, 2, 5, 4, 5, 2, 3};

        System.out.println(solution(k, mandarin));
    }
    public static int solution(int k, int[] tangerine) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();

        Arrays.sort(tangerine);

        int count = 1;
        int num = tangerine[0];
        if(tangerine.length == 1) {
            list.add(count);
        }
        else {
            for (int i = 1; i < tangerine.length; i++) {
                if (tangerine[i] == num) {
                    count++;
                } else {
                    list.add(count);
                    count = 1;
                    num = tangerine[i];
                }
                if (i == tangerine.length - 1) {
                    list.add(count);
                }
            }
        }

        Collections.sort(list, (a, b) -> b-a);

        int temp = 0;
        for(int i : list) {
            temp += i;
            answer++;
            if(temp >= k) break;
        }
        return answer;
    }
}
