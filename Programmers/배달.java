import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        List<Info>[] map = new ArrayList[N + 1];
        for (int i = 0; i < map.length; i++) 
            map[i] = new ArrayList<>();
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        for (int i = 0; i < road.length; i++) {
            map[road[i][0]].add(new Info(road[i][1], road[i][2]));
            map[road[i][1]].add(new Info(road[i][0], road[i][2]));
        }
        
        for (int t = 1; t <= N; t++) {
            int min = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dist[i] < dist[min]) min = i;
            }
            visited[min] = true;
            
            for (int i = 0; i < map[min].size(); i++) {
                if (dist[min] + map[min].get(i).dis < dist[map[min].get(i).town])
                    dist[map[min].get(i).town] = dist[min] + map[min].get(i).dis;
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) 
                answer++;
        }

        return answer;
    }
}

class Info {
    int town, dis;
    
    Info (int town, int dis) {
        this.town = town;
        this.dis = dis;
    }
}