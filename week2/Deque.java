import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{

    private class Node{
        private Item m_item;
        private Node m_next;
        private Node m_last;

        public Node(Item item)
        {
            m_item = item;
            m_last = null;
            m_next = null;
        }
    }

    private int m_size = 0;
    private Node m_head = null;
    private Node m_tail = null;

    public Deque()
    {

    }

    public boolean isEmpty()
    {
        return m_size == 0;
    }

    public int size()
    {
        return m_size;
    }

    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null into Deque!");

        Node t_node = new Node(item);
        if (m_head == null && m_tail == null)
        {
            m_head = t_node;
            m_tail = t_node;
        }
        else
        {
            Node t_head = m_head;
            t_head.m_last = t_node;
            t_node.m_next = t_head;
            m_head = t_node;
        }
        m_size++;
    }

    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null into Deque!");

        Node t_node = new Node(item);
        if (m_head == null && m_tail == null)
        {
            m_head = t_node;
            m_tail = t_node;
        }
        else
        {
            Node t_tail = m_tail;
            t_node.m_last = t_tail;
            t_tail.m_next = t_node;
            m_tail = t_node;
        }
        m_size++;
    }

    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove element if the Deque is empty");

        Item ret = m_head.m_item;
        m_head = m_head.m_next;
        if (m_head != null)
            m_head.m_last = null;
        else
            m_tail = null;
        m_size--;
        return ret;
    }

    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove element if the Deque is empty");

        Item ret = m_tail.m_item;
        m_tail = m_tail.m_last;
        if (m_tail != null)
            m_tail.m_next = null;
        else
            m_head = null;
        m_size--;
        return ret;
    }

    public Iterator<Item> iterator(){ return new dequeIterator(); }

    private class dequeIterator implements Iterator<Item>
    {
        private Node m_current = m_head;

        public boolean hasNext()
        {
            return m_current != null;
        }

        public void remove(){ throw new UnsupportedOperationException("Operation not supported!"); }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException("No more element is available!");

            Item item = m_current.m_item;
            m_current = m_current.m_next;
            return item;
        }
    }

    public static void main(String[] argv)
    {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
//        deque.addFirst(4);
//        deque.addFirst(7);
//        deque.addLast(5);
//        deque.addLast(8);
//        deque.addLast(0);
//        System.out.println(deque.removeLast());
//        System.out.println(deque.removeLast());
//        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
    }
}
