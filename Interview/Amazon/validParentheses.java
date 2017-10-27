package Amazon;
import java.util.*;

public class validParentheses {
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
    	String s = in.next();
    	System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        Stack mystack = new Stack();
        for (int i = 0;i<s.length();i++){
            char buff = s.charAt(i);
            if (buff == '(' || buff  == '{' || buff == '['){
                mystack.push(buff);
            }else{
                if (mystack.isEmpty()){
                    return false;
                }else if ((buff == ')' && (char)mystack.peek() != '(')||(buff == ']' && (char)mystack.peek() != '[')||(buff == '}' && (char)mystack.peek() != '{')){
                    return false;
                }else {
                    mystack.pop();
                }
            
            }
            
        }
        if (mystack.empty() == true){
            return true;
        }else {
            return false;
        }
    }
    
}
