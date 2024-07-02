package Jo_Seongjeong.Study_8주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 5635 생일
 *
 * 조건
 * 학생 수 n : 1 ~100
 * 이름이 같거나 생일이 같은 사람  x
 *
 * 문제에서 구하고자 하는 것
 * 나이가 가장 적은 사람과 많은 사람 구하기
 *
 * 문제 해결 프로세스
 * 정렬 입력의 제일 마지막 기준 오름차순, 같으면 그 앞, 같으면 그 앞
 * 인덱스 0번, n-1번 출력
 *
 * 고려한 시간 복잡도
 * 100log100
 * */

public class BJ5635 { // 메모리 : 18092kb, 시간: 220ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        String[][] array = new String[n][4];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                array[i][j] = st.nextToken();
            }
        }

        Arrays.sort(array, (a, b) -> {
            int year1 = Integer.parseInt(a[3]);
            int year2 = Integer.parseInt(b[3]);
            if(year1 == year2) {
                int month1 = Integer.parseInt(a[2]);
                int month2 = Integer.parseInt(b[2]);
                if(month1 == month2)
                    return Integer.parseInt(a[1]) - Integer.parseInt(b[1]);
                return month1 - month2;
            }
            return year1 - year2;
        });

        System.out.println(array[n-1][0]);
        System.out.println(array[0][0]);
    }
}
