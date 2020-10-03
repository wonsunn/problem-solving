import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1); // 최대힙
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i1, i2) -> i1 - i2); // 최소힙

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());
            
            if (maxHeap.size() == minHeap.size()) maxHeap.add(val);
            else minHeap.add(val);
            
            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (minHeap.peek() < maxHeap.peek()) {
                    int tmp = minHeap.poll();
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(tmp);
                }
            }

            System.out.println(maxHeap.peek());
        }
    }
}

