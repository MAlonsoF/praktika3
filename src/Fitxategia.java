import java.io.*;
import java.util.Scanner;

public class Fitxategia {

    public Fitxategia() {}

    // === Carga de Editoreak ===
    public void irakurriEditoreak(String pFitxeroa) throws IOException {
        try (Scanner entrada = new Scanner(new FileReader(pFitxeroa))) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (linea.isBlank() || linea.startsWith("#")) continue;

                String[] datuak = linea.split("\\s+#\\s+");
                if (datuak.length < 2) continue;

                String id = datuak[0].trim();
                String izena = datuak[1].trim();

                Editorea existente = EditoreaBiltegi.getNireEditoreaBiltegi().bilatuEditorea(id);
                if (existente == null) {
                    Editorea e = new Editorea(id, izena);
                    EditoreaBiltegi.getNireEditoreaBiltegi().gehituEditorea(e);
                }
            }
        }
    }

    // === Carga de Argitalpenak ===
    public void irakurriArgitalpenak(String fitxategia) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxategia))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank() || linea.startsWith("#")) continue;

                String[] zatia = linea.split("\\s+#\\s+");
                if (zatia.length < 2) continue;

                String id = zatia[0].trim();
                String izenburua = zatia[1].trim();

                Argitalpena a = new Argitalpena(id, izenburua);
                ArgitalpenaBiltegi.getNireArgitalpenaBiltegi().gehituArgitalpena(id, a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // === Carga de relación Argitalpena-Editorea ===
    public void irakurriArgitalpenaEditoreak(String pFitxeroa) throws IOException {
        try (Scanner entrada = new Scanner(new FileReader(pFitxeroa))) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (linea.isBlank() || linea.startsWith("#")) continue;

                String[] datuak = linea.split("\\s+#\\s+");
                if (datuak.length < 2) continue;

                String idArgitalpena = datuak[0].trim();
                String idEditorea = datuak[1].trim();

                // Buscamos solo una vez
                Argitalpena a = ArgitalpenaBiltegi.getNireArgitalpenaBiltegi().bilatuArgitalpena(idArgitalpena);
                Editorea e = EditoreaBiltegi.getNireEditoreaBiltegi().bilatuEditorea(idEditorea);

                if (a != null && e != null) {
                    a.gehituEgilea(e);
                    e.gehituArgitalpena(a);
                }
            }
        }
    }

    // === Carga de relaciones entre Argitalpenak (aipamenak) ===
    public void irakurriArgitalpenaAgintapeak(String pFitxeroa) throws IOException {
        try (Scanner entrada = new Scanner(new FileReader(pFitxeroa))) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (linea.isBlank() || linea.startsWith("#")) continue;

                String[] datuak = linea.split("\\s+#\\s+");
                if (datuak.length < 2) continue;

                String idArg1 = datuak[0].trim();
                String idArg2 = datuak[1].trim();

                // Buscamos solo una vez
                Argitalpena a1 = ArgitalpenaBiltegi.getNireArgitalpenaBiltegi().bilatuArgitalpena(idArg1);
                Argitalpena a2 = ArgitalpenaBiltegi.getNireArgitalpenaBiltegi().bilatuArgitalpena(idArg2);

                if (a1 != null && a2 != null) {
                    a1.gehituArgitalpena(a2);
                    a2.gehituArgitalpena(a1);
                }
            }
        }
    }

    // === Guardar Editoreak ===
    public void gordeEditoreak(String fitxeroa) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fitxeroa))) {
            UnorderedDoubleLinkedList<Editorea> lista = EditoreaBiltegi.getNireEditoreaBiltegi().getEditoreak();
            for (Editorea e : lista) {
                pw.println(e.getId() + " # " + e.getIzena());
            }
        }
    }

    // === Guardar Argitalpenak ===
    public void gordeArgitalpenak(String fitxeroa) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fitxeroa))) {
            UnorderedDoubleLinkedList<Argitalpena> lista = ArgitalpenaBiltegi.getNireArgitalpenaBiltegi().getArgitalpenak();
            for (Argitalpena a : lista) {
                pw.println(a.getIdA() + " # " + a.getIzenburua());
            }
        }
    }
}
