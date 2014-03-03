package harmonie;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Harmonisations {

	/**
	 * Constants publics
	 **/
	public static final int DO1 = 0, RE1 = 1, MI1 = 2, FA1 = 3, SOL1 = 4,
			LA1 = 5, SI1 = 6, DO2 = 7, RE2 = 8, MI2 = 9, FA2 = 10, SOL2 = 11,
			LA2 = 12, SI2 = 13, DO3 = 14, RE3 = 15, MI3 = 16, FA3 = 17,
			SOL3 = 18, LA3 = 19, SI3 = 20, DO4 = 21, RE4 = 22, MI4 = 23,
			FA4 = 24, SOL4 = 25, LA4 = 26, SI4 = 27,

			REPEAT = 99, PAUSE = 50;

	public static final Graphe I = new Graphe(1), II = new Graphe(2),
			III = new Graphe(3), IV = new Graphe(4), IVb = new Graphe(42),
			V = new Graphe(5), VI = new Graphe(6), VII = new Graphe(7),
			NONE = new Graphe(-1);

	public static ArrayList<ArrayList<Graphe>> listeHarmonies = new ArrayList<ArrayList<Graphe>>();

	/**
	 * Foremost Algorithm
	 */
	public static void main(String[] args) {
		// Etape 1 : Coder Soprano
		int[] soprano = new int[16];
		soprano[0] = DO4;
		soprano[1] = DO4;
		soprano[2] = DO4;
		soprano[3] = RE4;
		soprano[4] = MI4;
		soprano[5] = REPEAT;
		soprano[6] = RE4;
		soprano[7] = REPEAT;
		soprano[8] = DO4;
		soprano[9] = MI4;
		soprano[10] = RE4;
		soprano[11] = RE4;
		soprano[12] = DO4;
		soprano[13] = REPEAT;
		soprano[14] = REPEAT;
		soprano[15] = PAUSE;
		// affichage(soprano);

		// Etape 2-3: Création d'un tableau "jeu" contenant tous les jeux de
		// notes respectant les règles d'harmonisations locales.
		int[][] jeu = remplissageJeu(soprano);
		affichageDouble(jeu);

		// Etape 4-5: Création d'un tableau de listes "suivant" contenant chaque
		// accord suivant
		// possible pour chaque accord contenu dans le tableau "jeu".
		ArrayList<Integer>[][] suivant = remplissageSuivant(jeu);
		// affichageSuivant(suivant, jeu);

		// Etape 6: Elimination dans les listes "suivant",les jeux sans
		// harmonisation.
		supprimerListeSansSuivant(suivant, jeu);
		// affichageSuivant(suivant, jeu);

		// Etape 7: Production d'une liste d'harmonisation.
		listeHarmonies = new ArrayList<ArrayList<Graphe>>();
		Graphe[][] listeAccords_Graphe = Graphe.parcoursGraphe(jeu, suivant);
		affichageGraphe(listeAccords_Graphe);

		Graphe.parcoursPaths(listeAccords_Graphe, listeHarmonies);
		//affichagePaths(listeHarmonies);

		//
		// -> listeHarmonies contients toutes les listes d'harmonisations
		//
	}

	/**
	 * Tableau des accords
	 */
	public static int[][] remplissageJeu(int[] sop) {
		int[][] jeu = new int[16][4];

		for (int i = 0; i < sop.length; i++) {
			switch (sop[i]) {
			case DO1:
			case DO2:
			case DO3:
			case DO4:
				jeu[i][0] = I.getValeur();
				jeu[i][1] = II.getValeur();
				if (!(i == 0 || i == 15)) {
					jeu[i][2] = IVb.getValeur();
				} else {
					jeu[i][2] = NONE.getValeur();
				}
				jeu[i][3] = VI.getValeur();
				break;

			case RE1:
			case RE2:
			case RE3:
			case RE4:
				jeu[i][0] = II.getValeur();
				jeu[i][1] = V.getValeur();
				jeu[i][2] = VII.getValeur();
				jeu[i][3] = NONE.getValeur();
				break;

			case MI1:
			case MI2:
			case MI3:
			case MI4:
				jeu[i][0] = I.getValeur();
				jeu[i][1] = III.getValeur();
				jeu[i][2] = VI.getValeur();
				jeu[i][3] = NONE.getValeur();
				break;

			case FA1:
			case FA2:
			case FA3:
			case FA4:
				jeu[i][0] = II.getValeur();
				jeu[i][1] = IV.getValeur();
				if (!(i == 0 || i == 15)) {
					jeu[i][2] = IVb.getValeur();
				} else {
					jeu[i][2] = NONE.getValeur();
				}
				jeu[i][3] = VII.getValeur();
				break;

			case SOL1:
			case SOL2:
			case SOL3:
			case SOL4:
				jeu[i][0] = I.getValeur();
				jeu[i][1] = III.getValeur();
				jeu[i][2] = V.getValeur();
				jeu[i][3] = NONE.getValeur();
				break;

			case LA1:
			case LA2:
			case LA3:
			case LA4:
				jeu[i][0] = II.getValeur();
				jeu[i][1] = IV.getValeur();
				if (!(i == 0 || i == 15)) {
					jeu[i][2] = IVb.getValeur();
				} else {
					jeu[i][2] = NONE.getValeur();
				}
				jeu[i][3] = VI.getValeur();
				break;

			case SI1:
			case SI2:
			case SI3:
			case SI4:
				jeu[i][0] = III.getValeur();
				jeu[i][1] = V.getValeur();
				jeu[i][2] = VII.getValeur();
				jeu[i][3] = NONE.getValeur();
				break;
			case PAUSE:
				for (int k = 0; k < 4; k++) {
					jeu[i][k] = NONE.getValeur();
				}
				break;

			case REPEAT:
				jeu[i][0] = jeu[i - 1][0];
				jeu[i][1] = jeu[i - 1][1];
				jeu[i][2] = jeu[i - 1][2];
				jeu[i][3] = jeu[i - 1][3];
				break;

			}
		}
		return jeu;
	}

	/**
	 * Tableau des suivants
	 */
	public static ArrayList<Integer>[][] remplissageSuivant(int[][] jeu) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[][] suivant = new ArrayList[jeu.length][jeu[0].length];

		for (int i = 0; i < jeu.length; i++) {
			for (int j = 0; j < jeu[i].length; j++) {
				switch (jeu[i][j]) {
				case 1:
				case 4:
					suivant[i][j] = new ArrayList<Integer>();

					for (int k = 1; k < 8; k++) {
						suivant[i][j].add(k);
					}
					break;

				case 2:
					suivant[i][j] = new ArrayList<Integer>();

					suivant[i][j].add(II.getValeur());
					suivant[i][j].add(V.getValeur());
					suivant[i][j].add(VII.getValeur());
					break;

				case 3:
					suivant[i][j] = new ArrayList<Integer>();

					for (int k = 2; k < 8; k++) {
						suivant[i][j].add(k);
					}
					break;

				case 42:
					suivant[i][j] = new ArrayList<Integer>();

					suivant[i][j].add(IVb.getValeur());
					suivant[i][j].add(I.getValeur());
					break;
				case 5:
					suivant[i][j] = new ArrayList<Integer>();

					suivant[i][j].add(I.getValeur());
					suivant[i][j].add(III.getValeur());
					suivant[i][j].add(IVb.getValeur());
					suivant[i][j].add(V.getValeur());
					suivant[i][j].add(VI.getValeur());
					break;

				case 6:
					suivant[i][j] = new ArrayList<Integer>();

					suivant[i][j].add(II.getValeur());
					suivant[i][j].add(III.getValeur());
					suivant[i][j].add(IV.getValeur());
					suivant[i][j].add(V.getValeur());
					suivant[i][j].add(VI.getValeur());
					break;

				case 7:
					suivant[i][j] = new ArrayList<Integer>();

					suivant[i][j].add(I.getValeur());
					suivant[i][j].add(III.getValeur());
					suivant[i][j].add(VII.getValeur());
					break;

				case -1:
					suivant[i][j] = new ArrayList<Integer>();
					break;

				case 0:
					suivant[i][j] = suivant[i - 1][j];
					break;

				}
			}
		}
		return suivant;
	}

	/**
	 * Nettoyage des jeu sans harmonisation
	 */
	public static void supprimerListeSansSuivant(
			ArrayList<Integer>[][] suivant, int[][] jeu) {
		for (int i = jeu.length - 2; i > 0; i--) {
			for (int j = jeu[i].length - 1; j >= 0; j--) {
				// si l'accord n'appartient pas a une des liste [i-1][1,2,3,4]
				if (!(appartientListePrec(jeu[i][j], suivant, i - 1))) {
					// alors on vide la liste des suivant de cet accord
					suivant[i][j].clear();
				}
			}
		}
	}

	/**
	 * Test d'appartenance d'un accords à la liste précédente
	 */
	public static boolean appartientListePrec(int n,
			ArrayList<Integer>[][] suivant, int i) {
		boolean bool = false;
		for (int j = 0; j < suivant[i].length - 1; j++) {
			if (suivant[i][j].contains(n)) {
				return true;
			}
		}
		return bool;
	}

	/**
	 * Méthodes de test
	 **/
	public static void affichageDouble(int[][] tab) {
		System.out.println("\n\nTableau des jeux\n");
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j] + "  ");
			}
			System.out.print("\n");
		}
	}

	public static void affichage(int[] tab) {
		System.out.print("Soprano: ");
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + "  ");
		}
	}

	public static void affichageSuivant(ArrayList<Integer>[][] suiv, int[][] tab) {
		System.out.println("\n Listes des Suivants :\n");
		for (int i = 0; i < suiv.length; i++) {
			for (int j = 0; j < suiv[i].length; j++) {
				System.out.print("Accord   " + tab[i][j] + ": (" + i + "," + j
						+ ")-->");
				System.out.println(suiv[i][j].toString());
			}
			System.out.print("\n");
		}
	}

	public static void affichageGraphe(Graphe[][] listeAccords_Graphe) {
		System.out.println("\n Affichage du Graphe:\n");

		for (int j = 0; j < listeAccords_Graphe.length; j++) {
			for (int k = 0; k < listeAccords_Graphe[j].length; k++) {
				System.out.print(listeAccords_Graphe[j][k].getValeur() + " (-");

				Iterator<Graphe> verif = listeAccords_Graphe[j][k].getPere()
						.iterator();
				while (verif.hasNext()) {
					System.out.print(" " + verif.next().getValeur() + " ");
				}
				System.out.print("-) ");
			}
			System.out.println(" ");
		}
	}

	public static void affichagePaths(
			ArrayList<ArrayList<Graphe>> listeHarmonies) {
		System.out.println("\n Affichage des chemins:\n");

		Iterator<ArrayList<Graphe>> it1 = listeHarmonies.iterator();
		while (it1.hasNext()) {
			ArrayList<Graphe> li = it1.next();

			Iterator<Graphe> it2 = li.iterator();
			while (it2.hasNext()) {
				Graphe save = it2.next();
				System.out.print(save.getValeur() + " ");
			}
			System.out.print("taille :" + li.size() + " \n");
		}
		System.out.print("Nombre d'harmonisations :" + listeHarmonies.size()
				+ " \n");
	}
}
