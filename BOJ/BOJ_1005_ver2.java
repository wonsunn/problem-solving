import java.io.*;
import java.util.*;

class Main {

    static int t, n, k;
    static int[] delay, result, inDegree;
    static List<Integer>[] arr;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            delay = new int[n + 1];
            result = new int[n + 1];
            inDegree = new int[n + 1];
            arr = new ArrayList[n + 1];
            for (int i = 0; i < arr.length; i++)
                arr[i] = new ArrayList<>();

            q = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                delay[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x].add(y);
                inDegree[y]++;
            }

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0)
                    q.add(i);
            }
            System.out.println(getTime(Integer.parseInt(br.readLine())));
        }
    }

    static int getTime(int dest) {
        while (inDegree[dest] > 0) {
            int x = q.poll();
            for (int nx : arr[x]) {
                result[nx] = Math.max(result[nx], result[x] + delay[x]);
                if (--inDegree[nx] == 0)
                    q.add(nx);
            }
        }
        
        return result[dest] + delay[dest];
    }
}