import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	WeightedRandomObject<String> test = new WeightedRandomObject<>();
    	test.put("abc", 0.5);
    	test.put("def", 0.4);
    	test.put("xyz", 0.4);
    	test.put("abc", 0.1);

    	int cnt1 = 0, cnt2 = 0, cnt3 = 0;

    	for (int i = 0; i < 1000000; ++i) {
    		if (test.get().equals("abc")) {
    			cnt1++;
    		} else if (test.get().equals("def")) {
    			cnt2++;
    		} else {
    			cnt3++;
    		}
    	}

    	System.out.println("Count for ABC: " + cnt1);
    	System.out.println("Count for DEF: " + cnt2);
    	System.out.println("Count for XYZ: " + cnt3);
    }
}

class WeightedRandomObject<T> {
	private double total;
	private Map<T, Double> weight;
	private Random rand;

	public WeightedRandomObject() {
		total = 0;
		weight = new HashMap<>();
		rand = new Random();
	}

	public void put(T obj, Double w) {
		// maintain total
		if (weight.containsKey(obj)) {
			total -= weight.get(obj);
		}
		total += w;

		weight.put(obj, w);
	}

	public T get() {
		T res = null;

		Double cur = rand.nextDouble() * total;

		for (Map.Entry<T, Double> entry : weight.entrySet()) {
			cur -= entry.getValue();
			if (cur.compareTo(0.0) <= 0) {
				res = entry.getKey();
				break;
			}
		}

		return res;
	}
}
