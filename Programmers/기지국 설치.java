import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int len = stations.length;
        List<Integer> list = new ArrayList<>();
        
        if (stations[0] - w >= 1)
            list.add((stations[0] - w - 1));
        
        for (int i = 0; i < len - 1; i++) {
            int start = stations[i] + w + 1;
            int end = stations[i + 1] - w - 1;
            if (end >= start) 
                list.add(end - start + 1);       
        }
        
        if (stations[len - 1] + w <= n) 
            list.add(n - (stations[len - 1] + w + 1) + 1);

        int width = w * 2 + 1;
        int answer = 0;
        for (int num : list) {
            if (num % width == 0) answer += (num / width);
            else answer += (num / width + 1);
        }
        
        return answer;
    }
}