import java.util.List;
import java.util.function.Predicate;

public interface QuizService {

    void save(Quiz quiz);

    Quiz getByNumeCurs(String numeCurs);

    List<Quiz> getAll();

    List<Quiz> sortQuizesByOraStart(String ora);


    void delete(Quiz quiz);

    List<Quiz> findByCustomFilter(Predicate<Quiz> filter);
}