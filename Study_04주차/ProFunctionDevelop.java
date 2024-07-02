package Jo_Seongjeong.Study_4주차;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ProFunctionDevelop {
    public static int[] solution(int[] progresses, int[] speeds) {
        int idx = 0;
        int count = 0;
        boolean[] isChecked = new boolean[progresses.length];
        List<Integer> list = new ArrayList<>();

        while(true) {
            Queue<Integer> queue = new ArrayDeque<>();
            for(int i = 0; i < progresses.length; i++) {
                if(isChecked[i]) continue;
                progresses[i] += speeds[i];
                queue.add(progresses[i]);
            }

            if(queue.size() == 0) break;

            while(true) {
                if (queue.peek() >= 100) {
                    queue.poll();
                    count++;
                    isChecked[idx++] = true;

                    if(queue.size() == 0) break;
                }
                else break;
            }

            if(count > 0) {
                list.add(count);
                count = 0;
            }
        }

        int[] answer = new int[list.size()];

        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
