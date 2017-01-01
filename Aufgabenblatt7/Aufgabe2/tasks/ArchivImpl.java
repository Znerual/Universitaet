import java.util.LinkedList;

public class ArchivImpl implements Archiv {
    private LinkedList<Meerestier> m_bestand;
    public ArchivImpl() {
        m_bestand = new LinkedList<>();
    }
    @Override
    public void registrieren(Meerestier m) {
        m_bestand.add(m);
    }

    @Override
    public void ausraeumen() {
        for (Meerestier meerestier : m_bestand) {
            meerestier.ausraeumen();
        }
    }

    @Override
    public void einordnen() {
        for (int i = 0; i < m_bestand.size(); i++) {
            m_bestand.get(i).einordnen(i+1);
        }
    }

    @Override
    public void neuWiegen(int[] gewicht) {
        for (int i = 0; i < m_bestand.size(); i++) {
            m_bestand.get(i).abwiegen(gewicht[i]);
        }
    }
    @Override
    public String toString() {
        if (m_bestand.size() == 0) return "Leeres Archiv";
        String result = "";
        for (Meerestier tier : m_bestand) {
            result += tier + "\n";
        }
       // return result.substring(0, result.length() - 1);
        return result;
    }
}

