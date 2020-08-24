import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;

        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(" ");
            if (Integer.parseInt(str[0]) == 0) {
                union(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
            }
            else {
                if (find(Integer.parseInt(str[1])) == find(Integer.parseInt(str[2]))) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    static int find(int n) {
        if (n == parent[n]) return n;
        else return parent[n] = find(parent[n]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}