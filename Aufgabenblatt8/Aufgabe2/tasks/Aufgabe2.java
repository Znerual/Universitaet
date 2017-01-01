import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/*
    Aufgabe 2) Eingabe und Ausgabe mit Dateien

    Es soll ein Textfile "Geschichte.txt" geöffnet und eingelesen werden.
    Durchsuchen Sie das File nach sogenannten Palindromen (z.b. Hannah, Otto,
    etc.), also Wörtern die von vorn und von hinten gelesen dasselbe ergeben.
    Implementieren Sie dazu eine statische Methode "searchPalindrome(...)", die
    als Argumente die Filenamen für die Ein- und Ausgabe übergeben bekommt.
    Schreiben Sie alle gefundenen Palindrome in die Datei "Palindrome.txt".
    Zusätzlich geben Sie alle Palindrome mit System.out.println(...) aus (in der
    Reihenfolge in der diese im File auftreten).

    Erstellen Sie zusätzlich (unabhängig von Palindromen) eine Statistik über
    alle vorkommenden Buchstaben (auch Umlaute bzw. ein scharfes ß sollen, falls
    vorhanden, aufgelistet werden).
    Groß- und Kleinbuchstaben werden nicht unterschieden. Implementieren Sie
    dazu eine statische Methode "generateStatistic(...)" und geben Sie die
    Ergebnisse in folgender Form aus:
    Buchstabe - Gesamt (Wie oft kommt Buchstabe vor)
    z.B.:   a - 17
            b - 3
            c - 2
            ...

    Zusatzfragen:
    1. Was ist der Unterschied zwischen ungepufferten und gepufferten
       Datenströmen?
    2. Was ist der Unterschied zwischen FileInputStream und FileReader?
*/
public class Aufgabe2 {
    
    public static void searchPalindrome(String inFileName, String outFileName){
        try {
            FileReader fr = new FileReader("Aufgabe2/tasks/" + inFileName);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("Aufgabe2/tasks/" + outFileName);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = br.readLine();
            while (line != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (isPalindrome(word)){
                        bw.write(word + "\n");
                        System.out.println(word);
                    }
                }
                line = br.readLine();
            }
            bw.flush();
            bw.close();
            fw.close();
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: Implementieren Sie hier die Angabe.
    }
    private static boolean isPalindrome(String str) {
        int to = str.length() / 2;
        for (int  i = 0; i < to; i++) {
            if (!(str.toLowerCase().charAt(i) == str.toLowerCase().charAt(str.length() - (i +1)))) return false;
        }
        return true;
    }
    public static void generateStatistic(String inFileName){
        TreeMap<Character, Integer> map = new TreeMap<>();
        try {
            FileReader fr = new FileReader("Aufgabe2/tasks/" + inFileName);
            int input;
            while ((input = fr.read()) != -1) {
                Character c = (char) input;
                if (Character.isLetter(c)) {
                    c = Character.toLowerCase(c);
                    if (map.containsKey(c)) {
                        map.put(c, map.get(c) + 1);
                    } else  {
                        map.put(c, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Character> iterator =  map.navigableKeySet().iterator();
        while (iterator.hasNext()) {
            Character ca = iterator.next();
            System.out.println(ca + " - " + map.get(ca));
        }
    }
    
    public static void main(String[] args) {
    }
}

