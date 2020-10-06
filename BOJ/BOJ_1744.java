import java.io.*;
import java.util.*;

class Main {

    static int n, res = 0;
    static List<Integer> minus = new ArrayList<>();
    static List<Integer> plus = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            
            if (num <= 0) minus.add(num);
            else if (num > 1) plus.add(num);
            else res += num;
        }

        if (!minus.isEmpty()) Collections.sort(minus, (i1, i2) -> i1 - i2);
        if (!plus.isEmpty()) Collections.sort(plus, (i1, i2) -> i2 - i1);

        res += solve(plus);
        res += solve(minus);

        System.out.println(res);
    }

    static int solve(List<Integer> list) {
        int ans = 0, val = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0)
                val = list.get(i);
            else
                ans += val * list.get(i);
        }

        return list.size() % 2 == 0 ? ans : ans + val;
    }
}