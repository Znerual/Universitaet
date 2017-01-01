public class Saeugetier implements Meerestier {
    int m_regal, m_gewicht;
    String m_art;
    public Saeugetier(String art, int regal, int gewicht) {
        m_art = art;
        m_gewicht = gewicht;
        m_regal = regal;
    }
    @Override
    public void einordnen(int regal) {
        m_regal = regal + 100;
    }

    @Override
    public void ausraeumen() {
        return;
    }

    @Override
    public void abwiegen(int gewicht) {
        m_gewicht = gewicht * 1000;
    }
    @Override
    public String toString() {
        return String.format("%s, SpezialRegal %d, Gewicht %dkg", m_art, m_regal, m_gewicht);
    }
}
