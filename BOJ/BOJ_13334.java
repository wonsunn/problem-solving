import java.io.*;
import java.util.*;

class Main {
    
    static int n, max = 0;
    static PriorityQueue<Integer> pq;
    static List<Pair> pairs = new ArrayList<>();

    private static class Pair {
        int left, right;

        Pair (int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            pairs.add(new Pair(Math.min(l, r), Math.max(l, r)));
        }
        Collections.sort(pairs, (p1, p2) ->
                p1.right == p2.right ? p1.left - p2.left : p1.right - p2.right);

        int d = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (Pair p : pairs) {
            while (!pq.isEmpty() && pq.peek() < p.right - d) {
                pq.poll();
                cnt--;
            }

            if (p.left >= p.right - d) {
                cnt++;
                pq.add(p.left);
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}