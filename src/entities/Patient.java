package entities;

import utils.HelperUtils;

public class Patient extends Person {

    private String bloodGroup;
    private String emergencyContact;
    private String registrationDate;

    private String[] allergies;
    private String[] recordIds;

    private int allergyCount;
    private int recordCount;

    private double outstandingBalance;
    private boolean insured;


    // Constructor

    public Patient(
            String id,
            String firstName,
            String lastName,
            String dateOfBirth,
            String gender,
            String phoneNumber,
            String email,
            String address,
            String nationalId,
            int age,
            boolean active,
            String bloodGroup,
            String emergencyContact,
            String registrationDate,
            double outstandingBalance,
            boolean insured) {

        super(
                id,
                firstName,
                lastName,
                dateOfBirth,
                gender,
                phoneNumber,
                email,
                address,
                nationalId,
                age,
                active
        );

        setBloodGroup(bloodGroup);
        setEmergencyContact(emergencyContact);
        setRegistrationDate(registrationDate);
        setOutstandingBalance(outstandingBalance);
        setInsured(insured);

        allergies = new String[20];
        recordIds = new String[50];

        allergyCount = 0;
        recordCount = 0;
    }


    // Setters

    public void setBloodGroup(String bloodGroup) {

        if (!HelperUtils.isValidText(bloodGroup)) {
            IO.println("Blood group cannot be empty.");
            return;
        }

        this.bloodGroup = bloodGroup;
    }


    public void setEmergencyContact(String emergencyContact) {

        if (!HelperUtils.isValidText(emergencyContact)) {
            IO.println("Emergency contact cannot be empty.");
            return;
        }

        this.emergencyContact = emergencyContact;
    }


    public void setRegistrationDate(String registrationDate) {

        if (!HelperUtils.isValidText(registrationDate)) {
            IO.println("Registration date cannot be empty.");
            return;
        }

        this.registrationDate = registrationDate;
    }


    public void setOutstandingBalance(double outstandingBalance) {

        if (outstandingBalance < 0) {
            IO.println(
                    "Outstanding balance cannot be negative."
            );
            return;
        }

        this.outstandingBalance = outstandingBalance;
    }


    public void setInsured(boolean insured) {
        this.insured = insured;
    }


    // Getters

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public boolean isInsured() {
        return insured;
    }

    public int getAllergyCount() {
        return allergyCount;
    }

    public int getRecordCount() {
        return recordCount;
    }


    // Allergy Methods

    public void addAllergy(String allergy) {

        if (!HelperUtils.isValidText(allergy)) {
            IO.println("Allergy cannot be empty.");
            return;
        }

        if (hasAllergy(allergy)) {
            IO.println("Allergy already exists.");
            return;
        }

        if (allergyCount >= allergies.length) {
            IO.println("Allergy list is full.");
            return;
        }

        allergies[allergyCount] = allergy;
        allergyCount++;
    }


    public boolean hasAllergy(String allergy) {

        if (!HelperUtils.isValidText(allergy)) {
            return false;
        }

        for (int i = 0; i < allergyCount; i++) {

            if (allergies[i].equalsIgnoreCase(allergy)) {
                return true;
            }
        }

        return false;
    }


    public void listAllergies() {

        if (allergyCount == 0) {
            IO.println("No allergies.");
            return;
        }

        for (int i = 0; i < allergyCount; i++) {
            IO.println(allergies[i]);
        }
    }


    // Medical Records

    public void addRecordId(String recordId) {

        if (!HelperUtils.isValidText(recordId)) {
            IO.println("Record ID cannot be empty.");
            return;
        }

        if (recordCount >= recordIds.length) {
            IO.println("Record list is full.");
            return;
        }

        recordIds[recordCount] = recordId;
        recordCount++;
    }


    // Balance

    public void addToBalance(double amount) {

        if (!HelperUtils.isPositive(amount)) {
            IO.println(
                    "Amount must be greater than zero."
            );
            return;
        }

        outstandingBalance += amount;
    }


    public void clearBalance() {
        outstandingBalance = 0;
    }


    // Overloading

    public void updateContact(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }


    public void updateContact(
            String phoneNumber,
            String email) {

        setPhoneNumber(phoneNumber);
        setEmail(email);
    }


    // Overriding

    @Override
    public void displayInfo() {

        IO.println(
                "Patient: " + getFullName() +
                        ", ID: " + getId() +
                        ", Blood Group: " + getBloodGroup() +
                        ", Emergency Contact: " + getEmergencyContact() +
                        ", Registration Date: " + getRegistrationDate() +
                        ", Outstanding Balance: " + getOutstandingBalance() +
                        ", Insured: " + isInsured()
        );
    }
}