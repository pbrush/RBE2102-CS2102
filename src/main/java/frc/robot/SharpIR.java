package frc.robot;

 
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * SharpIr is a class for declaring an
 * object and the associated default methods.
*/
public class SharpIR {

    /**
     * Instantiation of the distanceSensor as an AnalogInput.
     */
    public AnalogInput distanceSensor;

    /**
     * Constructor for the SharpIR object
     */
    public SharpIR(){
        distanceSensor = new AnalogInput(2); //could be 3
    }

        /**
         * blockDetected() is a mehtod that uses the distance sensor
         * to determine if there is an object blocking the path of
         * the robot.
         * 
         * @return              Returns true if there is a block in the way
         *                      or false if there is nothing in the way.
         */
    public boolean blockDetected(){
        if(distanceSensor.getAverageVoltage() > 1.6 && distanceSensor.getAverageVoltage() < 3.5){
            if ((Var.odometry.dir)%4 == 0){
                Var.grid.getNode(Var.odometry.currentPos.row,Var.odometry.currentPos.col+1).setBlocked(true);
                System.out.println("The intersection" + Var.grid.getNode(Var.odometry.currentPos.row,Var.odometry.currentPos.col+1) + " is " + Var.grid.getNode(Var.odometry.currentPos.row,Var.odometry.currentPos.col+1).getBlocked());
            }
            else if ((Var.odometry.dir)%4 == 1){
                Var.grid.getNode(Var.odometry.currentPos.row+1, Var.odometry.currentPos.col).setBlocked(true);
                System.out.println("The intersection" + Var.grid.getNode(Var.odometry.currentPos.row+1,Var.odometry.currentPos.col) + " is " + Var.grid.getNode(Var.odometry.currentPos.row+1,Var.odometry.currentPos.col).getBlocked());
            }
            else if ((Var.odometry.dir)%4 == 2){
                Var.grid.getNode(Var.odometry.currentPos.row, Var.odometry.currentPos.col-1).setBlocked(true);
                System.out.println("The intersection" + Var.grid.getNode(Var.odometry.currentPos.row,Var.odometry.currentPos.col-1) + " is " + Var.grid.getNode(Var.odometry.currentPos.row,Var.odometry.currentPos.col-1).getBlocked());
            }
            else if ((Var.odometry.dir)%4 == 3){
                Var.grid.getNode(Var.odometry.currentPos.row-1, Var.odometry.currentPos.col).setBlocked(true);
                System.out.println("The intersection" + Var.grid.getNode(Var.odometry.currentPos.row-1,Var.odometry.currentPos.col) + " is " + Var.grid.getNode(Var.odometry.currentPos.row-1,Var.odometry.currentPos.col).getBlocked());
            }
            return true;
        }
        else{
            return false;
        }
    }

}
