package frc.robot;

import java.util.ArrayList;

/** The Manhattan Class uses the Manhattan algorithm to
 * create a path between a given start and end position.
 */
public class Manhattan implements Algorithm{

    static ArrayList<Node> path = new ArrayList<Node>();

/**
 * findPath(grid, startPos, destPos) is a method that takes in a
 * grid, a start position, and a final position, and returns an array
 * of nodes which represents a path between the start and final positon. 
 *
 * @param grid          the grid the method will path through
 * @param startPos      the start position of the robot
 *                      which is a Node object.
 * @param destPos       the desired destination that the method
 *                      creates a path to.
 * @return              the path the algorithm created for the robot to
 *                      navigate along.
 */
    public static ArrayList findPath(Grid grid, Node startPos, Node destPos){
        path.add(destPos);
        System.out.println(path);
        return path;
    }

}
