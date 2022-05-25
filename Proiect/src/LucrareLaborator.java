import com.sun.nio.sctp.SendFailedNotification;

import java.util.ArrayList;

public class LucrareLaborator extends Quiz {

    private String probaPractica;

    public LucrareLaborator(int id, String cursCorespondent, String utiliZator, String oraStart, String durata, ArrayList<String> listaIntrebari, String probaPractica) {
        super(id, cursCorespondent, utiliZator, oraStart, durata, listaIntrebari);
        this.probaPractica = probaPractica;
    }

    public String getProbaPractica() {
        return probaPractica;
    }

    public void setProbaPractica(String probaPractica) {
        this.probaPractica = probaPractica;
    }
}
