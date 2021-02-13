import java.util.*;

public class Maze
{
    private Bot 	bot;
    private int[][] spaces;
    //-1 = wall
    //0  = space
    //1  = explored
    private int 	size;

    private int[][] solution;
    private int[][] explored;

    public Maze (int n)
    {
        size = n;
        spaces = new int[n][n];

        for(int i = 0; i < n; i++)
            Arrays.fill(spaces[i], 0);
    }

    public void addWallValues (Space[][] grid)
    {
        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                spaces[i][j] = grid[i][j].getValue();
            }
        }
    }

    public void start()
    {
        bot = new Bot(spaces);
        solution = bot.getSolution();

    }

    public int[][] getSolution()
    {
        return solution;
    }

    public int[][] getExplored()
    {
        return bot.getExplored();
    }
}