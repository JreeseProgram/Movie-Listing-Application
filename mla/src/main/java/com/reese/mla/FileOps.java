package com.reese.mla;
/*                          FileOps
 * Creates/reads a file from the disk to add into the list.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FileOps {
    static void startupCheck(List<Movie> list){
        //Ensure files exists
        Path folder = Paths.get("./StartupMovies/");
        Path filePath = Paths.get("./StartupMovies/Movies.txt");
       
        try{
            if(Files.notExists(folder)){
                Files.createDirectories(folder);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        }
        catch(IOException IOE){
            Methods.showMessage("Failed to create file");
        }

        //if the file exists it will then attempt to read and delimit the file
        try(BufferedReader bReader = new BufferedReader(new FileReader(filePath.toString()))){
            ArrayList<String[]> fromFile = new ArrayList<String[]>();
            String temp = "";
            //while the current line is not null, add to the list
            while((temp = bReader.readLine()) != null){
                fromFile.add(temp.split(","));
            }
            if(!(fromFile.size() == 0)){
            //takes the new delimited strings and passes them into the book constructor
                for (String[] strings : fromFile) {
                    list.add(new Movie(Integer.parseInt(strings[0]), strings[1],Integer.parseInt(strings[2]), strings[3], strings[4], Double.parseDouble(strings[5])));
                }
            }
            //Search for duplicates
            int dupeCount = 0;
            //set used to reduce duplicates and having the full list be an array list would not be desirable
            Set<Integer> IDtoRemove = new LinkedHashSet<Integer>();
            for (Movie movie : list) {
                dupeCount = 0;
                for(Movie movieCompare : list){
                    if(movie.getMovieID() == movieCompare.getMovieID()){
                        dupeCount++;
                    }
                }
                if(dupeCount > 1){
                    IDtoRemove.add(movie.getMovieID());
                }
            }
            //delete dupes entirely (if multiple 2's, remove all 2's) then sort
            for (Integer id : IDtoRemove) {
                list.removeIf(movie -> movie.getMovieID() == id);
            }
            list.sort(MovieComparator.sortByIDAsc());
        }
        catch(IOException IOE){
            Methods.showMessage(IOE.toString());
        }
        catch(IndexOutOfBoundsException IOOBE){
            Methods.showMessage("List had an out of bounds exception");
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("When Creating a New Movie, There was a field in the wrong section ignoring item");
        }
        catch(NullPointerException NPE){
            Methods.showMessage("Startup File is empty, Ignoring file");
        }
    }
}