<<<<<<< HEAD
/*
    Model:
        [a1, a2, ...an]
        head -- pointer
        size -- size of queue

    Inv:
        tail >= head
        forall i = head..head + size - 1: a[i] != null

    mainCond: queue != null

    enqueue(queue, e)
    Pred: e != null && mainCond
    Post: size = size' + 1 && head = head' && a[tail'] = e && forall i = head..head + size - 1: a[i] = a'[i]

    dequeue(queue)
    Pred: size > 0 && mainCond
    Post: head = head' + 1 && size == size' - 1 R == a[head'] && i = head..head + size - 1: a[i] = a'[i]

    element(queue)
    Pred: size > 0 && mainCond
    Post: R == a[head] && head == head' && size = size' && forall i = head..head + size - 1: a[i] = a'[i]

    size(queue)
    Pred: true && mainCond
    Post: R == size && forall i = head..head + size - 1: a[i] = a'[i] && size = size' && head = head'

    isEmpty(queue)
    Pred: true && mainCond
    Post: R == (size == 0) && forall i = head..head + size - 1: a[i] = a'[i] && head = head' && size = size'

    toArray(queue)
    Pred: true && mainCond
    Post: Object[] array : array.length = size && forall i : 0 <= i <= array.length --> a[i] == i - ый эл-т в очереди && forall i = head..head + size - 1: a[i] = a'[i] size == size' && head == head'

    clear()
    Pred: true && mainCond
    Post: forall i = head..head + size - 1 : a[i] = null && head == size = 0
 */

package queue;

public class ArrayQueueADT {
    private int head, size;
    private Object[] elements = new Object[10];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.size + 1 /* new capacity */);
        queue.elements[(queue.head + queue.size) % queue.elements.length] = element;
        queue.size++;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity > queue.elements.length) {
            queue.elements = fillArray(queue, new Object[queue.elements.length * 2]);
            queue.head = 0;
        }
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object ret = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head++;
        queue.size--;
        queue.head %= queue.elements.length;
        return ret;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[5];
        queue.head = queue.size = 0;
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        return fillArray(queue, new Object[queue.size]);
    }

    private static Object[] fillArray(ArrayQueueADT queue, Object[] array) {
        for (int i = 0; i < queue.size; i++) {
            array[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        return array;
    }
=======
package queue;

public class ArrayQueueADT {
>>>>>>> 0ad04519b817984df6dd89162e4efc2c1f40d8ee
}
