package io.github.pxlop.twenty;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainController {
    
    @FXML private Label timerText;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button settingsButton;
    
    private Timeline t;
    DateFormat timeFormat = new SimpleDateFormat( "mm:ss" );
    
    public void setupUI(long ms) {
        //Upate the timer text
        updateTimerText(ms);
        
        //Set button actions
        startButton.setOnAction(e -> startTimer(ms));
        stopButton.setOnAction(e -> t.stop());
        settingsButton.setOnAction(e -> Settings.display());
    }
    
    public void updateTimerText(long ms) {
        timerText.setText(timeFormat.format(ms));
    }
    
    public void startTimer(long ms) {
        long endTime = System.currentTimeMillis() + ms;
        t = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            final long diff = endTime - System.currentTimeMillis();
            
            if(diff < 0) {
                //If timer has hit zero ms left
                updateTimerText(1000);
                t.stop();
                
                if(ms == Main.mins * 60000) {
                    //If this is minutes timer
                    //Play sound
                    if(Main.playAfterMins) Main.playSound(Main.class.getResourceAsStream("files" + "/alarmAudio.wav"));
                    
                    //Display alert
                    AlertBox.display("Alert", Main.mins + " minutes are up, rest your eyes!", () -> startTimer(Main.secs * 1000));
                }
                else if(ms == Main.secs * 1000) {
                    //If this is seconds timer
                    //Play sound
                    if(Main.playAfterSecs) Main.playSound(Main.class.getResourceAsStream("files" + "/alarmAudio.wav"));
                    
                    //Start minutes timer
                    startTimer(Main.mins * 60000);
                }
            }
            else updateTimerText(diff + 1000);
        }));
        t.setCycleCount((int) (ms / 10));
        t.play();
    }
}
