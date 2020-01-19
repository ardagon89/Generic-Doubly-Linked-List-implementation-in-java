package bsv180000;
import java.util.Iterator;
import java.util.Scanner;

/** 
 * @author		bsv180000
 * @author 		sxa190016
 * @version		1.0		
 * Doubly linked list: Short project 1
 * Entry class has generic type associated with it, to allow inheritance.
 * The DoublyLinkedList class extends the functionality of SinglyLinkedList class
 * and implements methods hasPrev(), prev(), add(x)
 * and DLLIterator which extends SinglyLinkedList.SLLIterator class.
 */
public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

    /** Class Entry holds a single node of the list */
    static class DEntry<E> extends SinglyLinkedList.Entry<E>{
    	DEntry<E> prev;
    	DEntry<E> next;

        DEntry(E x, DEntry<E> next, DEntry<E> prev) {
            super(x, next);
            this.prev = prev;
            this.next = next;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public DoublyLinkedList(){
        head = new DEntry<>(null ,null,null);
        tail = head;
        size = 0;
    }
    
    public void add(T x) {
    	add(new DEntry<>(x, null, null));
    }
    
    public void add(DEntry<T> entry) {
    	this.tail.next = entry;
    	entry.prev = (DEntry<T>) this.tail;
    	tail = tail.next;
    	this.size++;
    }

    /**
     * Main function to test my class
     * @param args
     */
	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
		for (int i = 1; i <= n; i++) {
			lst.add(Integer.valueOf(i));
		}
		lst.printList();

		Iterator<Integer> it = lst.iterator();
		Scanner in = new Scanner(System.in);
		whileloop: while (in.hasNext()) {
			int com = in.nextInt();
			switch (com) {
			case 1: // Move to next element and print it
				if (it.hasNext()) {
					System.out.println(it.next());
				} else {
					break whileloop;
				}
				break;
			case 2: // Remove element
				it.remove();
				lst.printList();
				break;
			default: // Exit loop
				break whileloop;
			}
		}
		lst.printList();
		lst.unzip();
		lst.printList();
	}

}

