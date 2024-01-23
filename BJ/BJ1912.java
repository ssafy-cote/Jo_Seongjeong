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
            if (sum > 0) {
                sum += array[i];
            } else {
                sum = array[i];
            }

            result = Math.max(sum, result);
        }
        System.out.println(result);
    }
}

