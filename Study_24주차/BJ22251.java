package Jo_Seongjeong.Study_24주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 22251 빌런 호석
 *
 * 조건
 * 이용 가능한 꼭대기 층 n : 1 ~ 1000000
 * 디스플레이 자릿수 k : 1 ~ 6
 * LED 중 반전 가능한 개수 p : 1 ~ 42
 * 엘리베이터 현재 층 x : 1 ~ 1000000
 * 디스플레이 표시된 수는 0으로 시작할 수 있음
 * 표시등 개수 : 7 -> 0 ~ 9 표현 가능
 *
 * 문제에서 구하고자 하는 것
 * 호석이가 반전시킬 수 있는 경우의 수
 *
 * 문제 해결 프로세스
 * 각 숫자에 대해, LED 상태 배열 만들기
 * 0 : 0, 1, 2, 3, 4, 5
 * 1 :    1, 2
 * 2 : 0, 1,    3, 4,    6
 * 3 : 0, 1, 2, 3,       6
 * 4 :    1, 2,       5, 6
 * 5 : 0,    2, 3,    5, 6
 * 6 : 0,    2, 3, 4, 5, 6
 * 7 : 0, 1, 2
 * 8 : 0, 1, 2, 3, 4, 5, 6
 * 9 : 0, 1, 2, 3,    5, 6
 * 0 -> 1, 0 -> 2, ... , 9 -> 8에 대한 LED 반전 개수를 저장하는 배열 생성
 * boolean 배열 활용해서 가능한 개수 저장
 * [10][10] i -> j로 변할 때 반전되는 led 수 구하기
 * 1층부터 n층까지 반전되는 led 개수 count해 비교
 *
 * 고려한 시간 복잡도
 * 6 * 100 + 1000000 * 6 = 6000600
 * */

public class BJ22251 { // 메모리 : 12716kb, 시간 : 160ms
    static int n;
    static int k;
    static int p;
    static int x;
    static boolean[][] led;
    static int[][] num;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        led = new boolean[10][7];
        num = new int[10][10];

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 7; j++) {
                if(j == 0) {
                    if(i == 1 || i == 4) continue;
                }
                else if(j == 1) {
                    if(i == 5 || i == 6) continue;
                }

                else if(j == 2) {
                    if(i == 2) continue;
                }
                else if(j == 3) {
                    if(i == 1 || i == 4 || i == 7) continue;
                }
                else if(j == 4) {
                    if(i == 1 || i == 3 || i == 4 || i == 5 || i == 7 || i == 9) continue;
                }
                else if(j == 5) {
                    if(i == 1 || i == 2 || i == 3 || i == 7) continue;
                }
                else {
                    if(i == 0 || i == 1 || i == 7) continue;
                }
                led[i][j] = true;
            }
        }


        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(i == j) continue;
                num[i][j] = changeCount(i, j);
            }
        }

        for(int i = 1; i <= n; i++) {
            if(i == x) continue;

            int index = 1;
            int count = 0;
            for(int j = 0; j < k; j++) {
                int current = i / index % 10; // 자리수에 해당하는 수 구하기
                int start = x / index % 10;
                count += num[start][current];
                index *= 10;
            }
            if(count <= p) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static int changeCount(int current, int next) {
        int count = 0;
        for(int i = 0; i < 7; i++) {
            if(led[current][i] == led[next][i]) continue;
            count++;
        }

        return count;
    }
}
