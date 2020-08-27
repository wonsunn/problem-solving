import java.io.*;
import java.util.*;

class Main {

    static int t, n, cnt;
    static int[] team;
    static boolean[] visited, done;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            team = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                team[i] = Integer.parseInt(st.nextToken());

            cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i])
                    DFS(i);
            }

            System.out.println(n - cnt);
        }
    }

    static void DFS(int num) {
        visited[num] = true;

        int next = team[num];
        if (!visited[next])
            DFS(next);
        else if (!done[next]) {
            for (int i = next; i != num; i = team[i])
                cnt++; //팀원 모두 count
            cnt++; // 자기 자신 count
        }
        
        done[num] = true;
    }
}