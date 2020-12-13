import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] NGE = new int[n];
        Arrays.fill(NGE, -1);
        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i])
                NGE[stack.pop()] = A[i];

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : NGE)
            sb.append(i).append(" ");

        System.out.println(sb.toString());
    }
}
