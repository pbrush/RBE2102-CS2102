package frc.robot;

import javax.swing.JButton;
import java.awt.*;
import java.awt.Graphics;

/**
 * Button is a class that extends JButton
 * to have the same functionality of JButton
 * with the addition of storing X and Y coordaintes
 * that coincide with the coordinates on the grid that
 * each button represents.*/
public class Button extends JButton{

    /**
     * Variable declarations
     */
    int xPos;
    int yPos;
    //boolean destination;

    /**
     * Constructor for the Button objects
     * @param x         The associated X coordinate
     * @param y         The associated Y coordinate
     */
    public Button(int x, int y){
        super();
        xPos = x;
        yPos = y;
        //destination = false;
    }

    /**
     * getXPos() is a method to return the x coordinate
     * of the button.
     *
     * @return          An int representing the associated x coordinate
     */
    public int getXPos(){
        return xPos;
    }

    /**
     * getXPos() is a method to return the y coordinate
     * of the button.
     *
     * @return          An int representing the associated y coordinate
     */
    public int getYPos(){
        return yPos;
    }

    }


