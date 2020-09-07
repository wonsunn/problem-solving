import java.io.*;
import java.util.*;

class Main {

    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int sum = arr[0], start = 0, end = 0;
        int res = Integer.MAX_VALUE;
        while (start < n && end < n) {
            if (sum < s) {
                sum += arr[end++];
            }
            else {
                res = Math.min(res, end - start + 1);
                sum -= arr[start++];
            }
        }

        if (res == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(res);
    }
}