package frc.robot;

/**
 * Odometry is a class that is used to determine the
 * current pose of the robot.
 */
public class Odometry {
    Node currentPos;
    int dir;

    public Odometry(){
        //Node currentPos = Var.grid.getNode(0,0);
        //Node currentPos = new Node(0,0);
        int dir = 0;  //where 0 is north, 1 is east, 2 is south, and 3 is west
    }

    /**
     * turnToDir() is a method that makes the robot turn in place
     * until it is heading in the inputted direction.
     * 
     * @param newDir              the desired heading of the robot.
     */
    public void turnToDir(int newDir){
        while (dir%4 != newDir){
//            if (Math.abs(dir%4 - newDir) >= 2) {
                Var.drivetrain.turnDegRight(45, 0.35);
                Var.linefollower.lookingForLine(1);
                dir++;
            //}
//            else if (Math.abs(dir%4 - newDir) <= 2){
//                Var.drivetrain.turnDegLeft(45,0.35);
//                Var.linefollower.lookingForLine(0);
//                dir--;
//            }
        }
    }

    /**
     * Sets the current position fo the robot to the point on the
     * grid that the robot is about to navigate to.  I'm honestly
     * not sure why I didn't just make it set the robot's current position
     * to the point that it is on and just call this method later in the
     * newPositionHandler() in the Navigation class.
     */
    public void updatePos() {
        if (dir%4 == 0) {
            currentPos.col++;
        } else if (dir%4 == 1){
            currentPos.row++;
        } else if (dir%4 == 2){
            currentPos.col--;
        } else if (dir%4 == 3){
            currentPos.row--;
        }
    }
}