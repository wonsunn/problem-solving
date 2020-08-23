import java.io.*;
import java.util.*;

class Main {

    static int v, e, k;
    static int[][] map;
    static int[] dis;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        List<Info>[] map = new ArrayList[v + 1];
        for (int i = 0; i < map.length; i++)
            map[i] = new ArrayList<>();

        visited = new boolean[v + 1];
        dis = new int[v + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k] = 0;

        for (int i = 0; i < e; i++) {
            String[] str = br.readLine().split(" ");
            map[Integer.parseInt(str[0])].add(new Info(Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

        for (int i = 0; i < v; i++) {
            int min = 0;
            
            for (int j = 1; j <= v; j++) {
                if (dis[min] > dis[j] && !visited[j]) 
                    min = j;
            }
            
            visited[min] = true;
            
            for (int j = 0; j < map[min].size(); j++) {
                if (dis[min] + map[min].get(j).cost < dis[map[min].get(j).node]) 
                    dis[map[min].get(j).node] = dis[min] + map[min].get(j).cost;
            }
        }

        for (int i = 1; i <= v; i++) {
            if (dis[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dis[i]);
        }
    }
}

class Info {
    int node, cost;

    Info (int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}