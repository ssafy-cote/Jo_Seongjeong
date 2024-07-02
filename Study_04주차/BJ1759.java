package Jo_Seongjeong.Study_4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1759 암호 만들기
 *
 * 조건
 * L : 암호문에 사용될 알파벳 개수 (3 ~ 15)
 * C : 암호로 사용될 후보 알파벳 개수 (3 ~ 15)
 * 암호문에는 최소 한 개의 모음, 최소 두 개의 자음으로 구성
 * 모음 : a, e, i, o, u
 * 중복 x, 순서는 있음 정렬 후 조합으로 뽑자
 *
 * 문제에서 구하고자 하는 것
 * 주어진 알파벳 중 길이 L에 해당하는 모든 암호문의 경우
 *
 * 문제 해결 프로세스
 * 입력 받은 문자 배열을 정렬한다
 * 6C4 조합을 구한다
 * 이 중, 모음이 최소 1개이상, 자음이 2개이상인 경우만 출력할 수 있도록 한다
 *
 * 고려한 시간 복잡도
 * 2^15 = 32,768
 * */

public class BJ1759 { // 메모리 : 13944kb, 시간 : 96ms
    static int l;
    static int c;
    static char[] input;
    static char[] picked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        input = new char[c];
        picked = new char[l];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < c; i++) {
            input[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(input);
        combi(0, 0, 0, 0);


    }

    private static void combi(int depth, int index, int vowel, int consanant) {
        if(depth == l) {
            if(vowel > 0 && consanant > 1) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < l; i++) {
                    sb.append(picked[i]);
                }
                System.out.println(sb);
            }
            return;
        }

        for(int i = index; i < c; i++) {
            picked[depth] = input[i];
            if(picked[depth] == 'a' || picked[depth] == 'e' || picked[depth] == 'i' || picked[depth] == 'o' || picked[depth] == 'u')
                combi(depth+1, i+1, vowel+1, consanant);
            else
                combi(depth+1, i+1, vowel, consanant+1);
        }
    }
}
