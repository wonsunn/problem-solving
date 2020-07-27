import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int a = 1; a <= t; a++) {
            int n = Integer.parseInt(br.readLine());
            long res = 0;

            int[] price = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) price[i] = Integer.parseInt(st.nextToken());

            int idx = n - 1;
            while (idx > 0) {
                boolean flag = true;

                for (int j = idx - 1; j >= 0; j--) {
                    if (price[j] < price[idx]) res += (price[idx] - price[j]);
                    else {
                        idx = j;
                        flag = false;
                        break;
                    }
                }

                if (flag) idx = 0;
            }

            System.out.println("#" + a + " " + res);
        }
    }
}