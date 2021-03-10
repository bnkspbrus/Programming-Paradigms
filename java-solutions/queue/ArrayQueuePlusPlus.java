package queue;

import java.util.Arrays;

public class ArrayQueuePlusPlus {
    private int head, tail;
    private Object[] elements = new Object[10];

    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity(tail + 1);
        this.elements[tail++] = element;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = Arrays.copyOf(Arrays.copyOfRange(elements, head, tail), 2 * capacity);
            Arrays.fill(elements, tail - head, tail, null);
            tail -= head;
            head = 0;
        }
    }

    public Object dequeue() {
        assert tail - head > 0;
        Object ret = elements[head];
        elements[head++] = null;
        return ret;
    }

    public Object element() {
        assert tail - head > 0;
        return elements[head];
    }

    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return tail - head == 0;
    }

    public void clear() {
        elements = new Object[5];
        head = tail = 0;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(elements, head, tail));
    }
}
