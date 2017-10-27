package Amazon;
import java.util.*;
public class warehouse {
    public static void main (String[] args){
    	Scanner in = new Scanner(System.in);
    	List<List<Integer>> input = new ArrayList<>();
    	input.add(Arrays.asList(1,1));
    	input.add(Arrays.asList(1,2));
    	input.add(Arrays.asList(1,3));
    	input.add(Arrays.asList(1,4));
    	input.add(Arrays.asList(1,5));
    	input.add(Arrays.asList(3,5));
    	int n = 6;
    	int m = 3;
    	System.out.println(topK(input,n,m));
    }
    public static List<List<Integer>> topK(List<List<Integer>> input, int n,int m){
    	PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(n,
    			new Comparator<List<Integer>>(){
    		      public int compare (List<Integer> e1,List<Integer> e2){
    		    	  return e1.get(0)*e1.get(0) + e1.get(1)*e1.get(1) - e2.get(0)*e2.get(0) - e2.get(1)*e2.get(1);
    		      }
    	        });
    	for(List<Integer> e1:input){
    		pq.add(e1);
    	}
    	List<List<Integer>> result = new ArrayList<>();
    	for(int i = 0;i < m && i < n;i++){
    		result.add(pq.remove());
    	}
        return result;	
    }
}
