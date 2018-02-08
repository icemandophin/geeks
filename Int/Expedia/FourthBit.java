package solution;
import java.lang.management.ManagementPermission;
import java.util.*;
// System.out.println("res: ");

import javax.imageio.ImageTypeSpecifier;

public class Solution {
	public static void main(String[] args) {
		System.out.println(fourthBit(32));
	}

	static int fourthBit(int n) {
		return n >>> 3 & 1;
	}
}
