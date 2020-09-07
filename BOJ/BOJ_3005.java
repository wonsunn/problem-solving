import java.io.*;
import java.util.*;

class Main {

    static int r, c;
    static char[][] puzzle;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        puzzle = new char[r][c];

        for (int i = 0; i < r; i++)
            puzzle[i] = br.readLine().toCharArray();

        solveHorizontal();
        solveVertical();

        Collections.sort(list);

        System.out.println(list.get(0));
    }

    static void solveHorizontal() {
        for (int i = 0; i < r; i++) {
            StringBuilder tmp = new StringBuilder();

            for (int j = 0; j < c; j++) {
                if (puzzle[i][j] == '#') {
                    if (tmp.length() > 1)
                        list.add(tmp.toString());

                    tmp = new StringBuilder();
                    continue;
                }
                tmp.append(String.valueOf(puzzle[i][j]));
            }

            if (tmp.length() > 1)
                list.add(tmp.toString());
        }
    }

    static void solveVertical() {
        for (int i = 0; i < c; i++) {
            StringBuilder tmp = new StringBuilder();

            for (int j = 0; j < r; j++) {
                if (puzzle[j][i] == '#') {
                    if (tmp.length() > 1)
                        list.add(tmp.toString());

                    tmp = new StringBuilder();
                    continue;
                }
                tmp.append(String.valueOf(puzzle[j][i]));
            }

            if (tmp.length() > 1)
                list.add(tmp.toString());
        }
    }
}