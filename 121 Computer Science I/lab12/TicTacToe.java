import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TicTacToe extends JPanel implements ActionListener {
	private JButton newGame = new JButton("New Game");
	private JButton[][] buttons = new JButton[3][3];
	private final int GRID_DIM = 3; 
	private JPanel scores = new JPanel();
	private int numWins = 0;
	private int numLosses = 0;
	private JLabel wins = new JLabel("Wins: ");
	private JLabel losses = new JLabel("Losses: ");
	
	
	
	private TicTacToe() {
		
		this.setLayout(new BorderLayout());
		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(GRID_DIM, GRID_DIM));
		gameBoard.setPreferredSize(new Dimension(200,200));

		
		for(int i = 0; i < buttons.length; i++)
		{
			for(int j = 0; j < buttons[i].length; j++)
			{
				buttons[i][j] = new JButton("");
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFont(new Font("Arial", 0, 32));
				gameBoard.add(buttons[i][j]);
			}
		}
		
		newGame.addActionListener(this);

		scores.add(wins);
		scores.add(losses);
		this.add(gameBoard, BorderLayout.CENTER);
		this.add(newGame, BorderLayout.WEST);
		this.add(scores, BorderLayout.SOUTH);
		
		
	}
	
	private void ComputerMove()
	{
	
	
	for(int i = 0; i < buttons.length; i++)
		{
			for(int j = 0; j < buttons[i].length; j++)
			{
				if(buttons[i][j].getText().equals(""))
				{
					buttons[i][j].setText("O");
					return;
				}
				
			}
		}
	}
	

	
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == newGame)
		{
			for(int i = 0; i < buttons.length; i++)
			{
				for(int j = 0; j < buttons[i].length; j++)
				{
					buttons[i][j].setText("");
				}
			}
		}
		
		for (int i = 0; i < buttons.length; i++) 
		{
			for (int j = 0; j < buttons[i].length; j++) 
			{
				if (e.getSource() == buttons[i][j] && buttons[i][j].getText().equals("")) 
				{
					buttons[i][j].setText("X");
					ComputerMove();
				}	
			}
		}
		
		
		
		
		
		
	}
	
	
	
				
				

		
	
		


		
		public static void main(String[] args) {
			JFrame frame = new JFrame("TicTacToe");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			TicTacToe game = new TicTacToe();
			frame.getContentPane().add(game);
			
			frame.pack();
			frame.setVisible(true);
		}
	


	
	
	

}
