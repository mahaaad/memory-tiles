package memorytiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	JLabel lblMemoryTiles;
	JLabel gray1 = new JLabel();
	JLabel gray2 = new JLabel();
	JLabel gray3 = new JLabel();
	JLabel gray4 = new JLabel();
	JButton btnNewButton;
	JButton btnStats;
	JButton btnHelp;
	JButton btnQuit;
	int buttonx;
	int buttony;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image tileimg = toolkit.getImage("images/tile.png"); 
	ImageIcon icon = new ImageIcon(tileimg);
	ImageIcon grayicon;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	public Menu() throws InterruptedException {
		setIconImage(tileimg);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setBounds(100, 100, 750, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		buttonx = 250;
		buttony = 200;

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(this);

		Image backgroundimg = toolkit.getImage("images/background.png"); 
		ImageIcon backgroundicon = new ImageIcon(backgroundimg);

		Image grayimg = toolkit.getImage("images/gray.png"); 
		tracker.addImage(grayimg, 0);
		tracker.waitForAll();
		grayimg = grayimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		grayicon = new ImageIcon(grayimg);

		btnNewButton = new JButton("");
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		Image playimg = toolkit.getImage("images/play.png");
		tracker.addImage(playimg, 0);
		tracker.waitForAll();
		playimg = playimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		ImageIcon playicon = new ImageIcon(playimg);
		btnNewButton.setIcon(playicon);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.main(null);
				dispose();

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				gray1.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				gray1.setVisible(false);
			}
		});
		btnNewButton.setBounds(850, buttony, 240, 70);
		contentPane.add(btnNewButton);

		btnHelp = new JButton("HELP");
		btnHelp.setOpaque(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);

		Image helpimg = toolkit.getImage("images/help.png");
		tracker.addImage(helpimg, 0);
		tracker.waitForAll();
		helpimg = helpimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		ImageIcon helpicon = new ImageIcon(helpimg);
		btnHelp.setIcon(helpicon);
		btnHelp.addMouseListener(new MouseAdapter()  {
			public void mouseClicked(MouseEvent arg0) {
				help.main(null);
				dispose();
			}
			public void mouseEntered(MouseEvent arg0) {
				gray3.setVisible(true);
			}
			public void mouseExited(MouseEvent arg0) {
				gray3.setVisible(false);
			}
		});
		btnHelp.setBounds(950, buttony + 200, 240, 70);
		contentPane.add(btnHelp);

		btnStats = new JButton("STATS");
		btnStats.setOpaque(false);
		btnStats.setContentAreaFilled(false);
		btnStats.setBorderPainted(false);

		Image buttonimg = toolkit.getImage("images/stats.png");
		tracker.addImage(buttonimg, 0);
		tracker.waitForAll();
		buttonimg = buttonimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		ImageIcon statsicon = new ImageIcon(buttonimg);
		btnStats.setIcon(statsicon);
		btnStats.addMouseListener(new MouseAdapter()  {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				stats.main(null);
			}
			public void mouseEntered(MouseEvent arg0) {
				gray2.setVisible(true);
			}
			public void mouseExited(MouseEvent arg0) {
				gray2.setVisible(false);
			}
		});
		btnStats.setBounds(900, buttony + 100, 240, 70);
		contentPane.add(btnStats);

		btnQuit = new JButton("QUIT");
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		Image quitimg = toolkit.getImage("images/quit.png");
		tracker.addImage(quitimg, 0);
		tracker.waitForAll();
		quitimg = quitimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		ImageIcon quiticon = new ImageIcon(quitimg);
		btnQuit.setIcon(quiticon);
		btnQuit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			public void mouseEntered(MouseEvent arg0) {
				gray4.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				gray4.setVisible(false);
			}
		});
		btnQuit.setBounds(1000, buttony + 300, 240, 70);
		contentPane.add(btnQuit);

		lblMemoryTiles = new JLabel("MEMORY TILES");
		lblMemoryTiles.setForeground(Color.CYAN);
		lblMemoryTiles.setFont(new Font("Impact", Font.PLAIN, 90));
		lblMemoryTiles.setBounds(700, 67, 562, 84);
		contentPane.add(lblMemoryTiles);

		contentPane.add(gray1);
		contentPane.add(gray2);
		contentPane.add(gray3);
		contentPane.add(gray4);

		JLabel background = new JLabel("");
		background.setBounds(-100,-10,1000,700);
		background.setIcon(backgroundicon);
		contentPane.add(background);

		animate();
	}

	public void animate()
	{
		ActionListener animateX = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//how fast the text moves in pixels/ms
				int speed = 2;
				if(lblMemoryTiles.getX() >= 160)
				{
					lblMemoryTiles.setBounds(lblMemoryTiles.getX()-speed, lblMemoryTiles.getY(), lblMemoryTiles.getWidth(), lblMemoryTiles.getHeight());
				}
				if(btnNewButton.getX() >= buttonx)
				{
					btnNewButton.setBounds(btnNewButton.getX()-speed, btnNewButton.getY(), btnNewButton.getWidth(), btnNewButton.getHeight());
					gray1.setVisible(false);
					gray1.setBounds(buttonx,buttony,270,70);
					gray1.setIcon(grayicon);
				}
				if(btnStats.getX() >= buttonx)
				{
					btnStats.setBounds(btnStats.getX()-speed, btnStats.getY(), btnStats.getWidth(), btnStats.getHeight());
					gray2.setVisible(false);
					gray2.setBounds(buttonx,buttony + 100,270,70);
					gray2.setIcon(grayicon);
				}
				if(btnHelp.getX() >= buttonx)
				{
					btnHelp.setBounds(btnHelp.getX()-speed, btnHelp.getY(), btnHelp.getWidth(), btnHelp.getHeight());
					gray3.setVisible(false);
					gray3.setBounds(buttonx,buttony + 200,270,70);
					gray3.setIcon(grayicon);
				}
				if(btnQuit.getX() >= buttonx)
				{
					btnQuit.setBounds(btnQuit.getX()-speed, btnQuit.getY(), btnQuit.getWidth(), btnQuit.getHeight());
					gray4.setVisible(false);
					gray4.setBounds(buttonx,buttony + 300,270,70);
					gray4.setIcon(grayicon);
				}
			}
		};
		Timer xTimer = new Timer(1, animateX);
		xTimer.setRepeats(true);
		xTimer.start();
	}
}
