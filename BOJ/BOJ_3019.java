import java.io.*;
import java.util.*;

class Main {

    static int c, p, res = 0;
    static int[] height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        height = new int[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        switch(p) {
            case 1:
                res = c + getCnt(new int[]{0, 0, 0, 0});
                break;
            case 2:
                res = getCnt(new int[]{0, 0});
                break;
            case 3:
                res = getCnt(new int[]{1, 1, 0}) + getCnt(new int[]{0, 1});
                break;
            case 4:
                res = getCnt(new int[]{0, 1, 1}) + getCnt(new int[]{1, 0});
                break;
            case 5:
                res = getCnt(new int[]{0, 0, 0}) + getCnt(new int[]{1, 0}) + getCnt(new int[]{0, 1, 0}) + getCnt(new int[]{0, 1});
                break;
            case 6:
                res = getCnt(new int[]{0, 0, 0}) + getCnt(new int[]{0, 0}) + getCnt(new int[]{1, 0, 0}) + getCnt(new int[]{0, 2});
                break;
            case 7:
                res = getCnt(new int[]{0, 0, 0}) + getCnt(new int[]{2, 0}) + getCnt(new int[]{0, 0, 1}) + getCnt(new int[]{0 ,0});
                break;
        }

        System.out.println(res);
    }

    static int getCnt(int[] arr) {
        int sum = 0, ans = 0;

        for (int i = 0; i <= c - arr.length; i++) {
            ans = arr[0] + height[i];
            boolean flag = true;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] + height[i + j] != ans) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sum++;
            }
        }

        return sum;
    }
}