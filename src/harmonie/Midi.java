package harmonie;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;

public class Midi {
    
    /**
     * Cette classe contiens les outils pour ecrire un fichier midi.
     */

    /**
     * Constantes public : le nom de l'option et le nombre d'argument
     * qu'elle prend en parametre
     */
    public static final String	OPTION = "-midi";
    public static final int	NB_ARGUMENTS = 2;

    /**
     * Constantes private
     */
    private static final String	FILE_SUFFIX = ".mid";

    private static final int FILE_TYPE = 1;

    private static final int RESOLUTION = 1;
    private static final int TRACK_NUM = 4;
    private static final int VELOCITY = 64;

    private static final int INSTRU_SOPRA = 74; //Flute
    private static final int CANAL_SOPRA = 1;

    private static final int INSTRU_ALTO = 11; //Vibraphone
    private static final int CANAL_ALTO = 2;

    private static final int INSTRU_TENOR = 12; //Vibraphone
    private static final int CANAL_TENOR = 3;

    private static final int INSTRU_BASSE = 32; //Basse
    private static final int CANAL_BASSE = 4;

    private static final int CONST1 = 36;
    private static final int CONST2 = 12;

    /**
     * La methode appelee par la classe CLI
     * @param	c	L'objet chant cree par Beaute. Null si Beaute n'a
     *			pas ete appelee
     * @param	args	Les arguments entrees en ligne de commande
     * @return		La valeur a ajoute a l'iterateur pour
     *			sauter les arguments de l'option
     */
    public static void	exec(Chant c, String[] args) 
	throws OptionsFormatException {
	if (args.length - 1 < NB_ARGUMENTS
	    || CLI.isOption(args[1]) || CLI.isOption(args[2]))
	    throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
	if (c == null)
	    write(args[1], args[2]);
	else
	    write(c, new File(args[2]));
	c = null;
    }

    /**
     * La methode appelee par la classe Dossier
     * @param	parent	Le dossier qui contiendra les fichiers a ecrire
     * @param	c	Le chant a ecrire
     */
    public static File	exec(File parent, Chant c) {
	return write(c,
		     new File(parent, c.getTitre() + FILE_SUFFIX));
    }
    
    /**
     * Les methodes d'ecriture de fichiers Midi
     */
    private static File write(String fileIn, String fileOut)  {
	try {
	    return write(new Chant(fileIn), new File(fileOut));
	}
	catch (EmptyFileException | ChantFormatException
	       | IOException e) {
	    System.out.println(e);
	}
	return null;
    }

    private static File write(Chant c, File out) {
	try {
	    Sequence	seq = new Sequence
		(Sequence.PPQ, RESOLUTION, TRACK_NUM);
	    
	    writeTrack(seq, c.getSoprano(), INSTRU_SOPRA, CANAL_SOPRA);
	    /*
	    writeTrack(seq, c.getAlto(), INSTRU_ALTO, CANAL_ALTO);
	    writeTrack(seq, c.getTenor(), INSTRU_TENOR, CANAL_TENOR);
	    writeTrack(seq, c.getBasse(), INSTRU_BASSE, CANAL_BASSE);
	    */
	    MidiSystem.write(seq, FILE_TYPE, out);
	    return out;
	}
	catch (IOException | InvalidMidiDataException e) {
	    System.out.println(e);
	}
	return null;
    }

    /**
     * Ecriture d'un track unique sur une sequence
     */
    private static void	writeTrack(Sequence seq,
				   int[] t, int instru, int canal) 
	throws InvalidMidiDataException {
	Track	track = seq.createTrack();
	int	note;

	track.add(instrument(instru, canal));
	for (int i = 0; i < t.length;) {
	    if (Chant.isNote(t[i])) {
		note = noteToMidi(t[i]);
		track.add(noteOn(note, i++, canal));
		for (; i < t.length && t[i] == Chant.REPEAT; ++i);
		track.add(noteOff(note, i, canal));
	    }
	    else
		++i;
	}
    }

    /**
     * Methodes gerant les evenements du track
     */
    private static MidiEvent noteOn(int nKey, long lTick, int canal)
	throws InvalidMidiDataException {
	return createEvent
	    (ShortMessage.NOTE_ON, nKey, VELOCITY, lTick, canal);
    }

    private static MidiEvent noteOff(int nKey, long lTick, int canal)
	throws InvalidMidiDataException {
	return createEvent
	    (ShortMessage.NOTE_OFF, nKey, 0, lTick, canal);
    }

    private static MidiEvent instrument(int instrument, int canal)
	throws InvalidMidiDataException {
	return createEvent
	    (ShortMessage.PROGRAM_CHANGE, instrument, 0, 0, canal);
    }

    private static MidiEvent createEvent
	(int nCommand, int nKey, int nVelocity, long lTick, int canal)
	throws InvalidMidiDataException {
	ShortMessage message = new ShortMessage();

	message.setMessage(nCommand, canal, nKey, nVelocity);
	return new MidiEvent(message, lTick);
    }

    private static int	noteToMidi(int note) {
	return CONST1 + 2 * Chant.getNoteBase(note)
	    + CONST2 * Chant.getOctave(note)
	    - ((isLowerThanFa(note)) ? 0 : 1);
    }

    private static boolean	isLowerThanFa(int note) {
	return Chant.getNoteBase(note) >= Chant.DO
	    && Chant.getNoteBase(note) <= Chant.MI;
    }
}
