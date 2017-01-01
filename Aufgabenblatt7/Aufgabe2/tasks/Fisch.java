public class Fisch implements Meerestier {
    int m_regal, m_gewicht;
    String m_art;
    public Fisch(String art, int regal, int gewicht) {
        m_art = art;
        m_gewicht = gewicht;
        m_regal = regal;
    }
    @Override
    public void einordnen(int regal) {
        m_regal = regal + 200;
    }

    @Override
    public void ausraeumen() {
        m_regal = 0;
    }

    @Override
    public void abwiegen(int gewicht) {
        m_gewicht = gewicht;
    }

    @Override
    public String toString() {
        return String.format("%s, Regal %d, Gewicht %dkg", m_art, m_regal, m_gewicht);
    }
}
