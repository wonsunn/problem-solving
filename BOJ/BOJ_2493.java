import java.io.*;
import java.util.*;

class Main {

    static int n;
    static Stack<Info> s = new Stack<>();
    
    private static class Info {
        int h, idx;
        
        Info (int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            
            while (!s.isEmpty()) {
                if (height < s.peek().h) {
                    System.out.print(s.peek().idx + " ");
                    break;
                }
                
                s.pop();
            }
            
            if (s.isEmpty())
                System.out.print(0 + " ");
            
            s.push(new Info(height, i));
        }
    }
}
