package memorytiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.Font;

public class game extends JFrame {

	private JPanel contentPane;
	private JLabel tile;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int numtiles;
	private int dimensions = 5;
	private int mistakes = 0;
	private int uncovered = 0;
	//used for countdown
	int time;
	//time taken per level
	long start;
	long end;
	Timer xTimer;
	private int[][]grid = new int[dimensions][dimensions];
	//icons
	Image tileimg = toolkit.getImage("images/tile.png"); 
	ImageIcon icon = new ImageIcon(tileimg);
	Image ximg = toolkit.getImage("images/x.png"); 
	ImageIcon xicon = new ImageIcon(ximg);
	Image correctimg = toolkit.getImage("images/correct2.png"); 
	ImageIcon correcticon = new ImageIcon(correctimg);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game frame = new game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 * @throws InterruptedException 
	 */
	public game() throws FileNotFoundException, InterruptedException {
		setIconImage(tileimg);
		//reads in number of tiles from file
		File file = new File("src/userdata/numTiles.txt");
		Scanner sc = new Scanner(file);
		numtiles = sc.nextInt();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setBounds(100, 100, 750, 750);
		countDown();

	}
	public void countDown()
	{
		time = 3;
		xTimer = null;
		JLabel countdown = new JLabel(Integer.toString(time));
		countdown.setForeground(Color.WHITE);
		countdown.setFont(new Font("Tahoma", Font.BOLD, 150));
		countdown.setBounds(340,230,150,150);
		contentPane.add(countdown);
		ActionListener animateX = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(time == 1)
				{
					xTimer.setRepeats(false);
					xTimer.stop();
					setDimensions();
					start();
					return;
				}
				else if (time > 1)
				{
					time--;
					countdown.setText(Integer.toString(time));
				}

			}
		};
		xTimer = new Timer(1000, animateX);
		xTimer.setRepeats(true);
		xTimer.start();

	}
	public void start()
	{
		mistakes = 0;
		uncovered = 0;
		grid = generateArray();
		printGrid();
		addTiles();
		hideTiles();
	}

	public void setDimensions()
	{
		if (numtiles < 10)
		{
			dimensions = 5;
		}
		else if (numtiles >= 10 && numtiles < 15) 
		{
			dimensions = 6;
		}
		else if(numtiles >= 15  && numtiles < 20)
		{
			dimensions = 7;
		}
		else if (numtiles >= 20)
		{
			dimensions = 8;
		}

		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.setLayout(new GridLayout(dimensions, dimensions, 10, 10));
	}

	public int[][] generateArray()
	{
		grid = new int[dimensions][dimensions];
		//creates 2d array with tiles at random squares in the grid
		for (int i = 0; i < numtiles; i++)
		{
			int randomX = (int)(Math.random() * (grid.length));
			int randomY = (int)(Math.random() * (grid.length));
			while (grid[randomX][randomY] == 1)
			{
				randomX = (int)(Math.random() * (grid.length));
				randomY = (int)(Math.random() * (grid.length));
			}
			grid[randomX][randomY] = 1;
		}
		return grid;
	}

	public void addTiles()
	{
		//clears previous tiles
		contentPane.removeAll();
		contentPane.revalidate();	
		//adds tile texture where there is a one in the 2d array
		for (int i = 0; i < grid.length; i++)
		{
			for (int x = 0; x < grid[i].length; x++)
			{
				JLabel tile = new JLabel();
				if(grid[i][x] == 1)
				{
					tile.setIcon(icon);
				}
				else
				{
					tile.setBackground(Color.BLACK);
				}
				tile.setOpaque(true);
				contentPane.add(tile);
			}
		}
	}

	public void hideTiles()
	{
		//start counting time
		start = System.currentTimeMillis();
		//hide tiles
		ActionListener delay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();

				for (int i = 0; i < grid.length;i++)
				{
					for (int x = 0; x < grid[i].length; x++)
					{
						JButton tile = new JButton();
						tile.setBackground(Color.BLACK);
						tile.setIcon(null);
						tile.setOpaque(true);
						tile.setVisible(true);

						//if user chooses correct tile then the blue tile is uncovered
						if (grid[i][x] == 1)
						{
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									//if tiles is not already uncovered
									if(tile.getIcon() != icon)
									{
										tile.setIcon(icon);
										uncovered++;
										//checks if user uncovers all tiles
										if(uncovered == numtiles)
										{
											try {
												addLevelsPlayed();
												//get total time for level
												end = System.currentTimeMillis();
												averageTime((end - start)/1000);
											} catch (FileNotFoundException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
											//make last tile have a check mark on it
											tile.setIcon(correcticon);
											//increases numtiles in txt file
											try {
												changeNumTiles(1);
											} catch (FileNotFoundException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											//prevents user from clicking more tiles during delay
											setEnabled(false);
											//2s before next level
											ActionListener delay2 = new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setEnabled(true);
													setDimensions();
													start();
												}
											};
											Timer delaytimer2 = new Timer(2000, delay2);
											delaytimer2.setRepeats(false);
											delaytimer2.start();
										}
									}

								}
							});
						}
						//if not an x is displayed
						else
						{
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									//if tile was already uncovered and was a wrong choice
									if (tile.getIcon() != xicon)
									{
										tile.setIcon(xicon);
										mistakes++;
										try {
											//adds total mistakes to txt file
											addMistakes();
										} catch (FileNotFoundException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
										//if more than 2 mistakes the player loses
										if (mistakes > 2)
										{
											try {
												addLevelsPlayed();
												addLevelsFailed();
												//get total time for level
												end = System.currentTimeMillis();
												averageTime((end - start)/1000);
											} catch (FileNotFoundException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
											//minimum 5 tiles
											if(numtiles > 5)
											{
												//lowers numtiles in txt file
												try {
													changeNumTiles(-1);
												} catch (FileNotFoundException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											//prevents user from clicking more tiles during delay
											setEnabled(false);
											ActionListener animateX = new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													if (tile.getIcon() == xicon)
													{
														tile.setIcon(null);
													}
													else
													{
														tile.setIcon(xicon);
													}
												}

											};
											Timer xTimer = new Timer(250, animateX);
											xTimer.setRepeats(true);
											xTimer.start();
											//2s before next level
											ActionListener delay2 = new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setEnabled(true);
													setDimensions();
													start();
												}
											};
											Timer delaytimer2 = new Timer(2000, delay2);
											delaytimer2.setRepeats(false);
											delaytimer2.start();
										}
									}
								}
							});
						}
						contentPane.add(tile);
					}
				}
			}	
		};
		//3s to memorize tiles
		Timer delaytimer = new Timer(3000, delay);
		delaytimer.setRepeats(false);
		delaytimer.start();
	}

	public void changeNumTiles(int num) throws FileNotFoundException
	{
		changeMostTiles();
		numtiles = numtiles + num;
		List<String> lines = Arrays.asList(Integer.toString(numtiles));
		Path file = Paths.get("src/userdata/numTiles.txt");
		try {
			Files.write(file, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeMostTiles() throws FileNotFoundException
	{
		File file1 = new File("src/userdata/mostTiles.txt");
		Scanner sc = new Scanner(file1);
		int mostTiles = sc.nextInt();
		//writes new highest tiles to txt file
		if(numtiles > mostTiles)
		{
			List<String> lines = Arrays.asList(Integer.toString(numtiles));
			Path file2 = Paths.get("src/userdata/mostTiles.txt");
			try {
				Files.write(file2, lines, StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addMistakes() throws FileNotFoundException
	{
		File file1 = new File("src/userdata/mistakes.txt");
		Scanner sc = new Scanner(file1);
		int mistakes = sc.nextInt();
		//writes new highest tiles to txt file
		List<String> lines = Arrays.asList(Integer.toString(mistakes+1));
		Path file2 = Paths.get("src/userdata/mistakes.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addLevelsPlayed() throws FileNotFoundException
	{
		File file1 = new File("src/userdata/levelsplayed.txt");
		Scanner sc = new Scanner(file1);
		int lvlsplayed = sc.nextInt();
		//writes new highest tiles to txt file
		List<String> lines = Arrays.asList(Integer.toString(lvlsplayed+1));
		Path file2 = Paths.get("src/userdata/levelsplayed.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addLevelsFailed() throws FileNotFoundException
	{
		File file1 = new File("src/userdata/levelsfailed.txt");
		Scanner sc = new Scanner(file1);
		int lvlsfailed = sc.nextInt();
		//writes new highest tiles to txt file
		List<String> lines = Arrays.asList(Integer.toString(lvlsfailed+1));
		Path file2 = Paths.get("src/userdata/levelsfailed.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void averageTime(long time) throws FileNotFoundException
	{
		File file1 = new File("src/userdata/avrgtime.txt");
		Scanner sc = new Scanner(file1);
		double totaltime = sc.nextDouble();
		totaltime += time;

		//writes new highest tiles to txt file
		List<String> lines = Arrays.asList(Double.toString((totaltime)));
		Path file2 = Paths.get("src/userdata/avrgtime.txt");
		try {
			Files.write(file2, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void IQ()
	{

	}

	public void printGrid()
	{
		System.out.println("\n");
		for (int i = 0; i < grid.length; i++)
		{
			System.out.println(Arrays.toString(grid[i]));
		}
	}

}
