package harmonie;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

public class listeJeuxTest extends listeJeux {

	private static Note DO = new Note("DO", 0, 7, 14, 21);
	private static Note RE = new Note("RE", 1, 8, 15, 22);
	private static Note MI = new Note("MI", 2, 9, 16, 23);
	private static Note FA = new Note("FA", 3, 10, 17, 24);
	private static Note SOL = new Note("SOL", 4, 11, 18, 25);
	private static Note LA = new Note("LA", 5, 12, 19, 26);
	private static Note SI = new Note("SI", 6, 13, 20, 27);

	private Accord I = new Accord("DO", DO, MI, SOL);
	private Accord II = new Accord("RE", RE, FA, LA);
	private Accord III = new Accord("MI", MI, SOL, SI);
	private Accord IV = new Accord("FA", FA, LA, DO);
	private Accord IVb = new Accord("FAb", FA, LA, DO);
	private Accord V = new Accord("SOL", SOL, SI, RE);
	private Accord VI = new Accord("LA", LA, DO, MI);
	private Accord VII = new Accord("SI", SI, RE, FA);

	public static LinkedList<Note> listeGeneralNotes = new LinkedList<Note>(
			Arrays.asList(DO, RE, MI, FA, SOL, LA, SI, DO));
	
	private LinkedList<Integer> jeu = new LinkedList<Integer>();
	private LinkedList<Integer> jeu2 = new LinkedList<Integer>();
	private LinkedList<Integer> jeu3 = new LinkedList<Integer>();
	private LinkedList<Integer> jeu4 = new LinkedList<Integer>();

	private listeJeux listeJeuI = new listeJeux(jeu, 1, I);
	private listeJeux listeJeuII = new listeJeux(jeu, 2, II);
	private listeJeux listeJeuIII = new listeJeux(jeu2, 3, III);
	private listeJeux listeJeuIV = new listeJeux(jeu, 4, IV);
	private listeJeux listeJeuIVb = new listeJeux(jeu4, 5, IVb);
	private listeJeux listeJeuV = new listeJeux(jeu, 6, V);
	private listeJeux listeJeuVI = new listeJeux(jeu3, 7, VI);
	private listeJeux listeJeuVII = new listeJeux(jeu2, 8, VII);

	@Test
	public void testlisteJeuxSimple() {
		listeJeux listeJeuSimple = new listeJeux();
		assertNotNull(listeJeuSimple);
	}

	@Test
	public void testlisteJeux() {
		assertNotNull(listeJeuI);
		assertNotNull(listeJeuII);
		assertNotNull(listeJeuIII);
		assertNotNull(listeJeuIV);
		assertNotNull(listeJeuIVb);
		assertNotNull(listeJeuV);
		assertNotNull(listeJeuVI);
		assertNotNull(listeJeuVII);
	}

	@Test
	public void testgetPlusBeauPrec() {
		assertEquals(null, listeJeuI.getPlusBeauPrec());
		assertEquals(null, listeJeuII.getPlusBeauPrec());
		assertEquals(null, listeJeuIII.getPlusBeauPrec());
		assertEquals(null, listeJeuIV.getPlusBeauPrec());
		assertEquals(null, listeJeuIVb.getPlusBeauPrec());
		assertEquals(null, listeJeuV.getPlusBeauPrec());
		assertEquals(null, listeJeuVI.getPlusBeauPrec());
		assertEquals(null, listeJeuVII.getPlusBeauPrec());
	}

	@Test
	public void testsetPlusBeauPrec() {
		listeJeuI.setPlusBeauPrec(listeJeuII);
		listeJeuII.setPlusBeauPrec(listeJeuI);
		listeJeuV.setPlusBeauPrec(listeJeuIV);
		listeJeuVI.setPlusBeauPrec(listeJeuVII);

		assertEquals(listeJeuII, listeJeuI.getPlusBeauPrec());
		assertEquals(listeJeuI, listeJeuII.getPlusBeauPrec());
		assertEquals(listeJeuIV, listeJeuV.getPlusBeauPrec());
		assertEquals(listeJeuVII, listeJeuVI.getPlusBeauPrec());
	}

	@Test
	public void testgetValeurfinale() {
		assertEquals(0, listeJeuI.getValeurfinale());
		assertEquals(0, listeJeuII.getValeurfinale());
		assertEquals(0, listeJeuVI.getValeurfinale());
		assertEquals(0, listeJeuIVb.getValeurfinale());
	}

