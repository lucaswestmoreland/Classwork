import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Displays a grid of JButtons and a JTextArea. Together,
 * they act as a very small keyboard and text display.
 * This class manages layout of controls and also handles events.
 * 
 * @author mvail
 */
@SuppressWarnings("serial")
public class TextButtonsPanel extends JPanel implements ActionListener {
	private JButton[] buttons;	//Do not change
	private JTextArea textArea;	//Do not change

	/**
	 * Constructor
	 * @author Lucas Westmoreland
	 */
	public TextButtonsPanel() {
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(4 ,3));
		gridPanel.setPreferredSize(new Dimension(300, 300));
		
		
		//buttons
		buttons = new JButton[12];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(""+(1+i));;
			
			
			if(i == 9)
				buttons[i].setText("Enter");
			
			if(i == 10)
				buttons[i].setText("Space");
			
			if(i == 11)
				buttons[i].setText("Clear");
			
			buttons[i].addActionListener(this);
			gridPanel.add(buttons[i]);
			
		}
		
		this.add(gridPanel);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(140, 350));
		this.add(scrollPane);
	}

	@Override
	/**
	 * Action Listener
	 */
	public void actionPerformed(ActionEvent e) {
		
		
		if((JButton)e.getSource() == buttons[9])
		{
			textArea.append("\n");
		}
		
		else if((JButton)e.getSource() == buttons[10])
		{
			textArea.append(" ");
		}
		else if((JButton)e.getSource() == buttons[11])
		{
			textArea.setText("");
		}
		else if((JButton)e.getSource() == buttons[0])
		{
			textArea.append("1");
		}
		else if((JButton)e.getSource() == buttons[1])
		{
			textArea.append("2");
		}
		else if((JButton)e.getSource() == buttons[2])
		{
			textArea.append("3");
		}
		else if((JButton)e.getSource() == buttons[3])
		{
			textArea.append("4");
		}
		else if((JButton)e.getSource() == buttons[4])
		{
			textArea.append("5");
		}
		else if((JButton)e.getSource() == buttons[5])
		{
			textArea.append("6");
		}
		else if((JButton)e.getSource() == buttons[6])
		{
			textArea.append("7");
		}
		else if((JButton)e.getSource() == buttons[7])
		{
			textArea.append("8");
		}
		else if((JButton)e.getSource() == buttons[8])
		{
			textArea.append("9");
		}
		
		
		
		
		
		
		//TODO update the contents of textArea according to which
		//  button generated the ActionEvent.
	}
}