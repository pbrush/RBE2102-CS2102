//package frc.robot;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.*;
import frc.robot.Grid;
import frc.robot.Node;
import frc.robot.Dijkstras;
import frc.robot.Var;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class JUnit {

    /**
     * testBounds is a test that checks if a given Node
     * is out of the bounds of the grid.
     */
    @Test
    public void testBounds(){
        Node[][] grid = new Node[5][5];
        Grid gridTest = new Grid();
        Node testNode1 = new Node(1,2);
        Node testNode2 = new Node(7,8);
        Node testNode3 = new Node(0,0);
        Node testNode4 = new Node(4,4);
        Node testNode5 = new Node(6,6);
        boolean node1State = Grid.checkBounds(testNode1, gridTest);
        boolean node2State = Grid.checkBounds(testNode2, gridTest);
        boolean node3State = Grid.checkBounds(testNode3, gridTest);
        boolean node4State = Grid.checkBounds(testNode4, gridTest);
        boolean node5State = Grid.checkBounds(testNode5, gridTest);

        assertEquals(true, node1State);
        assertEquals(false, node2State);
        assertEquals(true, node3State);
        assertEquals(true, node4State);
        assertEquals(false, node5State);


    }

    /**
     * testPathLength is a test that checks the length of a path
     * to a given Node.
     */
    @Test
    public void testPathLength(){
//        Node[][] grid = new Node[5][5];
//        Grid gridTest = new Grid();
        Dijkstras testDijkstras = new Dijkstras();

        int testPathLength1 = testDijkstras.findPath(Var.grid, Var.grid.getNode(0,0), Var.grid.getNode(3,2)).size();
        int testPathLength2 = testDijkstras.findPath(Var.grid, Var.grid.getNode(0,0), Var.grid.getNode(0,0)).size();
        int testPathLength3 = testDijkstras.findPath(Var.grid, Var.grid.getNode(3,2), Var.grid.getNode(0,0)).size();

        assertEquals(5, testPathLength1, 0);
        assertEquals(0, testPathLength2, 0);
        assertEquals(5, testPathLength3, 0);
    }
}
