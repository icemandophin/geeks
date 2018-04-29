package Solution;
import java.util.*;

public class Solution {
    public static void main(String args[]) {
        Singleton one = Singleton.getInstance();
        Singleton two = Singleton.getInstance();
    }
}

class Singleton
{
    // static variable single_instance of type Singleton
    private static Singleton single_instance = null;

    // variable of type String
    private static String str1;
    private static String str2;

    // private constructor restricted to this class itself
    private Singleton()
    {
        str1 = "Create new instance of Singleton class. ";
        str2 = "Direct return the existing instance of Singleton class. ";
    }

    // static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null) {
            single_instance = new Singleton();
            System.out.println(str1);
        } else {
            System.out.println(str2);
        }

        return single_instance;
    }
}
