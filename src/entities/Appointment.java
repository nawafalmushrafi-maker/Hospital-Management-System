package entities;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String reason;
    private String status;
    private boolean completed;


    // * Constructor *

    public Appointment(
            String appointmentId,
            String patientId,
            String doctorId,
            String appointmentDate,
            String appointmentTime,
            String reason,
            String status,
            boolean completed) {

        setAppointmentId(appointmentId);
        setPatientId(patientId);
        setDoctorId(doctorId);
        setAppointmentDate(appointmentDate);
        setAppointmentTime(appointmentTime);
        setReason(reason);
        setStatus(status);
        setCompleted(completed);
    }


    // * Setters *

    public void setAppointmentId(String appointmentId) {

        if (appointmentId == null || appointmentId.trim().isEmpty()) {
            System.out.println("Appointment ID cannot be empty.");
            return;
        }

        this.appointmentId = appointmentId;
    }


    public void setPatientId(String patientId) {

        if (patientId == null || patientId.trim().isEmpty()) {
            System.out.println("Patient ID cannot be empty.");
            return;
        }

        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {

        if (doctorId == null || doctorId.trim().isEmpty()) {
            System.out.println("Doctor ID cannot be empty.");
            return;
        }

        this.doctorId = doctorId;
    }


    public void setAppointmentDate(String appointmentDate) {

        if (appointmentDate == null || appointmentDate.trim().isEmpty()) {
            System.out.println("Appointment date cannot be empty.");
            return;
        }

        this.appointmentDate = appointmentDate;
    }


    public void setAppointmentTime(String appointmentTime) {

        if (appointmentTime == null || appointmentTime.trim().isEmpty()) {
            System.out.println("Appointment time cannot be empty.");
            return;
        }

        this.appointmentTime = appointmentTime;
    }


    public void setReason(String reason) {

        if (reason == null || reason.trim().isEmpty()) {
            System.out.println("Reason cannot be empty.");
            return;
        }

        this.reason = reason;
    }


    public void setStatus(String status) {

        if (status == null || status.trim().isEmpty()) {
            System.out.println("Status cannot be empty.");
            return;
        }

        this.status = status;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    // * Getters *

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompleted() {
        return completed;
    }
    // * Complete Appointment *

    public void completeAppointment() {

        completed = true;
        status = "Completed";

        IO.println("Appointment completed.");
    }


    // Cancel Appointment

    public void cancelAppointment() {

        if (completed) {
            IO.println("Completed appointment cannot be cancelled.");
            return;
        }

        status = "Cancelled";

        IO.println("Appointment cancelled.");
    }


    // * Confirm Appointment *

    public void confirmAppointment() {

        if (completed) {
            IO.println("Appointment is already completed.");
            return;
        }

        status = "Confirmed";

        IO.println("Appointment confirmed.");
    }



}



