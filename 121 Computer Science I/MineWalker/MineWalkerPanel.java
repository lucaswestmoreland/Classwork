import javax.swing.JButton;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is a game where the user must go from one corner of the 
 * grid to the other while hitting as few of the mines as possible.
 * @author Lucas Westmoreland
 *
 */
@SuppressWarnings("serial")
public class MineWalkerPanel extends JPanel implements ActionListener {

	//creates Panels to be used in GUI
	private JPanel gridPanel,dataPanel,minesPanel,pathPanel, colorKey, eastPanel;
	//creates Buttons to be used in GUI
	private JButton newGame, zeroMines, oneMine, twoMines, threeMines, explodedMines, You;
	//creates button array used to store buttons grid panel
	private JButton [] [] gridButtons;
	// creates JButton array for mines
	private boolean[][] gridMines;
	//creates Radio Buttons to be used in GUI
	private JRadioButton showPathY,showPathN,showMinesY, showMinesN;
	//creates JTextArea to be used in GUI
	private JTextField gridSizeArea;
	//creates JLabels to be used in GUI
	private JLabel Lives, Score;
	//Lives Variables
	private int numLives = 5;
	private int numScore = 500;
	//creates JSLider to be used in GUI
	private JSlider percentMinesSlider;
	//creates private integer to be used a grid size
	private int gridSize=10, percentMines;
	//creates button group used to radio buttons
	private ButtonGroup bGroup, bGroupTwo;
	//creates font to be used in GUI
	private Font myFont= new Font("Jokerman", 0, 48);
	private Font myFont2= new Font("Jokerman", 0,24);
	//creates string to be used in buttons
	private String blank="";
	//creates a randomwalk object to be used to create path to the finish line
	private RandomWalk walk;

	private JButton currButton;

	private boolean validity=true;

