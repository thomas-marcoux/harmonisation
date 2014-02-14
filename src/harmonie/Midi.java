package harmonie;

import	java.io.DataOutputStream;
import	java.io.BufferedOutputStream;
import	java.io.FileOutputStream;
import	java.io.FileNotFoundException;
import	java.io.IOException;

public abstract class Midi {
    
    /**
     * Cette classe contiens les outils
     * pour ecrire un fichier midi.
     */

    private static final String	HEADER_CHUNK_ID = "MThd";
    private static final int	CHUNK_SIZE = 6;
    private static final short	FORMAT_TYPE = 0;
    private static final short	TRACK_NUMBER = 1;
    private static final short	TIME_DIVISION = 60;

    private static final String	TRACK_CHUNK_ID = "MTrk";

    static public void	write(String file_in, String file_out)
	throws IOException, FileNotFoundException, ChantFormatException,
	       EmptyFileException {
	write(new Chant(file_in), file_out);
    }

    static public void	write(Chant c, String file_out)
	throws FileNotFoundException, IOException {
	write(c.getSoprano(), file_out);
    }

    static public void	write(int[] track, String file_out)
	throws FileNotFoundException, IOException {
	DataOutputStream	out = new DataOutputStream
	    (new BufferedOutputStream(new FileOutputStream(file_out)));

	writeHeader(out);
	writeTrack(out, track);
	out.close();
    }

    static private void	writeHeader(DataOutputStream out)
	throws IOException {
	out.writeBytes(HEADER_CHUNK_ID);
	out.writeInt(CHUNK_SIZE);
	out.writeShort(FORMAT_TYPE);
	out.writeShort(TRACK_NUMBER);
	out.writeShort(TIME_DIVISION);
    }

    static private void	writeTrack(DataOutputStream out, int[] track)
	throws IOException {
	out.writeBytes(TRACK_CHUNK_ID);
	out.writeInt(128);

	/*
	 * Note On
	 */	
	out.writeInt(0);		//Delta time
	out.writeByte(0x9);		//Event Type
	out.writeByte(72);		//Note number
	out.writeByte(1);		//Velocity
	
	/*
	 *  Note Off
	 */
	out.writeInt(0);		//Delta time
	out.writeByte(0x8);		//Event Type
	out.writeByte(72);		//Note number
	out.writeByte(1);		//Velocity
    }
}
