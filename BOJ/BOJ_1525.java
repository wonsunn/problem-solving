import java.io.*;
import java.util.*;

class Main {

    static final String target = "123456789";
    static Queue<String> q = new LinkedList<>();
    static Map<String, Integer> map = new HashMap<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 0)
                    input = 9;

                tmp = tmp * 10 + input;
            }
        }
        String strTmp = String.valueOf(tmp);
        map.put(strTmp, 0);
        q.add(strTmp);

        System.out.println(BFS());
    }

    static int BFS() {
        while (!q.isEmpty()) {
            String cur = q.poll();

            if (cur.equals(target)) {
                return map.get(cur);
            }

            // 0의 위치 찾기
            int zeroIdx = cur.indexOf('9');
            int x = zeroIdx / 3;
            int y = zeroIdx % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;

                // 이동하기(swap을 통해)
                char[] tmpArr = cur.toCharArray();
                char tmp = tmpArr[zeroIdx];
                tmpArr[zeroIdx] = tmpArr[nx * 3 + ny];
                tmpArr[nx * 3 + ny] = tmp;

                String next = String.valueOf(tmpArr);

                if (!map.containsKey(next)) {
                    map.put(next, map.get(cur) + 1);
                    q.add(next);
                }
            }
        }

        return -1;
    }
}