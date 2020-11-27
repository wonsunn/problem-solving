import java.io.*;
import java.util.*;

class Main {

    static int A, B;

    private static class Info {
        long num;
        int cnt;

        Info (long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(BFS(A));
    }

    static int BFS(long a) {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(a, 1));

        while (!q.isEmpty()) {
            long cur = q.peek().num;
            int cnt = q.peek().cnt;

            q.poll();

            if (cur == B)
                return cnt;

            long next = cur * 2;
            if (next <= B) 
                q.add(new Info(next, cnt + 1));

            next = cur * 10 + 1;
            if (next <= B) 
                q.add(new Info(next, cnt + 1));
        }

        return -1;
    }
}