package queue;

public class ArrayQueue {
    private int head, size;// tail;
    private Object[] elements = new Object[5];

    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1/* new capacity */);
        elements[(head + size) % elements.length] = element;
        size++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = fillArray(new Object[elements.length * 2]);
            head = 0;
        }
    }

    public Object dequeue() {
        assert size > 0;
        Object ret = elements[head];
        elements[head] = null;
        head++;
        size--;
        head %= elements.length;
        return ret;
    }

    public Object element() {
        assert size > 0;
        return elements[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        elements = new Object[5];
        head = size = 0;
    }

    public Object[] toArray() {
        return fillArray(new Object[size]/*tail - head*/);
    }

    private Object[] fillArray(Object[] array) {
//        Object[] newElements = new Object[newSize];
        for (int i = 0; i < size; i++) {
            array[i] = elements[(head + i) % elements.length];
        }
        return array;
    }
}
