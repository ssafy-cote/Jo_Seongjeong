package Jo_Seongjeong.Study_3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1074 Z
 *
 * 조건
 * n: 1 ~ 15
 * 배열 크기 2^n * 2^n : 4  ~ 1,073,741,824
 * 배열 순회 순서 : 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸
 * n>1 인 경우 재귀적 순서
 *
 * 문제에서 구하고자 하는 것
 * 입력된 r행 c열을 몇번째로 방문하는지
 *
 * 문제 해결 프로세스
 * 재귀
 * 현재 n에 대해 2^n-1 값을 구하자 -> 더 작은 사각형으로 4등분
 * 현재 구하고자 하는 r,c값이 몇사분면인지 판단
 * 결국 4칸씩 보는 것이므로, 사분면에 따라 첫번 째 Z 시작 순서로 맞춰준다
 * 반복
 *
 * 고려한 시간 복잡도
 * 2^15 = 32,768
 * */

public class BJ1074 { // 메모리 : 11564kb, 시간 : 84ms
    static int r;
    static int c;
    static int num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        if(n > 1) findRowCol(n);
        else num = 2 * r + c;

        System.out.println(num);


    }

    private static void findRowCol(int n) {
        if(n == -1) return;

        int size = (int)Math.pow(2, n-1);

        // 2, 1, 4, 3분면 순서
        if(r < size && c < size) {

        }
        else if(r < size && c >= size) {
            num += size * size;
            c -= size;
        }
        else if(r >= size && c < size) {
            num += size * size * 2;
            r -= size;
        }
        else {
            num += size * size * 3;
            r -= size;
            c -= size;
        }
        findRowCol(n -1);
    }
}
