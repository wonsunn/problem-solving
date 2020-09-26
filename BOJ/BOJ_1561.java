import java.io.*;
import java.util.*;

class Main {

    static int n, m, child;
    static int[] time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            time[i] = Integer.parseInt(st.nextToken());

        // 아이 수가 더 적으면 놀이기구 번호 출력(놀이기구 순서대로)
        if (n <= m) {
            System.out.println(n);
            return;
        }

        long t = getTime(); // 마지막 아이가 탑승할 시간 구하기

        // 마지막 아이 탑승 시간 전까지 아이 번호 구하기
        child = m;
        for (int i = 0; i < m; i++)
            child += (t - 1) / time[i];
        
        // 탑승 시간 때 놀이기구 번호 구하기
        for (int i = 0; i < m; i++) {
            if (t % time[i] == 0)
                child++;

            if (child == n) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    static long getTime() {
        long s = 0, e = 2000000000L * 30;

        while (s < e) {
            long mid = (s + e) / 2;

            long child = m;
            for (int i = 0; i < m; i++)
                child += (mid / time[i]);

            if (child >= n)
                e = mid;
            else
                s = mid + 1;
        }

        return s;
    }
}
