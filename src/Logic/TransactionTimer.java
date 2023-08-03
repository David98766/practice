/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TransactionTimer {
    private static TransactionTimer instance; // Singleton instance
    private long startTime; // Variable to store the time when the timer starts
    private Timeline timeline; // Timeline for updating the timer display
    private Label lblRunningTimer; // Label to display the timer value

    private TransactionTimer(Label lblRunningTimer) {
        this.lblRunningTimer = lblRunningTimer; // Assign the label for displaying the timer value
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new TimerEventHandler())); // Create a timeline with a 1-second key frame
        timeline.setCycleCount(Animation.INDEFINITE); // Set the cycle count to indefinite for continuous updates
    }

    public static TransactionTimer getInstance(Label lblRunningTimer) {
        if (instance == null) {
            instance = new TransactionTimer(lblRunningTimer);
        }
        return instance;
    }

    public void start() {
        startTime = System.currentTimeMillis(); // Store the current system time as the start time
        timeline.play(); // Start the timeline
    }

    public void stop() {
        timeline.stop(); // Stop the timeline
    }

    private class TimerEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            long elapsedTime = System.currentTimeMillis() - startTime; // Calculate the elapsed time since the timer started
            long seconds = elapsedTime / 1000; // Calculate the seconds

            if (seconds >= 1 * 60) { // Check if elapsed time reaches or exceeds 30 minutes (30 * 60 seconds)
                lblRunningTimer.setTextFill(Color.RED); // Change the text color to red if it is over 30 minutes
            }

            long hours = seconds / 3600; // Calculate the hours
            long minutes = (seconds % 3600) / 60; // Calculate the minutes
            seconds = seconds % 60; // Calculate the remaining seconds

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds); // Format the time as HH:MM:SS
            lblRunningTimer.setText(formattedTime); // Set the formatted time as the text of the label
        }
    }
}
