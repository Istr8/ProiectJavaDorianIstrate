package platformaelearning;

public class Curs {

    protected String numeCurs;
    protected String cuprins;
    protected int salaCurs;

    public Curs(String numeCurs, String cuprins, int salaCurs) {
        this.numeCurs = numeCurs;
        this.cuprins = cuprins;
        this.salaCurs = salaCurs;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    public String getCuprins() {
        return cuprins;
    }

    public void setCuprins(String cuprins) {
        this.cuprins = cuprins;
    }

    public int getSalaCurs() {
        return salaCurs;
    }

    public void setSalaCurs(int salaCurs) {
        this.salaCurs = salaCurs;
    }
}
