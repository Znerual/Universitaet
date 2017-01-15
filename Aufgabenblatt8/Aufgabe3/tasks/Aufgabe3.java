import sun.security.provider.ConfigFile;

import java.util.HashMap;
import java.util.Scanner;

/*
    Aufgabe 3) hashCode, equals, Memoization -- Vier Gewinnt

    Fortsetzung des "Vier gewinnt" Spiels aus Aufgabenblatt 7.
    
    Hinweis: Sie können Hilfsmethoden implementieren.
    
    Bei der Berechnung von negamax() wird die selbe Stellung (Spielfeld mit
    bestimmten gesetzten Steinen) mehrmals bewertet: Z.B. ergeben die
    Zugfolgen x3-o2-x4 und x4-o2-x3 die selbe Stellung.  Und die Bewertung
    einer Stellung ist für die gleiche Tiefe auch jedes mal gleich (bzw. das
    Vorzeichen hängt noch vom Spieler ab, aber für die gleiche Stellung ist auch
    immer der gleiche Spieler dran). Da die Bewertung einer Stellung mit
    negamax() relativ teuer ist, vor allem für größere Tiefen, kann man Zeit
    sparen, indem man sich fuer eine berechnete Stellung die Bewertung merkt,
    und wenn sie noch einmal vorkommt, einfach gleich die gespeicherte Bewertung
    zurückgibt, statt sie noch einmal zu berechnen (diese Technik heißt
    Memoization).
    
    Kopieren Sie die Klassen Spielfeld und Viergewinnt, und ändern sie die
    Methode negamax() so, dass sie ab einer gewissen Tiefe vor der Berechnung
    der Bewertung überprüfen, ob die Stellung schon in einer
    HashMap<Spielfeld,Integer> abgespeichert ist, und wenn nicht, nach der
    Berechnung die Stellung und ihre Bewertung in der HashMap eintragen.
    
    Dazu müssen Sie für die Klasse Spielfeld auch die Methoden hashCode() und
    equals() implementieren.  Eine geeignete Hash-Funktion für unser Spielfeld
    ist
    
    (int)((c1*l1+c2*l2)>>32)
    
    wobei l1 und l2 die beiden longs des Spielfeldes sind, und c1 und c2
    grosse ungerade Konstanten, z.B. c1=0x97e2a1430e3ab551L,
    c2=0xddd7aaa5a1ccca9bL.
    
    Weiters dürfen Sie den Key, also das übergebene Spielfeld nach dem Einfügen
    in die HashMap nicht mehr verändern, und müssen daher Ihr Programm deswegen
    eventuell umorganisieren.
    
    Initialisieren Sie (bzw. löschen Sie mit clear() die HashMap an der
    richtigen Stelle, damit Sie einerseits nicht mit alten Bewertungen für
    andere Tiefen arbeiten, andererseits nicht aktuelle Bewertungen wegwerfen.
    
    Experimentieren Sie mit verschiedenen Tiefen, ab der die HashMap verwendet
    wird, und wählen Sie die, die die besten Ergebnisse bringt. Was waren die
    Ergebnisse bei den anderen Tiefen, die Sie ausprobiert haben?
    
    Zusatzfragen:
    
    1. Geben Sie eine Abschätzung der möglichen Zugfolgen und der möglichen
    Stellungen für Vier Gewinnt an.  Sie müssen dabei nicht ganz genau sein,
    aber die Berechnung sollte schon einigermassen plausibel sein. Welche Fehler
    hat Ihre Berechnung noch?
    
    2. Welche Änderungen waren an Ihrem Programm nötig, um die Anforderungen
    Map() im allgemeinen und von HashMap() im Besonderen zu erfüllen?
*/
public class Aufgabe3 {
    
