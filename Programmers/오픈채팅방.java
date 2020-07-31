import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer;
        Map<String, String> nickname = new HashMap<>();
        List<String> log = new ArrayList<>();
        
        for (String r : record) {
            StringTokenizer st = new StringTokenizer(r);
            String command = st.nextToken();
            String id = st.nextToken();
            String name = "";
            
            if (!command.equals("Leave")) {
                name = st.nextToken();
            }
            
            switch (command) {
                case "Enter":
                    log.add(id + "님이 들어왔습니다.");
                    nickname.put(id, name);
                    break;
                case "Leave":
                    log.add(id + "님이 나갔습니다.");
                    break;
                case "Change":
                    nickname.put(id, name);
                    break;
            }
        }
        
        int idx = 0;
        answer = new String[log.size()];
        for (String str : log) {
            String logId = str.substring(0, str.indexOf("님"));
            answer[idx++] = str.replace(logId, nickname.get(logId));
        }
 
        return answer;
    }
}