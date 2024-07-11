package Jo_Seongjeong.Study_23주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1747 소수&팰린드롬
 *
 * 조건
 * 어떤 수 N : 1 ~ 1000000
 * 팰린드롬 : 어떤 수와 그 수의 숫자 순서를 뒤집은 수가 일치하는 수
 * 소수 : 1과 자기 자신을 제외하고 어떠한 수로도 나누어 떨어지지 않는 수
 * 1은 소수 아님
 *
 * 문제에서 구하고자 하는 것
 * n보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서 가장 작은 수
 *
 * 문제 해결 프로세스
 * 소수인지 확인
 *  가장 쉬운 것 :  2부터 수-1까지 확인하기 -> 시간 초과
 *  다음 : 2부터 제곱근까지(가운데 약수) 확인하기 -> 성공
 *        단, 2를 고려해야함(2부터 확인하기 때문에 2의 제곱근이면 로직에 문제 생김)
 * 통과되면 팰린드롬 확인
 *  팰린드롬 확인 법
 *  String으로 바꾸고 reverse 시키자. 서로 비교
 *
 * 고려한 시간 복잡도
 * 7(회문) * 1000(제곱근)(소수 확인) = 7000
 * */

public class BJ1747 { // 메모리 : 20028kb, 시간 : 580ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int min = 0;
        int i = n;

//        if(i == 1) {
//            i++;
//        }

        while(true) {
            sb.setLength(0); // 초기화

            boolean flag = true;
            // 소수 확인
            for(int j = 2; j < Math.sqrt(i)+1; j++) {
                if(i % j == 0) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                // 회문 확인
                String str = i+"";
                sb.append(str);
                String str2 = sb.reverse().toString();

                for(int j = 0; j < str.length(); j++) {
                    if(str.charAt(j) != str2.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(i == 1 || i == 2) {
                min = 2;
                break;
            }
            if(flag) {
                min = i;
                break;
            }
            i++;
        }
        System.out.println(min);
    }
}
