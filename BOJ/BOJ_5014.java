import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int f, s, g, u, d;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new int[f + 1];

        if ((s < g && u == 0) || (s > g && d == 0)) {
            System.out.println("use the stairs");
            return;
        }

        int res = BFS();
        if (res == -1) System.out.println("use the stairs");
        else System.out.println(res);

    }

    static int BFS() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, 0));
        visited[s] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.stair == g) 
                return cur.cnt;

            if (cur.stair + u <= f && visited[cur.stair + u] == 0) {
                q.add(new Node(cur.stair + u, cur.cnt + 1));
                visited[cur.stair + u] = 1;
            }
            if (cur.stair - d > 0 && visited[cur.stair - d] == 0) {
                q.add(new Node(cur.stair - d, cur.cnt + 1));
                visited[cur.stair - d] = 1;
            }
        }
        return -1;
    }
}

class Node {
    int stair, cnt;

    Node (int stair, int cnt) {
        this.stair = stair; this.cnt = cnt;
    }
}