import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int timeMax = 0;
        
        m = convert(m);
            
        for (String music : musicinfos) {
            String[] info = music.split(",");
            
            int start = (60 * Integer.parseInt(info[0].substring(0, 2))) + Integer.parseInt(info[0].substring(3));
            int end = (60 * Integer.parseInt(info[1].substring(0, 2))) + Integer.parseInt(info[1].substring(3));
            int time = end - start;
         
            if (time > timeMax) {
                StringBuilder sb = new StringBuilder();
                String melody = convert(info[3]);
                
                for (int i = 0; i < time; i++) 
                    sb.append(melody.charAt(i % melody.length()));
                
                if (sb.toString().contains(m)) {
                    timeMax = time;
                    answer = info[2];
                }
            }
        }
        return answer;
    }
    
    static String convert(String m) {
        m = m.replaceAll("C#", "c");
        m = m.replaceAll("D#", "d");
        m = m.replaceAll("F#", "f");
        m = m.replaceAll("G#", "g");
        m = m.replaceAll("A#", "a");
        
        return m;
    }
}