import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Work> pq = new PriorityQueue<>(new Comparator<Work>(){
            public int compare(Work w1, Work w2) {
                return w1.workTime - w2.workTime;
            }
        });
        
        LinkedList<Work> waiting = new LinkedList<>();
        
        for (int[] job : jobs) 
            waiting.add(new Work(job[0], job[1]));
        
        Collections.sort(waiting, new Comparator<Work>(){
            public int compare(Work w1, Work w2) {
                return w1.reqTime - w2.reqTime;
            }
        });
        
        int cnt = 0;
        int answer = 0;
        int time = waiting.peek().reqTime;
        while (cnt < jobs.length) {
            while (!waiting.isEmpty() && waiting.peek().reqTime <= time) {
                pq.add(waiting.pollFirst());
            }
            
            if (!pq.isEmpty()) {
                Work work = pq.poll();
                time += work.workTime;
                answer += (time - work.reqTime); 
                cnt++;
            }
            else {
                time++;
            }
        }
        return answer / cnt;
    }
}

class Work {
    int reqTime, workTime;
    
    Work (int reqTime, int workTime) {
        this.reqTime = reqTime; this.workTime = workTime;
    }
}