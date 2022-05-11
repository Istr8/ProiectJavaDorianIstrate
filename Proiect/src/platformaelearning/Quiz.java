package platformaelearning;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    protected int id;
    protected String cursCorespondent;
    protected String utiliZator;
    protected String oraStart;
    protected String durata;
    protected ArrayList<String> listaIntrebari = new ArrayList<String>();

    public Quiz(int id, String cursCorespondent, String utiliZator, String oraStart, String durata, ArrayList<String> listaIntrebari) {
        this.id = id;
        this.cursCorespondent = cursCorespondent;
        this.utiliZator = utiliZator;
        this.oraStart = oraStart;
        this.durata = durata;
        this.listaIntrebari = listaIntrebari;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCursCorespondent() {
        return cursCorespondent;
    }

    public void setCursCorespondent(String cursCorespondent) {
        this.cursCorespondent = cursCorespondent;
    }

    public String getUtiliZator() {
        return utiliZator;
    }

    public void setUtiliZator(String utiliZator) {
        this.utiliZator = utiliZator;
    }

    public String getOraStart() {
        return oraStart;
    }

    public void setOraStart(String oraStart) {
        this.oraStart = oraStart;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public ArrayList<String> getListaIntrebari() {
        return listaIntrebari;
    }

    public void setListaIntrebari(ArrayList<String> listaIntrebari) {
        this.listaIntrebari = listaIntrebari;
    }
}
