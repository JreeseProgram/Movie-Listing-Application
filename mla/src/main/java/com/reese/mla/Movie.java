package com.reese.mla;

import javafx.collections.ObservableList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class creates a Movie Object, of which
 * includes Movie ID, Movie Name, Movie Release
 * Year, Director Name, Composer Name, Movie and
 * Review Score
 */
public class Movie {
    private int movieID;
    private String movieName;
    private int releaseYear;
    private String director;
    private String composer;
    private double reviewScore;

    /**
     * This Constructor is only used if you do
     * not wish to immediately fill in information
     * in the object
     */
    public Movie(){}//default constructor to be left empty

    /**
     * <b>USE WITH CAUTION</b> - This is a Constructor that
     * will instantiate with immediate input into the fields
     * of the class. The ' is removed from strings as it
     * interferes with SQL queries. The primary use for the
     * method is when importing from the SQL database. For
     * any other creations of movies please use createMovie().
     * @param movieID This is a Unique Integer for identifying
     *                this specific movie.
     * @param movieName This is a String for the name of this Movie.
     * @param releaseYear This is an Integer for the year this movie
     *                    was made.
     * @param director This is a String for the name of the director.
     * @param composer This is a String for the name of the Composer.
     * @param reviewScore This is a Double for the review score of
     *                    this movie.
     * @see com.reese.mla.Movie#createMovie(String, String, String, String, String, String);
     */
    public Movie(int movieID, String movieName, int releaseYear, String director, String composer, double reviewScore){
        this.movieID = movieID;
        this.movieName = movieName.replaceAll("'","");
        this.releaseYear = releaseYear;
        this.director = director.replaceAll("'","");
        this.composer = composer.replaceAll("'","");
        this.reviewScore = reviewScore;
    }

    /**
     * This is the safe version of the constructor, so that
     * a user's inputs as strings can be used to create a movie.
     * ID, Year, and Review will be INT, INT, and Double respectively,
     * so
     *
     * @param id This is a String that will be a Unique Integer
     *           for identifying this specific movie.
     * @param name This is a String for the name of this Movie.
     * @param year This is a String that will be an Integer
     *             for the year this movie was made.
     * @param director This is a String for the name of the director.
     * @param composer This is a String for the name of the Composer.
     * @param review This is a String that will become a Double for
     *               the review score of this movie.
     */
    public void createMovie(String id, String name, String year, String director, String composer, String review){
        setMovieID(id);
        setMovieName(name);
        setReleaseYear(year);
        setDirector(director.replaceAll("'",""));
        setComposer(composer);
        setReviewScore(review);
    }

    /**
     * @return This movies ID #
     */
    public int getMovieID(){
        return movieID;
    }

