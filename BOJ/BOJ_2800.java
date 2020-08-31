import java.io.*;
import java.util.*;

class Main {

    static int cnt = 0;
    static char[] c;
    static int[] idx;
    static ArrayList<String> list;
    static Set<String> set = new HashSet<>();
    static Set<Integer> which = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        c = br.readLine().toCharArray();
        idx = new int[c.length];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                s.add(++cnt);
                idx[i] = cnt;
            }
            else if (c[i] == ')') {
                idx[i] = s.pop();
            }
        }

        for (int i = 1; i <= cnt; i++) {
            DFS(i, 0);
            which.clear();
        }

        list = new ArrayList<>(set);
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    static void DFS(int num, int start) {
        if (num == which.size()) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < c.length; j++) {
                if (!which.contains(idx[j])) {
                    sb.append(c[j]);
                }
            }

            set.add(sb.toString());

            return;
        }

        for (int i = start + 1; i <= cnt; i++) {
            which.add(i);
            DFS(num, i);
            which.remove(i);
        }
    }
}