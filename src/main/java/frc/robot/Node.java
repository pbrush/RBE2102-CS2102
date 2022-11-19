package frc.robot;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Node is a class that creates the Node objects used
 * to represent points on the grid.
 */
public class Node implements Comparable<Node> {
    int row;
    int col;
    int dist;
    int prevDist;
    Node prevNode;
    boolean isBlocked;
    boolean destination;
    boolean inPath;
    ArrayList<Node> neighbors = new ArrayList<Node>();

    /**
     * Constructor
     * @param r         The X coordinate of the Node.
     * @param c         The Y coordinate of the Node.
     */
    public Node(int r, int c){
        this.row = r;
        this.col = c;
        destination = false;
        isBlocked = false;
        inPath =  false;
        prevDist = 0;
        dist = 1;
    }

    /**
     * getRow returns the row, or the X coordinate of the Node
     * on the grid.
     *
     * @return          X coordinate of the Node on the grid.
     */
    public int getRow(){
        return row;
    }

    /**
     * getRow returns the row, or the Y coordinate of the Node
     * on the grid.
     *
     * @return          Y coordinate of the Node on the grid.
     */
    public int getCol(){
        return col;
    }


    /**
     * compareTo(node) is a method that compares two nodes
     * for the Priority Queue in the Dijkstra's class.
     *
     * @param node              The input Node that the Priority Queue
     *                          compares to.
     * @return                  Returns a value that determines where in the
     *                          Priority Queue the Node is placed.  1 means it
     *                          gets placed before the Node it is compared to, while
     *                          -1 means it gets placed after the Node it is compared to.
     */
    public int compareTo(Node node){
        if (node.dist > this.dist){
            return 1;
        }
        else if (node.dist < this.dist){
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * setNeighbors is a method that creates the neighbirs for each 
     * Node on the grid.
     * 
     * @param aNode             The Node that the method is creating the
     *                          neighbors for.
     */
    public static void setNeighbors(Node aNode){
        if (aNode.row+1 < Grid.xUpBound){       //store x and y, not the nodes themselves
            aNode.neighbors.add(Var.grid.getNode(aNode.row+1, aNode.col));  //getNode(aNode.row+1, aNode.col)
        }
        if (aNode.col+1 < Grid.yUpBound){
            aNode.neighbors.add(Var.grid.getNode(aNode.row, aNode.col+1));
        }
        if (aNode.row-1 >= Grid.xLowBound){
            aNode.neighbors.add(Var.grid.getNode(aNode.row-1, aNode.col));
        }
        if (aNode.col-1 >= Grid.yLowBound){
            aNode.neighbors.add(Var.grid.getNode(aNode.row, aNode.col-1));
        }
    }

    /**
     * getNeighbors() is a method that takes in a Node
     * and returns an ArrayList of the neighbors of the
     * given Node.
     * 
     * @param aNode                 The Node that the method
     *                              creates the ArrayList of
     *                              neighbors for.
     * @return                      An ArrayList of neighboring Nodes.
     */
    public static ArrayList<Node> getNeighbors(Node aNode){
        return aNode.neighbors;
    }

    /**
     * setBlocked is a method that takes a Node and sets the variable
     * isNodeBlocked equal to the inputted parameter.
     * 
     * @param blocked               The state that the user wants to 
     *                              set the Node to.
     */
    public void setBlocked(boolean blocked){
        this.isBlocked = blocked;
    }

    /**getBlocked is a method that takes a Node and returns true if the
     * Node is blocked and false if it is not.
     */
    public boolean getBlocked(){
        return isBlocked;
    }

    /**
     * Converts the Node to a printable format
     * @return      The node's x and y coordinate as a string
     */
    public String toString(){
        return "(" + this.row + ", " + this.col + ")";
    }

}
