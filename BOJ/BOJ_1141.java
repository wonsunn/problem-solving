import java.io.*;
import java.util.*;

class Main {

    static int n, res = 0;
    static String[] words;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        words = new String[n];
        check = new boolean[n];

        for (int i = 0; i < n; i++)
            words[i] = br.readLine();

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].equals(words[j].substring(0, words[i].length()))) {
                    check[i] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!check[i]) res++;
        }

        System.out.println(res);
    }
}