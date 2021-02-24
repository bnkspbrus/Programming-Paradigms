package queue;

import java.util.Arrays;

public class ArrayQueueADTPlusPlus {
    private int head, tail;
    private Object[] elements = new Object[10];

    public static void enqueue(ArrayQueueADTPlusPlus queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.tail + 1);
        queue.elements[queue.tail++] = element;
    }

    private static void ensureCapacity(ArrayQueueADTPlusPlus queue, int capacity) {
        if (capacity > queue.elements.length) {
            queue.elements = Arrays.copyOf(Arrays.copyOfRange(queue.elements, queue.head, queue.tail), 2 * capacity);
            Arrays.fill(queue.elements, queue.tail - queue.head, queue.tail, null);
            queue.tail -= queue.head;
            queue.head = 0;
        }
    }

    public static Object dequeue(ArrayQueueADTPlusPlus queue) {
        assert queue.tail - queue.head > 0;
        Object ret = queue.elements[queue.head];
        queue.elements[queue.head++] = null;
        return ret;
    }

    public static Object element(ArrayQueueADTPlusPlus queue) {
        assert queue.tail - queue.head > 0;
        return queue.elements[queue.head];
    }

    public static int size(ArrayQueueADTPlusPlus queue) {
        return queue.tail - queue.head;
    }

    public static boolean isEmpty(ArrayQueueADTPlusPlus queue) {
        return queue.tail - queue.head == 0;
    }

    public static void clear(ArrayQueueADTPlusPlus queue) {
        queue.elements = new Object[5];
        queue.head = queue.tail = 0;
    }
}
