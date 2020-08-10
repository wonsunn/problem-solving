import java.util.*;

class Solution {
    
    static int[] startTime, endTime;
    static int answer = 0;
    
    public int solution(String[] lines) {
        
        startTime = new int[lines.length];
        endTime = new int[lines.length];
        getTimes(lines, startTime, endTime);
        
        solvedByStart(lines);
        solvedByEnd(lines);
        
        return answer;
    }
    
    static void getTimes(String[] lines, int[] startTime, int[] endTime) {
         for (int i = 0; i < lines.length; i++) {
            String[] log = lines[i].split(" ");
            String[] responseTime = log[1].split(":");
            int processTime = (int)(Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
            int start, end = 0;
            end += Integer.parseInt(responseTime[0]) * 60 * 60 * 1000;
            end += Integer.parseInt(responseTime[1]) * 60 * 1000;
            end += (int)(Double.parseDouble(responseTime[2]) * 1000);
            start = end - processTime + 1;
            
            startTime[i] = start;
            endTime[i] = end;
        }
    }
    
    static void solvedByStart(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            int s = startTime[i];
            int e = s + 1000;
            int cnt = 0;
            for (int j = 0; j < lines.length; j++) {
                if (startTime[j] >= s && startTime[j] < e) cnt++;
                else if (endTime[j] >= s && endTime[j] < e) cnt++;
                else if (startTime[j] <= s && endTime[j] >= e) cnt++;
                
                answer = Math.max(answer, cnt);
            }
        }
    }
    
    static void solvedByEnd(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            int s = endTime[i];
            int e = s + 1000;
            int cnt = 0;
            for (int j = 0; j < lines.length; j++) {
                if (startTime[j] >= s && startTime[j] < e) cnt++;
                else if (endTime[j] >= s && endTime[j] < e) cnt++;
                else if (startTime[j] <= s && endTime[j] >= e) cnt++;
                
                answer = Math.max(answer, cnt);
            }
        }
    }
}