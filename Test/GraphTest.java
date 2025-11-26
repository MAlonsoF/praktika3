import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class GraphTest {

    private EditoreaBiltegi biltegi;
    private Graph grafoa;

    @BeforeEach
    void setUp() {
        biltegi = EditoreaBiltegi.getNireEditoreaBiltegi();
        biltegi.erreseteatu();
        grafoa = new Graph();
    }

    // ---- TESTAK grafoaSortu ----
    @Test
    void oinarrizkoKasuaZerrendaHutsik() {
        grafoa.grafoaSortu(biltegi);
        assertEquals(0, grafoa.th.size());
        assertEquals(0, grafoa.keys.length);
        assertEquals(0, grafoa.adjList.length);
    }

    @Test
    void editoreIsolatuaBakarka() {
        Editorea e1 = new Editorea("E1", "Jon");
        biltegi.gehituEditorea(e1);
        grafoa.grafoaSortu(biltegi);

        assertEquals(1, grafoa.th.size());
        assertEquals("E1", grafoa.keys[0]);
        assertTrue(grafoa.adjList[0].isEmpty());
    }

    @Test
    void elkarlanSinpleaBiEditore() {
        Editorea e1 = new Editorea("E1", "Jon");
        Editorea e2 = new Editorea("E2", "Ane");
        Argitalpena a1 = new Argitalpena("A1", "Film A");
        a1.gehituEgilea(e1);
        a1.gehituEgilea(e2);
        e1.gehituArgitalpena(a1);
        e2.gehituArgitalpena(a1);
        biltegi.gehituEditorea(e1);
        biltegi.gehituEditorea(e2);

        grafoa.grafoaSortu(biltegi);
        int idx1 = grafoa.th.get("E1");
        int idx2 = grafoa.th.get("E2");
        assertTrue(grafoa.adjList[idx1].contains(idx2));
        assertTrue(grafoa.adjList[idx2].contains(idx1));
    }

    @Test
    void elkarlanAnitzEtaKonplexuak() {
        Editorea e1 = new Editorea("E1", "Jon");
        Editorea e2 = new Editorea("E2", "Ane");
        Editorea e3 = new Editorea("E3", "Mikel");
        Argitalpena a1 = new Argitalpena("A1", "Film A");
        Argitalpena a2 = new Argitalpena("A2", "Film B");
        Argitalpena a3 = new Argitalpena("A3", "Film C");
        a1.gehituEgilea(e1); a1.gehituEgilea(e2);
        a2.gehituEgilea(e2); a2.gehituEgilea(e3);
        a3.gehituEgilea(e1); a3.gehituEgilea(e3);
        e1.gehituArgitalpena(a1); e1.gehituArgitalpena(a3);
        e2.gehituArgitalpena(a1); e2.gehituArgitalpena(a2);
        e3.gehituArgitalpena(a2); e3.gehituArgitalpena(a3);
        biltegi.gehituEditorea(e1); biltegi.gehituEditorea(e2); biltegi.gehituEditorea(e3);

        grafoa.grafoaSortu(biltegi);
        int idx1 = grafoa.th.get("E1");
        int idx2 = grafoa.th.get("E2");
        int idx3 = grafoa.th.get("E3");
        assertTrue(grafoa.adjList[idx1].contains(idx2));
        assertTrue(grafoa.adjList[idx1].contains(idx3));
        assertTrue(grafoa.adjList[idx2].contains(idx1));
        assertTrue(grafoa.adjList[idx2].contains(idx3));
        assertTrue(grafoa.adjList[idx3].contains(idx1));
        assertTrue(grafoa.adjList[idx3].contains(idx2));
    }

    // ---- TESTAK erlazionatuta1 ----
    @Test
    void biEgileakGrafoanEtaDesberdinakIzanda() {
        Editorea e1 = new Editorea("E1", "Jon");
        Editorea e2 = new Editorea("E2", "Ane");
        Argitalpena a1 = new Argitalpena("A1", "Film A");
        a1.gehituEgilea(e1); a1.gehituEgilea(e2);
        e1.gehituArgitalpena(a1); e2.gehituArgitalpena(a1);
        biltegi.gehituEditorea(e1); biltegi.gehituEditorea(e2);
        grafoa.grafoaSortu(biltegi);

        assertTrue(grafoa.erlazionatuta1("E1", "E2"));
    }

    @Test
    void biEgileakGrafoanEtaBerdinakIzanda() {
        Editorea e1 = new Editorea("E1", "Jon");
        biltegi.gehituEditorea(e1);
        grafoa.grafoaSortu(biltegi);

        assertTrue(grafoa.erlazionatuta1("E1", "E1"));
    }

    @Test
    void biEgileetakoBatGrafoanEz() {
        Editorea e1 = new Editorea("E1", "Jon");
        biltegi.gehituEditorea(e1);
        grafoa.grafoaSortu(biltegi);

        assertFalse(grafoa.erlazionatuta1("E1", "E99"));
    }

    @Test
    void grafoanEgileetakoBatEreEz() {
        grafoa.grafoaSortu(biltegi);
        assertFalse(grafoa.erlazionatuta1("E1", "E2"));
    }

    // ---- TESTAK erlazionatuta2 ----
    @Test
    void bietakoBatEdoBiakEzDaudeGrafoanEtaNullBueltatzenDu() {
        grafoa.grafoaSortu(biltegi);
        assertNull(grafoa.erlazionatuta2("E1", "E2"));

        Editorea e1 = new Editorea("E1", "Jon");
        biltegi.gehituEditorea(e1);
        grafoa.grafoaSortu(biltegi);
        assertNull(grafoa.erlazionatuta2("E1", "E99"));
    }

    @Test
    void biakGrafoanDaudeEtaArrayListaBueltatzenDu() {
        Editorea e1 = new Editorea("E1", "Jon");
        Editorea e2 = new Editorea("E2", "Ane");
        Argitalpena a1 = new Argitalpena("A1", "Film A");
        a1.gehituEgilea(e1); a1.gehituEgilea(e2);
        e1.gehituArgitalpena(a1); e2.gehituArgitalpena(a1);
        biltegi.gehituEditorea(e1); biltegi.gehituEditorea(e2);
        grafoa.grafoaSortu(biltegi);

        ArrayList<String> bidea = grafoa.erlazionatuta2("E1", "E2");
        assertNotNull(bidea);
        assertEquals(2, bidea.size());
        assertEquals("E1", bidea.get(0));
        assertEquals("E2", bidea.get(1));
    }
}