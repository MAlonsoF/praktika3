import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Erlazionatuta1Test {

    private EditoreaBiltegi biltegi;
    private Graph grafoa;

    @BeforeEach
    void setUp() {
        biltegi = EditoreaBiltegi.getNireEditoreaBiltegi();
        biltegi.erreseteatu();
        grafoa = new Graph();
    }

    @Test
    void biEgileakGrafoanEtaDesberdinakIzanda() {
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

        assertFalse(grafoa.erlazionatuta1("E1", "E99")); // E99 no existe
    }

    @Test
    void grafoanEgileetakoBatEreEz() {
        grafoa.grafoaSortu(biltegi); // grafo vacío

        assertFalse(grafoa.erlazionatuta1("E1", "E2"));
    }
}