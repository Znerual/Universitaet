public class Koralle implements Meerestier{
    String m_art;
    int m_regal;
    public Koralle(String art, int regal) {
        m_art = art;
        m_regal = regal;
    }
    @Override
    public void einordnen(int regal) {
        m_regal = regal;
    }

    @Override
    public void ausraeumen() {
        m_regal = 0;
    }

    @Override
    public void abwiegen(int gewicht) {
        return;
    }
    @Override
    public String toString() {
        return String.format("%s, Regal %d, Gewicht 1000g", m_art, m_regal);
    }
}
