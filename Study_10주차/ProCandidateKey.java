package Jo_Seongjeong.Study_10주차;

import java.util.ArrayList;
import java.util.List;

/**
 프로그래머스 후보키

 조건
 후보키 : 유일성과 최소성을 모두 만족하는 attribute
 relation은 2차원 배열
 열의 길이 : 1 ~ 8
 행의 길이 : 1 ~ 20, 튜플 -> 행 단위에서 중복되는 행은 없음
 문자열의 길이 : 1 ~ 8

 문제에서 구하고자 하는 것
 후보키의 최대 개수

 문제 해결 프로세스
 relation = table로 보자
 각 열에 대한 조합으로 뽑기
 열C1 ~ 열C열까지 뽑자
 1부터 뽑는데 해당 길이로 다 뽑고, 중복된 tuple이 아니라면 해당 컬럼을 true 처리
 다음 길이의 조합으로 갈 때 true인 열은 뽑지 않는다

 고려한 시간 복잡도
 8C4 = 70 제일 긴 조합 뽑기
 70 * 8 = 560 최악의 조합 시간 복잡도
 560 * 20(행) * 5(평균 행 개수 대충) = 5600 충분히 빨라보임

 */

class ProCandidateKey {
    static int colLen;
    static int depth;
    static int[] picked;
    static List<int[]> list;

    public static void main(String[] args) {
        String[][] relation = new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        int ans = solution(relation);
        System.out.println(ans);
    }

    public static int solution(String[][] relation) {
        int answer = 0;
        colLen = relation[0].length;
        list = new ArrayList<>();

        for(int i = 1; i <= colLen; i++) {
            picked = new int[i];
            depth = i;
            combi(0, 0, 0, relation);
        }

        answer = list.size();
        return answer;
    }
    private static void combi(int index, int current, int temp, String[][] relation) {
        if(depth == temp) {
            // 조합 잘 뽑힌 것 확인함

            String[][] candidate = new String[relation.length][depth];

            for(int i = 0; i < relation.length; i++) {
                for(int j = 0; j < depth; j++) {
                    candidate[i][j] = relation[i][picked[j]];
                }
            }

            boolean flag = false;
            A :for(int i = 0; i < relation.length-1; i++) {
                for(int k = i+1; k < relation.length; k++) {
                    int count = 0;
                    for(int j = 0; j < depth; j++) {
                        if(candidate[i][j].equals(candidate[k][j])) count++;
                        else break;
                    }
                    if(count==depth) {
                        flag=true;
                        break A;
                    }
                }
            }

            if(!flag) {
                boolean bl = false;
                if(!list.isEmpty()) {
                    B: for(int i = 0; i < list.size(); i++) {
                        int cnt = 0;
                        for(int j = 0; j < list.get(i).length; j++) {
                            for(int k = 0; k < picked.length; k++) {
                                if(list.get(i)[j] == picked[k]) cnt++;
                            }
                        }
                        if(cnt == list.get(i).length) {
                            bl = true;
                            break B;
                        }
                    }
                }
                if(!bl) {
                    list.add(picked.clone());
                }
            }
            return;
        }
        for(int i = current; i < colLen; i++) {
            picked[index] = i;
            combi(index+1, i+1, temp+1, relation);
        }
    }
}
