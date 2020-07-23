import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int j = Integer.parseInt(br.readLine());
        int dir = 0, idx = 1;
        
        for (int i = 0; i < j; i++) {
            int num = Integer.parseInt(br.readLine());

            while (true) {
                Boolean flag = false;

                for (int k = idx; k <= idx + m - 1; k++) {
                    if (k == num) {
                        flag = true;
                        break;
                    }
                }

                if (flag) break;

                if (idx < num) idx++;
                else idx--;

                dir++;
            }
        }
        System.out.println(dir);
    }
}