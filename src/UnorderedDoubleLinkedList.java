public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// hasieran gehitu
		// KODEA OSATU ETA KOSTUA KALKULATU
		Node<T> newNode = new Node<T>(elem);

	    if (isEmpty()) {
	        first = newNode;
	        last = newNode;
	        newNode.next = newNode;
	        newNode.prev = newNode;
		} else {
	        newNode.next = first;
	        newNode.prev = last;
	        first.prev = newNode;
	        last.next = newNode;
	        first = newNode;
		}
		count++;
	}

	public void addToRear(T elem) {
	// bukaeran gehitu
		// KODEA OSATU ETA KOSTUA KALKULATU
		Node<T> newNode = new Node<T>(elem);

	    if (isEmpty()) {
	        first = newNode;
	        last = newNode;
	        newNode.next = newNode;
	        newNode.prev = newNode;
		} else {
	        newNode.prev = last;
	        newNode.next = first;
	        last.next = newNode;
	        first.prev = newNode;
	        last = newNode;
		}
		count++;
	}
	
	public void addAfter(T elem, T target) {
		// KODEA OSATU ETA KOSTUA KALKULATU
		if (isEmpty()) {
			return;
		}

		Node<T> current = last;
		while (current.prev != null) {
			current = current.prev;
		}

		while (current != null && !current.data.equals(target)) {
			current = current.next;
		}

		if (current == null) {
			return;
		}

		Node<T> newNode = new Node<T>(elem);

		newNode.prev = current;
		newNode.next = current.next;

		if (current.next != null) {
			current.next.prev = newNode;
		} else {
			last = newNode; 
		}
		current.next = newNode;

		count++;
	}

}
