import java.util.Scanner;

/*
    Aufgabe 3, 4, 5,) Zweidimensionale Arrays -- Vier Gewinnt

    Beim Spiel "Vier gewinnt" gewinnt der Spieler, der als erstes vier Steine
    in eine Reihe bringt (horizontal, vertikal, oder diagonal). Das Spielfeld
    steht senkrecht und ist 7 Spalten breit und 6 Reihen hoch. Steine können nur
    im untersten Feld einer Spalte platziert werden, das noch nicht von einem
    anderen Stein besetzt ist.

    "Vier gewinnt" wird in mehreren, aufeinander aufbauenden Aufgaben in
    mehreren Aufgabenblättern verwendet, Sie sollten daher diese Aufgaben
    unbedingt lösen. In diesem Aufgabenblatt deckt das Spiel "Vier gewinnt"
    3 Aufgaben ab. Bitte kreuzen Sie diese separat in TUWEL an.
    
    Hinweis: Sie können Hilfsmethoden implementieren, dürfen aber vorgegebene
             Methoden und deren Signaturen nicht verändern.
    
    *****************************  Aufgabe 3  **********************************
    Für Aufgabe 3 schreiben Sie folgende statische Methoden:

    1) public static int[][] spielfeld()
    
    Diese Methode erzeugt ein leeres Vier-Gewinnt-Spielfeld. Das Spielfeld soll
    als zweidimensionales Array von int-Werten dargestellt werden, wobei auf
    ein Feld in Reihe r und Spalte s im Feld f mit f[r][s] zugegriffen werden
    soll. Ein leeres Feld wird mit 0 repraesentiert, ein Stein auf einem Feld
    durch 1 für einen Stein des Spielers 1 bzw. 2 für einen Stein des
    Spielers 2.

    2) public static void spielstand(int[][] f)
    
    Diese Methode gibt den Spielstand f in folgender Form aus:
    
    |       |
    |       |
    |       |   Definition: Die linke unterste Ecke ist als Koordinate [0][0]
    |       |               definiert und stellt den Ausgangspunkt des
    |  xo   |               Spielbrettes dar.
    |  ox   |
    +-------+
    
    wobei für ein leeres Feld ein Leerzeichen ausgegeben wird, für einen Stein
    von Spieler 1 ein x, und für einen Stein von Spieler 2 ein o.
    
    Zusatzfragen:
    1. Welche anderen Möglichkeiten neben der von Ihnen gewählten gibt es, um
    von der Spielernummer auf x bzw. o zu kommen?
    ****************************************************************************
    
    *****************************  Aufgabe 4  **********************************
    Für Aufgabe 4 schreiben Sie folgende statische Methoden:

    1) public static int[][] zug(int[][] f, int spieler, int spalte)

    Diese Methode führt einen Zug des Spielers "spieler" in Spalte
    "spalte" (0-6 für legale Züge) durch und gibt die neue Stellung
    (das Spielfeld nach dem Zug) zurück.  Wenn in Spalte "spalte" kein
    Zug möglich ist (weil die Spalte voll ist oder nicht im erlaubten
    Bereich), soll zug() null zurückgeben.  Das vom Parameter f
    referenzierte Feld darf verändert werden oder unverändert bleiben.

    2) public static boolean sieg(int[][] f, int spieler)

    Diese Methode liefert true, wenn "spieler" vier Steine in einer Reihe hat,
    sonst false.

    Zusatzfragen:
    1. Welche Vor- und Nachteile hat es für dieses Beispiel und in
    Hinblick auf Aufgabe 5, den Parameter f von zug() zu verändern.
    ****************************************************************************

    *****************************  Aufgabe 5  **********************************
    Für Aufgabe 5 schreiben Sie folgende statische Methode:

    1) public static void spiel()

    Diese Methode führt ein Vier-Gewinnt-Spiel zwischen zwei Spielern durch:
    Beginnend mit einem leeren Spielfeld werden abwechselnd Spieler 1 und
    Spieler 2 zur Eingabe eines Spielzuges aufgefordert, der Spielzug
    durchgeführt, und der aktuelle Spielstand ausgegeben, solange bis ein
    Spieler gewonnen hat oder das Spielfeld voll ist. Überlegen Sie sich
    eine sinnvolle Behandlung von ungültigen Eingaben. In "main" wird am Ende
    nur noch die Methode spiel() aufgerufen. Testen Sie spiel() selbst, auch
    den Fall, dass das Spielfeld voll wird, ohne dass ein Spieler gewonnen hat.

    Zusatzfragen:
    1. Was machen Sie bei ungültigen Eingaben?
    ****************************************************************************
*/
public class Aufgabe3 {
    static int r = 6;
    static int s = 7;
    static int victoryLimit = 4;
    static boolean unerlaubterZug = false;
    //***************************  Aufgabe 3  **********************************
    public static int[][] spielfeld(){
        int ia_spielfeld[][] = new int[r][s];

        return ia_spielfeld; //diese Anweisung ändern oder löschen.
    }
    
