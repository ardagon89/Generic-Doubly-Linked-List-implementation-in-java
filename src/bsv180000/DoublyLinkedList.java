package bsv180000;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * @author sxa190016
 * @author bsv180000
 * @version 1.0 Doubly linked list: Short project 1 Entry class has generic type
 *          associated with it, to allow inheritance. The DoublyLinkedList class
 *          extends the functionality of SinglyLinkedList class and implements
 *          methods hasPrev(), prev(), add(x) and DLLIterator which extends
 *          SinglyLinkedList.SLLIterator class.
 */
public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

	/**
	 * @author sxa190016
	 * @author bsv180000
	 * @version 1.0 Class Entry inherits from SinglyLinkedList.Entry and holds a
	 *          single node of the DoublyLinkedList object
	 */
	static class Entry<E> extends SinglyLinkedList.Entry<E> {

		/**
		 * Pointer which points to the previous node
		 */
		SinglyLinkedList.Entry<E> prev;

		/**
		 * Constructor which calls the super constructor and initializes the prev
		 * variable
		 * 
		 * @param x    The value to be stored in the node
		 * @param next Pointer to the next node in the DLL
		 * @param prev Pointer to the previous node in the DLL
		 */
		Entry(E x, Entry<E> next, Entry<E> prev) {
			super(x, next);
			this.prev = prev;
		}
	}

	/**
	 * Call the add method with a new Entry object
	 * 
	 * @param x The generic value stored in the node
	 */
	public void add(T x) {
		add(new Entry<>(x, null, null));
	}

	/**
	 * Adds a new node at the end of the DLL
	 * 
	 * @param entry The object of entry class to be added to the end of the DLL
	 */
	public void add(Entry<T> entry) {
		entry.prev = this.tail;
		super.add(entry);
	}

	/**
	 * Creates and returns an object of DLLIterator class
	 * 
	 * @return an object of DLLIterator class
	 */
	public DLLIterator iterator() {
		return new DLLIterator();
	}

	/**
	 * @author sxa190016
	 * @author bsv180000
	 * @version 1.0 Class DLLIterator inherits from SinglyLinkedList.SLLIterator and
	 *          implements an Iterator of generic type <T>
	 */
	protected class DLLIterator extends SLLIterator {

		/**
		 * The next node to the cursor in the DLL
		 */
		SinglyLinkedList.Entry<T> next;

		/**
		 * Constructor which calls the super class constructor and initializes the next variable
		 */
		public DLLIterator() {
			super();
			next = null;
		}

		/**
		 * Checks if the previous node exists or not
		 * 
		 * @return true if the previous node exists else returns false
		 */
		public boolean hasPrev() {
			return (cursor != head) && (cursor != head.next) && ((Entry<T>) cursor).prev != null;
		}

		/**
		 * Moves the cursor to the previous node and returns it's value
		 * 
		 * @return	the value of the previous node in the DLL
		 */
		public T prev() {
			//Cannot call prev() from the 1st node or the head(null) node
			if (cursor == head || cursor == head.next) {
				throw new NoSuchElementException();
			}
			next = cursor;
			cursor = ((Entry<T>) cursor).prev;
			prev = ((Entry<T>) cursor).prev;
			return cursor.element;
		}

		/**
		 * Adds a new node after the cursor and move the cursor to the new node
		 * 
		 * @param x	Value of the new node to be created
		 */
		public void add(T x) {
			/**
			 * the new node of the DLL
			 */
			Entry<T> node = new Entry<T>(x, null, null);
			node.prev = cursor;
			node.next = cursor.next;
			cursor.next = node;
			prev = cursor;
			cursor = cursor.next;
			next = cursor.next;
			
			//Move the tail if the new node is added at the end of the DLL
			if (prev == tail) {
				tail = cursor;
			//Else point the prev pointer of the next node to the new node
			} else {
				((Entry<T>) next).prev = cursor;
			}
			ready = false;
			size++;
		}

		/**
		 * Removes the node at the location of the cursor
		 */
		public void remove() {
			super.remove();
			
			//If removing from middle, point the prev pointer of the next node to the cursor
			if (cursor != tail) {
				((Entry<T>) cursor.next).prev = cursor;
			}
		}
	}

	/**
	 * Main function to test my class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * The number of nodes to be created in the DLL
		 */
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		/**
		 * An object of the DoublyLinkedList class
		 */
		DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();

		for (int i = 1; i <= n; i++) {
			lst.add(Integer.valueOf(i));
		}
		lst.printList();

		/**
		 * An iterator of the DoublyLinkedList class
		 */
		DoublyLinkedList<Integer>.DLLIterator it = lst.iterator();
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
			case 3:// Move to the previous node and print the element value
				if (it.hasPrev()) {
					System.out.println(it.prev());
				} else {
					break whileloop;
				}
				break;
			case 4:// Move to the next node without checking if it exists
				System.out.println(it.next());
				break;
			case 5:// Move to the previous node without checking if it exists
				System.out.println(it.prev());
				break;
			case 6:// Add a new node after the current node
				it.add(++n);
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
