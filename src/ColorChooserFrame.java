
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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A JFrame displaying a colored rectangle along with sliders and textfields for setting the 
 * color of the rectangle using RGB values.
 * 
 * @author agoettler
 *
 */
public class ColorChooserFrame extends JFrame
{
	/**
	 * ID number for serialization
	 */
	private static final long serialVersionUID = -1982661008601786406L;
	/**
	 * GridBagLayout for organizing the frame elements.
	 */
	private GridBagLayout chooserLayout;
	/**
	 * GridBagConstraints for organizing the frame elements.
	 */
	private GridBagConstraints chooserConstraints;
	/**
	 * JTextArea for labeling the red slider and text box.
	 */
	private JTextArea redLabel;
	/**
	 * JTextArea for labeling the green slider and text box.
	 */
	private JTextArea greenLabel;
	/**
	 * JTextArea for labeling the blue slider and text box.
	 */
	private JTextArea blueLabel;
	/**
	 * JSlider for adjusting the red color value.
	 */
	private JSlider redSlider;
	/**
	 * JSlider for adjusting the green color value.
	 */
	private JSlider greenSlider;
	/**
	 * JSlider for adjusting the blue color value.
	 */
	private JSlider blueSlider;
	/**
	 * JTextField for displaying and adjusting the red color value.
	 */
	private JTextField redField;
	/**
	 * JTextField for displaying and adjusting the green color value.
	 */
	private JTextField greenField;
	/**
	 * JTextField for displaying and adjusting the blue color value.
	 */
	private JTextField blueField;
	/**
	 * ColorPanel for displaying a rectangle of the specified color.
	 */
	private ColorPanel rectanglePanel;
	/**
	 * Action listener for the color JTextFields.
	 */
	private FieldHandler colorFieldHandler = new FieldHandler();
	/**
	 * Event listener for the color JSliders.
	 */
	private SliderHandler colorSliderHandler = new SliderHandler();
	
