package com.reese.mla;
/*                               Methods
 * This contains methods that will be reused many times and simplfy the code
 * mostly in terms of readability
 */
import java.util.Scanner;

public class Methods {

    //displays a message in the console
    public static void showMessage(String mess){
        System.out.println(mess);
    }
    //gets an input from the user and returns it as a string
    public static String getInput(String request){
        Scanner in = new Scanner(System.in);
        Methods.showMessage(request);
        String result = in.nextLine();
        return result;
    }

}
