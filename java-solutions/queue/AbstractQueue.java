package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert size > 0;
        Object result = element();
        remove();
        size--;
        return result;
    }

    protected abstract void remove();

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        removeAllImpl();
        size = 0;
    }

    protected abstract void removeAllImpl();

    public Object[] toArray() {
        return fillArrayImpl(new Object[size]);
    }

    protected abstract Object[] fillArrayImpl(Object[] array);
}
