import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        LinkedList<Character> ll = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!ll.isEmpty() && k > 0 && ll.peekLast() < str.charAt(i)) {
                ll.pollLast();
                k--;
            }

            ll.addLast(str.charAt(i));
        }

        // k가 0이 되기 전에 연산이 끝날 경우, (ll.size() - k)의 개수만큼 뽑아내기
        while (ll.size() > k) 
            System.out.print(ll.pollFirst());
    }
}