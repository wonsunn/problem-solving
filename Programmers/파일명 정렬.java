import java.util.*;

class Solution {
    
    List<File> fileList = new ArrayList<>();
    int idx;
    
    public String[] solution(String[] files) {
        String[] answer = {};
    
        for (String file : files) {
            String h = file.split("[0-9]")[0];
            String tmp = file.replace(h, "");
            
            if (tmp.length() > 5) tmp = tmp.substring(0, 5);
            else tmp = tmp.substring(0, tmp.length());
            String n = tmp.split("[^0-9]")[0];
            
            fileList.add(new File(h.toUpperCase(), Integer.parseInt(n), idx++));
        }
        
        Collections.sort(fileList, new Comparator<File>(){
            public int compare(File f1, File f2) {
                if (f1.head.equals(f2.head)) {
                    if (f1.number == f2.number) 
                        return f1.order - f2.order;
                    else
                        return f1.number - f2.number;
                }
                else
                    return f1.head.compareTo(f2.head);
            }
        });
        
        idx = 0;
        answer = new String[fileList.size()];
        for (File f : fileList)
            answer[idx++] = files[f.order];
        
        return answer;
    }
}

class File {
    String head;
    int number, order;
    
    File (String head, int number, int order) {
        this.head = head;
        this.number = number;
        this.order = order;
    }
}