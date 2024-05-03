package Jo_Seongjeong.Study_16주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1991 트리 순회
 *
 * 조건
 * 이진 트리 노드 개수 N : 1 ~ 26
 * 노드 이름 : A부터 시작, A는 루트 노드
 * 자식 노드가 없는 경우 .으로 표시
 *
 * 문제에서 구하고자 하는 것
 * 트리의 전위, 중위, 후위 순회 결과 각각 출력
 *
 * 문제 해결 프로세스
 * 전위 순회
 *  본인 먼저 넣고(들어오자마자), 왼쪽, 오른쪽 순회
 *
 * 중위 순회
 *  왼쪽, 본인 넣고, 오른쪽 순회
 *
 * 후위 순회
 *  왼쪽, 오른쪽, 본인 넣기(없는 경우)
 * 고려한 시간 복잡도
 * 3 * 2 * 26 = 156
 * */

public class BJ1991 { // 메모리 : 11624kb, 시간 : 80ms
    static int n;
    static List<Character>[] adjList;
    static String pre;
    static String in;
    static String post;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        adjList = new List[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            char ch = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            adjList[ch-65] = new ArrayList<>();
            adjList[ch-65].add(left);
            adjList[ch-65].add(right);
        }

        pre = "";
        preorder(0);

        in = "";
        inorder(0);

        post = "";
        postorder(0);

        sb.append(pre + "\n").append(in + "\n").append(post + "\n");
        System.out.println(sb);
    }
    private static void preorder(int idx) {
        pre += (char) (idx+65) + "";

        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                if(adjList[idx].get(i) == '.') continue;
                preorder(adjList[idx].get(i)-65);
            }

            else {
                if(adjList[idx].get(i) == '.') continue;
                preorder(adjList[idx].get(i)-65);
            }
        }
    }

    private static void inorder(int idx) {
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                if(adjList[idx].get(i) == '.') continue;
                inorder(adjList[idx].get(i)-65);
            }

            else {
                in += (char) (idx+65) + "";
                if(adjList[idx].get(i) == '.') continue;
                inorder(adjList[idx].get(i)-65);
            }
        }
    }

    private static void postorder(int idx) {
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                if(adjList[idx].get(i) == '.') continue;
                postorder(adjList[idx].get(i)-65);
            }

            else {
                if(adjList[idx].get(i) == '.') continue;
                postorder(adjList[idx].get(i)-65);
            }
        }
        post += (char) (idx+65) + "";
    }
}
