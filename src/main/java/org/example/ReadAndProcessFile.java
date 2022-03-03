package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ReadAndProcessFile {
    private final HashMap<String, List<AssignmentObj>> fileItemHash;
    private boolean hasHeader = true;

    public ReadAndProcessFile() {
        fileItemHash = new HashMap<>();
    }

    /**
     * Main driver class to run this program.
     * No Exception handling has implemented yet.
     * @param args default String array
     * @throws IOException ioexception
     * @throws CsvException csvexception
     */
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

    /**
     * Main logic to read the file
     * @throws IOException  IOException
     * @throws CsvException CsvException
     */
    public void readFile() throws IOException, CsvException {
        String fileName = "ProgrammingAssignment.csv";
        InputStream inputStream = getClass().getResourceAsStream("/" + fileName);

        if (!Objects.isNull(inputStream)) {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            if (hasHeader) {
                reader.skip(1);
            }

            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                AssignmentObj anObj = new AssignmentObj(lineInArray);
                loadItemWithKeys(anObj);
            }
        }
    }

    /**
     * Load file content into map with different keys
     * @param anObj AssignmentObj
     */
    private void loadItemWithKeys(AssignmentObj anObj) {
        loadIntoHash(anObj, anObj.getBankName());
        loadIntoHash(anObj, anObj.getType());
        loadIntoHash(anObj, anObj.getCity());
        loadIntoHash(anObj, anObj.getState());
        loadIntoHash(anObj, anObj.getZipCode());
        loadIntoHash(anObj, concatKeys(anObj.getCity(), anObj.getState()));
    }

    /**
     * If key does not exist then add a new entry
     * Otherwise,  add to an existing key
     * @param anObj AssignmentObj
     * @param key String
     */
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

    /**
     * Combine two string to create a key
     * @param key1 String
     * @param key2 String
     * @return String
     */
    private String concatKeys(String key1, String key2) {
        return key1 + "|" + key2;
    }

    /**
     * Find using a single key
     * @param key String
     * @return List<AssignmentObj>
     */
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

    /**
     * Find using multiple keyps
     * @param key1 String
     * @param key2 String
     * @return List<AssignmentObj>
     */
    public List<AssignmentObj> findByTwoKeys(String key1, String key2) {
        return findBySingleKey(concatKeys(key1, key2));
    }

    /**
     * Setter method to set veriable value as needed
     * @param hasHeader boolean
     */
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
}
