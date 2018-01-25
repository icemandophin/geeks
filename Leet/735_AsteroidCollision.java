/*
at the end, all the negative star has to be on the left,
and all the positive star has to be on the right.
from the left, a negative star will pass through if no positive star on the left;
keep track of all the positive stars moving to the right, the right most one
will be the 1st confront the challenge of any future negative star.
if it survives, keep going, otherwise, any past positive star will be exposed
to the challenge, by being popped out of the stack.
*/
class Solution {
    public int[] asteroidCollision(int[] a) {
        LinkedList<Integer> s = new LinkedList<>();
        // use LinkedList to simulate stack
        // so that we don't need to reverse at end.
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 || s.isEmpty() || s.getLast() < 0)
                s.add(a[i]);
            else if (s.getLast() <= -a[i])
                if (s.pollLast() < -a[i]) i--;
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}
