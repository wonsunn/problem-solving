import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] phone = new String[n];
            for (int i = 0; i < n; i++)
                phone[i] = br.readLine();

            Arrays.sort(phone);

            boolean flag = true;
            for (int i = 0; i < n - 1; i++) {
                int len = phone[i].length();

                if (phone[i + 1].length() > len && phone[i + 1].substring(0, len).equals(phone[i])) {
                    System.out.println("NO");
                    flag = false;
                    break;
                }
            }

            if (flag) System.out.println("YES");
        }
    }
}
