package harmonie;

import java.util.ArrayList;
import java.util.Iterator;

public class Graphe {

	/**
	 * Constants publics
	 **/
	private ArrayList<Graphe> adjacents;
	private int etat;
	private Graphe pere;
	private int valeur;
	private static final int NONATTEINTS = 0, ATTEINTS = 1, TRAITE = 2;
	private static int pathNumber;

	/**
	 * Constructors
	 */
	public Graphe(int val) {
		valeur = val;
		adjacents = new ArrayList<Graphe>();
		pere = null;
		etat = NONATTEINTS;
	}

	/**
	 * Graphe's methods
	 */
	public static Graphe[][] reset(int[][] listeAccords,
			ArrayList<Integer>[][] listeSuivant) {
		Graphe listeAccords_Graphe[][] = new Graphe[16][4];

		// Reset des Sommets "Graphe" en créant un graphe avec chaque accords pour sommets
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

	public static void visiter(Graphe source, int compt,
			Graphe[][] listeAccords,
			ArrayList<ArrayList<Integer>> listeHarmonies) {
		source.etat = ATTEINTS;
		
		// On parcours la liste des sommets adjacents du sommet
		Iterator<Graphe> it = source.getAdjacents().iterator();
		while (it.hasNext()) {
			Graphe suiv = it.next();
			// On parcours la liste de la colonne suivante pour savoir s'il y a un sommet égale à un sommet suivant
			for (int i = 0; i < listeAccords[0].length; i++) {
				if ((suiv.getValeur() == listeAccords[compt][i].getValeur())
						&& (suiv.getEtat() == NONATTEINTS)) {
					listeAccords[compt][i].setPere(source);
					visiter(listeAccords[compt][i], compt + 1, listeAccords,
							listeHarmonies);
				}
			}
		}
	}

	public static int pathNumber() {
		return pathNumber;
	}

	public static ArrayList<ArrayList<Integer>> retournerListe(Graphe suiv,
			Graphe[][] listeAccords,
			ArrayList<ArrayList<Integer>> listeHarmonies) {
		
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		while (suiv != null) {
			chemin.add(suiv.getValeur());
			suiv = suiv.getPere();
		}
		listeHarmonies.add(chemin);
		return listeHarmonies;
	}

	/**
	 * Graphe Algorithm
	 */
	public static Graphe[][] parcoursGraphe(int[][] listeAccords_int,
			ArrayList<Integer>[][] listeSuivant_int,
			ArrayList<ArrayList<Integer>> listeHarmonies) {

		Graphe listeAccords_Graphe[][] = Graphe.reset(listeAccords_int,
				listeSuivant_int);
		// On parcours la première colone pour lancer la méthode visiter sur ces 4 sommets
		for (int k = 0; k < listeAccords_Graphe[0].length; k++) {
			if (listeAccords_Graphe[0][k].getEtat() == NONATTEINTS) {
				visiter(listeAccords_Graphe[0][k], 0 + 1, listeAccords_Graphe,
						listeHarmonies);
			}
		}
		// On retourne les listes de tous les chemins possibles
		return listeAccords_Graphe;
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

	public Graphe getPere() {
		return pere;
	}

	public void setPere(Graphe pere) {
		this.pere = pere;
	}

	public int getValeur() {
		return valeur;
	}
}