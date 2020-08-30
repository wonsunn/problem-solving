import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String res = "";
        int cnt, max;

        int n = Integer.parseInt(br.readLine());
        String[] title = new String[n];

        for (int i = 0; i < n; i++)
            title[i] = br.readLine();

        Arrays.sort(title);

        String tmp = title[0];
        cnt = 0; max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (tmp.equals(title[i])) {
               cnt++;
            }
            else {
                tmp = title[i];
                cnt = 1;
            }

            if (cnt > max) {
                max = cnt;
                res = title[i];
            }
        }

        System.out.println(res);
    }
}