import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static boolean flag = false;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        list = new ArrayList[n];
        for (int i = 0; i < n; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            visited[i] = true;

            flag = false;
            solve(i, 1);

            if (flag) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    static void solve(int s, int cnt) {
        if (cnt == 5) {
            flag = true;
            return;
        }

        for (int i = 0; i < list[s].size(); i++) {
            int next = list[s].get(i);
            if (!visited[next]) {
                visited[next] = true;
                solve(next, cnt + 1);
                visited[next] = false;
            }
        }
    }
}