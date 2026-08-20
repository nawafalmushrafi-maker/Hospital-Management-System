package Services;

import entities.Nurse;
import interfaces.Manageable;
import interfaces.Searchable;

public class NurseService implements Manageable, Searchable {

    private Nurse[] nurses = new Nurse[100];
    private int nurseCount = 0;




    @Override
    public void add(Object entity) {

        if (!(entity instanceof Nurse)) {
            IO.println("Only Nurse objects can be added.");
            return;
        }

        Nurse nurse = (Nurse) entity;

        if (searchById(nurse.getId()) != null) {
            IO.println("Nurse ID already exists.");
            return;
        }

        if (nurseCount >= nurses.length) {
            IO.println("Nurse storage is full.");
            return;
        }

        nurses[nurseCount] = nurse;
        nurseCount++;
    }


    @Override
    public boolean removeById(String id) {

        for (int i = 0; i < nurseCount; i++) {

            if (nurses[i].getId().equals(id)) {

                for (int j = i; j < nurseCount - 1; j++) {
                    nurses[j] = nurses[j + 1];
                }

                nurses[nurseCount - 1] = null;
                nurseCount--;

                return true;
            }
        }

        return false;
    }


    @Override
    public Object[] getAll() {

        Nurse[] result = new Nurse[nurseCount];

        for (int i = 0; i < nurseCount; i++) {
            result[i] = nurses[i];
        }

        return result;
    }


    @Override
    public Object[] search(String keyword) {

        Nurse[] temp = new Nurse[nurseCount];
        int count = 0;

        for (int i = 0; i < nurseCount; i++) {

            Nurse nurse = nurses[i];

            if (
                    nurse.getFirstName()
                            .toLowerCase()
                            .contains(keyword.toLowerCase())

                            || nurse.getLastName()
                            .toLowerCase()
                            .contains(keyword.toLowerCase())

                            || nurse.getId()
                            .toLowerCase()
                            .contains(keyword.toLowerCase())

                            || nurse.getDepartmentId()
                            .toLowerCase()
                            .contains(keyword.toLowerCase())
            ) {

                temp[count] = nurse;
                count++;
            }
        }

        Nurse[] result = new Nurse[count];

        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }


    @Override
    public Object searchById(String id) {

        for (int i = 0; i < nurseCount; i++) {

            if (nurses[i].getId().equals(id)) {
                return nurses[i];
            }
        }

        return null;
    }


    public Nurse[] listByShift(String shift) {

        Nurse[] temp = new Nurse[nurseCount];
        int count = 0;

        for (int i = 0; i < nurseCount; i++) {

            if (nurses[i]
                    .getShift()
                    .equalsIgnoreCase(shift)) {

                temp[count] = nurses[i];
                count++;
            }
        }

        Nurse[] result = new Nurse[count];

        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }



    public boolean reassign(
            String fromNurseId,
            String toNurseId,
            String patientId) {

        Nurse fromNurse =
                (Nurse) searchById(fromNurseId);

        Nurse toNurse =
                (Nurse) searchById(toNurseId);

        if (fromNurse == null || toNurse == null) {
            return false;
        }

        fromNurse.unassignPatient(patientId);
        toNurse.assignPatient(patientId);

        return true;
    }



    public int getNurseCount() {
        return nurseCount;
    }
}
