package app.domain.model;

import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.employee.CenterCoordinator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyVaccinationCenterTest {

    private static List<VaccinationCenter>vaccinationCenterList = new ArrayList<>();
    Company company = new Company("Test");
    @Test

    void createVaccinationCenter() {
        VaccineType vt = new VaccineType("awsdf","x","Rna");
        VaccinationCenter vc1 = new MassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
        VaccinationCenter actual = company.createMassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
        assertNotNull(actual);
    }

    @Test
    void validateVaccinationCenterTestTrue() {
        VaccineType vt = new VaccineType("awsdf","x","Rna");
        VaccinationCenter vc1 = new MassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
        boolean actual = company.validateMassVaccinationCenter((MassVaccinationCenter) vc1);
        assertTrue(actual);
    }

    @Test
    void validateVaccinationCenterTestFalse() {
        VaccinationCenter vc1 = new MassVaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999),null);
        boolean actual = company.validateMassVaccinationCenter((MassVaccinationCenter) vc1);
        assertFalse(actual);
    }

    @Test
    void validateVaccinationCenterTestNullParameters() {
        VaccinationCenter vc1 = new MassVaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999),null);
        boolean actual = company.validateMassVaccinationCenter((MassVaccinationCenter) vc1);
        assertFalse(actual);
    }

    @Test
    void validateVaccinationCenterTestEmptyParameter() {
        VaccinationCenter vc1 = new MassVaccinationCenter("","","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),null);
        boolean actual = company.validateMassVaccinationCenter((MassVaccinationCenter) vc1);
        assertFalse(actual);
    }

    @Test
    void saveVaccinationCenterTrue() {
        VaccineType vt = new VaccineType("awsdf","x","Rna");
        VaccinationCenter vc1 = new MassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
        company.saveMassVaccinationCenter((MassVaccinationCenter) vc1);

        boolean actual = true;
        if(Company.vaccinationCenterList.contains(vc1)){
            actual = true;
        }else{
            actual = false;
        }

        assertTrue(actual);
    }
    @Test
    void saveVaccinationCenterFalse() {
        VaccineType vt = new VaccineType("awsdf","x","Rna");
        VaccinationCenter vc1 = new MassVaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999),vt);
        company.saveMassVaccinationCenter((MassVaccinationCenter) vc1);
        boolean actual = true;
        if(Company.vaccinationCenterList.contains(vc1)){
            actual = true;
        }else{
            actual = false;
        }
        assertFalse(actual);
    }

    @Test
    void getVaccinationCenterList() {
        VaccineType vt = new VaccineType("awsdf","x","Rna");
        VaccinationCenter vc1 = new MassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
        VaccinationCenter vc2 = new MassVaccinationCenter("Centro2","Rua do Centro2","976543211","centro2@gmail.com","132-222-2222","www.centro2.pt","8:00","19:00",5,10,new CenterCoordinator("João","Casa do João",912345680,"joao@gmail.com",687654333),vt);
        Company.vaccinationCenterList.add(vc1);
        Company.vaccinationCenterList.add(vc2);
        List<VaccinationCenter> expected = Company.getVaccinationCenterList();
        assertEquals(Company.vaccinationCenterList,expected);
    }

}