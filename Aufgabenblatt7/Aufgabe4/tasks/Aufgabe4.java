/*
    Aufgabe 4) Bäume

    In der Klasse IntTree haben Sie eine Baumimplementierung gegeben, die einen
    sortierten Binärbaum abbildet. Jede Veränderung des Baumes durch eine
    Methode muss gewährleisten, dass dieser sortiert bleibt. Dazu sollen Sie
    folgende zusätzliche Methoden implementieren:

    - add:              Fügt einen Knoten in den Baum ein. Werden folgende
    (bereits gegeben)   Elemente {12,4,6,15,1,13,5,14} nacheinander in den Baum
                        eingefügt, wird folgender Baum aufgebaut:
                                                 12
                                              /      \
                                            4         15
                                          /   \      /
                                         1     6   13
                                              /      \
                                             5        14
    - countNodes:       Liefert die Anzahl aller Knoten im Baum zurück. Wird
                        ohne Parameter aufgerufen.
    - countLeaves:      Liefert die Anzahl der Blattknoten zurück. Wird ohne
                        Parameter aufgerufen.
    - height:           Liefert die Höhe des Baumes zurück. Der leere Baum hat
                        die Höhe 0. Hat der Baum nur einen Knoten (Wurzel), dann
                        hat er die Höhe 1. Mit jeder zusätzlichen Stufe von
                        Nachfolgern erhöht sich die Höhe um 1. Der oben gezeigte
                        Baum hat die Höhe 4.
    - printLeaves:      Diese Methode gibt die Elemente der Blätterknoten aus,
                        wobei das linke Blatt immer vor dem rechten Blatt
                        ausgegeben wird. Verwenden Sie an entsprechender Stelle
                        für die Ausgabe -> System.out.println(this.elem);
    - printInOrderUp:   Diese Methode gibt alle Elemente in aufsteigender
                        Reihenfolge aus.
                        Verwenden Sie an entsprechender Stelle für die
                        Ausgabe -> System.out.println(this.elem);
    - printInOrderUpSub:Diese Methode gibt alle Elemente eines Teilbaums in
                        aufsteigender Reihenfolge aus. Dazu wird der Methode ein
                        Element übergeben, welches dem Wurzelknoten des
                        Teilbaums entspricht. Nun wird der komplette Teilbaum
                        inklusive Wurzel ausgegben. Verwenden Sie an
                        entsprechender Stelle für die
                        Ausgabe -> System.out.println(this.elem);
    - printPostOrder:   Diese Methode gibt alle Elemente in der sogenannten
                        Post-Order aus. Die Post-Order für den oben abgebildeten
                        Baum ergibt folgende Reihenfolge (1,5,6,4,14,13,15,12).
                        Verwenden Sie an entsprechender Stelle für die
                        Ausgabe -> System.out.println(this.elem);
    - printPreOrder:    Diese Methode gibt alle Elemente in der sogenannten
                        Pre-Order aus. Die Pre-Order für den oben abgebildeten
                        Baum ergibt folgende Reihenfolge (12,4,1,6,5,15,13,14).
                        Verwenden Sie an entsprechender Stelle für die
                        Ausgabe -> System.out.println(this.elem);

    Zusatzfragen:
    1. Wie könnte man vorgehen, wenn man einen Knoten in den Baum einfügen
       möchte. Reicht es, den Knoten einzuhängen, oder müssen zusätzliche
       Operationen durchgeführt werden?

*/
public class Aufgabe4 {
    
    public static void main(String[] args) {
    }
}


