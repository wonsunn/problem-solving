import java.io.*;
import java.util.*;

class Main {

    static int n, idx = 0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(st.nextToken());
                pq.add(val);
            }
        }

        while (++idx < n)
            pq.poll();

        System.out.println(pq.peek());
    }
}