import java.io.*;
import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (isSameParents(a, b)) {
                System.out.println(i+1);
                return;
            }
            union(a, b);
        }

        System.out.println(0);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a < b) parents[b] = a;
        else parents[a] = b;
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        else return parents[a] = find(parents[a]);
    }

    static boolean isSameParents(int a, int b) {
        return find(a) == find(b);
    }
}