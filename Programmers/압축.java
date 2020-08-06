import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        int idx = 26;
        Map<String, Integer> dict = new HashMap<>();        
        for (int i = 0; i < idx; i++) 
            dict.put(String.valueOf((char)(65 + i)), i + 1);
        
        String w = "";
        for (int i = 0; i < msg.length(); i++) {
            w += msg.charAt(i);
    
            if (!dict.containsKey(w)) {
                dict.put(w, ++idx);
                
                answer.add(dict.get(w.substring(0, w.length() - 1)));
                w = String.valueOf(msg.charAt(i));
            }
        }
        answer.add(dict.get(w)); // 마지막 단어 처리
        
        int[] ans = new int[answer.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = answer.get(i).intValue();
        
        return ans;
    }
}