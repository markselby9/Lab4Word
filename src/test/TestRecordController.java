package test;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.RecordController;
import junit.framework.TestCase;

public class TestRecordController extends TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testRecordController() {
		RecordController rController = new RecordController();
		assertNotNull(rController);
		
	}

	@Test
	public void testSaveRecord() {
		RecordController rController = new RecordController();
		HashMap<Integer,Integer> recordmap = new HashMap<>();
		int[] lastto = new int[26];
		assertSame(rController.saveRecord(recordmap, lastto), true);
	}

	@Test
	public void testOpenRecord() {
		RecordController rController = new RecordController();
		HashMap<Integer,Integer> record = new HashMap<>();
		assertNotNull(rController.openRecord());
	}

}
