import java.io.*;
import java.util.*;

class Main {

    static final int MAX = 987654321;
    static int n, e;
    static ArrayList<Info>[] graph;

    private static class Info {
        int node, cost;

        Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Info(b, c));
            graph[b].add(new Info(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] arr1 = dijkstra(1);
        int[] arr2 = dijkstra(v1);
        int[] arr3 = dijkstra(v2);

        int ans = Math.min(arr1[v1] + arr2[v2] + arr3[n], arr1[v2] + arr3[v1] + arr2[n]);

        if (ans >= MAX || ans < 0)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.cost - i2.cost);
        int[] arr= new int[n + 1];
        Arrays.fill(arr, MAX);
        arr[start] = 0;

        pq.add(new Info(start, 0));
        while (!pq.isEmpty()) {
            int node = pq.peek().node;
            int cost = pq.peek().cost;

            pq.poll();

            if (arr[node] < cost) continue;

            for (int i = 0; i < graph[node].size(); i++) {
                int nextNode = graph[node].get(i).node;
                int c = graph[node].get(i).cost;

                if (c + arr[node] < arr[nextNode]) {
                    arr[nextNode] = c + arr[node];
                    pq.add(new Info(nextNode, arr[nextNode]));
                }
            }
        }

        return arr;
    }
}
