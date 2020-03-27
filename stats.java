package memorytiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class stats extends JFrame {

    private static DecimalFormat df2 = new DecimalFormat("#.##");
	private JPanel contentPane;
	int x = 150;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stats frame = new stats();
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
	public stats() throws FileNotFoundException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(this);
		
		File file = new File("src/userdata/mostTiles.txt");
		Scanner sc = new Scanner(file);
		int mosttiles = sc.nextInt();
		
		file = new File("src/userdata/levelsplayed.txt");
		sc = new Scanner(file);
		int levelsplayed = sc.nextInt();
		
		file = new File("src/userdata/levelsfailed.txt");
		sc = new Scanner(file);
		int levelsfailed = sc.nextInt();
		
		file = new File("src/userdata/mistakes.txt");
		sc = new Scanner(file);
		int mistakes = sc.nextInt();
		
		file = new File("src/userdata/avrgtime.txt");
		sc = new Scanner(file);
		double averagetime = sc.nextDouble();
		
		file = new File("src/userdata/iq.txt");
		sc = new Scanner(file);
		int iq = sc.nextInt();
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu.main(null);
			}
		});
		btnNewButton.setBounds(588, 618, 132, 43);
		contentPane.add(btnNewButton);
		
		JLabel mostTiles = new JLabel("Most tiles uncovered: " + mosttiles);
		mostTiles.setForeground(Color.CYAN);
		mostTiles.setSize(337, 43);
		mostTiles.setLocation(x+21, 116);
		mostTiles.setFont(new Font("Comfortaa", Font.BOLD, 25));
		contentPane.add(mostTiles);
		
		JLabel lblLevelsPlayed = new JLabel("Levels played: " + levelsplayed);
		lblLevelsPlayed.setForeground(Color.CYAN);
		lblLevelsPlayed.setFont(new Font("Comfortaa", Font.BOLD, 25));
		lblLevelsPlayed.setBounds(x+21, 169, 337, 43);
		contentPane.add(lblLevelsPlayed);
		
		JLabel lblMistakesMade = new JLabel("Mistakes made: " + mistakes);
		lblMistakesMade.setForeground(Color.CYAN);
		lblMistakesMade.setFont(new Font("Comfortaa", Font.BOLD, 25));
		lblMistakesMade.setBounds(x+21, 277, 337, 43);
		contentPane.add(lblMistakesMade);
		
		String avrg = df2.format(averagetime/levelsplayed);
		if (levelsplayed == 0)
		{
			avrg = "0";
		}
		JLabel lblAverageTimePer = new JLabel("Avrg. time per level: " + avrg + "s");
		lblAverageTimePer.setForeground(Color.CYAN);
		lblAverageTimePer.setFont(new Font("Comfortaa", Font.BOLD, 25));
		lblAverageTimePer.setBounds(x+21, 331, 360, 43);
		contentPane.add(lblAverageTimePer);
		
		JLabel lblLevelsFailed = new JLabel("Levels failed:  " + levelsfailed);
		lblLevelsFailed.setForeground(Color.CYAN);
		lblLevelsFailed.setFont(new Font("Comfortaa", Font.BOLD, 25));
		lblLevelsFailed.setBounds(x+21, 223, 337, 43);
		contentPane.add(lblLevelsFailed);
		
		JLabel lblIq = new JLabel("IQ: " + iq);
		lblIq.setForeground(Color.CYAN);
		lblIq.setFont(new Font("Comfortaa", Font.BOLD, 25));
		lblIq.setBounds(x+21, 391, 337, 43);
		contentPane.add(lblIq);
		
		JLabel title = new JLabel("");
		title.setBounds(237, 11, 358, 107);
		Image titleimg = toolkit.getImage("images/stats.png");
		tracker.addImage(titleimg, 0);
		tracker.waitForAll();
		titleimg = titleimg.getScaledInstance(270, 70, Image.SCALE_SMOOTH);
		ImageIcon statsicon = new ImageIcon(titleimg);
		title.setIcon(statsicon);
		contentPane.add(title);
		
		Image grayimg = toolkit.getImage("images/gray.png"); 
		tracker.addImage(grayimg, 0);
		tracker.waitForAll();
		grayimg = grayimg.getScaledInstance(540, 140, Image.SCALE_SMOOTH);
		ImageIcon grayicon = new ImageIcon(grayimg);
		
		int graywidth = 400;
		
		JLabel gray1 = new JLabel();
		gray1.setBounds(mostTiles.getX()-5,mostTiles.getY(),graywidth,40);
		gray1.setIcon(grayicon);
		contentPane.add(gray1);	

		JLabel label = new JLabel();
		label.setBounds(x+16, 172, graywidth, 40);
		label.setIcon(grayicon);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(x+16, 223, graywidth, 40);
		label_1.setIcon(grayicon);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setBounds(x+16, 280, graywidth, 40);
		label_2.setIcon(grayicon);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel();
		label_3.setBounds(x+16, 334, graywidth, 40);
		label_3.setIcon(grayicon);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setBounds(x+16, 390, graywidth, 40);
		label_4.setIcon(grayicon);
		contentPane.add(label_4);

		Image backgroundimg = toolkit.getImage("images/background.png"); 
		ImageIcon backgroundicon = new ImageIcon(backgroundimg);
		
		JLabel background = new JLabel("");
		background.setBounds(-100,-10,1000,700);
		background.setIcon(backgroundicon);
		contentPane.add(background);
	}
}
