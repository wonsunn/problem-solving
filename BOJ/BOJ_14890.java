import java.io.*;
import java.util.*;

class Main {

    static int n, l, res = 0;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n * 2][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 세로를 가로 밑으로
        for (int i = n; i < n * 2; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = map[j][i - n];
            }
        }

        for (int i = 0; i < n * 2; i++) {
            int zero = 1;
            boolean check = true;
            Arrays.fill(visited, false);

            for (int j = 1; j < n; j++) {
                if (!visited[j]) {
                    int val = map[i][j] - map[i][j - 1];

                    if (val == 0)
                        zero++;
                    // 높은 칸 만났을 때
                    else if (val == 1) {
                        if (zero < l) {
                            check = false;
                            break;
                        }
                        else
                            zero = 1;
                    }
                    // 낮은 칸 만났을 때
                    else if (val == -1) {
                        int tmp = map[i][j];
                        for (int k = j; k < j + l; k++) {
                            if (k >= n || map[i][k] != tmp) {
                                check = false;
                                break;
                            }

                            visited[k] = true;
                        }
                        if (check)
                            zero = 0;
                    }
                    else {
                        check = false;
                        break;
                    }

                    visited[j] = true;
                }
            }

            if (check) res++;
        }

        System.out.println(res);
    }
}