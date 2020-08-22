package io.github.pxlop.twenty;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class Settings {
    
    public static void display() {
        //Set-up GridPane
        GridPane gridPane = new GridPane();
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        //Title label
        Label label = new Label("Settings");
        label.setStyle("-fx-font-size: 24;");
        
        //Minutes label
        Label minutesLabel = new Label("Minutes:");
        GridPane.setConstraints(minutesLabel, 0, 0);
        
        //Minutes input
        TextField minutesInput = new TextField();
        minutesInput.setPromptText("minutes");
        minutesInput.setText(String.valueOf(Main.mins));
        GridPane.setConstraints(minutesInput, 1, 0);
        
        //Seconds label
        Label secondsLabel = new Label("Seconds:");
        GridPane.setConstraints(secondsLabel, 0, 1);
        
        //Seconds input
        TextField secondsInput = new TextField();
        secondsInput.setPromptText("seconds");
        secondsInput.setText(String.valueOf(Main.secs));
        GridPane.setConstraints(secondsInput, 1, 1);
        
        //Add all to gridPane
        gridPane.getChildren().addAll(minutesLabel, minutesInput, secondsLabel, secondsInput);
        
        //Play sound after minutes timer input
        CheckBox playAfterMinsInput = new CheckBox("Play sound after minutes timer ends");
        playAfterMinsInput.setSelected(Main.playAfterMins);
        
        //Play sound after seconds timer input
        CheckBox playAfterSecsInput = new CheckBox("Play sound after seconds timer ends");
        playAfterSecsInput.setSelected(Main.playAfterSecs);
        
        //VBox for the CheckBoxes
        VBox checkBoxes = new VBox(8);
        checkBoxes.setAlignment(Pos.CENTER);
        checkBoxes.getChildren().addAll(playAfterMinsInput, playAfterSecsInput);
        
        //Ok button
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            try {
                //Set the variables
                int minutes = Integer.parseInt(minutesInput.getText());
                int seconds = Integer.parseInt(secondsInput.getText());
                
                //Handle if the variables are out of bounds
                if(minutes > 59) minutes = 59;
                else if(minutes < 1) minutes = 1;
                if(seconds > 59) seconds = 59;
                else if(seconds < 1) seconds = 1;
                
                Main.updateOptions(minutes, seconds, playAfterMinsInput.isSelected(), playAfterSecsInput.isSelected());
                
                Main.setSceneMain();
            }
            catch(NumberFormatException numberFormatException) {
                label.setText("Please enter valid numbers");
            }
            catch(FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        okButton.setStyle("-fx-background-radius: 0;");
        
        //Cancel button
        Button cancelButton = new Button("Close");
        cancelButton.setOnAction(e -> Main.setSceneMain());
        cancelButton.setStyle("-fx-background-radius: 0;");
        
        //Apply button
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            try {
                //Set the variables
                int minutes = Integer.parseInt(minutesInput.getText());
                int seconds = Integer.parseInt(secondsInput.getText());
                
                //Handle if the variables are out of bounds
                if(minutes > 59) minutes = 59;
                else if(minutes < 1) minutes = 1;
                if(seconds > 59) seconds = 59;
                else if(seconds < 1) seconds = 1;
                
                Main.updateOptions(minutes, seconds, playAfterMinsInput.isSelected(), playAfterSecsInput.isSelected());
                
                cancelButton.setText("Close");
            }
            catch(NumberFormatException numberFormatException) {
                label.setText("Please enter valid numbers");
            }
            catch(FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        applyButton.setStyle("-fx-background-radius: 0;");
        
        //HBox for buttons
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(okButton, cancelButton, applyButton);
        
        //Vbox containing everything
        VBox layout = new VBox(8);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, gridPane, checkBoxes, buttons);
        
        //Set the scene
        Main.scene.setRoot(layout);
        Main.window.setTitle("Settings");
        
        //Listeners to change the cancel/close button if the text in the inputs changes
        minutesInput.textProperty().addListener((observable, oldValue, newValue) -> cancelButton.setText("Cancel"));
        secondsInput.textProperty().addListener((observable, oldValue, newValue) -> cancelButton.setText("Cancel"));
        playAfterMinsInput.selectedProperty().addListener((observable, oldValue, newValue) -> cancelButton.setText("Cancel"));
        playAfterSecsInput.selectedProperty().addListener((observable, oldValue, newValue) -> cancelButton.setText("Cancel"));
    }
}
