import java.io.*;
import java.util.*;

class Main {

    static int n, idx1, idx2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int start = 0; int end = n - 1;
        int min = Integer.MAX_VALUE;
        while (start < end) {
            int sum = arr[start] + arr[end];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);

                idx1 = start; idx2 = end;
            }

            if (sum <= 0)
                start++;
            else
                end--;
        }

        System.out.println(arr[idx1] + " " + arr[idx2]);
    }
}
