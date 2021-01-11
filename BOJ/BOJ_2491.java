import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int len = 1, max = 1;
        // 증가 부분 체크
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] <= arr[i]) len++;
            else len = 1;
            max = Math.max(max, len);
        }

        len = 1;
        // 감소 부분 체크
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] >= arr[i]) len++;
            else len = 1;
            max = Math.max(max, len);
        }

        System.out.println(max);
    }
}