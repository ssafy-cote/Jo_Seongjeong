package Jo_Seongjeong.Study_29주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 17205 진우의 비밀번호
 * 조건
 * 비밀번호 -> 영어 소문자로 구성
 * 비밀번호 찾기 -> 문자열들을 사전순으로 시도해 찾음
 * 비밀번호 길이 n : 1 ~ 10
 * 하나의 비밀번호를 시도하는 데 1초 소요
 *
 * 문제에서 구하고자 하는 것
 * 비밀번호의 가능성이 있는 문자열을 사전순으로 시도할 때 몇초(몇번째 시도)가 걸리는지
 *
 * 문제 해결 프로세스
 * 중복 순열에 대한 순서를 구하자 단, a-z로 각 자리수에 대한 26가지 경우의 수
 * 1자리 -> 26
 * 2자리 -> 26 * 26 + 26(한자리도 포함)
 * 3자리 -> 26 * 26 * 26 + 26 * 26 + 26 (두, 세자리도 포함)
 * 각 자리수에 대해 이전까지에 대한 경우의 수 구해보기
 * 3자리 제한, ba의 경우 = 705
 * 1자리 2개(a, b) + 2자리 (aa ~ az) 26개 + 3자리(aaa ~ azz) 26*26개 = 704번째(b)
 * 일반항
 * 각 자리수에 대해, 본인 이전 알파벳 개수 * 26^자리수-1들을 각각 모두 더하면 이전이 나옴
 * +1하면 답
 *
 * 고려한 시간 복잡도
 * 10
 * */

public class BJ17205 { // 메모리 : 11532kb, 시간 : 64ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String password = br.readLine();

        long[] array = new long[n+1];
        array[0] = 1;
        for(int i = 1; i < n+1; i++) {
            array[i] = array[i-1] * 26 + 1; // 각 자리수에 대한 이전 경우의 수 + 나
        }


        long num = 0;
        for(int i = 0; i < password.length(); i++) {
            num += (password.charAt(i) - 'a') * array[n-i-1] + 1;
        }

        System.out.println(num);

    }
}