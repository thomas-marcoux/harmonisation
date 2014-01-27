package harmonie;

public class HarmonieAuto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HarmonieRoot	root = new HarmonieRoot();
		
		if (args.length > 1)
			root.start(args);
		else
			root.showHelp();
	}

}
