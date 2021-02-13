import javafx.scene.control.*;
import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

public class Bot
{
    private Node 				root;
    private ArrayList <Node> 	fringe;
    private ArrayList <Node>	explored;
    private ArrayList <Node>    solution;

    //private int COUNT = 0;

    public Bot (int[][] spaces)
    {
        root        = new Node(0, 0, null, spaces.length);
        fringe      = new ArrayList<Node>();
        explored    = new ArrayList<Node>();
        solution    = new ArrayList<Node>();

        try
        {
            search(spaces);
        }
        catch (StackOverflowError e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            dialog.show();
            fringe.clear();
        }
        catch(Exception e)
        {
        }
    }

    /**
     * FUNCTION TO LOOK FOR THE SOLUTION
     */
    public void search (int[][] spaces) throws Exception
    {
		
		fringe.add(root);
		Node currNode = root;
		
        //System.out.println(COUNT + " (" + currNode.getRow() + ", " + currNode.getCol() + ") " + currNode.getDepth());
        //COUNT++;
		
        int row;
        int col;

        
		do
        {
			//System.out.println(COUNT + " (" + currNode.getRow() + ", " + currNode.getCol() + ") " + currNode.getDepth());
			
			row = currNode.getRow();
			col = currNode.getCol();
			
            /**BASE CASE: GOAL HAS BEEN FOUND*/
            if(currNode.getRow() == spaces[0].length - 1 &&
                    currNode.getCol() == spaces[0].length - 1)
            {
                storeSolution(currNode);
                fringe.clear();
            }
            else
            {

                Node n;

                //go up
                if (row != 0)
                {
                    if (spaces[row - 1][col] == 0 &&
                        !isInExplored(row - 1, col) &&
                        !isInFringe(row - 1, col))
                    {
                        n = new Node(row - 1, col, currNode, spaces[0].length);
                        fringe.add(n);
                    }
                }

                //go down
                if (row != spaces.length - 1)
                {
                    if (spaces[row + 1][col] == 0 &&
                        !isInExplored(row + 1, col) &&
                        !isInFringe(row + 1, col))
                    {
                        n = new Node(row + 1, col, currNode, spaces[0].length);
                        fringe.add(n);
                    }
                }

                //go left
                if (col != 0)
                {
                    if (spaces[row][col - 1] == 0 &&
                        !isInExplored(row, col - 1) &&
                        !isInFringe(row, col - 1))
                    {
                        n = new Node(row, col - 1, currNode, spaces[0].length);
                        fringe.add(n);
                    }
                }

                //go right
                if (col != spaces.length - 1)
                {
                    if (spaces[row][col + 1] == 0 &&
                        !isInExplored(row, col + 1) &&
                        !isInFringe(row, col + 1))
                    {
                        n = new Node(row, col + 1, currNode, spaces[0].length);
                        fringe.add(n);
                    }
                }

                fringe.remove(currNode);
                explored.add(currNode);

            }

            // if the fringe is not empty
            if(fringe.size() != 0) {
                try {
                    currNode = fringe.get(getLeastHeuristic());
                } catch (StackOverflowError e) {
                    Alert dialog = new Alert(Alert.AlertType.ERROR, "Stack Overflow! Cannot find solution.", ButtonType.OK);
                    dialog.show();
                    fringe.clear();
                }
            }
        }while(fringe.size() != 0);
    }

    /**
     * GETS THE NODE IN THE FRINGE WITH THE LEAST HEURSITIC
     */
    private int getLeastHeuristic()
    {
        int index = -1;
        double leastHeuristic = Double.MAX_VALUE;

        for(int i = 0; i < fringe.size(); i++)
        {
            if(fringe.get(i).getH() < leastHeuristic)
            {
                index = i;
                leastHeuristic = fringe.get(i).getH();
            }
        }

        return index;
    }

    /**
     * CHECKS IF THE SPACE IS ALREADY IN THE EXPLORED LIST
     */
    public boolean isInExplored(int row, int col)
    {
        int i = 0;
        while (i < explored.size())
        {
            if (explored.get(i).getRow() == row && explored.get(i).getCol() == col)
            {
                return true;
            } else i++;
        }
        return false;
    }

    /**
     * CHECKS IF THE SPACE IS ALREADY IN THE FRINGE
     */
    public boolean isInFringe(int row, int col)
    {

        int i = 0;
        while (i < fringe.size())
        {
            if (fringe.get(i).getRow() == row && fringe.get(i).getCol() == col)
            {
                return true;
            } else i++;
        }
        return false;
    }

    /**
     * TRACES BACK FOR THE SOLUTION
     */
    public void storeSolution (Node currNode)
    {
        solution = new ArrayList<>();

        while(currNode.getParent() != null)
        {
            solution.add(currNode);
            currNode = currNode.getParent();
        }

        //ADDS THE ROOT NODE
        solution.add(currNode);

        Collections.reverse(solution);
    }

    /**
     * RETURNS THE COORDINATES
     * OF THE SOLUTION
     */
    public int[][] getSolution()
    {
        int[][] realSolution = new int[solution.size()][2];

        for(int i = 0; i < solution.size(); i++)
        {
            realSolution[i][0] = solution.get(i).getRow();
            realSolution[i][1] = solution.get(i).getCol();
        }
        return realSolution;
    }


    /**
     * RETURNS THE COORDINATES
     * OF THE EXPLORED NODES
     */
    public int[][] getExplored()
    {
        int[][] realExplored = new int[explored.size()][2];

        for(int i = 0; i < explored.size(); i++)
        {
            realExplored[i][0] = explored.get(i).getRow();
            realExplored[i][1] = explored.get(i).getCol();
        }
        return realExplored;
    }
}