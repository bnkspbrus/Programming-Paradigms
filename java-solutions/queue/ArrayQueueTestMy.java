package queue;

import java.util.Arrays;

public class ArrayQueueTestMy {
    public static void fill(ArrayQueue queue) {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
    }
    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println("element: " + queue.element() + " dequeue " + queue.dequeue() + " size: " + queue.size() + " toArray: " + Arrays.toString(queue.toArray()));
        }
    }
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
    }
}

