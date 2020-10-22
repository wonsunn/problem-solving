import java.io.*;
import java.util.*;

class Main {

    static int t, n;
    static List<Integer>[] graph;
    static List<Node> list;
    static boolean[] visited;

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            visited = new boolean[n + 2];

            graph = new ArrayList[n + 2];
            for (int i = 0; i < n + 2; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
                list.add(new Node(x, y));
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = i + 1; j < n + 2; j++) {
                    if (isValid(list.get(i), list.get(j))) {
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }

            DFS(0);

            if (visited[n + 1]) System.out.println("happy");
            else System.out.println("sad");
        }

    }

    static void DFS(int s) {
        visited[s] = true;

        for (int i = 0; i < graph[s].size(); i++) {
            int next = graph[s].get(i);
            if (!visited[next]) {
                DFS(next);
            }
        }
    }

    static boolean isValid(Node n1, Node n2) {
        if (Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y) <= 1000) return true;
        else return false;
    }
}