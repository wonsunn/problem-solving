import java.io.*;
import java.util.*;

class Main {

    static String addr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        addr = br.readLine();
        int not_zero = 0;

        String colon[] = addr.split(":");

        for (String s : colon) {
            if (!s.equals(""))
                not_zero++;
        }

        StringBuilder sb = new StringBuilder();
        if (addr.substring(0, 2).equals("::")) {
            for (int i = 0; i < 8 - not_zero; i++)
                sb.append("0000:");

            sb.append(solve(2, addr.length()));
        }
        else if (addr.substring(addr.length() - 2, addr.length()).equals("::")) {
            sb.append(solve(0, addr.length() - 2));

            for (int i = 0; i < 8 - not_zero; i++)
                sb.append("0000:");
        }
        else {
            for (int i = 0; i < colon.length; i++) {
                if (colon[i].equals("")) {
                    for (int j = 0; j < 8 - not_zero; j++)
                        sb.append("0000:");
                }
                else {
                    int len = colon[i].length();
                    for (int j = 0; j < 4 - len; j++)
                        sb.append("0");
                    sb.append(colon[i]).append(":");
                }
            }
        }

        System.out.println(sb.toString().substring(0, 39));
    }

    static String solve(int s, int e) {
        StringBuilder sb = new StringBuilder();

        String tmp[] = addr.substring(s, e).split(":");
        for (int i = 0; i < tmp.length; i++) {
            int len = tmp[i].length();

            for (int j = 0; j < 4 - len; j++)
                sb.append("0");
            sb.append(tmp[i]).append(":");
        }

        return sb.toString();
    }
}