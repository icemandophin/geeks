package Amazon;
import java.util.*;
public class bigunion {
    public static void main(String[] args){
    	String[][] lists = {{"1","5"},
    			            {"1","6"},
//    			            {"1","7"},
//    			            {"2","1"},
    			            {"2","3"},
//    			            {"6","2"},
    			            {"2","7"},
//    			            {"3","5"},
    			            {"3","7"},
    			            {"4","2"},
//    			            {"4","9"},
    			            {"9","8"}};

    	System.out.println(bigU(lists));
    }
    
    public static Set<String> bigU (String[][] lists){
    	Map<String,Set<String>> map = new HashMap<String,Set<String>>();
    	if (lists == null || lists.length == 0 ){
    		return null;
    	}
    	for (String[] list:lists){
    		if (list == null || list.length == 0){
    			continue;
    		}
    		if (!map.containsKey(list[0])){
    			map.put(list[0], new HashSet<String>(Arrays.asList(list[1])));
    		}else {
    			map.get(list[0]).add(list[1]);
    		}
    		if (!map.containsKey(list[1])){
    			map.put(list[1], new HashSet<String>(Arrays.asList(list[0])));
    		}else {
    			map.get(list[0]).add(list[1]);
    		}
    	}
    	PriorityQueue<Set<String>> pq = new PriorityQueue<Set<String>>(new Comparator<Set<String>>(){
    			public int compare(Set<String> set1,Set<String> set2){
    		          return set2.size()-set1.size();
    	         }	
    	});

    	for(String curr:map.keySet()){

    		boolean exist = false;
    		for(Set<String> se:pq){
    			if (se.contains(curr)){
    				exist = true;
    				break;
    			}
    		}
    		if (exist == true){
    			continue;
    		}else{
                HashSet<String> set = new HashSet<String>();
            	Queue<String> q = new LinkedList<>();
              	q.offer(curr);
            	while(!q.isEmpty()){
            		String qcurr = q.poll();
            		if(!set.contains(qcurr)){
            			set.add(qcurr);
            		}
            		for (String x:map.get(qcurr)){
            			if (!set.contains(x) && !q.contains(x)){
            				q.offer(x);
            			}
            		}
            	}
                pq.add(set);
    			
    		}
    		
    	}
//    	String[] re = (String[]) pq.poll().toArray();
    	return pq.poll();
    }
}
