package harmonie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

public class ParcoursGrapheTest extends ParcoursGraphe{

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
	public void testgetPath (){
		
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
		
		listeJeux listeJeu = new listeJeux(jeu,1,Accord.I);
		listeDesJeux.add(listeJeu);		
		assertEquals(listeDesJeux,copieListeJeux(listeDesJeux));
	
		listeJeu.setJeu(jeu);
		listeDesJeux.add(listeJeu);
		assertEquals(listeDesJeux,copieListeJeux(listeDesJeux));
	}
	
	@Test
	public void testrechercheChemin () {
		
	}
	
	@Test
	public void testreche (){
		
	}

}
