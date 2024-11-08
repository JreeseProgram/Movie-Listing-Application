package com.reese.mla;
/*                          FileOps
 * Creates/reads a file from the disk to add into the list
 * as well as importing/exporting SQL
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileOps {

    //Initializes DB and returns 0 if successful, 1 for SQL error, and 2 for IOE error
    public static int initializeDB(ObservableList<Movie> list, ConnectionToPass connection, String url, String user, String password, String pathToDb){
        try{
            //Connect to DB
            connection.connect(url, user, password);

            //Create our DB
            Statement statement = connection.getConnection().createStatement();




            importFromSQL(connection, pathToDb);
            //Ensures everything is up-to-date
            statement = connection.getConnection().createStatement();

            //ResultSet is basically the table outputted from a query
            ResultSet table = statement.executeQuery("SELECT MovieID, MovieName, ReleaseYear, Director, Composer, ReviewScore FROM movies;");
            //Adds to our list a new movie for every line there is
            while(table.next()){
                list.add(new Movie(
                        table.getInt("MovieID"),
                        table.getString("MovieName"),
                        table.getInt("ReleaseYear"),
                        table.getString("Director"),
                        table.getString("Composer"),
                        table.getDouble("ReviewScore")
                ));
            }
            return 0;
        }
        catch (SQLException sqlE){
            sqlE.printStackTrace();
            return 1;
        }
        catch(IOException IOE){
            IOE.printStackTrace();
            return 2;
        }
    }

    //reads the SQL file and imports into our DB in connection
    public static void importFromSQL(ConnectionToPass connection, String pathToDb) throws IOException, SQLException {
        Methods.showMessage(pathToDb);
        //only runs this if there is a path to a new db
        if(pathToDb != null && !pathToDb.isEmpty()) {
            Statement statement = connection.getConnection().createStatement();
            String dbname = "MovieDB";

            statement.executeUpdate("DROP DATABASE IF EXISTS " + dbname + ";");
            statement.executeUpdate("CREATE DATABASE " + dbname + ";");
            statement.executeUpdate("USE " + dbname + ";");

            BufferedReader bReader = new BufferedReader(new FileReader(pathToDb, StandardCharsets.UTF_16));
            StringBuilder sBuilder = new StringBuilder();

            String line;

            while ((line = bReader.readLine()) != null) {
                //Remove Whitespace
                line = line.trim();
                //exclude any lines that are empty or are comments
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                sBuilder.append(line);
                //If end of current statement, execute the statement
                if (line.endsWith(";")) {
                    statement.execute(sBuilder.toString());
                    sBuilder.setLength(0);
                }
            }
        }
        else{
            Statement statement = connection.getConnection().createStatement();
            String dbname = "MovieDB";
            statement.executeUpdate("USE " + dbname + ";");
        }

    }

    public static void updateDB(ObservableList<Movie> list, ConnectionToPass connection){
        try {
            Statement statement = connection.getConnection().createStatement();
            //Clears current table
            statement.execute("TRUNCATE TABLE movies;");
            statement.execute("USE movieDB;");
            String executionStatement;
            //Inserts every movie into the DB
            for(Movie movie : list){
                executionStatement = String.format("INSERT INTO movies VALUES(%d, '%s', %d,'%s', '%s', %f);",
                                                        movie.getMovieID(), movie.getMovieName(), movie.getReleaseYear(),
                                                        movie.getDirector(),movie.getComposer(), movie.getReviewScore()
                );
                statement.execute(executionStatement);
            }
        }
        catch (SQLException SQLE){
            SQLE.printStackTrace();
            System.exit(0);
        }
    }

    public static boolean hotloadList(ObservableList<Movie> list, String path){
        //Get File Path From User

        Path filePath = Paths.get(path);
        List<Movie> tempList = new ArrayList<>();
        boolean fileExists;
       
        if (Files.notExists(filePath)) {
            fileExists = false;
            Methods.showMessage("File Not Found");
            return false;
        }
        else{
            fileExists = true;
            Methods.showMessage("File Found!");
        }

        //if the file exists it will then attempt to read and delimit the file
        if(fileExists){
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
                        tempList.add(new Movie(Integer.parseInt(strings[0]), strings[1],Integer.parseInt(strings[2]), strings[3], strings[4], Double.parseDouble(strings[5])));
                    }
                }
                //Search for duplicates
                int dupeCount = 0;
                //set used to reduce duplicates and having the full list be an array list would not be desirable
                Set<Integer> IDtoRemove = new LinkedHashSet<Integer>();
            
                for (Movie movie : tempList) {
                    dupeCount = 0;
                    for(Movie movieCompare : tempList){
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
                    tempList.removeIf(movie -> movie.getMovieID() == id);
                }
                list.clear();
                list.setAll(tempList);
                list.sort(MovieComparator.sortByIDAsc());
                return true;
            }
            catch(IOException IOE){
                Methods.showMessage(IOE.toString());
                return false;
            }
            catch(IndexOutOfBoundsException IOOBE){
                Methods.showMessage("List had an out of bounds exception");
                return false;
            }
            catch(NumberFormatException NFE){
                Methods.showMessage("When Creating a New Movie, There was a field in the wrong section ignoring item");
                return false;
            }
            catch(NullPointerException NPE){
                Methods.showMessage("Startup File is empty, Ignoring file");
                return false;
            }        
        }//End if(fileExists)
        else return false;
    }
}