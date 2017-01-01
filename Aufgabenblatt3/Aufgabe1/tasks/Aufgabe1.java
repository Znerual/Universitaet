/*******************************************************************************

 AUFGABENBLATT 3 - Allgemeine Informationen

 Achten Sie bei der Implementierung auf folgende Punkte:

 - Ihr Programm sollte den dazugehörenden Test (z.B. enthält Aufgabe1Test den
 Test zu Aufgabe1) bestehen.

 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich
 thematisch auf das erstellte Programm.  Sie müssen diese Zusatzfragen in der
 Übung beantworten können.

 - Verwenden Sie bei allen Ausgaben immer System.out.println().

 - Verwenden Sie für die Lösung der Aufgaben keine speziellen Aufrufe aus der
 Java-API, die die Aufgaben verkürzen würden.

 Abgabe: Die Abgabe erfolgt in TUWEL. Bitte laden Sie Ihr IntelliJ-Projekt
 bis spätestens Montag 07.11.2016 08:00 Uhr in TUWEL hoch. Zusätzlich
 müssen Sie in TUWEL ankreuzen welche Aufgaben Sie gelöst haben und während
 der Übung präsentieren können.

 ******************************************************************************/

/*
    Aufgabe 1) Schleifenanalyse

    Erweitern Sie die Klasse 'Aufgabe1' um eine statische Methode namens
    "drawNumDiamond(int h)", die einen Diamanten (Raute) mit Zahlen ausgibt.
    Der übergebene Parameter "h" entspricht der Höhe des Diamanten (Raute).
    Ein h=9 führt zu folgender Ausgabe:

        1
       222
      33333
     4444444
    555555555
     4444444
      33333
       222
        1

    Wird eine gerade Zahl dem Parameter "h" übergeben so wird "NO VALID INPUT"
    ausgegeben. Bei h=0 wird nichts ausgegeben und die Methode sofort verlassen.
    Der Rückgabetyp der Methode ist "void".
    Es sollen für die Implmentierung der Methdoe nicht mehr als 3 for-Schleifen
    (keine while- oder do/while-Schleife) verwendet werden. Überlegen
    Sie wie man eventuell weitere for-Schleifen einsparen könnte.
    (Die Methode soll für h <= 17 funktionieren.)

    Zusatzfragen:
    1. Wie ist die Vorgangsweise abzuändern, wenn statt jedem Wert 1 der
    Buchstabe A, statt jedem Wert 2 der Buchstabe B, ... und statt jedem Wert 5
    der Buchstabe E ausgegeben werden soll ?

*/
public class Aufgabe1 {
    
    // TODO: Implementieren Sie hier die Angabe
    
    
    public static void main(String[] args) {
        drawNumDiamond(9);
    }
    public static void drawNumDiamond(int h) {
        //Abbruchbed.
        if (h==0) return;
        if (h % 2 == 0) {
            System.out.println("NO VALID INPUT");
        } else {
            //Geht jede Zeile durch
            for (int i = 0; i < h; i++) {
                int amount; //Anzahl an Zahlen pro Zeile
                int trim; //Wird für die Anzahl der Leerzeichen vor und nach der Zahl benötigt
                if (i < h/2) {
                    amount = i * 2 + 1;
                    trim = i;
                } else if (i > h / 2) {
                    amount = (h - i) * 2 -1;
                    trim = h - i -1;
                } else {
                    amount = h;
                    trim = h;
                }
                for (int j = 0; j < h; j++) {
                    //Füllt jede Zeile mit den entsprechenden Werten
                    //h/2 ist der Mittelwert und trim die Anzahl an Werten links bzw rechts von der Mitte
                    if (j < (h/2 -trim) || j > (h/2) + trim) {
                        System.out.print(" ");
                    } else {
                        System.out.print((amount / 2 )+ 1);
                        //Zusatzfrage:
                        //char output = (char)(amount / 2 + 65);
                        //System.out.print(output);
                    }
                }
                System.out.println("");
            }
        }

    }
}






