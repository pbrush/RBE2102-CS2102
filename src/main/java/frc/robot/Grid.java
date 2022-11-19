package frc.robot;

/**
 * Grid is a class that is used to make the grid of
 * point that the robot navigates around.
 */
public class Grid {

    /**
     * Declarations of the variables needed to create the grid.
     */
    static int xUpBound = 6;
    static int yUpBound = 6;
    static int xLowBound = 0;
    static int yLowBound = 0;
    Node[][] grid = new Node[xUpBound][yUpBound];

    /**
     * Constructor for the Grid object; runs the init() method.
     */
    public Grid(){
        init();
    }

    /**
     * Creates the grid as a 2D array based on the
     * variables xUpBound and yUpBound.
     */
    public void init(){
        for (int r = 0; r < xUpBound; r++){
            for (int c = 0; c < yUpBound; c++){
                this.grid[r][c] = new Node(r, c);
            }
        }
    }

    /**
     * Creates the neighbors of each Node in the grid.
     */
    public void gridNeighbors(){
        for (int r = 0; r < xUpBound; r++){
            for (int c = 0; c < yUpBound; c++){
                Node.setNeighbors(this.getNode(r,c));
            }
        }
    }

    /**
     * Returns the Node on the grid with the inputted
     * X and Y coordinates.
     * @param r       The row of the desired Node
     * @param c       The column of the desired Node
     * @return        Returns the Node on the grid
     *                with the associated r and c values.
     */
    public Node getNode(int r, int c){
        return grid[r][c];
    }

    /**
     * checkBounds is a method that takes in a Node and
     * checks if it is in the grid/within the bounds of 
     * the grid.
     * @param aNode         The given Node you want to check
     * @param grid          The grid you want to compare the Node to
     * @return              If the Node is within the bounds of the grid
     *                      it will return true, and false if it is not.
     */
    public static boolean checkBounds(Node aNode, Grid grid){
        return (aNode.row >= xLowBound && aNode.row < xUpBound) && (aNode.col >= yLowBound && aNode.col < yUpBound);
    }

}
