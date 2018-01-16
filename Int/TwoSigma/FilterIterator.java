/*
generator of random number that can be divided by 5
*/

public class FilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer next = null;
    private boolean nextSet = false;
    private boolean canRemove = false;

    public FilterIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return nextSet || setNext();
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Integer temp = next;
        next = null;
        nextSet = false;
        canRemove = true;
        return temp;
    }

    public void remove() {
        if (!canRemove) {
            throw new IllegalStateException();
        }

        iterator.remove();
        canRemove = false;
    }

    private boolean setNext() {
        while (iterator.hasNext()) {
            canRemove = false;
            Integer num = iterator.next();

            if (num % 5 == 0) {
                next = num;
                nextSet = true;
                return true;
            }
        }

        next = null;
        nextSet = false;
        return false;
    }
}
