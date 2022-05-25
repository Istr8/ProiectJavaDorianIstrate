import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CursantCsvService implements CursantService{

    private final File cursantFile;

    private static CursantCsvService single_instance = null;

    public static CursantCsvService getInstance()
    {
        if (single_instance == null)
            single_instance = new CursantCsvService();

        return single_instance;
    }

    public CursantCsvService()
    {
        this.cursantFile = new File("./src/resources/cursant.csv");
        if(!cursantFile.exists()) {
            try {
                cursantFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(Cursant cursant)
    {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(cursantFile,true);
            bufferedWriter = new BufferedWriter(fileWriter);

            boolean cursantAlreadyExists = getAll().stream()
                    .anyMatch(storedCursant ->storedCursant.getLegitimatieStudent().equals(cursant.getLegitimatieStudent()));

            if(!cursantAlreadyExists){
                bufferedWriter.write(formatForCsv(cursant));
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            if (fileWriter != null) {
                try{
                    fileWriter.close();
                } catch (IOException e){
                }
            }
            if(bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public Cursant getByNumeUtilizator(String numeUtilizator){

        Optional<Cursant> cursantOptional = getAll().stream().filter(cursant -> cursant.getNumeUtilizator().equals(numeUtilizator)).findFirst();

        if(cursantOptional.isPresent())
        {
            return cursantOptional.get();
        }
        return null;
    }

    @Override
    public List <Cursant> getAll(){
        try{
            FileReader fileReader = new FileReader(cursantFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<Cursant> cursanti = bufferedReader.lines().map(line -> getCursantFromCsvLine(line))
                    .collect(Collectors.toList());

            return cursanti;
        } catch (Exception e){

        }

        return Collections.emptyList();

    }

    @Override
    public void delete(Cursant cursant){
        List<Cursant> remainingCursants = getAll().stream()
                .filter(storedCursant -> !storedCursant.getLegitimatieStudent().equals(cursant.getLegitimatieStudent())).collect(Collectors.toList());

        try(FileWriter fileWriter = new FileWriter(cursantFile,false)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Cursant cursantt : remainingCursants) {
                bufferedWriter.write(formatForCsv(cursantt));
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
        }catch(IOException e){

        }
    }

    @Override
    public List<Cursant> findByCustomFilter(Predicate<Cursant> filter) {
        return getAll().stream().filter(filter).collect(Collectors.toList());
    }

    private Cursant getCursantFromCsvLine(String line){

        String[] values = line.split(",");
        Cursant cursant = new Cursant(
                values[0],
                values[1],
                values[2],
                Integer.parseInt(values[3]),
                Integer.parseInt(values[4]),
                values[5],
                Integer.parseInt(values[6])
        );

        return cursant;
    }

    private String formatForCsv(Cursant cursant){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cursant.numeUtilizator);
        stringBuilder.append(",");
        stringBuilder.append(cursant.email);
        stringBuilder.append(",");
        stringBuilder.append(cursant.parola);
        stringBuilder.append(",");
        stringBuilder.append(cursant.numarCursuri);
        stringBuilder.append(",");
        stringBuilder.append(cursant.varsta);

        return stringBuilder.toString();
    }
}
