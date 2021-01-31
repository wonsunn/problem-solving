import java.io.*;
import java.util.*;

public class Main {

    static int r, c, k, curR, curC;
    static int[][] arr = new int[101][101];
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        int time = 0;
        curR = 3;
        curC = 3;

        while (time <= 100) {
            if (arr[r][c] == k) {
                flag = true;
                break;
            }

            time++;

            if (curR >= curC)
                operateR();
            else
                operateC();
        }

        if (flag) System.out.println(time);
        else System.out.println(-1);
    }

    static void operateR() {
        int[][] tmp = new int[101][101];
        for (int i = 1; i <= curR; i++) {
            map.clear();
            for (int j = 1; j <= curC; j++) {
                if (arr[i][j] != 0)
                    map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }
            curC = Math.max(curC, map.size() * 2);

            List<Map.Entry<Integer, Integer>> list = mapToList(map);

            int idx = 1;
            for (Map.Entry<Integer, Integer> m : list) {
                tmp[i][idx++] = m.getKey();
                tmp[i][idx++] = m.getValue();
            }
        }

        copy(tmp, curR, curC);
    }

    static void operateC() {
        int[][] tmp = new int[101][101];
        for (int j = 1; j <= curC; j++) {
            map.clear();
            for (int i = 1; i <= curR; i++) {
                if (arr[i][j] != 0)
                    map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }
            curR = Math.max(curR, map.size() * 2);

            List<Map.Entry<Integer, Integer>> list = mapToList(map);
            int idx = 1;
            for (Map.Entry<Integer, Integer> m : list) {
                tmp[idx++][j] = m.getKey();
                tmp[idx++][j] = m.getValue();
            }
        }

        copy(tmp, curR, curC);
    }

    static List<Map.Entry<Integer, Integer>> mapToList(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (m1, m2) -> (m1.getValue() == m2.getValue()
                ? m1.getKey() - m2.getKey() : m1.getValue() - m2.getValue()));

        return list;
    }

    static void copy(int[][] tmp, int r, int c) {
        for (int i = 1; i <= r; i++)
            for (int j = 1; j <= c; j++)
                arr[i][j] = tmp[i][j];
    }
}