package com.reese.mla;
/*                         MAIN
 *  This mainly calls required methods to run the program
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        ObservableList<Movie> list = FXCollections.observableArrayList();
        FileOps.startupCheck(list);
        GuiCreate.mainMenu(list, stage);

    }
    public static void main(String[] args) {
        launch(args);
    }
}




/