import java.io.*;
import java.util.*;

class Main {

    static String str = "";
    static int[] pairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        Stack<Integer> stack = new Stack<>();
        pairs = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(')
                stack.add(i);
            else if (c == ')')
                pairs[stack.pop()] = i;
        }

        System.out.println(solve(0, str.length()));
    }

    static int solve(int start, int end) {
        int res = 0;
        
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == '(') {
                int k = str.charAt(i - 1) - '0';

                res += k * solve(i + 1, pairs[i]) - 1;
                i = pairs[i];

                continue;
            }

            res++;
        }

        return res;
    }
}