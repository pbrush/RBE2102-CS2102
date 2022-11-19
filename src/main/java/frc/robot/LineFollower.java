package frc.robot;

/**
 * Creates an object for a Line Follower and the necessary methods
 * that utilize a light sensor object to follow a line.
 */
public class LineFollower {

    /**
     * lineFollower() is a method that uses the error calculated
     * by the light sensor to determine how much in each either direction
     * the robot needs to turn to get back ont he line.
     */
    public void lineFollower(int dPos){     //currently at 0.4  //
        if (dPos != 0){
            while(!Var.lightsensor.atIntersection()) {
                Var.drivetrain.arcadeDrive(Var.pid.baseSpeed, (Var.lightsensor.calcError() * Var.pid.kp));
            }
        }
        else {
            Var.drivetrain.arcadeDrive(0,0);
        }
    }

    public void lineFollowDist(double distance){
        Var.drivetrain.resetEncoders();
        double startDist = Var.drivetrain.getLeftDistanceInch();
        double currentDist = Var.drivetrain.getLeftDistanceInch();
        while (currentDist - startDist < distance){
            Var.drivetrain.arcadeDrive(Var.pid.baseSpeed, (Var.lightsensor.calcError() * Var.pid.kp));
            currentDist = Var.drivetrain.getLeftDistanceInch();
        }
        Var.drivetrain.arcadeDrive(0,0);
    }

    /**
     * lookingForLine is a method that turns the robot in a circle
     * until lightsensor.overWhite() returns true, or, the sensor
     * detects a line.
     */
    public void lookingForLine(int dir){
      while(Var.lightsensor.overWhite()){
          Var.drivetrain.arcadeDrive(0,0.40);
      }
    }
    
    /**
     * handleIntersection is a mehtod that centers the robot over an
     * intersection when the line sensor detects and intersection  This
     * is necessary because the light sensor is offset from the center
     * of the robot, meaning that the robot would miss the correct grid line
     * if it were to turn without centering itself.
     */
    public void handleIntersection(){
        lineFollowDist(2.7);  //1.7;
    }
  }
