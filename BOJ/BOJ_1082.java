import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] price = new int[n];
        char[] res;
        st = new StringTokenizer(br.readLine());

        int min = Integer.MAX_VALUE, idx = 0;
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            if (price[i] <= min) {
                min = price[i];
                idx = i;
            }
        }

        int money = Integer.parseInt(br.readLine());
        int len = money / min;
        res = new char[len];
        money %= min;
        Arrays.fill(res, (char)(idx + '0'));

        int s = 0;
        for (int i = 0; i < len; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (price[j] <= money + min) {
                    res[i] = (char)(j + '0');
                    money += min - price[j];
                    break;
                }
            }

            if (res[s] == '0') {
                s++;
                money += min;
            }
        }

        if (s == len) {
            System.out.println(0);
            return;
        }

        String ans = "";
        for (int i = s; i < len; i++)
            ans += res[i];

        System.out.println(ans);

    }
}