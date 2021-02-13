import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

import java.io.File;

public class MazeScreenView
{
    private Pane    root = new Pane();
    private Button  bExplore;
    private Button  bSimulate;
    private Button  bCount;
    private Text    count;

    private int     mazesize;
    private double  screensize;
    private double  spacesize;

    private int     movecount = 0;

    private Space[][] grid;
    private ImageView pinky;
    private ImageView pacman;
    private MediaPlayer mp;

    public MazeScreenView()
    {
        screensize = Screen.getPrimary().getVisualBounds().getHeight() - Screen.getPrimary().getVisualBounds().getHeight() * 0.1;

        bExplore = new Button();
        ImageView exploreGraphic = new ImageView(new Image("Assets/mazescreen_explore.png"));
        exploreGraphic.setFitHeight(screensize/13);
        exploreGraphic.setPreserveRatio(true);
        bExplore.setGraphic(exploreGraphic);
        bExplore.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        bExplore.setTranslateX(screensize + screensize * 0.2);
        bExplore.setTranslateY(screensize/2);

        bSimulate = new Button();
        ImageView simulateGraphic = new ImageView(new Image("Assets/mazescreen_simulate.png"));
        simulateGraphic.setFitHeight(screensize/13);
        simulateGraphic.setPreserveRatio(true);
        bSimulate.setGraphic(simulateGraphic);
        bSimulate.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        bSimulate.setTranslateX(screensize + screensize * 0.2);
        bSimulate.setTranslateY(screensize / 2 + screensize * 0.1);
        bSimulate.setDisable(true);

        bCount = new Button();
        bCount.setFont(new Font(15));
        bCount.setTextFill(Color.PEACHPUFF);
        ImageView countGraphic = new ImageView(new Image("Assets/mazescreen_empty.png"));
        countGraphic.setFitHeight(screensize/13);
        countGraphic.setPreserveRatio(true);
        bCount.setGraphic(countGraphic);
        bCount.setStyle("-fx-background-color: transparent;");
        bCount.setTranslateX(screensize + screensize * 0.2);
        bCount.setTranslateY(screensize / 2 - screensize * 0.1);

        count = new Text();
        count.setText("Step Count: 0");
        count.setFont(new Font(15));
        count.setFill(Color.BLACK);
        count.setTranslateX(screensize + screensize * 0.2 + screensize/24);
        count.setTranslateY(screensize / 2 - screensize * 0.1 + screensize/19);

        root.getChildren().addAll(bExplore, bSimulate, bCount, count);
    }

    public Parent asParent()
    {
        this.root.setStyle("-fx-background-color: #000000");
        return this.root;
    }

    public void createMaze(int size)
    {
        this.mazesize = size;
        this.spacesize = screensize/mazesize;

        grid = new Space[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Space currSpace = new Space(i, j, mazesize, spacesize);

                grid[i][j] = currSpace;
                root.getChildren().add(currSpace);
            }
        }

        pinky = new ImageView(new Image("Assets/ghost.png"));
        pinky.setFitHeight(spacesize/1.4);
        pinky.setFitWidth(spacesize/1.4);
        pinky.setTranslateX(spacesize * 0.15);
        pinky.setTranslateY(spacesize * 0.15);
        pinky.setPreserveRatio(true);

        pacman = new ImageView(new Image("Assets/pacman.png"));
        pacman.setFitHeight(spacesize/1.4);
        pacman.setFitWidth(spacesize/1.4);
        pacman.setTranslateX(spacesize * (mazesize - 1) + spacesize * 0.15);
        pacman.setTranslateY(spacesize * (mazesize - 1) + spacesize * 0.15);
        pacman.setPreserveRatio(true);

        root.getChildren().addAll(pinky, pacman);
    }

    public void disablePanes()
    {
        for(int i = 0; i < mazesize; i++)
            for(int j = 0; j < mazesize; j++)
                grid[i][j].setDisablePane(true);
    }

    public void simulate(int[][] solution)
    {
        Timeline move = new Timeline(new KeyFrame(Duration.millis(200), event1 -> {
            move(solution);
        }));
        move.setCycleCount(solution.length);
        move.play();
    }

    private void move(int[][] solution)
    {
        count.setText("Step Count: " + movecount);

        if(movecount != 0)
            grid[solution[movecount - 1][0]][solution[movecount - 1][1]].setPassedBy();

        if(movecount == solution.length - 1)
            pacman.setVisible(false);

        pinky.setTranslateX(solution[movecount][1] * spacesize + (spacesize * 0.15));
        pinky.setTranslateY(solution[movecount][0] * spacesize + (spacesize * 0.15));
        movecount++;
    }

    public void showExplored(int[][] explored)
    {
        for(int i = 0; i < explored.length; i++)
            grid[explored[i][0]][explored[i][1]].setExplored();
    }

    public Space[][] getGrid()
    {
        return grid;
    }

    public Button getExploreButton()
    {
        return bExplore;
    }

    public Button getSimulateButton()
    {
        return bSimulate;
    }
}
