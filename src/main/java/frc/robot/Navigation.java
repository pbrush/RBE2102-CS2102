package frc.robot;

import java.awt.*;
import java.util.ArrayList;
//import java.lang.Math.abs();

/**
 * Navigation is a class that combines the functionality of
 * the odometry class which determines the pose of the robot,
 * the classes that implement the Algorithm interface which
 * plan a path for the robot to navigate along, and
 * the RomiDrivetrain class which allows the robot to move.
 */
public class Navigation{

     /**
     * moveToPoint is a method that takes in the values of the robot's
     * old position and desired position and the axis the robot is intended
     * to move along.
     * 
     * @param axis              The axis the robot will drive along.
     * @param oldPos            The old or current position of the robot.
     * @param newPos            The desired position of the robot.
     */
  public static void moveToPoint(String axis, int oldPos, int newPos){
      int dPos = newPos - oldPos;
        if (axis.equals("x")){
            if (dPos > 0){
                Var.odometry.turnToDir(1);
                newPositionHandler(dPos);
            }
            else if (0 > dPos){
                dPos = Math.abs(dPos);
                Var.odometry.turnToDir(3);
                newPositionHandler(dPos);
            }
            else {
                Var.drivetrain.arcadeDrive(0,0);
            }
        }
        else if (axis.equals("y")){
            if (dPos > 0){
                Var.odometry.turnToDir(0);
                newPositionHandler(dPos);
            }
            else if (0 > dPos){
                dPos = Math.abs(dPos);
                Var.odometry.turnToDir(2);
                newPositionHandler(dPos);
            }
            else {
                Var.drivetrain.arcadeDrive(0,0);
            }
          }
    }

    /**
     * newNavTo(newPos) is a method I never got to test but is supposed to
     * have the functionality of navigateTo(newPos) and moveToPoint(axis, oldPos, newPos)
     * in one method.
     *
     * @param newPos        The desired destination position, which
     *                      is given as a Node in the grid.
     */
    public static void newNavTo(Node newPos){
      int dxPos = newPos.row - origin.row;
      int dyPos = newPos.col - origin.col;

        if (dxPos > 0){
            Var.odometry.turnToDir(1);
            if (!Var.sharpIR.blockDetected()){
                newPositionHandler(dxPos);
                origin = newPos;
            }
        }
        else if (0 > dxPos){
            dxPos = Math.abs(dxPos);
            Var.odometry.turnToDir(3);
            if (!Var.sharpIR.blockDetected()){
                newPositionHandler(dxPos);
                origin = newPos;
            }
        }
        if (dyPos > 0){
            Var.odometry.turnToDir(0);
            if (!Var.sharpIR.blockDetected()){
                newPositionHandler(dyPos);
                origin = newPos;
            }

        }
        else if (0 > dyPos){
            dyPos = Math.abs(dyPos);
            Var.odometry.turnToDir(2);
            if (!Var.sharpIR.blockDetected()){
                newPositionHandler(dyPos);
                origin = newPos;
            }
        }
    }

    /**
     * newPositionHandler is a method that takes in the difference
     * between the robot's current pose and the desired pose, and
     * drives the robot until it reaches the desired pose,
     * or the difference in the pose = 0.
     * 
     * @param dPos            The difference in the current and
     *                        desire position of the robot.
     */
    public static void newPositionHandler(int dPos){
        while(dPos != 0){
            Var.linefollower.lineFollower(dPos);
            if (Var.lightsensor.atIntersection()){
                Var.linefollower.handleIntersection();
                dPos--;
                Var.odometry.updatePos();
                Var.linefollower.lineFollower(dPos);
                System.out.println("The current position is " + Var.odometry.currentPos);
            }
        }
    }


    /**
     * Origin was a Node I put in place to act as the robot's starting position;
     * initially I tried using Var.odometry.currentPos = Var.grid.getNode(0,0);
     * however that threw null pointer exceptions and switching to this fixed that.
     */
    public static Node origin = new Node(0,0);

