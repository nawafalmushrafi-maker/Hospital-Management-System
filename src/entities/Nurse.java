package entities;

import utils.HelperUtils;

public class Nurse extends Person {

    private String departmentId;
    private String shift;
    private String[] assignedPatientIds;
    private int patientCount;
    private int yearsOfService;


    // Constructor

    public Nurse(
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
            String departmentId,
            String shift,
            int yearsOfService) {

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

        setDepartmentId(departmentId);
        setShift(shift);
        setYearsOfService(yearsOfService);

        assignedPatientIds = new String[50];
        patientCount = 0;
    }


    // Setters

    public void setDepartmentId(String departmentId) {

        if (!HelperUtils.isValidText(departmentId)) {
            IO.println(
                    "Department ID cannot be empty."
            );
            return;
        }

        this.departmentId = departmentId;
    }


    public void setShift(String shift) {

        String[] allowedShifts = {
                "Morning",
                "Evening",
                "Night"
        };

        if (!HelperUtils.isOneOf(
                shift,
                allowedShifts)) {

            IO.println(
                    "Shift must be Morning, Evening, or Night."
            );
            return;
        }

        this.shift = shift;
    }


    public void setYearsOfService(int yearsOfService) {

        if (!HelperUtils.isInRange(
                yearsOfService,
                0,
                Integer.MAX_VALUE)) {

            IO.println(
                    "Years of service cannot be negative."
            );
            return;
        }

        this.yearsOfService = yearsOfService;
    }


    // Getters

    public String getDepartmentId() {
        return departmentId;
    }

    public String getShift() {
        return shift;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }


    // Assign Patient

    public void assignPatient(String patientId) {

        if (!HelperUtils.isValidText(patientId)) {
            IO.println(
                    "Patient ID cannot be empty."
            );
            return;
        }

        if (patientCount >= assignedPatientIds.length) {
            IO.println("Patient list is full.");
            return;
        }

        assignedPatientIds[patientCount] = patientId;
        patientCount++;
    }


    // Unassign Patient

    public void unassignPatient(String patientId) {

        if (!HelperUtils.isValidText(patientId)) {
            return;
        }

        for (int i = 0; i < patientCount; i++) {

            if (assignedPatientIds[i]
                    .equalsIgnoreCase(patientId)) {

                for (int j = i; j < patientCount - 1; j++) {
                    assignedPatientIds[j] =
                            assignedPatientIds[j + 1];
                }

                assignedPatientIds[patientCount - 1] = null;
                patientCount--;

                return;
            }
        }
    }


    public int getPatientLoad() {
        return patientCount;
    }


    public boolean isNightShift() {
        return shift != null
                && shift.equalsIgnoreCase("Night");
    }


    @Override
    public void displayInfo() {

        IO.println(
                "Nurse: " + getFullName() +
                        ", ID: " + getId() +
                        ", Department ID: " + getDepartmentId() +
                        ", Shift: " + getShift() +
                        ", Years of Service: " + getYearsOfService() +
                        ", Patient Load: " + getPatientLoad()
        );
    }
}