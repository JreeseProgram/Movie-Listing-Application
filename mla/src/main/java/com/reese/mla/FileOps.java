package com.reese.mla;

import javafx.collections.ObservableList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * This class houses only static methods, of which are
 * File operations as well as SQL operations
 */
public class FileOps {

    //Initializes DB and returns 0 if successful, 1 for SQL error, and 2 for IOE error

    /**
     * This initializes the DB connection as well as fills in
     * the list with information from the DB
     * @param list This is the list that holds all the movies
     * @param connection This is the ConnectionToPass that
     *                   stores the current connection.
     * @param url This is the full URL for the SQL connection
     * @param user This is the username for the SQL connection
     * @param password This is the password for the SQL connection
     * @param pathToDb This is the path to a SQL file
     * @return Success Status:
     *         <ul>
     *             <li>0 if successful</li>
     *             <li>1 if SQL Error</li>
     *             <li>2 for IOE Error</li>
     *         </ul>
     */
    public static int initializeDB(ObservableList<Movie> list, ConnectionToPass connection, String url, String user, String password, String pathToDb){
        try{
            connection.connect(url, user, password);
            importFromSQL(connection, pathToDb);
            // Ensures everything is up-to-date
            Statement statement = connection.getConnection().createStatement();

            // ResultSet is basically the table outputted from a query
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

    /**
     * This connects to the DB and then writes to the DB
     * from a SQL file
     * @param connection This is the ConnectionToPass that
     *                   stores the current connection.
     * @param pathToDb This is the path to a SQL file
     * @throws IOException Handle the IO exception for if
     *                     path to file is impossible to
     *                     retrieve
     * @throws SQLException Handle a SQL error, likely Bad
     *                      Connection info
     */
    public static void importFromSQL(ConnectionToPass connection, String pathToDb) throws IOException, SQLException {
        Methods.showMessage(pathToDb);
        // Only runs this if there IS a path to a new db
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
                // Remove excess whitespace to ensure that commands execute correctly
                line = line.trim();
                // This removes any lines that are empty or are comments
                // to ensure we don't label the entire command a comment
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                sBuilder.append(line);

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

    /**
     * This is called at the end of the program so ensure that
     * whenever the user closes the application changes are saved.
     * @param list This is the list that holds all the movies
     * @param connection This is the ConnectionToPass that
     *                   stores the current connection.
     */
    public static void updateDB(ObservableList<Movie> list, ConnectionToPass connection){
        try {
            Statement statement = connection.getConnection().createStatement();
            //Clears current table so that we don't duplicate rows
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
}