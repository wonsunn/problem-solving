import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int t, m, a, x, y, c, p;
    static int ax, ay, bx, by, sum;
    static int[][] move;
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static Boolean[][] path;
    static Bc[] bcInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int T = 1; T <= t; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            move = new int[2][m + 1];
            path = new Boolean[2][a];
            bcInfo = new Bc[a];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= m; j++) {
                    move[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken()); y = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken()); p = Integer.parseInt(st.nextToken());
                bcInfo[i] = new Bc(x, y, c, p);
            }

            sum = 0;
            ax = ay = 1;
            bx = by = 10;

            for (int i = 0; i <= m; i++) {
                ax += dx[move[0][i]]; ay += dy[move[0][i]];
                bx += dx[move[1][i]]; by += dy[move[1][i]];

                for (Boolean[] j : path) Arrays.fill(j, false);

                for (int j = 0; j < a; j++) {
                    if (isValid(ax, bcInfo[j].x, ay, bcInfo[j].y) <= bcInfo[j].c) path[0][j] = true;
                    if (isValid(bx, bcInfo[j].x, by, bcInfo[j].y) <= bcInfo[j].c) path[1][j] = true;
                }

                sum += getMax();
            }
            System.out.println("#" + T +  " " + sum);
        }

    }

    static int isValid(int x, int bcx, int y, int bcy) {
        return Math.abs(x - bcx) + Math.abs(y - bcy);
    }

    static int getMax() {
        int max = 0;

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                int res = 0;
                if (path[0][i]) {
                    if (path[1][j]) {
                        res = (i == j) ? bcInfo[j].p : bcInfo[i].p + bcInfo[j].p;
                    }
                    else res = bcInfo[i].p;
                }
                else {
                    if (path[1][j]) res = bcInfo[j].p;
                }
                max = Math.max(max, res);
            }
        }

        return max;
    }
}

class Bc {
    int x, y, c, p;

    Bc(int x, int y, int c, int p) {
        this.x = x; this.y = y; this.c = c; this.p = p;
    }
}