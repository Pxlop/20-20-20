package io.github.pxlop.twenty;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    
    public static void display(String title, String message, Runnable runAfterClose) {
        //Set-up new Stage
        Stage window = new Stage();
        window.getIcons().add(new Image(Main.class.getResourceAsStream("files" + "/icon.png")));
        window.initModality(Modality.APPLICATION_MODAL); //Block events to other windows
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);
        
        //Message label
        Label label = new Label();
        label.setText(message);
        
        //Close button
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-radius: 0;");
        closeButton.setOnAction(e -> {
            window.close();
            runAfterClose.run();
        });
        
        //VBox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        //Set scene and show
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    public static void display(String title, String message) {
        //Set-up new Stage
        Stage window = new Stage();
        window.getIcons().add(new Image(Main.class.getResourceAsStream("files" + "/icon.png")));
        window.initModality(Modality.APPLICATION_MODAL); //Block events to other windows
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);
        
        //Message label
        Label label = new Label();
        label.setText(message);
        
        //Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        
        //VBox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        //Set scene and show
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}