package queue;

import java.util.Arrays;

/*
    Model:
        [a1, a2, ...an]
        tail, head -- pointers
        tail - head -- size of queue
        tail >= head

    Inv:
        tail >= head
        forall i = head..tail: a[i] != null

    enqueue(e)
    Pred: e != null
    Post: tail = tail' + 1 && head = head' && a[tail'] = e && forall i = head..tail' - 1: a[i] = a'[i]

    dequeue()
    Pred: n > 0
    Post: head = head' + 1 && tail == tail' R == a[head'] && i = head..tail - 1: a[i] = a'[i]

    element()
    Pred: n > 0
    Post: R == a[head] && head == head' && tail = tail' && forall i = head..tail - 1: a[i] = a'[i]

    size()
    Pred: true
    Post: R == tail - head && forall i = head..tail - 1: a[i] = a'[i] && head = head' && tail = tail'

    isEmpty()
    Pred: true
    Post: R == (tail - head == 0) && forall i = head..tail - 1: a[i] = a'[i] && head = head' && tail = tail'

    toArray()
    Pred: true
    Post: Object[] array : array.length = head - tail && forall i : 0 <= i <= array.length --> a[i] == i - ый эл-т в очереди && forall i = head..tail - 1: a[i] = a'[i] tail == tail' && head == head'

 */

public class ArrayQueueModule {
    private static int head, tail;
    private static Object[] elements = new Object[5];

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(tail + 1 - head /* new capacity */);
        elements[tail % elements.length] = element;
        tail++;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] newElements = Arrays.copyOf(Arrays.copyOfRange(elements, head, elements.length), elements.length * 2);
            if (tail > elements.length) {
                System.arraycopy(elements, 0, newElements, elements.length - head, tail % elements.length);
            }
            head = 0;
            tail = elements.length;
            elements = newElements;
        }
    }

    public static Object dequeue() {
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

    public static Object[] toArray() {
        Object[] ans = new Object[tail - head];
        if (Math.min(tail, elements.length) - head >= 0)
            System.arraycopy(elements, head, ans, 0, Math.min(tail, elements.length) - head);
        if (tail > elements.length) {
            System.arraycopy(elements, 0, ans, elements.length - head, tail % elements.length);
        }
        return ans;
    }
}
