package queue;

/*
    Model:
        [a1, a2, ...an]
        head -- pointer
        size -- size of queue

    Inv:
        tail >= head
        forall i = head..head + size - 1: a[i] != null

    enqueue(queue, e)
    Pred: e != null
    Post: size = size' + 1 && head = head' && a[tail'] = e && forall i = head..head + size - 1: a[i] = a'[i]

    dequeue(queue)
    Pred: size > 0
    Post: head = head' + 1 && size == size' - 1 R == a[head'] && i = head..head + size - 1: a[i] = a'[i]

    element(queue)
    Pred: size > 0
    Post: R == a[head] && head == head' && size = size' && forall i = head..head + size - 1: a[i] = a'[i]

    size(queue)
    Pred: true
    Post: R == size && forall i = head..head + size - 1: a[i] = a'[i] && size = size' && head = head'

    isEmpty(queue)
    Pred: true
    Post: R == (size == 0) && forall i = head..head + size - 1: a[i] = a'[i] && head = head' && size = size'

    toArray(queue)
    Pred: true
    Post: Object[] array : array.length = size && forall i : 0 <= i <= array.length --> a[i] == i - ый эл-т в очереди && forall i = head..head + size - 1: a[i] = a'[i] size == size' && head == head'

    clear()
    Pred: true
    Post: forall i = head..head + size - 1 : a[i] = null && head == size = 0
 */

public class ArrayQueueModule {
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
}
