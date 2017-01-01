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


