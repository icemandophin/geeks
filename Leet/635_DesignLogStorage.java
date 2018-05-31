package Solution;

import java.util.*;

/*
* fast approach:
* 1. create start/end time spots from each interval
* 2. for each start time => cnt++ for each end time => cnt--
* 3. when cnt 1->0 => free time start; when count 0->1 free time end
*/
public class Solution {
    public static void main(String[] args) {
        LogSystem log = new LogSystem();
        log.put(1, "2017:01:01:23:59:59");
        log.put(2, "2017:01:01:22:59:59");
        log.put(3, "2016:01:01:00:00:00");

        System.out.println(Arrays.toString(log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year").toArray()));
        System.out.println(Arrays.toString(log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour").toArray()));
    }
}

class LogSystem {
    // use treemap for range query
    private TreeMap<String, Integer> tsMap;
    // map time granularity to its index in timestamp format
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
    // convert time array to string
    private String convert(int[] ts) {
        String str = String.format("%04d", ts[0]);

        for (int i = 1; i < ts.length; i++) {
            str += ":" + String.format("%02d", ts[i]);
        }

        return str;
    }
    // generate start/end range for
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
