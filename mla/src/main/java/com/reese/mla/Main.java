package com.reese.mla;
/*                         MAIN
 *  This mainly calls requried methods to run the program
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Movie> list = new ArrayList<Movie>();
        FileOps.startupCheck(list);
        Movie.movieMenu(list);  
    }
}