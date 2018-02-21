import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SongWriter{
    private Hashtable<Integer,String> pitchToNote;
    
    // The constructor of this class
    public SongWriter(){
        this.initPitchToNoteDictionary();
    }
    
    // This initialises the pitchToNote dictionary,
    // which will be used by you to convert pitch numbers
    // to note letters
    public void initPitchToNoteDictionary(){
        pitchToNote  = new Hashtable<Integer, String>();
        pitchToNote.put(60, "C");
        pitchToNote.put(61, "C#");
        pitchToNote.put(62, "D");
        pitchToNote.put(63, "D#");
        pitchToNote.put(64, "E");
        pitchToNote.put(65, "F");
        pitchToNote.put(66, "F#");
        pitchToNote.put(67, "G");
        pitchToNote.put(68, "G#");
        pitchToNote.put(69, "A");
        pitchToNote.put(70, "A#");
        pitchToNote.put(71, "B");
    }

    // This method converts a single MidiNote to its notestring representation
    public String noteToString(MidiNote note){
    	//gets pitch while assuming as if it has octave 0;
    	int pitch = note.getPitch()-(note.getOctave()*12);
    	String letter = pitchToNote.get(pitch);
    	//checks if note is silent
    	if(note.isSilent())
    	{
    		letter = "P";
    	}
    	//if duration>1, write the duration before the note;
    	if(note.getDuration()>1)
    	{
    		letter = note.getDuration() + letter;
    	}
    	
        String result = letter;
        // TODO: Q4.a.
        return result;
    }

    // This method converts a MidiTrack to its notestring representation.
    // You should use the noteToString method here
    public  String trackToString(MidiTrack track){
        ArrayList<MidiNote> notes = track.getNotes();
        String result = "";
        int previous_octave = 0;
        MidiNote current_note;
        //iterate through array 
        for(int i=0; i<notes.size();i++)
        {
        	int current_octave = 0;
        	current_note = notes.get(i);
        	current_octave = notes.get(i).getOctave();
        	String octave ="";
        	//octave difference calculator
        	int octave_difference = current_octave - previous_octave;
        	//modify string is there's an octave difference
        	if(octave_difference>0)
        	{
        		for(int j = 0; j<octave_difference;j++)
        		{
        			octave = octave + ">";
        		}
        	}
        	else if(octave_difference < 0)
        	{
        		octave_difference = octave_difference*(-1);
        		for(int j = 0; j<octave_difference; j++)
        		{
        			octave = octave + "<";
        		}
        	}
        	previous_octave = current_octave;
        	result=result+octave;
        	
        	String c_note = this.noteToString(current_note);
        	result = result + c_note;
        }
        // TODO: Q4.b.

        /* 
        * A hint for octaves: if the octave of the previous MidiNote was -1
        * and the octave of the current MidiNote is +3, we will have 
        * to append ">>>>" to the result string.
        */

        return result;
    }

    // TODO Q4.c.
    public void  writeToFile(Song s1, String file_path) throws IOException
    {
    	ArrayList<MidiTrack> tracks = s1.getTracks();
    	FileWriter fw = new FileWriter(file_path);
    	BufferedWriter bw = new BufferedWriter(fw);
    	//writes reverse song
    	bw.write("name = " + s1.getName() +"_reverse\n");
    	bw.write("bpm =" + s1.getBPM()+"\n");
    	//checks if contains soundbank;
    	if(s1.getSoundbank()!="")
    	{
    	bw.write("soundbank =" + s1.getSoundbank() + "\n");
    	}
    	for(int i = 0; i < tracks.size();i++ )
    	{
    		bw.write("instrument = " + tracks.get(i).getInstrumentId()+"\n");
    		bw.write(" track = " + this.trackToString(tracks.get(i))+"\n");
    	}
    	bw.close();
    	fw.close();
    	
    	
    }
    // Implement the void writeToFile( Song s1 , String file_path) method
    // This method writes the properties of the Song s1 object
    // and writes them into a file in the location specified by 
    // file_path. This file should have the same format as the sample
    // files in the 'data/' folder.

    public static void main( String[] args){
        // TODO: Q4.d.
        // Create a Song object

        // Load text file using the given song_filename, remember to 
        // catch the appropriate Exceptions, print meaningful messages!
        // e.g. if the file was not found, print "The file FILENAME_HERE was not found"

        // call the revert method of the song object.
        
        // Create a SongWriter object here, and call its writeToFile( Song s, String file_location) method.
    	
    	//loads song from file
    	Song s1 = new Song();
    	String song_file_path = "./data/07.txt";
    	try
    	{
    		s1.loadSongFromFile(song_file_path);
    	}
    	 catch  (IOException e)
        {
        	System.out.println("The file " + song_file_path + "was not found");
        }
    	s1.revert();
    	//writes reverted song
    	SongWriter s1_reverse = new SongWriter();
    	
    	String spf = "./data/"+s1.getName()+"_reverse"+".txt";
    	try
    	{
    		s1_reverse.writeToFile(s1, spf);
    	}
    	catch(IOException e)
    	{
    		System.out.println("The file " + spf + "was not found");
    	}
    	//plays reverted song
    	   
           Song reverseSong = new Song();
           try
           {
        	   reverseSong.loadSongFromFile(spf);
           }
           catch  (IOException e)
           {
           	System.out.println("Could not open file.");
           }
           MusicInterpreter mi = new MusicInterpreter();
           mi.setBPM(reverseSong.getBPM());
           mi.loadSong(reverseSong);
           mi.play();
           mi.close();
    }
}
