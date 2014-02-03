package harmonie;

public class HarmonieAuto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    /*
		HarmonieRoot	root = new HarmonieRoot();
		
		if (args.length > 1)
			root.start(args);
		else
			root.showHelp();
	    */
	    Chant	c = new Chant("lune.chant");
	    
	    for (int i : c.getSoprano())
	    {
	    	if (i == Chant.REPEAT)
	    		System.out.print("(REPEAT), ");
	    	else if (i == Chant.PAUSE)
	    		System.out.print("(PAUSE)");
	    	else
	    		System.out.print(i + ", ");
	    }	
	}

}
