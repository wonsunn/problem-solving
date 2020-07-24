import java.util.*;

class Solution {
    static List<String> arr1 = new ArrayList<>();
    static List<String> arr2 = new ArrayList<>();
    
    public int solution(String str1, String str2) {
        int answer = 0;
        int union, intersect = 0;
        
        arr1 = getArr(str1);
        arr2 = getArr(str2);
        
        List<String> tmp = new ArrayList<>();
        tmp.addAll(arr2);
        for (String s1 : arr1) {
            for (String s2 : tmp) {
                if (s1.equals(s2)) {
                    intersect++;
                    tmp.remove(s2);
                    break;
                }
            }
        }
        union = arr1.size() + arr2.size() - intersect;
        
        if (union == 0 && intersect == 0) return 65536;
        else return (int)(((double)intersect / (double)union) * 65536);
                        
    }
    
    static Boolean isRange(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) return true;
        else return false;
    }
    
    static ArrayList<String> getArr(String str) {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            if (isRange(str.charAt(i)) && isRange(str.charAt(i + 1))) arr.add(str.substring(i, i + 2).toUpperCase());
        }
        
        return arr;
    }
}