public class Utilizator {

    protected String numeUtilizator;
    protected String email;
    protected String parola;
    protected int numarCursuri;
    protected int varsta;

    public Utilizator(String numeUtilizator, String email, String parola, int numarCursuri, int varsta) {
        this.numeUtilizator = numeUtilizator;
        this.email = email;
        this.parola = parola;
        this.numarCursuri = numarCursuri;
        this.varsta = varsta;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getNumarCursuri() {
        return numarCursuri;
    }

    public void setNumarCursuri(int numarCursuri) {
        this.numarCursuri = numarCursuri;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}
