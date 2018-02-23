/*
build depart -> List of dest mapping
lexical order => priority queue
*/
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> res = new LinkedList<>();
        // build mapping
        for(int i = 0; i < tickets.length; i ++) {
            String key = tickets[i][0];
            if(map.get(key) == null) {
                map.put(key, new PriorityQueue<String>());
            }
            map.get(key).offer(tickets[i][1]);
        }
        // DFS with stack
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while(!stack.isEmpty()) {
            String cur = stack.peek();

            if(map.containsKey(cur) && map.get(cur).size() > 0) {
                // only push top of PQ
                stack.push(map.get(cur).poll());
            }
            else {
                // start poping from last dest
                res.addFirst(stack.pop());
            }
        }

        return res;
}
}
