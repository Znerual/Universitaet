/*
    Aufgabe 2) Rekursion und Termination

    Die Methoden f, g, h, p und q sind vorgegeben. Rufen Sie in main jede
    dieser Methoden mit allen Argumenten im Bereich von [-10, 10] (in
    aufsteigender Reihenfolge) auf und geben sie die Ergebnisse aus, wenn die
    Aufrufe mit diesen Werten terminieren. Aufrufe, die nicht terminieren
    würden, sind auszulassen. Vermeiden Sie Exceptions.

    Zusatzfragen:
    1. Beschreiben Sie überblicksartig, was die Methoden f, g, h, p und q
    berechnen.
    2. Nennen Sie für jeden nicht terminierenden Aufruf von f, g, h, p und q im
    Intervall [-10, 10] einen Grund für die Endlosrekursion.
    2. Bedeutet ein StackOverflowError immer, dass eine Endlosrekursion
    vorhanden ist?
*/
public class Aufgabe2{
    //berechnet 2 mal die wurzel aus 10, terminiert für alle x
    private static int f(int x) {
        return x * x > 10 ? 0 : f(x - 1) + 1;
    }
    //terminiert nie für positive x, weil x/ 2 >= 0 ist, für negative x liefert es den wert 0
    private static int g(int x) {
        return x < 0 ? 0 : g(x / 2) + 1;
    }
    //terminiert für alle x, außer 1, berechnet x^wieviel = 10, log_x = 10
    private static int h(int x) {
        return x > 10 ? 0 : h(x * x) + 1;
    }
    //terminiert für x = 0,
    //Bei ungeradem x - durch 2
    private static int p(int x) {
        return x == 0 ? 0 : x % 2 == 1 ? p(x / 2) + 2 : p(- x - 1) + 1;
    }
    //Negative Zahlen werden um eins größer gemacht und dann auf % 3 geprüft, positive um  +2 größer, falls sie nicht vorher schon % 3 sind
    private static int q(int x) {
        return x % 3 == 0 ? 0 : q(x + x % 3 + 1) + 1;
    }
    
    public static void main(String[] args) {
        System.out.println("Ergebnisse für f:");
        for (int i = -10; i <= 10; i++ ) {
           //System.out.println("f:" + i + ", " + f(i));
            System.out.println(f(i));
        }
        
        System.out.println("Ergebnisse für g:");
        for (int i = -10; i < 0; i++ ) {
         //   System.out.println("g:" + i + ", " + g(i));
            System.out.println(g(i));
        }
        
        System.out.println("Ergebnisse für h:");
        for (int i = -10; i <= 10; ) {
            if (i <= 1 && i >= -1)
            {
                i++;
            } else {
              //  System.out.println("h:" + i + ", " + h(i));
                System.out.println(h(i));
                i++;
            }

        }
        
        System.out.println("Ergebnisse für p:");
        for (int i = -8; i <= 8; i++) {
            if ((i % 2 == 1 && i != 5) || ((-i -1) % 2 == 1 && i != -6) || i == 0 || i == -1) {
                System.out.println(p(i));
               // System.out.println("p:" + i + ", " + p(i));
            }

        }
        
        System.out.println("Ergebnisse für q:");
        for (int i = -10; i <= 10; i++ ) {
            int mod = i % 3;
            if (mod  == 0 || (i <= 0 ?(i - 1) % 3 == 0 : (i + 2) % 3 == 0 )){
              //  System.out.println("q:" + i + ", " + q(i));
                System.out.println(q(i));
            }

        }


    }
}

