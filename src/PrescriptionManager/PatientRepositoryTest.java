package PrescriptionManager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DrugRepositoryTest {
	@Test
	public void testAddPatient() throws ClassNotFoundException, SQLException {
		PatientRepository patientRepo;
		patientRepo = new PatientRepository();
		patientRepo.addPatient("Tom", "Hanks", "06/09/1956");
		patientRepo.addPatient("Mick", "Jagger", "06/26/1943");
		patientRepo.addPatient("Justin", "Bieber", "03/01/1994");
		assertTrue(patientRepo.getPID("Tom", "Hanks", "06/09/1956") == 1);
		assertTrue(patientRepo.getPatientList().contains("Azithromycin - 200 mg"));
		assertTrue(patientRepo.getDrugList().contains("Amoxicillin - 300 mg"));
	}
	
	@Test
	public void testGetPID() throws ClassNotFoundException, SQLException {
		PatientRepository patientRepo;
		patientRepo = new PatientRepository();
		patientRepo.addPatient("Tom", "Hanks", "06/09/1956");
		patientRepo.addPatient("Mick", "Jagger", "06/26/1943");
		patientRepo.addPatient("Justin", "Bieber", "03/01/1994");
		assertTrue(patientRepo.getPID("Tom", "Hanks", "06/09/1956") == 1);
		assertTrue(patientRepo.getPID("Mick", "Jagger", "06/26/1943") == 2);
		assertTrue(patientRepo.getPID("Justin", "Bieber", "03/01/1994") == 3);
		assertFalse(patientRepo.getPID("Tom", "Hanks", "06/09/1956") == 3);
		assertFalse(patientRepo.getPID("Mick", "Jagger", "06/26/1943") == 10);
		assertFalse(patientRepo.getPID("Justin", "Bieber", "03/01/1994") == 1);
	}
	
	
	@Test
	public void testGetDOB() throws ClassNotFoundException, SQLException {
		PatientRepository patientRepo;
		patientRepo = new PatientRepository();
		patientRepo.addPatient("Tom", "Hanks","06/09/1956");
		patientRepo.addPatient("Mick", "Jagger", "06/26/1943");
		patientRepo.addPatient("Justin", "Bieber", "03/01/1994");
		assertTrue(patientRepo.getDOB(patientRepo.getPID("Tom", "Hanks", "06/09/1956").equals("06/09/1956"));
		assertTrue(patientRepo.getDOB(patientRepo.getPID("Mick", "Jagger", "06/26/1943").equals("06/26/1943"));
		assertTrue(patientRepo.getDOB(patientRepo.getPID("Justin", "Bieber", "03/01/1994").equals("03/01/1994"));
	}
	
	@Test
	public void testGetPatientList() throws ClassNotFoundException, SQLException {
		PatientRepository patientRepo;
		patientRepo = new PatientRepository();
		patientRepo.addPatient("Tom", "Hanks","06/09/1956");
		patientRepo.addPatient("Mick", "Jagger", "06/26/1943");
		patientRepo.addPatient("Justin", "Bieber", "03/01/1994");
		assertTrue(patientRepo.getPatientList().contains("Tom", "Hanks", "06/09/1956"));
		assertTrue(patientRepo.getPatientList().contains("Mick", "Jagger", "06/26/1943"));
		assertTrue(patientRepo.getPatientList().contains("Justin", "Bieber", "03/01/1994"));
		assertFalse(patientRepo.getPatientList().contains("Shia", "LaBeouf", "05/11/1986"));		
	}
	
	
	/*
	@Test
	public void testRemovePatient() throws ClassNotFoundException, SQLException {
		PatientRepository patientRepo;
		patientRepo = new PatientRepository();
		patientRepo.addPatient("Tom", "Hanks","06/09/1956");
		patientRepo.addPatient("Mick", "Jagger", "06/26/1943");
		patientRepo.addPatient("Justin", "Bieber", "03/01/1994");
		patientRepo.removePatient(patientRepo.getPID("Tom", "Hanks", "06/09/1956")
		assertFalse(patientRepo);
		assertTrue(patientRepo.getDOB(patientRepo.getPID("Mick", "Jagger", "06/26/1943").equals("06/26/1943"));
		assertTrue(patientRepo.getDOB(patientRepo.getPID("Justin", "Bieber", "03/01/1994").equals("03/01/1994"));
	}
	*/
}
	
	
