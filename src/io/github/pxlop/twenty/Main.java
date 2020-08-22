package io.github.pxlop.twenty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Main extends Application {
    
    public static int mins;
    public static int secs;
    public static boolean playAfterMins;
    public static boolean playAfterSecs;
    public String homeDir;
    public static Stage window;
    public static Scene scene;
    private static Parent root;
    private static MainController controller;
    private static File optionsFile;
    
    @Override public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.getIcons().add(new Image(Main.class.getResourceAsStream("files/icon.png")));
        
        //File location variables
        homeDir = System.getProperty("user.home");
        String folderDir = homeDir + "/20-20-20";
        File filesDir = new File(folderDir);
        optionsFile = new File(folderDir + "/options.txt");
        
        //Make the options.txt file if it doesn't already exist
        filesDir.mkdirs();
        if(!optionsFile.isFile()) {
            PrintWriter createFile = new PrintWriter(optionsFile);
            
            //Default settings
            createFile.println("minutes: " + 20);
            createFile.println("seconds: " + 20);
            createFile.println("playAfterMins: true");
            createFile.println("playAfterSecs: false");
            createFile.close();
        }
        
        //Read the options in the options.txt file
        readOptions();
        
        //Load and show
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("index.fxml"));
        root = loader.load();
        window.setTitle("20-20-20");
        scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.show();
        
        controller = loader.getController();
        controller.setupUI(mins * 60000);
    }
    
    public static void setSceneMain() {
        controller.setupUI(mins * 60000);
        scene.setRoot(root);
        window.setTitle("20-20-20");
    }
    
    private static void readOptions() throws FileNotFoundException {
        Scanner in = new Scanner(optionsFile);
        
        //Set the variables
        in.next();
        mins = in.nextInt();
        in.next();
        secs = in.nextInt();
        in.next();
        playAfterMins = in.nextBoolean();
        in.next();
        playAfterSecs = in.nextBoolean();
        in.close();
        
        //Print out the settings
        System.out.println(); //New line for more organized output
        System.out.println("minutes: " + mins);
        System.out.println("seconds: " + mins);
        System.out.println("playAfterMins: " + playAfterMins);
        System.out.println("playAfterSecs: " + playAfterSecs);
    }
    
    public static void updateOptions(int minutes, int seconds, boolean playAfterMins, boolean playAfterSecs) throws FileNotFoundException {
        //Set the variables to the new settings
        mins = minutes;
        secs = seconds;
        Main.playAfterMins = playAfterMins;
        Main.playAfterSecs = playAfterSecs;
        
        //Print the settings to the options.txt file
        PrintWriter createFile = new PrintWriter(optionsFile);
        createFile.println("minutes: " + minutes);
        createFile.println("seconds: " + seconds);
        createFile.println("playAfterMins: " + playAfterMins);
        createFile.println("playAfterSecs: " + playAfterSecs);
        createFile.close();
        
        //Print out the new settings
        System.out.println(); //New line for more organized output
        System.out.println("minutes: " + minutes);
        System.out.println("seconds: " + seconds);
        System.out.println("playAfterMins: " + playAfterMins);
        System.out.println("playAfterSecs: " + playAfterSecs);
    }
    
    public static synchronized void playSound(InputStream inputStream) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
