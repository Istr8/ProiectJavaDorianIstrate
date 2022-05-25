import java.util.List;
import java.util.function.Predicate;

public interface CursantService {

    void save(Cursant cursant);

    public Cursant getByNumeUtilizator(String numeUtilizator);

    List<Cursant> getAll();

    void delete(Cursant cursant);

    List<Cursant> findByCustomFilter(Predicate<Cursant> filter);

}
