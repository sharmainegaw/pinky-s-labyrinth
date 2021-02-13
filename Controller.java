import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class Controller
{
    private MainScreenView  mainview;
    private MazeScreenView  mazeview;
    private Stage           stage;

    private Maze            maze;

    private int[][]         solution;
    private int[][]         explored;

    public Controller(Stage primaryStage)
    {
        this.stage = primaryStage;

        /** INITIALIZING SCENE 1: mainview */
        mainview = new MainScreenView();
        mainview.getEnterButton().setOnMouseClicked(e -> handleEnterButton());

        /** INITIALIZING SCENE 2: mazeview */
        mazeview = new MazeScreenView();
        mazeview.getExploreButton().setOnMouseClicked(e -> handleExploreButton());
        mazeview.getSimulateButton().setOnMouseClicked(e -> handleSimulateButton());
    }

    /**METHODS FOR SCENE 1: mainview*/

    public MainScreenView getMainscreenview()
    {
        return mainview;
    }

    /**
     * IF TEXT FIELD IS NOT EMPTY
     * INPUT IS STORED IN
     * AND MAZE IS CREATED
     */
    public void handleEnterButton()
    {
        if(!mainview.getTextField().getText().isEmpty())
        {
            int size = 0;

            try
            {
                size = Integer.parseInt(mainview.getTextField().getText());
                if (size < 8 || size > 64)
                {
                    Alert dialog = new Alert(Alert.AlertType.ERROR, "Only numbers between 8 and 64 (inclusive) are valid.", ButtonType.OK);
                    dialog.show();
                }
            } catch (NumberFormatException e) {
                Alert dialog = new Alert(Alert.AlertType.ERROR, "Not a valid input. Enter numbers only.", ButtonType.OK);
                dialog.show();
            }

            if(size >= 8 && size <= 64)
            {
                mazeview.createMaze(size);
                maze = new Maze(size);

                double screensize = Screen.getPrimary().getVisualBounds().getHeight();

                Scene scene = new Scene(mazeview.asParent(), screensize + screensize * 0.5, screensize - screensize * 0.1);
                stage.setScene(scene);
            }
        }
    }

    /**METHODS FOR SCENE 2: mazeview*/

    public MazeScreenView getMazescreenview()
    {
        return mazeview;
    }

    /**
     * BOT STARTS FINDING THE SOLUTION
     * AND RETURNS IT
     */
    public void handleExploreButton()
    {
        mazeview.disablePanes();

        maze.addWallValues(mazeview.getGrid());
        maze.start();

        solution = maze.getSolution();
        explored = maze.getExplored();

        mazeview.showExplored(explored);

        mazeview.getSimulateButton().setDisable(false);
        mazeview.getExploreButton().setDisable(true);
    }

    /**
     * BOT STARTS MOVING TO SHOW THE SIMULATION
     */

    public void handleSimulateButton()
    {
        if(solution.length != 0)
            mazeview.simulate(solution);
        else
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR, "No solution found.", ButtonType.OK);
            dialog.show();
        }
        mazeview.getSimulateButton().setDisable(true);
    }

}
