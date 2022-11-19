package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Dijkstras is a class that uses Dijkstra's algorithm to find the
 * shortest path between two points.
 */
public class Dijkstras implements Algorithm {

    /**
     * findPath(grid, startPos, destPos) is a method that takes in a
     * grid, a start position, and a final position, and returns an array
     * of nodes which represents a path between the start and final positon.
     *
     * @param grid      the grid the method will path through
     * @param startPos  the start position of the robot
     *                  which is a Node object.
     * @param destPos   the desired destination that the method
     *                  creates a path to, which is also a Node object.
     * @return          the path the algorithm created for the robot to
     *                  navigate along.
     */
    public static ArrayList<Node> findPath(Grid grid, Node startPos, Node destPos) {
        System.out.println("In findPath");
        PriorityQueue<Node> prioQ = new PriorityQueue<Node>();
        for (int r = 0; r < grid.xUpBound; r++) {
            for (int c = 0; c < grid.yUpBound; c++) {
                grid.grid[r][c].dist = 1000000000;
                grid.getNode(r, c).prevNode = null;

            }
        }
        grid.getNode(startPos.getRow(), startPos.getCol()).dist = 0;
        prioQ.add(grid.getNode(startPos.getRow(), startPos.getCol()));
        int distBtwNodes = 1;

        ArrayList<Node> path = new ArrayList<Node>();
        //ArrayList<Node> beenTo = new ArrayList<Node>();
        System.out.println("The startPos is " + startPos);

        while (prioQ.size() > 0) {
            //System.out.println("The priority queue contains: " + prioQ);
            Node u = prioQ.poll();
            //beenTo.add(u);

            for (Node n : u.neighbors) {
                //System.out.println("The PrioQ is: " + prioQ);
                if (!n.getBlocked()) {
                    //System.out.println("U.dist is: " + u.dist);
                    int alt = u.dist + distBtwNodes;
                    if (alt < n.dist) {
                        n.dist = alt;
                        n.prevNode = u;
                        prioQ.add(n);
                    }
                }
            }
        }
        Node n = grid.getNode(destPos.getRow(), destPos.getCol());
        //System.out.println(n.dist);
        while (n.prevNode != null) {
            //System.out.println("I am in the loop");
            path.add(0, n);
            //System.out.println(n + " has been added to the path");
            n = n.prevNode;
        }
        //System.out.println("The new path is: " + path);
        //System.out.println(path.size());
        return path;
    }
}
