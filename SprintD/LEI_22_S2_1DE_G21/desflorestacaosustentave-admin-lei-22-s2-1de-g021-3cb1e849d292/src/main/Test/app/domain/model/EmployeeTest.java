package app.domain.model;

import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void nurseIsANurse() {
        Nurse nu = new Nurse("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        assertInstanceOf(Nurse.class, nu);
    }

    @Test
    void centerCoordinatorIsACenterCoordinator() {
        CenterCoordinator cen = new CenterCoordinator("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        assertInstanceOf(CenterCoordinator.class, cen);
    }

    @Test
    void oreceptionistIsAReceptionist() {
        Receptionist re = new Receptionist("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        assertInstanceOf(Receptionist.class, re);
    }

}