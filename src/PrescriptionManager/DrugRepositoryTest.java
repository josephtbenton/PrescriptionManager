package PrescriptionManager;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DrugRepositoryTest {

	@Test
	public void testAddDrug() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		assertTrue(drugRepo.getDrugList().contains("Lisinopril - 10 mg"));
		assertTrue(drugRepo.getDrugList().contains("Azithromycin - 200 mg"));
		assertTrue(drugRepo.getDrugList().contains("Amoxicillin - 300 mg"));
	}
	
	@Test
	public void testRemoveDrug() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		drugRepo.removeDrug(drugRepo.getDrugID("Azithromycin", "200 mg"));
		assertTrue(drugRepo.getDrugList().contains("Lisinopril - 10 mg"));
		assertFalse(drugRepo.getDrugList().contains("Azithromycin - 200 mg"));
		assertTrue(drugRepo.getDrugList().contains("Amoxicillin - 300 mg"));
	}
	
	@Test
	public void testGetCount() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Lisinopril", "10 mg")) == 300);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Azithromycin", "200 mg")) == 2000);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Amoxicillin", "300 mg")) == 2000);
	}
	
}
