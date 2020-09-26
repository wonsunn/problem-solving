class Main {

    static int t, n, m;
    static long res = 0;
    static int[] a, b;
    static List<Integer> listA, listB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            b[i] = Integer.parseInt(st.nextToken());

        listA = new ArrayList<>();
        saveList(a, listA);
        listB = new ArrayList<>();
        saveList(b, listB);

        for (int i : listA) {
            int val = t - i;
            int low = lower_bound(listB, val);
            int high = upper_bound(listB, val);
            res += (high - low);
        }

        System.out.println(res);
    }

    static void saveList(int[] arr, List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = arr[i];
            list.add(sum);
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }

        Collections.sort(list);
    }

    // lower bound는 찾고자 하는 값 이상이 처음 나타나는 위치
    static int lower_bound(List<Integer> list, int val) {
        int s = 0, e = list.size();

        while (s < e) {
            int mid = (s + e) / 2;

            if (list.get(mid) >= val)
                e = mid;
            else
                s = mid + 1;
        }

        return s;
    }

    // upper bound는 찾고자 하는 값보다 큰 값이 처음으로 나타나는 위치
    static int upper_bound(List<Integer> list, int val) {
        int s = 0, e = list.size();

        while (s < e) {
            int mid = (s + e) / 2;

            if (list.get(mid) <= val)
                s = mid + 1;
            else
                e = mid;
        }

        return s;
    }
}

