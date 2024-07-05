package Jo_Seongjeong.Study_20주차;

import java.util.*;

/**
 *
 * 프로그래머스 혼자 놀기의 달인
 *
 * 조건
 * 카드 : 100장(1~100)
 * 1. 2 이상 100 이하의 자연수를 하나 정함
 * 2. 정한 수보다 작거나 같은 숫자 카드들로 카드 준비 + 고른 개수만큼 상자 준비 (입력)
 * 3. 상자 나열 순서에 따라 번호 붙임
 * 4. 상자 선택해 카드 확인 -> 카드 번호에 해당하는 숫자 엶 -> 이미 열려있느 상자 고를 때까지 반복
 * 5. 1번 그룹 생성, 만약 1번 그룹 제외하고 상자가 없으면 게임 종료, 0점
 * 6. 반복 후 2번 그룹 생성
 * 7. 1번 그룹 상자 수 * 2번 그룹 상자 수 = 게임 점수
 *
 * 문제에서 구하고자 하는 것
 * 게임에서 얻을 수 있는 최고 점수
 *
 * 문제 해결 프로세스
 * dfs로 풀기
 * 1. 1라운드에 가능한 상자를 뽑아보자
 * 2. 뽑은 상자의 다음이 이미 뽑은거라면 라운드 종료
 * 3. 2라운드 시작
 * 4. 마찬가지로 종료되면 최대값 갱신
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 2 = 20000
 *
 */

class ProMasterPalyingAlone {
    static int size;
    static boolean[] visited;
    static int group2;
    static int max;

    public static void main(String[] args) {
        int[] cards = new int[]{8,6,3,7,2,5,1,4};

        int result = solution(cards);

        System.out.println(result);
    }

    public static int solution(int[] cards) {
        int answer = 0;
        size = cards.length;
        for(int i = 0; i < size; i++) {
            visited = new boolean[size];
            dfs(i, cards, 0);
        }

        answer = max;
        return answer;
    }

    private static void dfs(int start, int[] cards, int num) {
        if(visited[start]) {
            // System.out.println(num);
            for(int i = 0; i < size; i++) {
                if(visited[i]) continue;
                dfs2(i, cards, 0);
                max = Math.max(max, group2 * num);
            }
            return;
        }
        visited[start] = true;
        dfs(cards[start]-1, cards, num+1);
        visited[start] = false;
    }

    private static void dfs2(int start, int[] cards, int num) {
        if(visited[start]) {
            group2 = num;
            // System.out.println(group2);
            return;
        }
        visited[start] = true;
        dfs2(cards[start]-1, cards, num+1);
        visited[start] = false;
    }
}