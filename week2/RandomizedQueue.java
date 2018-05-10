import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int m_totalSize;
    private int m_currentSize;
    private Item[] m_queue;

    public RandomizedQueue()
    {
        Item[] a = (Item[]) new Object[2];
        m_queue = a;
        m_totalSize = 2;
        m_currentSize = 0;
    }

    public boolean isEmpty()
    {
        return m_currentSize == 0;
    }

    public int size()
    {
        return m_currentSize;
    }

    private void resize(int n)
    {
        Item[] t_queue = (Item[]) new Object[n];
        for (int i = 0; i < m_currentSize; i++)
        {
            t_queue[i] = m_queue[i];
        }
        m_queue = t_queue;
    }

    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Cannot enqueue null element!");

        m_queue[m_currentSize++] = item;
        if (m_currentSize >= m_totalSize)
        {
            resize(m_totalSize * 2);
            m_totalSize *= 2;
        }
    }

    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Invalid operation");

        int num = StdRandom.uniform(m_currentSize);
        Item temp = m_queue[num];
        m_queue[num] = m_queue[m_currentSize - 1];
        m_queue[m_currentSize - 1] = null;
        m_currentSize--;
        if (m_currentSize <= m_totalSize / 4)
        {
            resize(m_totalSize / 2);
            m_totalSize /= 2;
        }
        return temp;
    }

    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException("Invalid operation!");

        int num = StdRandom.uniform(m_currentSize);
        Item temp = m_queue[num];
        return temp;
    }

    public Iterator<Item> iterator()
    {
        return new randomizedQueueIterator();
    }

    private class randomizedQueueIterator implements Iterator<Item>
    {
        private int N = m_currentSize;
        private Item[] m_iteratorItem;
        public randomizedQueueIterator()
        {
            m_iteratorItem = (Item[]) new Object[m_currentSize];
            for (int i = 0; i < m_currentSize; i++)
            {
                m_iteratorItem[i] = m_queue[i];
            }
            StdRandom.shuffle(m_iteratorItem);
        }

        public boolean hasNext()
        {
            return N > 0;
        }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException("Invalid operation!");

            return m_iteratorItem[--N];
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported!");
        }
    }

    public static void main(String[] argv)
    {
        RandomizedQueue<Integer> rdmQueue = new RandomizedQueue<Integer>();
        rdmQueue.enqueue(1);
        rdmQueue.enqueue(2);
        rdmQueue.enqueue(3);
        rdmQueue.enqueue(4);
        rdmQueue.enqueue(5);
        rdmQueue.enqueue(6);
        while (!rdmQueue.isEmpty())
        {
            System.out.println(rdmQueue.dequeue());
        }
    }

}
