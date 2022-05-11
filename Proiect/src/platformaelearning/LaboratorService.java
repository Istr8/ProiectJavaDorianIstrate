
package platformaelearning;

import java.util.List;
import java.util.function.Predicate;

public interface LaboratorService {

    void save(Laborator laborator);

    Laborator getByNumeCurs(String numeCurs);

    List<Laborator> getAll();

    List<Laborator> findLaboratorBetweenRooms(int start, int end);


    void delete(Laborator laborator);

    List<Laborator> findByCustomFilter(Predicate<Laborator> filter);
}