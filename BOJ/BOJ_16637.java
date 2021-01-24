import java.io.*;
import java.util.*;

public class Main {

    static int n, max = Integer.MIN_VALUE;
    static int[] num;
    static char[] oper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        num = new int[n / 2 + 1];
        oper = new char[n / 2];

        int idx = 0, idx2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) num[idx++] = str.charAt(i) - '0';
            else oper[idx2++] = str.charAt(i);
        }

        if (n == 1)
            System.out.println(num[0]);
        else if (n == 3)
            System.out.println(calculate(num[0], num[1], oper[0]));
        else {
            DFS(0, num[0]);
            System.out.println(max);
        }

    }

    static void DFS(int idx, int res) {
        if (idx >= n / 2) {
            max = Math.max(max, res);
            return;
        }

        int curRes = calculate(res, num[idx + 1], oper[idx]);
        DFS(idx + 1, curRes);

        if (idx + 1 < n / 2) {
            int nextRes = calculate(num[idx + 1], num[idx + 2], oper[idx + 1]);
            curRes = calculate(res, nextRes, oper[idx]);
            DFS(idx + 2, curRes);
        }
    }

    static int calculate(int num1, int num2, char op) {
        if (op == '+') return num1 + num2;
        else if (op == '-') return num1 - num2;
        else return num1 * num2;
    }
}