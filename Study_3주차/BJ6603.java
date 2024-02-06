package Jo_Seongjeong.Study_3주차;

import java.util.Scanner;

public class BJ6603 {
    static int k;
    static int[] array;
    static int[] pick = new int[6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            k = sc.nextInt();

            if(k == 0) break;

            array = new int[k];

            for(int i =0; i < k; i++) {
                array[i] = sc.nextInt();
            }
            combi(0, 0);
            System.out.println();

        }
    }

    private static void combi(int r, int index) {
        if(index == 6) {
            for(int i = 0; i < 6; i++) {
                System.out.print(pick[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = r; i < k; i++) {
            pick[index] = array[i];
            combi(i+1, index+1);
        }
    }
}