    /**
     * Takes a String parameter and converts it to an
     * Int and sets this movies ID if valid
     * @param stringID takes the Movies ID as a string
     *                 that will be converted to an Int.
     * @return Success Status:
     *          <ul>
     *              <li>0 If Operation was successful,</li>
     *              <li>1 If the ID is <= 0</li>
     *              <li>2 If its NaN</li>
     *          </ul>
     */
    public int setMovieID(String stringID){
        try{
            int tempID = Integer.parseInt(stringID);
            if (tempID <= 0) {
                Methods.showMessage("Invalid, ID must be greater than 0");
                return 1;
            }
            else{
                this.movieID = tempID;
                return 0;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Please Enter a Whole Number Greater Than 0");
            return 2;
        }
    }

    /**
     * @return Returns this Movie's name
     */
    public String getMovieName(){
        return movieName;
    }

    /**
     * This takes a movies name as a String and sets this
     * movies name to the given name. All ' are removed as
     * misc ' can mess up any SQL queries
     * @param movieNameString Takes the String that you want to be
     *                        the movies name.
     * @return Success Status:
     *         <li>0 if successful</li>
     */
    public int setMovieName(String movieNameString){
        this.movieName = movieNameString.replaceAll("'","");
        return 0;
    }

    /**
     * @return This movies release year
     */
    public int getReleaseYear(){
        return releaseYear;
    }

    /**
     * This attempts to set the release year for this Movie
     * @param stringYear A String that will be converted to an int
     *                   for this movies release year
     * @return Success Status:
     *        <ul>
     *             <li>0 If Operation was successful,</li>
     *             <li>1 If the year is less than 1888</li>
     *             <li>2 If its NaN</li>
     *        </ul>
     */
    public int setReleaseYear(String stringYear){
        try{
            int year = Integer.parseInt(stringYear);
            //First movie was considered to be released in 1888, so nothing allowed before that.
            if(year < 1888){
                Methods.showMessage("Movie must be released as of or later than 1888");
                return 1;
            }
            else{
                this.releaseYear = year;
                return 0;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Invalid, Must Be a Whole Number Greater Than or Equal to 1888");
            return 2;
        }
    }

    /**
     * @return This movie's director
     */
    public String getDirector(){
        return director;
    }

    /**
     * Attempts to set this movies Director.
     * @param stringDirector A string that will become
     *                       this movies director.
     * @return Success Status:
     *         <li>0 if successful</li>
     */
    public int setDirector(String stringDirector){
        this.director = stringDirector.replaceAll("'","");
        return 0;
    }

    /**
     * @return This movies Composer
     */
    public String getComposer(){
        return composer;
    }

    /**
     * Attempts to set this movies Composer.
     * @param stringComposer A string that will become
     *                       this movies Composer.
     * @return Success Status:
     *         <li>0 if successful</li>
     */
    public int setComposer(String stringComposer){
        this.composer = stringComposer.replaceAll("'","");
        return 0;
    }

    /**
     *
     * @return This movies Review Score
     */
    public double getReviewScore(){
        return reviewScore;
    }

    //Attempts to set review score
    //0 means success, 1 means score was too low
    //2 means too high, 3 means NaN

    /**
     * Attempts to set this movies review score.
     * @param stringReviewScore A string that will be converted
     *                          to a double.
     * @return Success Status:
     *         <ul>
     *             <li>0 If Operation was successful,</li>
     *             <li>1 If the Score is too low (<0.1)</li>
     *             <li>2 If its too high (>10.0)</li>
     *             <li>3 If its NaN</li>
     *         </ul>
     */
    public int setReviewScore(String stringReviewScore){
        try{
            double tempScore = Double.parseDouble(stringReviewScore);
            if(tempScore < 0.1){
                Methods.showMessage("The Score Must Be at least 0.1");
                return 1;
            }
            else if(tempScore > 10.00){
                Methods.showMessage("The Score Must Be less than or equal to 10.00");
                return 2;
            }
            else{
                tempScore = Math.round(tempScore * 10.0) / 10.0;
                this.reviewScore = tempScore;
                return 0;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Invalid, Must Be a number greater than or equal to 0 and also less than or equal to 10");
            return 3;
        }
        
    }

    /**
     * This method takes a list and searches either by an
     * ID (passed as a string) or by Movie Name, and returns
     * the Index of the movie
     * @param list This is the list to be searched
     *             for a specific movie
     * @param movieToSearch Can be an Int for ID (as a String) or
     *                     left alone for movie Name Search.
     * @return Movie Index:
     *         <ul>
     *             <li>-1 If movie is not found,</li>
     *             <li> Any other integer If Index of Movie is if found</li>
     *         </ul>
     */
    public static int movieSearch(ObservableList<Movie> list, String movieToSearch) {
        int indexFound = -100;
        try {
            int parsedID = -100;
            parsedID = Integer.parseInt(movieToSearch);
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                //checks for match
                if (list.get(i).getMovieID() == parsedID) {
                    found = true;
                    indexFound = i;
                    //create copy of movie and later check new movie if it has a dupe,
                    //then if no dupes "adds" new movie into list and sorts

                }
            }
            if (!found) {
                Methods.showMessage("Movie ID Not Found");
                return -1;
            } else {
                return indexFound;
            }

        }
        catch (NumberFormatException NFE) {//Look by name
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                //checks for match
                if (list.get(i).getMovieName().toLowerCase().equals(movieToSearch.toLowerCase())) {
                    indexFound = i;
                    found = true;
                    break;
                }
            }//end For
            if (!found) {
                Methods.showMessage("Movie Name not Found");
                return -1;
            } else {
                return indexFound;
            }
        }
    }

    /**
     * This method takes an ObservableList of type Movie and
     * calculates the average review score of all Movies in the
     * list.
     * @param list The Observable list for the review scores to be averaged
     * @return the review score as a string.
     */
    public static String calcMovieAvg(ObservableList<Movie> list){
        double avgScore = 0;
        int avgCount = 0;
        for (Movie movie : list) {
            if(movie.getMovieID() != 0){
                Methods.showMessage(movie.toString());
                avgScore += movie.getReviewScore();
                avgCount++;
            }
        }
        return String.format("%.1f",avgScore/avgCount);
    }

    /**
     * This Method is what is used to sort the list of movies by all
     * possible metrics
     * @param list The list to be sorted
     * @param filterValues This is a doozy: This is here as the areas
     *                     this method is called is inside lambdas, but
     *                     those lambdas do not want to directly affect a
     *                     boolean so using a map solves this issue. If a
     *                     solution is known, please solve this!
     */
    public static void sortMovieList(ObservableList<Movie> list, Map<String, Boolean> filterValues){
        if(filterValues.get("isByID")) {
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByIDAsc() : MovieComparator.sortByIDDesc());
        }
        else if(filterValues.get("isByName")){
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByMovieNameAsc() : MovieComparator.sortByMovieNameDesc());
        }
        else if(filterValues.get("isByYear")){
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByReleaseYearAsc() : MovieComparator.sortByReleaseYearDesc());
        }
        else if(filterValues.get("isByDirector")){
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByDirectorAsc() : MovieComparator.sortByDirectorDesc());
        }
        else if(filterValues.get("isByComposer")){
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByComposerAsc() : MovieComparator.sortByComposerDesc());
        }
        else if(filterValues.get("isByReviewScore")){
            Collections.sort(list, filterValues.get("isAscending") ? MovieComparator.sortByReviewScoreAsc() : MovieComparator.sortByReviewScoreDesc());
        }
    }

    /**
     * Custom toString for a Movie
     * @return A string in format of: Movie ID: | Movie Name: | Release Year: | Director: | Composer: | ReviewScore:
     */
    public String toString(){
        return String.format("Movie ID: %d | Movie Name: %s | Release Year: %d | Director: %s | Composer: %s | ReviewScore: %.1f",
            this.movieID, this.movieName,this.releaseYear,this.director,this.composer,this.reviewScore);
    }

}
