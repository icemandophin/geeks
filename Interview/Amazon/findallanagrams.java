package Amazon;
import java.util.*;

public class findallanagrams {
   public static void main(String[] args){
	   Scanner in = new Scanner(System.in);
	   String s = in.next();
	   String p = in.next();
	   System.out.println(findAnagrams(s,p));
   }
   public static List<Integer> findAnagrams(String s, String p) {
       List<Integer> result = new ArrayList<Integer>();
       if (s == null || s.length() == 0 || p == null || p.length() == 0){
           return result;
       }

       Map<Character,Integer> map = new HashMap<Character,Integer> ();
       for (int i = 0;i< p.length();i++){
           if (map.containsKey(p.charAt(i)) != true){
               map.put(p.charAt(i),1);
           }else {
               map.put(p.charAt(i),map.get(p.charAt(i))+1);
           }
       }
       int match = 0;
       for (int i = 0 ;i< s.length();i++){
           char b = s.charAt(i);
           if (map.containsKey(b)){
               map.put(b,map.get(b)-1);
               if (map.get(b) == 0){
                   match++;
               }
           }
           if ( i >= p.length() ){
               b = s.charAt(i-p.length());
               if (map.containsKey(b)){
                   map.put(b,map.get(b)+1);
                   if (map.get(b) == 1){
                       match--;
                   }
               }
           }
           if (match == map.size()){
               result.add(i - p.length() +1);
           }
       }
       return result;
   }
}
