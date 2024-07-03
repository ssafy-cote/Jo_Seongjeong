package Jo_Seongjeong.Study_21주차;
import java.util.*;

/**
 * 프로그래머스 광물 캐기
 *
 * 조건
 *
 * 피로도 : 어떤 곡괭이로 어떤 광물을 캘 때 생기는 수치
 * 한 곡괭이는 연속 5번 사용하고 버려야 함
 * 광물은 순서대로 캘 수 있음
 * 모든 광물을 캐거나, 더 이상 사용할 곡괭이가 없을 때까지 진행
 * 각 곡괭이의 개수 : 0 ~ 5
 * picks : 다이아, 철, 스톤 순서의 배열 : 각 배열값은 개수
 * minerals : 광물 종류
 * 광물 개수 : 5 ~ 50

 * 문제에서 구하고자 하는 것
 * 광뭉 캐기를 진행했을 때 피로도의 최소값
 *
 * 문제 해결 프로세스
 * 광물 종류에 따라 피로도를 다르게 하자
 * 다이아 : 3, 철 : 2, 돌 : 1
 * 5개씩 더해서 우선순위 큐에 넣자(배열 형태로, 0은 순서, 1은 피로도, 2~4는 광물개수(다이아, 철, 돌) -> 우선순위는 피로도 순)
 * 순서 > picks 합 : 큐에 나오는 순으로 picks 하나씩 꺼내자, 피로도 계산
 * else : 뽑았을 때, picks합보다 큰 순서는 버리고 위와 같이 진행
 *
 * 고려한 시간 복잡도
 * 50 + 10*3 = 80
 **/

public class ProMineCraft {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        int i = 0;
        int order = 1;
        while(i < minerals.length) {
            int dia = 0;
            int iron = 0;
            int stone = 0;
            int score = 0;
            for(int j = i; j < i+5; j++) {
                if(j == minerals.length) break;

                if(minerals[j].equals("diamond")) {
                    dia++;
                    score += 25;
                }
                else if(minerals[j].equals("iron")) {
                    iron++;
                    score += 5;
                }
                else {
                    stone++;
                    score++;
                }
            }
            pq.offer(new int[]{order++, score, dia, iron, stone});
            i+=5;
        }

        int size = pq.size();
        int num = picks[0] + picks[1] + picks[2];
        while(!pq.isEmpty()) {
            int[] temp = pq.poll();

            if(temp[0] > num) continue;

            if(picks[0] > 0) {
                picks[0] --;
                answer += (temp[2] + temp[3] + temp[4]);
            }
            else if(picks[1] > 0) {
                picks[1] --;
                answer += (temp[2]*5 + temp[3] + temp[4]);
            }
            else {
                picks[2] --;
                answer += (temp[2] * 25 + temp[3] * 5 + temp[4]);
            }
        }

        return answer;
    }
}
