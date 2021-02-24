package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private int head, tail;
    private Object[] elements = new Object[10];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.tail + 1 - queue.head /* new capacity */);
        queue.elements[queue.tail % queue.elements.length] = element;
        queue.tail++;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity > queue.elements.length) {
            Object[] newElements = Arrays.copyOf(Arrays.copyOfRange(queue.elements, queue.head, queue.elements.length), queue.elements.length * 2);
            if (queue.head % queue.elements.length > 0) {
                System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.head, queue.head);
            }
            queue.head = 0;
            queue.tail = queue.elements.length;
            queue.elements = newElements;
        }
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.tail - queue.head > 0;
        Object ret = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head++;
        if (queue.head == queue.elements.length) {
            queue.head -= queue.elements.length;
            queue.tail -= queue.elements.length;
        }
        return ret;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.tail - queue.head > 0;
        return queue.elements[queue.head];
    }

    public static int size(ArrayQueueADT queue) {
        return queue.tail - queue.head;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.tail - queue.head == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[5];
        queue.head = queue.tail = 0;
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] ans = new Object[queue.tail - queue.head];
        if (Math.min(queue.tail, queue.elements.length) - queue.head >= 0)
            System.arraycopy(queue.elements, queue.head, ans, 0, Math.min(queue.tail, queue.elements.length) - queue.head);
        if (queue.tail > queue.elements.length) {
            System.arraycopy(queue.elements, 0, ans, queue.elements.length - queue.head, queue.tail % queue.elements.length);
        }
        return ans;
    }
}
