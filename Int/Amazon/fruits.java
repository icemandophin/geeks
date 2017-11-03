package Amazon;
import java.util.*;

public class fruits {
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
        List<List<String>> fruitlist = new ArrayList<>();
        List<String> chart = new ArrayList<>();
        fruitlist.add(Arrays.asList("a", "a"));
        fruitlist.add(Arrays.asList("o", "b","o"));

    //    fruitlist.add(Arrays.asList("p", "o","g"));
        chart = Arrays.asList("o","a","a","o","b","o","p","g");
        System.out.println(checkWinner(fruitlist,chart));
    		
    	
    }
    public static int checkWinner (List<List<String>> codeList, List<String> shoppingCart) {
    	int x = 0;
    	int y = 0;
    	int index = 0;
    	while (x < codeList.size()){
    		y = 0;
    		while(y < codeList.get(x).size() && index < shoppingCart.size()){
    			if (codeList.get(x).get(y) == shoppingCart.get(index) || codeList.get(x).get(y) == "*"){
    				y++;
    				index++;
    			}else{
    				index++;
    			}
    		}
    		if (index == shoppingCart.size() && (x != codeList.size()-1 || y != codeList.get(x).size()-1)){
    			return 0;
    		}
    		x++;
    	}
    	return 1;
    }
}
