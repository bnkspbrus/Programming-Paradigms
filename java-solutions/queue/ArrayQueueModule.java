package queue;

public class ArrayQueueModule {
<<<<<<< HEAD
    private static int head, size;
    private static Object[] elements = new Object[5];

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1 /* new capacity */);
        elements[(head + size) % elements.length] = element;
        size++;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = fillArray(new Object[elements.length * 2]);
            head = 0;
        }
    }

    public static Object dequeue() {
        assert size > 0;
        Object ret = elements[head];
        elements[head] = null;
        head++;
        size--;
        head %= elements.length;
        return ret;
    }

    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        elements = new Object[5];
        head = size = 0;
    }

    public static Object[] toArray() {
        return fillArray(new Object[size]/*tail - head*/);
    }

    private static Object[] fillArray(Object[] array) {
//        Object[] newElements = new Object[newSize];
        for (int i = 0; i < size; i++) {
            array[i] = elements[(head + i) % elements.length];
        }
        return array;
    }
=======
>>>>>>> 0ad04519b817984df6dd89162e4efc2c1f40d8ee
}
