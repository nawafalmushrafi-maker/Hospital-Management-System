package entities;

import utils.HelperUtils;

public class Doctor extends Person {

    private String specialization;
    private int experienceYears;
    private double consultationFee;

    private String[] availableSlots;
    private String[] assignedPatientIds;

    private int slotCount;
    private int patientCount;

    private boolean onCall;


    // Constructor
    public Doctor(
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
            String specialization,
            int experienceYears,
            double consultationFee,
            boolean onCall) {

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

        setSpecialization(specialization);
        setExperienceYears(experienceYears);
        setConsultationFee(consultationFee);
        setOnCall(onCall);

        availableSlots = new String[20];
        assignedPatientIds = new String[50];

        slotCount = 0;
        patientCount = 0;
    }


    // Setters

    public void setSpecialization(String specialization) {

        if (!HelperUtils.isValidText(specialization)) {
            System.out.println(
                    "Specialization cannot be empty."
            );
            return;
        }

        this.specialization = specialization;
    }


    public void setExperienceYears(int experienceYears) {

        if (!HelperUtils.isInRange(
                experienceYears,
                0,
                Integer.MAX_VALUE)) {

            System.out.println(
                    "Experience years cannot be negative."
            );
            return;
        }

        this.experienceYears = experienceYears;
    }


    public void setConsultationFee(double consultationFee) {

        if (!HelperUtils.isInRange(
                consultationFee,
                0.0,
                Double.MAX_VALUE)) {

            IO.println(
                    "Consultation fee cannot be negative."
            );
            return;
        }

        this.consultationFee = consultationFee;
    }


    public void setOnCall(boolean onCall) {
        this.onCall = onCall;
    }


    // Getters

    public String getSpecialization() {
        return specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public boolean isOnCall() {
        return onCall;
    }

    public int getPatientLoad() {
        return patientCount;
    }


    // Slots

    public void addSlot(String slot) {

        if (!HelperUtils.isValidText(slot)) {
            IO.println("Slot cannot be empty.");
            return;
        }

        if (hasSlot(slot)) {
            IO.println("Slot already exists.");
            return;
        }

        if (slotCount >= availableSlots.length) {
            IO.println("Slot list is full.");
            return;
        }

        availableSlots[slotCount] = slot;
        slotCount++;
    }


    public boolean hasSlot(String slot) {

        if (!HelperUtils.isValidText(slot)) {
            return false;
        }

        for (int i = 0; i < slotCount; i++) {

            if (availableSlots[i].equalsIgnoreCase(slot)) {
                return true;
            }
        }

        return false;
    }


    public void removeSlot(String slot) {

        if (!HelperUtils.isValidText(slot)) {
            return;
        }

        for (int i = 0; i < slotCount; i++) {

            if (availableSlots[i].equalsIgnoreCase(slot)) {

                for (int j = i; j < slotCount - 1; j++) {
                    availableSlots[j] =
                            availableSlots[j + 1];
                }

                availableSlots[slotCount - 1] = null;
                slotCount--;

                return;
            }
        }
    }


    // Patient Assignment

    public void assignPatient(String patientId) {

        if (!HelperUtils.isValidText(patientId)) {
            IO.println("Patient ID cannot be empty.");
            return;
        }

        if (patientCount >= assignedPatientIds.length) {
            IO.println("Patient list is full.");
            return;
        }

        assignedPatientIds[patientCount] = patientId;
        patientCount++;
    }


    // Fees

    public void raiseFee(double amount) {

        if (!HelperUtils.isPositive(amount)) {
            IO.println(
                    "Fee increase must be greater than zero."
            );
            return;
        }

        setConsultationFee(
                consultationFee + amount
        );
    }


    public void updateFee(double fee) {
        setConsultationFee(fee);
    }


    public void updateFee(
            double fee,
            String reason) {

        setConsultationFee(fee);
        IO.println("Reason: " + reason);
    }


    // Overriding

    @Override
    public void displayInfo() {

        IO.println(
                "Doctor: " + getFullName() +
                        ", ID: " + getId() +
                        ", Specialization: " + getSpecialization() +
                        ", Experience: " + getExperienceYears() + " years" +
                        ", Consultation Fee: " + getConsultationFee() +
                        ", Patient Load: " + getPatientLoad() +
                        ", On Call: " + isOnCall()
        );
    }
}


