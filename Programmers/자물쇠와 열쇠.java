import java.util.*;

class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        int size = lock.length - 1;
        
        for (int d = 0; d < 4; d++) {
            int[][] rotateKey = rotate(key, d);
            int[][] paddingKey = padding(rotateKey, size);
            
            for (int R = 0; R < paddingKey.length - size; R++) {
                for (int C = 0; C < paddingKey[0].length - size; C++) {
                    
                    boolean flag = true;
                    for (int r = 0; r < lock.length; r++) {
                        for (int c = 0; c < lock[0].length; c++) {
                            if (paddingKey[r + R][c + C] == lock[r][c])
                                flag = false;
                        }
                    }
                    if (flag) return true;
                }
            }
        }
        return false;
    }
    
    private int[][] rotate(int[][] arr, int d) {
        if (d == 0) return arr;
        
        int[][] result = null;

        for (int t = 0; t < d; t++) {
            result = new int[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    result[j][arr.length - 1 - i] = arr[i][j];
                }
            }
            arr = Arrays.copyOf(result, result.length);
        }
        return result;
    }
    
    private int[][] padding(int[][] arr, int size) {
        int[][] result = new int[arr.length + size * 2][arr[0].length + size * 2];
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                result[i + size][j + size] = arr[i][j];
            }
        }
        return result;
    }
}