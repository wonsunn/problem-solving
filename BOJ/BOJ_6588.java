import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int n = Integer.parseInt(br.readLine());
            boolean flag = false;

            if (n == 0) break;

            for (int i = 3; i <= n / 2; i += 2) {
                int j = n - i;
                if (isPrime(i) && isPrime(j)) {
                    System.out.println(n + " = " + i + " + " + j);
                    flag = true;
                    break;
                }
            }

            if (!flag)
                System.out.println("Goldbach's conjecture is wrong.");
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}