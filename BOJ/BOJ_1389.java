import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static final int INF = 987654321;
    static boolean[][] relation;
    static boolean[] visited;
    static Queue<Info> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = INF, res = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relation = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation[a][b] = true; relation[b][a] = true;
        }

        for (int i = 1; i <= n; i++) {
            int val = BFS(i);
            if (val < min) {
                min = val;
                res = i;
            }
        }

        System.out.println(res);

    }

    static int BFS(int id) {
        int sum = 0;
        visited = new boolean[n + 1];
        q.add(new Info(id, 0));
        visited[id] = true;

        while (!q.isEmpty()) {
            Info cur = q.poll();
            for (int j = 1; j <= n; j++) {
                if (relation[cur.num][j] && !visited[j]) {
                    visited[j] = true;
                    sum += (cur.cnt + 1);
                    q.add(new Info(j, cur.cnt + 1));
                }
            }
        }

        return sum;
    }

}

class Info {
    int num, cnt;

    Info (int num, int cnt) {
        this.num = num; this.cnt = cnt;
    }
}