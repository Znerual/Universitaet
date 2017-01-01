/*******************************************************************************

 AUFGABENBLATT 6 - Allgemeine Informationen

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
 bis spätestens Montag 12.12.2016 08:00 Uhr in TUWEL hoch. Zusätzlich
 müssen Sie in TUWEL ankreuzen welche Aufgaben Sie gelöst haben und während
 der Übung präsentieren können.

 ******************************************************************************/
/*
    Aufgabe 1) Sortieren & Suchen

    Implementieren Sie in dieser Aufgabe in der gegebenen Klasse Aufgabe1
    folgende statische Methoden:

    - sort:       Diese Methode soll den Sortieralgorihtmus "QuickSort"
                  implementieren. Sie müssen den Sortieralgorithmus selbst
                  ausimplementieren und dürfen keinen entsprechenden Aufruf aus
                  der Java-API verwenden.

    - binSearch:  Dieser Methode wird ein sortiertes Array übergeben.
                  Zusätzlich erhält die Methode einen Wert nach dem gesucht
                  werden soll. Es soll eine binäre Suche implementiert werden,
                  die true zurückliefert falls das Element enthalten ist,
                  ansonsten false.
                  
    Hinweis: Sie dürfen zusätzliche Hilfsmethoden implementieren und verwenden!

    Zusatzfragen:
    1. Welche API-Aufrufe bietet Java für das Sortieren von Arrays an?
    2. Welcher Sortieralgorithmus wird in der Java (1.8) für das Sortieren von
       Arrays verwendet?
    3. Warum ist die Wahl des Pivot-Elements entscheidend für die Performance
       des Quicksort Algorithmus?
    4. Warum muss das Array für die binäre Suche sortiert sein?
    5. Wie geht man vor wenn man in einem absteigend sortierten Array die
       Binärsuche anwenden will?
*/
public class Aufgabe1 {
    
    public static void sort(int[] array) {
        recSort(array,0,array.length-1);
    }
    private static void recSort(int[] array, int min, int max) {
        if (min < max) {
            int grenze = teile(array, min, max);
            recSort(array, min, grenze -1);
            recSort(array, grenze +1, max);
        }
    }
    private static int teile(int[] array, int min, int max) {
        int i = min, j = max -1;
        int pivot = array[max];
        do {
            while (array[i] <= pivot && i < max) {
                i++;
            }
            while (array[j] >= pivot && j > min) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
            }
        } while (i < j);
        if (array[i] > pivot) {
            swap(array, i, max);
        }
        return i;
    }
    private static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
    
    
    public static boolean binSearch(int[] array, int elem) {
        return recSearch(array, elem, 0, array.length);
        
    }
    private static boolean recSearch(int[] array, int element, int from, int to) {
        if (from == to) return false;
        int mid = getMedium(from, to);
        if (array[mid]==element) {
            return true;
        }
        if (element < array[mid]) {
            return recSearch(array, element, from, mid);
        } else {
            return recSearch(array, element, mid, to);
        }
    }
    private static int getMedium(int from, int to) {
        return (from + to) / 2;
    }
    
    public static void main(String[] args) {
    }
}



