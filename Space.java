import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

public class Space extends StackPane
{
	private int row, col;			//x and y coordinates of the space
	private int value;				//-1 	- wall
									//0		- unexplored
									//1		- explored
	private double mazesize;			//size of the maze

	private Rectangle 	box;
	private Rectangle	bit;
	private boolean 	isDisabled = false;
	
	public Space(int row, int col, double mazesize, double spacesize)
	{
		setOnMouseClicked(e -> handleOnClick());
		//setOnMouseMoved(e -> handleOnClick(/**e*/));
		
		this.row = row;
		this.col = col;
		this.value = 0;
		this.mazesize = mazesize;

		box = new Rectangle(spacesize, spacesize);
		box.setFill(Color.BLACK);
		box.setStroke(Color.BLUE);

		bit = new Rectangle(spacesize/8, spacesize/8);
		bit.setFill(Color.YELLOW);
		bit.setVisible(false);
		
		if(!(row == 0 && col == 0) && !(row == mazesize - 1 && col == mazesize - 1))
		{
			bit.setVisible(true);
		}

		getChildren().addAll(box, bit);

		setTranslateX(col * spacesize);
		setTranslateY(row * spacesize);
	}
	
	public void handleOnClick()
	{
		// IF PANE IS NOT DISABLED
		// AND PANE IS NEITHER THE INITIAL
		// NOR THE GOAL PANE
		if	(!isDisabled &&
			!(row == 0 && col == 0) &&
			!(row == mazesize -1 && col == mazesize - 1))
		{
			getChildren().clear();
					
			if(value == -1)
			{
				value = 0;
				box.setFill(Color.BLACK);
				getChildren().addAll(box, bit);
			}
			else
			{
				value = -1;
				box.setFill(Color.BLUE);
				getChildren().addAll(box);
			}
		}
	}

	public void setDisablePane(boolean bool)
	{
		isDisabled = bool;
	}
	
	public void setExplored()
	{
		bit.setVisible(false);
	}

	public void setPassedBy()
	{
		box.setStroke(Color.BLACK);
		bit.setFill(Color.PEACHPUFF);
		bit.setVisible(true);
	}

	public int getValue()
	{
		return value;
	}
}