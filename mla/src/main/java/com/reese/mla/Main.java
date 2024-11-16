package com.reese.mla;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * NOT THE "MAIN" IN THE COMPILER!. Java compiler hates when
 * main either extends or implements another component, and
 * the "main" is now in CompilingSucks
 * @see CompilingSucks
 */
public class Main extends Application {

    /**
     * Acts as main due to JavaFX requirements. initializes all
     * necessary items for the rest of the program to use.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage){
        ConnectionToPass connection = new ConnectionToPass();
        ObservableList<Movie> list = FXCollections.observableArrayList();
        GuiCreate.initialDBSelect(list, stage, connection);

        //Ensure proper save on closing
        stage.setOnCloseRequest((WindowEvent event)->{
            if(!list.isEmpty()){
                FileOps.updateDB(list, connection);
            }
        });

    }

    /**
     * Launches the JavaFX component for initial startup
     * @param args Traditional main args
     *
     */
    public static void main(String[] args) {
        launch(args);
    }
}