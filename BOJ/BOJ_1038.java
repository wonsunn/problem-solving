import java.io.*;
import java.util.*;

class Main {

    static long n, idx;
    static Queue<Info> q = new LinkedList<>();

    private static class Info {
        long num, idx;

        Info (long num, long idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++)
            q.add(new Info(i, i));

        idx = 10;
        System.out.println(BFS());
    }

    static long BFS() {
        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.idx == n) {
                return cur.num;
            }

            long tmp = cur.num % 10;
            for (int i = 0; i < tmp; i++) {
                q.add(new Info(cur.num * 10 + i, idx++));
            }
        }

        return -1;
    }
}