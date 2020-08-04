import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int t, n;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            rank = new int[n + 1];
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                rank[first] = second;
            }

            for (int i = n; i > 0; i--) {
                boolean fail = false;
                for (int j = i - 1; j > 0; j--) {
                    if (rank[i] > rank[j]) {
                        fail = true;
                        break;
                    }
                }
                if (!fail) cnt++;
            }
            System.out.println(cnt);
        }
    }
}
