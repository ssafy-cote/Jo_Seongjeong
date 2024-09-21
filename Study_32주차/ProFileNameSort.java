package Jo_Seongjeong.Study_32주차;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 프로그래머스 파일명 정렬 프로그래머스 파일명 정렬
 *
 * 조건
 * 파일 길이 : 1 ~ 1000
 * 파일명 길이 : 1 ~100
 * 파일명 구조 : 영대소문자, 숫자, 공백, 마침표, 빼기부호
 * head : 첫글자부터 숫자 전까지
 * number : 숫자
 * tail : 나머지
 * 정렬 순서
 * head 사전순 (대소문자 구분x)
 * number 오름차순
 * 입력 순서
 *
 * 문제에서 구하고자 하는 것
 * 파일명 정렬
 *
 * 문제 해결 프로세스
 * 처음 문자열을 읽어서 head, number, 순서로 나눠 저장하자
 * 우선순위 큐에 싹 저장
 * 큐 비우면서, 순서에 따른 file명 answer에 저장
 *
 * 고려한 시간 복잡도
 * 1000 * 100 * 3 = 300000
 */

public class ProFileNameSort {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        String[] answer = solution(files);

        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];

        String[][] part = new String[files.length][3];

        int index = 0;
        for(int i = 0; i < files.length; i++) {

            String head = "";
            String number = "";
            for(int j = 0; j < files[i].length(); j++) {
                char ch = files[i].charAt(j);

                if(ch >= 48 && ch <= 57) number += ch; // 문자 0 ~ 9
                else {
                    if(number.length() == 0) head += ch;
                    else break;
                }
            }

            part[i][0] = head.toUpperCase();
            part[i][1] = number;
            part[i][2] = index + "";
            index++;
        }

        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0].compareTo(b[0]) == 0) {
                if(Integer.parseInt(a[1]) == Integer.parseInt(b[1])) return Integer.parseInt(a[2]) - Integer.parseInt(b[2]);
                return Integer.parseInt(a[1]) - Integer.parseInt(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        for(int i = 0; i < part.length; i++) {
            pq.offer(part[i]);
        }

        index = 0;
        while(!pq.isEmpty()) {
            String[] cur = pq.poll();
            int num = Integer.parseInt(cur[2]);
            answer[index] = files[num];
            index++;
        }
        return answer;
    }
}
