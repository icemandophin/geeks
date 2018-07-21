class MyQueue {
    // input stack
    private Stack<Integer> stack1;
    // output stack / queue
    private Stack<Integer> stack2;
    // cur queue head
    private int peek;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
        peek = 0;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stack1.empty()) {
            peek = x;
        }

        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.empty()) {
            // load input stack value to output stack
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!stack2.empty()) {
            return stack2.peek();
        }

        return peek;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */