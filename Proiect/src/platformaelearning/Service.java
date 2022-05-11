package platformaelearning;

import jdk.jshell.execution.Util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

public class Service {

    private ArrayList<String> listaActiuni;
    private ArrayList listaObiecte;


    public Service(ArrayList listaObiecte) {

        this.listaActiuni = new ArrayList<String> (asList("create", "read","update","delete"
                ,"list all rooms", "list courses lectures and laboratories names grouped","list all homeworks","list all Classmates",
                "sign in", "current exams"));

        this.listaObiecte = listaObiecte;
    }

    public ArrayList<String> getListaActiuni() {
        return listaActiuni;
    }

    public void setListaActiuni(ArrayList<String> listaActiuni) {
        this.listaActiuni = listaActiuni;
    }

    public ArrayList getListaObiecte() {
        return listaObiecte;
    }

    public void setListaObiecte(ArrayList listaObiecte) {
        this.listaObiecte = listaObiecte;
    }

}
