package harmonie;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

public class listeJeuxTest extends listeJeux {
	
	private LinkedList<Integer> jeu = new LinkedList<Integer>();
	private LinkedList<Integer> jeu2 = new LinkedList<Integer>();
	private LinkedList<Integer> jeu3 = new LinkedList<Integer>();
	private LinkedList<Integer> jeu4 = new LinkedList<Integer>();

	private listeJeux listeJeuI = new listeJeux(jeu, 1, Accord.I);
	private listeJeux listeJeuII = new listeJeux(jeu, 2, Accord.II);
	private listeJeux listeJeuIII = new listeJeux(jeu2, 3, Accord.III);
	private listeJeux listeJeuIV = new listeJeux(jeu, 4, Accord.IV);
	private listeJeux listeJeuIVb = new listeJeux(jeu4, 5, Accord.IVb);
	private listeJeux listeJeuV = new listeJeux(jeu, 6, Accord.V);
	private listeJeux listeJeuVI = new listeJeux(jeu3, 7, Accord.VI);
	private listeJeux listeJeuVII = new listeJeux(jeu2, 8, Accord.VII);

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
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuI));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuI));
		assertEquals(true, listeJeuII.regleAccord(listeJeuI));
		assertEquals(true, listeJeuVII.regleAccord(listeJeuI));

		assertEquals(true, listeJeuII.regleAccord(listeJeuII));
		assertEquals(true, listeJeuV.regleAccord(listeJeuII));
		assertEquals(true, listeJeuVII.regleAccord(listeJeuII));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuII));

		assertEquals(false, listeJeuI.regleAccord(listeJeuIII));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuIII));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuIII));
		assertEquals(true, listeJeuV.regleAccord(listeJeuIII));

		assertEquals(false, listeJeuIVb.regleAccord(listeJeuIV));
		assertEquals(true, listeJeuIV.regleAccord(listeJeuIV));
		assertEquals(true, listeJeuV.regleAccord(listeJeuIV));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuIV));

		assertEquals(true, listeJeuI.regleAccord(listeJeuIVb));
		assertEquals(true, listeJeuIVb.regleAccord(listeJeuIVb));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuIVb));
		assertEquals(false, listeJeuVI.regleAccord(listeJeuIVb));

		assertEquals(true, listeJeuI.regleAccord(listeJeuV));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuV));
		assertEquals(false, listeJeuII.regleAccord(listeJeuV));
		assertEquals(false, listeJeuVII.regleAccord(listeJeuV));

		assertEquals(true, listeJeuII.regleAccord(listeJeuVI));
		assertEquals(true, listeJeuVI.regleAccord(listeJeuVI));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuVI));
		assertEquals(false, listeJeuI.regleAccord(listeJeuVI));

		assertEquals(true, listeJeuI.regleAccord(listeJeuVII));
		assertEquals(true, listeJeuIII.regleAccord(listeJeuVII));
		assertEquals(false, listeJeuIV.regleAccord(listeJeuVII));
		assertEquals(false, listeJeuIVb.regleAccord(listeJeuVII));
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
		
		assertEquals(true, listeJeuI.regleDifferenceDeuxNotes(listeJeuI));

		Sop = 26;
		Alto = 24;
		Tenor = 20;
		Basse = 10;
		
		jeu2.add(Sop);
		jeu2.add(Alto);
		jeu2.add(Tenor);
		jeu2.add(Basse);
		listeJeuII.setJeu(jeu2);
		
		assertEquals(false, listeJeuI.regleDifferenceDeuxNotes(listeJeuII));
	}

	@Test
	public void testmemeAccord() {
		assertEquals(false, listeJeuI.memeAccord(21,20,null));
		assertEquals(true, listeJeuII.memeAccord(0,1,null));
		assertEquals(false, listeJeuV.memeAccord(4,21,null));
		assertEquals(true, listeJeuVII.memeAccord(11,25,null));
		assertEquals(true, listeJeuI.memeAccord(21,21,null));
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
		assertEquals(Accord.I, listeJeuI.getAccord());
		assertEquals(Accord.II, listeJeuII.getAccord());
		assertEquals(Accord.III, listeJeuIII.getAccord());
		assertEquals(Accord.IV, listeJeuIV.getAccord());
		assertEquals(Accord.IVb, listeJeuIVb.getAccord());
		assertEquals(Accord.V, listeJeuV.getAccord());
		assertEquals(Accord.VI, listeJeuVI.getAccord());
		assertEquals(Accord.VII, listeJeuVII.getAccord());
	}
	
	@Test
	public void testsetAccord() {
		listeJeuI.setAccord(Accord.VII);
		listeJeuII.setAccord(Accord.VI);
		listeJeuIII.setAccord(Accord.V);
		listeJeuIV.setAccord(Accord.IVb);
		listeJeuIVb.setAccord(Accord.IV);
		listeJeuV.setAccord(Accord.III);
		listeJeuVI.setAccord(Accord.II);
		listeJeuVII.setAccord(Accord.I);
		
		assertEquals(Accord.VII, listeJeuI.getAccord());
		assertEquals(Accord.VI, listeJeuII.getAccord());
		assertEquals(Accord.V, listeJeuIII.getAccord());
		assertEquals(Accord.IVb, listeJeuIV.getAccord());
		assertEquals(Accord.IV, listeJeuIVb.getAccord());
		assertEquals(Accord.III, listeJeuV.getAccord());
		assertEquals(Accord.II, listeJeuVI.getAccord());
		assertEquals(Accord.I, listeJeuVII.getAccord());
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
