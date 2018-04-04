/*
flatten with stack approach:
1. push all nestedList items to stack back->front (due to FILO)
2. hasNext: if top is integer - pop/return true
if top is list - pop list and push its items to stack (also back->front)
then check top element
3. next: always pop stack top integer (flattened in hasNext)
*/
public class NestedIterator implements Iterator<Integer> {
    // stack for flattening nested list
    Stack<NestedInteger> sk = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        // push elements into stack
        for (int i = nestedList.size() -1 ; i >= 0; --i) {
            sk.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        // pop top integer
        return sk.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!sk.isEmpty()) {
            // get stack top
            NestedInteger cur = sk.peek();
            if (cur.isInteger()) {
                return true;
            }
            // top is list, need flatten
            sk.pop();
            for (int i = cur.getList().size() - 1; i >= 0; --i) {
                sk.push(cur.getList().get(i));
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

 /*
  * // This is the interface that allows for creating nested lists.
  * // You should not implement it, or speculate about its implementation
  * public interface NestedInteger {
  *
  *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
  *     public boolean isInteger();
  *
  *     // @return the single integer that this NestedInteger holds, if it holds a single integer
  *     // Return null if this NestedInteger holds a nested list
  *     public Integer getInteger();
  *
  *     // @return the nested list that this NestedInteger holds, if it holds a nested list
  *     // Return null if this NestedInteger holds a single integer
  *     public List<NestedInteger> getList();
  * }
  */
