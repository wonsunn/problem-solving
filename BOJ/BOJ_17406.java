import java.io.*;
import java.util.*;

class Main {

    static int n, m, k, answer = Integer.MAX_VALUE;
    static int[] order;
    static int[][] arr, newArr;
    static boolean[] selected;
    static Pair[] p;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Pair {
        int r, c, s;

        Pair (int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        newArr = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        p = new Pair[k];
        order = new int[k];
        selected = new boolean[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            p[i] = new Pair(r, c, s);
        }

        DFS(0);
        System.out.println(answer);
    }

    static void rotate(int c, int r, int s) {
        int lx = r - s; int ly = c - s;
        int rx = r + s; int ry = c + s;
        int rotateCnt = (rx - lx) / 2;

        for (int t = 0; t < rotateCnt; t++) {
            int x = lx; int y = ly;
            int tmp = newArr[x][y]; // 처음 위치 값 저장

            // 반시계 방향으로 탐색, 다음 위치에 있는 값이 현재 자기자신의 위치에 저장되는 개념
            int dir = 2;
            while (true) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 그 다음 위치가 처음 위치면, 처음에 저장한 값을 현재 위치에 저장하면서 종료
                if (nx == lx && ny == ly) {
                    newArr[x][y] = tmp;
                    break;
                }

                if (lx <= nx && nx <= rx - t && ly <= ny && ny <= ry - t) {
                    newArr[x][y] = newArr[nx][ny];
                    x = nx;
                    y = ny;
                }
                // 범위를 벗어나면 그 다음 순서로 지정
                else {
                    dir = changeDirection(dir);
                }
            }

            // 다음 처음 위치 update ex) (0, 0) -> (1, 1)
            lx++;
            ly++;
        }
    }

    static int changeDirection(int d) {
        if (d == 0) return 3;
        else if (d == 1) return 0;
        else if (d == 2) return 1;
        else return 2;
    }

    static int calculate() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= m; j++) {
                sum += newArr[i][j];
            }
            min = Math.min(min, sum);
        }

        return min;
    }

    static void DFS(int l) {
        if (l == k) {
            // clone arr -> newArr
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++)
                    newArr[i][j] = arr[i][j];
            }

            for (int i = 0; i < k; i++)
                rotate(p[order[i]].c, p[order[i]].r, p[order[i]].s);

            answer = Math.min(answer, calculate());
            return;
        }

        // 배열 순서를 정하기 위함(순열 생성)
        for (int i = 0; i < k; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            order[l] = i;
            DFS(l + 1);
            selected[i] = false;
        }
    }
}