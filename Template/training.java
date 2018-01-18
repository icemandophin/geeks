package solution;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Pipe;
import java.util.*;

public class Training {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is Java Training");
		TrainHeap obj = new TrainHeap();
	}
}

class TrainHeap {
	int k;
	Map<String, Double> map;
	PriorityQueue<Map.Entry<String, Double>> heap;
	public TrainHeap() {
		k = 3;
		map = new HashMap<>();
		// build min heap
		heap = new PriorityQueue<>(k, new StockComp());
		// add record to both
		map.put("MSFT", 83.72);
		map.put("FB", 181.86);
		map.put("GOOG", 1034.49);
		map.put("FAKE", 95.27);
		for (Map.Entry<String, Double> cur : map.entrySet()) {
			heap.offer(cur);
			if (heap.size() > k) {
				heap.poll();
			}
		}
		while (heap.peek() != null) {
			Map.Entry<String, Double> res = heap.poll();
			System.out.println(res.getKey() + ": " + res.getValue());
		}
	}
}

class StockComp implements Comparator<Map.Entry<String, Double>> {
	public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
		// build min heap
		return Double.compare(a.getValue(), b.getValue());
	}
}

class TrainMap {
	Map<Integer, Pairs> map;
	public TrainMap() {
		map = new HashMap<>();
		Pairs p1 = new Pairs();
		p1.add(new Pair(1, 8));
		p1.add(new Pair(2, 7));
		p1.add(new Pair(3, 6));
		p1.add(new Pair(4, 5));
		map.put(9, p1);
		Pairs p2 = new Pairs();
		p2.add(new Pair(3, 4));
		map.put(7, p2);
		// check key
		System.out.println(map.containsKey(7));
		// check value
		System.out.println(map.containsValue(p2));
		// insert to value
		Pairs cur = map.getOrDefault(7, new Pairs());
		cur.add(new Pair(2, 5));
		map.put(7, cur);
		cur = map.getOrDefault(100, new Pairs());
		cur.add(new Pair(1, 99));
		map.put(100, cur);
		// remove
		map.remove(9);
		// all keys
		for (int i : map.keySet()) {
			System.out.println(i);
			map.get(i).print();
		}
		// all values
		System.out.println("values: ");
		for (Pairs j : map.values()) {
			j.print();
		}
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
		// check existence
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

class Pairs {
	List<Pair> list = new ArrayList<>();
	public void add(Pair x) {
		list.add(x);
	}
	public Pair get(int y) {
		return list.get(y);
	}
	public void print() {
		for (Pair i : list) {
			System.out.println("(" + i.x + ", " + i.y + ")");
		}
	}
}

class Pair {
	public int x;
	public int y;
	Pair(int a, int b) {
		x = a;
		y = b;
	}
}

class TrainStack {
	public TrainStack() {
		Stack<Integer> sk = new Stack<>();;
		System.out.println(sk.isEmpty());
		sk.push(777);
		System.out.println(sk.peek());
		sk.push(999);
		System.out.println(sk.pop());
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

class TrainScan {
	Scanner scan;
	public TrainScan() {
		scan = new Scanner(System.in);
		if (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
	}
}

class TrainIO {
	BufferedReader br;
	public TrainIO() {
		// read from console
		br = new BufferedReader(new InputStreamReader(System.in));
		char c = '0';
		do {
			try {
				c = (char) br.read();
				System.out.println(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (c != 'q');
	}
}
