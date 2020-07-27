package io.github.Pxlop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends Application {
    
    private static int mins;
    private static int secs;
    public String homeDir;
    public String separator;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        homeDir = System.getProperty("user.home");
        separator = File.separator;
        String folderDir = homeDir + separator + "20-20-20";
        File optionsFileDir = new File(folderDir);
        File optionsFile = new File(folderDir + separator + "options.txt");
        optionsFileDir.mkdirs();
        if(!optionsFile.isFile()) {
            PrintWriter createFile = new PrintWriter(optionsFile);
            createFile.println(20);
            createFile.println(20);
            createFile.close();
        }
        
        Scanner in = new Scanner(optionsFile);
        setMins(in.nextInt());
        setSecs(in.nextInt());
        in.close();
    
        System.out.println("minutes: " + getMins());
        System.out.println("seconds: " + getSecs());
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("index.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("20-20-20");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        
        MainController controller = loader.getController();
        controller.setupUI(getMins() * 60000);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static int getMins() {
        return mins;
    }
    
    public void setMins(int mins) {
        Main.mins = mins;
    }
    
    public static int getSecs() {
        return secs;
    }
    
    public void setSecs(int secs) {
        Main.secs = secs;
    }
}