    public static void spielstand(int[][] f){
        for (int i = r -1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < s; j++) {
                switch (f[i][j]) {
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
        for (int k = 0; k < s ; k++) {
            System.out.print("-");
        }
        System.out.println("+");

    }
    //**************************************************************************
    
    
    //***************************  Aufgabe 4  **********************************
    public static int[][] zug(int[][] f, int spieler, int spalte){
        if (spalte < 0 || spalte > 6) return null;
        for (int i = 0 ; i < r; i++) {
            if (f[i][spalte] == 0) {
                f[i][spalte] = spieler;
                unerlaubterZug = false;
                return f;
            }
        }
        unerlaubterZug = true;
        return null; //diese Anweisung ändern oder löschen.
    }
    private static boolean testStraight(int[][] f, int spieler, int loop1, int loop2, boolean inverse) {
        for (int i = 0;i <loop1; i++ ) {
            int count = 0;
            for (int j = 0; j < loop2; j++) {
                if (inverse) {
                    if (f[i][j] == spieler) {
                        if (++count > victoryLimit -1) {
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                } else {
                    if (f[j][i] == spieler) {
                        if (++count > victoryLimit -1) {
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                }

            }
        }
        return  false;
    }
    private static boolean testDiagonal(int [][] f, int spieler, int loop1Start, int loop1End,  boolean leftToRight) {
        for (int i = loop1Start; i < loop1End; i++ ){
            for (int j = 0; j < s-victoryLimit+1; j++) {
                int count = 0;
                for (int k = 0; k < victoryLimit; k++) {
                    if (leftToRight) {
                        if (f[i + k][j + k] == spieler) {
                            if (++count > victoryLimit -1) {
                                return true;
                            }
                        }
                    } else {
                        if (f[i - k][j + k] == spieler) {
                            if (++count > victoryLimit -1) {
                                return true;
                            }
                        }
                    }

                }
            }
        }//Diagonal 1: l1s = 0, l2s = 0, l1e = r - vL, l2e = s - vL, lT = true
        return  false;
    }
    public static boolean sieg(int[][] f, int spieler) {


        //Horizontal
        return testStraight(f,spieler,r,s, true) || testStraight(f,spieler,s,r, false) || testDiagonal(f, spieler,0,r-victoryLimit, true) || testDiagonal(f,spieler,victoryLimit -1, r,false);
        //Vertikal



    }
    public static boolean gameFull(int[][] f) {
        for (int i = 0; i < s; i++) {
            if (f[0][i] == 0) {
                return false;
            }
        }
        return true;
    }
    //**************************************************************************
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
    
    //***************************  Aufgabe 5  **********************************
    public static void spiel(){
        Scanner scanner = new Scanner(System.in);
        int[][] ai_spielfeld = spielfeld();
        boolean gameOver = false;
        int currentPlayer = 1;
        do {
            spielstand(ai_spielfeld);
            do {
                int spalte = getInputSpalte(scanner, currentPlayer);
                zug(ai_spielfeld, currentPlayer, spalte);
                if (unerlaubterZug) System.out.println("Hier kannst du keinen Stein platzieren!");
            } while (unerlaubterZug);
            gameOver = (gameFull(ai_spielfeld) || sieg(ai_spielfeld, currentPlayer));
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        } while(!gameOver);
        System.out.println("Das spiel ist vorbei!");
    }
    //**************************************************************************

    public static void main(String[] args) {

        spiel();
    }
}

