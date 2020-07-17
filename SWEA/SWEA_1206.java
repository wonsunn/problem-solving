import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, res;
        int[] height;

        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            height = new int[n];
            res = 0;

            for (int i = 0; i < n; i++) height[i] = Integer.parseInt(st.nextToken());

            for (int i = 2; i < n - 2; i++) {
                int max = -1;
                for (int j = i - 2; j <= i + 2; j++) {
                    if (j == i) continue;
                    max = Math.max(max, height[j]);
                }
                res += (height[i] - max > 0 ? height[i] - max : 0);
            }

            System.out.println("#" + t + " " + res);
        }
    }
}
