public class OrderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
		// KODEA OSATU ETA KOSTUA KALKULATU
		Node<T> newNode = new Node<T>(elem);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
            count++;
            return;
        }

        Node<T> current = first;

        if (elem.compareTo(current.data) < 0) {
            newNode.next = current;
            current.prev = newNode;
            first = newNode;
            count++;
            return;
        }

        while (current.next != null && elem.compareTo(current.next.data) > 0) {
            current = current.next;
        }

        newNode.next = current.next;
        newNode.prev = current;
        current.next = newNode;

        if (newNode.next != null) {
            newNode.next.prev = newNode;
        } else {
            last = newNode; 
        }

        count++;


	}

	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> zerrenda){
		// KODEA OSATU ETA KOSTUA KALKULATU
		OrderedDoubleLinkedList<T> emaitza = new OrderedDoubleLinkedList<T>();

        if (this.isEmpty() || zerrenda.isEmpty()) {
            return emaitza;
        }

        Node<T> n1 = this.first;
        Node<T> n2 = zerrenda.first;

        while (n1 != null && n2 != null) {
            int cmp = n1.data.compareTo(n2.data);
            if (cmp == 0) {
                emaitza.add(n1.data);
                n1 = n1.next;
                n2 = n2.next;
            } else if (cmp < 0) {
                n1 = n1.next;
            } else {
                n2 = n2.next;
            }
        }

        return emaitza;


	}


}
