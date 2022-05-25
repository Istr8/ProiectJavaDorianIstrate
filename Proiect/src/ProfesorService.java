import java.util.List;
import java.util.function.Predicate;

public interface ProfesorService {

    void save(Profesor profesor);

    public Profesor getByNumeCurs(String numeCurs);

    List<Profesor> getAll();



    void delete(Profesor profesor);

    List<Profesor> findByCustomFilter(Predicate<Profesor> filter);
}