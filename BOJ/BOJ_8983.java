import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] loc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(loc);

        int res = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());

            if (b > l) continue; // 음수 발생

            int start = 0; int end = n - 1;
            int upper = a - b + l; int lower = a + b - l;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (loc[mid] >= lower && loc[mid] <= upper) {
                    res++;
                    break;
                }
                else if (loc[mid] < lower)
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }

        System.out.println(res);
    }
}
