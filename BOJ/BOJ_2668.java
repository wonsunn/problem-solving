import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] second, visited, cycle;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        second = new int[n + 1];
        visited = new int[n + 1];
        cycle = new int[n + 1];

        for (int i = 1; i <= n; i++) second[i] = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) visited[j] = cycle[j];

            DFS(i, second[i]);
        }

        System.out.println(cnt);
        for (int i = 1; i <= n; i++) {
            if (cycle[i] == 1) System.out.println(i);
        }
    }

    static boolean DFS(int st, int target) {
        if (visited[target] == 1) return false;

        visited[target] = 1;

        if (st == target || DFS(st, second[target])) {
            cnt++;
            cycle[target] = 1;
            return true;
        }

        return false;
    }
}
