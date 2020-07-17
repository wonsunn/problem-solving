import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, h;
    static int[] top, down;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = INF;
        int cnt = 0;

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        top = new int[n / 2];
        down = new int[n / 2];

        for (int i = 0; i < n / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            top[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(top);

        for (int i = 1; i <= h; i++) {
            int tmp = (down.length - findIdx(down, down.length - 1, i));
            tmp += (top.length - findIdx(top, top.length - 1, h - i + 1));

            if (tmp < min) {
                min = tmp;
                cnt = 1;
            }
            else if (tmp == min) cnt++;
        }

        System.out.println(min + " " + cnt);
    }

    static int findIdx(int[] arr, int end, int key) {
        int st = 0;

        if (key > arr[arr.length - 1]) return arr.length;

        while (st < end) {
            int mid = (st + end) / 2;
            if (key <= arr[mid]) end = mid;
            else st = mid + 1;
        }

        return end;
    }
}