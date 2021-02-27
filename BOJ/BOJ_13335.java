import java.io.*;
import java.util.*;

public class BOJ_13335 {

	private static class Truck {
		int weight, state;
		
		Truck (int weight, int state) {
			this.weight = weight;
			this.state = state;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		Queue<Integer> ready_queue = new LinkedList<>();
		LinkedList<Truck> bridge = new LinkedList<>();
		int time = 0, weight = 0;
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			ready_queue.add(Integer.parseInt(st.nextToken()));
		
		while (!ready_queue.isEmpty()) {
			if (bridge.isEmpty()) {
				int truck = ready_queue.poll();
				bridge.add(new Truck(truck, 1));
				weight += truck;
			}
			else {
				boolean exit = false;
				for (Truck t : bridge) {
					if (t.state == w) {
						exit = true;
						weight -= t.weight;
					}
					else {
						t.state++;
					}
				}
				if (exit) bridge.poll();
				
				int truck = ready_queue.peek();
				if (weight + truck <= L) {
					ready_queue.poll();
					bridge.add(new Truck(truck, 1));
					weight += truck;
				}
			}

			time++;
		}
		
		time += w;
		
		System.out.println(time);
	}
}