package io.github.Pxlop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class Settings {
    
    public static void display() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        //Title label
        Label label = new Label("Settings");
        label.setStyle("-fx-font-size: 24;");
        GridPane.setConstraints(label, 1, 0);
        
        //Minutes label
        Label minutesLabel = new Label("Minutes:");
        GridPane.setConstraints(minutesLabel, 0, 1);
        
        //Minutes input
        TextField minutesInput = new TextField();
        minutesInput.setPromptText("minutes");
        GridPane.setConstraints(minutesInput, 1, 1);
        
        //Seconds label
        Label secondsLabel = new Label("Seconds:");
        GridPane.setConstraints(secondsLabel, 0, 2);
        
        //Seconds input
        TextField secondsInput = new TextField();
        secondsInput.setPromptText("seconds");
        GridPane.setConstraints(secondsInput, 1, 2);
    
        gridPane.getChildren().addAll(label, minutesLabel, minutesInput, secondsLabel, secondsInput);
        
        //Ok button
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            try {
                int minutes = Integer.parseInt(minutesInput.getText());
                int seconds = Integer.parseInt(secondsInput.getText());
                if(minutes > 59) minutes = 59;
                else if(minutes < 1) minutes = 1;
                if(seconds > 59) seconds = 59;
                else if(seconds < 1) seconds = 1;
                Main.updateMinsAndSecs(minutes, seconds);
                Main.setSceneMain();
            }catch(NumberFormatException numberFormatException) {
                label.setText("Please enter valid numbers");
            }catch(FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        okButton.setStyle("-fx-background-radius: 0;");
        
        //Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> Main.setSceneMain());
        cancelButton.setStyle("-fx-background-radius: 0;");
        
        //Apply button
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            try {
                int minutes = Integer.parseInt(minutesInput.getText());
                int seconds = Integer.parseInt(secondsInput.getText());
                if(minutes > 59) minutes = 59;
                else if(minutes < 1) minutes = 1;
                if(seconds > 59) seconds = 59;
                else if(seconds < 1) seconds = 1;
                Main.updateMinsAndSecs(minutes, seconds);
            }catch(NumberFormatException numberFormatException) {
                label.setText("Please enter valid numbers");
            }catch(FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        applyButton.setStyle("-fx-background-radius: 0;");
        
        //HBox for buttons
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(okButton, cancelButton, applyButton);
        
        //Vbox containing everything
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gridPane, buttons);
        
        Scene scene = new Scene(layout, 600, 400);
        Main.window.setScene(scene);
        Main.window.setTitle("Settings");
    }
}
