import java.io.*;
import java.util.*;

class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static long[] dist;
    static List<Pair> edges = new ArrayList<>();

    static class Pair {
        int curNode, nextNode, cost;

        Pair (int curNode, int nextNode, int cost) {
            this.curNode = curNode;
            this.nextNode = nextNode;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Pair(a, b, c));
        }

        boolean cycleOfMinus = isCycleOfMinus(1);

        if (cycleOfMinus) System.out.println("-1");
        else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }
    }

    /*
    벨만포드 알고리즘을 통해 음의 사이클 여부를 판단
     */
    static boolean isCycleOfMinus(int start) {
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curNode = edges.get(j).curNode;
                int nextNode = edges.get(j).nextNode;
                int cost = edges.get(j).cost;

                if (dist[curNode] != INF && dist[curNode] + cost < dist[nextNode]) {
                    dist[nextNode] = dist[curNode] + cost;
                    if (i == n - 1)
                        return true;
                }
            }
        }

        return false;
    }
}