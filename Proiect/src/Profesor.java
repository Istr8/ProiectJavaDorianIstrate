import java.util.ArrayList;


public class Profesor extends Utilizator {

    private String certificare;
    private ArrayList<String> listaMaterii = new ArrayList<String>();

    public Profesor(String numeUtilizator, String email, String parola, int numarCursuri, int varsta, String certificare, ArrayList<String> listaMaterii) {
        super(numeUtilizator, email, parola, numarCursuri, varsta);
        this.certificare = certificare;
        this.listaMaterii = listaMaterii;
    }

    public String getCertificare() {
        return certificare;
    }

    public void setCertificare(String certificare) {
        this.certificare = certificare;
    }

    public ArrayList<String> getListaMaterii() {
        return listaMaterii;
    }

    public void setListaMaterii(ArrayList<String> listaMaterii) {
        this.listaMaterii = listaMaterii;
    }
}
