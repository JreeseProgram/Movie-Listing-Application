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



// import javafx.application.Application;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// public class Main extends Application {
    
//     @Override
//     public void start(Stage primaryStage) {
//         // Create a Label
//         Label label = new Label("Hello, JavaFX!");
        
//         // Create a TextField
//         TextField textField = new TextField();
//         textField.setPromptText("Enter something...");
        
//         // Create a Button
//         Button button = new Button("Click Me!");
//         button.setOnAction(e -> label.setText("Hello, " + textField.getText() + "!"));

//         // Create a TableView
//         TableView<Person> tableView = new TableView<>();
//         TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
//         TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");

//         firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
//         lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

//         tableView.getColumns().add(firstNameCol);
//         tableView.getColumns().add(lastNameCol);

//         // Add some data to the TableView
//         ObservableList<Person> data = FXCollections.observableArrayList(
//             new Person("John", "Doe"),
//             new Person("Jane", "Smith")
//         );
//         tableView.setItems(data);

//         // Layout using VBox
//         VBox vbox = new VBox(10); // 10 is the spacing between elements
//         vbox.getChildren().addAll(label, textField, button, tableView);

//         // Set up the Scene and Stage
//         Scene scene = new Scene(vbox, 300, 250);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("JavaFX Demo");
//         primaryStage.show();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }

//     // Person class with properties for TableView
//     public class Person {
//         private final SimpleStringProperty firstName;
//         private final SimpleStringProperty lastName;

//         Person(String fName, String lName) {
//             this.firstName = new SimpleStringProperty(fName);
//             this.lastName = new SimpleStringProperty(lName);
//         }

//         public String getFirstName() {
//             return firstName.get();
//         }

//         public void setFirstName(String fName) {
//             firstName.set(fName);
//         }

//         public String getLastName() {
//             return lastName.get();
//         }

//         public void setLastName(String fName) {
//             lastName.set(fName);
//         }

//         public SimpleStringProperty firstNameProperty() {
//             return firstName;
//         }

//         public SimpleStringProperty lastNameProperty() {
//             return lastName;
//         }
//     }
// }
        