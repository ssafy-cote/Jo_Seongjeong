package Jo_Seongjeong.Study_4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ProFunctionDevelop_Main {
    static int n;
    static int[] progresses;
    static int[] speeds;
    static boolean[] isChecked;
    static int[] answer;
    static int count;
    static int idx = 0;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        progresses = new int[n];
        speeds = new int[n];
        isChecked = new boolean[n];
        count = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            progresses[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            speeds[i] = Integer.parseInt(st.nextToken());
        }

        while(add());


        answer = new int[list.size()];

        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        System.out.println(Arrays.toString(answer));
    }
    private static boolean add() {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(isChecked[i]) continue;
            progresses[i] += speeds[i];
            queue.add(progresses[i]);
        }

        if(queue.size() == 0) return false;

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

        return true;
    }
}
