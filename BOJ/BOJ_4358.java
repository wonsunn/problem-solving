import java.io.*;
import java.util.*;

public class BOJ_4358 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> map = new HashMap<>();
		int cnt = 0;
		
		while (true) {
			String tree = br.readLine();
			if (tree == null || tree.length() == 0) break;
			
			map.put(tree, map.getOrDefault(tree, 0) + 1);
			cnt++;
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (m1, m2) -> m1.getKey().compareTo(m2.getKey()));
				
		for (Map.Entry<String, Integer> m : list) {
			double p = (double)m.getValue() / cnt * 100;
			sb.append(m.getKey()).append(' ').append(String.format("%.4f", p));
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}