package harmonie;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Accord {
	/**
	 * Tous les "Accords" tel que chaque "Accord" contient toutes les "Note" lui
	 * appartenant
	 */
	public static Accord I = new Accord("DO", Note.DO, Note.MI, Note.SOL);
	public static Accord II = new Accord("RE", Note.RE, Note.FA, Note.LA);
	public static Accord III = new Accord("MI", Note.MI, Note.SOL, Note.SI);
	public static Accord IV = new Accord("FA", Note.FA, Note.LA, Note.DO);
	public static Accord IVb = new Accord("FAb", Note.FA, Note.LA, Note.DO);
	public static Accord V = new Accord("SOL", Note.SOL, Note.SI, Note.RE);
	public static Accord VI = new Accord("LA", Note.LA, Note.DO, Note.MI);
	public static Accord VII = new Accord("SI", Note.SI, Note.RE, Note.FA);

	/**
	 * "listeGeneralAccord" est la liste contenant tous les "Accord" et donc
	 * tous les "Note" de chaque "Accords"
	 */
	public static LinkedList<Accord> listeGeneralAccords = new LinkedList<Accord>(
			Arrays.asList(I, II, III, IV, IVb, V, VI, VII));

	private String nom;
	private Note tonique;
	private Note tierce;
	private Note quinte;

	/**
	 * Constructeur vide de "Accord", il initialiste tout à "" ou null
	 */
	public Accord() {
		this.nom = "";
		this.tonique = null;
		this.tierce = null;
		this.quinte = null;
	}

	/**
	 * Constructeur de "Accord", il prend un String et Trois notes
	 * 
	 * @param nom
	 *            String qui identifie "Accord"
	 * @param tonique
	 *            Le "Note" tonique qui sera ajouté à "Accord"
	 * @param tierce
	 *            Le "Note" tierce qui sera ajouté à "Accord"
	 * @param quinte
	 *            Le "Note" quinte qui sera ajouté à "Accord"
	 */
	public Accord(String nom, Note tonique, Note tierce, Note quinte) {
		this.nom = nom;
		this.tonique = tonique;
		this.tierce = tierce;
		this.quinte = quinte;
	}

	/**
	 * Retourne les trois "Accords" correspondants à "Note" donné en paramètre
	 * 
	 * @param note
	 *            Note à l'instant indice
	 * 
	 * @param indice
	 *            L'indice de note dans le chant soprano
	 * 
	 * @return LinkedList<Accord> La liste des trois accords où se trouve Note
	 * */
	public static LinkedList<Accord> trouverAccord(Note note, int indice,
			int[] tabSoprano) {
		LinkedList<Accord> listeAccord = new LinkedList<Accord>();

		Iterator<Accord> it = listeGeneralAccords.iterator();
		while (it.hasNext()) {
			Accord actuel = it.next();

			if ((note.equals(actuel.getTonique()))
					|| (note.equals(actuel.getTierce()))
					|| (note.equals(actuel.getQuinte()))) {

				if (!((indice == 0) && (actuel.getNom().equals("FAb")))
						|| ((indice == tabSoprano.length - 1) && (actuel
								.getNom().equals("IVb")))) {
					listeAccord.add(actuel);
				}
			}
		}

		return listeAccord;
	}

	/**
	 * Retourne le nom de "Accord"
	 * 
	 * @return Le String nom de "Accord"
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de modifier le nom de "Accord"
	 * 
	 * @param nom
	 *            Le String que prendra "Accord"
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la tonique de "Accord"
	 * 
	 * @return Le "Note" correspondant à la tonique de "Accord"
	 */
	public Note getTonique() {
		return tonique;
	}

	/**
	 * Permet de modifier la tonique de "Accord"
	 * 
	 * @param tonique
	 *            Note que prendra la tonique de "Accord"
	 */
	public void setTonique(Note tonique) {
		this.tonique = tonique;
	}

	/**
	 * Retourne la tierce de "Accord"
	 * 
	 * @return Le "Note" correspondant à la tierce de "Accord"
	 */
	public Note getTierce() {
		return tierce;
	}

	/**
	 * Permet de modifier la tierce de "Accord"
	 * 
	 * @param tierce
	 *            Note que prendra la tiere de "Accord"
	 */
	public void setTierce(Note tierce) {
		this.tierce = tierce;
	}

	/**
	 * Retourne la quinte de "Accord"
	 * 
	 * @return Le "Note" correspondant à la quinte de "Accord"
	 */
	public Note getQuinte() {
		return quinte;
	}

	/**
	 * Permet de modifier la quinte de "Accord"
	 * 
	 * @param quinte
	 *            Note que prendra la quinte de "Accord"
	 */
	public void setQuinte(Note quinte) {
		this.quinte = quinte;
	}

}
