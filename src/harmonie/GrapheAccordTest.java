package harmonie;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class GrapheAccordTest extends GrapheAccord {

	@Test
	public void testinitialisationDuGrapheEtChoixDuK() {

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
	public void testtrouverAccord() {
		LinkedList<Accord> AccDo,AccFa,AccSi;
		AccDo = new LinkedList<Accord>();
		AccFa = new LinkedList<Accord>();
		AccSi = new LinkedList<Accord>();
		
		AccDo.add(I);
		AccDo.add(IV);
		AccDo.add(IVb);
		AccDo.add(VI);
		
		AccFa.add(II);
		AccFa.add(IV);
		AccFa.add(IVb);
		AccFa.add(VII);
		
		AccSi.add(III);
		AccSi.add(V);
		AccSi.add(VII);
		
		assertEquals(AccDo,trouverAccord(DO,1));
		assertEquals(AccFa,trouverAccord(FA,1));
		assertEquals(AccSi,trouverAccord(SI,1));
	}
	
	
/*	@Test
	public void testlisteJeuxNote() {
		LinkedList<Accord> listeAccord = new LinkedList<Accord> ();
		LinkedList<Note> listeNote = new LinkedList<Note>();
		
		listeNote = listeJeuxNote(listeAccord,DO,21,1,null);
		assertNotNull(listeNote);
		
	}
	
	
	@Test
	public void testtrouverTableauJeux() {
		LinkedList<Note> listeNote = new LinkedList<Note>();
		listeNote.add(DO);
		listeNote.add(RE);
		listeNote.add(MI);
		listeNote.add(FA);
		listeNote.add(SOL);
		listeNote.add(LA);
		listeNote.add(SI);
		
		int [][] tableau = trouverTableauJeux(listeNote,21,1,null,I);
	
		assertNotNull(tableau[0][0]);
	}
*/	
	@Test
	public void testregleInterval() {
		assertEquals(true,regleInterval(11,2));
		assertEquals(true,regleInterval(15,2));
		assertEquals(true,regleInterval(22,2));
		assertEquals(false,regleInterval(10,2));
		assertEquals(false,regleInterval(23,2));
		
		assertEquals(true,regleInterval(7,3));
		assertEquals(true,regleInterval(19,3));
		assertEquals(true,regleInterval(10,3));
		assertEquals(false,regleInterval(6,3));
		assertEquals(false,regleInterval(20,3));
		
		assertEquals(true,regleInterval(3,4));
		assertEquals(true,regleInterval(15,4));
		assertEquals(true,regleInterval(10,4));
		assertEquals(false,regleInterval(2,4));
		assertEquals(false,regleInterval(16,4));
	}
	
	@Test
	public void testparcoursTableauJeu() {
		int [][] tabJeu = new int [16][4];
		LinkedList<listeJeux> liste = new LinkedList<listeJeux>();
		LinkedList<Integer> chemin = new LinkedList<Integer>();
		
		for(int i =0; i <tabJeu.length; i++){
			for (int j = 0; j < tabJeu[i].length; j++){
				tabJeu[i][j] = i+j;
			}
		}
		
		getPath(tabJeu,tabJeu[0][0],chemin,0,liste,null);
		assertFalse(chemin.isEmpty());	
	}
	
	@Test
	public void testcopieChemin() {
		LinkedList<Integer> chemin1, chemin2;
		chemin1 = new LinkedList<Integer>();
		chemin2 = new LinkedList<Integer>();
		
		chemin1.add(1);
		chemin1.add(3);
		chemin1.add(5);
		chemin1.add(7);
		chemin1.add(9);
		
		chemin2.add(2);
		chemin2.add(4);
		chemin2.add(6);
		chemin2.add(8);
		chemin2.add(10);
		
		assertEquals(chemin1,copieChemin(chemin1));
		assertEquals(chemin2,copieChemin(chemin2));
		
	}
	
	@Test
	public void testcopieListeJeux () {
		LinkedList<listeJeux> listeDesJeux = new LinkedList<listeJeux>();
		LinkedList<Integer> jeu = new LinkedList<Integer>();
		jeu.add(2);
		jeu.add(4);
		jeu.add(6);
		jeu.add(8);
		jeu.add(10);
		
		listeJeux listeJeu = new listeJeux(jeu,1,I);
		listeDesJeux.add(listeJeu);		
		assertEquals(listeDesJeux,copieListeJeux(listeDesJeux));
	
		listeJeu.setJeu(jeu);
		listeDesJeux.add(listeJeu);
		assertEquals(listeDesJeux,copieListeJeux(listeDesJeux));
	}
}
