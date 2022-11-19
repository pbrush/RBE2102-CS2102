
package frc.robot;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;


import javax.swing.*;



/**
 * GraphicsTest is a class that makes the window/frame where
 * the GUI is displayed.  Each button represents an intersection on
 * the grid that the robot is meant to travel on.  Once you click on
 * a button it will turn green to signify that the robot is pathing to
 * that button.  Each button is white to signify that it is a blank intersection
 * (not blocked, not where the robot is, nor is it the destination), with a
 * black box behind it to give each button an outline to be visible (this isn't
 * necessary, but I made the graphics using Draw Component work before the Buttons
 * worked, and then I realized I didn't need the graphics, but didn't have the heart
 * to get rid of them after sinking so much time into them).
 */
public class GraphicsTest extends JComponent{
    

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 600;
    public static final JFrame frame = new JFrame("Graphics Test");

    /**
     * runGraphicsTest() is the method that creates a new JFrame object where
     * the GUI is displayed, a new DrawComponent objet to display the graphics,
     * and a grid of JButton objects.
     */
    public static void runGraphicsTest(){

        /**
         * These are declarations/instantiations of certain related
         * variables/objects.
         */
        Var.grid.gridNeighbors();
        Var.odometry.currentPos = Var.grid.grid[0][0];

        int windowWidth = DEFAULT_WIDTH;
        int windowHeight = DEFAULT_HEIGHT;

        int gridWidth = 6;
        int gridHeight = 6;
        int borderScale = 12;
        int borderWidth = windowWidth / (borderScale*(gridWidth+1));  //was 10
        int borderHeight = windowHeight / (borderScale*(gridHeight+1));
        int nodeWidth = (windowWidth - (borderWidth * (gridWidth + 1))) / (gridWidth + 2);
        int nodeHeight = (windowHeight - (borderWidth * (gridHeight + 1))) / (gridHeight + 2);
        int nodeArea = nodeWidth * nodeHeight;
        int gridLeftX = 0;       
        int gridTopY = 0;
        
        Button[] currentDestination = new Button[]{null};

        /**
         * This large block of code is where the JFrame, DrawComponent,
         * and Button Objects are made to create the GUI.
         */
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                //JFrame frame = new JFrame("Graphics Test");
                frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

                DrawComponent component = new DrawComponent();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(component);
                //JButton[][] button = new JButton[6][6];
                for (int r = 0; r < Grid.xUpBound; r++){
                    for (int c = 0; c < Grid.yUpBound; c++){
                        final int R = r, C = c;
                        Button button = new Button(r, c);
                        button.setBackground(Color.WHITE);
                        button.setBounds(gridLeftX + (r+1) * (nodeWidth + borderWidth), gridTopY + (6-c) * (nodeHeight + borderHeight), nodeWidth, nodeHeight);
                        component.add(button);
                        button.setVisible(true);
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Button pressed");
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        /**
                                         * This is the input for the method, which determines what a press of the button
                                         * will actually make the robot do.
                                         */
                                        button.setBackground(Color.GREEN);
                                        frame.repaint();
                                        //Navigation.navPath("dijkstra", Var.odometry.currentPos, Var.grid.grid[R][C]);
                                        //Navigation.navigateTo(Var.grid.getNode(R,C));
                                        //Navigation.navPath("manhattan", Var.grid.getNode(Var.odometry.currentPos.getRow(),Var.odometry.currentPos.getCol()), Var.grid.getNode(R,C));
                                        //System.out.print(Var.dijkstras.findPath(Var.grid, Var.grid.getNode(0,0), Var.grid.getNode(R,C)));
                                        Navigation.newNavPath("dijkstras", Var.grid.getNode(Var.odometry.currentPos.getRow(),Var.odometry.currentPos.getCol()), Var.grid.getNode(R,C));
                                        //System.out.println(Var.grid.grid[R][C].neighbors);


                                    }
                                });
                                /**
                                 * This handles which buttons are and aren't green.
                                 */
                                if(currentDestination[0] != null){
                                    currentDestination[0].setBackground(Color.WHITE);
                                }
                                currentDestination[0] = button;
                            }
                        });
                    }
                }
                frame.setVisible(true);
                System.out.println(frame.getWidth() + ", " + frame.getHeight());
            }
        });
    }
}