import java.io.*;
import java.util.*;

class Main {

    static int A, B, C;
    static boolean[][][] visited;
    static List<Integer> list = new ArrayList<>();
    static Queue<Pair> q = new LinkedList<>();

    private static class Pair {
        int a, b, c;

        Pair(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1][C + 1];
        q.add(new Pair(0, 0, C));
        BFS();

        Collections.sort(list);
        list.forEach(i -> System.out.print(i + " "));
    }

    static void BFS() {
        while (!q.isEmpty()) {
            int a = q.peek().a;
            int b = q.peek().b;
            int c = q.peek().c;
            q.poll();

            if (visited[a][b][c])
                continue;

            visited[a][b][c] = true;

            if (a == 0)
                list.add(c);

            // a -> b
            if (a + b > B) q.add(new Pair(a + b - B, B, c));
            else q.add(new Pair(0, a + b, c));
            // b -> a
            if (a + b > A) q.add(new Pair(A, a + b - A, c));
            else q.add(new Pair(a + b, 0, c));
            // a -> c
            if (a + c > C) q.add(new Pair(a + c - C, b, C));
            else q.add(new Pair(0, b, a + c));
            // c -> a
            if (a + c > A) q.add(new Pair(A, b, a + c - A));
            else q.add(new Pair(a + c, b, 0));
            // b -> c
            if (b + c > C) q.add(new Pair(a, b + c - C, C));
            else q.add(new Pair(a, 0, b + c));
            // c -> b
            if (b + c > B) q.add(new Pair(a, B, b + c - B));
            else q.add(new Pair(a, b + c, 0));
        }
    }
}