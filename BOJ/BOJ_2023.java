import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer> primeArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] st = {2, 3, 5, 7};

        for(int i : st) DFS(i, 1);

        Collections.sort(primeArr);
        for (int prime : primeArr) System.out.println(prime);
    }

    static void DFS(int num, int L) {
        if (L == n) {
            if (isPrime(num)) primeArr.add(num);
            return;
        }

        if (!isPrime(num)) return;

        for (int i = 0; i < 10; i++) {
            int tmp = num;
            num = (num * 10) + i;
            DFS(num, L + 1);
            num = tmp;
        }
    }

    static Boolean isPrime(int num) {
        if (num == 1) return false;

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}