	private Point startPoint = new Point(gridSize-1,gridSize-1), currPoint, p; 
	//creates constructor to create the GUI and features of the GUI



/**
 * Main Constructor for MineWalker
 */
	public MineWalkerPanel(){

		//sets up main panel with a border layout
		this.setLayout(new BorderLayout());
		createGridPanel();
		createDataPanel();
		createEastPanel();

	}//end of method
/**
 * Constructor for GridPanel
 */
	private void createGridPanel(){

		//set up panel for grid and add buttons to grid
		gridPanel= new JPanel(); 
		gridPanel.setLayout(new GridLayout(gridSize,gridSize));
		gridPanel.setBorder(BorderFactory.createTitledBorder ("MineWalker"));
		gridPanel.setFont(myFont);
		gridPanel.setPreferredSize(new Dimension(700,500));
		gridButtons= new JButton[gridSize][gridSize];
		//creates button Array and buttons on gridPanel
		for(int i=0;i<gridButtons.length;i++){
			for(int j=0;j<gridButtons[i].length;j++){
				gridButtons[i][j]= new JButton(blank);
				gridPanel.add(gridButtons[i][j]);
				gridPanel.add(gridButtons[i][j]).setBackground(Color.LIGHT_GRAY);
				gridButtons[i][j].addActionListener(this);
			}		
		}
		//sets staring point
		gridButtons[gridSize-1][gridSize-1].setBackground(Color.WHITE);
		//creates walk
		walk= new RandomWalk(gridSize);
		walk.createWalk();
		//creates mines in the grid
		gridMines= new boolean[gridSize][gridSize];
		percentMines= ((gridButtons.length*gridButtons.length)-walk.getPath().size())/4;
		Random rand= new Random();
		Point tempPoint;
		int tempInt=0;
		int tempX, tempY;
		//sets all gridMines[][] values to false	
		for(int i=0; i<gridSize;i++){
			for(int j=0;j<gridSize;j++){
				gridMines[i][j]=false;
			}		
		}
		//sets random gridMines[][] to true 
		while(tempInt<=percentMines){
			tempX= rand.nextInt(gridSize);
			tempY= rand.nextInt(gridSize);
			tempPoint= new Point(tempX,tempY);
			if(!walk.getPath().contains(tempPoint)){
				gridMines[tempX][tempY]=true;
				tempInt++;
			}
		}
		//adds gripPanel to main panel
		this.add(gridPanel, BorderLayout.CENTER);

	}//end of create grid Panel method
/**
 * Constructor for Data Panel
 */
	private void createDataPanel(){

		//sets up panel for all options and data of the game
		dataPanel= new JPanel();
		dataPanel.setLayout(new GridLayout(1,6));
		dataPanel.setPreferredSize(new Dimension(700,100));
		//creates radio button for showing mines
		bGroup = new ButtonGroup();
		showMinesN= new JRadioButton("No");
		showMinesN.setSelected(true);
		showMinesY= new JRadioButton("Yes");
		bGroup.add(showMinesN);
		bGroup.add(showMinesY);
		showMinesY.addActionListener(this);
		showMinesN.addActionListener(this);
		//creates radio button for showing path
		bGroupTwo = new ButtonGroup();
		showPathN= new JRadioButton("No");
		showPathN.setSelected(true);
		showPathY= new JRadioButton("Yes");
		bGroupTwo.add(showPathN);
		bGroupTwo.add(showPathY);
		showPathY.addActionListener(this);
		showPathN.addActionListener(this);
		//adds mines radio buttons to dataPanel with a sub-panel
		minesPanel= new JPanel();
		minesPanel.add(showMinesY);
		minesPanel.add(showMinesN);
		minesPanel.setBorder(BorderFactory.createTitledBorder("Show Mines:"));
		//adds path radio buttons to dataPanel with a sub-panel
		pathPanel= new JPanel();
		pathPanel.add(showPathY);
		pathPanel.add(showPathN);
		pathPanel.setBorder(BorderFactory.createTitledBorder("Show Path:")); 
		//creates a new game button
		newGame= new JButton("New Game (Forfeit?)");
		newGame.addActionListener(this);
		//creates a text area for grid size input
		gridSizeArea= new JTextField();
		gridSizeArea.addActionListener(this);
		gridSizeArea.setBorder(BorderFactory.createTitledBorder("Grid Size"));
		//creates a JLabels for scores and lives
		Lives= new JLabel("Lives: " + numLives);
		Score= new JLabel("Score: " + numScore);
		//add components to dataPanel
		dataPanel.add(newGame);
		dataPanel.add(gridSizeArea);
		dataPanel.add(minesPanel);
		dataPanel.add(pathPanel);
		dataPanel.add(Lives);
		dataPanel.add(Score);
		//adds components to main Panel
		this.add(dataPanel, BorderLayout.SOUTH);

	}//end of dataPanel method
/**
 * Constructor for East Panel
 */
	private void createEastPanel(){

		zeroMines= new JButton("0 nearby Mines");
		zeroMines.setForeground(Color.black);
		zeroMines.setBackground(Color.green);
		oneMine= new JButton("1 nearby Mines");
		oneMine.setForeground(Color.black);
		oneMine.setBackground(Color.yellow);
		twoMines= new JButton("2 nearby Mines");
		twoMines.setForeground(Color.black);
		twoMines.setBackground(Color.orange);
		threeMines= new JButton("3 nearby Mines");
		threeMines.setForeground(Color.black);
		threeMines.setBackground(Color.red);
		explodedMines= new JButton(" Exploded Mine");
		explodedMines.setForeground(Color.white);
		explodedMines.setBackground(Color.black);
		You= new JButton("X<<<----You");
		//creates JSlider for minePercentage

		//SliderListener sListener = new SliderListener();
		percentMinesSlider= new JSlider(0,100,25);
		percentMinesSlider.setMajorTickSpacing(25);
		percentMinesSlider.setMinorTickSpacing(5);
		percentMinesSlider.setPaintTicks(true);
		percentMinesSlider.setBorder(BorderFactory.createTitledBorder("Percent of Mines"));
		//percentMinesSlider.addChangeListener(sListener);

		// adds mine Labels to color key panel
		colorKey= new JPanel();
		colorKey.setBorder(BorderFactory.createTitledBorder("Color Key"));
		colorKey.setPreferredSize(new Dimension(200,300));
		colorKey.add(zeroMines);
		colorKey.add(oneMine);
		colorKey.add(twoMines);
		colorKey.add(threeMines);
		colorKey.add(explodedMines);
		colorKey.add(You);
		//creates east Panel for color key and JSlider
		eastPanel= new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.Y_AXIS));
		eastPanel.add(colorKey);
		eastPanel.add(percentMinesSlider);
		//adds components to main Panel
		this.add(eastPanel, BorderLayout.EAST);

	}//end of create eastPanel method
