public class Solution {
	public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
	    int res = 0, sum = 0, total = 0;
	    for (int i = 0; i < gas.size(); ++i) {
	        total += gas.get(i) - cost.get(i);
	        sum += gas.get(i) - cost.get(i);
	        if (sum < 0) {
	            res = i + 1;
	            sum = 0;
	        }
	    }

	    if (total < 0) {
	        return -1;
	    } else {
	        return res;
	    }
	}
}
