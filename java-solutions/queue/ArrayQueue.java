package queue;

import java.util.Arrays;

public class ArrayQueue {
    private int head, tail;
    private Object[] elements = new Object[5];

    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity(tail + 1 - head /* new capacity */);
        elements[tail % elements.length] = element;
        tail++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] newElements = Arrays.copyOf(Arrays.copyOfRange(elements, head, elements.length), elements.length * 2);
            if (head > 0) {
                System.arraycopy(elements, 0, newElements, elements.length - head, head);
            }
            head = 0;
            tail = elements.length;
            elements = newElements;
        }
    }

    public Object dequeue() {
        assert tail - head > 0;
        Object ret = elements[head];
        elements[head] = null;
        head++;
        if (head == elements.length) {
            head -= elements.length;
            tail -= elements.length;
        }
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

    public Object[] toArray() {
        Object[] ans = new Object[tail - head];
        if (Math.min(tail, elements.length) - head >= 0)
            System.arraycopy(elements, head, ans, 0, Math.min(tail, elements.length) - head);
        if (tail > elements.length) {
            System.arraycopy(elements, 0, ans, elements.length - head, tail % elements.length);
        }
        return ans;
    }
}
