import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int s = Integer.parseInt(br.readLine());

        List<Student> frame = new ArrayList<>();
        for (int i = 0; i < n; i++) frame.add(new Student(0,0));

        int[] cnt = new int[s + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            int rec = Integer.parseInt(st.nextToken());
            Boolean replace = true;

            cnt[rec]++;

            for (int j = 0; j < n; j++) {
                if (frame.get(j).num == 0) {
                    frame.set(j, new Student(rec, i));
                    replace = false;
                    break;
                }

                if (frame.get(j).num == rec) {
                    replace = false;
                    break;
                }
            }

            if (replace) {
                int idx = 0;
                for (int j = 1; j < n; j++) {
                    if (cnt[frame.get(j).num] == cnt[frame.get(idx).num]) {
                        if (frame.get(j).order < frame.get(idx).order) {
                            idx = j;
                        }
                    }
                    else if (cnt[frame.get(j).num] < cnt[frame.get(idx).num]){
                        idx = j;
                    }
                }

                cnt[frame.get(idx).num] = 0;
                frame.set(idx, new Student(rec, i));
            }
        }

        Collections.sort(frame, (a, b) -> a.num - b.num);
        for (Student stu : frame) System.out.print(stu.num + " ");
    }
}

class Student {
    int num, order;

    Student (int num, int order) {
        this.num = num; this.order = order;
    }
}