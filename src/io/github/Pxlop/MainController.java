package io.github.Pxlop;

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
    
    public Timeline t;
    DateFormat timeFormat = new SimpleDateFormat( "mm:ss" );
    
    public void setupUI(int ms) {
        updateTimerText(ms);
        getStartButton().setOnAction(e -> startTimer(ms));
        getStopButton().setOnAction(e -> t.stop());
    }
    
    public void updateTimerText(long ms) {
        getTimerText().setText(timeFormat.format(ms));
    }
    
    public void startTimer(int ms) {
        long endTime = System.currentTimeMillis() + ms;
        t = new Timeline(new KeyFrame(Duration.millis(10), e -> {
                    final long diff = endTime - System.currentTimeMillis();
                    if(diff < 0) {
                        updateTimerText(1000);
                        if(ms == Main.getMins() * 60000) {
                            AlertBox.display("Alert", Main.getMins() + " minutes are up, rest your eyes!");
                            startTimer(Main.getSecs() * 1000);
                        }
                        else if(ms == Main.getSecs() * 1000) startTimer(Main.getMins() * 60000);
                    }
                    else updateTimerText(diff + 1000);
                }
        ));
        t.setCycleCount(ms / 10);
        t.play();
    }
    
    public Button getStartButton() {
        return startButton;
    }
    
    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }
    
    public Button getStopButton() {
        return stopButton;
    }
    
    public void setStopButton(Button stopButton) {
        this.stopButton = stopButton;
    }
    
    public Label getTimerText() {
        return timerText;
    }
    
    public void setTimerText(Label timerText) {
        this.timerText = timerText;
    }
}
