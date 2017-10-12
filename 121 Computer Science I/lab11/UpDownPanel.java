import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UpDownPanel extends JPanel {
	private JButton up, down;
	private JLabel label;
	private JPanel buttonPanel;
	private int counter = 50;

	/**
	 * Constructor: Sets up the GUI.
	 */
	public UpDownPanel()
	{
		up = new JButton("Up");
		down = new JButton("Down");

		ButtonListener listener = new ButtonListener();
		up.addActionListener(listener);
		down.addActionListener(listener);

		label = new JLabel("" + counter);

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(200, 40));
		buttonPanel.setBackground(Color.orange);
		buttonPanel.add(up);
		buttonPanel.add(down);

		setPreferredSize(new Dimension(200, 80));
		Color lightGreen = new Color(206, 255, 199);
		setBackground(lightGreen);
		add(label);
		add(buttonPanel);
	}

	/**
	 * Represents a listener for both buttons.
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Determines which button was pressed and sets the label text
		 * accordingly.
		 */
		public void actionPerformed(ActionEvent event)
		{
			JButton source = (JButton)event.getSource();
			if (source == up)
			{
				counter++;
			label.setText(""+counter);
			}
			else
			{
				counter--;
			label.setText(""+counter);
		}
		}
	}
}
