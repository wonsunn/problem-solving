import java.io.*;
import java.util.*;

class Main {

    static int n, sum = 0;
    static int[] height = new int[1001];
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int maxIdx = -1, minIdx = 1001;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            height[idx] = h;

            maxIdx = Math.max(maxIdx, idx); // 가장 끝에 있는 지점 구하기 위함.
            minIdx = Math.min(minIdx, idx); // 가장 처음 지점 구하기 위함.
        }

        // 왼쪽부터 창고 높이 구하기
        int pivot = height[minIdx];
        for (int i = minIdx + 1; i <= maxIdx; i++) {
            if (height[i] < pivot) q.add(i);
            else {
                while (!q.isEmpty())
                    height[q.poll()] = pivot;

                pivot = height[i];
            }
        }

        q.clear();
        // 오른쪽부터 창고 높이 구하기
        pivot = height[maxIdx];
        for (int i = maxIdx - 1; i >= minIdx; i--) {
            if (height[i] < pivot) q.add(i);
            else {
                while (!q.isEmpty())
                    height[q.poll()] = pivot;

                pivot = height[i];
            }
        }

        for (int i = minIdx; i <= maxIdx; i++)
            sum += height[i];

        System.out.println(sum);
    }
}