import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributuak
	protected Node<T> first;
	protected Node<T> last;  // azkenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;

	public DoubleLinkedList() {
		first = null;
		last = null;
		deskr = "";
		count = 0;
	}
	
	public void setDeskr(String ize) {
	  deskr = ize;
	}

	public String getDeskr() {
	  return deskr;
	}

	public T removeFirst() {
	// listako lehen elementua kendu da
	// Aurrebaldintza: 
		// KODEA OSATU ETA KOSTUA KALKULATU
		if (isEmpty()) throw new NoSuchElementException();

	    T elem = first.data;

	    if (count == 1) {
	        first = null;
	        last = null;
	    } else {
	        first = first.next;
	        first.prev = last;
	        last.next = first;
	    }

	    count--;
	    return elem;
	}

	public T removeLast() {
	// listako azken elementua kendu da
	// Aurrebaldintza: 
		// KODEA OSATU ETA KOSTUA KALKULATU
	    if (isEmpty()) throw new NoSuchElementException();

	    T elem = last.data;

	    if (count == 1) {
	        first = null;
	        last = null;
	    } else {
	        last = last.prev;
	        last.next = first;
	        first.prev = last;
	    }

	    count--;
	    return elem;
    }


	public T remove(T elem) {
	// Aurrebaldintza: 
	// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
        //  bueltatuko du (null ez baldin badago)

		// KODEA OSATU ETA KOSTUA KALKULATU
		 if (isEmpty()) return null;

	        Node<T> current = last.next;
	        boolean found = false;

	        for (int i = 0; i < count && !found; i++) {
	            if (current.data.equals(elem)) {
	                found = true;
	            } else {
	                current = current.next;
	            }
	        }

	        if (!found) return null;

	        // caso único elemento
	        if (count == 1) {
	            last = null;
	        } else {
	            current.prev.next = current.next;
	            current.next.prev = current.prev;

	            if (current == last) {
	                last = current.prev;
	            }
	        }

	        count--;
	        return current.data;
        };
		
	public void removeAll(T elem) {
	// Aurrebaldintza: 
	// Balio zehatz baten agerpen guztiak ezabatzen ditu
	
		// KODEA OSATU ETA KOSTUA KALKULATU
		 if (isEmpty()) return;

	        Node<T> current = last.next;
	        int n = count;

	        for (int i = 0; i < n; i++) {
	            Node<T> next = current.next;
	            if (current.data.equals(elem)) {
	                // reutilizamos remove(T)
	                remove(current.data);
	            }
	            current = next;
	            if (isEmpty()) break;
	        }
    };

	public T first() {
	// listako lehen elementua ematen du
	   // KODEA OSATU ETA KOSTUA KALKULATU
        if (isEmpty()) throw new NoSuchElementException("Lista vacía");
        return last.next.data;
	}

	public T last() {
	// listako azken elementua ematen du
	   // KODEA OSATU ETA KOSTUA KALKULATU
        if (isEmpty()) throw new NoSuchElementException("Lista vacía");
        return last.data;
	}

	public DoubleLinkedList<T> clone(){
		// Zerrendaren kopia bat itzultzen du (ez du punteroa bikoizten)
	   // KODEA OSATU ETA KOSTUA KALKULATU		
		DoubleLinkedList<T> copia = new DoubleLinkedList<>();
        if (isEmpty()) return copia;

        Node<T> current = last.next;
        for (int i = 0; i < count; i++) {
            Node<T> nodo = new Node<>(current.data);
            if (copia.isEmpty()) {
                copia.last = nodo;
                nodo.next = nodo.prev = nodo;
            } else {
                nodo.next = copia.last.next;
                nodo.prev = copia.last;
                copia.last.next.prev = nodo;
                copia.last.next = nodo;
                copia.last = nodo;
            }
            copia.count++;
            current = current.next;
        }
        return copia;
	} 

	public boolean contains(T elem) {
	// Egiazkoa bueltatuko du aurkituz gero, eta false bestela
		      if (isEmpty())
		          return false;

		      		// KODEA OSATU ETA KOSTUA KALKULATU
		        Node<T> current = last.next;
		        for (int i = 0; i < count; i++) {
		            if (current.data.equals(elem)) return true;
		            current = current.next;
		        }
		        return false;
		   }

	public T find(T elem) {
	// Elementua bueltatuko du aurkituz gero, eta null bestela

		// KODEA OSATU ETA KOSTUA KALKULATU
		 if (isEmpty()) return null;

	        Node<T> current = last.next;
	        for (int i = 0; i < count; i++) {
	            if (current.data.equals(elem)) return current.data;
	            current = current.next;
	        }
	        return null;
	}

	public boolean isEmpty() { 
	// KODEA OSATU ETA KOSTUA KALKULATU
		 return count == 0;
	}
	
	public int size() { 
	// KODEA OSATU ETA KOSTUA KALKULATU
		 return count;
	}
	
	   public Iterator<T> iterator() { return new ListIterator(); } 
 
	   private class ListIterator implements Iterator<T> { 

		// KODEA OSATU 
		    private Node<T> current;
		    private int visited;

		    public ListIterator() {
		        current = first;
		        visited = 0;
		    }

		    public boolean hasNext() {
		        return visited < count;
		    }

		    public T next() {
		        if (!hasNext()) throw new NoSuchElementException();
		        T data = current.data;
		        current = current.next;
		        visited++;
		        return data;
		    }
	   } // private class
		
		
		public void adabegiakInprimatu() {
			System.out.println(this.toString());
		}

		
		public String toString() {
			String result = new String();
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result = result + "[" + elem.toString() + "] \n";
			}	
			return "DoubleLinkedList " + result + "]";
		}

}
