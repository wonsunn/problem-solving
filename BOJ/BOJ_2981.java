import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] number = new int[n];

        for (int i = 0; i < n; i++)
            number[i] = Integer.parseInt(br.readLine());

        Arrays.sort(number);

        int gcd = number[1] - number[0];
        for (int i = 2; i < n; i++)
            gcd = GCD(gcd, number[i] - number[i - 1]);

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i * i <= gcd; i++) {
            if (i * i == gcd) {
                result.add(i);
                continue;
            }
            if (gcd % i == 0) {
                result.add(i);
                result.add(gcd / i);
            }
        }
        result.add(gcd);

        Collections.sort(result);
        for (int i : result)
            System.out.print(i + " ");
    }

    static int GCD(int a, int b) {
        if (a % b == 0)
            return b;
        else
            return GCD(b, a % b);
    }
}