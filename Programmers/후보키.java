import java.util.*;

class Solution { 
    
    static int rowSize, colSize;
    static List<Integer> candidate = new ArrayList<>();
    
    public int solution(String[][] relation) { 
        rowSize = relation.length; 
        colSize = relation[0].length; 
         
        for (int i = 0; i < (1 << colSize); i++) {
            if (!isMinimal(i)) continue; // 최소성을 만족하지 못하면 패스
            if (!isUnique(i, relation)) continue; // 유일성을 만족하지 못하면 패스
            
            candidate.add(i);
        }
        
        return candidate.size();
    }
    
    static boolean isMinimal(int set) {
        for (int c : candidate) {
            if ((set & c) == c) return false;
        }
        return true;
    }
    
    static boolean isUnique(int set, String[][] relation) {
        List<Integer> columnList = getColumn(set);
        Set<String> hashset = new HashSet<>();
        
        for (int i = 0; i < rowSize; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j : columnList) {
                sb.append(relation[i][j]).append(" ");
            }   
            hashset.add(sb.toString());
        }
        
        return hashset.size() == rowSize;
    }
    
    // '0101' -> [0,2] 반환
    static List<Integer> getColumn(int set) { 
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < colSize; i++) {
            if (((set >> i) & 1) == 1) list.add(i);
        }
        
        return list;
    }
}
}