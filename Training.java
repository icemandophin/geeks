import java.util.*;

public class Training {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("this is Java Training");
		TrainTraverse obj = new TrainTraverse();
		
	}

}

class TrainTraverse {
	List<Integer> a;
	List<String> s;
	Map<Integer, List<String>> map;
	public TrainTraverse() {
		a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		s = new LinkedList<>();
		s.add("hello");
		s.add("this");
		s.add("is");
		s.add("401");
		map = new HashMap<>();
		List<String> cur = map.getOrDefault(7, new ArrayList<String>());
		cur.add("777");
		map.put(7,  cur);
		cur = map.getOrDefault(9, new ArrayList<String>());
		cur.add("IX");
		map.put(9,  cur);
		cur = map.getOrDefault(7, new ArrayList<String>());
		cur.add("add to 7");
		map.put(7,  cur);
		
		for (Integer key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		for (Map.Entry<Integer, List<String>> en : map.entrySet()) {
			System.out.println(en.getKey() + " : " + en.getValue());
		}
		for (int i : a) {
			System.out.println(i);
		}
		Iterator<String> j = s.iterator();
		while (j.hasNext()) {
			System.out.println(j.next());
		}
	}
}

class TrainVector {
	Vector<Integer> v;
	public TrainVector() {
		Vector<Integer> v = new Vector<>();
		//check empty
		System.out.println(v.isEmpty());
		v.add(1);
		// check existance
		System.out.println(v.contains(1));
		//remove v[0]
		v.remove(0);
		System.out.println(v.contains(1));
		v.add(2);
		v.add(3);
		v.set(1, 7);
		// get value
		System.out.println(v.get(1));
		// get index 
		System.out.println(v.indexOf(2));
		// find index - return -1 when not found
		System.out.println(v.lastIndexOf(7));
		System.out.println("size of vector: " + v.capacity());
	}
}

class TrainEnum {
	// enumeration is interface
	Enumeration<String> days;
	public TrainEnum() {
		// implement with vector of string
		Vector<String> names = new Vector<String>();
		names.add("Sun");
		names.add("Mon");
		names.add("Tue");
		names.add("Wed");
		names.add("Thu");
		names.add("Fri");
		names.add("Sat");
		days = names.elements();
	}
	public void print() {
		// check if next exist
		while (days.hasMoreElements()) {
			// print next element equal to i++
			System.out.println(days.nextElement());
		}
		
	}
}
