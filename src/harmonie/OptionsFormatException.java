package harmonie;

public class OptionsFormatException extends Exception {

    /**
     * Gestion de mauvaises commandes
     */

    private static final long	serialVersionUID = 2;

    /**
     * Constructeur
     * @param	option	Une option inconnue
     */
    public	OptionsFormatException(String option) {
	super("Mauvais argument : " + option);
    }

    /**
     * Constructeur
     * @param	option	Une option a laquelle on a passe un nombre
     *			d'argument incorrect
     * @param	nb_arg	Le nombre d'argument que prend cette option
     */
    public	OptionsFormatException(String option, int nb_arg) {
	super("L'option " + option + " prend "
	      + nb_arg + " arguments en parametre.");
    }
}
