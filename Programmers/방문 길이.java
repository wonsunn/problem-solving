import java.util.*;

class Solution {
     
    public int solution(String dirs) {
        int x, y, dx, dy;

        Set<String> set = new HashSet<>();
        Map<Character, int[]> move = new HashMap<>();
        move.put('U', new int[]{-1, 0});
        move.put('D', new int[]{1, 0});
        move.put('R', new int[]{0, 1});
        move.put('L', new int[]{0, -1});
        
        x = 5; y = 5;
        for (int i = 0; i < dirs.length(); i++) {
            dx = x; dy = y;
            dx += move.get(dirs.charAt(i))[0];
            dy += move.get(dirs.charAt(i))[1];
            
            if (dx < 0 || dx >= 11 || dy < 0 || dy >= 11) 
                continue;
            if (set.contains(x + "" + y + "" + dx + "" + dy) ||
               set.contains(dx + "" + dy + "" + x + "" + y)) {
                x = dx; y = dy;
                continue;
            }
            
            set.add(x + "" + y + "" + dx + "" + dy);
            set.add(dx + "" + dy + "" + x + "" + y);

            x = dx; y = dy;
        }
        return set.size() / 2;
    }
}