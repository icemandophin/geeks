package Amazon;
import java.util.*;

public class baseball {
   public static void main(String[] args){
	   Scanner in = new Scanner (System.in);
	   List<String> line = new ArrayList<String>();
	   line = Arrays.asList("5", "Z", "X", "1","X", "+", "1", "1");
	   System.out.println(helper(line));
   }
   private static int helper(List<String> input){
	   if (input == null || input.size() == 0){
		   return 0;
		   
	   }
	   int sum =0;
	   Stack<Integer> mystack = new Stack<Integer>();
	   mystack.push(0);
	   mystack.push(0);
       for (int i = 0;i < input.size();i++){
    	   int current = 0;
		   int size = mystack.size();
    	   String buff = input.get(i);
    	   if (buff == "Z"){
    		   current = mystack.pop();
    		   sum -= current;
    	   }else
    	   if (buff == "+"){

    		   current = mystack.get(size-1)+ mystack.get(size-2);
    		   sum +=current;
    		   mystack.push(current);
    	   }else
    	   if (buff == "X"){
               current = mystack.get(size-1) * 2;
               sum += current;
               mystack.push(current);
    	   }else{
    		   current = Integer.parseInt(buff);
    		   mystack.push(current);
    		   sum += current;
    	   }
    	   
    	   if (mystack.size()<2){
    		   mystack.push(0);
    	   }
       }
       return sum;
   }
}
