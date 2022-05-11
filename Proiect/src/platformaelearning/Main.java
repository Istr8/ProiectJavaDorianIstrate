package platformaelearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {

        //Etapa I
        ArrayList listaObiecte =  new ArrayList(asList(new Cursant("MarianAlexandru","marianalexandru@gmail.com", "marian123",6,22,
                        "Unibuc9992", 9),
                new Profesor("MihaiAlexandru","mihaialexandru@gmail.com", "mihai123",6,35,
                        "Diploma in Algoritmi si Structuri de Date",
                        new ArrayList<String> (asList("Baze de Date", "AutoCad","Fundamente Retele"))),
                new Curs("Baze de Date","1.Interogari 2.Subcereri 3.Tabele",22),
                new Seminar("Tehnici Programare","1.Date 2.Algoritmi 3.Clase",42,
                        new ArrayList<String> (asList("Problemele din PDF", "Rezolvati problema comis voiajorului",
                                "Faceti un magazin online"))),
                new Quiz(259, "Baze de date", "Marian Alexandru","15:00","30",
                        new ArrayList<String> (asList("1.Scrieti o subcerere pentru a obtine toate atributele din tabela elevi",
                                "2.Ordonati crescator angajatii dupa salriu")) ),
                new Laborator("PAO","1.exceptii 2.colectii 3.singletons",42,4,"Proiect Clase"),
                new LucrareLaborator(301,"Electronica Digitala","MarianAlexandru","15:00",
                        "40",new ArrayList<String>(asList("1.Realizati o dioda",
                        "2.Realizati un transformator")), "Construiti un circuit"),
                new Utilizator("Guest12345","","",0,18)
        ));
        Service aplicatie = new Service(listaObiecte);

        //Etapa 2

        LaboratorCsvService labService = new LaboratorCsvService();

        List<Laborator> labs = labService.getAll();

    }
}