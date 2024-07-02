package Jo_Seongjeong.Study_3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ2839 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sugar = Integer.parseInt(br.readLine());
        int min = sugar;

        for(int i = 0; i*5 <= sugar; i++) {
            int count = 0;
            int temp = sugar - (5*i);

            if(temp % 3 == 0) {
                count = i;
                count += temp/3;
                min = Math.min(min, count);
            }
        }
        if(min == sugar) {
            min = -1;
        }
        System.out.println(min);
    }
}
