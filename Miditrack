import java.util.ArrayList;
import java.util.Hashtable;

public class MidiTrack {
	private Hashtable<Character, Integer> noteToPitch;

	private ArrayList<MidiNote> notes;
	private int instrumentId;

	// The constructor for this class
	public MidiTrack(int instrumentId) {
		notes = new ArrayList<MidiNote>();
		this.instrumentId = instrumentId;
		this.initPitchDictionary();
	}

	// This initialises the noteToPitch dictionary,
	// which will be used by you to convert note letters
	// to pitch numbers
	public void initPitchDictionary() {
		noteToPitch = new Hashtable<Character, Integer>();
		noteToPitch.put('C', 60);
		noteToPitch.put('D', 62);
		noteToPitch.put('E', 64);
		noteToPitch.put('F', 65);
		noteToPitch.put('G', 67);
		noteToPitch.put('A', 69);
		noteToPitch.put('B', 71);
	}

	// GETTER METHODS
	public ArrayList<MidiNote> getNotes() {
		return notes;
	}

	public int getInstrumentId() {
		return instrumentId;
	}

	// This method converts notestrings like
	// <<3E3P2E2GP2EPDP8C<8B>
	// to an ArrayList of MidiNote objects
	// ( the notes attribute of this class )
	public void loadNoteString(String notestring) {
		// convert the letters in the notestring to upper case
		notestring = notestring.toUpperCase();
		int pitch = 0;
		int octave = 0;
		int duration = 0;
	
		// NOTES
		//This method assumes that the user knows how to input musical notes
		//for e.g. putting a number '3' followed by '>' may not produce
		//the expected musical notes as "3>" doesn't make sense in this context.
		for (int i = 0; i < notestring.length(); i++) 
		{
			//duration set to 1 for default
			duration = 0;
			//checks for integers N including numbers with more than one digit
			if((int)notestring.charAt(i)>=48 && (int)notestring.charAt(i)<=57)
			{
				int j = i;
				String number = "";
				while((int)notestring.charAt(j)>=48 && (int)notestring.charAt(j)<=57)
				{
					number+=notestring.charAt(j);
					j++;
					//i++ for next if statement to check if it's letter
					i++;
				}
				duration = Integer.parseInt(number);
			}
			//checks for characters 'A' to 'G';
			if((int)notestring.charAt(i)>=65 && (int)notestring.charAt(i)<=71)
			{
				//get pitch
				pitch = noteToPitch.get(notestring.charAt(i));	
				//check for flats or sharps
				//checks index out of bound first
				if(i+1<notestring.length() && notestring.charAt(i+1)=='#')
				{
					pitch++;
					i++;
				}
				if(i+1<notestring.length()&& notestring.charAt(i+1)=='!')
				{
					pitch--;
					i++;
				}
				//creates note
				MidiNote note = new MidiNote(pitch+octave*12,duration);
				note.setDuration(duration);
				notes.add(note);
				
			}
			//check for octave 
			else if(notestring.charAt(i)=='>')
			{
				//increases pitch by 12 for each successive '>'
				octave++;
				
			}
			else if(notestring.charAt(i)=='<')
			{
				//decreases pitch by 12 for each successive '<'
				octave--;
			}
			else if(notestring.charAt(i)=='P')
			{
				MidiNote note = new MidiNote(pitch+octave*12,1);
				note.setDuration(duration);
				//silences note
				note.setSilent(true);
				notes.add(note);
			}
		}
					
	}

	// TODO: Q2. implement this method
	// Q2.a. Notes
	// Q2.b. Pauses
	// Q2.c. Durations
	// Q2.d. Octaves
	// Q2.e. Flat and sharp notes
	// Hint1: use a for loop with conditional statements
	// Hint2: Use the get method of the noteToPitch object (Hashtable class)

	public void revert() {
		ArrayList<MidiNote> reversedTrack = new ArrayList<MidiNote>();
		for (int i = notes.size() - 1; i >= 0; i--) {
			MidiNote oldNote = notes.get(i);
			// create a newNote
			MidiNote newNote = new MidiNote(oldNote.getPitch(),
					oldNote.getDuration());

			// check if the note was a pause
			if (oldNote.isSilent()) {
				newNote.setSilent(true);
			}

			// add the note to the new arraylist
			reversedTrack.add(newNote);
		}
		notes = reversedTrack;
	}

	// This will only be called if you try to run this file directly
	// You may use this to test your code.
	
	//TESTING
	public static void main(String[] args) {
		//String notestring = "CPCPCPCPCP";
		// String notestring = "<<3E3P2E2GP2EPDP8C<8B>3E3P2E2GP2EPDP8C<8B>";
		String notestring = "D#DCPCC2CCCD#PCC2CD#FGPGG2GD#F2GFF2FD#DCPCC2CCCD#PCC2CD#FGPGG2GD#F5G>2PD#DCPCC2CCCD#PCC2CD#FGPGG2GD#F2GFF2FD#DCPCC2CCCD#PCC2CD#FGPGG2GD#F5G";
		int instrumentId = 0;
		MidiTrack newTrack = new MidiTrack(instrumentId);
		newTrack.loadNoteString(notestring);
		// Build a MusicInterpreter and set a playing speed
		MusicInterpreter mi = new MusicInterpreter();
		mi.setBPM(400);
		// Load the track and play it
		mi.loadSingleTrack(newTrack);
		mi.play();
		mi.close();
		// close the player so that your program terminates mi.close();

		// Build the MidiTrack object
		// Build a MusicInterpreter and set a playing speed
		// Load the track and play it
		// Close the player so that your program terminates
	}
}
