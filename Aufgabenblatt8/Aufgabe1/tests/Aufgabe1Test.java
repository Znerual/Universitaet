import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.rules.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Aufgabe1Test {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void test_stringToInt_OK() throws Exception {
        assertTrue(Aufgabe1.stringToInt("-456897") == -456897);
    }
    
    @Test
    public void test_stringToInt_0() throws Exception {
        assertTrue(Aufgabe1.stringToInt("0") == 0);
    }
    
    @Test
    public void test_stringToInt_MAX() throws Exception {
        assertTrue(Aufgabe1.stringToInt("2147483647") == 2147483647);
    }
    
    @Test(expected = NumberFormatException.class)
    public void test_stringToInt_LETTERS() throws Exception {
        Aufgabe1.stringToInt("+45asdf");
    }
    
    @Test(expected = NumberFormatException.class)
    public void test_stringToInt_OVF() throws Exception {
        Aufgabe1.stringToInt("2147483648");
    }
    
    @Test
    public void test_printStringIfInt_NORMAL() throws Exception {
        Aufgabe1.printStringIfInt("-456897");
        assertEquals("-456897"+System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void test_printStringIfInt_0() throws Exception {
        Aufgabe1.printStringIfInt("0");
        assertEquals("0"+System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void test_printStringIfInt_MAX() throws Exception {
        Aufgabe1.printStringIfInt("2147483647");
        assertEquals("2147483647"+System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void test_printStringIfInt_LETTERS() throws Exception {
        Aufgabe1.printStringIfInt("+45asdf");
        assertEquals("Keine ganze Zahl"+System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void test_printStringIfInt_OVF() throws Exception {
        Aufgabe1.printStringIfInt("2147483648");
        assertEquals("Keine ganze Zahl"+System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void test_conditionalString_OK() throws Exception {
        assertTrue(Aufgabe1.conditionalString("test1test"));
    }
    
    @Test
    public void test_conditionalString_SHORT() throws Exception {
        try {
            if(Aufgabe1.conditionalString("") == true)
                fail();
        }catch(InvalidStringLengthException ex){
            //pass
        }
    }
    
    @Test
    public void test_conditionalString_LONG() throws Exception {
        try {
            if(Aufgabe1.conditionalString("1234567890") == true)
                fail();
        }catch(InvalidStringLengthException ex){
            //pass
        }
    }
    
    @Test
    public void test_conditionalString_NoNumber() throws Exception {
        try {
            if(Aufgabe1.conditionalString("abcdef") == true)
                fail();
        }catch(NoNumberException ex){
            //pass
        }
    }
    
    @Test
    public void test_conditionalString_MultipleNumber() throws Exception {
        try {
            if(Aufgabe1.conditionalString("abc4de5f") == true)
                fail();
        }catch(MultipleNumbersException ex){
            //pass
        }
    }
    
    @Test
    public void test_conditionalString_InvalidChar() throws Exception {
        try {
            if(Aufgabe1.conditionalString("abxcXdef") == true)
                fail();
        }catch(InvalidCharException ex){
            //pass
        }
    }
}


