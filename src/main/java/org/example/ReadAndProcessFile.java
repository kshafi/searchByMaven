package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ReadAndProcessFile {
    private final HashMap<String, List<AssignmentObj>> fileItemHash;

    public ReadAndProcessFile() {
        fileItemHash = new HashMap<>();
    }

    public static void main(String[] args) throws IOException, CsvException {
        ReadAndProcessFile driver = new ReadAndProcessFile();
        driver.readFile();

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String line;

        System.out.println("Enter value(s) to search. Max 2 values. Type 'Q' to end your entries.");
        while (!(line = scanner.nextLine()).equals("Q")) {
            list.add(line);
        }

        if (list.isEmpty()) {
            System.out.println("Enter a value to search an item.");
        } else if (list.size() == 1) {
            System.out.print(
            driver.findBySingleKey(list.get(0))
                    );
        } else {
            System.out.print(
            driver.findByTwoKeys(list.get(0), list.get(1))
            );
        }
    }

    public void readFile() throws IOException, CsvException {

        InputStream inputStream = getClass().getResourceAsStream("/ProgrammingAssignment.csv");

        if (!Objects.isNull(inputStream)) {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                AssignmentObj anObj = getObjectForARow(lineInArray);
                loadItemWithKeys(anObj);
            }
        }
    }

    private AssignmentObj getObjectForARow(String[] fileItem) {
        return new AssignmentObj(fileItem[0], fileItem[1],  fileItem[2], fileItem[3], fileItem[4], fileItem[5]);
    }

    private void loadItemWithKeys(AssignmentObj anObj) {
        loadIntoHash(anObj, anObj.getBankName());
        loadIntoHash(anObj, anObj.getType());
        loadIntoHash(anObj, anObj.getCity());
        loadIntoHash(anObj, anObj.getState());
        loadIntoHash(anObj, anObj.getZipCode());
        loadIntoHash(anObj, concatKeys(anObj.getCity(), anObj.getState()));
    }

    private void loadIntoHash(AssignmentObj anObj, String key) {
        List<AssignmentObj> hashItem = fileItemHash.get(key);
        if (Objects.isNull(hashItem)) {
            hashItem = new ArrayList<>();
            hashItem.add(anObj);
            fileItemHash.put(key, hashItem);
        } else {
            hashItem.add(anObj);
        }
    }

    private String concatKeys(String key1, String key2) {
        return key1 + "|" + key2;
    }
    
    public List<AssignmentObj> findBySingleKey(String key) {
        List<AssignmentObj> hashItem = fileItemHash.get(key);
        if (Objects.isNull(hashItem)) {
            System.out.println("Unable to find any entries with this key.");
            return null;
        } else {
            hashItem.forEach(System.out::println);
            return hashItem;
        }
    }

    public List<AssignmentObj> findByTwoKeys(String key1, String key2) {
        return findBySingleKey(concatKeys(key1, key2));
    }
}