    /**
     * navigateTo is a method that takes in a point, represented
     * by a Node, and calls the moveTo method to drive the robot
     * to the x and y coordinates of the inputted Node.
     * 
     * @param newPos            The desired Pose of the Robot.
     */ 
    public static void navigateTo(Node newPos){
        System.out.println("Navigation.129");
        System.out.println("Navigating to a new position!");
        //Node origin = new Node(0,0);newPos.row - origin.row;
        if (Math.abs(newPos.row - origin.row) < Math.abs(newPos.col - origin.col)) {
            moveToPoint("x", origin.getRow(), newPos.row);//Var.odometry.currentPos.getRow()
            moveToPoint("y", origin.getCol(), newPos.col);
        }
        else {
            moveToPoint("y", origin.getCol(), newPos.col);
            moveToPoint("x", origin.getRow(), newPos.row);//Var.odometry.currentPos.getRow()
        }
        origin = newPos;

    }

    /**
     * newNavPath(algo, startPos, destPos) is a method that implements the same logic as the moveTo(axis, oldPos, newPos)
     * method to navigate to each Node in the path returned from the findPath(grid, startPos, destPos).  This method used to
     * use the navigateTo(newPos) and newNavTo(newPos) methods, however the robot needed to check if the intersection
     * ahead of it is blocked AFTER it turned to face the direction of the next node in the path, however by the time it checked
     * if the next node in the path was blocked, it was in the middle of the function to navigate to that node, and I couldn't
     * figure out a way to use either navigateTo(newPos) or newNavTo(newPos) and have the functionality of detecting blocked intersections.
     *
     * 
     * @param algo              The inputted algorithm that the method will use to
     *                          create a path.
     * @param startPos          The starting position of the robot along the path,
     *                          given as a Node on the grid.
     * @param destPos           The Node on the grid that the algorithm will path to.
     */
    public static void newNavPath(String algo, Node startPos, Node destPos){
        ArrayList<Node> path = new ArrayList<Node>();
        switch (algo) {
            case "manhattan":
                path = Manhattan.findPath(Var.grid, origin, destPos);
                for (Node node : path) {
                    newNavTo(node);
                    path.remove(node);
                }
                break;
            case "dijkstras":
                path = Dijkstras.findPath(Var.grid, startPos, destPos);
                System.out.println(path);
                    for (Node n : path) {
                        int dxPos = n.row - origin.row;
                        int dyPos = n.col - origin.col;
                        System.out.println("The dxPos is " + dxPos);
                        System.out.println(("The dyPos is " + dyPos));
                        if (dxPos > 0) {
                            System.out.println("In 1st if");
                            Var.odometry.turnToDir(1);
                            if (Var.sharpIR.blockDetected()) {
                                newNavPath("dijkstras", Var.odometry.currentPos, destPos);
                                break;
                            }
                            newPositionHandler(dxPos);
                            origin = n;
                        } else if (0 > dxPos) {
                            dxPos = Math.abs(dxPos);
                            System.out.println("In 2nd if");
                            Var.odometry.turnToDir(3);
                            if (Var.sharpIR.blockDetected()) {
                                newNavPath("dijkstras", Var.odometry.currentPos, destPos);
                                break;
                            }
                            newPositionHandler(dxPos);
                            origin = n;
                        } else if (dyPos > 0) {
                            System.out.println("In 3rd if");
                            Var.odometry.turnToDir(0);
                            if (Var.sharpIR.blockDetected()) {
                                newNavPath("dijkstras", Var.odometry.currentPos, destPos);
                                break;
                            }
                            newPositionHandler(dyPos);
                            origin = n;
                        } else if (0 > dyPos) {
                            dyPos = Math.abs(dyPos);
                            System.out.println("In 4th if");
                            Var.odometry.turnToDir(2);
                            if (Var.sharpIR.blockDetected()) {
                                newNavPath("dijkstras", Var.odometry.currentPos, destPos);
                                break;
                            }
                            newPositionHandler(dyPos);
                            origin = n;
                        }
                    }
                    System.out.println("I am out of the path");
                break;
            case "A*":
                System.out.println("Sorry, haven't gotten to that yet.");
                break;
            default:
                System.out.print("Please enter a valid algorithm type.");
                break;
        }
    }











}