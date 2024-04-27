package Jo_Seongjeong.Study_15주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 21939 문제 추천 시스템 Version 1
 *
 * 조건
 * 문제 리스트에 있는 문제의 개수 문제의 개수 N : 1 ~ 100000
 * 문제 번호 P : 1 ~ 100000
 * 명령문의 개수 M : 1 ~ 10000
 * 난이도 L : 1 ~ 100
 * 명령어 종류
 * recommend x
 *  x = 1, 추천 문제 리스트에서 가장 어려운 문제의 번호 출력
 *  만약 가장 어려운 문제가 여러 개라면 문제 번호가 큰 것으로 출력
 *  x = -1, 가장 쉬운 문제의 번호 출력
 *  만약 가장 쉬운 문제가 여러 개라면 문제 번호가 작은 것으로 출력
 * add P L
 *  난이도 L인 문제 번호 P를 추가
 *  추천 문제 리스트에 없는 문제 번호 P만 주어짐
 *  이전 추천 문제 리스트에 있던 문제 번호가 다른 난이도로 다시 들어울 수 있음
 * solved P
 *  문제 번호 P를 제거
 *  추천 문제 리스트에 있는 문제 번호 P만 주어짐
 * recommend는 리스트에 문제가 하나 이상 있을 때만 주어짐
 * solved는 리스트에 번호가 하나 이상 있을 때만 추어짐
 *
 * 문제에서 구하고자 하는 것
 * recommend 명령이 주어질 때마다 문제 번호 출력
 *
 * 문제 해결 프로세스
 * 우선순위 큐 2개
 * 어려운 문제 순
 * 쉬운 문제 순
 * 해당 문제가 리스트에 있는 것인지 확인
 * 방문 배열 -> 행 : 문제 번호, 열 : 문제 난이도
 * true인 것은 들어있고, false인 것은 없는 것
 * command에 따라 작업
 *
 * 고려한 시간 복잡도
 * 10000 * 1000 = 10000000
 * */

public class BJ21939 { // 메모리 : 64748kb, 시간 : 540ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> easy = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1]-b[1];
        });

        PriorityQueue<int[]> hard = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return b[0] - a[0];
            return b[1]-a[1];
        });

        boolean[][] isChecked = new boolean[100001][101];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            isChecked[num][level] = true;

            easy.offer(new int[] {num, level});
            hard.offer(new int[] {num, level});
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if(command.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                for(int j = 1; j < isChecked[num].length; j++) {
                    if(isChecked[num][j]) {
                        isChecked[num][j] = false;
                        break;
                    }
                }

                isChecked[num][level] =true;
                easy.offer(new int[] {num, level});
                hard.offer(new int[] {num, level});
            }
            else if(command.equals("solved")) {
                int num = Integer.parseInt(st.nextToken());

                for(int j = 1; j < isChecked[num].length; j++) {
                    if(isChecked[num][j]) {
                        isChecked[num][j] = false;
                        break;
                    }
                }
            }
            else {
                int sel = Integer.parseInt(st.nextToken());
                // 어려운 거
                if(sel == 1) {
                    while(!hard.isEmpty()) {
                        int[] temp = hard.poll();
                        if(isChecked[temp[0]][temp[1]]) {
                            sb.append(temp[0] + "\n");
                            hard.offer(temp);
                            break;
                        }
                    }
                }
                // 쉬운 거
                else {
                    while(!easy.isEmpty()) {
                        int[] temp = easy.poll();
                        if(isChecked[temp[0]][temp[1]]) {
                            sb.append(temp[0] + "\n");
                            easy.offer(temp);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
