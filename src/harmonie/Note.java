package harmonie;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Note {
	/**
	 * Tous les "Note" tel que chaque "Note" contient toutes les notes
	 * correspondantes.
	 */
	public static Note DO = new Note("DO", 0, 7, 14, 21);
	public static Note RE = new Note("RE", 1, 8, 15, 22);
	public static Note MI = new Note("MI", 2, 9, 16, 23);
	public static Note FA = new Note("FA", 3, 10, 17, 24);
	public static Note SOL = new Note("SOL", 4, 11, 18, 25);
	public static Note LA = new Note("LA", 5, 12, 19, 26);
	public static Note SI = new Note("SI", 6, 13, 20, 27);

	/**
	 * "listeGeneralNotes" est la liste contenant tous les "Note" et donc toutes
	 * les notes.
	 */
	public static LinkedList<Note> listeGeneralNotes = new LinkedList<Note>(
			Arrays.asList(DO, RE, MI, FA, SOL, LA, SI, DO));

	private String nom;
	private LinkedList<Integer> listeNotes;

	/**
	 * Constructeur Vide qui met tout à "" ou null
	 */
	public Note() {
		this.nom = "";
		this.listeNotes = new LinkedList<Integer>();
	}

	/**
	 * Constructeur de "Note", il prend un string et trois notes
	 * 
	 * @param nom
	 *            String qui identifie "Note"
	 * @param note1
	 *            Première note qui sera ajoutée à "Note"
	 * @param note2
	 *            Deuxième note qui sera ajoutée à "Note"
	 * @param note3
	 *            Troisième note qui sera ajoutée à "Note"
	 * @param note4
	 */
	public Note(String nom, int note1, int note2, int note3, int note4) {
		listeNotes = new LinkedList<Integer>();

		this.nom = nom;
		listeNotes.add(note1);
		listeNotes.add(note2);
		listeNotes.add(note3);
		listeNotes.add(note4);
	}

	/**
	 * Prend un Integer et retourne la note correspondante à celui-ci.
	 * 
	 * @param noteInt
	 *            L'Integer dans le chant soprano où il faut trouver le "Note"
	 * @return Note Le "Note" correspondant à l'Integer noteInt
	 * 
	 * */
	public static Note trouverNote(int noteInt) {
		Note noteNote = null;
		boolean continuer = true;

		Iterator<Note> it = listeGeneralNotes.iterator();
		while (it.hasNext() && continuer) {
			Note actuelle = it.next();

			if (actuelle.getListeNotes().contains(noteInt)) {
				noteNote = actuelle;
				continuer = false;
			}
		}

		return noteNote;
	}

	/**
	 * Renvoie un boolean celon si le "Note" en parametre est égale au "Note" en
	 * this
	 * 
	 * @param a
	 *            "Note" a qui sera comparer au "Note" this
	 * @return "Note" this comparé au "Note" en paramètre
	 */
	public boolean equals(Note a) {
		return (this.getNom().equals(a.getNom()));
	}

	/**
	 * Retourne le nom de "Note"
	 * 
	 * @return Le String nom de "Note"
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de modifier le nom de "Note"
	 * 
	 * @param nom
	 *            Le String que prendra "Note"
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la liste des notes de "Note"
	 * 
	 * @return LinkedList<Integer> listeNotes de "Note"
	 */
	public LinkedList<Integer> getListeNotes() {
		return listeNotes;
	}

	/**
	 * Permet de modifier la liste des notes de "Note"
	 * 
	 * @param listeNotes
	 *            LinkedList<Integer> que prendra "Note"
	 */
	public void setListeNotes(LinkedList<Integer> listeNotes) {
		this.listeNotes = listeNotes;
	}
}
