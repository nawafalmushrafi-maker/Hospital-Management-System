package entities;

public class Doctor extends Person {

    // Doctor Attributes
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

        if (specialization == null || specialization.trim().isEmpty()) {
            System.out.println("Specialization cannot be empty.");
            return;
        }

        this.specialization = specialization;
    }


    public void setExperienceYears(int experienceYears) {

        if (experienceYears < 0) {
            System.out.println("Experience years cannot be negative.");
            return;
        }

        this.experienceYears = experienceYears;
    }


    public void setConsultationFee(double consultationFee) {

        if (consultationFee < 0) {
            System.out.println("Consultation fee cannot be negative.");
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

}


