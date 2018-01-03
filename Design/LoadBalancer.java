public class LoadBalancer {

    private Map<Integer, Integer> dict = null;
    private List<Integer> servers = null;

    public LoadBalancer() {
        // Initialize your data structure here.
        dict = new HashMap<Integer, Integer>();
        servers = new ArrayList<Integer>();
    }

    // @param server_id add a new server to the cluster
    // @return void
    public void add(int server_id) {
        // Write your code here
        int m = servers.size();
        dict.put(server_id, m);
        servers.add(server_id);
    }

    // @param server_id server_id remove a bad server from the cluster
    // @return void
    public void remove(int server_id) {
        // Write your code here
        int index = dict.get(server_id);
        int m = servers.size();
        dict.put(servers.get(m - 1), index);
        servers.set(index, servers.get(m - 1));
        servers.remove(m - 1);
        dict.remove(server_id);
    }

    // @return pick a server in the cluster randomly with equal probability
    public int pick() {
        // Write your code here
        Random r = new Random();
        int m = servers.size();
        int index = Math.abs(r.nextInt()) % m;
        return servers.get(index);
    }
}

// version: 高频题班
public class LoadBalancer {
    int n = 0;
    Map<Integer, Integer> pos = new HashMap<>();
    List<Integer> array = new ArrayList<>();
    Random rand = new Random();

    public LoadBalancer() {
        // Initialize your data structure here.
    }

    // @param server_id add a new server to the cluster
    // @return void
    public void add(int server_id) {
        // Write your code here
        if (!pos.containsKey(server_id)) {
            array.add(server_id);
            pos.put(server_id, n);
            n++;
        }
    }

    // @param server_id server_id remove a bad server from the cluster
    // @return void
    public void remove(int server_id) {
        // Write your code here
        if (pos.containsKey(server_id)) {
            int lastItem = array.get(n - 1);
            int removeIdx = pos.get(server_id);

            pos.put(lastItem, removeIdx);
            array.set(removeIdx, lastItem);

            pos.remove(server_id);
            array.remove(n - 1);
            n--;
        }
    }

    // @return pick a server in the cluster randomly with equal probability
    public int pick() {
        // Write your code here
        return array.get(rand.nextInt(n));
    }
}
