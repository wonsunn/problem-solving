import java.io.*;
import java.util.*;

class Main {

    static int n;
    static Stack<Character> left = new Stack<>();
    static Stack<Character> right = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
            left.add(str.charAt(i));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            if (c == 'P')
                edit(st.nextToken().charAt(0));
            else
                edit(c);
        }

        while (!left.isEmpty())
            right.push(left.pop());

        while (!right.isEmpty())
            System.out.print(right.pop());
    }

    static void edit(char what) {
        switch(what) {
            case 'L':
                if (!left.isEmpty())
                    right.push(left.pop());
                break;
            case 'D':
                if (!right.isEmpty())
                    left.push(right.pop());
                break;
            case 'B':
                if (!left.isEmpty())
                    left.pop();
                break;
            default:
                left.push(what);
                break;
        }
    }
}