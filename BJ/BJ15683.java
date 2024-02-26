package Jo_Seongjeong.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15683 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] office = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        int min = n * m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; i < m; j++) {
                int cctv = office[i][j];
                switch (cctv) {
                    case 1:
                        int temp = 0;
                        for (int c = i - 1; i >= 0; i--) {
                            if (office[c][j] == 0) {
                                temp++;
                            } else {
                                break;
                            }
                        }
                        min = Math.min(temp, min);
                        temp = 0;
                        for(int c = i+1; i < n; i++) {
                            if (office[c][j] == 0) {
                                temp++;
                            } else {
                                break;
                            }
                        }
                        min = Math.min(temp, min);
                        for(int r = j-1; r >= 0; r--) {
                            if (office[i][r] == 0) {
                                temp++;
                            } else {
                                break;
                            }
                        }
                        min = Math.min(temp, min);
                        for(int r = j + 1; r < m; r++) {
                            if (office[i][r] == 0) {
                                temp++;
                            } else {
                                break;
                            }
                        }
                        min = Math.min(temp, min);
                    case 2:
                }
            }
        }
//        for(int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(office[i]));
//        }
    }
}
