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
	public void testGetDrugID() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		assertTrue(drugRepo.getDrugID("Lisinopril", "10 mg") == 1);
		assertTrue(drugRepo.getDrugID("Azithromycin", "200 mg") == 2);
		assertTrue(drugRepo.getDrugID("Amoxicillin", "300 mg") == 3);
		assertFalse(drugRepo.getDrugID("Lisinopril", "10 mg") == 2);
		assertFalse(drugRepo.getDrugID("Azithromycin", "200 mg") == 4);
		assertFalse(drugRepo.getDrugID("Amoxicillin", "300 mg") == 1);
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
	
	@Test
	public void testUpdateCount() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		drugRepo.updateCount(drugRepo.getDrugID("Lisinopril", "10 mg"), 700);
		drugRepo.updateCount(drugRepo.getDrugID("Azithromycin", "200 mg"), 3000);
		drugRepo.updateCount(drugRepo.getDrugID("Amoxicillin", "300 mg"), 500);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Lisinopril", "10 mg")) == 1000);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Azithromycin", "200 mg")) == 5000);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Amoxicillin", "300 mg")) == 2500);
		assertFalse(drugRepo.getCount(drugRepo.getDrugID("Amoxicillin", "300 mg")) == 500);
		drugRepo.updateCount(drugRepo.getDrugID("Azithromycin", "200 mg"), -1000);
		assertTrue(drugRepo.getCount(drugRepo.getDrugID("Azithromycin", "200 mg")) == 4000);
	}
	
	@Test
	public void testGetStrength() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		assertTrue(drugRepo.getStrength(drugRepo.getDrugID("Lisinopril", "10 mg")) == "10 mg");
		assertTrue(drugRepo.getStrength(drugRepo.getDrugID("Azithromycin", "200 mg")) == "200 mg");
		assertTrue(drugRepo.getStrength(drugRepo.getDrugID("Amoxicillin", "300 mg")) == "300 mg");
		assertFalse(drugRepo.getStrength(drugRepo.getDrugID("Lisinopril", "10 mg")) == "100000 mg");
		assertFalse(drugRepo.getStrength(drugRepo.getDrugID("Azithromycin", "200 mg")) == "1 mg");
		assertFalse(drugRepo.getStrength(drugRepo.getDrugID("Amoxicillin", "300 mg")) == "12 kg");
	}
	
	@Test
	public void testGetDrug() throws ClassNotFoundException, SQLException {
		DrugRepository drugRepo;
		drugRepo = new DrugRepository();
		drugRepo.addDrug("Lisinopril", "10 mg", 300);
		drugRepo.addDrug("Azithromycin", "200 mg", 2000);
		drugRepo.addDrug("Amoxicillin", "300 mg", 2000);
		assertTrue(drugRepo.getDrug(drugRepo.getDrugID("Lisinopril", "10 mg")) == "Lisinopril");
		assertTrue(drugRepo.getDrug(drugRepo.getDrugID("Azithromycin", "200 mg")) == "Azithromycin");
		assertTrue(drugRepo.getDrug(drugRepo.getDrugID("Amoxicillin", "300 mg")) == "Amoxicillin");
		assertFalse(drugRepo.getDrug(drugRepo.getDrugID("Lisinopril", "10 mg")) == "Azithromycin");
		assertFalse(drugRepo.getDrug(drugRepo.getDrugID("Azithromycin", "200 mg")) == "Amoxicillin");
		assertFalse(drugRepo.getDrug(drugRepo.getDrugID("Amoxicillin", "300 mg")) == "Lisinopril");
	}
}
