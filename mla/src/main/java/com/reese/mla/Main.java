package com.reese.mla;
/*                         MAIN
 *  This mainly calls required methods to run the program
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicReference;


public class Main extends Application {

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
    public static void main(String[] args) {
        launch(args);
    }
}