package harmonie;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class NoteTest extends Note{

	public NoteTest() {
		super();
	}

	private Note note1 = new Note("1", 1, 2, 3, 4);
	private Note note2 = new Note("2", 5, 6, 7, 8);

	@Test
	public void testNote() {
		assertNotNull(note1);
		assertNotNull(note2);
	}

	@Test
	public void testtrouverNote() {
		assertEquals(DO, trouverNote(21));
		assertEquals(DO, trouverNote(0));
		assertEquals(RE, trouverNote(8));
		assertEquals(RE, trouverNote(15));
		assertEquals(MI, trouverNote(2));
		assertEquals(MI, trouverNote(9));
		assertEquals(FA, trouverNote(3));
		assertEquals(FA, trouverNote(17));
		assertEquals(SOL, trouverNote(4));
		assertEquals(SOL, trouverNote(11));
		assertEquals(LA, trouverNote(12));
		assertEquals(LA, trouverNote(19));
		assertEquals(SI, trouverNote(6));
		assertEquals(SI, trouverNote(27));
	}
	
	@Test
	public void testEquals() {
		assertEquals(false, note1.equals(note2));
		assertEquals(true, note1.equals(note1));
		assertEquals(true, note2.equals(note2));
	}

	@Test
	public void testgetNom() {
		assertEquals("1", note1.getNom());
		assertEquals("2", note2.getNom());
	}

	@Test
	public void testsetNom() {
		note1.setNom("Note12");
		assertEquals("Note12", note1.getNom());

		note2.setNom("Note22");
		assertEquals("Note22", note2.getNom());
	}

	@Test
	public void testgetListeNotes() {
		LinkedList<Integer> listeNote1, listeNote2;

		listeNote1 = new LinkedList<Integer>();
		listeNote2 = new LinkedList<Integer>();

		listeNote1.add(1);
		listeNote1.add(2);
		listeNote1.add(3);
		listeNote1.add(4);
		listeNote2.add(5);
		listeNote2.add(6);
		listeNote2.add(7);
		listeNote2.add(8);

		assertEquals(listeNote1, note1.getListeNotes());
		assertEquals(listeNote2, note2.getListeNotes());
	}

	@Test
	public void testsetListeNotes() {
		LinkedList<Integer> listeNote1, listeNote2;

		listeNote1 = new LinkedList<Integer>();
		listeNote2 = new LinkedList<Integer>();

		listeNote1.add(1);
		listeNote1.add(2);
		listeNote1.add(3);
		listeNote1.add(4);
		listeNote2.add(5);
		listeNote2.add(6);
		listeNote2.add(7);
		listeNote2.add(8);

		note1.setListeNotes(listeNote2);
		note2.setListeNotes(listeNote1);

		assertEquals(listeNote2, note1.getListeNotes());
		assertEquals(listeNote1, note2.getListeNotes());
	}

}
