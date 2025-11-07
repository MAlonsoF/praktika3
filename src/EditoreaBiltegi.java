import java.util.HashMap;
import java.util.Iterator;

public class EditoreaBiltegi {
    private static EditoreaBiltegi nireEditoreaBiltegi = null;
    private UnorderedDoubleLinkedList<Editorea> lista;
    private HashMap<String, Editorea> indexById;

    private EditoreaBiltegi() {
        this.lista = new UnorderedDoubleLinkedList<>();
        this.indexById = new HashMap<>();
    }

    public static EditoreaBiltegi getNireEditoreaBiltegi() {
        if (nireEditoreaBiltegi == null) nireEditoreaBiltegi = new EditoreaBiltegi();
        return nireEditoreaBiltegi;
    }

    public int editoreKopurua() {
        return lista.size();
    }

    public void erreseteatu() {
        this.lista = new UnorderedDoubleLinkedList<>();
        this.indexById.clear();
    }

    public void gehituEditorea(Editorea e) {
        if (e != null && !indexById.containsKey(e.getId())) {
            lista.addToRear(e);
            indexById.put(e.getId(), e);
        }
    }

    public Editorea bilatuEditorea(String id) {
        return indexById.get(id);
    }

    public void ezabatuEditorea(String id) {
        Editorea e = indexById.remove(id);
        if (e != null) {
            lista.remove(e);
            for (Argitalpena a : e.getArgitalpenakObjektuak()) {
                a.kenduEgilea(id);
            }
        }
    }

    public UnorderedDoubleLinkedList<Editorea> getEditoreak() {
        return lista;
    }

    public UnorderedDoubleLinkedList<String> getEditoreIds() {
        UnorderedDoubleLinkedList<String> ids = new UnorderedDoubleLinkedList<>();
        Iterator<Editorea> it = lista.iterator();
        while (it.hasNext()) {
            ids.addToRear(it.next().getId());
        }
        return ids;
    }
}
