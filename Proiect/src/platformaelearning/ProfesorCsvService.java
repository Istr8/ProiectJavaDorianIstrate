package platformaelearning;

import platformaelearning.LaboratorService;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//nu e inca implementat

public class ProfesorCsvService implements ProfesorService {

    private final File profFile;

    private static ProfesorCsvService single_instance = null;

    public static ProfesorCsvService getInstance()
    {
        if (single_instance == null)
            single_instance = new ProfesorCsvService();

        return single_instance;
    }

    public ProfesorCsvService() {
        this.profFile = new File("./src/resources/profesor.csv");
        if(!profFile.exists()) {
            try {
                profFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * exceptii:
     *  - checked: extind Exception; necesita try-catch-finally sau throws
     *  - uncheked: extind RuntimeException; nu necesita handling
     *
     *  best practice: wrap checked exceptions inside an unchecked exceptions
     *
     *  try {
     *      ...
     *  } catch(IOException e) {
     *      throw new RuntimeException(e);
     *  }
     */
    @Override
    public void save(Profesor profesor) {
        FileWriter fileWriter= null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(profFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //TODO: check if diploma already exists
            //it already exists if absolvent + an + serie match
//            List<Diploma> allDiplomas = getAll().stream()
//                    .filter(storedDiploma -> storedDiploma.getAn() == diploma.getAn() &&
//                                            storedDiploma.getSerie().equals(diploma.getSerie()) &&
//                                            storedDiploma.getAbsolvent().equals(diploma.getAbsolvent())
//                            )
//                    .collect(Collectors.toList());
//
//            if(allDiplomas.size() == 0) {
//                bufferedWriter.write(formatForCsv(diploma));
//                bufferedWriter.write("\n");
//            }

            boolean profAlreadyExists = getAll().stream()
                    .anyMatch(storedProf -> storedProf.get() == profesor.() &&
                            storedProf .getNumeCurs().equals(profesor.()) &&
                            storedProf.getNumeProiect().equals(profesor.())
                    );

            if(!profAlreadyExists) {
                bufferedWriter.write(formatForCsv(profesor));
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
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
    public Profesor getByNumeCurs(String nume) {
        Optional<Profesor> profesorOptional = getAll().stream()
                .filter(profesor -> profesor.get().equals(numecurs))
                .findFirst();

        if(profesorOptional.isPresent()) {
            return profesorOptional.get();
        }

        return null;

//        try {
//            FileReader fileReader = new FileReader(diplomeFile);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//
//            //lines -> Diploma -> diploma indeplineste conditia?(absolvent) -> getFirst
//            Optional<Diploma> diplomaOptional = bufferedReader.lines()
//                    .map(line -> getDiplomaFromCsvLine(line))
//                    .filter(diploma -> diploma.getAbsolvent().equals(absolvent))
//                    .findFirst();
//
//            if(diplomaOptional.isPresent()) {
//                return diplomaOptional.get();
//            }
//        } catch (Exception e) {
//
//        }
//
//        return null;
    }

    @Override
    public List<Profesor> getAll() {
        try {
            FileReader fileReader = new FileReader(profFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<Laborator> profi = bufferedReader.lines()
                    .map(line -> getProfFromCsvLine(line))
                    .collect(Collectors.toList());

            return labs;
        } catch (Exception e) {

        }

        return Collections.emptyList();
    }

    @Override
    public List<Profesor> findBetweenMaterii(String materie1, String materie2) {

        return getAll().stream()
                .filter(profesor -> profesor.getListaMaterii())
                .collect(Collectors.toList());

//        try {
//            FileReader fileReader = new FileReader(diplomeFile);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            List<Diploma> diplome = bufferedReader.lines()
//                    .map(line -> getDiplomaFromCsvLine(line))
//                    .filter(diploma -> diploma.getAn() >= start && diploma.getAn() <= end)
//                    .collect(Collectors.toList());
//
//            return diplome;
//        } catch (Exception e) {
//
//        }

//        return Collections.emptyList();
    }





    /*
    1. parcurgem fiecare linie din fisier
    2. mapam la obiect de tip diploma
    3. verificam daca este obiectul nostru
    4. daca este, atunci il stergem(filtrare)
    5. outputul este o lista de linii care vor ramane
    6. scriem outputul inapoi in fisier
     */
    @Override
    public void delete(Laborator labToDelete) {
        List<Profesor> remainingProfs = getAll().stream()
                .filter(storedLab -> storedLab.getNrPrezente() != labToDelete.getNrPrezente() ||
                        !storedLab.getNumeProiect().equals(labToDelete.getNumeProiect()) ||
                        !storedLab.getNumeCurs().equals(labToDelete.getNumeCurs()))
                .collect(Collectors.toList());


        try(FileWriter fileWriter = new FileWriter(labFile, false)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Laborator laborator : remainingLabs) {
                bufferedWriter.write(formatForCsv(laborator));
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {

        }
    }

    @Override
    public List<Profesor> findByCustomFilter(Predicate<Profesor> filter) {
        return getAll().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    private Profesor getProfFromCsvLine(String line) {
        String[] values = line.split(",");
        System.out.print(values[4]);
        Laborator laborator = new Profesor(
                values[0],
                values[1],
                values[2],
                Integer.parseInt(values[3]),
                Integer.parseInt(values[4]),
                values[5],
                Integer.parseInt(values[6]));
        return laborator;
    }

    private String formatForCsv(Profesor profesor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(profesor.numeUtilizator);
        stringBuilder.append(",");
        stringBuilder.append(profesor.email);
        stringBuilder.append(",");
        stringBuilder.append(profesor.parola);
        stringBuilder.append(",");
        stringBuilder.append(profesor.numarCursuri);
        stringBuilder.append(",");
        stringBuilder.append(profesor.varsta);
        stringBuilder.append(",");
        stringBuilder.append(profesor.getCertificare());
        stringBuilder.append(",");
        stringBuilder.append(profesor.getListaMaterii());

        return stringBuilder.toString();
    }
}