package harmonie;

public class HarmonieAuto {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	if (args.length == 0)
	    CLI.showHelp();
	else
	    CLI.parse(args);
    }
}
