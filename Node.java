public class Node
{
    private int 			row;
    private int 			col;
    private Node			parent;
    private double			heuristic;
    private double          depth;

    public Node (int row, int col, Node parent, int n)
    {
        this.row = row;
        this.col = col;
        this.parent = parent;

        if(this.parent == null)
            depth = 0;
        else
            depth = parent.getDepth() + 1;
        computeH(n);
    }

    /**
     * GETS THE NODE'S ROW
     */
    public int getRow()
    {
        return row;
    }

    /**
     * GETS THE NODE'S COLUMN
     */
    public int getCol()
    {
        return col;
    }

    /**
     * GETS THE NODE'S HEURISTIC
     */
    public double getH()
    {
        return heuristic;
    }

    /**
     * GETS THE NODE'S PARENT
     */
    public Node getParent()
    {
        return parent;
    }

    /**
     * GETS THE NODE'S DEPTH
     */
    public double getDepth()
    {
        return depth;
    }

    /**
     * COMPUTES FOR THE NODE'S HEURISTIC
     */
    public void computeH (int n)
    {
        heuristic = Math.abs(n - row) + Math.abs(n - col) + depth;
        //heuristic = Math.sqrt((n - row) * (n - row) + (n - col) * (n - col)) + depth; // euclidean distance
    }

	public String toString()
    {
        return row + ", " + col;
    }
}