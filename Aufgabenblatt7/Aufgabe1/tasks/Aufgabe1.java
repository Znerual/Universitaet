/*******************************************************************************

 AUFGABENBLATT 7 - Allgemeine Informationen

 Achten Sie bei der Implementierung auf folgende Punkte:

 - Ihr Programm sollte den dazugehörenden Test (z.B. enthält Aufgabe1Test den
 Test zu Aufgabe1) bestehen.

 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich
 thematisch auf das erstellte Programm.  Sie müssen diese Zusatzfragen in der
 Übung beantworten können.

 - Verwenden Sie bei allen Ausgaben immer System.out.println().

 Abgabe: Die Abgabe erfolgt in TUWEL. Bitte laden Sie Ihr IntelliJ-Projekt
 bis spätestens Montag 09.01.2017 08:00 Uhr in TUWEL hoch. Zusätzlich
 müssen Sie in TUWEL ankreuzen welche Aufgaben Sie gelöst haben und während
 der Übung präsentieren können.

 ******************************************************************************/
/*
    Aufgabe 1) toString, equals, hashCode implementieren

    Implementieren Sie die Klasse Person mit folgenden Eigenschaften:

    - Vorname
    - Nachname
    - Geschlecht
    - Alter (als Zahl)
    - Sozialversicherungsnummer (als vierstellige Zahl)
    - Liste aller Kinder dieser Person (Es kann beliebig viele Kinder,
      aber auch gar kein Kind geben)

    Abgesehen von der Liste der Kinder sollen alle Eigenschaften im
    Konstruktor festgelegt werden.

    Vermeiden Sie unnötige "setter"-Methoden!

    Um ein Kind hinzufügen zu können, implementieren Sie die Methode:

      void neuesKind(Person p);

    Stellen Sie sicher, dass die Ausgabe eines Objekts vom Typ Person
    mittels System.out.println(person) folgende Beschreibung dieser Person
    liefert:

    # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <Sozialversicherungsnummer>

    Hat eine Person Kinder, so werden auch rekursiv alle Kinder dieser
    Person (sowie auch alle darunterliegenden Hierarchieebenen) bei der
    Ausgabe berücksichtigt. Jede Ebene wird mit einem Leerzeichen
    eingerückt, z.B:

    # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <Sozialversicherungsnummer>
     # <Vorname Kind1> <Nachname Kind1>, <Geschlecht Kind1>, <Alter Kind1> Jahre, Svnr: <Sozialversicherungsnummer Kind1>
     # <Vorname Kind2> <Nachname Kind2>, <Geschlecht Kind2>, <Alter Kind2> Jahre, Svnr: <Sozialversicherungsnummer Kind2>
      # <Vorname Kind1 von Kind2> <Nachname Kind1 von Kind2>, <Geschlecht Kind1 von Kind2>, <Alter Kind1 von Kind2> Jahre, Svnr: <Sozialversicherungsnummer Kind1 von Kind2>

    Implementieren Sie createHubert() so, dass die zurückgelieferte Person
    folgende Ausgabe hat:

    # Hubert Maier, maennlich, 88 Jahre, Svnr: 1234
     # Julia Maier, weiblich, 54 Jahre, Svnr: 1111
      # Thomas Maier, maennlich, 22 Jahre, Svnr: 2222
      # Gernot Maier, maennlich, 24 Jahre, Svnr: 3333
     # Gernot Mueller, maennlich, 40 Jahre, Svnr: 1115
      # Roman Mueller, maennlich, 12 Jahre, Svnr: 1116
      # Sophie Mueller, weiblich, 14 Jahre, Svnr: 1117

    createJulia() soll innerhalb von createHubert() verwendet werden
    und createThomas() innerhalb von createJulia(). Es dürfen weitere
    Hilfsmethoden verwendet werden.

    Innerhalb der Liste der Kinder einer Person, darf es keine zwei
    gleichen Objekte vom Typ Person geben. Zwei Personen sind gleich, wenn
    ihr Vor- und Nachname, sowie ihre Sozialversicherungsnummer gleich ist.

    Achten Sie auch darauf, dass hashCode() korrekt funktioniert und alle
    Modifier korrekt sind.

    Kommentieren Sie Ihr Programm ausreichend!

    Zusatzfragen:
        1. Wie verhält sich das Programm wenn es keine eigene Implementierung
           von equals gibt?
        2. Warum sollte man zur textuellen Beschreibung einer Klasse toString
           verwenden und keine Methode mit einem anderen Namen implementieren?
        3. Warum wird hashCode benötigt und wie kann der Rückgabewert dieser
           Methode interpretiert werden?
        4. Warum eignet sich hashCode() nicht für Vergleiche von Objekten?
        5. Inwiefern können Namen und Kommentare altern?
*/
public class Aufgabe1 {
    //# Hubert Maier, maennlich, 88 Jahre, Svnr: 1234
    public static Person createHubert() {

        Person hubschi = new Person("Hubert", "Maier", true, 88, 1234);
        hubschi.neuesKind(createJulia());
        hubschi.neuesKind(createGernot());
        return hubschi;
    }
    // # Julia Maier, weiblich, 54 Jahre, Svnr: 1111
    public static Person createJulia() {
        Person julia = new Person("Julia", "Maier", false, 54, 1111);
        julia.neuesKind(createThomas());
        julia.neuesKind(new Person("Gernot", "Maier", true, 24,3333));
        return julia;
    }
    public static Person createGernot() {
        Person gernot = new Person("Gernot", "Mueller", true, 40, 1115);
        gernot.neuesKind(new Person("Roman", "Mueller", true, 12,1116));
        gernot.neuesKind(new Person("Sophie", "Mueller", false,14,1117));
        return gernot;
    }
    //  # Thomas Maier, maennlich, 22 Jahre, Svnr: 2222
    public static Person createThomas() {
        return new Person("Thomas", "Maier", true, 22,2222);
    }
    
    public static void main(String[] args) {
        System.out.print(createHubert());
    }
}



