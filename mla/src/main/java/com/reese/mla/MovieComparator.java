package com.reese.mla;
/*                     MOVIECOMPARATOR
 * This houses all the sort comparators for the movie object for easier comparison
 */
import java.util.Comparator;

public class MovieComparator {
    static Comparator<Movie> sortByIDAsc(){
        return Comparator.comparing(Movie::getMovieID);
    }
    static Comparator<Movie> sortByIDDesc(){
        return Comparator.comparing(Movie::getMovieID).reversed();
    }    
    static Comparator<Movie> sortByMovieNameAsc(){
        return Comparator.comparing(Movie::getMovieName, String.CASE_INSENSITIVE_ORDER);   
    }
    static Comparator<Movie> sortByMovieNameDesc(){
        return Comparator.comparing(Movie::getMovieName, String.CASE_INSENSITIVE_ORDER).reversed();
    }
    static Comparator<Movie> sortByReleaseYearAsc(){
        return Comparator.comparing(Movie::getReleaseYear);
    }
    static Comparator<Movie> sortByReleaseYearDesc(){
        return Comparator.comparing(Movie::getReleaseYear).reversed();
    }
    static Comparator<Movie> sortByDirectorAsc(){
        return Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER);
    }
    static Comparator<Movie> sortByDirectorDesc(){
        return Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER).reversed();
    }
    static Comparator<Movie> sortByComposerAsc(){
        return Comparator.comparing(Movie::getComposer, String.CASE_INSENSITIVE_ORDER);
    }
    static Comparator<Movie> sortByComposerDesc(){
        return Comparator.comparing(Movie::getComposer, String.CASE_INSENSITIVE_ORDER).reversed();
    }
    static Comparator<Movie> sortByReviewScoreAsc(){
        return Comparator.comparing(Movie::getReviewScore);
    }
    static Comparator<Movie> sortByReviewScoreDesc(){
        return Comparator.comparing(Movie::getReviewScore).reversed();
    }
    

    


}
