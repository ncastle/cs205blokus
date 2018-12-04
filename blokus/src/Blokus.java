
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javax.print.attribute.standard.Chromaticity.COLOR;

/**
 * The blokus Application, which contains the board and a message label.
 *
 */
public class Blokus extends Application {

    public static double MILLISEC = 300;
    private Game game;
    //private Board board;
    //private Board inventory;
    private Timeline animation;


    //Stage variable for current stage to display
    private Stage primaryStage;

    /**
     * Launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets up the blokus board and game, as well as a status label that can be
     * used to display scores and messages
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        //Scene scene = new Scene(layout);    //create scene object

        game = new Game(new Group(), this);

        // Can add events that occur automatically here.
        // setUpAnimation();

        this.primaryStage.setTitle("Blokus");   //set title to stage
        this.primaryStage.setScene(game);      //add scene to stage
        this.primaryStage.show();              //display contents of stage
    }

    /**
     * Sets up an animation timeline that calls update on the game every
     * MILLISEC milliseconds.
     */
//    private void setUpAnimation() {
//        // Create a handler
//        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
//            this.pause();
//            game.update();
//            this.resume();
//        };
//        // Create an animation for alternating text
//        animation = new Timeline(new KeyFrame(Duration.millis(MILLISEC), eventHandler));
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.play();
//    }

    /**
     * Pauses the animation.
     */
    private void pause() {
        animation.pause();
    }

    /**
     * Resumes the animation.
     */
    private void resume() {
        animation.play();
    }

}
