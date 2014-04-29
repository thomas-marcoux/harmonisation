package Exceptions;

public class EmptyFileException extends Exception {

	/**
	 * Gestion des fichiers chant vides
	 */

	private static final long serialVersionUID = 3;

	/**
	 * Constructeur
	 * 
	 * @param file
	 *            Le nom du fichier vide
	 */
	public EmptyFileException(String file) {
		super("Le fichier " + file + " est vide.");
	}
}
