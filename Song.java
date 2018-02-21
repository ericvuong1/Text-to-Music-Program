import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Song{
    String myName;
    int myBeatsPerMinute;
    String mySoundbank;
    ArrayList<MidiTrack> myTracks;
    
    // The constructor of this class
    public Song(){
        this. myTracks = new ArrayList<MidiTrack>();
        this.myBeatsPerMinute = 200;
        this.mySoundbank = "";
        this.myName = "Default_Name";
    }

    // GETTER METHODS

    public String getName(){
       return myName;
    }

    public String getSoundbank(){
       return mySoundbank;
    }
    
    public int getBPM(){
        return myBeatsPerMinute;
    }

    public ArrayList<MidiTrack> getTracks(){
        return myTracks;
    }

    // TODO: Q3.a.
    // Implement void loadFromFile(String file_path) method
    // This method loads the properties and build the tracks of this
    // song object from a file in the location specified by 
    // file
    public void loadSongFromFile(String file_path) throws IOException
    {
    	{
    	FileReader fr = new FileReader(file_path);
    	BufferedReader br = new BufferedReader(fr);
    	String currentLine = "";
    	while(currentLine!=null)
    		{
    			currentLine = br.readLine();
    			//checks for each if it contains specific names for song properties
    			if(currentLine!=null)
    			{
    				String[] parts = currentLine.split("=");
    				for(int i=0; i<parts.length;i++)
    				{
    					parts[i] = parts[i].trim();
    				}
    				if(parts[0].startsWith("name"))
    				{
    					this.myName = parts[1];
    				}
    				else if(parts[0].equals("bpm"))
    				{
    					this.myBeatsPerMinute = Integer.parseInt(parts[1]);
    				}
    				else if(parts[0].startsWith("soundbank"))
    				{
    					this.mySoundbank = parts[1];
    				}
    				//instrument and tracks are assumed to be appear consecutively
    				else if(parts[0].startsWith("instrument"))
    				{
    					int Instrument = Integer.parseInt(parts[1]);
    					currentLine = br.readLine();
    					String[] track = currentLine.split("=");
    					for(int i=0; i<track.length; i++)
    					{
    						track[i] = track[i].trim();
    					}
    					MidiTrack newTrack = new MidiTrack(Instrument);
    					newTrack.loadNoteString(track[1]);
    					myTracks.add(newTrack);
    				}
    			}
    		}
    	br.close();
    	fr.close();
    	}
    }

    public void revert(){
        for (int i = 0; i<myTracks.size(); i++){
            myTracks.get(i).revert();
        }
    }
}
