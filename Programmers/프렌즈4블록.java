import java.util.*;

class Solution {
    
    static boolean[][] visited;
    static char[][] newBoard;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        newBoard = new char[m][n];
        for (int i = 0; i < m; i++)
            newBoard[i] = board[i].toCharArray();
        
        while (true) {
            boolean erased = false;
            visited = new boolean[m][n];
            
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (newBoard[i][j] == '*') continue;
                    
                    if (isErased(i, j)) {
                        erased = true;
                        for (int k = i; k < i + 2; k++) {
                            for (int l = j; l < j + 2; l++) {
                                if (!visited[k][l]) {
                                    visited[k][l] = true;
                                    answer++;
                                }
                            }
                        }
                    }
                }
            }
            if (!erased) break; // 지워질 블록이 없으면 중단
            
            setBlocked(m, n);
            doBlockDown(m, n);
        }
        return answer;
    }
    
    // 지워질 블록 blocked 처리
    static void setBlocked(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] && newBoard[i][j] != '*') {
                    newBoard[i][j] = '*';
                }
            }
        }
    }
    
    // 지워진 블록 처리 후 남은 빈공간 채우기
    static void doBlockDown(int m, int n) {
        for (int j = 0; j < n; j++) {
            while (true) {
                boolean flag = true;
                for (int i = m - 2; i >= 0; i--) {
                    // 현재 블록은 빈공간이 아니고 아래 블록은 빈공간일 때 내려갈 수 있음
                    if (newBoard[i][j] != '*' && newBoard[i + 1][j] == '*') {
                        newBoard[i + 1][j] = newBoard[i][j];
                        newBoard[i][j] = '*';
                        flag = false;
                    }
                }
                if (flag) break;
            }
        }
    } 
    
    // 4개의 블록 지워질 것인지 여부
    static boolean isErased(int x, int y) {
        if (newBoard[x][y] == newBoard[x][y + 1] && newBoard[x][y + 1] == newBoard[x + 1][y]
           && newBoard[x + 1][y] == newBoard[x + 1][y + 1])
            return true;
        else return false;
    }
}