import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	
      HashMap<String, Integer> th;
      String[] keys;
      ArrayList<Integer>[] adjList;
	
		public void grafoaSortu(EditoreaBiltegi lista){
		// Post: pelikulen zerrendatik grafoa sortu
		//       Nodoak aktore izenak dira
		
		
          // 1. pausua:  th? bete
          // KODEA INPLEMENTATU
        th = new HashMap<>();
        int index = 0;
        
        // Recorrer todos los editores y asignarles un índice
        for (Editorea editorea : lista.getEditoreak()) {
            if (!th.containsKey(editorea.getId())) {
                th.put(editorea.getId(), index);
                index++;
            }
        }
          // 2. pausua: keys? bete
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;
		
		
		
			// PONER ALGO MAS, SOLO ESTA LO DEL PROFESOR
		
		
		
	    // 3. pausua: adjList? bete
          // KODEA INPLEMENTATU           
        adjList = new ArrayList[th.size()];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        // Construir las conexiones entre autores que colaboran en las mismas publicaciones
        for (Editorea editorea1 : lista.getEditoreak()) {
            int idx1 = th.get(editorea1.getId());
            
            // Para cada publicación del editor actual
            for (Argitalpena argitalpena : editorea1.getArgitalpenakObjektuak()) {
                // Conectar con todos los coautores de esta publicación
                for (String egileId : argitalpena.egileak()) {
                    if (!egileId.equals(editorea1.getId())) { // No conectar consigo mismo
                        int idx2 = th.get(egileId);
                        if (!adjList[idx1].contains(idx2)) {
                            adjList[idx1].add(idx2);
                        }
                    }
                }
            }
        }
	}
	public void print(){
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
	public boolean erlazionatuta1(String a1, String a2){
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean aurkitua = false;
		boolean[] aztertuak = new boolean[th.size()];

               // KODEA INPLEMENTATU  
        // Verificar que ambos autores existen en el grafo
        if (!th.containsKey(a1) || !th.containsKey(a2)) {
            return false;
        }
        // Si son el mismo autor
        if (pos1 == pos2) {
            return true;
        }
        
        // BFS para buscar conexión
        aztertuGabeak.add(pos1);
        aztertuak[pos1] = true;
        
        while (!aztertuGabeak.isEmpty() && !aurkitua) {
            int unekoa = aztertuGabeak.poll();
            
            // Recorrer todos los vecinos
            for (int hurrengoa : adjList[unekoa]) {
                if (!aztertuak[hurrengoa]) {
                    if (hurrengoa == pos2) {
                        aurkitua = true;
                        break;
                    }
                    aztertuak[hurrengoa] = true;
                    aztertuGabeak.add(hurrengoa);
                }
            }
        }
		return aurkitua;

	}

	public ArrayList<String> erlazionatuta2(String a1, String a2){
		    // KODEA INPLEMENTATU    
        // Verificar que ambos autores existen en el grafo
        if (!th.containsKey(a1) || !th.containsKey(a2)) {
            return null;
        }
        
        int hasiera = th.get(a1);
        int helburua = th.get(a2);
        
        // Si son el mismo autor
        if (hasiera == helburua) {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(a1);
            return lista;
        }
        
        boolean[] aztertuak = new boolean[th.size()];
        int[] aurrekoak = new int[th.size()]; // Para reconstruir el camino
        for (int i = 0; i < aurrekoak.length; i++) {
            aurrekoak[i] = -1;
        }
        
        Queue<Integer> aztertuGabeak = new LinkedList<>();
        aztertuGabeak.add(hasiera);
        aztertuak[hasiera] = true;
        
        boolean aurkitua = false;
        
        while (!aztertuGabeak.isEmpty() && !aurkitua) {
            int unekoa = aztertuGabeak.poll();
            
            for (int hurrengoa : adjList[unekoa]) {
                if (!aztertuak[hurrengoa]) {
                    aztertuak[hurrengoa] = true;
                    aurrekoak[hurrengoa] = unekoa;
                    aztertuGabeak.add(hurrengoa);
                    
                    if (hurrengoa == helburua) {
                        aurkitua = true;
                        break;
                    }
                }
            }
        }
        
        if (!aurkitua) {
            return null;
        }
        
        // Reconstruir el camino desde el final hasta el principio
        ArrayList<String> emaitza = new ArrayList<>();
        int unekoa = helburua;
        
        while (unekoa != -1) {
            emaitza.add(0, keys[unekoa]); // Añadir al principio
            unekoa = aurrekoak[unekoa];
        }
        
        return emaitza;
	}

}
