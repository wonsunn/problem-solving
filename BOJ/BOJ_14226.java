import java.io.*;
import java.util.*;

class Main {

    static int s;
    static boolean[][] visited = new boolean[10001][10001];
    static Queue<Info> q = new LinkedList<>();

    private static class Info {
        int screen, clip, cnt;

        Info(int screen, int clip, int cnt) {
            this.screen = screen;
            this.clip = clip;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = Integer.parseInt(br.readLine());

        q.add(new Info(1, 0, 0));
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.screen == s) {
                System.out.println(cur.cnt);
                break;
            }

            //연산 1 : 화면 -> 클립보드 복사
            if (!visited[cur.screen][cur.screen]) {
                q.add(new Info(cur.screen, cur.screen, cur.cnt + 1));
                visited[cur.screen][cur.screen] = true;
            }

            // 연산 2 : 클립보드 -> 화면 붙여넣기
            if (cur.clip != 0) {
                int ns = cur.screen + cur.clip;
                if (!visited[ns][cur.clip]) {
                    q.add(new Info(ns, cur.clip, cur.cnt + 1));
                    visited[ns][cur.clip] = true;
                }
            }

            // 연산 3 : 화면에 있는 이모티콘 하나 삭제
            if (cur.screen > 0) {
                if (!visited[cur.screen - 1][cur.clip]) {
                    q.add(new Info(cur.screen - 1, cur.clip, cur.cnt + 1));
                    visited[cur.screen - 1][cur.clip] = true;
                }
            }
        }
    }
}