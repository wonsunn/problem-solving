import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        LinkedList<Integer> q = new LinkedList<>();
        Queue<Integer> table = new PriorityQueue<>();
        
        for (int i = 0; i < timetable.length; i++) {
            int hour = Integer.parseInt(timetable[i].split(":")[0]);
            int minute = Integer.parseInt(timetable[i].split(":")[1]);
            table.add(hour * 60 + minute);
        }
        
        int busTime = 540, conTime = 0;
        int person;
        while (n-- > 0) {
            person = 0;
            
            while (true) {
                int tmp = table.peek();
                if (tmp <= busTime) {
                    q.addLast(table.poll());
                    person++;
                }
                else 
                    break; 
                
                if (table.isEmpty() || person == m) 
                    break;
            }
            
            if (n == 0) {
                if (person == m) 
                    conTime = q.peekLast() - 1;
                else 
                    conTime = busTime;
            }
            
            busTime += t;
        }
        
        return String.format("%02d:%02d", conTime / 60, conTime % 60);
    }  
}