package Jo_Seongjeong.Study_33주차;

import java.util.ArrayDeque;
import java.util.Queue;

public class ProMakeSameSumOfTwoQueue {
    static Queue<Integer> q1;
    static Queue<Integer> q2;
    static long sum1;
    static long sum2;
    static long goal;

    public static void main(String[] args) {
        int[] queue1 = new int[]{3, 2, 7, 2};
        int[] queue2 = new int[]{4, 6, 5, 1};

        int answer = solution(queue1, queue2);

        System.out.println(answer);
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();

        for(int i : queue1) {
            q1.offer(i);
            sum1 += i;
        }

        for(int i : queue2) {
            q2.offer(i);
            sum2 += i;
        }

        goal = (sum1 + sum2) / 2;

        answer = findByNum(q1.size() + q2.size());

        return answer;
    }

    private static int findByNum(int limit) {
        int count = 0;

        while(count < 2 * limit) {

            if(sum1 == goal) return count;

            else if(sum1 < goal && sum2 < goal) return -1;

            else if(sum1 > goal) {
                int cur = q1.poll();
                sum1 -= cur;
                sum2 += cur;
                q2.offer(cur);
                count++;
            }

            else if(sum2 > goal) {
                int cur = q2.poll();
                sum2 -= cur;
                sum1 += cur;
                q1.offer(cur);
                count++;
            }
        }

        return -1;
    }

}
