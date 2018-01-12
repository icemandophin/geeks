/*
save addtional min variable to record current min in stack
push: if input <= min => push min then input into stack, and update min
pop: if top element is also the current min, pop the top 2 elements and set min = 2nd element
if current element is not min, just regular operation
*/

class MinStack {
    private int min;
    private Stack<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<Integer>();
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            // according to push
            // element below cur min is always 2nd min
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
