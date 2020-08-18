import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer;
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        int start = 0, startPoint = 0;
        int end = Integer.MAX_VALUE;
        
        for (String gem : gems) set.add(gem);
        
        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            q.add(gems[i]);
            
            while (true) {
                String tmp = q.peek();
                if (map.get(tmp) > 1) {
                    q.poll();
                    start++;
                    map.put(tmp, map.get(tmp) - 1);
                }
                else {
                    break;
                }
            }
            
            if (map.size() == set.size() && end > q.size()) {
                end = q.size();
                startPoint = start;
            }
        }
        
        return new int[]{startPoint + 1, startPoint + end};
    }
}