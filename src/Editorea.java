public class Editorea {
    private String id;
    private String izena;
    private UnorderedDoubleLinkedList<Argitalpena> argitalpenak;

    public Editorea(String pId, String pIzena){
        this.id = pId;
        this.izena = pIzena;
        this.argitalpenak = new UnorderedDoubleLinkedList<>();
    }

    public String getIzena() {
        return izena;
    }

    public String getId() {
        return id;
    }

    public void gehituArgitalpena(Argitalpena a) {
        if (findArgitalpena(a.getIdA()) == null) {
            argitalpenak.addToRear(a);
        }
    }
    public UnorderedDoubleLinkedList<String> getArgitalpenak() {
        UnorderedDoubleLinkedList<String> lista = new UnorderedDoubleLinkedList<>();
        for (Argitalpena a : argitalpenak) {
            lista.addToRear(a.getIdA());
        }
        return lista;
    }
    public UnorderedDoubleLinkedList<Argitalpena> getArgitalpenakObjektuak() {
        return argitalpenak;
    }
    
    private Argitalpena findArgitalpena(String idA) {
        for (Argitalpena a : argitalpenak) {
            if (a.getIdA().equals(idA)) return a;
        }
        return null;
    }
}
