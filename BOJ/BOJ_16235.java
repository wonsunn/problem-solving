import java.io.*;
import java.util.*;

class Main {

    static int n, m, k, cnt = 0;
    static int[][] A, nutrient;
    static List<Integer>[][] trees;
    static int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[n + 1][n + 1];
        nutrient = new int[n + 1][n + 1];
        trees = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                trees[i][j] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[r][c].add(age);
        }

        for (int i = 0; i < k; i++) {
            springSummer();
            fall();
            winter();
        }

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                cnt += trees[i][j].size();

        System.out.println(cnt);
    }

    static void springSummer() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (trees[i][j].size() > 0) {
                    Collections.sort(trees[i][j]);

                    int dead = 0;
                    LinkedList<Integer> tmp = new LinkedList<>();

                    for (int age : trees[i][j]) {
                        if (nutrient[i][j] < age) {
                            dead += (age / 2);
                        }
                        else {
                            nutrient[i][j] -= age;
                            tmp.add(age + 1);
                        }
                    }

                    trees[i][j].clear();

                    nutrient[i][j] += dead;
                    while (!tmp.isEmpty())
                        trees[i][j].add(tmp.poll());
                }
            }
        }
    }

    static void fall() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (trees[i][j].size() > 0) {
                    for (int age : trees[i][j]) {
                        if (age % 5 != 0) continue;

                        for (int k = 0; k < 8; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                            trees[nx][ny].add(1);
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                nutrient[i][j] += A[i][j];
    }
}