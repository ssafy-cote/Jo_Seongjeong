package Jo_Seongjeong.Study_16주차;

/**
 프로그래머스 타겟 넘버

 조건
 주어지는 숫자의 개수 : 2 ~ 20
 각 숫자의 범위 : 1 ~ 50
 타겟 넘버 : 1 ~ 1000
 타겟 넘버를 만드는 법
 더하기 / 빼기

 문제에서 구하고자 하는 것
 입력으로 주어진 수들을 모두 이용해 타겟 넘버 만드는 경우의 수

 문제 해결 프로세스
 중복 순열 (순서도 중요함)
 더하기, 빼기를 입력 숫자 개수만큼 뽑자

 고려한 시간 복잡도
 2H20 = 1,048,576
 */

public class ProTargetNumber {
    static int[] picked;
    static int[] giho = new int[]{0, 1}; //0, 1 (0이면 -, 1이면 +)
    static int n;
    static int rtn;

    public static void main(String[] args) {
        int[] numbers = new int[] {1, 1, 1, 1, 1};
        int target = 3;

        int answer = solution(numbers, target);

        System.out.println(answer);
    }

    public static int solution(int[] numbers, int target) {
        n = numbers.length;
        picked = new int[n];

        dupPermu(0, numbers, target);

        int answer = rtn;
        return answer;
    }

    private static void dupPermu(int index, int[] numbers, int target) {
        if(index == n) {
            // System.out.println(Arrays.toString(picked));
            int num = 0;

            for(int i = 0; i < n; i++) {
                if(picked[i] == 1) num += numbers[i];
                else num += (numbers[i] - 2 * numbers[i]);
            }

            if(num == target) rtn++;
            return;
        }

        for(int i = 0; i < 2; i++) {
            picked[index] = giho[i];
            dupPermu(index+1, numbers, target);
        }
    }
}
