import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class MainScreenView
{
    private GridPane 	view;

    private TextField 	tfSize;
    private Button 		bEnter;

    private ImageView   logoView;
    private ImageView   sizeView;
    private ImageView   textView;
    private ImageView   createView;
    private double      screensize;

    public MainScreenView()
    {
        screensize = Screen.getPrimary().getVisualBounds().getHeight();

        logoView = new ImageView(new Image("Assets/mainscreen_logo.png"));
        logoView.setFitHeight(screensize/3);
        logoView.setPreserveRatio(true);
        GridPane.setColumnSpan(logoView, 3);

        sizeView = new ImageView(new Image("Assets/mainscreen_size.png"));
        sizeView.setFitHeight(screensize/13);
        sizeView.setPreserveRatio(true);
        GridPane.setColumnSpan(sizeView, 1);

        textView = new ImageView(new Image("Assets/mainscreen_textfield.png"));
        textView.setFitHeight(screensize/13);
        textView.setPreserveRatio(true);
        GridPane.setColumnSpan(textView, 1);

        tfSize  = new TextField();
        tfSize.setFont(new Font(15));
        tfSize.setStyle("-fx-background-color: transparent;");
        tfSize.setPrefWidth(screensize/12);
        GridPane.setColumnSpan(tfSize, 1);

        bEnter  = new Button();
        ImageView btnGraphic = new ImageView(new Image("Assets/mainscreen_enter.png"));
        btnGraphic.setFitHeight(screensize/13);
        btnGraphic.setPreserveRatio(true);
        bEnter.setGraphic(btnGraphic);
        bEnter.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        GridPane.setColumnSpan(bEnter, 1);


        view = new GridPane();

        //SETTING UP THE PROPERTIES OF THE GRIDPANE
        view.setMinSize(1000, 1000);
        view.setVgap(5);
        view.setHgap(5);
        view.setAlignment(Pos.CENTER);

        //ADDING ALL ELEMENTS TO THE GRID
        view.add(logoView, 0,0);
        view.add(sizeView, 0, 2);
        view.add(textView, 1, 2);
        view.add(tfSize, 1, 2);
        view.add(bEnter, 2, 2);
    }

    /**
     * RETURNS THE GRIDPANE,
     * USED FOR CREATING THE SCENE
     * IN THE CONTROLLER
     */
    public Parent asParent()
    {
        view.setStyle("-fx-background-color: #000000");
        return this.view;
    }

    public Button getEnterButton()
    {
        return bEnter;
    }

    public TextField getTextField()
    {
        return tfSize;
    }
}
