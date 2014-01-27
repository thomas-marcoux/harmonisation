package harmonie;

public class HarmonieRoot {
public	HarmonieRoot() {
	}
	public void	start(String[] args) {
		try {
			cleanArgs(args);
			parseArgs(args);
		}	
		catch(OptionsFormatException e) {
		
		}
	}
	public void	parseArgs(String[] args) {
		/*
		 * lis tous les arguments et appelle
		 * 	une methode pour executer chaque option
		 * au fur et a mesure.
		 * les options seront toujours bien formatees
		 * car on aura nettoye la liste avec
		 * cleanArgs au prealable
		 */
	}
	public void	cleanArgs(String[] args)
			throws OptionsFormatException {
		/*
		 * controle le format des options
		 * et de ses arguments
		 * lance une exception si une erreur survient
		 */
		throw new OptionsFormatException();
	}
	public void	showHelp() {
		/*
		 * affiche la liste des options du programme
		 * et comment les utiliser
		 */
	}
	public void	showNames() {
		/*
		 * affiche les noms des membres du groupe
		 * 
		 */
	}
}
