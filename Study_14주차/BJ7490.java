package Jo_Seongjeong.Study_14주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 7490 0 만들기
 *
 * 조건
 * 테스트 케이스의 개수 : 1 ~ 9
 * 자연수 수열의 최대 수 N : 3 ~ 9
 * 숫자 사이에 삽입할 수 있는 경우의 수
 * + : 더하기
 * - : 빼기
 * 공백 : 숫자 이어붙이기
 *
 * 문제에서 구하고자 하는 것
 * 해당 수열에서 연산을 입력했을 때 0이 되는 연산
 *
 * 문제 해결 프로세스
 * 중복순열
 * 3가지 연산의 경우의 수에 맞게 모두 뽑자
 * 배열 하나로 연산 만들기(index번호 = 수열의 수, 배열 값 = 연산) 마지막에 num 붙이기
 * 다 뽑으면 연산 시행
 * 0이 되면 해당 연산 출력
 *
 * 고려한 시간 복잡도
 * 3^8(3가지 연산이 최대 8번 들어갈 수 있음) * 10(테스트 케이스 수) = 65,610
 * */

public class BJ7490 { // 메모리 : 22012kb, 시간 : 132ms
    static StringBuilder sb;
    static int n;
    static String[] op = new String[] {" ", "+", "-"};
    static String[] picked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());

            picked = new String[n];

            dupPermu(1);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static void dupPermu(int idx) {
        if(idx == n) {
//            System.out.println(Arrays.toString(picked));
            int calc = 0;
            String num = "1";
            for(int i = 1; i < n; i++) {
                if(!picked[i].equals(" ")) {
                    calc += Integer.parseInt(num);
                    num = picked[i] + (i+1);
                }
                else num += (i+1);

                if(i == n-1) calc += Integer.parseInt(num);

            }
            if(calc != 0) return;

            String str = "";
            for(int i = 1; i < n+1; i++) {
                str += i;
                if(i < n) str += picked[i];
            }

            sb.append(str + "\n");
            return;
        }

        for(int i = 0; i < op.length; i++) {
            picked[idx] = op[i];
            dupPermu(idx+1);
        }

    }

}
