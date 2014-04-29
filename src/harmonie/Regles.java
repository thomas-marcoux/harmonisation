package harmonie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Regles {
	/**
	 * Prend un int correspondant à une note, et un autre corresondant à une
	 * voix, et test si l'int de la note peut appartenir à la voix en fonction
	 * de la règle des intervales
	 * 
	 * @param note
	 *            int correspondant à une note
	 * @param voix
	 *            int correspondant à une voix (2:alto,3:tenor,4:basse)
	 * @return true si l'int note compris dans l'interval correspondant à la
	 *         voix, false sinon
	 */
	public static boolean regleInterval(int note, int voix) {
		switch (voix) {
		case 2: // alto
			if (note >= 11 && note <= 22)
				return true;
			break;

		case 3: // tenor
			if (note >= 7 && note <= 19)
				return true;
			break;

		case 4: // basse
			if (note >= 3 && note <= 15)
				return true;
			break;

		}

		return false;
	}

	/**
	 * Dans le cas ou k=1, parcours la listeGeneraleJeux et fait correspondre
	 * aux listes une valeur de beauté dependant d'elle même et des liste d'où
	 * elles peuvent venir
	 * 
	 * @param listeGeneraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @return Liste des regles initialisee
	 * */

	public static ArrayList<LinkedList<listeJeux>> initialisationListesJeuxRegleUne(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		Iterator<listeJeux> itInit = listeGeneraleJeux.get(0).iterator();

		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(actuelInit.getValeurBeaute1());
		}

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {

			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();
					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuel.getValeurBeaute1()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuel.getValeurBeaute1());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		return listeGeneraleJeux;
	}

	/**
	 * Dans le cas ou k=2 ou k=3, parcours la listeGeneraleJeux et fait
	 * correspondre aux listes une valeur de beauté dependant d'elle même et des
	 * liste d'où elles peuvent venir
	 * 
	 * @param listeGeneraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @return Liste des regles initialisee
	 * */
	public static ArrayList<LinkedList<listeJeux>> initialisationListesJeuxRegleDeux(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {

			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();
					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuelEnt.getValue()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuelEnt.getValue());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		return listeGeneraleJeux;
	}

	/**
	 * Dans le cas ou k=4, parcours la listeGeneraleJeux fait correspondre aux
	 * listes une valeur de beauté dependant d'elle même et des liste d'où elles
	 * peuvent venir
	 * 
	 * @param listeGeneraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @return Liste des regles initialisee
	 * */
	public static ArrayList<LinkedList<listeJeux>> initialisationListesJeuxRegleQuatre(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {

		Iterator<listeJeux> itInit = listeGeneraleJeux.get(0).iterator();
		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(actuelInit.getValeurBeaute1());
		}

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {

			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();

					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuel.getValeurBeaute1()
							+ suivActuelEnt.getValue()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuel.getValeurBeaute1()
								+ suivActuelEnt.getValue());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		return listeGeneraleJeux;
	}

	/**
	 * Dans le cas où on cherche le nombre d'haonisation, parcours la
	 * listeGeneraleJeux et compte le nombre d'harmonisation possibles en
	 * partant de la fin et en additionnant tous les suivants possible de chaque
	 * jeux
	 * 
	 * @param listeGeneraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @return Nombre d'harmonisations
	 * */

	public static long nombreHarmonisation(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		long nbHarmonie = 0;

		Iterator<listeJeux> itInit = listeGeneraleJeux.get(
				listeGeneraleJeux.size() - 1).iterator();
		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(1);
		}

		for (int i = listeGeneraleJeux.size() - 2; i >= 0; i--) {

			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();

					actuel.setValeurfinale(actuel.getValeurfinale()
							+ suivActuel.getValeurfinale());
				}
			}
		}

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(0).iterator();
		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();
			nbHarmonie += actuelFin.getValeurfinale();
		}

		if ((nbHarmonie > 2000000000) || (nbHarmonie < 0)) {
			nbHarmonie = -1;
		}
		return nbHarmonie;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return Difference entre a et b
	 */
	public static boolean difference(int a, int b) {
		int c;
		if (a < b) {
			c = b;
			b = a;
			a = c;
		}
		return ((a - b) <= 6);
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return Difference de regle entre a et b
	 */
	public static int differenceRegle(int a, int b) {
		int c;
		if (a < b) {
			c = b;
			b = a;
			a = c;
		}
		return a - b;
	}
}
