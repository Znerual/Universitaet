import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.rules.Timeout;
import static org.junit.Assert.assertEquals;

public class Aufgabe4Test {
    
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);
    
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    private static int number = (int) (Math.random() * 1000) + 10;
    private static int[] fill = new int[]{12, 18, 8, 15, 9, 22, 4};
    
    @Test
    public void testCount() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < number; i++) {
            test.add((int) (Math.random() * number));
        }
        assertEquals(test.countNodes(), number);
    }
    
    @Test
    public void testLeaves() throws Exception {
        IntTree test = new IntTree();
        for (int i : fill) {
            test.add(i);
        }
        assertEquals(test.countLeaves(), 4);
    }
    
    @Test
    public void testHeight() throws Exception {
        IntTree test = new IntTree();
        for (int i : fill) {
            test.add(i);
        }
        assertEquals(test.height(), 3);
    }
    
    @Test
    public void testSpecialCase() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < number; i++) {
            test.add(i);
        }
        assertEquals(test.height(), number);
        assertEquals(test.countNodes(), number);
    }
    
    int[] tree_elem = new int[]{12,4,6,15,1,13,5,14};
    
    @Test
    public void testPrintLeaves() throws Exception {
        IntTree test = new IntTree();
        for(int i = 0; i < tree_elem.length; i++) {
            test.add(tree_elem[i]);
        }
        String[] testValues = new String[]{"1", "5", "14"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        test.printLeaves();
        assertEquals(testString.toString(), log.getLog());
    }
    
    @Test
    public void testPrintInOrderUp() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < tree_elem.length; i++) {
            test.add(tree_elem[i]);
        }
        String[] testValues = new String[]{"1", "4", "5", "6", "12", "13", "14", "15"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        test.printInOrderUp();
        assertEquals(testString.toString(), log.getLog());
    }
    
    @Test
    public void testPrintInOrderUpSub() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < tree_elem.length; i++) {
            test.add(tree_elem[i]);
        }
        String[] testValues = new String[]{"1", "4", "5", "6"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        test.printInOrderUpSub(4);
        assertEquals(testString.toString(), log.getLog());
    }
    
    @Test
    public void testPrintPostOrder() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < tree_elem.length; i++) {
            test.add(tree_elem[i]);
        }
        String[] testValues = new String[]{"1", "5", "6", "4", "14", "13", "15", "12"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        test.printPostOrder();
        assertEquals(testString.toString(), log.getLog());
    }
    
    @Test
    public void testPrintPreOrder() throws Exception {
        IntTree test = new IntTree();
        for (int i = 0; i < tree_elem.length; i++) {
            test.add(tree_elem[i]);
        }
        String[] testValues = new String[]{"12", "4", "1", "6", "5", "15", "13", "14"};
        StringBuilder testString = new StringBuilder();
        for(String i: testValues) {
            testString.append(i).append(System.lineSeparator());
        }
        test.printPreOrder();
        assertEquals(testString.toString(), log.getLog());
    }
}

