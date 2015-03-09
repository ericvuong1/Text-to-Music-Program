Iimport java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PlaySong{
    public static void main( String[] args){
        //MusicInterpreter myMusicPlayer = new MusicInterpreter();
        //uncomment this line to print the available instruments
       //System.out.println(myMusicPlayer.availableInstruments());

        // TODO: Q3. b

        // Create a Song object
        //String song_file_path = "./data/07.txt";
    	String song_file_path = "./data/river_flows_in_you.txt";
        //String song_file_path = "./data/tsuna_awakens.txt";
        Song eSong = new Song();
        try
        {
        eSong.loadSongFromFile(song_file_path);
        }
        catch  (IOException e)
        {
        	System.out.println("The file " + song_file_path + " was not found");
        }
        System.out.println(eSong.getSoundbank());
        MusicInterpreter mi = new MusicInterpreter();
        mi.setBPM(eSong.getBPM());
        mi.loadSong(eSong);
        mi.play();
        mi.close();
        

        // load text file using the given song_filename, 
        // remember to catch the appropriate Exceptions

        // Play it
    }
}
