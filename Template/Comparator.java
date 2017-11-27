/*
separate:
*/
public class Stock {
    PriorityQueue<Map.Entry<String, Double>> heap;
    public Stock(int k) {
        new PriorityQueue<>(k, new StockComp());
    }
}

class StockComp implements Comparator<Map.Entry<String, Double>> {
	public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
		return Double.compare(a.getValue(), b.getValue());
	}
}

/*
during definition:
*/
public class Stock {
    PriorityQueue<Map.Entry<String, Double>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String, Double>>(){
        @Override
        public int compare(<Map.Entry<String, Double>> a, <Map.Entry<String, Double>> b) {
            return Double.compare(a.getValue(), b.getValue());
        }
    });
}
