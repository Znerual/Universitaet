/*
    Aufgabe5) Vervollständigung von Methoden

    Vervollständigen Sie die Methoden, sodass sie sich den Kommentaren
    entsprechend verhalten. Verändern Sie dabei nur Ausdrücke, die in einem
    Kommentar mit TODO: gekennzeichnet sind.
    Lassen Sie andere Teile der Klasse unverändert. Von dieser Einschränkung
    ausgenommen ist nur die Methode main, die Sie beliebig zum Testen verwenden
    können.

    Zusatzfragen:
    1. Warum können viele einfache rekursive Methoden durch nur eine einzige
       Return-Anweisung implementiert werden?
    2. In welchen Fällen sind die Ausdrücke (i & 1) und (i % 2) äquivalent,
       in welchen nicht?
    3. Wodurch unterscheiden sich die Operatoren >> und >>> in Java?
*/
public class Aufgabe5 {
    
    // Liefert den größten Rest aller Divisionen a / b für a zwischen 1 und x
    // (inklusive 1 und x)
    // Liefert 0 wenn x oder b kleiner als 1 ist
    private static int largestRemainder2(final int x, final int b) {
        if ( x < 1 || b < 1) {
            return 0;
        }
        final int rem = largestRemainder2(x-1, b);
        return (x<b? 1 + rem: rem);
    }
    private static int largestRemainder(final int x, final int b) {
        if (b > 1 && x > b && x % b != b -1) { //TODO: Ausdruck in (...) anpassen
            return (largestRemainder(x - 1, b)); //TODO: Ausdruck in (...) anpassen
        }
//        final int rem = largestRemainder(x , b); //TODO: Ausdrücke in (...) anpassen
        return ((b > 1 && x > 1)? x % b : 0); //TODO: Ausdruck in (...) anpassen
    }
    
    // Liefert (x * (2^n)) wenn n >= 0, liefert (x / (2^-n)) wenn n <= 0;
    // Nur vordefinierte Operatoren (in JAVA verfügbar) sollen zur Lösung dieses
    // Problems verwendet werden.
    private static int toThePower(final int x, final int n) {
        //0 ist das Abbruchargument, ansonsten wird für zwei fälle unterschieden und n hoch/runtergezählt und mulitpliziert bzw dividiert
        return (n > 0 ? toThePower(x, n -1) * 2 : n < 0 ? toThePower(x, n +1) / 2 : x); //TODO: Ausdruck in (...) anpassen
    }
    
    // Liefert die Faktorielle von i wenn i > 0, ansonsten wird i zurückgegeben.
    // Verhindert einen Überlauf eines int-Wertes, aber nicht den Überlauf eines
    // long-Wertes.
    private static long fact(final int i) {
        //falls i > 0 stehen würde, würde der ausdruck immer 0 sein (mult. mit 0)
        return (i > 1 ? i * fact(i -1)  : i); //TODO: Ausdruck in (...) anpassen
    }
    
    // Liefert einen String mit dem Inhalt i gefolgt von i Punkten, wenn i > 0;
    // z.B., recString(4) liefert "4....";
    // Liefert nur Punkte wenn i <= 0; z.B., recString(-4) liefert "....".
    private static String recString(final int i) {
        //Fügt falls beim ersten Aufruf i > 0 die Zahl hinzu, macht i < 0 und zählt bis 0 hinauf
        //beim hinaufzählen wird bis i == 0 ein pujnkt hinzugefügt
        return (i > 0 ? String.valueOf(i) + recString(i * -1): i != 0 ? "." + recString(i+1) : ""); //TODO: Ausdruck in (...) anpassen
    }

    // Liefert die Summe aller ungeraden Zahlen im Intervall zwischen 'left'
    // und 'right' (inklusive 'left' und 'right');
    // Liefert 0 wenn 'right' kleiner als 'left' ist.
    private static int oddSum(final int left, final int right) {
        //Idee: Linker wert bleibt stehen, rechter wert rückt immer um zwei felder weiter nach links
        //Beim ersten durchgang wird überprüft ob der startwert gerade oder ungerade ist
        //Kleines Problem bei negativen linken werten, weil der Modulo auch negativ sein kann, dh beim startwertüberpr. auch auf -1 schauen
        return (right < left ? 0 : (right % 2 == 1 || right % 2 == -1)? oddSum(left , right -2) + right : oddSum(left, right -1)); //TODO: Ausdruck in (...) anpassen
    }
    
    public static void main(String[] args) {
        System.out.println(recString(5));
        System.out.println(toThePower(4,3));
        System.out.println(fact(4));
        System.out.println(oddSum(2,8));
        System.out.println(largestRemainder(20,0));
    }
}




