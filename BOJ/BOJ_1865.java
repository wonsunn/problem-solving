import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static int n, m, w;
    static List<Info> edges;

    static class Info {
        int s, e, t;

        Info (int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Info(s, e, time));
                edges.add(new Info(e, s, time));
            }
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Info(s, e, -time));
            }

            if (isCycleOfMinus()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean isCycleOfMinus() {
        int[] dist = new int[n + 1];
        
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < edges.size(); j++) {
                int curNode = edges.get(j).s;
                int nextNode = edges.get(j).e;
                int cost = edges.get(j).t;

                if (dist[curNode] + cost < dist[nextNode]) {
                    dist[nextNode] = dist[curNode] + cost;
                    if (i == n - 1)
                        return true;
                }
            }
        }
        return false;
    }
}