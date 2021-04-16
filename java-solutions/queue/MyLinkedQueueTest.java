package queue;

import java.util.Arrays;

public class MyLinkedQueueTest {
    public static void fill(Queue queue) {
        for (int i = 0; i < 3; i++) {
            queue.enqueue("world + " + i);
        }
    }

    public static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(Arrays.toString(queue.toArray()));
            queue.dequeue();
        }
    }

    public static void test(Queue queue) {
        fill(queue);

        dump(queue);
        System.out.println();

    }

    public static void main(String[] args) {
        test(new LinkedQueue());
    }
}
