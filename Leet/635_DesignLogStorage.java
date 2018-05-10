public class LogSystem {
    private TreeMap<String, Integer> tsMap;
    private Map<String, Integer> graMap;

    public LogSystem() {
        tsMap = new TreeMap<>();
        graMap = new HashMap<>();
        graMap.put("Year", 0);
        graMap.put("Month", 1);
        graMap.put("Day", 2);
        graMap.put("Hour", 3);
        graMap.put("Minute", 4);
        graMap.put("Second", 5);
    }

    public void put(int id, String timestamp) {
        tsMap.put(timestamp, id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        String start = transform(s, gra, false);
        String end = transform(e, gra, true);

        for (String key : tsMap.subMap(start, end).keySet()) {
            res.add(tsMap.get(key));
        }

        return res;
    }

    private String convert(int[] ts) {
        String str = String.format("%04d", ts[0]);

        for (int i = 1; i < ts.length; i++) {
            str += ":" + String.format("%02d", ts[i]);
        }

        return str;
    }

    private String transform(String s, String gra, boolean isEnd) {
        int[] ts = Arrays.stream(s.split(":")).mapToInt(Integer::parseInt).toArray();
        int idx = graMap.get(gra);

        if (isEnd) {
            ts[idx]++;
        }

        for (int i = idx + 1; i < ts.length; i++) {
            ts[i] = 0;
        }

        return convert(ts);
    }
}
