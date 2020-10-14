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

        int s = 1, e = r;
        Set<String> set = new HashSet<>();
        while (s < e) {
            int mid = (s + e) / 2;

            if (set.size() > 0) set.clear();
            
            for (int i = 0; i < c; i++) {
                StringBuilder sb = new StringBuilder();
                
                for (int j = mid; j < r; j++)
                    sb.append(words[j][i]);
                
                set.add(sb.toString());
            }

            if (set.size() == c)
                s = mid + 1;
            else
                e = mid;
        }

        System.out.println(e - 1);
    }
}