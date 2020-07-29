import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, mid, ans = 0;
    static Map<Integer, List<Integer>> big = new HashMap<>();
    static Map<Integer, List<Integer>> small = new HashMap<>();
    static boolean[] bVisited, sVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mid = (n + 1) / 2;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken()); int s = Integer.parseInt(st.nextToken());

            if (big.containsKey(s)) big.get(s).add(b);
            else big.put(s, new ArrayList<>(Arrays.asList(b)));

            if (small.containsKey(b)) small.get(b).add(s);
            else small.put(b, new ArrayList<>(Arrays.asList(s)));
        }

        for (int i = 1; i <= n; i++) {
            bVisited = new boolean[n + 1];
            sVisited = new boolean[n + 1];

            bVisited[i] = true; sVisited[i] = true;

            if (DFS(i, 1) > mid || DFS(i, 2) > mid)
                ans++;

        }
        System.out.println(ans);
    }

    static int DFS(int num, int opt) {
        int res = 1, cur = 0;
        if (opt == 1 && big.containsKey(num)) {
            for (int i = 0; i < big.get(num).size(); i++) {
                cur = big.get(num).get(i);
                if (!bVisited[cur]) {
                    bVisited[cur] = true;
                    res += DFS(cur, 1);
                }
            }
        }
        else if (opt == 2 && small.containsKey(num)){
            for (int i = 0; i < small.get(num).size(); i++) {
                cur = small.get(num).get(i);
                if (!bVisited[cur]) {
                    bVisited[cur] = true;
                    res += DFS(cur, 2);
                }
            }
        }
        return res;
    }
}