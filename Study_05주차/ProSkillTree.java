package Jo_Seongjeong.Study_5주차;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 프로그래머스 스킬트리
 *
 * 조건
 * 선행스킬 : 어떤 스킬을 배우기 전, 먼저 배워야 하는 스킬
 * 순서가 있는 스킬은 선행 스킬을 배운 상태여야 배울 수 있음
 * 순서에 없는 스킬은 순서와 상관없이 배울 수 있음
 * 스킬 : 알파벳 대문자
 * 선행 스킬 순서의 길이 : 1 ~ 26(중복X)
 * 유저가 만든 스킬 트리 배열 skill_trees : 1 ~ 20
 * 배열 원소 길이 : 2 ~ 26, 중복X
 *
 * 문제에서 구하고자 하는 것
 * skill_trees 배열 중 실제 가능한 skill tree의 개수
 *
 * 문제 해결 프로세스
 * 스킬 트리 배열의 원소를 글자로 나누어 큐에 저장
 * 현재 배울 수 있는 스킬 트리를 체크할 26 길이의 배열 생성 (알파벳 순)
 *  -> A <- A - 65
 *  선행 스킬 트리인 경우 해당 배열 인덱스값은 true
 *  선행 스킬 트리의 순서를 알아야 하므로, queue에다 저장
 *  false 인 경우만 배울 수 있음
 *  만약 true인 경우, queue.peek -> 같으면 false로 변환
 *  반복하다 스킬 트리 배열의 원소 큐 size가 0이면 answer++
 *  다르면 break해서 다음으로 넘어감
 *
 *  고려한 시간 복잡도
 *  26(스킬트리 원소 개수) * 26(스킬 트리 원소의 길이) = 676
 * */

public class ProSkillTree {
    static Queue<Character> preSkill;
    static Queue<Character> candidate;
    static boolean[] isChecked;
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = new String[] {"CED","BACDE", "CBADF", "AECB", "BDA"};
        int answer = solution(skill, skill_trees);
        System.out.println(answer);
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i = 0; i < skill_trees.length; i++) {
            String str = skill_trees[i];
            candidate = new ArrayDeque<>();

            for(int j = 0; j < str.length(); j++) {
                candidate.offer(str.charAt(j));
            }

            preSkill = new ArrayDeque<>();
            isChecked = new boolean[26];
            for(int j = 0; j < skill.length(); j++) {
                preSkill.offer(skill.charAt(j));
                isChecked[skill.charAt(j)-65] = true;
            }

            while(!candidate.isEmpty()) {
                char ch = candidate.poll();

                if(!isChecked[ch-65]) continue;

                char order = preSkill.peek();

                if(ch == order) {
                    isChecked[order-65] = false;
                    preSkill.poll();
                }
                else {
                    candidate.offer(ch);
                    break;
                }
            }

            if(candidate.size() == 0) {
                answer++;
            }
        }
        return answer;
    }
}
