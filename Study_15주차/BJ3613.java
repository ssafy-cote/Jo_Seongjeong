package Jo_Seongjeong.Study_15주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 3613 Java vs C++
 *
 * 조건
 * 변수명 짓기
 * 자바
 * - 첫단어 소문자
 * - 다음 단어부터는 첫 문자만 대문자
 * - 모두 붙여씀
 * C++
 * - 모두 소문자
 * - 단어와 단어 사이에 _로 어음
 * 변수명의 길이 : 1 ~ 100
 *
 * 문제에서 구하고자 하는 것
 * 자바 -> C++, C++ -> 자바로 변수명 바꾸기
 *
 * 문제 해결 프로세스
 * 입력을 받을 때 _가 있는지 확인해 C++인지, Java인지 결정
 * C++로 판단하는 경우
 * 알파벳 대문자가 나오면 종료 Error!
 * _을 만나면 다음 문자는 대문자로 변경해서 붙여 출력
 * Java로 판단하는 경우
 * 첫문자는 알파벳 소문자여야 함 -> 소문자로 붙이기
 * 대문자가 나오면 앞에 _붙이고 소문자로 다 바꾸기
 * 공백이 있다면 error
 *
 * 고려한 시간 복잡도
 * 100 + 100 = 200
 * */

public class BJ3613 { // 메모리 : 11556kb, 시간 : 84ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String rs = "";
        boolean flag = false; // f- 자바, t- c++

        // C++인지 판단
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '_') {
                flag = true;
                break;
            }
        }

        boolean under = false;
        for(int i = 0; i < str.length(); i++) {
            // 자바로 바꾸기
            if (flag) {
                if(i == 0) {
                    if(str.charAt(i) == '_') {
                        System.out.println("Error!");
                        return;
                    }
                }

                if(i == str.length()-1) {
                    if(str.charAt(i) == '_') {
                        System.out.println("Error!");
                        return;
                    }
                }

                if(str.charAt(i) == '_') {
                    if(under) {
                        System.out.println("Error!");
                        return;
                    }
                    under = true;
                }
                else if(str.charAt(i) >= 97 && str.charAt(i) <= 123){
                    if(under) {
                        rs += Character.toUpperCase(str.charAt(i));
                        under = false;
                    }
                    else rs += str.charAt(i);
                }
                else {
                    System.out.println("Error!");
                    return;
                }
            }

            // C++로 바꾸기
            else {
                if(i == 0) {
                    if(str.charAt(i) >= 97 && str.charAt(i) <= 123) rs += str.charAt(i);
                    else {
                        System.out.println("Error!");
                        return;
                    }
                }
                else {
                    if(str.charAt(i) >= 97 && str.charAt(i) <= 123) rs += str.charAt(i);
                    else if(str.charAt(i) >= 65 && str.charAt(i) <= 91) {
                        rs += "_";
                        rs += Character.toLowerCase(str.charAt(i));
                    }
                    else {
                        System.out.println("Error!");
                        return;
                    }
                }

            }
        }
        System.out.println(rs);
    }
}
