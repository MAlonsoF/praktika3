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
		
		return aurkitua;

	}

}
