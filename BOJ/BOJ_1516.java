import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] inDegree, time, result;
    static List<Integer>[] edges;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        inDegree = new int[n + 1];
        time = new int[n + 1];
        result = new int[n + 1];
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                inDegree[i]++;
                edges[x].add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                result[i] = time[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < edges[cur].size(); i++) {
                int next = edges[cur].get(i);
                result[next] = Math.max(result[next], result[cur] + time[next]);
                if (--inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= n; i++)
            System.out.println(result[i]);

    }
}