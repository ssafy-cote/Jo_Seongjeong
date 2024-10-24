package Jo_Seongjeong.Study_34주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2138 전구와 스위치
 *
 * 조건
 * 스위치, 전구 개수 n : 2 ~ 100000
 * 켜진상태 : 0
 * 꺼진 상태 : 1
 * i 스위치 누르면, i-1, i, i+1 상태 반전
 *
 * 문제에서 구하고자 하는 것
 * 처음 상태에서 마지막 상태로 갈 때 누른 최소 횟수
 *
 * 문제 해결 프로세스
 * 그리디
 * 현재 전구의 최종 상태는 다음 스위치에 의해 결정
 * 1번 전구가 켜진 경우, 꺼진 경우로 놓고 각각 끝까지 진행
 * 이전 전구가 정답이랑 다른경우, 현재 스위치 누른다
 * 어차피 누르던 안 누르던, 한번 쭉 가면서 만들어보는게 답이 될 것 (최소)
 * 마지막 상태가 같으면 가능, 아니면 불가능, 둘 다 가능한 경우면 최소값으로 답 구하기
 *
 * 고려한 시간 복잡도
 * 100000
 * */

public class BJ2138 { // 메모리 : 40860kb, 시간 : 224ms
    static int[] answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String init = br.readLine();
        String last = br.readLine();
        int[] firstOn = new int[n];
        int[] firstOff = new int[n];
        answer = new int[n];

        for(int i = 0; i < n; i++) {
            firstOn[i] = Integer.parseInt(init.charAt(i)+"");
            firstOff[i] = firstOn[i];
            answer[i] = Integer.parseInt(last.charAt(i)+"");
        }

        int count1 = 0;
        int count2 = 0;
        if(firstOn[0] == 0) {
            count1 = makeSame(0, firstOn);
            firstOff[0] = 1 - firstOff[0];
            firstOff[1] = 1 - firstOff[1];
            count2 = makeSame(1, firstOff);
        }
        else {
            firstOn[0] = 1 - firstOn[0];
            firstOn[1] = 1 - firstOn[1];
            count1 = makeSame(1, firstOn);
            count2 = makeSame(0, firstOff);
        }

        if(count1 == -1  && count2 == -1) System.out.println(-1);
        else if(count1 == -1) System.out.println(count2);
        else if(count2 == -1) System.out.println(count1);
        else System.out.println(Math.min(count1, count2));
    }

    private static int makeSame(int count, int[] array) {
        for(int i = 1; i < array.length; i++) {
            if(array[i-1] == answer[i-1]) continue;
            count++;
            array[i-1] = 1-array[i-1];
            array[i] = 1 - array[i];
            if(i < array.length-1) array[i+1] = 1 -array[i+1];
        }

        if(array[array.length-1] == answer[array.length-1]) return count;
        else return -1;
    }
}
