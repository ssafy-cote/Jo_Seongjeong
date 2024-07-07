package Jo_Seongjeong.Study_18주차;

/**
 * 프로그래머스 점프와 순간 이동
 *
 * 조건
 * 이동 방법 : K칸 앞으로 점프, 현재거리 * 2에 해당하는 위치로 이동
 * 건전지 사용 : K칸 점프 -> K칸 소모
 * 목적지 n : 1 ~ 10억
 * 건전지 사용량을 줄이기 위해 점프 최소로 함
 *
 * 문제에서 구하고자 하는 것
 * 사용해야 하는 건전지 사용량의 최소값
 *
 * 문제 해결 프로세스
 * dp
 * dp[0] = 0
 * dp[1] = 1
 * dp[i] = dp[i/2] (홀수면 + 1)
 ****** 효율성 테스트에서 안됨
 * 그리디
 * n > 0
 * n -> 홀수일 때, +1 / 짝수일 때 + 0
 * n/2
 *
 * 고려한 시간 복잡도
 * 1
 */

public class ProJumpAndTeleport {
    public static void main(String[] args) {
        int n = 5;
        int result = solution(n);

        System.out.println(result);
    }

    public static int solution(int n) {
        int ans = 0;

        while(n > 0) {
            if(n % 2  == 1) ans++;
            n/=2;
        }

        return ans;
    }
}
