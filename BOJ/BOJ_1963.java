import java.io.*;
import java.util.*;

class Main {

    static int t, MAX = 10000;
    static String prev, changed;
    static int[] visited = new int[MAX];
    static int[] prime = new int[MAX];
    static Queue<String> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        setPrime();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            prev = st.nextToken(); changed = st.nextToken();

            Arrays.fill(visited, 0);
            visited[Integer.parseInt(prev)] = 1;

            q = new LinkedList<>();
            q.add(prev);

            int res = BFS();
            if (res > - 1) System.out.println(res);
            else System.out.println("Impossible");
        }
    }

    static int BFS() {
        while (!q.isEmpty()) {
            String cur = q.poll();

            if (cur.equals(changed))
                return visited[Integer.parseInt(cur)] - 1;

            for (int i = 0; i < 4; i++) {
                char[] tmp = cur.toCharArray();
                
                for (int j = 0; j < 10; j++) {
                    tmp[i] = Character.forDigit(j, 10);
                    int p = Integer.parseInt(String.valueOf(tmp));

                    if (p < 1000 || visited[p] != 0 || prime[p] == 0) continue;

                    visited[p] = visited[Integer.parseInt(cur)] + 1;
                    q.add(String.valueOf(p));
                }
            }
        }

        return -1;
    }

    static void setPrime() {
        for (int i = 2; i < MAX; i++)
            prime[i] = i;

        for (int i = 2; i < MAX; i++) {
            if (prime[i] == 0) continue;
            for (int j = i + i; j < MAX; j += i) {
                prime[j] = 0;
            }
        }
    }
}