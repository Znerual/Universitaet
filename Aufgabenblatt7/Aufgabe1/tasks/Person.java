import java.util.LinkedList;

public class Person {
    private String m_vorname;
    private String m_nachname;
    private boolean m_isMale;
    private int m_age;
    private int m_versicherung;
    private LinkedList<Person> m_kinder;
    public Person(String vornamen, String nachnamen, boolean istMaennlich, int alter, int versicherungsnummer) {
        m_vorname = vornamen;
        m_nachname = nachnamen;
        m_isMale = istMaennlich;
        m_age = alter;
        m_versicherung = versicherungsnummer;
        m_kinder = new LinkedList<>();
    }

    public String getvorname() {
        return m_vorname;
    }

    public String getnachname() {
        return m_nachname;
    }

    public boolean isMale() {
        return m_isMale;
    }
    public void neuesKind(Person kind) {
        if (!m_kinder.contains(kind)) m_kinder.add(kind);
    }

    public int getage() {
        return m_age;
    }

    public int getversicherung() {
        return m_versicherung;
    }
    @Override
    public String toString() {
       // # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <Sozialversicherungsnummer>
        String personString = String.format("# %s %s, %s, %d Jahre, Svnr: %d", m_vorname, m_nachname,m_isMale ? "maennlich" : "weiblich",m_age,m_versicherung);
        if (m_kinder.size() == 0) return " " +personString;
        String kinderString = "";
        for (Person kind : m_kinder)
        {
            kinderString += "\n " + kind.toString();
        }
        return personString + kinderString;
        /*
        String result = String.format("# %s %s, %s, %d Jahre, Svnr: %d ", m_vorname, m_nachname,m_isMale ? "maennlich" : "weiblich",m_age,m_versicherung);
        if (this.m_kinder.size() > 0) {
            for (Person child: this.m_kinder) {
                result += "\n ";
                result += child.toString();
            }
        }
        return result;
        */
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result =  m_vorname.hashCode();
        result *= m_nachname.hashCode();
        result *= prime + (m_isMale ? 1 : 0);
        result *= prime + m_age;
        result *= prime + m_versicherung;
        result *= m_kinder.hashCode();
        return result;
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Person)) return false;
        if (other == this) return true;
        if (m_vorname.equals(((Person) other).m_vorname) && m_nachname.equals(((Person) other).m_nachname) && m_isMale == ((Person) other).m_isMale &&  m_versicherung == ((Person) other).m_versicherung) return true;
        return false;
    }
    //TODO Implementieren Sie hier die Klasse Person
}
