import java.io.*;
import java.util.*;

class Main {

    static Map<String, Integer> map = new HashMap<>();
    static int[] parents, size;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            map.clear();
            parents = new int[2 * f + 1];
            size = new int[2 * f + 1];

            for (int j = 0; j < 2 * f + 1; j++) {
                parents[j] = j;
                size[j] = 1;
            }

            int idx = 0;
            for (int j = 0; j < f; j++) {
                String[] tmp = br.readLine().split(" ");

                if (!map.containsKey(tmp[0])) map.put(tmp[0], idx++);
                if (!map.containsKey(tmp[1])) map.put(tmp[1], idx++);

                System.out.println(union(map.get(tmp[0]), map.get(tmp[1])));
            }
        }
    }

    static int find(int idx) {
        if (idx == parents[idx]) return idx;
        else return parents[idx] = find(parents[idx]);
    }

    static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            size[pa] += size[pb];
            size[pb] = size[pa];
            parents[pb] = pa;
        }

        return size[pa];
    }
}
