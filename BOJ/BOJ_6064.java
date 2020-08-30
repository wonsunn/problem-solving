import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());

            int ny = x;
            int maxYear = LCM(m, n);
            boolean valid = false;
            for (int i = x; i <= maxYear; i += m) {
                if (ny > n) {
                    if (ny % n == 0) ny = n;
                    else ny = ny % n;
                }

                if (y == ny) {
                    valid = true;
                    System.out.println(i);
                    break;
                }

                ny += m;
            }

            if (!valid) System.out.println(-1);
        }
    }
    
    static int GCD (int a, int b) {
        if (a % b == 0) return b;
        else return GCD(b, a % b);
    }
    
    static int LCM (int a, int b) {
        return a * b / GCD(a, b);
    }
}