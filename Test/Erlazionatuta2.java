import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class Erlazionatuta2Test {

    private EditoreaBiltegi biltegi;
    private Graph grafoa;

    @BeforeEach
    void setUp() {
        biltegi = EditoreaBiltegi.getNireEditoreaBiltegi();
        biltegi.erreseteatu();
        grafoa = new Graph();
    }

    @Test
    void bietakoBatEdoBiakEzDaudeGrafoanEtaNullBueltatzenDu() {
        // Grafo vacío
        grafoa.grafoaSortu(biltegi);

        assertNull(grafoa.erlazionatuta2("E1", "E2"));

        // Solo un editor en el grafo
        Editorea e1 = new Editorea("E1", "Jon");
        biltegi.gehituEditorea(e1);
        grafoa.grafoaSortu(biltegi);

        assertNull(grafoa.erlazionatuta2("E1", "E99")); // E99 no existe
    }

    @Test
    void biakGrafoanDaudeEtaArrayListaBueltatzenDu() {
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

        ArrayList<String> bidea = grafoa.erlazionatuta2("E1", "E2");

        assertNotNull(bidea);
        assertEquals(2, bidea.size());
        assertEquals("E1", bidea.get(0));
        assertEquals("E2", bidea.get(1));
    }
}