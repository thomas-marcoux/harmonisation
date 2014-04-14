package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccordTest {
	private Note premiere = new Note("Tonique", 1, 2, 3, 4);
	private Note deuxieme = new Note("Tierce", 4, 3, 2, 1);
	private Note troisieme = new Note("Quinte", 4, 3, 1, 2);

	private Accord accord1 = new Accord("Accord1", premiere, deuxieme,
			troisieme);
	private Accord accord2 = new Accord("Accord2", troisieme, premiere,
			deuxieme);

	@Test
	public void testAccord() {
		assertNotNull(accord1);
		assertNotNull(accord2);
	}

	@Test
	public void testgetNom() {
		assertEquals("Accord1", accord1.getNom());
		assertEquals("Accord2", accord2.getNom());
	}

	@Test
	public void testsetNom() {
		accord1.setNom("Acc1");
		accord2.setNom("Acc2");

		assertEquals("Acc1", accord1.getNom());
		assertEquals("Acc2", accord2.getNom());
	}

	@Test
	public void testgetTonique() {
		assertEquals(premiere, accord1.getTonique());
		assertEquals(troisieme, accord2.getTonique());
	}

	@Test
	public void testsetTonique() {
		accord1.setTonique(troisieme);
		accord2.setTonique(premiere);

		assertEquals(troisieme, accord1.getTonique());
		assertEquals(premiere, accord2.getTonique());
	}

	@Test
	public void testgetTierce() {
		assertEquals(deuxieme, accord1.getTierce());
		assertEquals(premiere, accord2.getTierce());
	}

	@Test
	public void testsetTierce() {
		accord1.setTierce(premiere);
		accord2.setTierce(deuxieme);

		assertEquals(premiere, accord1.getTierce());
		assertEquals(deuxieme, accord2.getTierce());
	}

	@Test
	public void testgetQuinte() {
		assertEquals(troisieme, accord1.getQuinte());
		assertEquals(deuxieme, accord2.getQuinte());
	}

	@Test
	public void testsetQuinte() {
		accord1.setQuinte(deuxieme);
		accord2.setQuinte(troisieme);

		assertEquals(deuxieme, accord1.getQuinte());
		assertEquals(troisieme, accord2.getQuinte());
	}
}
