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
    
    @Test
    public void testSearchPalindrom() throws Exception {
        Aufgabe2.searchPalindrome("Geschichte.txt","Palindrome.txt");
        String[] testValues = new String[]{"Lagerregal", "Regallager", "neben", "Hannah", "Rentner", "Retter", "Neffen", "Uhu", "Otto"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        assertEquals(testString.toString(), log.getLog());
    }
    
    @Test
    public void testGenerateStatistic() throws Exception {
        Aufgabe2.generateStatistic("Geschichte.txt");
        String[] testValues = new String[]{
                        "a - 17",
                        "b - 3",
                        "c - 2",
                        "d - 10",
                        "e - 41",
                        "f - 4",
                        "g - 6",
                        "h - 7",
                        "i - 11",
                        "k - 1",
                        "l - 12",
                        "m - 6",
                        "n - 25",
                        "o - 9",
                        "p - 2",
                        "r - 21",
                        "s - 11",
                        "t - 15",
                        "u - 10",
                        "v - 4",
                        "w - 3",
                        "x - 1",
                        "z - 1",
                        "ß - 1"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        assertEquals(testString.toString(), log.getLog());
    }
}

