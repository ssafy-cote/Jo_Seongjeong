package Jo_Seongjeong.Study_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14501 퇴사
 *
 * 조건
 * 상담을 완료하는 데 걸리는 기간 T : 1 ~ 5
 * 상담 금액 P : 1 ~ 1000
 * 상담을 할 수 있는 기간 N : 1 ~ 15
 *
 * 문제에서 구하고자 하는 것
 * 상담 결과 최대 이익
 *
 * 문제 해결 프로세스
 * 배열을 입력받을 때 시작 날, 금액, 종료 날로 저장
 * 날짜를 선택하면, 해당 날짜부터 종료날까지 가장 큰 금액을 찾는다
 * 이때, 탐색했던 날은 true 처리로 중복 탐색x
 *
 * 고려한 시간 복잡도
 * 2^15 = 32,768
 * */

public class BJ14501 { // 메모리 : 12904kb, 시간 : 88ms
    static int money;
    static int n;
    static int[][] array;
    static boolean[] isChecked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        money = 0;
        n = Integer.parseInt(br.readLine());
        array = new int[n+1][3];
        isChecked = new boolean[n+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
            array[i][2] = i + array[i][0];
        }

        subset(1);

        System.out.println(money);
    }

    private static void subset(int index) {
        if(index >= n+1) {
            int sum = 0;
            for(int i  = 1; i< n+1; i++) {
                if(array[i][2] > n+1) continue;
                if(isChecked[i]) sum+= array[i][1];
            }
            money = Math.max(sum, money);
            return;
        }

        isChecked[index] = true;
        subset(index + array[index][0]);
        isChecked[index] = false;
        subset(index+1);
    }
}
