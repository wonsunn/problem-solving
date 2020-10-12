import java.io.*;
import java.util.*;

class Main {

    private static class Info {
        int posF, posS;

        Info (int posF, int posS) {
            this.posF = posF;
            this.posS = posS;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String first = st.nextToken();
            String second = st.nextToken();
            String third = st.nextToken();

            if (first.concat(second).equals(third) || second.concat(first).equals(third)) {
                System.out.println("Data set " + i + ": yes");
                continue;
            }

            boolean[][] visited = new boolean[first.length() + 1][second.length() + 1];

            Queue<Info> q = new LinkedList<>();
            q.add(new Info(0, 0));

            boolean yes = false;
            while (!q.isEmpty()) {
                int posF = q.peek().posF;
                int posS = q.peek().posS;
                int posStr = posF + posS;

                q.poll();

                if (posF == first.length() && posS == second.length() && posStr == third.length()) {
                    yes = true;
                    break;
                }

                if (posF < first.length() && !visited[posF + 1][posS] && first.charAt(posF) == third.charAt(posStr)) {
                    q.add(new Info(posF + 1, posS));
                    visited[posF + 1][posS] = true;
                }
                if (posS < second.length() && !visited[posF][posS + 1] && second.charAt(posS) == third.charAt(posStr)) {
                    q.add(new Info(posF, posS + 1));
                    visited[posF][posS + 1] = true;
                }
            }

            if (yes) System.out.println("Data set " + i + ": yes");
            else System.out.println("Data set " + i + ": no");
        }
    }
}
