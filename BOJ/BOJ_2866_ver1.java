import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] words = new char[r][c];
        for (int i = 0; i < r; i++)
            words[i] = br.readLine().toCharArray();

        Set<String> set = new HashSet<>();
        for (int i = 0; i < c; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < r; j++)
                sb.append(words[j][i]);

            set.add(sb.toString());
        }

        for (int i = 0; i < r - 1; i++) {
            Set<String> set2 = new HashSet<>();

            for (String str : set)
                set2.add(str.substring(1));

            if (set2.size() != c) {
                System.out.println(i);
                return;
            }

            set = set2;
        }

        System.out.println(r - 1);
    }
}

