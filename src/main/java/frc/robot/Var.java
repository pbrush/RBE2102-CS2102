package frc.robot;

/** Var is a class meant to hold
 * the instatniations of every object
 * used in the project.  I put them all in
 * this class instead of dispersed through
 * multiple other classes because I couldn't
 * keep track of them like that.*/
public class Var {
    public static Grid grid = new Grid();
    public static LightSensor lightsensor = new LightSensor();
    public static RomiDrivetrain drivetrain = new RomiDrivetrain();
    public static PIDController pid = new PIDController();
    public static SharpIR sharpIR = new SharpIR();
    public static Dijkstras dijkstras = new Dijkstras();
    public static Manhattan manhattan = new Manhattan();
    public static Odometry odometry = new Odometry();
    public static LineFollower linefollower = new LineFollower();

}
