package frc.robot;

import java.util.ArrayList;

/**
 * Algorithm is an interface with a findPath() method
 * ensuring that any class that implements Algorithm has
 * a findPath() method.
 */
public interface Algorithm {
    public static ArrayList<Node> findPath(Grid grid, Node startPos, Node destPos) {
        return null;
    }
}
