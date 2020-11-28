import java.io.*;
import java.util.*;

class Main {

    static int n, sum = 0;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((i1, i2) -> i1 - i2);

        for (int i = 0; i < n; i++)
            pq.add(Integer.parseInt(br.readLine()));

        if (n == 1) System.out.println(0);
        else {
            while (pq.size() != 1) {
                int c1 = pq.poll();
                int c2 = pq.poll();
                sum += c1 + c2;

                pq.add(c1 + c2);
            }

            System.out.println(sum);
        }
    }
}