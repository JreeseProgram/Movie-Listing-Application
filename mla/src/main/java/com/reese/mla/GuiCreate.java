package com.reese.mla;

/*               GuiCreate
* Handles all the different menus of the GUI in every
* possible state it could be in
 */


import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.File;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class GuiCreate {
    public static void mainMenu(ObservableList<Movie> list, Stage stage){
        //Create Grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(30);
        grid.setHgap(20);

        //Create Main Text
        Text mainMenuHeader = new Text("Main Menu");
        mainMenuHeader.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));

        //Create Buttons
        Button displayMovies = new Button("Display All Movies");
        Button reloadList = new Button("Reload With New List");
        Button addMovie = new Button("Add A New Movie");
        Button modifyMovie = new Button("Modify A Movie");
        Button deleteMovie = new Button("Delete A Movie");
        Button quitApplication = new Button("Quit Application");

        //Give Buttons Functionality
        displayMovies.setOnAction(event -> {
            GuiCreate.displayMovies(list, stage);
        });

        reloadList.setOnAction(event -> {
            GuiCreate.loadMovies(list, stage);
        });

        addMovie.setOnAction(event -> {
            GuiCreate.addMovie(list, stage);
        });

        modifyMovie.setOnAction(event -> {
            GuiCreate.modifyMovieSearch(list,stage);
        });

        deleteMovie.setOnAction(event -> {
            GuiCreate.deleteMovie(list, stage);
        });

        quitApplication.setOnAction(event -> {
            System.exit(0);
        });


        //Relative UI Pos
        grid.setAlignment(Pos.TOP_CENTER);
        GridPane.setHalignment(mainMenuHeader, HPos.CENTER);
        GridPane.setHalignment(displayMovies, HPos.CENTER);
        GridPane.setHalignment(reloadList, HPos.CENTER);
        GridPane.setHalignment(addMovie, HPos.CENTER);
        GridPane.setHalignment(modifyMovie, HPos.CENTER);
        GridPane.setHalignment(deleteMovie, HPos.CENTER);
        GridPane.setHalignment(quitApplication, HPos.CENTER);

        //Add UI to Grid
        grid.add(mainMenuHeader, 0,0);
        grid.add(displayMovies, 0,1);
        grid.add(reloadList, 0,2);
        grid.add(addMovie, 0,3);
        grid.add(modifyMovie, 0,4);
        grid.add(deleteMovie, 0,5);
        grid.add(quitApplication, 0,6);

        //Setup Scene
        Scene scene = new Scene(grid, 400,480);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void displayMovies (ObservableList<Movie> list, Stage stage){
        //Boolean Values for each button
            //Done this way to get around lambda's being annoying about setting booleans >:^(
        Map<String, Boolean> filterValues = new HashMap<>();
        filterValues.put("isAscending", true);
        filterValues.put("isByID", true);
        filterValues.put("isByName", false);
        filterValues.put("isByYear", false);
        filterValues.put("isByDirector", false);
        filterValues.put("isByComposer", false);
        filterValues.put("isByReviewScore", false);

        Movie.sortMovieList(list, filterValues);

        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(30);
        grid.setHgap(20);

        //Text
        Text header = new Text("Movies");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));
        VBox root = new VBox(header, grid);
        root.setAlignment(Pos.TOP_CENTER);

        Label avgReview = new Label("Average Review Score: " + Movie.calcMovieAvg(list));
        GridPane.setHalignment(avgReview, HPos.CENTER);



        //Table
        TableView<Movie> table = new TableView<>();
            //Columns
                //movieID
        TableColumn<Movie, Integer> IDColumn = new TableColumn<>("Movie ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("movieID"));
                //movieName
        TableColumn<Movie, String> movieNameColumn = new TableColumn<>("Movie Name");
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));
                //releaseYear
        TableColumn<Movie, Integer> releaseYearColumn = new TableColumn<>("Release Year");
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
                //director
        TableColumn<Movie, String> directorColumn = new TableColumn<>("Director");
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
                //composer
        TableColumn<Movie, String> composerColumn = new TableColumn<>("Composer");
        composerColumn.setCellValueFactory(new PropertyValueFactory<>("composer"));
                //reviewScore
        TableColumn<Movie, Double> reviewScoreColumn = new TableColumn<>("Review Score");
        reviewScoreColumn.setCellValueFactory(new PropertyValueFactory<>("reviewScore"));
            //Add Columns to Table
        table.getColumns().clear();
        table.getColumns().addAll(IDColumn, movieNameColumn,releaseYearColumn,directorColumn,composerColumn,reviewScoreColumn);
        //Kept Having an extra column for no reason, this fixes it :^)
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //Add list into table
        table.setItems(list);

        //Filters
        int buttonWidthSize = 140;
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Button ascending = new Button("Sort Ascending");
        ascending.setMinWidth(buttonWidthSize);

        Button descending = new Button("Sort Descending");
        descending.setMinWidth(buttonWidthSize);

        Button sortByID = new Button("Sort By Movie ID");
        sortByID.setMinWidth(buttonWidthSize);

        Button sortByMovieName = new Button("Sort By Movie Name");
        sortByMovieName.setMinWidth(buttonWidthSize);

        Button sortByReleaseYear = new Button("Sort By Release Year");
        sortByReleaseYear.setMinWidth(buttonWidthSize);

        Button sortByDirector = new Button("Sort By Director");
        sortByDirector.setMinWidth(buttonWidthSize);

        Button sortByComposer = new Button("Sort By Composer");
        sortByComposer.setMinWidth(buttonWidthSize);

        Button sortByReviewScore = new Button("Sort By Review Score");
        sortByReviewScore.setMinWidth(buttonWidthSize);

        hBox.getChildren().addAll(ascending, descending, sortByID, sortByMovieName, sortByReleaseYear, sortByDirector, sortByComposer, sortByReviewScore);

        //Menu Buttons
        Button returnToMenu = new Button("Main Menu");

        //Give Buttons Functionality
            //Changes sort to Asc
        ascending.setOnAction(event -> {
            filterValues.put("isAscending", true);
            Movie.sortMovieList(list, filterValues);
        });
            //Changes Sort to Desc
        descending.setOnAction(event -> {
            filterValues.put("isAscending", false);
            Movie.sortMovieList(list, filterValues);
        });

        //There's likely a better way, but this works so ¯\_(ツ)_/¯
            //Changes sort to be by movieID
        sortByID.setOnAction(event -> {
            filterValues.put("isByID", true);
            filterValues.put("isByName", false);
            filterValues.put("isByYear", false);
            filterValues.put("isByDirector", false);
            filterValues.put("isByComposer", false);
            filterValues.put("isByReviewScore", false);
            Movie.sortMovieList(list, filterValues);
        });
        //Changes sort to be by movieName
        sortByMovieName.setOnAction(event -> {
            filterValues.put("isByID", false);
            filterValues.put("isByName", true);
            filterValues.put("isByYear", false);
            filterValues.put("isByDirector", false);
            filterValues.put("isByComposer", false);
            filterValues.put("isByReviewScore", false);
            Movie.sortMovieList(list, filterValues);
        });
        //Changes sort to be by releaseYear
        sortByReleaseYear.setOnAction(event -> {
            filterValues.put("isByID", false);
            filterValues.put("isByName", false);
            filterValues.put("isByYear", true);
            filterValues.put("isByDirector", false);
            filterValues.put("isByComposer", false);
            filterValues.put("isByReviewScore", false);
            Movie.sortMovieList(list, filterValues);
        });
        //Changes sort to be by director
        sortByDirector.setOnAction(event -> {
            filterValues.put("isByID", false);
            filterValues.put("isByName", false);
            filterValues.put("isByYear", false);
            filterValues.put("isByDirector", true);
            filterValues.put("isByComposer", false);
            filterValues.put("isByReviewScore", false);
            Movie.sortMovieList(list, filterValues);
        });
        //Changes sort to be by composer
        sortByComposer.setOnAction(event -> {
            filterValues.put("isByID", false);
            filterValues.put("isByName", false);
            filterValues.put("isByYear", false);
            filterValues.put("isByDirector", false);
            filterValues.put("isByComposer", true);
            filterValues.put("isByReviewScore", false);
            Movie.sortMovieList(list, filterValues);
        });
        //Changes sort to be by reviewScore
        sortByReviewScore.setOnAction(event -> {
            filterValues.put("isByID", false);
            filterValues.put("isByName", false);
            filterValues.put("isByYear", false);
            filterValues.put("isByDirector", false);
            filterValues.put("isByComposer", false);
            filterValues.put("isByReviewScore", true);
            Movie.sortMovieList(list, filterValues);
        });
        //Returns to Main Menu
        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //UI relativePos
        grid.setAlignment(Pos.TOP_CENTER);

        table.setMinWidth(600);
        returnToMenu.setAlignment(Pos.BOTTOM_RIGHT);

        GridPane.setHalignment(ascending,HPos.CENTER);
        GridPane.setHalignment(descending,HPos.CENTER);
        GridPane.setHalignment(sortByID,HPos.CENTER);
        GridPane.setHalignment(sortByMovieName,HPos.CENTER);
        GridPane.setHalignment(sortByReleaseYear,HPos.CENTER);
        GridPane.setHalignment(sortByDirector,HPos.CENTER);
        GridPane.setHalignment(sortByComposer,HPos.CENTER);
        GridPane.setHalignment(sortByReviewScore,HPos.CENTER);
        GridPane.setHalignment(returnToMenu, HPos.RIGHT);

        //Add UI to Grid
        grid.add(ascending,0,1);
        grid.add(descending,0,2);
        grid.add(sortByID,3,1);
        grid.add(sortByMovieName,3,2);
        grid.add(sortByReleaseYear,4,1);
        grid.add(sortByDirector,4,2);
        grid.add(sortByComposer,5,1);
        grid.add(sortByReviewScore,5,2);
        grid.add(table, 0,3,6,5);
        grid.add(returnToMenu, 5,8,1,1);
        grid.add(avgReview,0,8);

        //Setup Scene
        Scene scene = new Scene(root, 700, 650);
        stage.setTitle("Display Movies");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void loadMovies (ObservableList<Movie> list, Stage stage){
        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(15);
        grid.setHgap(10);

        //Text
        Text header = new Text("Load Movies");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));
        GridPane.setHalignment(header, HPos.CENTER);

        Text chooseFileText = new Text("Please Choose The Text\n     File To Load From:");
        chooseFileText.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 25));
        GridPane.setHalignment(chooseFileText, HPos.CENTER);


        //Menu Buttons
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Movies File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        Button returnToMenu = new Button("Main Menu");
        GridPane.setHalignment(returnToMenu, HPos.CENTER);

        Button fileOpen = new Button("Browse...");
        fileOpen.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        GridPane.setHalignment(fileOpen, HPos.CENTER);

        //Give Buttons Functionality
            //Actions for when browsing for a file
        fileOpen.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if(file != null) {
                String filePath = file.getAbsolutePath();
                //If file is found and successfully
                if(FileOps.hotloadList(list, filePath)){
                    Stage successPopup = new Stage();
                    //Makes sure you cannot interact with main stage
                    successPopup.initModality(Modality.APPLICATION_MODAL);
                    successPopup.setTitle("Successful Read");

                    Label successDescription = new Label("File Successfully Read and Imported");
                    successDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                    successDescription.setAlignment(Pos.CENTER);

                    Button acknowledge = new Button("Yipee!");
                    acknowledge.setAlignment(Pos.BOTTOM_CENTER);

                    VBox popupRoot = new VBox();
                    popupRoot.setAlignment(Pos.CENTER);
                    popupRoot.setSpacing(30);
                    popupRoot.getChildren().addAll(successDescription, acknowledge);

                    //acknowledge button action
                    acknowledge.setOnAction(finalEvent -> {
                        GuiCreate.mainMenu(list, stage);
                        successPopup.close();
                    });

                    Scene popupScene = new Scene(popupRoot, 300,100);
                    successPopup.setScene(popupScene);
                    successPopup.showAndWait();


                }
                else{//Failure to import file
                    Stage failurePopup = new Stage();
                    //Makes sure you cannot interact with main stage
                    failurePopup.initModality(Modality.APPLICATION_MODAL);
                    failurePopup.setTitle("Error");

                    Label failureDescription = new Label("Error in File, cannot import:\n Check Formatting");
                    failureDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                    failureDescription.setAlignment(Pos.CENTER);

                    Button acknowledge = new Button(":^(");

                    VBox popupRoot = new VBox();
                    popupRoot.setAlignment(Pos.CENTER);
                    popupRoot.setSpacing(30);
                    popupRoot.getChildren().addAll(failureDescription, acknowledge);
                    //Acknowledge button action
                    acknowledge.setOnAction(finalEvent -> {
                        failurePopup.close();
                    });

                    Scene popupScene = new Scene(popupRoot, 200,100);
                    failurePopup.setScene(popupScene);
                    failurePopup.showAndWait();

                }

            }//end if (File != null)
        });
            //Returns to menu
        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //Add UI to Grid
        grid.getChildren().addAll(header,chooseFileText,fileOpen,returnToMenu);

        //Position UI
        GridPane.setConstraints(header,0,0);
        GridPane.setConstraints(chooseFileText,0,1);
        GridPane.setConstraints(fileOpen,0,2);
        GridPane.setConstraints(returnToMenu, 0,4);

        //Setup Scene
        Scene scene = new Scene(grid, 300,300);
        stage.setTitle("Load New Movies");
        stage.setScene(scene);
        stage.show();

    }

    public static void addMovie(ObservableList<Movie> list, Stage stage){

        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(15);
        grid.setHgap(20);

        //Text
        Text header = new Text("Add A Movie");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));

        //Fields
        TextField movieID = new TextField();
        GridPane.setHalignment(movieID, HPos.CENTER);

        Label movieIDLabel = new Label("Movie ID:");
        GridPane.setHalignment(movieIDLabel, HPos.CENTER);

        TextField movieName = new TextField();
        GridPane.setHalignment(movieName, HPos.CENTER);

        Label movieNameLabel = new Label("Movie Name:");
        GridPane.setHalignment(movieNameLabel, HPos.CENTER);

        TextField releaseYear = new TextField();
        GridPane.setHalignment(releaseYear, HPos.CENTER);

        Label releaseYearLabel = new Label("Release Year:");
        GridPane.setHalignment(releaseYearLabel, HPos.CENTER);

        TextField director = new TextField();
        GridPane.setHalignment(director, HPos.CENTER);

        Label directorLabel = new Label("Director:");
        GridPane.setHalignment(directorLabel, HPos.CENTER);

        TextField composer = new TextField();
        GridPane.setHalignment(composer, HPos.CENTER);

        Label composerLabel = new Label("Composer:");
        GridPane.setHalignment(composerLabel, HPos.CENTER);

        TextField reviewScore = new TextField();
        GridPane.setHalignment(reviewScore, HPos.CENTER);

        Label reviewScoreLabel = new Label("Review Score:");
        GridPane.setHalignment(reviewScoreLabel, HPos.CENTER);

        //Error Messages
        Label idError = new Label();
        GridPane.setHalignment(idError, HPos.CENTER);
        idError.setStyle("-fx-text-fill: red;");
        idError.setVisible(false);

        Label nameError = new Label();
        GridPane.setHalignment(nameError, HPos.CENTER);
        nameError.setStyle("-fx-text-fill: red;");
        nameError.setVisible(false);

        Label yearError = new Label();
        GridPane.setHalignment(yearError, HPos.CENTER);
        yearError.setStyle("-fx-text-fill: red;");
        yearError.setVisible(false);

        Label directorError = new Label();
        GridPane.setHalignment(directorError, HPos.CENTER);
        directorError.setStyle("-fx-text-fill: red;");
        directorError.setVisible(false);

        Label composerError = new Label();
        GridPane.setHalignment(composerError, HPos.CENTER);
        composerError.setStyle("-fx-text-fill: red;");
        composerError.setVisible(false);

        Label reviewScoreError = new Label();
        GridPane.setHalignment(reviewScoreError, HPos.CENTER);
        reviewScoreError.setStyle("-fx-text-fill: red;");
        reviewScoreError.setVisible(false);



        //Menu Buttons
        Button returnToMenu = new Button("Main Menu");
        GridPane.setHalignment(returnToMenu, HPos.CENTER);

        Button submit = new Button("Submit");
        GridPane.setHalignment(submit, HPos.CENTER);

        //Give Buttons Functionality
            //Submission Action
        submit.setOnAction(event -> {
            Movie newMovie = new Movie();
            int currentStatus;
            //LMAO:    Basically its getting the state (via int) from the setters, and 0 is success, anything else is error
            if((currentStatus = newMovie.setMovieID(movieID.getText())) == 0){
                idError.setVisible(false);
                if((currentStatus = newMovie.setMovieName(movieName.getText())) == 0){
                    if((currentStatus = newMovie.setReleaseYear(releaseYear.getText())) == 0){
                        yearError.setVisible(false);
                        if((currentStatus = newMovie.setDirector(director.getText())) == 0){
                            if((currentStatus = newMovie.setComposer(composer.getText())) == 0){
                                if((currentStatus = newMovie.setReviewScore(reviewScore.getText())) == 0){

                                    reviewScoreError.setVisible(false);

                                    //Check if duplicate ID
                                    boolean dupe = false;
                                    for (Movie movie : list) {
                                        if(newMovie.getMovieID() == movie.getMovieID()){
                                            dupe = true;
                                            break;
                                        }
                                    }

                                    if(!dupe){
                                        list.add(newMovie);
                                        list.sort(MovieComparator.sortByIDAsc());
                                        //show success dialog
                                        Stage successPopup = new Stage();
                                        //Makes sure you cannot interact with main stage
                                        successPopup.initModality(Modality.APPLICATION_MODAL);
                                        successPopup.setTitle("Success!");

                                        Label successDescription = new Label("Movie Successfully Created!");
                                        successDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                                        successDescription.setAlignment(Pos.CENTER);

                                        Button acknowledge = new Button("Yipee!");
                                        acknowledge.setAlignment(Pos.BOTTOM_CENTER);

                                        VBox popupRoot = new VBox();
                                        popupRoot.setAlignment(Pos.CENTER);
                                        popupRoot.setSpacing(30);
                                        popupRoot.getChildren().addAll(successDescription, acknowledge);

                                        //acknowledge button action
                                        acknowledge.setOnAction(finalEvent -> {
                                            GuiCreate.mainMenu(list, stage);
                                            successPopup.close();
                                        });

                                        Scene popupScene = new Scene(popupRoot, 300,100);
                                        successPopup.setScene(popupScene);
                                        successPopup.showAndWait();
                                    }
                                    else{
                                        idError.setVisible(true);
                                        idError.setText("Invalid: ID already exists");
                                    }
                                }
                                else if(currentStatus == 1){//review score Error 1
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("The Score Must Be at least 0.1");
                                }
                                else if(currentStatus == 2){//review score error 2
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("The Score Must Be less than or equal to 10.00");
                                }
                                else if(currentStatus == 3){
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("Invalid, Must Be a number greater than or equal to 0 and also less than or equal to 10");
                                }
                            }//end composer check
                        }//End Director check
                    }
                    else if(currentStatus == 1) {//Year Error 1
                        yearError.setVisible(true);
                        yearError.setText("Movie must be released as of or later than 1888");
                    }
                    else if(currentStatus == 2) {//Year Error 2
                        yearError.setVisible(true);
                        yearError.setText("Invalid, Must Be a Whole Number Greater Than or Equal to 1888");
                    }//End Year Check
                }//End Name Check
            }
            else if(currentStatus == 1) {//Movie ID Error 1
                idError.setVisible(true);
                idError.setText("Invalid, ID must be greater than 0");
            }
            else if(currentStatus == 2) {//Movie ID Error 2
                idError.setVisible(true);
                idError.setText("Please Enter a Whole Number Greater Than 0");
            }//endmovieID check

        });


        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //Add UI to Grid
        grid.setAlignment(Pos.TOP_CENTER);
        grid.getChildren().addAll(header,movieID,movieIDLabel,movieName,movieNameLabel,releaseYear,releaseYearLabel,
                director,directorLabel,composer,composerLabel,reviewScore,reviewScoreLabel,submit,returnToMenu,
                idError,nameError,yearError,directorError,composerError,reviewScoreError
        );

        //Position UI
        GridPane.setConstraints(movieIDLabel, 0, 1);
        GridPane.setConstraints(idError, 0,2);
        GridPane.setConstraints(movieID, 0, 3);

        GridPane.setConstraints(movieNameLabel, 0, 4);
        GridPane.setConstraints(nameError, 0,5);
        GridPane.setConstraints(movieName, 0, 6);

        GridPane.setConstraints(releaseYearLabel, 0, 7);
        GridPane.setConstraints(yearError, 0,8);
        GridPane.setConstraints(releaseYear, 0, 9);

        GridPane.setConstraints(directorLabel, 0, 10);
        GridPane.setConstraints(directorError, 0,11);
        GridPane.setConstraints(director, 0, 12);

        GridPane.setConstraints(composerLabel, 0, 13);
        GridPane.setConstraints(composerError, 0,14);
        GridPane.setConstraints(composer, 0, 15);

        GridPane.setConstraints(reviewScoreLabel, 0, 16);
        GridPane.setConstraints(reviewScoreError, 0,17);
        GridPane.setConstraints(reviewScore, 0, 18);

        GridPane.setConstraints(submit, 0, 19);
        GridPane.setConstraints(returnToMenu, 0, 20);

        ScrollPane scrollableWindow = new ScrollPane(grid);
        scrollableWindow.setFitToWidth(true);



        //Setup Scene
        Scene scene = new Scene(scrollableWindow, 400,700);
        stage.setTitle("Add A Movie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void modifyMovieSearch(ObservableList<Movie> list, Stage stage){
        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(10);
        grid.setVgap(15);

        //Text
        Text header = new Text("Modify a Movie");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));
        GridPane.setHalignment(header,HPos.CENTER);

        Label searchLabel = new Label("Search By Movie ID or Name:");
        GridPane.setHalignment(searchLabel, HPos.CENTER);

        //Error Display
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        errorLabel.setVisible(false);


        //Fields
        TextField movieSearch = new TextField();
        GridPane.setHalignment(movieSearch, HPos.CENTER);

        //Menu Buttons
        Button search = new Button("Search");
        GridPane.setHalignment(search, HPos.CENTER);


        Button returnToMenu = new Button("Main Menu");
        GridPane.setHalignment(returnToMenu, HPos.CENTER);

        //Give Buttons Functionality
        search.setOnAction(event -> {
            int result = Movie.movieSearch(list, movieSearch.getText());
            if (result == -1){
                errorLabel.setVisible(true);
                errorLabel.setText("Movie Not Found");
            }
            else{//Show success finding movie:
                Stage successPopup = new Stage();
                //Makes sure you cannot interact with main stage
                successPopup.initModality(Modality.APPLICATION_MODAL);
                successPopup.setTitle("Movie Found");

                Label successDescription = new Label("Movie Has Been Found");
                successDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                successDescription.setAlignment(Pos.CENTER);

                Button acknowledge = new Button("Proceed");
                acknowledge.setAlignment(Pos.BOTTOM_CENTER);

                VBox popupRoot = new VBox();
                popupRoot.setAlignment(Pos.CENTER);
                popupRoot.setSpacing(30);
                popupRoot.getChildren().addAll(successDescription, acknowledge);

                //acknowledge button action
                acknowledge.setOnAction(finalEvent -> {
                    GuiCreate.modifyMovie(list,result,stage);
                    successPopup.close();
                });

                Scene popupScene = new Scene(popupRoot, 300,100);
                successPopup.setScene(popupScene);
                successPopup.showAndWait();

            }

        });

        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //Add UI to Grid
        grid.getChildren().addAll(header, searchLabel, errorLabel, movieSearch, search, returnToMenu);

        //Position UI
        GridPane.setConstraints(header, 0,0);

        GridPane.setConstraints(searchLabel, 0,1);
        GridPane.setConstraints(errorLabel, 0,2);
        GridPane.setConstraints(movieSearch, 0 ,3);
        GridPane.setConstraints(search, 0,4);

        GridPane.setConstraints(returnToMenu, 0,5);

        //Setup Scene
        Scene scene = new Scene(grid, 300,350);
        stage.setTitle("Modify A Movie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void modifyMovie(ObservableList<Movie> list, int index, Stage stage){
        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(15);
        grid.setHgap(20);

        //Text
        Text header = new Text("Modify A Movie");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));

        //Fields
        TextField movieID = new TextField(Integer.toString(list.get(index).getMovieID()));
        GridPane.setHalignment(movieID, HPos.CENTER);

        Label movieIDLabel = new Label("Movie ID:");
        GridPane.setHalignment(movieIDLabel, HPos.CENTER);

        TextField movieName = new TextField(list.get(index).getMovieName());
        GridPane.setHalignment(movieName, HPos.CENTER);

        Label movieNameLabel = new Label("Movie Name:");
        GridPane.setHalignment(movieNameLabel, HPos.CENTER);

        TextField releaseYear = new TextField(Integer.toString(list.get(index).getReleaseYear()));
        GridPane.setHalignment(releaseYear, HPos.CENTER);

        Label releaseYearLabel = new Label("Release Year:");
        GridPane.setHalignment(releaseYearLabel, HPos.CENTER);

        TextField director = new TextField(list.get(index).getDirector());
        GridPane.setHalignment(director, HPos.CENTER);

        Label directorLabel = new Label("Director:");
        GridPane.setHalignment(directorLabel, HPos.CENTER);

        TextField composer = new TextField(list.get(index).getComposer());
        GridPane.setHalignment(composer, HPos.CENTER);

        Label composerLabel = new Label("Composer:");
        GridPane.setHalignment(composerLabel, HPos.CENTER);

        TextField reviewScore = new TextField(Double.toString(list.get(index).getReviewScore()));
        GridPane.setHalignment(reviewScore, HPos.CENTER);

        Label reviewScoreLabel = new Label("Review Score:");
        GridPane.setHalignment(reviewScoreLabel, HPos.CENTER);

        //Error Messages
        Label idError = new Label();
        GridPane.setHalignment(idError, HPos.CENTER);
        idError.setStyle("-fx-text-fill: red;");
        idError.setVisible(false);

        Label nameError = new Label();
        GridPane.setHalignment(nameError, HPos.CENTER);
        nameError.setStyle("-fx-text-fill: red;");
        nameError.setVisible(false);

        Label yearError = new Label();
        GridPane.setHalignment(yearError, HPos.CENTER);
        yearError.setStyle("-fx-text-fill: red;");
        yearError.setVisible(false);

        Label directorError = new Label();
        GridPane.setHalignment(directorError, HPos.CENTER);
        directorError.setStyle("-fx-text-fill: red;");
        directorError.setVisible(false);

        Label composerError = new Label();
        GridPane.setHalignment(composerError, HPos.CENTER);
        composerError.setStyle("-fx-text-fill: red;");
        composerError.setVisible(false);

        Label reviewScoreError = new Label();
        GridPane.setHalignment(reviewScoreError, HPos.CENTER);
        reviewScoreError.setStyle("-fx-text-fill: red;");
        reviewScoreError.setVisible(false);



        //Menu Buttons
        Button returnToMenu = new Button("Main Menu");
        GridPane.setHalignment(returnToMenu, HPos.CENTER);

        Button submit = new Button("Submit");
        GridPane.setHalignment(submit, HPos.CENTER);

        //Give Buttons Functionality
        //Submission Action
        submit.setOnAction(event -> {
            Movie originalMovie = list.get(index);
            Movie newMovie = new Movie();
            int currentStatus;
            //LMAO:    Basically its getting the state (via int) from the setters, and 0 is success, anything else is error
            if((currentStatus = newMovie.setMovieID(movieID.getText())) == 0){
                idError.setVisible(false);
                if((currentStatus = newMovie.setMovieName(movieName.getText())) == 0){
                    if((currentStatus = newMovie.setReleaseYear(releaseYear.getText())) == 0){
                        yearError.setVisible(false);
                        if((currentStatus = newMovie.setDirector(director.getText())) == 0){
                            if((currentStatus = newMovie.setComposer(composer.getText())) == 0){
                                if((currentStatus = newMovie.setReviewScore(reviewScore.getText())) == 0){

                                    reviewScoreError.setVisible(false);
                                    list.remove(originalMovie);

                                    //Check if duplicate ID
                                    boolean acceptableDupes;
                                    int dupes = 0;
                                    for (Movie movie : list) {
                                        if(newMovie.getMovieID() == movie.getMovieID()){
                                            dupes++;
                                            break;
                                        }
                                    }

                                    if(dupes > 0){acceptableDupes = false;}
                                    else{acceptableDupes = true;}

                                    if(acceptableDupes){
                                        list.add(newMovie);
                                        list.sort(MovieComparator.sortByIDAsc());
                                        //show success dialog
                                        Stage successPopup = new Stage();
                                        //Makes sure you cannot interact with main stage
                                        successPopup.initModality(Modality.APPLICATION_MODAL);
                                        successPopup.setTitle("Success!");

                                        Label successDescription = new Label("Movie Successfully Modified!");
                                        successDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                                        successDescription.setAlignment(Pos.CENTER);

                                        Button acknowledge = new Button("Yipee!");
                                        acknowledge.setAlignment(Pos.BOTTOM_CENTER);

                                        VBox popupRoot = new VBox();
                                        popupRoot.setAlignment(Pos.CENTER);
                                        popupRoot.setSpacing(30);
                                        popupRoot.getChildren().addAll(successDescription, acknowledge);

                                        //acknowledge button action
                                        acknowledge.setOnAction(finalEvent -> {
                                            GuiCreate.mainMenu(list, stage);
                                            successPopup.close();
                                        });

                                        Scene popupScene = new Scene(popupRoot, 300,100);
                                        successPopup.setScene(popupScene);
                                        successPopup.showAndWait();
                                    }
                                    else{
                                        idError.setVisible(true);
                                        idError.setText("Invalid: ID already exists");
                                        list.add(originalMovie);
                                        Collections.sort(list, MovieComparator.sortByIDAsc());
                                    }
                                }
                                else if(currentStatus == 1){//review score Error 1
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("The Score Must Be at least 0.1");
                                }
                                else if(currentStatus == 2){//review score error 2
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("The Score Must Be less than or equal to 10.00");
                                }
                                else if(currentStatus == 3){
                                    reviewScoreError.setVisible(true);
                                    reviewScoreError.setText("Invalid, Must Be a number greater than or equal to 0 and also less than or equal to 10");
                                }
                            }//end composer check
                        }//End Director check
                    }
                    else if(currentStatus == 1) {//Year Error 1
                        yearError.setVisible(true);
                        yearError.setText("Movie must be released as of or later than 1888");
                    }
                    else if(currentStatus == 2) {//Year Error 2
                        yearError.setVisible(true);
                        yearError.setText("Invalid, Must Be a Whole Number Greater Than or Equal to 1888");
                    }//End Year Check
                }//End Name Check
            }
            else if(currentStatus == 1) {//Movie ID Error 1
                idError.setVisible(true);
                idError.setText("Invalid, ID must be greater than 0");
            }
            else if(currentStatus == 2) {//Movie ID Error 2
                idError.setVisible(true);
                idError.setText("Please Enter a Whole Number Greater Than 0");
            }//endmovieID check

        });


        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //Add UI to Grid
        grid.setAlignment(Pos.TOP_CENTER);
        grid.getChildren().addAll(header,movieID,movieIDLabel,movieName,movieNameLabel,releaseYear,releaseYearLabel,
                director,directorLabel,composer,composerLabel,reviewScore,reviewScoreLabel,submit,returnToMenu,
                idError,nameError,yearError,directorError,composerError,reviewScoreError
        );

        //Position UI
        GridPane.setConstraints(movieIDLabel, 0, 1);
        GridPane.setConstraints(idError, 0,2);
        GridPane.setConstraints(movieID, 0, 3);

        GridPane.setConstraints(movieNameLabel, 0, 4);
        GridPane.setConstraints(nameError, 0,5);
        GridPane.setConstraints(movieName, 0, 6);

        GridPane.setConstraints(releaseYearLabel, 0, 7);
        GridPane.setConstraints(yearError, 0,8);
        GridPane.setConstraints(releaseYear, 0, 9);

        GridPane.setConstraints(directorLabel, 0, 10);
        GridPane.setConstraints(directorError, 0,11);
        GridPane.setConstraints(director, 0, 12);

        GridPane.setConstraints(composerLabel, 0, 13);
        GridPane.setConstraints(composerError, 0,14);
        GridPane.setConstraints(composer, 0, 15);

        GridPane.setConstraints(reviewScoreLabel, 0, 16);
        GridPane.setConstraints(reviewScoreError, 0,17);
        GridPane.setConstraints(reviewScore, 0, 18);

        GridPane.setConstraints(submit, 0, 19);
        GridPane.setConstraints(returnToMenu, 0, 20);

        ScrollPane scrollableWindow = new ScrollPane(grid);
        scrollableWindow.setFitToWidth(true);



        //Setup Scene
        Scene scene = new Scene(scrollableWindow, 400,700);
        stage.setTitle("Add A Movie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void deleteMovie(ObservableList<Movie> list, Stage stage){
        //Create Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(15);
        grid.setHgap(12);

        //Text
        Text header = new Text("Modify a Movie");
        header.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 38));
        GridPane.setHalignment(header,HPos.CENTER);

        Label searchLabel = new Label("Search By Movie ID or Name:");
        GridPane.setHalignment(searchLabel, HPos.CENTER);

        //Error Display
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        errorLabel.setVisible(false);


        //Fields
        TextField movieSearch = new TextField();
        GridPane.setHalignment(movieSearch, HPos.CENTER);

        //Menu Buttons
        Button search = new Button("Search");
        GridPane.setHalignment(search, HPos.CENTER);


        Button returnToMenu = new Button("Main Menu");
        GridPane.setHalignment(returnToMenu, HPos.CENTER);

        //Give Buttons Functionality
        search.setOnAction(event -> {
            int result = Movie.movieSearch(list, movieSearch.getText());
            if (result == -1){
                errorLabel.setVisible(true);
                errorLabel.setText("Movie Not Found");
            }
            else{//Show success finding movie:
                Stage successPopup = new Stage();
                //Makes sure you cannot interact with main stage
                successPopup.initModality(Modality.APPLICATION_MODAL);
                successPopup.setTitle("Movie Found");

                Label successDescription = new Label("Movie Has Been Found");
                successDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
                successDescription.setAlignment(Pos.CENTER);
                //shows movie info
                Label movieInfo = new Label(list.get(result).toString());
                movieInfo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
                movieInfo.setAlignment(Pos.CENTER);
                movieInfo.setWrapText(true);
                movieInfo.maxWidth(250);

                Label yesNo = new Label ("Would You Like to Delete This Movie?");
                yesNo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
                yesNo.setAlignment(Pos.CENTER);



                Button yes = new Button("Yes");
                yes.setAlignment(Pos.BOTTOM_RIGHT);
                Button no = new Button("No");
                no.setAlignment(Pos.BOTTOM_LEFT);

                //Container to have buttons fit on same row
                HBox bothButtons = new HBox(20);
                bothButtons.getChildren().addAll(yes,no);
                bothButtons.setAlignment(Pos.CENTER);


                VBox popupRoot = new VBox();
                popupRoot.setAlignment(Pos.CENTER);
                popupRoot.setSpacing(20);
                popupRoot.getChildren().addAll(successDescription,movieInfo,yesNo,bothButtons);

                //acknowledge button action
                yes.setOnAction(newEvent -> {
                    list.remove(result);
                    errorLabel.setVisible(false);
                    movieSearch.clear();
                    successPopup.close();
                });

                no.setOnAction(newerEvent ->{
                    movieSearch.clear();
                    errorLabel.setVisible(false);
                    successPopup.close();
                });

                Scene popupScene = new Scene(popupRoot, 350,200);
                successPopup.setScene(popupScene);
                successPopup.showAndWait();

            }

        });

        returnToMenu.setOnAction(event -> {
            GuiCreate.mainMenu(list, stage);
        });

        //Add UI to Grid
        grid.getChildren().addAll(header, searchLabel, errorLabel, movieSearch, search, returnToMenu);

        //Position UI
        GridPane.setConstraints(header, 0,0);

        GridPane.setConstraints(searchLabel, 0,1);
        GridPane.setConstraints(errorLabel, 0,2);
        GridPane.setConstraints(movieSearch, 0 ,3);
        GridPane.setConstraints(search, 0,4);

        GridPane.setConstraints(returnToMenu, 0,5);

        //Setup Scene
        Scene scene = new Scene(grid, 300,350);
        stage.setTitle("Delete A Movie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}