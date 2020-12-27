import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[] ladderInfo = new int[101];
    static int[] dist = new int[101];
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 100; i++)
            ladderInfo[i] = i;

        for (int i = 0; i < (n + m); i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ladderInfo[s] = e;
        }

        System.out.println(BFS());
    }

    static int BFS() {
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == 100)
                return dist[cur];

            for (int i = 1; i <= 6; i++) {
                if (cur + i > 100) continue;
                int next = ladderInfo[cur + i];

                if (dist[next] == 0) {
                    q.add(next);
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        return 0;
    }
}