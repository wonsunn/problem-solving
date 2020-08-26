import java.io.*;
import java.util.*;

class Main {

    static int t, n;
    static String method, num;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            method = br.readLine();
            n = Integer.parseInt(br.readLine());
            num = br.readLine();
            String[] tmp = num.substring(1, num.length() - 1).split(",");

            boolean front = true;
            boolean isError = false;

            list = new LinkedList<>();
            for (int i = 0; i < n; i++)
                list.add(Integer.parseInt(tmp[i]));

            for (int i = 0; i < method.length(); i++) {
                if (method.charAt(i) == 'R')
                    front = !front;

                else {
                    if (list.size() == 0) {
                        isError = true;
                        System.out.println("error");
                        break;
                    }
                    if (front)
                        list.pollFirst();
                    else
                        list.pollLast();
                }
            }
            
            if (!isError) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");

                if (front) {
                    while (!list.isEmpty()) {
                        sb.append(list.pollFirst());
                        if (!list.isEmpty())
                            sb.append(",");
                    }
                }
                else {
                    while (!list.isEmpty()) {
                        sb.append(list.pollLast());
                        if (!list.isEmpty())
                            sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}