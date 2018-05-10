import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
    public static void main(String[] argv)
    {
        int num = Integer.parseInt(argv[0]);
        int i = 0;
        RandomizedQueue<String> rdmQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
        {
            String temp = StdIn.readString();
            rdmQueue.enqueue(temp);

        }
        while (i < num)
        {

            String temp = rdmQueue.dequeue();
            System.out.println(temp);
            i++;
        }
    }
}
