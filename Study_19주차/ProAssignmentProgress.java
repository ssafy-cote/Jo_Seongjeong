package Jo_Seongjeong.Study_19주차;

import java.util.*;

/**
 * 프로그래머스 과제 진행하기
 *
 * 조건
 * 과제 계획
 * 각 과제는 시작하기로 한 시간이 되면 시작
 * 새로운 과제를 시작할 시간이 되면, 기존 진행 중이던 과제가 있는 경우 멈추고 새 과제 시작
 * 진행중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춘 과제를 이어서 진행
 * 만약 과제를 끝낸 시간에 새로 시작해야되는 과제와 잠시 멈춘 과제가 모두 있다면 새로운 과제가 우선
 * 멈춘 과제가 여러개이면, 가장 최근에 멈춘 과제부터 시작
 * 과제의 개수 : 3 ~ 1000
 * name : 과제의 이름 (2 ~ 10, 알파벳 소문자로만, 중복 x)
 * start : 과제 시작 시간 (hh : mm 형태)
 * 과제는 00:00 ~ 23:59 순으로 시작하면 됨
 * playtime : 과제 수행 시간, 단위는 분 (1 ~ 100)
 * 진행중이던 과제가 끝나는 시간과 새로운 과제를 시작해야하는 시간이 같은 경우, 진행중이던 과제는 끝으로 판단
 *
 * 문제에서 구하고자 하는 것
 * 과제가 끝난 순서
 *
 * 문제 해결 프로세스
 * 우선순위 큐를 두개 만들어보자
 * 한 번도 과제를 하지 않은 우선순위 큐 : 시간 오름차순
 * 중간에 끊긴 과제는 스택
 *
 * 고려한 시간 복잡도
 * 1000 * 2
 */

public class ProAssignmentProgress {
    public static void main(String[] args) {
        String[][] plans = {
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"}
        };

        String[] answer = solution(plans);

        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[][] plans) {
        String[] answer = {};
        PriorityQueue<String[]> assignment = new PriorityQueue<> ((a, b) -> {
            int hour1 = Integer.parseInt(a[1].substring(0, 2));
            int hour2 = Integer.parseInt(b[1].substring(0, 2));
            int minute1 = Integer.parseInt(a[1].substring(3, 5));
            int minute2 = Integer.parseInt(b[1].substring(3, 5));

            if(hour1 == hour2) return minute1 - minute2;

            return hour1 - hour2;
        });

        for(String[] row : plans) {
            assignment.offer(new String[]{row[0], row[1], row[2]});
        }

        List<String> list = new ArrayList<>();
        Deque<String[]> remain = new ArrayDeque<>();

        int idx = 0;
        while(!assignment.isEmpty()) {
            String[] current = assignment.poll();
            int playTime = Integer.parseInt(current[2]);

            if(assignment.isEmpty()) {
                list.add(current[0]);
                break;
            }

            String[] next = assignment.peek();

            int currentH = Integer.parseInt(current[1].substring(0, 2));
            int nextH = Integer.parseInt(next[1].substring(0, 2));
            int currentM = Integer.parseInt(current[1].substring(3, 5));
            int nextM = Integer.parseInt(next[1].substring(3, 5));

            int time = (nextH - currentH) * 60 + (nextM - currentM);

            // 두 시차가 현재 과제 시간과 같은 경우
            if(time == playTime) list.add(current[0]);

                // 두 시차가 현재 과제 시간보다 작은 경우 -> 중간에 그만 둬야 함
            else if(time < playTime) remain.push(new String[] {current[0], current[1], (playTime - time) + ""});

                // 두 시차가 현재 과제 시간보다 큰 경우
            else {
                list.add(current[0]);

                time -= playTime;

                if(!remain.isEmpty()) {
                    String[] temp = remain.pop();
                    int nextPlay = Integer.parseInt(temp[2]);

                    if(time == nextPlay) list.add(temp[0]);
                    else if(time < nextPlay) remain.push(new String[] {temp[0], temp[1], (nextPlay - time) + ""});
                    else {
                        while(true) {
                            if(time - nextPlay == 0) {
                                list.add(temp[0]);
                                break;
                            }
                            else if(time - nextPlay < 0) {
                                remain.push(new String[] {temp[0], temp[1], (nextPlay-time)+""});
                                break;
                            }
                            else {
                                time -= nextPlay;
                                list.add(temp[0]);
                                if(remain.isEmpty()) break;
                                temp = remain.pop();
                                nextPlay = Integer.parseInt(temp[2]);
                            }
                        }
                    }
                }
            }
        }

        while(!remain.isEmpty()) {
            String[] temp = remain.pop();
            list.add(temp[0]);
        }

        answer = list.toArray(new String[list.size()]);

        return answer;
    }
}
