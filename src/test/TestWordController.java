package test;

import java.util.ArrayList;

import model.Lexicon;
import model.Word;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.WordController;
import junit.framework.TestCase;

public class TestWordController extends TestCase {

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
	public void testWordController() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		assertNotNull(wordController);
	}

	@Test
	public void testGetLasttoInLexicon() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		wordController.loadrecord();
		assertNotNull(wordController.getLasttoInLexicon("A"));
		assertNotNull(wordController.getLasttoInLexicon("Z"));//related with record file
	}

	@Test
	public void testLoadwordlist() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
	}

	@Test
	public void testGetWordByID() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		Word word = wordController.getWordByID(0);
		assertEquals(word.getID(), 0);
		assertEquals(word.getWord(), "abandon");
	}

	@Test
	public void testGetIDByWord() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		int id = wordController.getIDByWord("abandon");
		assertEquals(id, 0);
		id = wordController.getIDByWord("abandonment");
		assertEquals(id, 1);
		id = wordController.getIDByWord("find");
		assertEquals(id, 2809);
	}

	@Test
	public void testGetStartInLexicon() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		int start = wordController.getStartInLexicon("LLL");
		assertEquals(start, -1);
		start = wordController.getStartInLexicon("A");
		assertEquals(start, 0);
	}

	@Test
	public void testGetLastInLexicon() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		int last = wordController.getLastInLexicon("S");
		assertEquals(last, 7061);
		last = wordController.getLastInLexicon("D");
		assertEquals(last, 2255);
		last = wordController.getLastInLexicon("K");
		assertEquals(last, 4007);
	}

	@Test
	public void testGetTotalInLexicon() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		int r = wordController.getTotalInLexicon("A");
		assertEquals(r, 562);
		r = wordController.getTotalInLexicon("B");
		assertEquals(r, 397);
	}

	@Test
	public void testGetLexiconStats() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		assertNotNull(wordController.getLexiconStats());
	}

	@Test
	public void testGetSimilarWords() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		wordController.loadwordlist();
		ArrayList<String> list = wordController.getSimilarWords("word", "W");
		assertEquals(list.size(), 6);
		list = wordController.getSimilarWords("abandon", "A");
		assertEquals(list.size(), 4);
		ArrayList<String> list2 = wordController.getSimilarWords("same", "W");
		assertEquals(list2.size(), 0);
	}

	@Test
	public void testGetListName() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		assertNotNull(wordController.getListName());
		assertEquals(wordController.getListName(), "wordlist/dictionary.txt");
	}

	@Test
	public void testIsValid() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		assertEquals(wordController.isValid(-2, -1, ""), -1);
		assertEquals(wordController.isValid(2, 1, ""), -2);
		assertEquals(wordController.isValid(1, 100, "A"), 0);
	}

	@Test
	public void testIsInteger() {
		WordController wordController = new WordController("wordlist/dictionary.txt");
		assertEquals(wordController.isInteger("77777777"), false);
		assertEquals(wordController.isInteger("7"), true);
	}

}
