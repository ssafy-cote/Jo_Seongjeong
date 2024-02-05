package Jo_Seongjeong.Programmers;

import java.util.Arrays;
import java.util.Scanner;

public class ProStockPrice {
    public static void main(String[] args) {
        // 입력 확인
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < n; i++) {
            prices[i] = scan.nextInt();
        }
        
        // 제출
        int[] answer = new int[prices.length];

        for(int i = 0; i < prices.length-1; i++) {
            int time = 0;
            for(int j = i+1; j <prices.length; j++) {
                if(prices[i] <= prices[j] ) {
                    time++;
                }
                else {
                    time++;
                    break;
                }
            }

            answer[i] = time;
        }
        
        // 출력 확인
        System.out.println(Arrays.toString(answer));
    }
}
