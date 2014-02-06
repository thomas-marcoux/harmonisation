package harmonie;

import	java.io.IOException;

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

	try	{
	    Chant	c = new Chant("lune.chant");
	    int[]	s = c.getSoprano();
		
	    for (int i = 0; i < s.length; ++i)
		{
		    System.out.print("soprano[" + i + "] = " + s[i]);
		    if (s[i] == Chant.REPEAT)
			System.out.print(" (Chant.REPEAT)");
		    else if (s[i] == Chant.PAUSE)
			System.out.print("(Chant.PAUSE)");
		    System.out.println();
		}
	}
	catch (IOException | ChantFormatException
	       | EmptyFileException e) {
	    System.out.println(e);
	}
    }
}
