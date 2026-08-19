package entities;

public class InPatient {
    public class Inpatient extends Patient {

        private String admissionDate;
        private String roomNumber;
        private double dailyCharges;
        private int daysAdmitted;


        // * Constructor *

        public Inpatient(
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
                boolean insured,
                String admissionDate,
                String roomNumber,
                double dailyCharges,
                int daysAdmitted) {

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
                    active,
                    bloodGroup,
                    emergencyContact,
                    registrationDate,
                    outstandingBalance,
                    insured
            );

            setAdmissionDate(admissionDate);
            setRoomNumber(roomNumber);
            setDailyCharges(dailyCharges);
            setDaysAdmitted(daysAdmitted);
        }


        // * Setters *

        public void setAdmissionDate(String admissionDate) {

            if (admissionDate == null || admissionDate.trim().isEmpty()) {
                IO.println("Admission date cannot be empty.");
                return;
            }

            this.admissionDate = admissionDate;
        }


        public void setRoomNumber(String roomNumber) {

            if (roomNumber == null || roomNumber.trim().isEmpty()) {
                IO.println("Room number cannot be empty.");
                return;
            }

            this.roomNumber = roomNumber;
        }


        public void setDailyCharges(double dailyCharges) {

            if (dailyCharges < 0) {
                System.out.println("Daily charges cannot be negative.");
                return;
            }

            this.dailyCharges = dailyCharges;
        }


        public void setDaysAdmitted(int daysAdmitted) {

            if (daysAdmitted < 0) {
                System.out.println("Days admitted cannot be negative.");
                return;
            }

            this.daysAdmitted = daysAdmitted;
        }


        // * Getters *

        public String getAdmissionDate() {
            return admissionDate;
        }


        public String getRoomNumber() {
            return roomNumber;
        }


        public double getDailyCharges() {
            return dailyCharges;
        }


        public int getDaysAdmitted() {
            return daysAdmitted;
        }


        // * Calculate Total Charges *

        public double calculateTotalCharges() {

            return dailyCharges * daysAdmitted;
        }
        // * Add One Day *

        public void addDay() {

            daysAdmitted++;

            System.out.println(
                    "One day added. Total days: " + daysAdmitted
            );
        }


        // * Update Room *

        public void changeRoom(String newRoomNumber) {

            if (newRoomNumber == null ||
                    newRoomNumber.trim().isEmpty()) {

                System.out.println("Room number cannot be empty.");
                return;
            }

            roomNumber = newRoomNumber;

            System.out.println(
                    "Room changed to: " + roomNumber
            );
        }


        // * Discharge Patient *

        public void dischargePatient() {

            setActive(false);

            System.out.println(
                    "Patient " + getFullName() +
                            " has been discharged."
            );
        }


        // * Display Information *

        @Override
        public void displayInfo() {

            System.out.println(
                    "In-Patient: " + getFullName() +
                            ", ID: " + getId() +
                            ", Blood Group: " + getBloodGroup() +
                            ", Admission Date: " + getAdmissionDate() +
                            ", Room: " + getRoomNumber() +
                            ", Daily Charges: " + getDailyCharges() +
                            ", Days Admitted: " + getDaysAdmitted() +
                            ", Total Charges: " + calculateTotalCharges() +
                            ", Active: " + isActive()
            );
        }
        public void updateContact(String phoneNumber) {
            setPhoneNumber(phoneNumber);
        }

        public void updateContact(
                String phoneNumber,
                String email) {

            setPhoneNumber(phoneNumber);
            setEmail(email);
        }
    }
}




