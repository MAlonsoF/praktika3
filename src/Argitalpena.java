public class Argitalpena {
	private String idA;
	private String izenburua;
    private UnorderedDoubleLinkedList<Editorea> egileak;
    private UnorderedDoubleLinkedList<Argitalpena> erlazionatuak;

	public Argitalpena(String pIdA, String pIzenburua) {
		this.idA = pIdA;
		this.izenburua = pIzenburua;
        this.egileak = new UnorderedDoubleLinkedList<>();
        this.erlazionatuak = new UnorderedDoubleLinkedList<>();
	}
	 public String getIdA() { 
        return idA;
	 }

	 public String getIzenburua() { 
		return izenburua;
	 }

	 public void gehituEgilea(Editorea e) {
	        if (findEgilea(e.getId()) == null) {
	            egileak.addToRear(e);
	        }
	 }
	 public UnorderedDoubleLinkedList<String> egileak() {
	        UnorderedDoubleLinkedList<String> lista = new UnorderedDoubleLinkedList<>();
	        for (Editorea e : egileak) {
	            lista.addToRear(e.getId());
	        }
	        return lista;
	    }

	 public void gehituArgitalpena(Argitalpena a) {
	        if (findArgitalpena(a.getIdA()) == null) {
	            erlazionatuak.addToRear(a);
	        }
		} 

	public UnorderedDoubleLinkedList<String> aipamenak() {
		UnorderedDoubleLinkedList<String> lista = new UnorderedDoubleLinkedList<>();
	        for (Argitalpena a : erlazionatuak) {
	            lista.addToRear(a.getIdA());
	        }
	        return lista;
	    }
	    
     public void kenduEgilea(String idEgile) {
         Editorea ezabatzeko = findEgilea(idEgile);
         if (ezabatzeko != null) {
             egileak.remove(ezabatzeko);
         }
     }
    private Editorea findEgilea(String id) {
        for (Editorea e : egileak) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }
    private Argitalpena findArgitalpena(String id) {
        for (Argitalpena a : erlazionatuak) {
            if (a.getIdA().equals(id)) return a;
        }
        return null;
    }


}
