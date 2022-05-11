
package platformaelearning;

import java.util.List;
import java.util.function.Predicate;

public interface ProfesorService {

    void save(Profesor profesor);

    Laborator getByNumeUtilizator(String numeUtilizator);

    List<Profesor> getAll();

    List<Profesor> findBetweenMaterii(String materie1, String materie2);


    void delete(Profesor profesor);

    List<Profesor> findByCustomFilter(Predicate<Profesor> filter);
}