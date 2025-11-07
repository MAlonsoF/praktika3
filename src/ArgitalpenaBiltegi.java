import java.util.HashMap;
import java.util.Iterator;


public class ArgitalpenaBiltegi {
    private static ArgitalpenaBiltegi nireArgitalpenaBiltegi = null;
    private UnorderedDoubleLinkedList<Argitalpena> lista; 
    private HashMap<String, Argitalpena> indexById;

    private ArgitalpenaBiltegi() {
        this.lista = new UnorderedDoubleLinkedList<>();
        this.indexById = new HashMap<>();
    }

    public static ArgitalpenaBiltegi getNireArgitalpenaBiltegi() {
        if (nireArgitalpenaBiltegi == null) nireArgitalpenaBiltegi = new ArgitalpenaBiltegi();
        return nireArgitalpenaBiltegi;
    }

    public int argitalpenKopurua() {
        return lista.size();
    }

    public void erreseteatu() {
        lista = new UnorderedDoubleLinkedList<>();
        indexById.clear();
    }

    public void gehituArgitalpena(String id, Argitalpena a) {
        if (!indexById.containsKey(id)) {
            lista.addToRear(a);
            indexById.put(id, a);
        }
    }

    public Argitalpena bilatuArgitalpena(String id) {
        return indexById.get(id);
    }

    public void ezabatuArgitalpena(String id) {
        Argitalpena a = indexById.remove(id);
        if (a != null) {
            lista.remove(a);
        }
    }

    public OrderedDoubleLinkedList<String> argitalpenakOrdenatutaLimit(int limit) {
        OrderedDoubleLinkedList<String> listaOrdenada = new OrderedDoubleLinkedList<>();
        Iterator<Argitalpena> it = lista.iterator();
        while (it.hasNext() && limit > 0) {
            Argitalpena a = it.next();
            listaOrdenada.add(a.getIzenburua());
            limit--;
        }
        return listaOrdenada;
    }

    public UnorderedDoubleLinkedList<Argitalpena> getArgitalpenak() {
        return lista;
    }
	
	public OrderedDoubleLinkedList<String> argitalpenakOrdenatuta() {
	    OrderedDoubleLinkedList<String> listaOrdenada = new OrderedDoubleLinkedList<>();
	    for (Argitalpena a : lista) {
	        listaOrdenada.add(a.getIzenburua()); 
	    }
	    return listaOrdenada;
	}

    
}
