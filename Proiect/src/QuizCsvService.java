import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuizCsvService implements QuizService {

    private final File quizFile;

    private static QuizCsvService single_instance = null;


    public static QuizCsvService getInstance()
    {
        if (single_instance == null)
            single_instance = new QuizCsvService();

        return single_instance;
    }

    public QuizCsvService(){
        this.quizFile = new File("./src/resources/quiz.csv");
        if(!quizFile.exists()){
            try{
                quizFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(Quiz quiz){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(quizFile,true);
            bufferedWriter = new BufferedWriter(fileWriter);

            boolean quizAlreadyExists = getAll().stream()
                    .anyMatch(storedQuiz -> storedQuiz.getId() == quiz.getId());

            if(!quizAlreadyExists){
                bufferedWriter.write(formatForCsv(quiz));
                bufferedWriter.write("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
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
    public Quiz getByNumeCurs(String numeCurs){
        Optional<Quiz> quizOptional = getAll().stream()
                .filter(quiz -> quiz.getCursCorespondent().equals(numeCurs))
                .findFirst();
        if(quizOptional.isPresent()){
            return quizOptional.get();
        }

        return null;

    }

    @Override
    public List<Quiz> getAll(){
        try{
            FileReader fileReader = new FileReader(quizFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<Quiz> quizzes = bufferedReader.lines().map(line -> getQuizFromCsvLine(line))
                                .collect(Collectors.toList());

            return quizzes;
        } catch (Exception e){

        }

        return Collections.emptyList();
    }

    @Override
    public List<Quiz> sortQuizesByOraStart(String ora) {
            List<Quiz> quizGreaterThanOra = getAll().stream().filter(quizzz -> quizzz.getOraStart().compareTo(ora) > 0).toList();
        Collections.sort(quizGreaterThanOra,(a,b) -> a.getOraStart().compareTo(b.getOraStart()));
        return quizGreaterThanOra;
    }

    @Override
    public void delete(Quiz quiz){
        List <Quiz> remainingQuizes = getAll().stream()
                .filter(storedQuiz -> storedQuiz.getId() != quiz.getId()).collect(Collectors.toList());

        try(FileWriter fileWriter = new FileWriter(quizFile,false)){

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Quiz quizz : remainingQuizes){
                bufferedWriter.write(formatForCsv(quizz));
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
        } catch (IOException e){

        }

    }

    @Override
    public List<Quiz> findByCustomFilter(Predicate<Quiz> filter) {
        return getAll().stream().filter(filter).collect(Collectors.toList());
    }

    private Quiz getQuizFromCsvLine(String line){

        String[] values = line.split(",");
        ArrayList<String> lista = new ArrayList<String>();
        lista = (ArrayList<String>) Arrays.asList(values[5].split("\\\\s+"));
        Quiz quiz = new Quiz(
            Integer.parseInt(values[0]),
                values[1],
                values[2],
                values[3],
                values[4],
                lista
        );

        return quiz;
    }

    private String formatForCsv(Quiz quiz){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(quiz.id);
        stringBuilder.append(",");
        stringBuilder.append(quiz.cursCorespondent);
        stringBuilder.append(",");
        stringBuilder.append(quiz.oraStart);
        stringBuilder.append(",");
        stringBuilder.append(quiz.durata);
        stringBuilder.append(",");
        stringBuilder.append(quiz.listaIntrebari);

        return stringBuilder.toString();
    }



}
