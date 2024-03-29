package Jo_Seongjeong.Study_11주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14719 빗물
 *
 * 조건
 * 세로길이(행) H : 1 ~ 500
 * 가로길이(열) W : 1 ~ 500
 * 바닥은 항상 막혀 있음
 * 배열 한 칸의 용량 = 1
 * 블록 내부의 빈 공간이 생길 수 x
 *
 * 문제에서 구하고자 하는 것
 * 고이는 빗물의 총량, 없으면 0
 *
 * 문제 해결 프로세스
 * 블록이 있는 곳을 true로, 없는 곳을 false로해 맵을 입력 받자
 * 마지막 행은 모두 true로 채워놓기
 * 마지막 행-1부터(열은 정방향) false인 곳을 찾자
 * false인 경우
 * 1. 아래가 true인지
 * 2. 좌, 우에 true가 모두 있는지 확인
 * 있으면 고일 수 있는 자리이므로 cnt++, 해당 자리 true 처리
 *
 * 고려한 시간 복잡도
 * 500 * 500 * 2 * 500 = 2억 정도인데 이정도는 안될듯(최악)
 * */

public class BJ14719 { // 메모리 : 12400kb, 시간 : 116ms
    static int h;
    static int w;
    static int count = 0;
    static int[] delta = new int[]{-1, 1};
    static boolean[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new boolean[h+1][w];

        for(int i = 0; i < w; i++) {
            map[h][i] = true;
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < w; i++) {
            int len = Integer.parseInt(st.nextToken());

            for(int j = h; j >= h-len; j--) {
                map[j][i] = true;
            }
        }

        for(int i = h-1; i >= 0; i--) {
            for(int j = 0; j < w; j++) {
                if(map[i][j]) continue;
                checkWater(i, j);
            }
        }

        System.out.println(count);

    }
    private static void checkWater(int r, int c) {
        if(!map[r+1][c]) return;


        for(int d = 0; d < 2; d++) {
            int weight = 1;
            while(true) {
                int nc = c + weight*delta[d];
                if(nc < 0 || nc >= w) return;

                if(map[r][nc]) break;

                weight++;
            }
        }

        map[r][c] = true;
        count++;
    }
}
