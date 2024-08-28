package Jo_Seongjeong.Study_30주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 1107 리모컨
 *
 * 조건
 * 리모컨 버튼 종류
 * 0 ~ 9, +, -
 * 0에서 -는 채널이 변하지 않음
 * 채널은 무한대만큼 있다 -> +는 항상 가능
 * 이동하려는 채널 n : 0 ~ 500000
 * 현재 채널 : 100
 * 고장난 숫자 버튼의 개수 m : 0 ~ 10
 *
 * 문제에서 구하고자 하는 것
 * 채널 n으로 이동하기 위해 버튼을 눌러야 하는 횟수
 *
 * 문제 해결 프로세스
 * 해당 채널을 기준으로 +- 하면서 이동 가능한지 확인
 * 가능하다면 1번 + n - 이동채널 하면 정답 나올듯
 * 정답이 나올 때까지 반복
 *
 * 고려한 시간 복잡도
 * 500000 * 6 * 2 = 6,000,000
 * */

public class BJ1107 { // 메모리 : 55024kb, 시간 : 200ms
    static boolean[] button;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int first = 100;
        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        button = new boolean[10];
        Arrays.fill(button, true);

        if(m != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                String str = st.nextToken();

                if (str.equals("+") || str.equals("-")) continue;

                button[Integer.parseInt(str)] = false;
            }
        }

        if(m == 10) {
            System.out.println(Math.abs(n - first));
            return;
        }

        int count = 0;

        while(true) {
            boolean flag = false;
            int cur = 0;

            if(first == n) break;

            else {
                cur = n - count;
                if(cur >= 0) flag = findChannel(cur);

                if(!flag) {
                    cur = n + count;
                    flag = findChannel(cur);
                }
            }
            count++;

            if(!flag) continue;

            String str = cur + "";
            answer = Math.min(str.length() + count -1, Math.abs(n - first));
            break;
        }

        System.out.println(answer);
    }

    private static boolean findChannel(int cur) {
        String str = cur + "";

        for(int i = 0 ; i < str.length(); i++) {
            if(!button[str.charAt(i) - '0']) {
                return false;
            }
        }

        return true;
    }
}
