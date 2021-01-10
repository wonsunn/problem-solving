import java.io.*;
import java.util.*;

class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n, res = INF;
    static int[] people;

    static List<Integer>[] graph;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            people[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= (n / 2); i++) {
            set.clear();
            func(0, 1, i);
        }

        System.out.println((res == INF) ? -1 : res);
    }

    /*
    한 선거구에 있는 구역이 모두 연결되어있는지 판단하는 함수
     */
    static boolean isConnected(List<Integer> area) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        int first = area.get(0);
        q.add(first);
        visited[first] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if (!area.contains(next) || visited[next])
                    continue;

                visited[next] = true;
                q.add(next);
            }
        }

        for (int i = 0; i < area.size(); i++) {
            if (!visited[area.get(i)])
                return false;
        }

        return true;
    }

    /*
    두 선거구의 인구 차이를 구하는 함수
     */
    static int diffOfArea(List<Integer> areaA, List<Integer> areaB) {
        int tmpA = 0, tmpB = 0;
        for (int i = 0; i < areaA.size(); i++)
            tmpA += people[areaA.get(i)];

        for (int i = 0; i < areaB.size(); i++)
            tmpB += people[areaB.get(i)];

        return Math.abs(tmpA - tmpB);
    }

    /*
    재귀를 통한 조합 구현
     */
    static void func(int l, int s, int limit) {
        if (l == limit) {
            List<Integer> areaA = new ArrayList<>();
            List<Integer> areaB = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (set.contains(i)) areaA.add(i);
                else areaB.add(i);
            }

            if (!isConnected(areaA) || !isConnected(areaB))
                return;

            res = Math.min(res, diffOfArea(areaA, areaB));
        }

        for (int i = s; i <= n; i++) {
            set.add(i);
            func(l + 1, i + 1, limit);
            set.remove(i);
        }
    }
}