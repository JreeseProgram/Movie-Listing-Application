//import static org.junit.jupiter.api.Assertions.*;
//import java.io.ByteArrayInputStream;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.*;
//import com.reese.mla.Movie;
//
///*            MovieTest
// * This tests all applicable methods in Movie
// * IMPORTANT!!!!!!!!!!!!!!
// *      If you use newInputStream(), please type "System.setIn(System.In)"
// *      to reset input stream :^)
// */
//
//public class MovieTest{
//    private Movie validMovie;
//
//    @BeforeEach
//    public void setup(){
//        validMovie = new Movie(
//            3,"The Best Movie", 2024, "John Smith", "Jane Doe", 9.9
//        );
//    }
//
//
//    @Test
//    @DisplayName("Tests if movie ID will be returned")
//    public void getMovieIDTest(){
//        assertEquals(3, validMovie.getMovieID());
//    }
//
//    @Test
//    @DisplayName("Tests if setting a movie is possible")
//    public void setMovieIDTest(){
//        newInputStream("5\n");
//        validMovie.setMovieID();
//        System.setIn(System.in);
//        assertEquals(5, validMovie.getMovieID());
//
//    }
//
//    @Test
//    @DisplayName("Tests if method catches bad input for movie ID; NaN")
//    public void setMovieIDTestNaN(){
//        newInputStream("howdy\n");
//        boolean result = validMovie.setMovieID();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Test if method catches bad input for movie ID; negative numbers")
//    public void setMovieIDTestNegNum(){
//        newInputStream("-43\n");
//        boolean result = validMovie.setMovieID();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Test if retrieving movie name works")
//    public void getMovieNameTest(){
//        assertEquals("The Best Movie", validMovie.getMovieName());
//    }
//
//    @Test
//    @DisplayName("Test if entering a movie name works")
//    public void setMovieNameTest(){
//        newInputStream("The Newer Better Movie\n");
//        validMovie.setMovieName();
//        System.setIn(System.in);
//        assertEquals("The Newer Better Movie", validMovie.getMovieName());
//    }
//
//    @Test
//    @DisplayName("Tests if the boolean value is successfully returned")
//    public void setMovieNameTestBool(){
//        newInputStream("The Newer Better Movie\n");
//        boolean result = validMovie.setMovieName();
//        System.setIn(System.in);
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("Tests if you can retreieve the movie year")
//    public void getReleaseYearTest(){
//        assertEquals(2024, validMovie.getReleaseYear());
//    }
//
//    @Test
//    @DisplayName("Tests if Adding a Year works")
//    public void setReleaseYearTest(){
//        newInputStream("2027\n");
//        validMovie.setReleaseYear();
//        System.setIn(System.in);
//        assertEquals(2027, validMovie.getReleaseYear());
//    }
//    @Test
//    @DisplayName("Tests if NaN year entered and catches it")
//    public void setReleaseYearTestNaN(){
//        newInputStream("what year?");
//        boolean result = validMovie.setReleaseYear();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//    @Test
//    @DisplayName("Tests if year is too low and catches it")
//    public void setReleaseYearTestLowYear(){
//        newInputStream("1887");
//        boolean result = validMovie.setReleaseYear();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Tests if returning director name works")
//    public void getDirectorTest(){
//        assertEquals("John Smith", validMovie.getDirector());
//    }
//
//    @Test
//    @DisplayName("Tests if Setting Director Works")
//    public void setDirectorTest(){
//        newInputStream("Johnathon Johnny\n");
//        validMovie.setDirector();
//        System.setIn(System.in);
//        assertEquals("Johnathon Johnny", validMovie.getDirector());
//    }
//
//    @Test
//    @DisplayName("Tests if setting director returns the correct boolean")
//    public void setDirectorTestBool(){
//        newInputStream("Johnathon Johnny\n");
//        boolean result = validMovie.setDirector();
//        System.setIn(System.in);
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("Tests if it returns the composer")
//    public void getComposerTest(){
//        assertEquals("Jane Doe", validMovie.getComposer());
//    }
//
//    @Test
//    @DisplayName("Tests if setting composer works")
//    public void setComposerTest(){
//        newInputStream("Janey Don\n");
//        validMovie.setComposer();
//        System.setIn(System.in);
//        assertEquals("Janey Don", validMovie.getComposer());
//    }
//
//    @Test
//    @DisplayName("Tests if Composer returns bool correctly")
//    public void setComposerTestBool(){
//        newInputStream("Janey Don\n");
//        boolean result = validMovie.setComposer();
//        System.setIn(System.in);
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("Tests if ReviewScore gets returned")
//    public void getReviewScoreTest(){
//        assertEquals(9.9, validMovie.getReviewScore());
//    }
//
//    @Test
//    @DisplayName("Tests if review score can be set")
//    public void setReviewScoreTest(){
//        newInputStream("8.7");
//        validMovie.setReviewScore();
//        System.setIn(System.in);
//        assertEquals(8.7, validMovie.getReviewScore());
//    }
//
//    @Test
//    @DisplayName("Tests if too low review score gets caught")
//    public void setReviewScoreTestLow(){
//        newInputStream("0.0");
//        boolean result = validMovie.setReviewScore();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//    @Test
//    @DisplayName("Tests if too high review score gets caught")
//    public void setReviewScoreTestHigh(){
//        newInputStream("10.1");
//        boolean result = validMovie.setReviewScore();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Tests for bad input in review score")
//    public void setReviewScoreTestNaN(){
//        newInputStream("it was pretty mid");
//        boolean result = validMovie.setReviewScore();
//        System.setIn(System.in);
//        assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Test To String Formatted correctly")
//    public void toStringTest(){
//        assertEquals(
//            "Movie ID: 3 | Movie Name: The Best Movie | Release Year: 2024 | Director: John Smith | Composer: Jane Doe | ReviewScore: 9.9",
//            validMovie.toString()
//        );
//    }
//
//    //Since there is extensive input from the user, being able to automate it is critical to efficent testing, ez solution :)
//    private void newInputStream(String newIn){
//        System.setIn(new ByteArrayInputStream(newIn.getBytes()));
//    }
//
//
//}
