
package frc.robot;

import javax.swing.JComponent;
import org.w3c.dom.css.Rect;
import java.awt.*;
import java.awt.geom.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/** 
 * DrawComponent is a class for all graphics used in the
 * GUI displaying the grid and the robot as it paths
 * from point to point.
 */
public class DrawComponent extends JComponent{


    /**
     * These are the variables used to create the grid with black rectangles.
     */
    private static final long serialVersionUID = 1L;

        Graphics2D g2;
        Color color = Color.BLACK;

        int windowWidth = 600;//getWidth();
        int windowHeight = 600; //getHeight();   

        int gridWidth = Grid.xUpBound;
        int gridHeight = Grid.yUpBound;

        int borderScale = 12;
        int borderWidth = windowWidth / (borderScale*(gridWidth+1));
        int borderHeight = windowHeight / (borderScale*(gridHeight+1));

        int nodeWidth = (windowWidth - (borderWidth * (gridWidth + 1))) / (gridWidth + 2);
        int nodeHeight = (windowHeight - (borderWidth * (gridHeight + 1))) / (gridHeight + 2);

        int gridLeftX = 0;       //do I even need these?
        int gridTopY = 0;

        /**
         * paintComponent is a method that draws the grid that
         * the the robot has to path through. 
         */
        public void paintComponent(Graphics g) {

        g2 = (Graphics2D) g;

        g2.setColor(color);
        
        Rectangle2D[][] gui = new Rectangle2D[Grid.xUpBound][Grid.yUpBound];

        /**
         * This is the loop used to mkae the Grid of squares.
         */
        for (int c = 1; c <= gridWidth; c++){
            for (int r = 1; r <= gridHeight; r++){
                Rectangle2D rect = new Rectangle2D.Double(gridLeftX + c * (nodeWidth + borderWidth), gridTopY + r * (nodeHeight + borderHeight), nodeWidth, nodeHeight);
                g2.draw(rect);
                g2.fill(rect);
             }
        }
    }
}
