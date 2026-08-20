import entities.Person;
import entities.Patient;
import entities.Doctor;
import entities.Nurse;
import entities.InPatient;
import entities.Surgeon;
import utils.InputHandler;

public class HospitalApp {

    public static void main(String[] args) {
        start();
    }


    // Main Menu

    public static void start() {

        boolean exit = false;

        while (!exit) {

            IO.println("\n==============================");
            IO.println("   HOSPITAL MANAGEMENT SYSTEM");
            IO.println("==============================");
            IO.println("1. Patients");
            IO.println("2. Doctors");
            IO.println("3. Nurses");
            IO.println("4. Appointments");
            IO.println("5. Medical Records");
            IO.println("6. Reports");
            IO.println("7. Exit");

            int choice = InputHandler.readInt(
                    "Choose option: ",
                    1,
                    7
            );

            switch (choice) {

                case 1:
                    IO.println(
                            "Patients menu coming next."
                    );
                    break;

                case 2:
                    IO.println(
                            "Doctors menu coming next."
                    );
                    break;

                case 3:
                    IO.println(
                            "Nurses menu coming next."
                    );
                    break;

                case 4:
                    IO.println(
                            "Appointments menu coming next."
                    );
                    break;

                case 5:
                    IO.println(
                            "Medical Records menu coming next."
                    );
                    break;

                case 6:
                    IO.println(
                            "Reports menu coming next."
                    );
                    break;

                case 7:
                    exit = true;
                    IO.println(
                            "Exiting Hospital Management System."
                    );
                    break;
            }
        }
    }


    // Polymorphism Helpers

    public static void printAll(Person[] people) {

        for (Person person : people) {

            if (person != null) {
                person.displayInfo();
            }
        }
    }


    public static void countByType(Person[] people) {

        int personCount = 0;
        int patientCount = 0;
        int doctorCount = 0;
        int nurseCount = 0;
        int inPatientCount = 0;
        int surgeonCount = 0;

        for (Person person : people) {

            if (person == null) {
                continue;
            }

            if (person instanceof InPatient) {
                inPatientCount++;

            } else if (person instanceof Surgeon) {
                surgeonCount++;

            } else if (person instanceof Patient) {
                patientCount++;

            } else if (person instanceof Doctor) {
                doctorCount++;

            } else if (person instanceof Nurse) {
                nurseCount++;

            } else {
                personCount++;
            }
        }

        IO.println("\n--- COUNT BY TYPE ---");
        IO.println("Person: " + personCount);
        IO.println("Patient: " + patientCount);
        IO.println("Doctor: " + doctorCount);
        IO.println("Nurse: " + nurseCount);
        IO.println("InPatient: " + inPatientCount);
        IO.println("Surgeon: " + surgeonCount);
    }


    public static Person findOldest(Person[] people) {

        Person oldest = null;

        for (Person person : people) {

            if (person == null) {
                continue;
            }

            if (oldest == null
                    || person.getAge() > oldest.getAge()) {

                oldest = person;
            }
        }

        return oldest;
    }
}