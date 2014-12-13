
/*
 * Introduction to Software Design 2014
 * Author: Andrew Goettler
 * Problem: 25.13 Creating a Color Chooser: Modification (p. 1044)
 * Problem difficulty: Hard
 * Problem description: Modify the application in Exercise 25.12 to draw the current color as a
 * 		rectangle on an instance of a subclass of JPanel which provides its own paint-Component
 * 		method to draw the rectangle and provides set methods to set the red, green, and blue
 * 		values for the current color. When any set method is invoked, the drawing panel should
 * 		should automatically repaint itself.
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author agoettler
 *
 */
public class ColorPanel extends JPanel
{
	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = -7950327431518505720L;
	/**
	 * Red value for the panel color
	 */
	private int redValue = 255;
	/**
	 * Green value for the panel color
	 */
	private int greenValue = 255;
	/**
	 * Green value for the panel color
	 */
	private int blueValue = 255;
	
	/**
	 * This method paints the rectangle displayed in the color panel.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(new Color(redValue, blueValue, greenValue));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * This method sets the red value of the rectangle color.
	 * 
	 * @param redValue
	 */
	public void setRedValue(int redValue)
	{
		if(redValue >= 0 && redValue <= 255)
		{
			this.redValue = redValue;
			repaint();
		}
	}
	
	/**
	 * This method sets the green value of the rectangle color.
	 * 
	 * @param greenValue
	 */
	public void setGreenValue(int greenValue)
	{
		if(greenValue >= 0 && greenValue <= 255)
		{
			this.greenValue = greenValue;
			repaint();
		}
	}
	
	/**
	 * This method sets the blue value of the rectangle color.
	 * 
	 * @param blueValue
	 */
	public void setBlueValue(int blueValue)
	{
		if(blueValue >= 0 && blueValue <= 255)
		{
			this.blueValue = blueValue;
			repaint();
		}
	}
	
	/**
	 * This method returns the red value of the current rectangle color.
	 * 
	 * @return
	 */
	public int getRedValue()
	{
		return this.redValue;
	}
	
	/**
	 * This method returns the green value of the current rectangle color.
	 * 
	 * @return
	 */
	public int getGreenValue()
	{
		return this.greenValue;
	}
	
	/**
	 * This method returns the blue value of the current rectangle color.
	 * 
	 * @return
	 */
	public int getBlueValue()
	{
		return this.blueValue;
	}
}
