import java.util.*;

class Solution {
    static int[][] pad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Cur cl = new Cur(3, 0);
        Cur cr = new Cur(3, 2);
        
        int num = 1;
        
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) answer += "L";
             
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) answer += "R";
    
            else {
                Cur dest = convertToCur(numbers[i]);
                int dirL = calDistance(cl.x, cl.y, dest.x, dest.y); 
                int dirR = calDistance(cr.x, cr.y, dest.x, dest.y);
                if (dirL == dirR) {
                    if (hand.equals("right")) answer += "R";
                    else answer += "L";
                }
                else if (dirL < dirR) answer += "L";
                else answer += "R";
            }
            
            if (answer.charAt(answer.length() - 1) == 'R') cr = convertToCur(numbers[i]);
            else cl = convertToCur(numbers[i]);
        }
        
        return answer;
    }
    
    static int calDistance(int x, int y, int dx, int dy) {
        return Math.abs(x - dx) + Math.abs(y - dy);
    }
    
    static Cur convertToCur(int num) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (pad[i][j] == num) {
                    return new Cur(i, j);
                }
            }
        }
        return new Cur(0, 0);
    }
}
class Cur {
    int x, y;
    
    Cur (int x, int y) {
        this.x = x; this.y = y;
    }
}
