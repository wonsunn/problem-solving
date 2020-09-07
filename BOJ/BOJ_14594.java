import java.io.*;
import java.util.*;

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] room = new int[n + 1];
        for (int i = 1; i <= n; i++)
            room[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
            int r = room[x];
            
            for (int j = x + 1; j <= y; j++) {
                if (room[j] != r)
                    room[j] = r;
            }
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (room[i] == i)
                cnt++;
        }

        System.out.println(cnt);
    }
}