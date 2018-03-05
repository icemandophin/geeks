package solution;
import java.io.ObjectOutputStream.PutField;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.*;
import javax.activation.MailcapCommandMap;
//System.out.println();

/*
 * two iterators: one traverse list, the other traverse each element of cur list
 */
public class Solution {
	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(Arrays.asList(1, 2));
		input.add(Arrays.asList(3, 4, 5));
		input.add(Arrays.asList(6, 7, 8, 9));

		Vector2D test = new Vector2D(input);
		while (test.hasNext()) {
			System.out.println(test.next());
		}
	}
}

class Vector2D implements Iterator<Integer> {
	private int a;
	private int b;
	private List<List<Integer>> nest;

	public Vector2D(List<List<Integer>> in) {
		a = 0;
		b = 0;
		nest = in;
	}

	@Override
    public Integer next() {
		return nest.get(a).get(b++);
	}

	@Override
    public boolean hasNext() {
		while (a < nest.size()) {
			if (b < nest.get(a).size()) {
				return true;
			} else {
				// move to next row and check again
				a++;
				b = 0;
			}
		}

		return false;
	}
}


// optimize to O(N) space
class Vector2D implements Iterator<Integer> {
  	private Iterator<List<Integer>> ls;
	private Iterator<Integer> is;

	public Vector2D(List<List<Integer>> in) {
		// ls works as row iterator
		ls = in.iterator();
		// is works as element iterator
		is = null;
	}

	@Override
    public Integer next() {
		return is.next();
	}

	@Override
    public boolean hasNext() {
		// reset is when reach end of each list
		while (is == null || !is.hasNext() && ls.hasNext()) {
			// move to next row
			is = ls.next().iterator();
		}

		return is != null && is.hasNext();
	}
}
