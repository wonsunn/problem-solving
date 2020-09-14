import java.io.*;
import java.util.*;

class Main {

    static String str;
    static LinkedList<Long> number = new LinkedList<>();
    static LinkedList<String> giho = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        boolean minus = false;
        if (str.charAt(0) == '-') {
            minus = true;
            str = str.substring(1);
        }

        String[] tmp = str.split("[^0-9]");

        int idx = 0;
        for (String s : tmp) {
            if (minus && idx++ == 0) {
                number.add(-Long.parseLong(s));
                continue;
            }
            number.add(Long.parseLong(s));
        }

        tmp = str.substring(1).split("[0-9]");
        for (String s : tmp) {
            if (!s.equals(""))
                giho.add(s);
        }

        while (true) {
            if (number.size() == 1) {
                System.out.println(number.pollFirst());
                break;
            }

            solve();
        }
    }

    static void solve() {
        boolean front = false, back = false;
        String g1 = giho.peekFirst(); String g2 = giho.peekLast();

        long front_ans = calculate(0, g1);
        long back_ans = calculate(number.size() - 2, g2);

        // 우선순위에 따라 결정
        if (priorGiho(g1) > priorGiho(g2)) front = true;
        else if (priorGiho(g1) < priorGiho(g2)) back = true;
        // 결과값이 같거나 앞이 크면 앞쪽 계산, 뒤가 크면 뒤쪽 계산
        else {
            if (front_ans >= back_ans) front = true;
            else back = true;
        }

        if (front) {
            for (int i = 0; i < 2; i++) number.pollFirst();
            number.addFirst(front_ans);
            giho.pollFirst();
        }
        else {
            for (int i = 0; i < 2; i++) number.pollLast();
            number.addLast(back_ans);
            giho.pollLast();
        }
    }

    static long calculate(int idx, String giho) {
        switch(giho) {
            case "+": return number.get(idx) + number.get(idx + 1);
            case "-": return number.get(idx) - number.get(idx + 1);
            case "*": return number.get(idx) * number.get(idx + 1);
            case "/": return number.get(idx) / number.get(idx + 1);
        }
        return 0;
    }

    static int priorGiho(String giho) {
        if (giho.equals("+") || giho.equals("-")) return 1;
        else return 2;
    }
}