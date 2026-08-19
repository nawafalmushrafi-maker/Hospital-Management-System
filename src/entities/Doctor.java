package entities;

public class Doctor extends Person {

    // * Doctor Attributes *
    private String specialization;
    private int experienceYears;
    private double consultationFee;

    private String[] availableSlots;
    private String[] assignedPatientIds;

    private int slotCount;
    private int patientCount;

    private boolean onCall;


    // * Constructor *

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

        if (specialization == null || specialization.trim().isEmpty()) {
            IO.println("Specialization cannot be empty.");
            return;
        }

        this.specialization = specialization;
    }


    public void setExperienceYears(int experienceYears) {

        if (experienceYears < 0) {
            IO.println("Experience years cannot be negative.");
            return;
        }

        this.experienceYears = experienceYears;
    }


    public void setConsultationFee(double consultationFee) {

        if (consultationFee < 0) {
            IO.println("Consultation fee cannot be negative.");
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
// * Slot Methods *

    public void addSlot(String slot) {

        if (slot == null || slot.trim().isEmpty()) {
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

        if (slot == null || slot.trim().isEmpty()) {
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

        if (slot == null || slot.trim().isEmpty()) {
            IO.println("Slot cannot be empty.");
            return;
        }

        for (int i = 0; i < slotCount; i++) {

            if (availableSlots[i].equalsIgnoreCase(slot)) {

                for (int j = i; j < slotCount - 1; j++) {
                    availableSlots[j] = availableSlots[j + 1];
                }

                availableSlots[slotCount - 1] = null;
                slotCount--;

                return;
            }
        }

        IO.println("Slot not found.");
    }


    //* Patient Assignment *

    public void assignPatient(String patientId) {

        if (patientId == null || patientId.trim().isEmpty()) {
            IO.println("Patient ID cannot be empty.");
            return;
        }

        if (hasPatient(patientId)) {
            IO.println("Patient is already assigned.");
            return;
        }

        if (patientCount >= assignedPatientIds.length) {
            IO.println("Patient list is full.");
            return;
        }

        assignedPatientIds[patientCount] = patientId;
        patientCount++;
    }


    public boolean hasPatient(String patientId) {

        if (patientId == null || patientId.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < patientCount; i++) {

            if (assignedPatientIds[i].equalsIgnoreCase(patientId)) {
                return true;
            }
        }

        return false;
    }


    //* Fee Method *

    public void raiseFee(double amount) {

        if (amount <= 0) {
            IO.println("Fee increase must be greater than zero.");
            return;
        }

        setConsultationFee(
                consultationFee + amount
        );
    }


    //* Overriding *

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

    public void updateFee(double fee) {
        setConsultationFee(fee);
    }


    public void updateFee(double fee, String reason) {

        setConsultationFee(fee);

        if (reason == null || reason.trim().isEmpty()) {
            IO.println("Reason cannot be empty.");
            return;
        }

        IO.println("Reason: " + reason);
    }
}



