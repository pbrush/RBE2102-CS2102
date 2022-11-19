package frc.robot;


/**
 * PIDController is a class that uses a PID loop to
 * calculate the speed that the robot should be traveling.
 */
public class PIDController {

    /**
     * Declarations of the necessary variables
     * for the PID control.
     */
    double kp = 0.08;//0.09
    double baseSpeed = 0.4;
    double turnSpeed = 0.4;
    double ki = 0;
    double kd = 0;
    double calcError = Var.drivetrain.getRightDistanceInch() - Var.drivetrain.getLeftDistanceInch();
    double pidTurn = (calcError*kp) + (calcError*ki) + (calcError*kd);

}
