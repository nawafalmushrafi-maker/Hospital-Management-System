import entities.Person;
import entities.Patient;
import entities.Doctor;
import entities.Nurse;
import entities.InPatient;
import entities.Surgeon;
import entities.Appointment;
import entities.MedicalRecord;

import services.PatientService;
import services.DoctorService;
import services.NurseService;
import services.appointmentService;
import services.recordService;

import utils.InputHandler;

public class HospitalApp {

    private static PatientService patientService = new PatientService();
    private static DoctorService doctorService = new DoctorService();
    private static NurseService nurseService = new NurseService();
    private static appointmentService appointmentService = new appointmentService();
    private static recordService recordService = new recordService();

    public static void main(String[] args) {
        start();
    }

    // =========================================================
    // Main Menu
    // =========================================================

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

            int choice = InputHandler.readInt("Choose option: ", 1, 7);

            switch (choice) {
                case 1:
                    patientMenu();
                    break;
                case 2:
                    doctorMenu();
                    break;
                case 3:
                    nurseMenu();
                    break;
                case 4:
                    appointmentMenu();
                    break;
                case 5:
                    recordMenu();
                    break;
                case 6:
                    reportsHandler();
                    break;
                case 7:
                    exit = true;
                    IO.println("Exiting Hospital Management System.");
                    break;
            }
        }
    }

    // =========================================================
    // Patient Menu
    // =========================================================

    public static void patientMenu() {
        boolean back = false;

        while (!back) {
            IO.println("\n--- PATIENT MENU ---");
            IO.println("1. Add Patient");
            IO.println("2. View All Patients");
            IO.println("3. Search Patient By ID");
            IO.println("4. Remove Patient");
            IO.println("5. Total Outstanding");
            IO.println("6. Back");

            int choice = InputHandler.readInt("Choose option: ", 1, 6);

            switch (choice) {
                case 1:
                    addPatientHandler();
                    break;
                case 2:
                    viewPatientsHandler();
                    break;
                case 3:
                    searchPatientHandler();
                    break;
                case 4:
                    removePatientHandler();
                    break;
                case 5:
                    IO.println("Total Outstanding: " + patientService.totalOutstanding());
                    break;
                case 6:
                    back = true;
                    break;
            }
        }
    }

    public static void addPatientHandler() {
        String id = InputHandler.readText("Enter patient ID: ");
        String firstName = InputHandler.readText("Enter first name: ");
        String lastName = InputHandler.readText("Enter last name: ");
        String phone = InputHandler.readText("Enter phone number: ");
        String bloodGroup = InputHandler.readText("Enter blood group: ");

        patientService.addPatient(id, firstName, lastName, phone, bloodGroup);
        IO.println("Patient added successfully.");
    }

    public static void viewPatientsHandler() {
        Object[] patients = patientService.getAll();

        if (patients.length == 0) {
            IO.println("No patients found.");
            return;
        }

        for (Object obj : patients) {
            Patient patient = (Patient) obj;
            patient.displayInfo();
        }
    }

    public static void searchPatientHandler() {
        String id = InputHandler.readText("Enter patient ID: ");
        Patient patient = (Patient) patientService.searchById(id);

        if (patient == null) {
            IO.println("Patient not found.");
            return;
        }

        patient.displayInfo();
    }

    public static void removePatientHandler() {
        String id = InputHandler.readText("Enter patient ID to remove: ");
        boolean removed = patientService.removeById(id);

        if (removed) {
            IO.println("Patient removed successfully.");
        } else {
            IO.println("Patient not found.");
        }
    }

    // =========================================================
    // Doctor Menu
    // =========================================================

    public static void doctorMenu() {
        boolean back = false;

        while (!back) {
            IO.println("\n--- DOCTOR MENU ---");
            IO.println("1. View All Doctors");
            IO.println("2. Search Doctor By ID");
            IO.println("3. Remove Doctor");
            IO.println("4. List By Specialization");
            IO.println("5. Available Doctors");
            IO.println("6. Back");

            int choice = InputHandler.readInt("Choose option: ", 1, 6);

            switch (choice) {
                case 1:
                    viewDoctorsHandler();
                    break;
                case 2:
                    searchDoctorHandler();
                    break;
                case 3:
                    removeDoctorHandler();
                    break;
                case 4:
                    listDoctorsBySpecializationHandler();
                    break;
                case 5:
                    availableDoctorsHandler();
                    break;
                case 6:
                    back = true;
                    break;
            }
        }
    }

    public static void viewDoctorsHandler() {
        Object[] doctors = doctorService.getAll();

        if (doctors.length == 0) {
            IO.println("No doctors found.");
            return;
        }

        for (Object obj : doctors) {
            Doctor doctor = (Doctor) obj;
            doctor.displayInfo();
        }
    }

    public static void searchDoctorHandler() {
        String id = InputHandler.readText("Enter doctor ID: ");
        Doctor doctor = (Doctor) doctorService.searchById(id);

        if (doctor == null) {
            IO.println("Doctor not found.");
            return;
        }

        doctor.displayInfo();
    }

    public static void removeDoctorHandler() {
        String id = InputHandler.readText("Enter doctor ID to remove: ");
        boolean removed = doctorService.removeById(id);

        if (removed) {
            IO.println("Doctor removed successfully.");
        } else {
            IO.println("Doctor not found.");
        }
    }

    public static void listDoctorsBySpecializationHandler() {
        String specialization = InputHandler.readText("Enter specialization: ");
        Doctor[] doctors = doctorService.listBySpecialization(specialization);

        if (doctors.length == 0) {
            IO.println("No doctors found.");
            return;
        }

        for (Doctor doctor : doctors) {
            doctor.displayInfo();
        }
    }

    public static void availableDoctorsHandler() {
        Doctor[] doctors = doctorService.availableDoctors();

        if (doctors.length == 0) {
            IO.println("No available doctors found.");
            return;
        }

        for (Doctor doctor : doctors) {
            doctor.displayInfo();
        }
    }

    // =========================================================
    // Nurse Menu
    // =========================================================

    public static void nurseMenu() {
        boolean back = false;

        while (!back) {
            IO.println("\n--- NURSE MENU ---");
            IO.println("1. View All Nurses");
            IO.println("2. Search Nurse By ID");
            IO.println("3. Remove Nurse");
            IO.println("4. List By Shift");
            IO.println("5. Reassign Patient");
            IO.println("6. Back");

            int choice = InputHandler.readInt("Choose option: ", 1, 6);

            switch (choice) {
                case 1:
                    viewNursesHandler();
                    break;
                case 2:
                    searchNurseHandler();
                    break;
                case 3:
                    removeNurseHandler();
                    break;
                case 4:
                    listNursesByShiftHandler();
                    break;
                case 5:
                    reassignPatientHandler();
                    break;
                case 6:
                    back = true;
                    break;
            }
        }
    }

    public static void viewNursesHandler() {
        Object[] nurses = nurseService.getAll();

        if (nurses.length == 0) {
            IO.println("No nurses found.");
            return;
        }

        for (Object obj : nurses) {
            Nurse nurse = (Nurse) obj;
            nurse.displayInfo();
        }
    }

    public static void searchNurseHandler() {
        String id = InputHandler.readText("Enter nurse ID: ");
        Nurse nurse = (Nurse) nurseService.searchById(id);

        if (nurse == null) {
            IO.println("Nurse not found.");
            return;
        }

        nurse.displayInfo();
    }

    public static void removeNurseHandler() {
        String id = InputHandler.readText("Enter nurse ID to remove: ");
        boolean removed = nurseService.removeById(id);

        if (removed) {
            IO.println("Nurse removed successfully.");
        } else {
            IO.println("Nurse not found.");
        }
    }

    public static void listNursesByShiftHandler() {
        String shift = InputHandler.readText("Enter shift: ");
        Nurse[] nurses = nurseService.listByShift(shift);

        if (nurses.length == 0) {
            IO.println("No nurses found for this shift.");
            return;
        }

        for (Nurse nurse : nurses) {
            nurse.displayInfo();
        }
    }

    public static void reassignPatientHandler() {
        String fromNurseId = InputHandler.readText("Enter current nurse ID: ");
        String toNurseId = InputHandler.readText("Enter new nurse ID: ");
        String patientId = InputHandler.readText("Enter patient ID: ");

        boolean reassigned = nurseService.reassign(fromNurseId, toNurseId, patientId);

        if (reassigned) {
            IO.println("Patient reassigned successfully.");
        } else {
            IO.println("Unable to reassign patient.");
        }
    }

    // =========================================================
    // Appointment Menu
    // =========================================================

    public static void appointmentMenu() {
        boolean back = false;

        while (!back) {
            IO.println("\n--- APPOINTMENT MENU ---");
            IO.println("1. Schedule Appointment");
            IO.println("2. View All Appointments");
            IO.println("3. Cancel Appointment");
            IO.println("4. Complete Appointment");
            IO.println("5. Reschedule Appointment");
            IO.println("6. List By Status");
            IO.println("7. List By Patient");
            IO.println("8. Back");

            int choice = InputHandler.readInt("Choose option: ", 1, 8);

            switch (choice) {
                case 1:
                    scheduleAppointmentHandler();
                    break;
                case 2:
                    viewAppointmentsHandler();
                    break;
                case 3:
                    cancelAppointmentHandler();
                    break;
                case 4:
                    completeAppointmentHandler();
                    break;
                case 5:
                    rescheduleAppointmentHandler();
                    break;
                case 6:
                    listAppointmentsByStatusHandler();
                    break;
                case 7:
                    listAppointmentsByPatientHandler();
                    break;
                case 8:
                    back = true;
                    break;
            }
        }
    }

    public static void scheduleAppointmentHandler() {
        String patientId = InputHandler.readText("Enter patient ID: ");
        String doctorId = InputHandler.readText("Enter doctor ID: ");
        String date = InputHandler.readText("Enter appointment date: ");
        String time = InputHandler.readText("Enter appointment time: ");

        // Change addAppointment to schedule
        appointmentService.schedule(patientId, doctorId, date, time);
        IO.println("Appointment scheduled successfully.");
    }

    public static void viewAppointmentsHandler() {
        Object[] appointments = appointmentService.getAll();

        if (appointments.length == 0) {
            IO.println("No appointments found.");
            return;
        }

        for (Object obj : appointments) {
            Appointment appointment = (Appointment) obj;
            appointment.displayInfo();
        }
    }

    public static void cancelAppointmentHandler() {
        String id = InputHandler.readText("Enter appointment ID: ");
        boolean cancelled = appointmentService.cancel(id);

        if (cancelled) {
            IO.println("Appointment cancelled.");
        } else {
            IO.println("Appointment not found.");
        }
    }

    public static void completeAppointmentHandler() {
        String id = InputHandler.readText("Enter appointment ID: ");
        boolean completed = appointmentService.complete(id);

        if (completed) {
            IO.println("Appointment completed.");
        } else {
            IO.println("Appointment not found.");
        }
    }

    public static void rescheduleAppointmentHandler() {
        String id = InputHandler.readText("Enter appointment ID: ");
        String newDate = InputHandler.readText("Enter new date: ");
        String newTime = InputHandler.readText("Enter new time: ");

        boolean rescheduled = appointmentService.reschedule(id, newDate, newTime);

        if (rescheduled) {
            IO.println("Appointment rescheduled.");
        } else {
            IO.println("Appointment not found.");
        }
    }

    public static void listAppointmentsByStatusHandler() {
        String status = InputHandler.readText("Enter status: ");
        Appointment[] appointments = appointmentService.listByStatus(status);

        if (appointments.length == 0) {
            IO.println("No appointments found.");
            return;
        }

        for (Appointment appointment : appointments) {
            appointment.displayInfo();
        }
    }

    public static void listAppointmentsByPatientHandler() {
        String patientId = InputHandler.readText("Enter patient ID: ");
        Appointment[] appointments = appointmentService.listByPatient(patientId);

        if (appointments.length == 0) {
            IO.println("No appointments found.");
            return;
        }

        for (Appointment appointment : appointments) {
            appointment.displayInfo();
        }
    }

    // =========================================================
    // Medical Record Menu
    // =========================================================

    public static void recordMenu() {
        boolean back = false;

        while (!back) {
            IO.println("\n--- MEDICAL RECORD MENU ---");
            IO.println("1. View All Records");
            IO.println("2. Search Record By ID");
            IO.println("3. Remove Record");
            IO.println("4. List By Patient");
            IO.println("5. Count Confidential");
            IO.println("6. Back");

            int choice = InputHandler.readInt("Choose option: ", 1, 6);

            switch (choice) {
                case 1:
                    viewRecordsHandler();
                    break;
                case 2:
                    searchRecordHandler();
                    break;
                case 3:
                    removeRecordHandler();
                    break;
                case 4:
                    //listRecordsByPatientHandler();
                    break;
                case 5:
                    IO.println("Confidential Records: " );

                    break;
                case 6:
                    back = true;
                    break;
            }
        }
    }

    public static void viewRecordsHandler() {
        Object[] records = recordService.getAll();

        if (records.length == 0) {
            IO.println("No medical records found.");
            return;
        }

        for (Object obj : records) {
            MedicalRecord record = (MedicalRecord) obj;
            record.displayInfo();
        }
    }

    public static void searchRecordHandler() {
        String id = InputHandler.readText("Enter record ID: ");
        MedicalRecord record = (MedicalRecord) recordService.searchById(id);

        if (record == null) {
            IO.println("Medical record not found.");
            return;
        }

        record.displayInfo();
    }

    public static void removeRecordHandler() {
        String id = InputHandler.readText("Enter record ID to remove: ");
        boolean removed = recordService.removeById(id);

        if (removed) {
            IO.println("Medical record removed.");
        } else {
            IO.println("Medical record not found.");
        }
    }


    // =========================================================
    // Reports Handler
    // =========================================================

    public static void reportsHandler() {
        Object[] patients = patientService.getAll();
        Object[] doctors = doctorService.getAll();
        Object[] nurses = nurseService.getAll();
        Object[] appointments = appointmentService.getAll();
        Object[] records = recordService.getAll();
        InPatient[] inPatients = patientService.listInPatients();

        IO.println("\n==============================");
        IO.println("       HOSPITAL REPORTS");
        IO.println("==============================");
        IO.println("Total Patients: " + patients.length);
        IO.println("Total InPatients: " + inPatients.length);
        IO.println("Total Doctors: " + doctors.length);
        IO.println("Total Nurses: " + nurses.length);
        IO.println("Total Appointments: " + appointments.length);
        IO.println("Total Medical Records: " + records.length);
        IO.println("Confidential Records: " + recordService.countConfidential());
        IO.println("Total Outstanding Balance: " + patientService.totalOutstanding());
        IO.println("==============================");
    }

    // =========================================================
    // Polymorphism Helpers
    // =========================================================

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

            if (oldest == null || person.getAge() > oldest.getAge()) {
                oldest = person;
            }
        }

        return oldest;
    }
}