import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();
        
        for (int i : moves) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i - 1] != 0) {
                    if (basket.isEmpty()) basket.push(board[j][i - 1]);
                    else if (basket.peek() == board[j][i - 1]) {
                        basket.pop();
                        answer += 2;
                    }
                    else basket.push(board[j][i - 1]);
                    
                    board[j][i - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}