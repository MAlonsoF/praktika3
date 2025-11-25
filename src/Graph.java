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

            // 2. pausua: keys? bete
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;

 	    // 3. pausua: adjList? bete
            // KODEA INPLEMENTATU           
           
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

	public ArrayList<String> erlazionatuta(String a1, String a2){
		    // KODEA INPLEMENTATU
        ArrayList<String> aurkitua = new ArrayList<>();
        ArrayList<String> reverse = new ArrayList<>();
        int pos1 = th.get(a1);
        int pos2 = th.get(a2);
        int aurrekoa = pos2;
        Boolean amaitu = false;
        aurkitua.add(a1);
        if (erlazionatuta1(a1,a2)){
            while (!amaitu){
                for (int hurrengoa : adjList[aurrekoa]) {
                    String aux = keys[hurrengoa];
                    if (erlazionatuta1(a1,aux)) {
                        reverse.add(keys[hurrengoa]);
                        aurrekoa = hurrengoa;
                    }
                    if (hurrengoa == pos1) {
                        amaitu = true;
                        break;
                    }
                }
                // Funciona pero el costo puede ser enorme, no es eficiente en grafos grandes
                // Ademas, reverse es la lista que los conecta pero esta DEL REVES
            }
        }
        else{
            // al no estar relacionados no deberia de ejecutarse
        }
		
		return aurkitua;

	}

}
