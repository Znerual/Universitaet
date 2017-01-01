import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.rules.Timeout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Aufgabe1Test {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    
    @Test
    public void testEquals() throws Exception {
        Person hubert = Aufgabe1.createHubert();
        Person julia = Aufgabe1.createJulia();
        Person thomas = Aufgabe1.createThomas();
        
        Person hubert2 = Aufgabe1.createHubert();
        Person julia2 = Aufgabe1.createJulia();
        Person thomas2 = Aufgabe1.createThomas();
        
        assertEquals(true, hubert.equals(hubert));
        assertEquals(true, hubert.equals(hubert2));
    
        assertEquals(false, hubert.equals(julia));
        assertEquals(false, hubert.equals(thomas));
    
        assertEquals(true, julia.equals(julia));
        assertEquals(true, julia.equals(julia2));
    
        assertEquals(false, julia.equals(hubert));
        assertEquals(false, julia.equals(thomas));
    
        assertEquals(true, thomas.equals(thomas));
        assertEquals(true, thomas.equals(thomas2));
    
        assertEquals(false, thomas.equals(hubert));
        assertEquals(false, thomas.equals(julia));
    
        assertEquals(true, hubert.hashCode() == hubert.hashCode());
        assertEquals(true, hubert.hashCode() == hubert2.hashCode());
        assertEquals(true, julia.hashCode() == julia.hashCode());
        assertEquals(true, julia.hashCode() == julia2.hashCode());
        assertEquals(true, thomas.hashCode() == thomas.hashCode());
        assertEquals(true, thomas.hashCode() == thomas2.hashCode());
    }
    
    String example = "# Hubert Maier, maennlich, 88 Jahre, Svnr: 1234\n" +
            " # Julia Maier, weiblich, 54 Jahre, Svnr: 1111\n" +
            "  # Thomas Maier, maennlich, 22 Jahre, Svnr: 2222\n" +
            "  # Gernot Maier, maennlich, 24 Jahre, Svnr: 3333\n" +
            " # Gernot Mueller, maennlich, 40 Jahre, Svnr: 1115\n" +
            "  # Roman Mueller, maennlich, 12 Jahre, Svnr: 1116\n" +
            "  # Sophie Mueller, weiblich, 14 Jahre, Svnr: 1117";
    
    @Test
    public void testToString() throws Exception {
        Person hubert = Aufgabe1.createHubert();
        //assertEquals(hubert.toString(), example);
        assertEquals(true, hubert.toString().equals(example));
    }
    
    @Test
    public void testNeuesKind() throws Exception {
        Person hubert = Aufgabe1.createHubert();
        Person julia = Aufgabe1.createJulia();
        Person thomas = Aufgabe1.createThomas();
        
        Person hubert2 = Aufgabe1.createHubert();
        Person julia2 = Aufgabe1.createJulia();
        Person thomas2 = Aufgabe1.createThomas();
        
        // should not modify hubert
        hubert.neuesKind(julia);
        assertEquals(true, hubert.toString().equals(example));
        hubert.neuesKind(julia2);
        assertEquals(true, hubert.toString().equals(example));
        assertEquals(true, hubert.equals(hubert2));
        
        // should modify hubert
        hubert.neuesKind(thomas);
        assertEquals(false, hubert.toString().equals(example));
        assertEquals(true, hubert.equals(hubert2));
    }
}


