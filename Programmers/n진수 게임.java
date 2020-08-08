import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        char[] numeric = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        
        int len = t * m;
        char[] seq = new char[len];
        LinkedList<Character> list = new LinkedList<>();
        
        int tmp = 0, number = 0;
        int idx = number;
        while (idx < len) {
            if (tmp < n) {
                seq[idx++] = numeric[tmp];
                while (!list.isEmpty() && idx < len) {
                    seq[idx++] = list.pollLast();
                }
                tmp = ++number;
            }
            else {
                list.add(numeric[tmp % n]);
                tmp /= n;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = p - 1; i < seq.length; i += m) {
            sb.append(seq[i]);
        }
        
        return sb.toString();
    }
}