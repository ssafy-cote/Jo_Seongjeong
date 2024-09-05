package Jo_Seongjeong.Study_31주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 20055 컨베이어 벨트 위의 로봇
 *
 * 조건
 * 컨베이어 벨트 n : 2 ~ 100
 * 벨트 2n : 4 ~ 200
 * 벨트 길이 : 1
 * 칸 개수 : 2n
 * 내구도 Ai : 1 ~ 1000
 * 벨트는 순환
 * 올리는 위치 : 0, 0
 * 내리는 위치 : 0, n-1
 * 로봇은 올리는 위치에서만 올릴 수 있음
 * 로봇이 내리는 위치에 도착하면 즉시 내림
 * 로봇은 컨베이어 벨트 위에서 이동 가능
 * 로봇을 올리거나 어떤 칸으로 이동 시, 해당 칸 내구도 -1
 * 과정
 * 1. 벨트 회전 (로봇이 있는 경우 함께)
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 1칸 이동
 *  이동 가능하지 못하면, 가만히 있음
 *  이동 가능 여부 : 이동 후 칸에 로봇이 없으며, 칸의 내구도가 1 이상인 경우
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면, 로봇 올림
 * 4. 내구도가 0인 칸의 개수가 k이상이면 종료, 아니면 1번으로 돌아감
 *
 * 문제에서 구하고자 하는 것
 * 종료되었을 때, 몇번째 반복인지, 가장 처음은 1
 *
 * 문제 해결 프로세스
 * 배열돌리기처럼, 단계 반복하며 구해보자
 *
 * 고려한 시간 복잡도
 * 1000 * 100 * 1000 = 100000000
 * */

public class BJ20055 { // 메모리 : 15552kb, 시간 : 224ms
    static int n;
    static int k;
    static int[][] map;
    static boolean[][] isChecked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[2][n];
        isChecked = new boolean[2][n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            else {
                for(int j = n-1; j >= 0; j--) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int answer = moveBelt();

        System.out.println(answer);

    }

    private static int moveBelt() {
        int count = 1;
        int zero = 0;

        while(zero < k) {
            // 회전
            rotation();

            // 내리는 위치 확인
            if(isChecked[0][n-1]) isChecked[0][n-1] = false;

            // 로봇 이동
            for(int i = n-2; i >= 1; i--) {
                if(!isChecked[0][i]) continue;
                if(isChecked[0][i+1]) continue;
                if(map[0][i+1] < 1) continue;

                isChecked[0][i] = false;
                isChecked[0][i+1] = true;
                map[0][i+1]--;

                if(map[0][i+1] == 0) zero++;
            }

            if (isChecked[0][n - 1]) isChecked[0][n - 1] = false;

            // 로봇 올리기
            if(map[0][0] > 0) {
                isChecked[0][0] = true;
                map[0][0]--;

                if(map[0][0] == 0) zero++;
            }

            // 과정 종료
            if(zero >= k) break;

            count++;
        }

        return count;
    }

    private static void rotation() {
        int index = map[0][n-1];
        boolean temp = isChecked[0][n-1];

        for(int i = n-1; i >= 1; i--) {
            map[0][i] = map[0][i-1];
            isChecked[0][i] = isChecked[0][i-1];
        }

        map[0][0] = map[1][0];
        isChecked[0][0] = isChecked[1][0];

        for(int i = 0; i < n-1; i++) {
            map[1][i] = map[1][i+1];
        }

        map[1][n-1] = index;
        isChecked[1][n-1] = temp;
    }
}
