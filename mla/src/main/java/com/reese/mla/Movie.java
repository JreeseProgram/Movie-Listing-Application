package com.reese.mla;
/*                             MOVIE
 * Contains the Movie Object which has 6 fields, and any pertinent static 
 * methods relating to movies as well
 */

import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Movie {
    private int movieID;
    private String movieName;
    private int releaseYear;
    private String director;
    private String composer;
    private double reviewScore;

    public Movie(){}//default constructor to be left empty

    public Movie(int movieID, String movieName, int releaseYear, String director, String composer, double reviewScore){
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.composer = composer;
        this.reviewScore = reviewScore;
    }

    //only useful for CLI or testing.
    public void createMovie(String id, String name, String year, String director, String composer, String review){
        setMovieID(id);
        setMovieName(name);
        setReleaseYear(year);
        setDirector(director);
        setComposer(composer);
        setReviewScore(review);
    }

    public int getMovieID(){
        return movieID;
    }
    //Attempts to set movie id to the user input. If it fails, it returns false
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

    public String getMovieName(){
        return movieName;
    }

    public int setMovieName(String movieNameString){
        this.movieName = movieNameString;
        return 0;
    }

    public int getReleaseYear(){
        return releaseYear;
    }
    //Attempts to set releaseYear to user input.
    //0 means successful, 1 means year is too low, 2 means NaN
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

    public String getDirector(){
        return director;
    }   

    public int setDirector(String stringDirector){
        this.director = stringDirector;
        return 0;
    }

    public String getComposer(){
        return composer;
    }

    public int setComposer(String stringComposer){
        this.composer = stringComposer;
        return 0;
    }

    public double getReviewScore(){
        return reviewScore;
    }

    //Attempts to set review score
    //0 means success, 1 means score was too low
    //2 means too high, 3 means NaN
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

    //asks user for an ID or name. If name, it catches the error of it not being an int then
    //tests if the string given matches the name of any movies in the list
    public static void deleteMovie(List<Movie> list){
        String tempString = "";
        try{
            boolean found = false;
            tempString = Methods.getInput("Please Enter in ID or Name");
            int input = Integer.parseInt(tempString);
            for (Movie movie : list) {
                if (movie.getMovieID() == input){
                    found = true;
                    //Informs user movie is found and asks them to confirm deletion
                    Methods.showMessage("Information On Found Movie:\n" + movie.toString());
                    char yesNo = Methods.getInput("Are You Sure You Want To Remove The Movie? Y/N").charAt(0);
                    yesNo = Character.toLowerCase(yesNo);
                    switch(yesNo){
                        case 'y'://user wants to delete
                            list.remove(movie);
                            break;
                        case 'n'://user does not want to delete
                            break;
                        default://invalid input
                            Methods.showMessage("Invalid Input, Ignoring Changes");
                    }
                    break;
                }
            }
            if(!found){
                Methods.showMessage("Movie ID Not Found");
            }
            else{
                list.sort(MovieComparator.sortByIDAsc());
            }
        }
        //alternative if user did not enter an ID
        catch(NumberFormatException NFE){
            boolean found = false;
            for (Movie movie : list) {
                if(tempString.toLowerCase().equals(movie.getMovieName().toLowerCase())){
                    found = true;
                    //Informs user movie is found and asks them to confirm deletion
                    Methods.showMessage("Information On Found Movie:\n" + movie.toString());
                    char yesNo = Methods.getInput("Are You Sure You Want To Remove The Movie? Y/N").charAt(0);
                    yesNo = Character.toLowerCase(yesNo);
                    switch(yesNo){
                        case 'y'://user wants to delete
                            list.remove(movie);
                            break;
                        case 'n'://user does not want to delete
                            break;
                        default://invalid input
                            Methods.showMessage("Invalid Input, Ignoring Changes");
                    }
                    break;
                }
            }
            if(!found){
                Methods.showMessage("Movie Name not Found");
            }
            else{
                list.sort(MovieComparator.sortByIDAsc());
            }
        }
    }
    //Used for searching for a Movie
    public static int movieSearch(ObservableList<Movie> list, String passedString) {
        int indexFound = -100;
        try {
            int parsedID = -100;
            parsedID = Integer.parseInt(passedString);
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
                if (list.get(i).getMovieName().toLowerCase().equals(passedString.toLowerCase())) {
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


    public String toString(){
        return String.format("Movie ID: %d | Movie Name: %s | Release Year: %d | Director: %s | Composer: %s | ReviewScore: %.1f",
            this.movieID, this.movieName,this.releaseYear,this.director,this.composer,this.reviewScore);
    }

}