	@Test
	public void testsetValeurfinale() {
		listeJeuI.setValeurfinale(100);
		listeJeuII.setValeurfinale(-10);
		listeJeuVII.setValeurfinale(50);
		listeJeuIV.setValeurfinale(-5);
		
		assertEquals(100, listeJeuI.getValeurfinale());
		assertEquals(-10, listeJeuII.getValeurfinale());
		assertEquals(50, listeJeuVII.getValeurfinale());
		assertEquals(-5, listeJeuIV.getValeurfinale());
	}

	@Test
	public void testregleAccord() {
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuI, null));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuI, null));
		assertEquals(true, listeJeuII.regleAccord(listeJeuI, null));
		assertEquals(true, listeJeuVII.regleAccord(listeJeuI, null));

		assertEquals(true, listeJeuII.regleAccord(listeJeuII, null));
		assertEquals(true, listeJeuV.regleAccord(listeJeuII, null));
		assertEquals(true, listeJeuVII.regleAccord(listeJeuII, null));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuII, null));

		assertEquals(false, listeJeuI.regleAccord(listeJeuIII, null));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuIII, null));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuIII, null));
		assertEquals(true, listeJeuV.regleAccord(listeJeuIII, null));

		assertEquals(false, listeJeuIVb.regleAccord(listeJeuIV, null));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuIV, null));
		assertEquals(true, listeJeuV.regleAccord(listeJeuIV, null));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuIV, null));

		assertEquals(true, listeJeuI.regleAccord(listeJeuIVb, null));
		assertEquals(true, listeJeuIVb.regleAccord(listeJeuIVb, null));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuIVb, null));
		assertEquals(false, listeJeuVI.regleAccord(listeJeuIVb, null));

		assertEquals(true, listeJeuI.regleAccord(listeJeuV, null));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuV, null));
		assertEquals(false, listeJeuII.regleAccord(listeJeuV, null));
		assertEquals(false, listeJeuVII.regleAccord(listeJeuV, null));

		assertEquals(true, listeJeuII.regleAccord(listeJeuVI, null));
		assertEquals(true, listeJeuVI.regleAccord(listeJeuVI, null));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuVI, null));
		assertEquals(false, listeJeuI.regleAccord(listeJeuVI, null));

		assertEquals(true, listeJeuI.regleAccord(listeJeuVII, null));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuVII, null));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuVII, null));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuVII, null));
	}

	@Test
	public void testregleBeauteUne() {
		int Sop = 20;
		int Alto = 20;
		int Tenor = 5;
		
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		listeJeuI.setJeu(jeu);
		assertEquals(5 , listeJeuI.regleBeauteUne());
		
		
		Alto = 20;
		Tenor =19; 
		jeu.clear();
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		listeJeuII.setJeu(jeu);
		assertEquals(19,listeJeuII.regleBeauteUne());
	}

	@Test
	public void testregleBeauteDeux() {
		int Sop = 20; 
		int Alto = 19;
		int Tenor = 17;
		
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		listeJeuI.setJeu(jeu);
		assertEquals(20, listeJeuI.regleBeauteDeux(listeJeuI));
		
		
		Tenor = 19;
		jeu2.add(Sop);
		jeu2.add(Alto);
		jeu2.add(Tenor);
		listeJeuII.setJeu(jeu2);
		assertEquals(18,listeJeuI.regleBeauteDeux(listeJeuII));
	}

	@Test
	public void testRegleBeauteTrois() {
		int Sop = 20;
		int Alto = 19;
		int Tenor = 17;
		int Basse = 13;
		
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		jeu.add(Basse);
		listeJeuI.setJeu(jeu);
		assertEquals(0, listeJeuI.regleBeauteTrois(listeJeuI));
		
		Basse = 15;
		jeu2.add(Sop);
		jeu2.add(Alto);
		jeu2.add(Tenor);
		jeu2.add(Basse);
		listeJeuII.setJeu(jeu2);
		assertEquals(2, listeJeuI.regleBeauteTrois(listeJeuII));
	}

	@Test
	public void testBeauteQuatre() {
		int Sop = 20;
		int Alto = 19;
		int Tenor = 17;
		int Basse = 13;
		
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		jeu.add(Basse);
		listeJeuI.setJeu(jeu);
		assertEquals(20, listeJeuI.regleBeauteQuatre(listeJeuI));
		
		Basse=15;
		Tenor=19;
		jeu2.add(Sop);
		jeu2.add(Alto);
		jeu2.add(Tenor);
		jeu2.add(Basse);
		listeJeuII.setJeu(jeu2);
		assertEquals(20, listeJeuI.regleBeauteQuatre(listeJeuII));
	
	}

	@Test
	public void testregleDifferenceDeuxNotes() {
		int Sop = 20;
		int Alto = 18;
		int Tenor = 13;
		int Basse = 10;
		
		jeu.add(Sop);
		jeu.add(Alto);
		jeu.add(Tenor);
		jeu.add(Basse);
		listeJeuI.setJeu(jeu);
		
		assertEquals(true, listeJeuI.regleDifferenceDeuxNotes(listeJeuI,listeGeneralNotes));

		Sop = 26;
		Alto = 24;
		Tenor = 20;
		Basse = 10;
		
		jeu2.add(Sop);
		jeu2.add(Alto);
		jeu2.add(Tenor);
		jeu2.add(Basse);
		listeJeuII.setJeu(jeu2);
		
		assertEquals(false, listeJeuI.regleDifferenceDeuxNotes(listeJeuII,listeGeneralNotes));
	}

	@Test
	public void testmemeAccord() {
		assertEquals(false, listeJeuI.memeAccord(21,20,null,listeGeneralNotes));
		assertEquals(true, listeJeuII.memeAccord(0,1,null,listeGeneralNotes));
		assertEquals(false, listeJeuV.memeAccord(4,21,null,listeGeneralNotes));
		assertEquals(true, listeJeuVII.memeAccord(11,25,null,listeGeneralNotes));
		assertEquals(true, listeJeuI.memeAccord(21,21,null,listeGeneralNotes));
	}

	@Test
	public void testtrouverNote() {
		assertEquals(DO, trouverNote(0, listeGeneralNotes));
		assertEquals(RE, trouverNote(8, listeGeneralNotes));
		assertEquals(MI, trouverNote(16, listeGeneralNotes));
		assertEquals(FA, trouverNote(24, listeGeneralNotes));
		assertEquals(SOL, trouverNote(18, listeGeneralNotes));
		assertEquals(LA, trouverNote(12, listeGeneralNotes));
		assertEquals(SI, trouverNote(6, listeGeneralNotes));
		
	}

	@Test
	public void testdifference() {
		assertEquals(true, difference(10, 16));
		assertEquals(true, difference(16, 10));
		assertEquals(false, difference(20, 13));
		assertEquals(false, difference(13, 20));
	}

	@Test
	public void testdifferenceRegle() {
		assertEquals(10, differenceRegle(20, 10));
		assertEquals(10, differenceRegle(10, 20));
		assertEquals(0, differenceRegle(10, 10));
	}

	@Test
	public void testgetJeu() {
		assertEquals(jeu, listeJeuI.getJeu());
		assertEquals(jeu2, listeJeuII.getJeu());
		assertEquals(jeu3, listeJeuIII.getJeu());
		assertEquals(jeu4, listeJeuIV.getJeu());
	}

	@Test
	public void testsetJeu() {
		listeJeuI.setJeu(jeu4);
		listeJeuII.setJeu(jeu3);
		listeJeuIII.setJeu(jeu2);
		listeJeuIV.setJeu(jeu);

		assertEquals(jeu4, listeJeuI.getJeu());
		assertEquals(jeu3, listeJeuII.getJeu());
		assertEquals(jeu2, listeJeuIII.getJeu());
		assertEquals(jeu, listeJeuI.getJeu());
	}

	@Test
	public void testgetSuivant() {
		HashMap<listeJeux, Integer> Hm = new HashMap<listeJeux, Integer>();
		assertEquals(Hm, listeJeuI.getSuivants());
		assertEquals(Hm, listeJeuII.getSuivants());
		assertEquals(Hm, listeJeuV.getSuivants());
		assertEquals(Hm, listeJeuVII.getSuivants());
	}

	@Test
	public void testsetSuivant() {
		HashMap<listeJeux, Integer> Hm = new HashMap<listeJeux, Integer>();

		Hm.put(listeJeuI, 1);
		listeJeuI.setSuivants(Hm);
		assertEquals(Hm, listeJeuI.getSuivants());

		Hm.put(listeJeuII, 9);
		Hm.put(listeJeuIV, 6);
		listeJeuII.setSuivants(Hm);
		assertEquals(Hm, listeJeuII.getSuivants());

		Hm.put(listeJeuII, 9);
		Hm.remove(listeJeuII);
		listeJeuIII.setSuivants(Hm);
		assertEquals(Hm, listeJeuIII.getSuivants());

		Hm.clear();
		listeJeuIII.setSuivants(Hm);
		assertEquals(Hm, listeJeuV.getSuivants());
	}

	@Test
	public void tesgetPere() {
		HashMap<listeJeux, Integer> Hm = new HashMap<listeJeux, Integer>();

		assertEquals(Hm, listeJeuI.getPeres());
		assertEquals(Hm, listeJeuII.getPeres());
		assertEquals(Hm, listeJeuV.getPeres());
		assertEquals(Hm, listeJeuVII.getPeres());
	}

	@Test
	public void testsetPeres() {
		HashMap<listeJeux, Integer> Hm = new HashMap<listeJeux, Integer>();

		Hm.put(listeJeuI, 1);
		listeJeuI.setPeres(Hm);
		assertEquals(Hm, listeJeuI.getPeres());

		Hm.put(listeJeuII, 9);
		Hm.put(listeJeuIV, 6);
		listeJeuII.setPeres(Hm);
		assertEquals(Hm, listeJeuII.getPeres());

		Hm.put(listeJeuII, 9);
		Hm.remove(listeJeuII);
		listeJeuIII.setPeres(Hm);
		assertEquals(Hm, listeJeuIII.getPeres());

		Hm.clear();
		listeJeuIII.setPeres(Hm);
		assertEquals(Hm, listeJeuV.getPeres());
	}

	@Test
	public void testgetIndice() {
		assertEquals(1, listeJeuI.getIndice());
		assertEquals(2, listeJeuII.getIndice());
		assertEquals(3, listeJeuIII.getIndice());
		assertEquals(4, listeJeuIV.getIndice());
		assertEquals(5, listeJeuIVb.getIndice());
		assertEquals(6, listeJeuV.getIndice());
		assertEquals(7, listeJeuVI.getIndice());
		assertEquals(8, listeJeuVII.getIndice());
	}
	
	@Test
	public void testsetIndice() {
		listeJeuI.setIndice(8);
		listeJeuII.setIndice(7);
		listeJeuIII.setIndice(6);
		listeJeuIV.setIndice(5);
		listeJeuIVb.setIndice(4);
		listeJeuV.setIndice(3);
		listeJeuVI.setIndice(2);
		listeJeuVII.setIndice(1);
		
		assertEquals(8, listeJeuI.getIndice());
		assertEquals(7, listeJeuII.getIndice());
		assertEquals(6, listeJeuIII.getIndice());
		assertEquals(5, listeJeuIV.getIndice());
		assertEquals(4, listeJeuIVb.getIndice());
		assertEquals(3, listeJeuV.getIndice());
		assertEquals(2, listeJeuVI.getIndice());
		assertEquals(1, listeJeuVII.getIndice());
	}
	
	@Test
	public void testgetAccord() {
		assertEquals(I, listeJeuI.getAccord());
		assertEquals(II, listeJeuII.getAccord());
		assertEquals(III, listeJeuIII.getAccord());
		assertEquals(IV, listeJeuIV.getAccord());
		assertEquals(IVb, listeJeuIVb.getAccord());
		assertEquals(V, listeJeuV.getAccord());
		assertEquals(VI, listeJeuVI.getAccord());
		assertEquals(VII, listeJeuVII.getAccord());
	}
	
	@Test
	public void testsetAccord() {
		listeJeuI.setAccord(VII);
		listeJeuII.setAccord(VI);
		listeJeuIII.setAccord(V);
		listeJeuIV.setAccord(IVb);
		listeJeuIVb.setAccord(IV);
		listeJeuV.setAccord(III);
		listeJeuVI.setAccord(II);
		listeJeuVII.setAccord(I);
		
		assertEquals(VII, listeJeuI.getAccord());
		assertEquals(VI, listeJeuII.getAccord());
		assertEquals(V, listeJeuIII.getAccord());
		assertEquals(IVb, listeJeuIV.getAccord());
		assertEquals(IV, listeJeuIVb.getAccord());
		assertEquals(III, listeJeuV.getAccord());
		assertEquals(II, listeJeuVI.getAccord());
		assertEquals(I, listeJeuVII.getAccord());
	}

	@Test
	public void testgetValeurBeaute1() {
		assertEquals(0, listeJeuI.getValeurBeaute1());
		assertEquals(0, listeJeuII.getValeurBeaute1());
		assertEquals(0, listeJeuVI.getValeurBeaute1());
		assertEquals(0, listeJeuIVb.getValeurBeaute1());
	}

	@Test
	public void testsetValeurBeaute1() {
		listeJeuI.setValeurBeaute1(100);
		listeJeuII.setValeurBeaute1(-10);
		listeJeuVII.setValeurBeaute1(50);
		listeJeuIV.setValeurBeaute1(-5);
		
		assertEquals(100, listeJeuI.getValeurBeaute1());
		assertEquals(-10, listeJeuII.getValeurBeaute1());
		assertEquals(50, listeJeuVII.getValeurBeaute1());
		assertEquals(-5, listeJeuIV.getValeurBeaute1());
	}
}
