package Jo_Seongjeong.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1912 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        
        int sum = array[0];
        int result = array[0];
        for (int i = 1; i < n; i++) {
            // 누적 합이 양수인 경우 계속 더함
            if (sum > 0) {
                sum += array[i];
            } 
            // 누적 합이 음수인 경우 합 갱신
            else {
                sum = array[i];
            }
            
            // 합과 현재 최대값 비교 후 더 큰 값 저장
            result = Math.max(sum, result);
        }
        System.out.println(result);
    }
}

