package Services;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;

public class Appointmentservice {

    private Appointment[] appointments = new Appointment[100];
    private int appointmentCount = 0;


    //  1: ID & date

    public void schedule(
            String patientId,
            String doctorId,
            String date) {

        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patientId,
                doctorId,
                date,
                "12:30",
                "General",
                "Scheduled",
                false
        );

        addAppointment(appointment);
    }


    // 2: IDs & date & time

    public void schedule(
            String patientId,
            String doctorId,
            String date,
            String time) {

        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patientId,
                doctorId,
                date,
                time,
                "General",
                "Scheduled",
                false
        );

        addAppointment(appointment);
    }


    //  3: Full objects & reason

    public void schedule(
            Patient patient,
            Doctor doctor,
            String date,
            String time,
            String reason) {

        if (patient == null || doctor == null) {
            System.out.println(
                    "Patient and Doctor cannot be null."
            );
            return;
        }

        if (reason == null || reason.trim().isEmpty()) {
            System.out.println(
                    "Reason cannot be empty."
            );
            return;
        }

        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patient.getId(),
                doctor.getId(),
                date,
                time,
                reason,
                "Scheduled",
                false
        );

        addAppointment(appointment);
    }


    // Add Appointment

    private void addAppointment(Appointment appointment) {

        if (appointment == null) {
            System.out.println(
                    "Appointment cannot be null."
            );
            return;
        }

        if (appointmentCount >= appointments.length) {
            System.out.println(
                    "Appointment storage is full."
            );
            return;
        }

        appointments[appointmentCount] = appointment;
        appointmentCount++;
    }


    // Get Appointment Count

    public int getAppointmentCount() {
        return appointmentCount;
    }
}

