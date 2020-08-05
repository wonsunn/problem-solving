import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, num;
    static long order, sum;
    static long[] factorial;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        factorial = new long[n + 1];
        Arrays.fill(factorial, 1);
        for (int i = 1; i <= n; i++)
            factorial[i] = factorial[i - 1] * i;

        visited = new boolean[n + 1];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());

        if (num == 1) {
            order = Long.parseLong(st.nextToken());
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visited[j]) {
                        if (factorial[n - i - 1] < order)
                            order -= factorial[n - i - 1];
                        else {
                            arr[i] = j;
                            visited[j] = true;
                            break;
                        }
                    }
                }
            }
            for (int i : arr) System.out.print(i + " ");
        }
        else {
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            sum = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < arr[i]; j++) {
                    if (!visited[j])
                        sum += factorial[n - i - 1];
                }
                visited[arr[i]] = true;
            }
            System.out.println(sum);
        }
    }
}
