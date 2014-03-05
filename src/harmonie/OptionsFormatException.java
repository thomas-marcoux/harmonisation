package harmonie;

public class OptionsFormatException extends Exception {
    private static final long	serialVersionUID = 2;

    public	OptionsFormatException(String option) {
	super("Mauvais argument : " + option);
    }

    public	OptionsFormatException(String option, int nb_arg) {
	super("L'option " + option + " prend "
	      + nb_arg + " arguments en parametre.");
    }
}
