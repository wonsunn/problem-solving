import java.io.*;
import java.util.*;

class Main {

    static char[] str;
    static Stack<Character> s = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine().toCharArray();

        int res = 0, tmp = 1;
        boolean possible = true;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                tmp *= 2;
                s.push(str[i]);
            }
            else if (str[i] == '[') {
                tmp *= 3;
                s.push(str[i]);
            }
            else if (str[i] == ')') {
                if (!s.isEmpty() && s.peek() == '(') {
                    if (str[i - 1] == '(')
                        res += tmp;

                    s.pop();
                    tmp /= 2;
                }
                else {
                    possible = false;
                    break;
                }
            }
            else if (str[i] == ']') {
                if (!s.isEmpty() && s.peek() == '[') {
                    if (str[i - 1] == '[')
                        res += tmp;

                    s.pop();
                    tmp /= 3;
                }
                else {
                    possible = false;
                    break;
                }
            }
        }

        if (!s.isEmpty())
            possible = false;

        if (!possible) {
            System.out.println(0);
            return;
        }
        System.out.println(res);
    }
}