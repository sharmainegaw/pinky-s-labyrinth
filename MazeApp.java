import javafx.application.*;
import javafx.scene.*;
import javafx.scene.media.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

import java.io.File;

public class MazeApp extends Application
{
    private MediaPlayer mp;
    private double      screensize;

    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        try {
            screensize = Screen.getPrimary().getVisualBounds().getHeight();
            Media sound = new Media(new File("Assets/pacman_theme.mp3").toURI().toString());
            mp = new MediaPlayer(sound);
            mp.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mp.seek(Duration.ZERO);
                }
            });
            mp.play();

            Controller controller = new Controller(primaryStage);
            MainScreenView nextView = controller.getMainscreenview();

            Scene scene = new Scene(nextView.asParent(), screensize / 1.2, screensize / 1.5);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pinky's Labyrinth");
            primaryStage.show();
        }
        catch(Exception e)
        {
            //System.out.println("ERROR");
        }
    }
}