    public static void main(String[] args) {
    }
}
class Spielfeld {
    public final int ZEILEN = 6;
    public final int SPALTEN = 7;
    public final int VICTORY_LIMIT = 4;
    public final int[] VALUE = {1, 100, 10000};
    private long spieler1, spieler2;
    public boolean unerlaubterZug;
    public Spielfeld() {
        spieler1 = 0;
        spieler2 = 0;
    }
    private Spielfeld copySpielfeld() {
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.spieler1 = this.spieler1;
        spielfeld.spieler2 = this.spieler2;
        return spielfeld;
    }
    public int feld(int zeile, int spalte)
    {
        long s1 = spieler1;
        if ((s1 >>> (8 * zeile + spalte) & 1) != 0) return 1;
        long s2 = spieler2;
        if ((s2 >>> (8 * zeile + spalte) & 1) != 0) return 2;
        return 0;
    }
    private void set(int zeile, int spalte, int spieler) {
        int pos = (8 * zeile + spalte);
        if (spieler == 1) {
            spieler1 |= (1L<< pos);
        } else if(spieler == 2) {
            spieler2 |= (1L<< pos);
        }
    }
    private void unset(int zeile, int spalte) {
        int pos = (8 * zeile + spalte);
        spieler1 &= (~(1L << pos));
        spieler2 &= (~(1L << pos));
    }
    public void spielstand(){

        for (int i = ZEILEN -1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < SPALTEN; j++) {
                switch (feld(i,j)) {
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("x");
                        break;
                    case 2:
                        System.out.print("o");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.print("+");
        for (int k = 0; k < SPALTEN ; k++) {
            System.out.print("-");
        }
        System.out.println("+");


    }
    public Spielfeld zug(int spieler, int spalte){
        if (spalte < 0 || spalte > 6) return null;
        for (int i = 0 ; i < ZEILEN; i++) {
            if (feld(i, spalte) == 0) {
                set(i, spalte, spieler);
                unerlaubterZug = false;
                return this;
            }
        }
        unerlaubterZug = true;
        return null;
    }
    public boolean gameFull() {
        for (int i = 0; i < SPALTEN; i++) {
            if (feld(0, i) == 0) {
                return false;
            }
        }
        return true;
    }
    private long setupX(int spieler) {
        long x;
        if (spieler == 1) {
            x = spieler1;
        } else {
            x = spieler2;
        }
       // System.out.println(Long.toBinaryString(x));
        return x;
    }
    public boolean sieg(int spieler) {
        if (value(spieler, 4) > 0) return true;
        return false;
    }
    public int value(int spieler, int length) {
        int count = 0;
        long x, result;
        // first, check 4-in-a-row:
        x = setupX(spieler);
        count += bitshiftCount(0, x, length);
       // System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);
        // now, check 4-in-a-column:
        x = setupX(spieler);
        count += bitshiftCount(8, x, length);
      //  System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);

        x = setupX(spieler);
        count += bitshiftCount(9, x, length);

        x = setupX(spieler);
        count += bitshiftCount(7, x, length);
        return count;
    }
    private int bitshiftCount(int amount, long x, int length) {
        long result = x;
        for (int i = 0; i < length; i++) {
            result &= (x >> (i*amount));
        }
        return Long.bitCount(result);
    }
    public int wert1(int spieler){
        int count = 0;
        for (int i = 2; i <= 4; i++) {
            int val = value(spieler,i);
          //  System.out.println("S:" + spieler + " Anzahl: " + i + " wert: " + val);
            count += VALUE[i -2] * val;
        }
        return count;
    }

    public int wert(int spieler){
        int opponent = spieler == 1 ? 2 : 1;
        return wert1(spieler) - wert1(opponent);
    }

    public int negamax(int spieler, int tiefe, HashMap<Spielfeld, Integer> memorizer){
        if(tiefe == 0){
            return wert(spieler);
        }
        int opponent = spieler == 1 ? 2 : 1;
        int bestMaxValue = Integer.MIN_VALUE;
        for(int i = 0; i <= 6; i++){
            if(zug(spieler,i) != null){
                int currentMaxValue;
                if(sieg(spieler)){
                    currentMaxValue = Integer.MAX_VALUE;
                }
                else{
                    if (memorizer.containsKey(this)) {
                        currentMaxValue = memorizer.get(this);
                    } else {
                        currentMaxValue = -negamax(opponent,tiefe-1, memorizer);
                        memorizer.put(this.copySpielfeld(), currentMaxValue);
                    }

                }
                if(currentMaxValue > bestMaxValue){
                    bestMaxValue = currentMaxValue;
                }
                unzug(i);
            }
        }
        return bestMaxValue;
    }

    // Hilfsmethode
    private void unzug(int spalte){
        if(spalte < 0 || spalte > 6){
            return;
        }
        if(feld(0, spalte) == 0){
            return;
        }
        int r = 0;
        while(r <= 5 && feld(r, spalte) != 0){
            r++;
        }
        unset(r-1, spalte);
    }

    public int bester(int spieler, int tiefe){
        int bestIndex = -1;
        int opponent = spieler == 1 ? 2 : 1;
        int bestMaxValue = Integer.MIN_VALUE;
        for(int i = 0; i <= 6; i++){
            if(zug(spieler,i) != null){
                int currentMaxValue;
                if(sieg(spieler)){
                    currentMaxValue = Integer.MAX_VALUE;
                }
                else{
                    currentMaxValue = -negamax(opponent,tiefe, new HashMap<Spielfeld, Integer>());
                }
                if(currentMaxValue > bestMaxValue){
                    bestMaxValue = currentMaxValue;
                    bestIndex = i;
                }
                unzug(i);
            }
        }
        return bestIndex;
    }
    public int hashCode() {
        return (int)((0x97e2a1430e3ab551L*this.spieler1+0xddd7aaa5a1ccca9bL*spieler2)>>32);
    }
    public boolean equals(Object other) {
        if (!(other instanceof Spielfeld)) return false;
        if (other == this) return true;
        if (((Spielfeld) other).spieler1 == this.spieler1 && ((Spielfeld) other).spieler2 == this.spieler2) return true;
        return false;
    }

}

class Viergewinnt {
    public static void spiel() {
        Scanner scanner = new Scanner(System.in);
        Spielfeld spielfeld = new Spielfeld();
        boolean gameOver = false;
        int currentPlayer = 1;
        do {
            spielfeld.spielstand();
            makeMove(scanner, currentPlayer, spielfeld);
            gameOver = (spielfeld.gameFull() || spielfeld.sieg(currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while (!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }

    private static int getInputSpalte(final Scanner scanner, int spieler) {
        System.out.printf("Sie sind an der Reihe, Spieler %d%n", spieler);
        System.out.printf("In welche Reihe möchten Sie Ihren Stein werfen? [1-7]%n");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                if (selection > 0 && selection < 8) {
                    return selection - 1;
                } else {
                    System.out.printf("Upps, du hast dich wohl vertippt. Die Zahl %d passt nicht ins Feld ;)%n", selection);
                }
            } else {
                System.out.printf("Hm, das verstehe ich leider nicht. Ich benötige bitte eine Zahl und nicht %s%n", scanner.next());
            }
        }
        return 0;
    }

    public static void spiel1(int tiefe) {
        Spielfeld spielfeld = new Spielfeld();
        boolean gameOver = false;
        int computer = 1;
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Möchtest du beginnen? [y/n]");
        if (scanner.next().equals("y")) {
            computer = 2;
        }
        do {
            spielfeld.spielstand();
            if (currentPlayer == computer) {
                int findMove = spielfeld.bester(currentPlayer, tiefe);
                spielfeld.zug(currentPlayer, findMove);

            } else {
                makeMove(scanner, currentPlayer, spielfeld);
            }
            gameOver = (spielfeld.gameFull() || spielfeld.sieg(currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while (!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }

    private static void makeMove(Scanner scanner, int currentPlayer, Spielfeld spielfeld) {
        do {
            int spalte = getInputSpalte(scanner, currentPlayer);
            spielfeld.zug(currentPlayer, spalte);
            if (spielfeld.unerlaubterZug) System.out.println("Hier kannst du keinen Stein platzieren!");
        } while (spielfeld.unerlaubterZug);
    }
}