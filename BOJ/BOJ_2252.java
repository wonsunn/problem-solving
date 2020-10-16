import java.io.*;
import java.util.*;

class Main {
    
    static int n, m;
    static ArrayList<Integer>[] list;
    static Queue<Integer> q = new LinkedList<>();
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();
        inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            inDegree[e]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            System.out.print(cur + " ");

            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i);
                inDegree[next]--;

                if (inDegree[next] == 0)
                    q.add(next);
            }
        }
    }
}