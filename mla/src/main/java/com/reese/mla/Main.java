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




//import javafx.application.Application; // Import for JavaFX application class
//import javafx.collections.FXCollections; // Import for utility to create observable collections
//import javafx.collections.ObservableList; // Import for the observable list interface
//import javafx.geometry.Insets; // Import for padding and margins in layouts
//import javafx.scene.Scene; // Import for creating a scene in the JavaFX application
//import javafx.scene.control.*; // Import for various JavaFX controls like buttons, text fields, tables, etc.
//import javafx.scene.control.cell.PropertyValueFactory; // Import for binding table columns to properties
//import javafx.scene.layout.GridPane; // Import for a flexible layout that arranges components in a grid
//import javafx.stage.Stage; // Import for the primary stage of the JavaFX application
//
//public class Main extends Application {
//    private final ObservableList<User> userList = FXCollections.observableArrayList(); // Observable list to hold User objects for the table view
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create a new GridPane layout for organizing controls
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10)); // Set padding around the grid
//        grid.setVgap(8); // Set vertical gap between rows
//        grid.setHgap(10); // Set horizontal gap between columns
//
//        // Create input fields for name and age
//        TextField nameInput = new TextField(); // TextField for entering the user's name
//        nameInput.setPromptText("Name"); // Set placeholder text for the name input
//
//        TextField ageInput = new TextField(); // TextField for entering the user's age
//        ageInput.setPromptText("Age"); // Set placeholder text for the age input
//
//        // Create a button to submit user data
//        Button addButton = new Button("Add User"); // Button for adding a new user
//        addButton.setOnAction(e -> {
//            String name = nameInput.getText(); // Retrieve the name from the input field
//            int age = Integer.parseInt(ageInput.getText()); // Convert the age input text to an integer
//
//            // Create a new User object and add it to the user list
//            User newUser = new User(name, age);
//            userList.add(newUser); // Add the new User object to the observable list
//
//            // Clear the input fields after adding the user
//            nameInput.clear(); // Clear the name input field
//            ageInput.clear(); // Clear the age input field
//        });
//
//        // Create a TableView to display users
//        TableView<User> tableView = new TableView<>(); // Create a TableView for User objects
//
//        // Create columns for the table to display user properties
//        TableColumn<User, String> nameColumn = new TableColumn<>("Name"); // Column for the user's name
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Bind the column to the name property
//
//        TableColumn<User, Integer> ageColumn = new TableColumn<>("Age"); // Column for the user's age
//        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age")); // Bind the column to the age property
//
//        // Add the columns to the table
//        tableView.getColumns().add(nameColumn); // Add the name column to the table
//        tableView.getColumns().add(ageColumn); // Add the age column to the table
//
//        // Set the items of the table to the observable list of users
//        tableView.setItems(userList); // Bind the table's items to the userList
//
//        // Add controls to the grid layout
//        grid.add(nameInput, 0, 0); // Add the name input field to the grid at (column 0, row 0)
//        grid.add(ageInput, 1, 0); // Add the age input field to the grid at (column 1, row 0)
//        grid.add(addButton, 2, 0); // Add the add button to the grid at (column 2, row 0)
//        grid.add(tableView, 0, 1, 3, 1); // Add the table view to the grid spanning 3 columns at (column 0, row 1)
//
//        // Create a new scene with the grid layout and set its dimensions
//        Scene scene = new Scene(grid, 600, 400); // Create a scene with the specified width and height
//
//        // Set the title of the primary stage
//        primaryStage.setTitle("Movie Listing Application"); // Set the window title
//        primaryStage.setScene(scene); // Set the scene for the primary stage
//        primaryStage.show(); // Display the primary stage
//    }
//
//    // Main entry point for the JavaFX application
//    public static void main(String[] args) {
//        launch(args); // Launch the JavaFX application
//    }
//}
