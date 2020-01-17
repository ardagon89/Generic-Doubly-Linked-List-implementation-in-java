/** @author bsv180000, shariq ali
 *  Doubly linked list: Short project 1
 *
 */

package bsv180000;
import java.util.Scanner;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

    /** Class Entry holds a single node of the list */
    static class Entry<E> extends SinglyLinkedList.Entry<E>{
        Entry<E> prev;

        Entry(E x, Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public DoublyLinkedList(){
        head = new Entry<>(null ,null,null);
        tail = head;
        size = 0;
    }


    /**
     * Main function to test my class
     * @param args
     */
    public static void main(String[] args) {
        // Simple Input Scanner
        Scanner in;

        if(args.length > 0)
        {
            // read from the specified file
            in = new Scanner(args[1]);
        }
        else
        {
            // read from terminal
            in = new Scanner(System.in);
        }
    }

}

