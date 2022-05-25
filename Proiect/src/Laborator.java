public class Laborator extends Curs{

    private int nrPrezente;
    private String numeProiect;


    public Laborator(String numeCurs, String cuprins, int salaCurs, int nrPrezente, String numeProiect) {
        super(numeCurs, cuprins, salaCurs);
        this.nrPrezente = nrPrezente;
        this.numeProiect = numeProiect;
    }

    public int getNrPrezente() {
        return nrPrezente;
    }

    public void setNrPrezente(int nrPrezente) {
        this.nrPrezente = nrPrezente;
    }

    public String getNumeProiect() {
        return numeProiect;
    }

    public void setNumeProiect(String numeProiect) {
        this.numeProiect = numeProiect;
    }
}
