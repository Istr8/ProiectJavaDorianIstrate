import java.lang.reflect.Array;
import java.util.ArrayList;

public class Seminar extends Curs {

    private ArrayList<String> temeSeminar = new ArrayList<String>();

    public Seminar(String numeCurs, String cuprins, int salaCurs, ArrayList<String> temeSeminar) {
        super(numeCurs, cuprins, salaCurs);
        this.temeSeminar = temeSeminar;
    }
}
