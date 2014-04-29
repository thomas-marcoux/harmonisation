package harmonie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ParcoursGraphe {
	/**
	 * Prend un tableau à double entrée correspondant à un jeu de note possible
	 * et l'envois à getPath pour trouver toutes les listes de jeux possibles à
	 * partir de cette série de note
	 * 
	 * @param jeu
	 *            Tableau contenant toutes les differentes notes possibles pour
	 *            chaque voix pour une série de note
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant à tous les jeux possibles pour l'indice
	 *            indice
	 * @param accord
	 *            Accord correspondant a la listeNote
	 * */
	public static void parcoursTableauJeu(int[][] jeu, int indice,
			LinkedList<listeJeux> liste, Accord accord) {
		LinkedList<Integer> chemin = new LinkedList<Integer>();

		getPath(jeu, jeu[0][0], chemin, indice, liste, accord);
	}

	/**
	 * Prend le tableau des notes et stocke tous les jeux possibles dans la
	 * liste qui correspond à l'indice du chant que l'on parcours
	 * 
	 * @param jeu
	 *            Tableau contenant toutes les differentes notes possibles pour
	 *            chaque voix pour une série de note
	 * @param sommetSource
	 *            La note du chant soprano donné en entrée d'indice "indice"
	 * @param chemin
	 *            Une liste actuelle de note possible
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant à tous les jeux possibles pour l'indice
	 *            indice
	 * 
	 * @param accord
	 *            Accord correspondant à la listeNote
	 * 
	 * */
	public static void getPath(int[][] jeu, int sommetSource,
			LinkedList<Integer> chemin, int indice,
			LinkedList<listeJeux> liste, Accord accord) {
		listeJeux listeJeu;
		LinkedList<Integer> copieChemin = new LinkedList<Integer>();

		chemin.add(sommetSource);

		if (chemin.size() < jeu.length) {

			for (int i = 0; i < jeu[0].length; i++) {
				if ((jeu[chemin.size()][i] != 0)
						&& (jeu[chemin.size()][i] < sommetSource)) {
					copieChemin = copieChemin(chemin);

					getPath(jeu, jeu[chemin.size()][i], copieChemin, indice,
							liste, accord);
				}
			}
		} else {
			listeJeu = new listeJeux(chemin, indice, accord);
			listeJeu.setValeurBeaute1(listeJeu.regleBeauteUne());
			liste.add(listeJeu);
		}
	}

	/**
	 * Prend une liste d'int et renvois une copie de cette liste
	 * 
	 * @param chemin
	 *            LinkedList<Integer> à copier
	 * @return La copie chemin
	 * 
	 * */
	public static LinkedList<Integer> copieChemin(LinkedList<Integer> chemin) {
		LinkedList<Integer> newChemin = new LinkedList<Integer>();
		int actuel;

		Iterator<Integer> it = chemin.iterator();
		while (it.hasNext()) {
			actuel = it.next();
			newChemin.add(actuel);
		}

		return newChemin;
	}

	/**
	 * Prend une LinkedList<listeJeux> et renvois une copie de cette liste
	 * 
	 * @param listeJeux
	 *            LinkedList<listeJeux> à copier
	 * @return La copie de listeJeux
	 * */
	public static LinkedList<listeJeux> copieListeJeux(
			LinkedList<listeJeux> listeJeux) {
		LinkedList<listeJeux> newListeJeux = new LinkedList<listeJeux>();
		listeJeux actuel;

		Iterator<listeJeux> it = listeJeux.iterator();
		while (it.hasNext()) {
			actuel = it.next();
			newListeJeux.add(actuel);
		}

		return newListeJeux;
	}

	/**
	 * Prend la listeGeneraleJeux en entrée qui pour chaque liste, contient un
	 * int qui reprensente sa valeur de beauté, et un precedent qui correspond à
	 * la plus belle liste à l'instant i-1 et remonte les liste en les stockant
	 * au fur et à mesure dans chemin
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @param chemin
	 *            La liste finale qui contiendra la plus belle Harmonie
	 * @param meilleure
	 *            Une listeJeux qui va continir pour chaque instant, la plus
	 *            belle listeJeux possible
	 * 
	 * 
	 * */
	public static LinkedList<listeJeux> rechercheChemin(
			LinkedList<listeJeux> chemin, listeJeux meilleur) {

		chemin.addFirst(meilleur);

		if (meilleur.getPlusBeauPrec() != null) {
			rechercheChemin(chemin, meilleur.getPlusBeauPrec());
		}

		return chemin;
	}

	/**
	 * Permet de lancer la recherche de chemin sur la "listeGeneraleJeu"
	 * 
	 * @param listeGeneraleJeux
	 * 		La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @return
	 * 		LinkedList<listeJeux> la meilleur harmonisations de listeGeneraljeux
	 */
	public static LinkedList<listeJeux> recherche(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		LinkedList<listeJeux> chemin = new LinkedList<listeJeux>();

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(listeGeneraleJeux.size()-1).iterator();
		listeJeux meilleur = null;
		int meilleurInt = 0;

		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();

			if (actuelFin.getValeurfinale() > meilleurInt) {
				meilleur = actuelFin;
				meilleurInt = actuelFin.getValeurfinale();
			}
		}

		return rechercheChemin(chemin, meilleur);
	}
}
