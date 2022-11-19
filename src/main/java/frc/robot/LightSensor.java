package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;


/**
 * lightSensor is a class for declaring an
 * object and the associated default methods.
 */
public class LightSensor {
    public final AnalogInput leftSensor;
    public final AnalogInput rightSensor;  //were previously private


    /**
     * Constructor for the LightSensor object, which initializes
     * the analog inputs for each individual sensor.
     */
    public LightSensor(){
        leftSensor = new AnalogInput(0);
        rightSensor = new AnalogInput(1);
    }

    /**
     * calcError is a method that calculates the difference between
     * the left and right reflectance sensors.
     * @return         the calculated error between the left and
     *                 right sensors.
     */
    public double calcError(){
        return (leftSensor.getAverageVoltage() - rightSensor.getAverageVoltage());

    }
    

    /**
     * atInterection() is a method that determines if the robot\
     * has passed an intersection by comparing the analog voltages 
     * from the left and right refelectance sensors and seeing if
     * BOTH are outputting values of higher than 2.1.
     * 
     * @return         true if the sensor detects an intersetcion,
     *                 and false if the sensor does not detect an
     *                  intersection.
     */
    public boolean atIntersection(){// previously 2.1
        return (leftSensor.getAverageVoltage() > 2.9 && rightSensor.getAverageVoltage() > 2.9);
    }


    /**
     * overWhite() is a method that determines if the robot
     * does not detect the refelctance tape that is used
     * for the grid on either sensor, or, is entirely over a
     * white part of the grid.  If it is entirely over white,
     * oth reflectance sensors will output values under 0.2.
     * 
     * @return          true if the sensor doesn't detect a line
     *                  and false if it does detect a line.
     */
    public boolean overWhite(){                         // previously 0.3
        return (leftSensor.getAverageVoltage() < 1.6 && rightSensor.getAverageVoltage() < 1.6);
    }
}