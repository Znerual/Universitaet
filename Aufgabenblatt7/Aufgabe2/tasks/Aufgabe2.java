/*
    Aufgabe 2) dynamisches Binden und Interfaces

    Implementieren Sie folgende Hierarchie:

            interface Meerestier
          /      |        \      \
    Koralle  Fisch  Saeugetier  Schildkroete

    D.h. Koralle, Fisch, .. usw. implementieren alle das Interface Meerestier.

    Sie sollen eine Archivverwaltung eines Museums implementieren.

    Schreiben Sie dazu eine Implementierung des Interfaces Archiv in der Datei
    "ArchivImpl.java", in dem eine Liste von Meerestieren gespeichert wird.
    Sie dürfen die Methodensignaturen der Interfaces nicht verändern.

    Die Methoden sollen sich folgendermaßen verhalten:
	    - void registrieren(Meerestier m);
	      Fuegt ein Meerestier im Archiv hinzu.

	    - void ausraeumen();
	      Raeumt alle Tiere bis auf Saeugetiere aus (= ins Regal 0 geben)

	    - void einordnen();
	      Ordnet alle Tiere der Reihe nach in Regalnummern, allerdings sind
	      Saeugetiere in den Regalen ab 100 und Fische ab den Regalen 200
	      (einfach 100 respektive 200 zu dem Index hinzugeben)

        - void neuWiegen(int gewicht[]);
	      Wiegt alle Tiere neu. Allerdings gibt es einen systematischen Fehler
	      bei Schildkröten. Diese wiegen tatsächlich um 10% weniger als die
	      Waage angzeigt hat (der Wert im Array). Sauegetiere werden immer in
	      Tonnen gewogen. Korallen haben immer ein Kilogramm, unabhängig vom
	      Wert im Array.

    Achtung! Es darf kein if, instanceof, getClass() o.ä. verwendet werden um
             die beschriebenen Unterschiede auszuprogrammieren. Stattdessen
             soll ausschließlich dynamisches Binden verwendet werden!

    Eigenschaften der Tiere sollen in den Klassen Koralle, Fisch, Saeugetier
    und Schildkroete gespeichert werden. Bei Korallen soll es keine Eigenschaft
    Gewicht geben.

    Es soll möglich sein, das ganze Archiv mit System.out.println(archiv)
    auszugeben. Dabei soll jedes Tier in einer Zeile geschrieben werden. Wenn
    das Archiv leer ist, soll toString() "Leeres Archiv" ausgegeben.

    Initial (in createArchiv()) sollen die Tiere so angelegt werden, dass
    folgende Ausgabe entsteht (einfach Tiere explizit so erzeugen, dass diese
    Zahlen gespeichert sind und ausgegeben werden):

        Hering, Regal 250, Gewicht 20kg
        Walhai, Regal 280, Gewicht 2050kg
        Kaiserfisch, Regal 1220, Gewicht 20kg
        Meeresschildkroete, Regal 33, Gewichtsklasse 20kg
        Wal, SpezialRegal 20004, Gewicht 20000kg
        Lederkoralle, Regal 40, Gewicht 1000g
        Feuerkoralle, Regal 41, Gewicht 1000g

    Wird nach der Initialisierung ausraeumen() durchgeführt, soll danach
    folgendes ausgegeben werden:

        Hering, Regal 0, Gewicht 20kg
        Walhai, Regal 0, Gewicht 2050kg
        Kaiserfisch, Regal 0, Gewicht 20kg
        Meeresschildkroete, Regal 0, Gewichtsklasse 20kg
        Wal, SpezialRegal 20004, Gewicht 20000kg
        Lederkoralle, Regal 0, Gewicht 1000g
        Feuerkoralle, Regal 0, Gewicht 1000g

    Nach weiters einer Durchführung von einordnen():

        Hering, Regal 201, Gewicht 20kg
        Walhai, Regal 202, Gewicht 2050kg
        Kaiserfisch, Regal 203, Gewicht 20kg
        Meeresschildkroete, Regal 4, Gewichtsklasse 20kg
        Wal, SpezialRegal 105, Gewicht 20000kg
        Lederkoralle, Regal 6, Gewicht 1000g
        Feuerkoralle, Regal 7, Gewicht 1000g

    Und wenn danach alle Tiere neu gewogen werden {23,2100, 40, 23, 30, 3, 0}:

        Hering, Regal 201, Gewicht 23kg
        Walhai, Regal 202, Gewicht 2100kg
        Kaiserfisch, Regal 203, Gewicht 40kg
        Meeresschildkroete, Regal 4, Gewichtsklasse 20kg
        Wal, SpezialRegal 105, Gewicht 30000kg
        Lederkoralle, Regal 6, Gewicht 1000g
        Feuerkoralle, Regal 7, Gewicht 1000g

    Zusatzfragen:
    1. Wie könnte man die Programmieraufgabe mit statischem Binden lösen?
    2. Verwendet ein Interface dynamisches Binden?
*/
public class Aufgabe2 {
    
    static Archiv createArchiv() {
        Archiv archiv = new ArchivImpl();
        Meerestier hering = new Fisch("Hering", 250, 20);
        Meerestier walhai = new Fisch("Walhai", 280, 2050);
        Meerestier kaiserfisch = new Fisch("Kaiserfisch", 1220, 20);
        Meerestier schildkroete = new Schildkroete("Meeresschildkroete",33,20);
        Meerestier wal = new Saeugetier("Wal", 20004, 20000);
        Meerestier lederkoralle = new Koralle("Lederkoralle", 40);
        Meerestier feuerkoralle = new Koralle("Feuerkoralle", 41);
        archiv.registrieren(hering);
        archiv.registrieren(walhai);
        archiv.registrieren(kaiserfisch);
        archiv.registrieren(schildkroete);
        archiv.registrieren(wal);
        archiv.registrieren(lederkoralle);
        archiv.registrieren(feuerkoralle);
        return archiv;
    }
    
    public static void main(String[] args) {
        System.out.println(createArchiv());
    }
}

