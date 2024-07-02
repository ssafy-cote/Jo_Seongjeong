package Jo_Seongjeong.Study_5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1966 프린터 큐
 *
 * 조건
 * 프린터  기기의 인쇄 순서
 * 현재 Queue의 가장 앞에 있는 문서의 중요도 확인
 * 현재 Queue 중, 중요도가 가장 높으면 인쇄
 * 아니라면 Queue에 가장 뒤에 재배치
 *문서의 개수 N : 1 ~ 100
 * 타켓 문서의 번호 M : 0 ~ 99
 * 중요도 : 1 ~ 9
 * 중요도가 같은 문서는 여러개일 수 있음
 *
 * 문제에서 구하고자 하는 것
 * 타켓 문서의 인쇄 순서
 *
 * 문제 해결 프로세스
 * 배열에 순서대로 중요도 저장
 * 큐를 배열로 생성
 * 0번 인덱스에는 문서 번호(인덱스 번호)
 * 1번 인덱스에는 중요도를 저장
 * 큐가 빌 때까지 반복
 * 큐를 뽑으면 본인 꺼 제외 중요도 비교
 * 인쇄하면 중요도 0으로 바꿈
 * 만약 발견하면 큐에 다시 넣음
 *
 * 고려한 시간 복잡도
 * 100 * 100 = 10000 = 2초는 충분
 * */

public class BJ1966 { // 메모리 : 12816kb, 시간 : 104ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());

            int count = 0;
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] importance = new int[n];
            Queue<int[]> queue = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                importance[i] = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{i, importance[i]});
            }

            while(!queue.isEmpty()) {
                int[] temp = queue.poll();
                boolean flag = false;
                for(int i = 0; i < n; i++) {
                    if(i==temp[0]) continue;
                    if(importance[i] > temp[1]) {
                        flag = true;
                        break;
                    }
                }

                if(flag) queue.offer(temp);
                else {
                    count++;
                    importance[temp[0]] = 0;
                    if(temp[0] == m) break;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
