package queue;

import java.util.Arrays;

/*
    Model:
        [a1, a2, ...an]
        tail, head -- pointers
        tail - head -- size of queue

    Inv:
        tail >= head
        forall i = head..tail: a[i] != null

    enqueue(e)
    Pred: e != null
    Post: tail = tail' + 1 && a[tail'] = e && forall i = head..tail' - 1: a[i] = a'[i]

    dequeue()
    Pred: n > 0
    Post: head = head' + 1 && R == a[head'] && i = head..tail - 1: a[i] = a'[i]

    element()
    Pred: n > 0
    Post: R == a[head]  && head == head' && forall i = head..tail - 1: a[i] = a'[i]

    size()
    Pred: true
    Post: R == tail - head

    isEmpty()
    Pred: true
    Post: R == (tail - head == 0)


 */

public class ArrayQueueModulePlusPlus {
    private static int head, tail;
    private static Object[] elements = new Object[5];

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(tail + 1);
        elements[tail++] = element;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = Arrays.copyOf(Arrays.copyOfRange(elements, head, tail), 2 * capacity);
            Arrays.fill(elements, tail - head, tail, null);
            tail -= head;
            head = 0;
        }
    }

    public static Object dequeue() {
        assert tail - head > 0;
        Object ret = elements[head];
        elements[head++] = null;
        return ret;
    }

    public static Object element() {
        assert tail - head > 0;
        return elements[head];
    }

    public static int size() {
        return tail - head;
    }

    public static boolean isEmpty() {
        return tail - head == 0;
    }

    public static void clear() {
        elements = new Object[5];
        head = tail = 0;
    }
}
