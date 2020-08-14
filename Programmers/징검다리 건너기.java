import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int[] arr = Arrays.copyOf(stones, stones.length);
        Arrays.sort(arr);
        
        int left = 0;
        int right = arr[arr.length - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int cur = 0;
            int cnt = 0;    
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] > mid) {
                    cnt = 0;
                    continue;
                }
                
                cnt++;
                cur = Math.max(cur, cnt);
            }
            
            if (cur >= k) {
                right = mid - 1;
                answer = mid;
            }
            else 
                left = mid + 1;
        }
        return answer;
    }
}