import java.io.*;
import java.util.*;

class Main {

    static int MAX = 1100000;
    static int[] prime = new int[MAX + 1];


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        getPrimeNumber();

        for (int i = num; i <= MAX; i++) {
            if (prime[i] != 0 && isPalindrome(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    static void getPrimeNumber() {
        for (int i = 2; i <= MAX; i++)
            prime[i] = i;

        for (int i = 2; i <= MAX; i++) {
            if (prime[i] == 0) continue;

            for (int j = i + i; j <= MAX; j += i) {
                prime[j] = 0;
            }
        }
    }

    static boolean isPalindrome(int num) {
        String str = String.valueOf(num);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        }
        return true;
    }
}