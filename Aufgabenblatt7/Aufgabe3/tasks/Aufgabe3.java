import java.util.Scanner;

/*
    Aufgabe 3) Datenrepräsentation -- Vier Gewinnt

    Fortsetzung des "Vier gewinnt" Spiels aus Aufgabenblatt 6.
     
    Auch auf diese Aufgaben werden spätere Aufgabenblätter aufbauen, Sie sollten
    sie daher unbedingt lösen.
    
    Hinweis: Sie können Hilfsmethoden implementieren.
    
    Kopieren Sie die Klassen Spielfeld und Viergewinnt, und ersetzen Sie die
    Repräsentation des Spielfeldes in der Klasse Spielfeld durch folgende:
    Das Spielfeld wird durch zwei longs repräsentiert: Im ersten long sind die
    Bits gesetzt, die den Steinen des ersten Spielers entsprechen, im zweiten
    die, die den Steinen des zweiten Spielers entsprechen. Die Nummerierung der
    Bits im Spielfeld ist wie folgt:
    
    |40 41 42 43 44 45 46|47
    |32 33 34 35 36 37 38|39
    |24 25 26 27 28 29 30|31
    |16 17 18 19 20 21 22|23
    | 8  9 10 11 12 13 14|15
    | 0  1  2  3  4  5  6| 7
    +--------------------+
    
    Wobei die bits 7, 15, 23, 31, 39, 47 keinen Feldern des Spielfelds
    entsprechen und immer auf 0 bleiben müssen.
    
    Durch diese Repräsentation kann die Überprüfung für sieg() und wert1()
    wesentlich schneller durchgeführt werden: Beachten Sie, dass der rechte
    Nachbar eine um 1 höhere Nummer hat, der Nachbar links oben eine um 7 höhere
    Nummer, der Nachbar oben eine um 8 höhere Nummer, und der Nachbar rechts
    oben eine um 9 höhere Nummer.  Wenn wir also z.B.
    
    (x>>(0*9)) & (x>>(1*9)) & (x>>(2*9)) & (x>>(3*9))
    
    berechnen, und im Resultat z.B. an Stelle 3 ein Bit gesetzt ist, heisst das,
    dass in x an Stelle 3, 12, 21, 30 ein Bit gesetzt ist und damit vier in
    einer Diagonale nach rechts oben gesetzt sind. Allgemeiner, wenn irgendein
    Bit im Resultat gesetzt ist (das Resultat
 also ungleich 0 ist), hat man
    irgendwo vier in einer Diagonale nach rechts oben; die achte, leere Spalte,
    sorgt dafuer, dass horizontal und diagonal keine Reihen jenseits des Randes
    fortgesetzt werden. Durch Verwenden von shifts um n*1, n*7, und n*8
    überprüfen Sie entsprechend auch horizontale Reihen, Reihen nach links oben,
    und Reihen nach oben.
    
    Für wert1() verwendet man entsprechend Ausdrücke auch mit drei und nur zwei
    Termen. Um dann für die Berechnung des Wertes die Anzahl der Reihen
    (also bits) zu zählen, kann man die Methode java.lang.Long.bitCount()
    verwenden.

    Probieren Sie für den Aufruf von spiel1() verschiedene Werte für
    tiefe aus, und wählen Sie einen, bei dem der Computer im
    Normalfall zwischen 0.1s und 1s zur Auswahl des besten Zugs
    braucht.

    Zusatzfragen:

    1) Ist spiel1() bei gleicher Tiefe durch diese Änderung schneller
    oder langsamer geworden? Um wieviel?

    2) Welche Methoden mussten Sie für diese Änderung ändern und
    welche sind gleichgeblieben?  Hätten Sie einige der geänderten
    Methoden anders schreiben können, um die Notwendigkeit von
    Änderungen zu vermeiden?  Gibt es neben den Kosten der Veränderung
    bei manchen Methoden auch Vorteile durch die Änderung?  Welche?
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
        result = x;
        for (int i = 0; i < length; i++) {
            result &= (x >> (i));
        }
        count += Long.bitCount(result);
       // System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);
        // now, check 4-in-a-column:
        x = setupX(spieler);
        result = x;
        for (int i = 0; i < length; i++) {
            result &= (x >> (i*8));
        }
        count += Long.bitCount(result);
      //  System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);

        x = setupX(spieler);
        result = x;
        for (int i = 0; i < length; i++) {
            result &= (x >> (i*9));
        }
        count += Long.bitCount(result);
       // System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);
        // finally, check 4-in-a-negative-diagonal:
        x = setupX(spieler);
        result = x;
        for (int i = 0; i < length; i++) {
            result &= (x >> (i*7));
        }
        count += Long.bitCount(result);
       // System.out.println("B: " + Long.toBinaryString(x)+ " V: " + Long.bitCount(x) + " C: " + count);
        return count;
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

    public int negamax(int spieler, int tiefe){
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
                    currentMaxValue = -negamax(opponent,tiefe-1);
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
                    currentMaxValue = -negamax(opponent,tiefe);
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


}

class Viergewinnt {
    public static void spiel(){
        Scanner scanner = new Scanner(System.in);
        Spielfeld spielfeld = new Spielfeld();
        boolean gameOver = false;
        int currentPlayer = 1;
        do {
            spielfeld.spielstand();
            makeMove(scanner, currentPlayer, spielfeld);
            gameOver = (spielfeld.gameFull() || spielfeld.sieg(currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while(!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }
    private static int getInputSpalte(final Scanner scanner, int spieler) {
        System.out.printf("Sie sind an der Reihe, Spieler %d%n", spieler);
        System.out.printf("In welche Reihe möchten Sie Ihren Stein werfen? [1-7]%n");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                if (selection > 0 && selection < 8) {
                    return selection -1;
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
        } while(!gameOver);
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

