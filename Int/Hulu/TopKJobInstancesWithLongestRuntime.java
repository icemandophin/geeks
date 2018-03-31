import java.util.*;

public class Solution {
	public static void main(String[] args) {
		List<Log> logs = new ArrayList<>();
		logs.add(new Log("start", "download", "321", 0));
		logs.add(new Log("start", "download", "4rdsd", 0));
		logs.add(new Log("start", "upload", "3213", 0));
		logs.add(new Log("start", "transfer", "fdsX1", 0));

		logs.add(new Log("end", "", "321", 1342));
		logs.add(new Log("end", "", "4rdsd", 321));
		logs.add(new Log("end", "", "3213", 123));
		logs.add(new Log("end", "", "fdsX1", 421));

		System.out.println(JobRuntime.topKRuntime(logs, 2));
	}
}

class JobRuntime {
	public static List<String> topKRuntime(List<Log> logs, int k) {
		List<String> res = new ArrayList<>();
		Map<String, String> idName = new HashMap<>();
		// record items in res for dedup
		Set<String> names = new HashSet<>();

		// sort job instances by time - max heap
		PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

		for (Log log : logs) {
			if (log.type.equals("start")) {
				// name info is missing for end log
				idName.put(log.id, log.name);
			} else {
				// find name of instance
				String name = idName.get(log.id);
				Job cur = new Job(name, log.id, log.time);
				pq.offer(cur);
			}
		}

		while (!pq.isEmpty() && res.size() < k) {
			Job instance = pq.poll();
			if (!names.contains(instance.name)) {
				res.add(instance.id);
				names.add(instance.name);
			}
		}

		return res;
	}
}

class Log {
	String type;
	String name;
	String id;
	int time;

	public Log(String type, String name, String id, int time) {
		this.type = type;
		this.name = name;
		this.id = id;
		this.time = time;
	}
}

class Job {
	String name;
	String id;
	int time;

	public Job(String name, String id, int time) {
		this.name = name;
		this.id = id;
		this.time = time;
	}
}
