import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import com.reese.mla.Movie;

/*            MovieTest
 * This tests all applicable methods in Movie
 * IMPORTANT!!!!!!!!!!!!!!
 *      If you use newInputStream(), please type "System.setIn(System.In)"
 *      to reset input stream :^)
 */

public class MovieTest{
    private Movie validMovie;

    @BeforeEach
    public void setup(){
        validMovie = new Movie(
            3,"The Best Movie", 2024, "John Smith", "Jane Doe", 9.9
        );
    }

    @Test
    @DisplayName("Tests if movie ID will be returned")
    public void getMovieIDTest(){
        assertEquals(3, validMovie.getMovieID());
    }

    @Test
    @DisplayName("Tests if setting a movie is possible")
    public void setMovieIDTest(){
        validMovie.setMovieID("5");
        assertEquals(5, validMovie.getMovieID());
    }

    @Test
    @DisplayName("Tests if setting movie ID returns correct for valid input: exit code 0")
    public void setMovieIDReturnTest(){
        int result = validMovie.setMovieID("5");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Tests if method catches bad input for movie ID; NaN: error code 2")
    public void setMovieIDTestNaN(){

        int result = validMovie.setMovieID("Howdy");
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test if method catches bad input for movie ID; negative numbers: error code 1")
    public void setMovieIDTestNegNum(){
        int result = validMovie.setMovieID("-43");
        assertEquals(1,result);
    }

    @Test
    @DisplayName("Test if method catches bad input for movie ID; the number 0: error code 1")
    public void setMovieIDTestNegNum2(){
        int result = validMovie.setMovieID("0");
        assertEquals(1,result);
    }

    @Test
    @DisplayName("Test if retrieving movie name works")
    public void getMovieNameTest(){
        assertEquals("The Best Movie", validMovie.getMovieName());
    }

    @Test
    @DisplayName("Test if entering a movie name works")
    public void setMovieNameTest(){
        int result = validMovie.setMovieName("The Newer Better Movie");
        assertEquals("The Newer Better Movie", validMovie.getMovieName());
    }

    @Test
    @DisplayName("Tests if the boolean value is successfully returned")
    public void setMovieNameTestBool(){
        int result = validMovie.setMovieName("The Newer Better Movie");
        assertEquals(0,result);
    }

    @Test
    @DisplayName("Tests if you can retrieve the movie year")
    public void getReleaseYearTest(){
        assertEquals(2024, validMovie.getReleaseYear());
    }

    @Test
    @DisplayName("Tests if Adding a Year works")
    public void setReleaseYearTest(){
        int result = validMovie.setReleaseYear("2027");
        assertEquals(2027, validMovie.getReleaseYear());
        assertEquals(0, result);
    }
    @Test
    @DisplayName("Tests if NaN year entered and catches it: exit with error code ")
    public void setReleaseYearTestNaN(){
        int result = validMovie.setReleaseYear("what year?");
        assertEquals(2,result);
    }

    @Test
    @DisplayName("Tests if year is too low and catches it: Exit with error code 1")
    public void setReleaseYearTestLowYear(){
        int result = validMovie.setReleaseYear("1887");
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Tests if returning director name works")
    public void getDirectorTest(){
        assertEquals("John Smith", validMovie.getDirector());
    }

    @Test
    @DisplayName("Tests if Setting Director Works")
    public void setDirectorTest(){
        validMovie.setDirector("Johnathon Johnny");
        assertEquals("Johnathon Johnny", validMovie.getDirector());
    }

    @Test
    @DisplayName("Tests if setting director returns correct: Success exit with 0")
    public void setDirectorTestBool(){
        int result = validMovie.setDirector("Johnathon Johnny");
        assertEquals(0,result);
    }

    @Test
    @DisplayName("Tests if it returns the composer")
    public void getComposerTest(){
        assertEquals("Jane Doe", validMovie.getComposer());
    }

    @Test
    @DisplayName("Tests if setting composer works")
    public void setComposerTest(){
        validMovie.setComposer("Janey Don");
        assertEquals("Janey Don", validMovie.getComposer());
    }

    @Test
    @DisplayName("Tests if Composer returns success code: exit code 0")
    public void setComposerTestBool(){
        int result = validMovie.setComposer("Janey Don");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Tests if ReviewScore gets returned")
    public void getReviewScoreTest(){
        assertEquals(9.9, validMovie.getReviewScore());
    }

    @Test
    @DisplayName("Tests if review score can be set")
    public void setReviewScoreTest(){
        validMovie.setReviewScore("8.7");
        assertEquals(8.7, validMovie.getReviewScore());
    }

    @Test
    @DisplayName("Tests if it returns 0 on successful execution")
    public void setReviewScoreReturnTest(){
        int result = validMovie.setReviewScore("8.7");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Tests if too low review score gets caught: Error code 1")
    public void setReviewScoreTestLow(){
        int result = validMovie.setReviewScore("0.0");
        assertEquals(1, result);
    }
    @Test
    @DisplayName("Tests if too high review score gets caught: Error code 2")
    public void setReviewScoreTestHigh(){
        int result = validMovie.setReviewScore("10.1");
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Tests for bad input in review score: Error code 3")
    public void setReviewScoreTestNaN(){
        int result = validMovie.setReviewScore("it was pretty mid");
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Test To String Formatted correctly")
    public void toStringTest(){
        assertEquals(
                "Movie ID: 3 | Movie Name: The Best Movie | Release Year: 2024 | Director: John Smith | Composer: Jane Doe | ReviewScore: 9.9",
                validMovie.toString()
        );
    }

    @Test
    @DisplayName("Tests if create movie can create a movie")
    public void createMovieTest(){
        Movie testingMovie = new Movie();
        testingMovie.createMovie("3","The Best Movie", "2024", "John Smith", "Jane Doe", "9.9");
        assertEquals(validMovie.toString(), testingMovie.toString());
    }

    @Test
    @DisplayName("Tests if average of movies can be calculated: should be 5 in this")
    public void calcMovieAvgTest(){
        ObservableList<Movie> list = FXCollections.observableArrayList();
        list.add(new Movie(3,"The Best Movie", 2024, "John Smith", "Jane Doe", 10));
        list.add(new Movie(3,"The Best Movie", 2024, "John Smith", "Jane Doe", 0));
        assertEquals("5.0", Movie.calcMovieAvg(list));
    }
}
