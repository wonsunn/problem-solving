import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();
        
        if (cacheSize == 0) return cities.length * 5;
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            
            if (cache.remove(city)) {
                cache.add(city);
                answer += 1;
            }
            else {
                if (cache.size() == cacheSize) cache.poll();
                
                cache.add(city);
                answer += 5;
            }
        }
        return answer;
    }
}