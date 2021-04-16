package queue;

public interface Queue {
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
    Post: tail = tail' + 1 && head = head' && a[tail'] = e && forall i = head..tail' - 1: a[i] = a'[i]

    */

    void enqueue(Object element);

    /*

    dequeue()
    Pred: tail - size > 0
    Post: head = head' + 1 && tail == tail' R == a[head'] && i = head..tail - 1: a[i] = a'[i]

    */

    Object dequeue();

    /*

    element()
    Pred: tail - size > 0
    Post: R == a[head] && head == head' && tail = tail' && forall i = head..tail - 1: a[i] = a'[i]

    */

    Object element();

    /*

    size()
    Pred: true
    Post: R == tail - head && forall i = head..tail - 1: a[i] = a'[i] && head = head' && tail = tail'

    */

    int size();

    /*

    isEmpty()
    Pred: true
    Post: R == (tail - head == 0) && forall i = head..tail - 1: a[i] = a'[i] && head = head' && tail = tail'

    */

    boolean isEmpty();

    /*

    toArray()
    Pred: true
    Post: Object[] array : array.length = head - tail && forall i : 0 <= i <= array.length --> a[i] == i - ый эл-т в очереди && forall i = head..tail - 1: a[i] = a'[i] tail == tail' && head == head'

    */

    Object[] toArray();

    /*

    clear()
    Pred: true
    Post: forall i = head..head + size - 1 : a[i] = null && head == size = 0
     */


    void clear();
}
