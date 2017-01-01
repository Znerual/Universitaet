/*
    Aufgabe 3) Rekursion in Iteration ändern

    Stellen Sie fest, was die Methode 'rec' macht. Schreiben Sie eine statische
    Methode 'iter', die das Gleiche macht wie 'rec'
    (gleiches Input-Output-Verhalten), aber ohne Rekursion auskommt.

    Zusatzfragen:
    1. Warum ist es notwendig, negative Parameterwerte getrennt zu behandeln?
    2. Warum ist es notwendig, 0 als Sonderfall zu behandeln?
    3. Ist es immer vernünftig, sich an die Hinweise einer IDE zu halten?
    
*/
public class Aufgabe3 {
    
    //Was berechnet "rec"?
    public static int rec(int x, int y) {
        //Betrag von x
        if (x < 0) {
            return rec(-x, y);
        }
        //Betrag von y
        if (y < 0) {
            return rec(x, -y);
        }
        //Abbruchbed. 1
        if (x == 0) {
            return 0;
        }
        //Sortieren, das größere nach rechts
        if (x > y) {
            return rec(y, x);
        }
        //Abbruch 2
        if (x == y) {
            return x;
        }
        //Falls x und y nicht gleich, 0 < x <= y, wird y kleiner
        //ermittelt das ggV(x,y)
        return rec(x, y - x);
    }
    public static int iter(int x, int y) {
        int xp = x > 0 ? x : -x;
        int yp = y > 0 ? y : -y;
        while (xp > 0) {
            if (xp == yp) {
                return xp;
            }
            if (xp > yp) {
                int swapBuffer = xp;
                xp = yp;
                yp = swapBuffer;
            }
            yp -= xp;
        }
        return 0;

    }
    // TODO: Implementieren Sie hier die Angabe
    
    public static void main(String[] args) {
        System.out.println(iter(8,2));
        System.out.println(iter(212, 4) == rec(212,4));
    }
}





