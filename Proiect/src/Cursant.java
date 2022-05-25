public class Cursant extends  Utilizator {
    private String legitimatieStudent;
    private int medie;

    public Cursant(String numeUtilizator, String email, String parola, int numarCursuri, int varsta, String legitimatieStudent, int medie) {
        super(numeUtilizator, email, parola, numarCursuri, varsta);
        this.legitimatieStudent = legitimatieStudent;
        this.medie = medie;
    }

    public String getLegitimatieStudent() {
        return legitimatieStudent;
    }

    public void setLegitimatieStudent(String legitimatieStudent) {
        this.legitimatieStudent = legitimatieStudent;
    }

    public int getMedie() {
        return medie;
    }

    public void setMedie(int medie) {
        this.medie = medie;
    }
}
