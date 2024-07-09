package Jo_Seongjeong.Study_22주차;

import java.util.*;

/**
 * 프로그래머스 N-Queen
 *
 * 조건
 * 체스판 n*n : 1 ~ 144
 * 퀸 개수 n : 1 ~ 12
 *
 * 문제에서 구하고자 하는 것
 * n개의 퀸이 한 번에 서로를 공격할 수 없게 배치하는 경우의 수
 *
 * 문제 해결 프로세스
 * dfs
 * 1행부터 n행까지 놓기
 * 열 배열을 만들자
 * 같은 행, 같은 열, 같은 대각에는 못 놓음
 * 하나 놓고 해당 열에 놓은 것 표시
 * 행 체크 : 이건 한 행에 하나 놓고 dfs 들어가니 체크 안 해도 됨
 * 열 체크 : 놓았던 표시가 있는 지 확인
 * 대각 체크 : 행의차 = 열의차로 놓았던 표시가 있는 지 확인
 *
 * 고려한 시간 복잡도
 *
 */

class ProNQueen {
    static int[] columns;
    static int size;
    static int num;

    public static void main(String[] args) {
        int n = 4;

        int result = solution(n);

        System.out.println(result);
    }

    public static int solution(int n) {
        int answer = 0;
        size = n+1;
        columns = new int[size];

        dfs(1);
        answer = num;

        return answer;
    }

    private static void dfs(int row) {
        if(size == row) {
            num++;
            return;
        }

        for(int i = 1; i < size; i++) {
            if(!isValid(row, i)) continue;
            columns[row] = i;
            dfs(row+1);
            columns[row] = 0;
        }

    }

    private static boolean isValid(int row, int col) {
        // 대각 체크
        for(int i = 1; i < row; i++) {
            if(columns[i] == col) {
                return false;
            }
            if(Math.abs(columns[i] - col) == Math.abs(i - row)) return false;
        }

        return true;
    }
}
