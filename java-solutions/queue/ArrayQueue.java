package queue;

<<<<<<< HEAD
public class ArrayQueue extends AbstractQueue {
    private int head;
    private Object[] elements = new Object[5];

//    public void enqueue(Object element) {
//        assert element != null;
//        ensureCapacity(size + 1 /* new capacity */);
//        elements[(head + size) % elements.length] = element;
//    }

    @Override
    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1 /* new capacity */);
        elements[(head + size) % elements.length] = element;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
//            Object[] newElements = Arrays.copyOf(Arrays.copyOfRange(elements, head, elements.length), elements.length * 2);
//            if (head + size > elements.length) {
//                System.arraycopy(elements, 0, newElements, elements.length - head, head);
//            }
//            head = 0;
//            elements = newElements;
            elements = fillArrayImpl(new Object[elements.length * 2]);
            head = 0;
        }
    }

    @Override
    protected void remove() {
        elements[head] = null;
        head++;
        head %= elements.length;
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }


    @Override
    protected void removeAllImpl() {
        elements = new Object[5];
        head = 0;
    }

    @Override
    protected Object[] fillArrayImpl(Object[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = elements[(head + i) % elements.length];
        }
        return array;
    }

//    private Object[] copyArray(int newSize) {
//        Object[] newElements = new Object[newSize];
//        for (int i = 0; i < size; i++) {
//            newElements[i] = elements[(head + i) % elements.length];
//        }
//        return newElements;
//    }
}

=======
public class ArrayQueue {
}
>>>>>>> 0ad04519b817984df6dd89162e4efc2c1f40d8ee
