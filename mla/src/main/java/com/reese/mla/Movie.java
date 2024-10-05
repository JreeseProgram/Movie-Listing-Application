package com.reese.mla;
/*                             MOVIE
 * Contains the Movie Object which has 6 fields, and any pertinent static 
 * methods relating to movies as well
 */

import java.util.Collections;
import java.util.List;

public class Movie {
    private int movieID;
    private String movieName;
    private int releaseYear;
    private String director;
    private String composer;
    private double reviewScore;

    public Movie(){}//default constructor to be left empty

    //Should only be used when reading from a file or with specific intent, otherwise use createMovie()
    public Movie(int movieID, String movieName, int releaseYear, String director, String composer, double reviewScore){
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.composer = composer;
        this.reviewScore = reviewScore;
        
    }

    public void createMovie(){
        setMovieID();
        setMovieName();
        setReleaseYear();
        setDirector();
        setComposer();
        setReviewScore();
    }

    public int getMovieID(){
        return movieID;
    }
    //Attempts to set movie id to the user input. If it fails, it returns false
    public boolean setMovieID(){
        try{
            int tempID = Integer.parseInt(Methods.getInput("Please Enter Movie ID:"));
            if (tempID <= 0) {
                Methods.showMessage("Invalid, ID must be greater than 0");
                return false;
            }
            else{
                this.movieID = tempID;
                return true;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Please Enter a Whole Number Greater Than 0");
            return false;
        }
    }

    public String getMovieName(){
        return movieName;
    }

    public boolean setMovieName(){
        this.movieName = Methods.getInput("Please Enter the Movie Name:");
        return true;
    }

    public int getReleaseYear(){
        return releaseYear;
    }
    //Attempts to set releaseYear to user input. If it fails, it will return false
    public boolean setReleaseYear(){
        try{
            int tempYear = Integer.parseInt(Methods.getInput("Please Enter the Movie Release Year:"));
            //First movie was considered to be released in 1888, so nothing allowed before that.
            if(tempYear < 1888){
                Methods.showMessage("Movie must be released as of or later than 1888");
                return false;
            }
            else{
                this.releaseYear = tempYear;
                return true;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Invalid, Must Be a Whole Number Greater Than or Equal to 1888");
            return false;
        }
    }

    public String getDirector(){
        return director;
    }

    public boolean setDirector(){
        this.director = Methods.getInput("Please Enter Director name");
        return true;
    }

    public String getComposer(){
        return composer;
    }

    public boolean setComposer(){
        this.composer = Methods.getInput("Please Enter the Composer for the Movie");
        return true;
    }

    public double getReviewScore(){
        return reviewScore;
    }

    public boolean setReviewScore(){
        try{
            double tempScore = Double.parseDouble(Methods.getInput("Please Enter the Movies Review Score:"));
            if(tempScore < 0.00){
                Methods.showMessage("The Score Must Be at least 0");
                return false;
            }
            else if(tempScore > 10.00){
                Methods.showMessage("The Score Must Be less than or equal to 10.00");
                return false;                
            }
            else{
                this.reviewScore = tempScore;
                return true;
            }
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("Invalid, Must Be a number greater than or equal to 0 and also less than or equal to 10");
            return false;
        }
        
    }
    //adds a movie to a list, will add to DB once implmented
    public static void addMovie(List<Movie> list){
        Movie newMovie = new Movie();
        newMovie.createMovie();
        //search for duplicates in list
        boolean dupe = false;
        for (Movie movie : list) {
            if(newMovie.getMovieID() == movie.getMovieID()){
                dupe = true;
            }
        }
        if(!dupe){
            list.add(newMovie);
            list.sort(MovieComparator.sortByIDAsc());
        }
        else{
            Methods.showMessage("Duplicate ID, Ignoring");
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
                    Character.toLowerCase(yesNo);
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
                if(tempString.equals(movie.getMovieName())){
                    found = true;
                    //Informs user movie is found and asks them to confirm deletion
                    Methods.showMessage("Information On Found Movie:\n" + movie.toString());
                    char yesNo = Methods.getInput("Are You Sure You Want To Remove The Movie?").charAt(0);
                    Character.toLowerCase(yesNo);
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

    public static void modifyMovie(List<Movie> list){
        String tempString = "";
        boolean dupe = false;
        try{
            boolean found = false;
            tempString = Methods.getInput("Please Enter in ID or Name");
            int input = Integer.parseInt(tempString);
            for (Movie movie : list) {
                //checks for match
                if (movie.getMovieID() == input){
                    found = true;
                    //create copy of movie and later check new movie if it has a dupe,
                    //then if no dupes "adds" new movie into list and sorts
                    Movie originalMovie = movie;
                    list.remove(movie);
                    Methods.showMessage("Information On Found Movie:\n" + originalMovie.toString());
                    Movie modMovie = new Movie();
                    modMovie.createMovie();
                    for (Movie comparedMovie : list) {
                        //Found Dupe
                        if (comparedMovie.getMovieID() == modMovie.getMovieID()){
                            Methods.showMessage("Dupe ID, reverting any changes");
                            list.add(originalMovie);
                            list.sort(MovieComparator.sortByIDAsc());
                            dupe = true;
                        }
                    }
                    if(!dupe){
                        list.add(modMovie);
                        list.sort(MovieComparator.sortByIDAsc());
                    }
                    break;
                }
            }
            if(!found){
                Methods.showMessage("Movie ID Not Found");
            }
            
        }
        //alternative if user did not enter an ID
        catch(NumberFormatException NFE){
            boolean found = false;
            for (Movie movie : list) {
                //checks for match
                if (movie.getMovieName().equals(tempString)){
                    found = true;
                    //create copy of movie and later check new movie if it has a dupe,
                    //then if no dupes "adds" new movie into list and sorts
                    Movie originalMovie = movie;
                    list.remove(movie);
                    Methods.showMessage("Information On Found Movie:\n" + originalMovie.toString());
                    Movie modMovie = new Movie();
                    modMovie.createMovie();
                    for (Movie comparedMovie : list) {
                        //Found Dupe
                        if (comparedMovie.getMovieID() == modMovie.getMovieID()){
                            Methods.showMessage("Dupe ID, reverting any changes");
                            list.add(originalMovie);
                            list.sort(MovieComparator.sortByIDAsc());
                            dupe = true;
                        }
                    }
                    if(!dupe){
                        list.add(modMovie);
                        list.sort(MovieComparator.sortByIDAsc());
                    }
                    
                    break;
                }
            }
            if(!found){
                Methods.showMessage("Movie Name not Found");
            }
        }
    }

    public static void movieMenu(List<Movie> list){
        boolean toExit = false;
        char userChoice = ' ';
        do{
            Methods.showMessage("------------------------------------------------------------");
            userChoice = Methods.getInput(
                "        Menu:\n" +
                "1) Display All Movies\n" +
                "2) Create a Movie\n" +
                "3) Modify a Movie\n" + 
                "4) Delete a Movie\n" + 
                "5) Exit"
            ).charAt(0);
            switch(userChoice){
                case '1'://Display All Movies
                    double avgScore = 0.00;
                    //Ask user how to sort
                    userChoice = Methods.getInput(
                        "Please choose which way to sort:\n" +
                        "1) MovieID\n" +
                        "2) Movie Name\n" +
                        "3) Release Year\n" +
                        "4) Director\n" +
                        "5) Composer\n" +
                        "6) Review Score\n"
                    ).charAt(0);
                    //ask user if they want ascending or descending order
                    boolean isAsc = true;
                    char tempChar = Methods.getInput("Sorted in (a)scending or (d)escending order?").charAt(0);
                    tempChar = Character.toLowerCase(tempChar);
                    if(tempChar == 'a'){
                        isAsc = true;
                    }
                    else if(tempChar == 'd'){
                        isAsc = false;
                    }
                    else {
                        Methods.showMessage("Invalid input, defaulting to ascending");
                    }
                    //Does the Sort of the list
                    switch (userChoice) {
                        case '1'://Movie ID
                            Collections.sort(list, isAsc ? MovieComparator.sortByIDAsc() : MovieComparator.sortByIDDesc());
                            break;
                        case '2'://Movie Name
                            Collections.sort(list, isAsc ? MovieComparator.sortByMovieNameAsc() : MovieComparator.sortByMovieNameDesc());
                            break;
                        case '3'://Release Year
                            Collections.sort(list, isAsc ? MovieComparator.sortByReleaseYearAsc() : MovieComparator.sortByReleaseYearDesc());
                            break;
                        case '4'://Director
                            Collections.sort(list, isAsc ? MovieComparator.sortByDirectorAsc() : MovieComparator.sortByDirectorDesc());
                            break;
                        case '5'://Composer
                            Collections.sort(list, isAsc ? MovieComparator.sortByComposerAsc() : MovieComparator.sortByComposerDesc());
                            break;
                        case '6'://Review Score
                            Collections.sort(list, isAsc ? MovieComparator.sortByReviewScoreAsc() : MovieComparator.sortByReviewScoreDesc());
                            break;
                        default:
                            Methods.showMessage("Invalid Input, sorting by ID Ascending");
                    }
                    
                    for (Movie movie : list) {
                        Methods.showMessage(movie.toString());
                        avgScore += movie.getReviewScore();
                    }
                    avgScore = avgScore/list.size();
                    Methods.showMessage("Average Review Score: " + String.format("%.1f", avgScore));
                    break;//End Case 1 Display all Movies
                case '2'://Create A Movie
                    Movie.addMovie(list);
                    break;
                case '3'://Modify a Movie
                    Movie.modifyMovie(list);
                    break;
                case '4'://Delete a Movie
                    Movie.deleteMovie(list);
                    break;
                case '5'://Exit
                    Methods.showMessage("Good Bye!");
                    toExit = true;
                    break;
                default:
                    Methods.showMessage("Invalid Input, Please Try Again; Choose Option 1-5");
            }


        }while(!toExit);
    }//End movieMenu

    public String toString(){
        return String.format("Movie ID: %d | Movie Name: %s | Release Year: %d | Director: %s | Composer: %s | ReviewScore: %.1f",
            this.movieID, this.movieName,this.releaseYear,this.director,this.composer,this.reviewScore);
    }

}
