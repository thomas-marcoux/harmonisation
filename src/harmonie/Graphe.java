package harmonie;

import java.util.ArrayList;
import java.util.Iterator;

public class Graphe {

	/**
	 * Constants publics
	 **/
	private ArrayList<Graphe> adjacents;
	private ArrayList<Graphe> pere;
	private int etat;
	private int valeur;
	private static boolean pereExiste;
	private static final int NONATTEINTS = 0, ATTEINTS = 1;

	/**
	 * Constructors
	 */
	public Graphe(int val) {
		valeur = val;
		etat = NONATTEINTS;
		adjacents = new ArrayList<Graphe>();
		pere = new ArrayList<Graphe>();
	}

	/**
	 * Graphe's methods
	 */
	public static Graphe[][] reset(int[][] listeAccords,
			ArrayList<Integer>[][] listeSuivant) {
		Graphe listeAccords_Graphe[][] = new Graphe[16][4];

		// Reset des Sommets "Graphe" en créant un graphe avec chaque accords
		// pour sommets
		for (int i = 0; i < listeAccords.length; i++) {
			for (int j = 0; j < listeAccords[i].length; j++) {
				listeAccords_Graphe[i][j] = new Graphe(listeAccords[i][j]);
			}
		}

		// Mise à jours des listes d'adjacences de chaque sommet "Graphe"
		for (int i = 0; i < listeSuivant.length; i++) {
			for (int j = 0; j < listeSuivant[i].length; j++) {
				Iterator<Integer> it = listeSuivant[i][j].iterator();
				while (it.hasNext()) {
					Integer t = it.next();
					Graphe v = new Graphe(t);
					listeAccords_Graphe[i][j].setAdjacents(v);
				}
			}
		}
		return listeAccords_Graphe;
	}

	public static void visiter(Graphe source, int compt, Graphe[][] listeAccords) {
		pereExiste = false;
		source.etat = ATTEINTS;

		// On parcours la liste des sommets adjacents du sommet
		Iterator<Graphe> it = source.getAdjacents().iterator();
		while (it.hasNext()) {
			Graphe suiv = it.next();
			// On parcours la liste de la colonne suivante pour savoir s'il y a
			// un sommet égale à un sommet suivant
			for (int i = 0; i < listeAccords[0].length; i++) {
				if ((suiv.getValeur() == listeAccords[compt][i].getValeur())
						&& (suiv.getEtat() == NONATTEINTS)) {

					// On parcours la liste des pères de source pour savoir s'il
					// y a
					// un sommet déjà présent en tant que père
					Iterator<Graphe> verif = listeAccords[compt][i].getPere()
							.iterator();
					while (verif.hasNext()) {
						Graphe seek = verif.next();
						if (seek.getValeur() == source.getValeur()) {
							pereExiste = true;
						}
					}
					// S'il n'y est pas alors on l'ajoute
					if (pereExiste == false) {
						listeAccords[compt][i].setPere(source);
					}
					visiter(listeAccords[compt][i], compt + 1, listeAccords);
				}
			}
		}
	}

	public static void getPath(Graphe source, ArrayList<Graphe> chemin,
			Graphe[][] listeAccords, ArrayList<ArrayList<Graphe>> listeHarmonies) {

		Iterator<Graphe> it = source.getPere().iterator();
		while (it.hasNext()) {
			Graphe suiv = it.next();
		
				chemin.add(suiv);

				getPath(suiv, chemin, listeAccords, listeHarmonies);
			
			listeHarmonies.add(chemin);
		}
		/*
		 * for (int i = 0; i < listeAccords[compt - 1].length; i++) {
		 * 
		 * if (suiv.getValeur() == listeAccords[compt - 1][i].getValeur()) {
		 * 
		 * if (chemin.size() != 15) { chemin.add(listeAccords[compt - 1][i]);
		 * 
		 * getPath(listeAccords[compt - 1][i], chemin, compt - 1, listeAccords,
		 * listeHarmonies); } else {
		 * 
		 * listeHarmonies.add(chemin); }
		 * 
		 * } }
		 */

	}

	/**
	 * Graphe Algorithm
	 */
	public static Graphe[][] parcoursGraphe(int[][] listeAccords_int,
			ArrayList<Integer>[][] listeSuivant_int) {

		Graphe listeAccords_Graphe[][] = Graphe.reset(listeAccords_int,
				listeSuivant_int);
		// On parcours la première colone pour lancer la méthode visiter sur ces
		// 4 sommets
		for (int k = 0; k < listeAccords_Graphe[0].length; k++) {
			if (listeAccords_Graphe[0][k].getEtat() == NONATTEINTS) {
				visiter(listeAccords_Graphe[0][k], 0 + 1, listeAccords_Graphe);
			}
		}
		// On retourne les listes de tous les chemins possibles
		return listeAccords_Graphe;
	}

	public static void parcoursPaths(Graphe[][] listeAccords_Graphe,
			ArrayList<ArrayList<Graphe>> listeHarmonies) {

		ArrayList<Graphe> chemin = new ArrayList<Graphe>();

		// for (int k = 0; k < listeAccords_Graphe[14].length; k++) {
		System.out.println("\n\n"+ listeAccords_Graphe[14][0].getValeur());
		getPath(listeAccords_Graphe[14][0], chemin, listeAccords_Graphe,
				listeHarmonies);
		// }
	}

	/**
	 * Getter and Setters publics
	 **/
	public ArrayList<Graphe> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(Graphe adjacents) {
		this.adjacents.add(adjacents);
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public ArrayList<Graphe> getPere() {
		return pere;
	}

	public void setPere(Graphe pere) {
		this.pere.add(pere);
	}

	public int getValeur() {
		return valeur;
	}
}