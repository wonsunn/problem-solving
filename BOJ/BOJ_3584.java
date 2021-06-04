import java.io.*;
import java.util.*;

public class BOJ_3584 {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            parents = new int[n+1];

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parents[c] = p;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int a_depth = getDepth(a);
            int b_depth = getDepth(b);

            if (a_depth < b_depth) {
                while (a_depth != b_depth) {
                    b_depth--;
                    b = parents[b];
                }
            }
            else if (a_depth > b_depth) {
                while(a_depth != b_depth) {
                    a_depth--;
                    a = parents[a];
                }
            }

            while (a != b) {
                a = parents[a];
                b = parents[b];
            }

            System.out.println(a);
        }

    }

    static int getDepth(int child) {
        int cnt = 0;
        while (true) {
            cnt++;
            int p = parents[child];

            if (p == 0)
                break;

            child = p;
        }

        return cnt;
    }
}