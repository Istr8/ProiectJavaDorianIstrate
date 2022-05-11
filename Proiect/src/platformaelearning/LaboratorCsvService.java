package platformaelearning;

import platformaelearning.LaboratorService;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LaboratorCsvService implements LaboratorService {

    private final File labFile;

    public LaboratorCsvService() {
        this.labFile = new File("./src/resources/laborator.csv");
        if(!labFile.exists()) {
            try {
                labFile.createNewFile();
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
    public void save(Laborator laborator) {
        FileWriter fileWriter= null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(labFile, true);
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

            boolean labAlreadyExists = getAll().stream()
                    .anyMatch(storedLab -> storedLab.getNrPrezente() == laborator.getNrPrezente() &&
                            storedLab.getNumeCurs().equals(laborator.getNumeCurs()) &&
                            storedLab.getNumeProiect().equals(laborator.getNumeProiect())
                    );

            if(!labAlreadyExists) {
                bufferedWriter.write(formatForCsv(laborator));
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
    public Laborator getByNumeCurs(String numecurs) {
        Optional<Laborator> laboratorOptional = getAll().stream()
                .filter(laborator -> laborator.getNumeCurs().equals(numecurs))
                .findFirst();

        if(laboratorOptional.isPresent()) {
            return laboratorOptional.get();
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
    public List<Laborator> getAll() {
        try {
            FileReader fileReader = new FileReader(labFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<Laborator> labs = bufferedReader.lines()
                    .map(line -> getLabFromCsvLine(line))
                    .collect(Collectors.toList());

            return labs;
        } catch (Exception e) {

        }

        return Collections.emptyList();
    }

    @Override
    public List<Laborator> findLaboratorBetweenRooms(int start, int end) {

        return getAll().stream()
                .filter(laborator -> laborator.getSalaCurs() >= start && laborator.getSalaCurs() <= end)
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
        List<Laborator> remainingLabs = getAll().stream()
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
    public List<Laborator> findByCustomFilter(Predicate<Laborator> filter) {
        return getAll().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    private Laborator getLabFromCsvLine(String line) {
        String[] values = line.split(",");
        System.out.print(values[4]);
        Laborator laborator = new Laborator(
                values[0],
                values[1],
                Integer.parseInt(values[2]),
                Integer.parseInt(values[3]),
                values[4]);

        return laborator;
    }

    private String formatForCsv(Laborator laborator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(laborator.getNumeCurs());
        stringBuilder.append(",");
        stringBuilder.append(laborator.getCuprins());
        stringBuilder.append(",");
        stringBuilder.append(laborator.getSalaCurs());
        stringBuilder.append(",");
        stringBuilder.append(laborator.getNrPrezente());
        stringBuilder.append(",");
        stringBuilder.append(laborator.getNumeProiect());

        return stringBuilder.toString();
    }
}