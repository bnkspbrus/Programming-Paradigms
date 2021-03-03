package queue;

import java.util.Arrays;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue) {
        for (int i = 0; i < 5; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }
    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println("element: " + ArrayQueueADT.element(queue) + " dequeue " + ArrayQueueADT.dequeue(queue) + " size: " + ArrayQueueADT.size(queue) + " toArray: " + Arrays.toString(ArrayQueueADT.toArray(queue)));
        }
    }
    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        fill(queue);
        dump(queue);
    }
}
