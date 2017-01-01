import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.rules.Timeout;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Aufgabe2Test {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);
    
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    
    String init =
            "Hering, Regal 250, Gewicht 20kg"+System.lineSeparator() +
            "Walhai, Regal 280, Gewicht 2050kg"+System.lineSeparator() +
            "Kaiserfisch, Regal 1220, Gewicht 20kg"+System.lineSeparator() +
            "Meeresschildkroete, Regal 33, Gewichtsklasse 20kg"+System.lineSeparator() +
            "Wal, SpezialRegal 20004, Gewicht 20000kg"+System.lineSeparator() +
            "Lederkoralle, Regal 40, Gewicht 1000g"+System.lineSeparator() +
            "Feuerkoralle, Regal 41, Gewicht 1000g"+System.lineSeparator();
    
    String ausr =
            "Hering, Regal 0, Gewicht 20kg"+System.lineSeparator() +
            "Walhai, Regal 0, Gewicht 2050kg"+System.lineSeparator() +
            "Kaiserfisch, Regal 0, Gewicht 20kg"+System.lineSeparator() +
            "Meeresschildkroete, Regal 0, Gewichtsklasse 20kg"+System.lineSeparator() +
            "Wal, SpezialRegal 20004, Gewicht 20000kg"+System.lineSeparator() +
            "Lederkoralle, Regal 0, Gewicht 1000g"+System.lineSeparator() +
            "Feuerkoralle, Regal 0, Gewicht 1000g"+System.lineSeparator();
    
    String eino =
            "Hering, Regal 201, Gewicht 20kg"+System.lineSeparator() +
            "Walhai, Regal 202, Gewicht 2050kg"+System.lineSeparator() +
            "Kaiserfisch, Regal 203, Gewicht 20kg"+System.lineSeparator() +
            "Meeresschildkroete, Regal 4, Gewichtsklasse 20kg"+System.lineSeparator() +
            "Wal, SpezialRegal 105, Gewicht 20000kg"+System.lineSeparator() +
            "Lederkoralle, Regal 6, Gewicht 1000g"+System.lineSeparator() +
            "Feuerkoralle, Regal 7, Gewicht 1000g"+System.lineSeparator();
    
    String wieg =
            "Hering, Regal 201, Gewicht 23kg"+System.lineSeparator() +
            "Walhai, Regal 202, Gewicht 2100kg"+System.lineSeparator() +
            "Kaiserfisch, Regal 203, Gewicht 40kg"+System.lineSeparator() +
            "Meeresschildkroete, Regal 4, Gewichtsklasse 20kg"+System.lineSeparator() +
            "Wal, SpezialRegal 105, Gewicht 30000kg"+System.lineSeparator() +
            "Lederkoralle, Regal 6, Gewicht 1000g"+System.lineSeparator() +
            "Feuerkoralle, Regal 7, Gewicht 1000g"+System.lineSeparator();
    
    @Test
    public void testArchiv() throws Exception {
        Archiv a = Aufgabe2.createArchiv();
        assertEquals(init, a.toString());
        a.ausraeumen();
        assertEquals(ausr, a.toString());
        a.einordnen();
        assertEquals(eino, a.toString());
        a.neuWiegen(new int[]{23, 2100, 40, 23, 30, 3, 0});
        assertEquals(wieg, a.toString());
    }
    
    @Test
    public void testHierarchie() throws Exception {
        assertTrue(Meerestier.class.isAssignableFrom(Fisch.class));
    }
}