/**
 * Get's the point at that certain spot in the grid
 * @param button
 * @return Point
 */
	private Point getPoint(JButton button){

		for(int i=0; i<gridSize;i++){
			for(int j=0;j<gridSize;j++){

				if(button.equals(gridButtons[i][j])){
					p = new Point(i,j);	
				}
			}
		}
		return p;

	}//end of getPoint method
/**
 * Starts a new game.
 */
	private void newGame()
	{
		this.remove(gridPanel);				
		createGridPanel();
		showPathN.setSelected(true);
		showMinesN.setSelected(true);
		this.revalidate();

		startPoint = new Point(gridSize-1,gridSize-1);
		numLives = 5;
		numScore = 500;
		Lives.setText("Lives: " + numLives);
		Score.setText("Score: " + numScore);
	}
/**
 * Checks to see if that spot in the grid is a mine
 * @param b
 * @return boolean
 */
	private boolean isNotMine(JButton b){

		Point pt= getPoint(b);

		if(gridMines[pt.x][pt.y]==true){
			validity=false;
		}
		else
		{
			validity = true;
		}


		return validity;

	}

/**
 * Gets the mines nearest to the spot that was clicked
 * @param button
 * @return int
 */
	private int getMines(JButton button)
	{

		Point p = getPoint(button);
		int count = 0;
		if(p.y + 1 < gridSize)
		{
			if(gridMines[p.x][p.y+1])
			{
				count++;
			}
		}
		if(p.y - 1 >= 0)
		{
			if(gridMines[p.x][p.y - 1])
			{
				count++;
			}
		}
		if(p.x + 1 < gridSize)
		{
			if(gridMines[p.x+1][p.y])
			{
				count++;
			}
		}
		if(p.x - 1 >= 0)
		{
			if(gridMines[p.x-1][p.y])
			{
				count++;
			}
		}
		return count;
	}





	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		//handles case where ShowPath is a activated
		if(e.getSource().equals(showPathY)){

			for(Point point: walk.getPath()){
				gridButtons[point.x][point.y].setBackground(Color.PINK);
			}

		}
		//handles case where Path is to be hidden

		else if(e.getSource().equals(showPathN)){

			for(Point point: walk.getPath()){
				gridButtons[point.x][point.y].setBackground(Color.LIGHT_GRAY);
				gridButtons[gridSize - 1][gridSize - 1].setBackground(Color.GREEN);
			}

		}

		//handles case where mines are to be shown
		else if(e.getSource().equals(showMinesY)){

			for(int i= 0; i<gridMines.length; i++){
				for(int j=0; j<gridMines[i].length;j++){
					if(gridMines[i][j]==true){
						gridButtons[i][j].setBackground(Color.BLUE);
					}
				}
			}

		}

		//handles case where mines are to be hidden
		else if(e.getSource().equals(showMinesN)){

			for(int i= 0; i<gridMines.length; i++){
				for(int j=0; j<gridMines[i].length;j++){
					if(gridMines[i][j]==true){
						gridButtons[i][j].setBackground(Color.LIGHT_GRAY);
					}
				}
			}

		}//end of else if


		else if(e.getSource()==gridSizeArea)
		{
			String str = gridSizeArea.getText();
			int y = Integer.parseInt(str);
			if(y <= 25)
			{
				gridSize = y;
			}
			newGame();



		}
		else {
			JButton source = (JButton)(e.getSource());	
			if(source.equals(newGame))
			{
				newGame();


			}//end of if

			else {


				currPoint= getPoint(source);
				System.out.println("clicked:"+currPoint);
				Point tempPoint=getPoint(source);
				System.out.println(tempPoint.x +" "+tempPoint.y);
				System.out.println(isNotMine(source));

				if(isNotMine(source)==true){


					if(currPoint.x-1==startPoint.x && currPoint.y==startPoint.y){ 

						gridButtons[currPoint.x][currPoint.y].setBackground(Color.GREEN);
						startPoint= currPoint;
						if(getMines(source) == 1)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.YELLOW);
						}
						if(getMines(source) == 2)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.ORANGE);
						}
						if(getMines(source) == 3)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.RED);
						}
						numScore += 25;
						Score.setText("Score: " + numScore);
						if(currPoint.x == 0 && currPoint.y == 0)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.CYAN);
							startPoint= currPoint;
							int n = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over",
									JOptionPane.YES_NO_OPTION);
							if(n == 0)
							{
								newGame();
							}
							else
							{
								System.exit(1);
							}

						}
					}

					else if(currPoint.x+1==startPoint.x && currPoint.y==startPoint.y ){
						gridButtons[currPoint.x][currPoint.y].setBackground(Color.GREEN);
						startPoint= currPoint;
						if(getMines(source) == 1)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.YELLOW);
						}
						if(getMines(source) == 2)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.ORANGE);
						}
						if(getMines(source) == 3)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.RED);
						}
						numScore += 25;
						Score.setText("Score: " + numScore);
						if(currPoint.x == 0 && currPoint.y == 0)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.CYAN);
							startPoint= currPoint;
							int n = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over",
									JOptionPane.YES_NO_OPTION);
							if(n == 0)
							{
								newGame();
							}
							else
							{
								System.exit(1);
							}

						}
					}

					else if(currPoint.y+1==startPoint.y && currPoint.x==startPoint.x){


						gridButtons[currPoint.x][currPoint.y].setBackground(Color.GREEN);
						startPoint= currPoint;
						if(getMines(source) == 1)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.YELLOW);
						}
						if(getMines(source) == 2)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.ORANGE);
						}
						if(getMines(source) == 3)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.RED);
						}
						numScore += 25;
						Score.setText("Score: " + numScore);
						if(currPoint.x == 0 && currPoint.y == 0)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.CYAN);
							startPoint= currPoint;
							int n = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over",
									JOptionPane.YES_NO_OPTION);
							if(n == 0)
							{
								newGame();
							}
							else
							{
								System.exit(1);
							}

						}
					}

					else if(currPoint.y-1==startPoint.y && currPoint.x == startPoint.x){

						gridButtons[currPoint.x][currPoint.y].setBackground(Color.GREEN);
						startPoint=currPoint;
						if(getMines(source) == 1)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.YELLOW);
						}
						if(getMines(source) == 2)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.ORANGE);
						}
						if(getMines(source) == 3)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.RED);
						}
						numScore += 25;
						Score.setText("Score: " + numScore);
						if(currPoint.x == 0 && currPoint.y == 0)
						{
							gridButtons[currPoint.x][currPoint.y].setBackground(Color.CYAN);
							startPoint= currPoint;



							int n = JOptionPane.showConfirmDialog(null, "Would you like to play again", "Game Over",
									JOptionPane.YES_NO_OPTION);
							if(n == 0)
							{
								newGame();
							}
							else
							{
								System.exit(1);
							}

						}
					}




				}

				else if(isNotMine(source) == false)
				{


					if(currPoint.x-1==startPoint.x && currPoint.y==startPoint.y){ 

						gridButtons[currPoint.x][currPoint.y].setBackground(Color.BLACK);
						currPoint=startPoint;
						numLives--;
						numScore -= 100;

						Lives.setText("Lives: " + numLives);
						Score.setText("Score: " + numScore);


					}

					else if(currPoint.x+1==startPoint.x && currPoint.y==startPoint.y ){
						gridButtons[currPoint.x][currPoint.y].setBackground(Color.BLACK);
						currPoint=startPoint;
						numLives--;
						numScore -= 100;

						Lives.setText("Lives: " + numLives);
						Score.setText("Score: " + numScore);
					}

					else if(currPoint.y+1==startPoint.y && currPoint.x==startPoint.x){

						gridButtons[currPoint.x][currPoint.y].setBackground(Color.BLACK);
						currPoint=startPoint;
						numLives--;
						numScore -= 100;

						Lives.setText("Lives: " + numLives);
						Score.setText("Score: " + numScore);
					}

					else if(currPoint.y-1==startPoint.y && currPoint.x == startPoint.x){

						gridButtons[currPoint.x][currPoint.y].setBackground(Color.BLACK);
						currPoint=startPoint;
						numLives--;
						numScore -= 100;

						Lives.setText("Lives: " + numLives);
						Score.setText("Score: " + numScore);
					}
					source.removeActionListener(this);

					if(numLives == 0)
					{
						int n = JOptionPane.showConfirmDialog(null, "You lost. Would you like to play again?", "Game Over",
								JOptionPane.YES_NO_OPTION);
						if(n == 0)
						{
							newGame();
						}
						else
						{
							System.exit(1);
						}


					}
				}

			}
		}//end of else







	}// end of action listener event




}//end of class