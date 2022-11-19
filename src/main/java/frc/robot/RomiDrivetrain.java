// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.lang.Math;


public class RomiDrivetrain {
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  //m_diffDrive.setSafetyEnabled(false);  *********HERE***********

  /** Creates a new RomiDrivetrain. */
  public RomiDrivetrain() {
    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
    m_rightMotor.setInverted(true);
    m_diffDrive.setSafetyEnabled(false);
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }


  /**
   * Variable for the conversion factor from degrees of the wheel turning
   * to the inches travelled.
   */
  double inPerDegrees = 0.04877777777;

  /**
   * Moves the robot forward a given amount at a given speed.
   * @param distance      Desired distance for the robot to travel.
   * @param speed         Desired speed for the robot to travel at.
   */
  public void moveForward(double distance, double speed){
  resetEncoders();
  double startDist = getLeftDistanceInch();
  double currentDist = getLeftDistanceInch();        
  while (currentDist - startDist < distance){
    arcadeDrive(speed, 0);
    currentDist = getLeftDistanceInch();
  }
  arcadeDrive(0,0);
}

  /**
   * Makes the robot turn right a set amount of degrees
   * at a desired speed.
   *
   * @param degrees     The amount of degrees for the robot to turn.
   * @param speed       The speed at which the robot will turn.
   */
  public void turnDegRight(double degrees, double speed){
  resetEncoders();
  double startDist = getLeftDistanceInch();
  double currentDist = getLeftDistanceInch();        
  double distInInches = inPerDegrees * degrees;
  while (currentDist -  startDist < distInInches){
    arcadeDrive(0,speed);
    currentDist = getLeftDistanceInch();              
  }
  arcadeDrive(0,0);
}

  /**
   * Makes the robot turn left a set amount of degrees
   * at a desired speed.
   *
   * @param degrees     The amount of degrees for the robot to turn.
   * @param speed       The speed at which the robot will turn.
   */
public void turnDegLeft(double degrees, double speed){
  resetEncoders();
  double startDist = getRightDistanceInch();
  double currentDist = getRightDistanceInch();
  double distInInches = inPerDegrees * degrees;
  while (currentDist -  startDist < distInInches){
    arcadeDrive(0,-speed);
    currentDist = getRightDistanceInch();
  }
  arcadeDrive(0,0);
}


}