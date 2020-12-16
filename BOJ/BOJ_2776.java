import java.io.*;
import java.util.*;

class Main {

    static int t, n, m;
    static int[] arr;
    static Map<Integer, Boolean> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                map.put(Integer.parseInt(st.nextToken()), true);

            m = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (map.containsKey(num)) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }
            System.out.print(sb.toString());
        }
    }
}