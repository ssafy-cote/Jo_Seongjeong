package Jo_Seongjeong.Study_12주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1497 기타콘서트
 *
 * 조건
 * 기타의 개수 N : 1 ~ 10
 * 곡의 개수 M : 1 ~ 50
 * Y : 연주할 수 있는 곡의 번호(글자순서 순)
 * N : 연주 불가한 곡의 번호(글자순서 순)
 * 기타의 이름 : 알파벳 대문자, 2 ~ 50
 * 두 기타의 이름이 같은 경우는 없음
 * 연주할 수 있는 곡이 없으면 -1 출력
 *
 *
 * 문제에서 구하고자 하는 것
 * 최대한 많은 곡을 제대로 연주하려고 할 때, 필요한 기타의 최소 개수
 *
 * 문제 해결 프로세스
 * 기타에 대한 부분집합
 * 부분집합에 대해 연주 가능지한 곡 확인
 * 최대값 갱신
 *
 * 고려한 시간 복잡도
 *  2^10 * 50 * 50 = 2,560,000
 * */

public class BJ1497 { // 메모리 : 12568ms, 시간 : 108ms
    static int n;
    static int m;
    static boolean[] isChecked;
    static String[][] array;
    static int min = Integer.MAX_VALUE;
    static int song = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new String[n][2];
        isChecked = new boolean[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                array[i][j] = st.nextToken();
            }
        }

        subset(0);
        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    private static void subset(int index) {
        if(index == n) {
            List<String[]> list = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if(!isChecked[i]) continue;

                list.add(new String[] {array[i][0], array[i][1]});
            }

            if(list.size() == 0) return;

            int count = serchPlay(list);

            if(count == 0) return;

            if(song < count) {
                song = count;
                min = list.size();
            }
            else if(song == count)  {
                if(min > list.size()) {
                    min = list.size();
                }
            }
            return;
        }

        isChecked[index] = true;
        subset(index+1);
        isChecked[index] = false;
        subset(index+1);

    }

    private static int serchPlay(List<String[]> list) {
        int count = 0;
        boolean[] play = new boolean[m];

        for(String[]row : list) {
            for(int i = 0; i < m; i++) {
                if(play[i]) continue;
                if(row[1].charAt(i) == 'N') continue;
                play[i] = true;
                count++;
            }
        }

        return count;

    }

}
