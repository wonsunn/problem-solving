import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, max;
    static Integer[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        score = new Integer[n];
        int num = 0, gijun = 0;

        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(score, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            if (score[i] + n >= gijun) num++;
            gijun = Math.max(gijun, score[i] + i + 1);
        }

        System.out.println(num);
    }
}