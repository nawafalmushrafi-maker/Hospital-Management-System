package entities;

public class Nurse extends Person {

    private String departmentId;
    private String shift;
    private String[] assignedPatientIds;
    private int patientCount;
    private int yearsOfService;


    // Constructor *

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


    // Setters *

    public void setDepartmentId(String departmentId) {

        if (departmentId == null || departmentId.trim().isEmpty()) {
            System.out.println("Department ID cannot be empty.");
            return;
        }

        this.departmentId = departmentId;
    }


    public void setShift(String shift) {

        if (shift == null ||
                (!shift.equalsIgnoreCase("Morning")
                        && !shift.equalsIgnoreCase("Evening")
                        && !shift.equalsIgnoreCase("Night"))) {

            System.out.println(
                    "Shift must be Morning, Evening, or Night."
            );
            return;
        }

        this.shift = shift;
    }


    public void setYearsOfService(int yearsOfService) {

        if (yearsOfService < 0) {
            System.out.println(
                    "Years of service cannot be negative."
            );
            return;
        }

        this.yearsOfService = yearsOfService;
    }


    // Getters *

    public String getDepartmentId() {
        return departmentId;
    }


    public String getShift() {
        return shift;
    }


    public int getYearsOfService() {
        return yearsOfService;
    }
    // Assign Patient *

    public void assignPatient(String patientId) {

        if (patientId == null || patientId.trim().isEmpty()) {
            System.out.println("Patient ID cannot be empty.");
            return;
        }

        if (hasPatient(patientId)) {
            System.out.println("Patient is already assigned.");
            return;
        }

        if (patientCount >= assignedPatientIds.length) {
            System.out.println("Patient list is full.");
            return;
        }

        assignedPatientIds[patientCount] = patientId;
        patientCount++;
    }


    // Check Patient *

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


    // Unassign Patient *

    public void unassignPatient(String patientId) {

        if (patientId == null || patientId.trim().isEmpty()) {
            System.out.println("Patient ID cannot be empty.");
            return;
        }

        for (int i = 0; i < patientCount; i++) {

            if (assignedPatientIds[i].equalsIgnoreCase(patientId)) {

                for (int j = i; j < patientCount - 1; j++) {
                    assignedPatientIds[j] =
                            assignedPatientIds[j + 1];
                }

                assignedPatientIds[patientCount - 1] = null;
                patientCount--;

                return;
            }
        }

        System.out.println("Patient not assigned to this nurse.");
    }


    // Patient Load *

    public int getPatientLoad() {
        return patientCount;
    }


    // Night Shift *

    public boolean isNightShift() {

        return shift != null &&
                shift.equalsIgnoreCase("Night");
    }


    // Overriding *

    @Override
    public void displayInfo() {

        System.out.println(
                "Nurse: " + getFullName() +
                        ", ID: " + getId() +
                        ", Department ID: " + getDepartmentId() +
                        ", Shift: " + getShift() +
                        ", Years of Service: " + getYearsOfService() +
                        ", Patient Load: " + getPatientLoad()
        );
    }
}