	/**
	 * This no-argument constructor instantiates a new ColorChooserFrame object.
	 */
	public ColorChooserFrame()
	{
		super("Color Chooser"); // use superclass constructor
		
		chooserLayout = new GridBagLayout();
		this.setLayout(chooserLayout);
		
		chooserConstraints = new GridBagConstraints();
		
		// set up panel
		rectanglePanel = new ColorPanel();
		
		// set up labels
		redLabel = new JTextArea("Red", 1, 5);
		greenLabel = new JTextArea("Green", 1, 5);
		blueLabel = new JTextArea("Blue", 1, 5);
		
		colorLabelCreator(redLabel);
		colorLabelCreator(greenLabel);
		colorLabelCreator(blueLabel);
		
		// set up sliders
		// set the sliders' initial values to those specified by the ColorPanel
		redSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, rectanglePanel.getRedValue());
		greenSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, rectanglePanel.getGreenValue());
		blueSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, rectanglePanel.getBlueValue());
		
		colorSliderCreator(redSlider);
		colorSliderCreator(greenSlider);
		colorSliderCreator(blueSlider);
		
		// set up fields
		// set the initial text to the values specified by the ColorPanel
		redField = new JTextField(String.valueOf(rectanglePanel.getRedValue()), 3);
		greenField = new JTextField(String.valueOf(rectanglePanel.getGreenValue()), 3);
		blueField = new JTextField(String.valueOf(rectanglePanel.getBlueValue()), 3);
		
		colorFieldCreator(redField);
		colorFieldCreator(greenField);
		colorFieldCreator(blueField);
		
		// set up the layout
		chooserConstraints.fill = GridBagConstraints.BOTH; // panel should resize in both directions
		this.addComponent(rectanglePanel, 0, 0, 4, 2); // add the panel to the layout
		
		chooserConstraints.fill = GridBagConstraints.NONE;// labels and fields should not resize
		
		this.addComponent(redLabel, 2, 0, 1, 1); // add the red label to the layout
		this.addComponent(greenLabel, 3, 0, 1, 1); // add the green label to the panel
		this.addComponent(blueLabel, 4, 0, 1, 1); // add the blue label to the panel
		
		this.addComponent(redField, 2, 3, 1, 1); // add the red field to the layout
		this.addComponent(greenField, 3, 3, 1, 1); // add the green field to the layout
		this.addComponent(blueField, 4, 3, 1, 1); // add the blue field to the layout
		
		chooserConstraints.fill = GridBagConstraints.HORIZONTAL; // sliders should resize horizontally
		this.addComponent(redSlider, 2, 1, 2, 1);
		this.addComponent(greenSlider, 3, 1, 2, 1);
		this.addComponent(blueSlider, 4, 1, 2, 1);
	}
	
	/*
	 * initializing the JTextAreas in this method causes nullPointerExceptions when
	 * the addComponent method is called; the nullPointerException is thrown by the
	 * gridBagLayout.setConstraints method
	 */
	/**
	 * This inner helper method is used for setting up the color labels.
	 * 
	 * @param colorLabel
	 */
	private void colorLabelCreator(JTextArea colorLabel)
	{
		// creates a new JTextArea object with 1 row and 5 columns
		// colorLabel = new JTextArea(label, 1, 5);
		colorLabel.setEnabled(false);
	}
	
	/*
	 * initializing the JSliders in this method causes nullPointerExceptions when
	 * the addComponent method is called; the nullPointerException is thrown by the
	 * gridBagLayout.setConstraints method
	 */
	/**
	 * This inner helper method is used for setting up the color sliders.
	 * 
	 * @param colorSlider
	 */
	private void  colorSliderCreator(JSlider colorSlider)
	{
		// create a new JSlider object with a min of 0, max of 255, and default of 0
		//colorSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		colorSlider.setMajorTickSpacing(50); // set major ticks every 50
		colorSlider.setMinorTickSpacing(10); // set minor ticks every 10
		colorSlider.setPaintTicks(true); // paint the ticks
		colorSlider.addChangeListener(colorSliderHandler); // add the event handler to each slider
	}
	
	/*
	 * initializing the JTextFields in this method causes nullPointerExceptions when
	 * the addComponent method is called; the nullPointerException is thrown by the
	 * gridBagLayout.setConstraints method
	 */
	/**
	 * This inner helper method is used for setting up the color text fields.
	 * 
	 * @param colorField
	 */
	private void colorFieldCreator(JTextField colorField)
	{
		// create a new JTextField object with 3 columns
		//colorField = new JTextField(3);
		colorField.addActionListener(colorFieldHandler); // add the action handler to each field
	}
	
	/**
	 * This inner helper method is used for adding components to the GridBagLayout.
	 * 
	 * @param component The component to be added
	 * @param row The grid row which the component should be placed on 
	 * @param column The grid column which the component should be placed on
	 * @param width The number of grid units the component should occupy horizontally
	 * @param height The number of grid units the component should occupy vertically
	 */
	private void addComponent(Component component, int row, int column, int width, int height)
	{
		chooserConstraints.gridy = row;
		chooserConstraints.gridx = column;
		chooserConstraints.gridwidth = width;
		chooserConstraints.gridheight = height;
		chooserLayout.setConstraints(component, chooserConstraints);
		this.add(component);
	}
	
	/**
	 * This private inner class is an ActionListener for the JTextFields used to set
	 * the RGB values of the rectangle.
	 * 
	 * @author agoettler
	 *
	 */
	private class FieldHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == redField)
			{
				try
				{
					rectanglePanel.setRedValue( Integer.parseInt( event.getActionCommand() ) );
					redSlider.setValue( rectanglePanel.getRedValue() );
				} 
				
				catch (NumberFormatException e)
				{
					e.printStackTrace();
					System.err.println("Value must be a number between 0 and 255.");
				}
			}
			
			else if(event.getSource() == greenField)
			{
				try
				{
					rectanglePanel.setGreenValue( Integer.parseInt( event.getActionCommand() ) );
					greenSlider.setValue( rectanglePanel.getGreenValue() );
				}
				
				catch (NumberFormatException e)
				{
					e.printStackTrace();
					System.err.println("Value must be a number between 0 and 255.");
				}
			}
			
			else if(event.getSource() == blueField)
			{
				try
				{
					rectanglePanel.setBlueValue( Integer.parseInt( event.getActionCommand() ) );
					blueSlider.setValue( rectanglePanel.getBlueValue() );
				}
				
				catch (NumberFormatException e)
				{
					e.printStackTrace();
					System.err.println("Value must be a number between 0 and 255.");
				}
			}
		}
	}
	
	/**
	 * This private inner class is a ChangeListener for the JSliders used to set the
	 * RGB values of the rectangle.
	 * 
	 * @author agoettler
	 *
	 */
	private class SliderHandler implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent event)
		{
			if(event.getSource() == redSlider)
			{
				rectanglePanel.setRedValue( redSlider.getValue() );
				redField.setText( String.valueOf( rectanglePanel.getRedValue() ) );
			}
			
			else if(event.getSource() == greenSlider)
			{
				rectanglePanel.setGreenValue( greenSlider.getValue() );
				greenField.setText( String.valueOf( rectanglePanel.getGreenValue() ) );
			}
			
			else if(event.getSource() == blueSlider)
			{
				rectanglePanel.setBlueValue( blueSlider.getValue() );
				blueField.setText( String.valueOf( rectanglePanel.getBlueValue() ) );
			}
		}
	}
}
