package org.example;

public class AssignmentObj {

    private String id;
    private String bankName;
    private String type;
    private String city;
    private String state;
    private String zipCode;


    /**
     * Constructor with all parms
     * @param id unique id
     * @param bankName bank name
     * @param type type
     * @param city city
     * @param state state
     * @param zipCode zipCode
     */
    public AssignmentObj(String id, String bankName, String type, String city, String state, String zipCode) {
        this.id = id;
        this.bankName = bankName;
        this.type = type;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /** Constructor to be use for an object from a file
     * Just for an easy use
     * @param fileItem Array of String
     */
    public AssignmentObj(String[] fileItem) {
        this.id = fileItem[0];
        this.bankName = fileItem[1];
        this.type = fileItem[2];
        this.city = fileItem[3];
        this.state = fileItem[4];
        this.zipCode = fileItem[5];
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * To display the object values
     * @return String
     */
    @Override
    public String toString() {
        return this.id + ", " +
         bankName + ", " +
         type + ", " +
         city + ", " +
         state + ", " +
         zipCode;
    }

}
