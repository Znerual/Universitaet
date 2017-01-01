/*
    Aufgabe 3) Klassen und Objekte -- Vier Gewinnt

    Fortsetzung des "Vier gewinnt" Spiels aus Aufgabenblatt 5.
     
    Auch auf diese Aufgabe werden spätere Aufgabenblätter aufbauen, Sie sollten
    sie daher unbedingt lösen.
    
    Definieren Sie eine Klasse Spielfeld mit einem Konstruktor, der ein leeres
    Spielfeld erzeugt, und folgenden (nicht-statischen) Methoden:

    - int feld(int reihe, int spalte) gibt 0, 1, oder 2 zurück, je nachdem, ob
      das Feld besetzt ist und von wem.

    Folgende Methoden sind nicht-statische Varianten für Spielfeld der früher
    definierten statischen Methoden:

    - void spielstand()
    - Spielfeld zug(int spieler, int Spalte)
    - boolean sieg(int spieler)
    - int wert1(int spieler)
    - int wert(int spieler)
    - int negamax(int spieler, int tiefe)
    - int bester(int spieler, int tiefe)

    Das Spielfeld wird dabei noch immer so repräsentiert wie zuvor.

    Schreiben Sie eine Klasse Viergewinnt mit den Methoden

    - public static void spiel()
    - public static void spiel1(int tiefe)

    die für das Spielfeld und die Methoden dazu die Klasse Spielfeld benutzen.

    Zusatzfrage: Ist spiel1() bei gleicher Tiefe durch diese Änderung
    schneller oder langsamer geworden? Um wieviel?
    ****************************************************************************
*/
// TODO: Implementieren Sie hier die Klasse "Spielfeld"

import java.util.Scanner;

// TODO: Implementieren Sie hier die Klasse "Viergewinnt"
class Viergewinnt {
    public static void spiel(){
        Scanner scanner = new Scanner(System.in);
        Spielfeld spielfeld = new Spielfeld();
        boolean gameOver = false;
        int currentPlayer = 1;
        do {
            spielfeld.spielstand();
            do {
                int spalte = getInputSpalte(scanner, currentPlayer);
                spielfeld.zug(currentPlayer, spalte);
                if (spielfeld.unerlaubterZug) System.out.println("Hier kannst du keinen Stein platzieren!");
            } while (spielfeld.unerlaubterZug);
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
                do {
                    int spalte = getInputSpalte(scanner, currentPlayer);
                    spielfeld.zug(currentPlayer, spalte);
                    if (spielfeld.unerlaubterZug) System.out.println("Hier kannst du keinen Stein platzieren!");
                } while (spielfeld.unerlaubterZug);
            }
            gameOver = (spielfeld.gameFull() || spielfeld.sieg(currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while(!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }
}
class Spielfeld {
    public final int ZEILEN = 6;
    public final int SPALTEN = 7;
    public final int VICTORY_LIMIT = 4;
    private int[][] m_feld;
    public boolean unerlaubterZug;
    public Spielfeld() {
        m_feld = new int[ZEILEN][SPALTEN];
    }
    public int feld(int zeile, int spalte) {
        return m_feld[zeile][spalte];
    }
    public void spielstand(){

        for (int i = ZEILEN -1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < SPALTEN; j++) {
                switch (m_feld[i][j]) {
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
            if (m_feld[i][spalte] == 0) {
                m_feld[i][spalte] = spieler;
                unerlaubterZug = false;
                return this;
            }
        }
        unerlaubterZug = true;
        return null;
    }
    public boolean gameFull() {
        for (int i = 0; i < SPALTEN; i++) {
            if (m_feld[0][i] == 0) {
                return false;
            }
        }
        return true;
    }
    public boolean sieg(int spieler) {
        // first, check 4-in-a-row:
        for (int r = 0; r <= 5; r++) {
            int cont = 0;
            for (int c = 0; c <= 6; c++) {
                if (m_feld[r][c] == spieler){
                    cont++;
                }
                else {
                    cont = 0;
                }
                if (cont == 4) {
                    return true;
                }
            }
        }

        // now, check 4-in-a-column:
        for (int c = 0; c <= 6; c++) {
            int cont = 0;
            for (int r = 0; r <= 5; r++) {
                if (m_feld[r][c] == spieler){
                    cont++;
                }
                else{
                    cont = 0;
                }
                if (cont == 4) {
                    return true;
                }
            }
        }

        // then, check 4-in-a-positive-diagonal:
        for (int d = -2; d <= 3; d++) {
            int cont = 0;
            for (int r = 0; r <= 5; r++) {
                int c = r + d;
                if (c >= 0 && c <= 6) {
                    if (m_feld[r][c] == spieler) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    if (cont == 4) {
                        return true;
                    }
                }
            }
        }

        // finally, check 4-in-a-negative-diagonal:
        for (int d = 3; d <= 8; d++) {
            int cont = 0;
            for (int r = 5; r >= 0; r--) {
                int c = d - r;
                if (c >= 0 && c <= 6) {
                    if (m_feld[r][c] == spieler) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    if (cont == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public int wert1(int spieler){
        int result = 0;
        for (int r = 0; r <= 5; ++r) {
            for(int c = 0; c <= 6; ++c) {
                for (int len = 2, weight = 1; len <=4; len++, weight*=100) {
                    boolean isRow = true, isColumn = true, isDiagP = true, isDiagN = true;
                    for(int i = 0; i < len; i++) {
                        if (c+i > 6 || m_feld[r][c+i] != spieler) isRow = false;
                        if (r+i > 5 || m_feld[r+i][c] != spieler) isColumn = false;
                        if (r+i > 5 || c+i > 6 || m_feld[r+i][c+i] != spieler) isDiagP = false;
                        if (r-i < 0 || c+i > 6 || m_feld[r-i][c+i] != spieler) isDiagN = false;
                    }
                    if (isRow) result += weight;
                    if (isColumn) result += weight;
                    if (isDiagP) result += weight;
                    if (isDiagN) result += weight;
                }
            }
        }
        return result;
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
    private int[][] unzug(int spalte){
        if(spalte < 0 || spalte > 6){
            return null;
        }
        if(m_feld[0][spalte] == 0){
            return null;
        }
        int r = 0;
        while(r <= 5 && m_feld[r][spalte] != 0){
            r++;
        }
        m_feld[r-1][spalte] = 0;
        return m_feld;
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
public class Aufgabe3 {
    
    public static void main(String[] args) {
    }
}

