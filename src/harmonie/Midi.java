package harmonie;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;

public abstract class Midi {
    
    /**
     * Cette classe contiens les outils pour ecrire un fichier midi.
     */

    private static final int RESOLUTION = 1;
    private static final int TRACK_NUM = 4;
    private static final int VELOCITY = 64;

    private static final int INSTRU_SOPRANO = 73; //Flute
    private static final int CANAL_SOPRANO = 1;

    private static final int INSTRU_ALTO = 11; //Vibraphone
    private static final int CANAL_ALTO = 2;

    private static final int INSTRU_TENOR = 12; //Mariba
    private static final int CANAL_TENOR = 3;

    private static final int INSTRU_BASSE = 32; //Basse
    private static final int CANAL_BASSE = 4;

    private static final int CONST1 = 36;
    private static final int CONST2 = 12;
    
    static public void write(int[] soprano, String file_out) 
	throws IOException, InvalidMidiDataException {
	File	out = new File(file_out);
	Sequence	seq = new Sequence
	    (Sequence.PPQ, RESOLUTION, TRACK_NUM);

	writeTrack(seq, soprano, INSTRU_SOPRANO, CANAL_SOPRANO);
	/*
	writeTrack(seq, alto, INSTRU_ALTO, CANAL_ALTO);
	writeTrack(seq, tenor, INSTRU_TENOR, CANAL_TENOR);
	writeTrack(seq, basse, INSTRU_BASSE, CANAL_BASSE);
	*/
	MidiSystem.write(seq, 1, out);
    }

    static private void	writeTrack
	(Sequence seq, int[] t, int instru, int canal) 
	throws InvalidMidiDataException {
	Track	track = seq.createTrack();
	int	note;

	track.add(instrument(instru, canal));
	for (int i = 0; i < t.length;) {
	    if (Chant.isNote(t[i])) {
		note = noteToMidi(t[i]);
		track.add(noteOn(note, i++, canal));
		for (; t[i] == Chant.REPEAT; ++i);
		track.add(noteOff(note, i, canal));
	    }
	    else
		++i;
	}
    }

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
