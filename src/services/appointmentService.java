package services;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import interfaces.Manageable;
import interfaces.Searchable;

public class appointmentService implements Manageable, Searchable {

    private Appointment[] appointments = new Appointment[100];
    private int appointmentCount = 0;


    // Schedule Overloads


    public void schedule(String patientId, String doctorId, String date) {
        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patientId,
                doctorId,
                date,
                "09:00",
                "Scheduled",
                "General",
                false
        );
        addAppointment(appointment);
    }

    public void schedule(String patientId, String doctorId, String date, String time) {
        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patientId,
                doctorId,
                date,
                time,
                "Scheduled",
                "General",
                false
        );
        addAppointment(appointment);
    }

    public void schedule(Patient patient, Doctor doctor, String date, String time, String reason) {
        if (patient == null || doctor == null) {
            IO.println("Patient and Doctor cannot be null.");
            return;
        }

        Appointment appointment = new Appointment(
                "A" + (appointmentCount + 1),
                patient.getId(),
                doctor.getId(),
                date,
                time,
                "Scheduled",
                reason,
                false
        );
        addAppointment(appointment);
    }


    // Manageable


    @Override
    public void add(Object entity) {
        if (!(entity instanceof Appointment)) {
            IO.println("Only Appointment objects can be added.");
            return;
        }

        Appointment appointment = (Appointment) entity;

        if (searchById(appointment.getAppointmentId()) != null) {
            IO.println("Appointment ID already exists.");
            return;
        }

        if (appointmentCount >= appointments.length) {
            IO.println("Appointment storage is full.");
            return;
        }

        appointments[appointmentCount] = appointment;
        appointmentCount++;
    }

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId().equals(id)) {
                for (int j = i; j < appointmentCount - 1; j++) {
                    appointments[j] = appointments[j + 1];
                }
                appointments[appointmentCount - 1] = null;
                appointmentCount--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        Appointment[] result = new Appointment[appointmentCount];
        for (int i = 0; i < appointmentCount; i++) {
            result[i] = appointments[i];
        }
        return result;
    }


    // Searchable


    @Override
    public Object[] search(String keyword) {
        Appointment[] temp = new Appointment[appointmentCount];
        int count = 0;

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.getAppointmentId().toLowerCase().contains(keyword.toLowerCase())
                    || appointment.getPatientId().toLowerCase().contains(keyword.toLowerCase())
                    || appointment.getDoctorId().toLowerCase().contains(keyword.toLowerCase())
                    || appointment.getStatus().toLowerCase().contains(keyword.toLowerCase())) {
                temp[count] = appointment;
                count++;
            }
        }

        Appointment[] result = new Appointment[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    @Override
    public Object searchById(String id) {
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId().equals(id)) {
                return appointments[i];
            }
        }
        return null;
    }


    // Appointment Actions


    public boolean cancel(String appointmentId) {
        Appointment appointment = (Appointment) searchById(appointmentId);
        if (appointment == null) {
            return false;
        }
        appointment.cancel();
        return true;
    }

    public boolean complete(String appointmentId) {
        Appointment appointment = (Appointment) searchById(appointmentId);
        if (appointment == null) {
            return false;
        }
        appointment.complete();
        return true;
    }

    public boolean reschedule(String appointmentId, String newDate, String newTime) {
        Appointment appointment = (Appointment) searchById(appointmentId);
        if (appointment == null) {
            return false;
        }
        appointment.reschedule(newDate, newTime);
        return true;
    }

    public Appointment[] listByStatus(String status) {
        Appointment[] temp = new Appointment[appointmentCount];
        int count = 0;

        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getStatus().equalsIgnoreCase(status)) {
                temp[count] = appointments[i];
                count++;
            }
        }

        Appointment[] result = new Appointment[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public Appointment[] listByPatient(String patientId) {
        Appointment[] temp = new Appointment[appointmentCount];
        int count = 0;

        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getPatientId().equalsIgnoreCase(patientId)) {
                temp[count] = appointments[i];
                count++;
            }
        }

        Appointment[] result = new Appointment[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    private void addAppointment(Appointment appointment) {
        add(appointment);
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }
}