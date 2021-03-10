package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    @Override
    protected void enqueueImpl(Object element) {
        if (tail != null) {
            tail.next = new Node(element);
            tail = tail.next;
            return;
        }
        tail = head = new Node(element);
    }

    @Override
    protected void remove() {
        if (head == tail) {
            head = tail = null;
            return;
        }
        head = head.next;
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    public void removeAllImpl() {
        head = tail = null;
    }

//    @Override
//    public Object[] toArray() {
//        Node iterator = head;
//        Object[] array = new Object[size];
//        for (int i = 0; i < size; i++) {
//            array[i] = iterator;
//            iterator = iterator.next;
//        }
//        return array;
//    }

    @Override
    protected Object[] fillArrayImpl(Object[] array) {
        Node iterator = head;
        for (int i = 0; i < size; i++) {
            array[i] = iterator.value;
            iterator = iterator.next;
        }
        return array;
    }


    private static class Node {
        private final Object value;
        private LinkedQueue.Node next;

        public Node(Object value) {
            assert value != null;

            this.value = value;
            this.next = null;
        }
    }

//    public static void main(String[] args) {
//        AbstractQueue queue = new LinkedQueue();
//        queue.enqueue(13);
//        queue.dequeue();
//    }
}
