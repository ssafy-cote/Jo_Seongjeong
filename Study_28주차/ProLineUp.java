package Jo_Seongjeong.Study_28주차;

import java.util.Arrays;

/**
 * 프로그래머스 줄 서는 방법
 *
 * 조건
 * 사람수 n : 1 ~ 20
 * x번째 정렬 모습 k : 1 ~ 20!

 * 문제에서 구하고자 하는 것
 * k번 째 정렬 모습
 *
 * 문제 해결 프로세스
 * 그리디
 * 각 자리수에 올 숫자를 구해보자
 * 첫번째 자리 -> (n-1)! 1개 당 숫자 오름차순으로 올 수 있음
 * 두번째 자리 -> (n-1)! / (n-1), 위와 같음
 * 반복
 * 숫자 결정되면, factorial 갱신 및 순서 갱신
 *
 * 고려한 시간 복잡도
 * 20 * 20 = 40
 */

public class ProLineUp {
    public static void main(String[] args) {
        int[] answer = solution(3, 5);

        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int n, long k) {
        boolean[] isChecked = new boolean[n+1];
        int[] answer = new int[n];

        long factorial = 1;
        for(long i = 2; i < n; i++) {
            factorial *= i;
        }

        long i = 1; // 오름차순 몇번째 수가 들어올 수 있는지
        long j = n-1; // factorial 갱신
        int index = 0; // 정답 배열 index
        while(index < n) {
            if(factorial * i >= k) {
                int num = 0;
                int count = 0;

                for(int l = 1; l < n+1; l++) {
                    if(isChecked[l]) continue;

                    count++;

                    if(count == i) {
                        num = l;
                        break;
                    }
                }

                answer[index] = num;
                isChecked[num] = true; // 순열 뽑은 것 체크
                index++;
                k -= (factorial * (i-1)); // 순서 갱신
                factorial /= j; // 다음 팩토리얼 경우의 수
                i = 0;
                if(j > 1)j--;
            }
            i++;
        }

        return answer;
    }
}
