// ICS3U1 CPT: <Higwarts Adventures>
// Phil Terence, Aayushma Sapkota
// Mr. Conway
// This is a Hogwarts themed RPG game where you basically go through a bunch of mini advenures that prepare you for the final battle. 
// First you will be asked to pick your character, then you will face your first challenge: getting past the moving hats to get sorted nto your house
// Then you will learn some spells, followed by a mini challneges to consolidate your skills
// Then you will recive an alert that warns you of the main villain of the game. 
// You go and get clues about the whereabouts of the villian where you have to play a mini game to get all the info
// Finally, you battle the villian

// Input/Output: used to ask the user to enter the code: 899
// If statements: Used if statement to see if all three spells hit the target to keep track of points: 794
// Random numbers: Used random numbers to select hogwarts house: 87
// Used for loop for dice roll game: 282
// used while loop for rejexc checking: 97 and 900
// used try catch statements for fonts: 220
// used JOption Pane to give instuctuions for diceroll game: 282
// used self made method to run the dice roll game: 282
// used colision detection to see if the enemies spells colided with the player: 584
// had 5 addditional classes: one to move avatar, one to choose avatar, one to regulate spells, one to move the hats, and the other to sotre properties of the villian 
// Used an array to store houses and randomly generate surnames: 61 and 62
// Used String methods chnaged house names to uppercase 977

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class main_game extends JPanel implements ActionListener, KeyListener, MouseListener  {
	
	// fields (global variables)
	private JFrame frame = new JFrame();
	private ImageIcon background, introscreen, privetdrive, envelope, letter, arrows, pickcharacter, boy, girl, controlback, infoback, mainHall, player, delores, siren;
	private Timer timerintroscreen, storytimer, lettertimer, pickcharactertimer, controltimer, infotimer, game1timer, avatarTimer, hatTimer, game2timer;
	private Font f, f2, f3, f4;
	private Color darkB, brown;
	private Rectangle2D playButt, controlButt, infoButt, continuee, ins, homescreen, message, maskAvatar, maskHat1, maskHat2, maskHat3, maskHat4, maskSnape;
	private int framewidth, frameheight, enx, eny, count, choice, count2, dir1, dir2, dir3, option1, house, spell, spell1x;
	private boolean drawletter, drawletter2, drawcontinue, up, right, left, down;
	private String charName, house2, code, codePattern, surname, realname;
	private avatarChoice avatar;
	private avatarMove move;
	private hat hat;
	private spells spells;
	private ImageIcon sortingHat, classroomimg, snape, target, library, draco, forest;
	private String[] houses = {"Gryffindor", "Slytherin", "Ravenclaw", "Hufflepuff"};
	private String[] surnmaes = {"The magical ", "The genius ", "The extordinary ", "The ordinary "};
	private Random rand, rand2;
	private int spellx2, spellx3, attempt1, attempt2, attempt3, targety, targetx, targetcounter, point1, point2, point3, dracocounter, totalpoints;
	private boolean drawsnape, targetappear, game;
	private Rectangle2D maskTarget, maskspell1, maskspell2, maskspell3, dracomask, spellum, spellum2, Maskum;
	private Timer game3timer, battletimer;
	private int mysum, dracosum, myroll, dracoroll, dirumbridge, spelldolx, spelldolx2, spelldolxcount, mylives, umlives;
	private umbridge umbridge;

	
	// constructor to initialize fields 
	public main_game()
	{
		// set properties of JPanel
		setLayout(null);
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
		
		// create random class 
		rand2 = new Random();
		rand = new Random();
		
		// initialize random number for house
		house = rand.nextInt(4);
		house2 = houses[house];
		spell = 0;
		spells = new spells();
		
		// initialize string variables 
		code = "";
		charName = "";

		// initialize regex pattern and variable to store user input of said pattern
		codePattern = "([a-zA-Z]\\d){2}\\-23";
		
		//randomly select surname
		surname = surnmaes[house];
		
	
		
		// initialize images
		introscreen = new ImageIcon("src/introscreen.jpg");
		privetdrive = new ImageIcon("src/privetDrive.jpg");
		envelope = new ImageIcon("src/envelope_sealed.png");
		letter = new ImageIcon("src/letter.png");
		arrows = new ImageIcon("src/arrows.png");
		pickcharacter = new ImageIcon("src/pickcharacter.jpg");
		boy = new ImageIcon("src/plainGuyBig.png");
		girl = new ImageIcon("src/plainGirlBig.png");
		controlback = new ImageIcon("src/controls.jpg");
		mainHall = new ImageIcon("src/hogwartsMainHall.png");
		sortingHat = new ImageIcon("src/sortingHat.png");
		classroomimg = new ImageIcon("src/classroomimg.jpg");
		snape = new ImageIcon("src/snape.png");
		target = new ImageIcon("src/targetpng.png");
		library = new ImageIcon("src/library.png");
		delores = new ImageIcon("src/DoloresNPC.png");
		draco = new ImageIcon("src/dracoNPC.png");
		forest = new ImageIcon("src/forest.jpg");
		delores = new ImageIcon("src/DoloresNPC.png");
		siren = new ImageIcon("src/altert.png");
		infoback = new ImageIcon("src/control.jpg");
		background = introscreen;
		
		// set counters, direction, and coordinates
		dracocounter = 0;
		enx= 430;
		eny = 20;
		count = 0;
		choice = 3;
		count2 = 0;
		dir1 = 1;
		dir2 = 1;
		dir3 = 1;
		targety = 50;

		point1 = 0;
		point2 = 0;
		point3 = 0;
		totalpoints = point1 + point2 + point3;

		dirumbridge = 1;
		spelldolx = -1500;
		spelldolx2 = -1500;
		mylives = 6;
		umlives = 12;
		
		// create objects for classes 
		avatar = new avatarChoice();
		move = new avatarMove(background.getIconHeight(), background.getIconWidth());
		player = avatar.getImage(choice);
		hat = new hat();
		umbridge = new umbridge();
		
		// initialize frame width and height
		framewidth = background.getIconWidth();
		frameheight = background.getIconHeight();
		
		// set properties of frame 
		frame.setSize(framewidth, frameheight);
		frame.setContentPane(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		// initialize timers 
		storytimer = new Timer(50, this);
		lettertimer = new Timer(50, this);
		timerintroscreen = new Timer(50, this);
		pickcharactertimer = new Timer(50, this);
		controltimer= new Timer(50, this);
		infotimer = new Timer(50, this);
		game1timer = new Timer(50, this);
		avatarTimer = new Timer(50, this);
		hatTimer = new Timer(50, this);
		game2timer = new Timer(50, this);
		game3timer = new Timer(50, this);
		battletimer = new Timer(50, this);
		timerintroscreen.start();
		
		// initialize rectangle masks and buttons 
		playButt = new Rectangle2D.Double(900, 300, 300, 75);
		controlButt = new Rectangle2D.Double(900, 400, 300, 75);
		infoButt = new Rectangle2D.Double(900, 500, 300, 75);
		continuee = new Rectangle2D.Double(215, 355, 220, 60);
		ins = new Rectangle2D.Double(165, 380, 300, 100);
		homescreen = new Rectangle2D.Double(220, 490, 100, 50);
		message = new Rectangle2D.Double(10, 10, 1360, 200);
		maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
		maskSnape = new Rectangle2D.Double(300, 180, snape.getIconWidth(), snape.getIconHeight());
		
		maskHat1 = new Rectangle2D.Double(hat.getX1(), hat.getY1(), hat.getHat1().getIconWidth(), hat.getHat1().getIconHeight());
		maskHat2 = new Rectangle2D.Double(hat.getX2(), hat.getY2(), hat.getHat2().getIconWidth(), hat.getHat2().getIconHeight());
		maskHat3 = new Rectangle2D.Double(hat.getX3(), hat.getY3(), hat.getHat3().getIconWidth(), hat.getHat3().getIconHeight());
		maskHat4 = new Rectangle2D.Double(350, 150, sortingHat.getIconWidth(), sortingHat.getIconHeight());
		
		maskTarget = new Rectangle2D.Double(700, move.getY(), target.getIconWidth(), target.getIconHeight());
		maskspell1 = new Rectangle2D.Double(spell1x, move.getY(), spells.getimg1().getIconWidth(), spells.getimg1().getIconHeight());
		maskspell2 = new Rectangle2D.Double(spellx2, move.getY(), spells.getimg2().getIconWidth(), spells.getimg2().getIconHeight());
		maskspell3 = new Rectangle2D.Double(spellx3, move.getY(), spells.getimg3().getIconWidth(), spells.getimg3().getIconHeight());

		dracomask = new Rectangle2D.Double(200, 600, draco.getIconWidth(), draco.getIconHeight());
		
		// initialize colors 
		darkB = new Color(48, 45, 35);
		brown = new Color(189, 165, 137);
		
		// initialize x coordinates of spells
		spell1x = 1500;
		spellx2 = 1500;
		spellx3 = 1500;
		
		targetx = -1000;
		
		// import fonts 
		try
		{
			f = Font.createFont(Font.TRUETYPE_FONT, new File ("src/RealtimeGamerPersonalUse-ZVL38.ttf")).deriveFont(60f);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "There is an Error with the font!", "ERROR",
			JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			f2 = Font.createFont(Font.TRUETYPE_FONT, new File ("src/Xefora-vp34.ttf")).deriveFont(40f);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "There is an Error with the font!", "ERROR",
			JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			f3 = Font.createFont(Font.TRUETYPE_FONT, new File ("src/RealtimeGamerPersonalUse-ZVL38.ttf")).deriveFont(25f);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "There is an Error with the font!", "ERROR",
			JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			f4 = Font.createFont(Font.TRUETYPE_FONT, new File ("src/RealtimeGamerPersonalUse-ZVL38.ttf")).deriveFont(15f);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "There is an Error with the font!", "ERROR",
			JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static void main(String[] args) {
		
		// calls constructor from this class 
		new main_game();

	}
	

	
	// moves target
	public void Targetmove()
	{
		if(targetcounter % 20 == 0)
		{
			targety = rand2.nextInt(400);
			maskTarget = new Rectangle2D.Double(targetx, targety, target.getIconWidth(), target.getIconHeight());
		}
	}
	
	// method for dicegame
	public void dicegame()
	{
		// for loop to increment through rounds 
		for(int i = 1; i<13; i++)
		{
			if(i == 10 && mysum > dracosum)
			{
				JOptionPane.showMessageDialog(null, "Game over you win!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "You only got lucky but a promise is a promise.\n Delores is in the ofrbidden forest right now" + dracosum, "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				game = false;
				game3timer.stop();
				battletimer.start();
				move.setX(50);
				move.setY(300);
				dracomask = new Rectangle2D.Double(-100, -100, 10, 10);
				break;
			}
			if(i == 10 && mysum < dracosum)
			{
				JOptionPane.showMessageDialog(null, "Game over you loose!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Hahah you lost! I bet I can beet you again!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				mysum = 0;
				dracosum = 0;
				i = 1;
			}
			if(i == 10 && mysum == dracosum)
			{
				JOptionPane.showMessageDialog(null, "Ughh it's a tie!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "I went easy on you. Let's go again.", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
				mysum = 0;
				dracosum = 0;
				i = 1;
			}
	
			if(i != 11)
			{
				myroll = rand.nextInt(6) + 1;
				mysum += myroll;
				JOptionPane.showMessageDialog(null, "You rolled a " + myroll + "\n Your sum is " + mysum, "ROUND " + i, JOptionPane.INFORMATION_MESSAGE);
				dracoroll = rand.nextInt(6) + 1;
				dracosum += dracoroll;
				JOptionPane.showMessageDialog(null, " Draco rolled a " + dracoroll + "\n Draco's sum is " + dracosum, "ROUND " + i, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			// sets boolean variable to true to move avatar 
			left = true;
			// updates mask
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
			// sets boundaries for avatar movement and sets boolean to false if avatar is out of bounds 
			if (background == mainHall)
			{
				if (move.getX() <= 60)
				{
					left = false;
				}
			}
			else if (background == classroomimg)
			{
				if (move.getX() <= 10)
				{
					left = false;
				}
			}
			else if (background == library)
			{
				if (move.getX() <= 10)
				{
					left = false;
				}
			}
			else if (background == forest)
			{
				if (move.getX() <= 10)
				{
					left = false;
				}
			}


		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			// set boolean variable to true to move avatar
			right = true;
			// updates mask
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
			// sets boundaries for avatar movement and sets boolean to false when avatar is out of bounds
			if (background == mainHall)
			{
				if (move.getX() >= 550)
				{
					right = false;
				}
			}
			else if (background == classroomimg)
			{
				if (move.getX() >= classroomimg.getIconWidth()/3)
				{
					right = false;
				}
			}
			else if (background == library)
			{
				if (move.getX() >= library.getIconWidth() - player.getIconWidth()*2)
				{
					right = false;
				}
			}
			else if(background == forest)
			{
				if (move.getY() >= 400)
				{
					right = false;
				}
			
			}
			

		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			// sets boolean variable to true to move avatar
			up = true;
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
			if (background == mainHall)
			{
				if (move.getY() <= 10)
				{
					up = false;
				}
			}
			else if (background == classroomimg)
			{
				if (move.getY() <= 10)
				{
					up = false;
				}
			}
			else if (background == library)
			{
				if (move.getY() <= 10)
				{
					up = false;
				}
			}
			else if (background == forest)
			{
				if (move.getY() <= 10)
				{
					up = false;
				}
			}
			

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			// sets boolean variable to true to move avatar
			down = true;
			// updates position of mask
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
			// sets boundaries for avatar movement and sets boolean to false if avatar is out of bounds 
			if (background == mainHall)
			{
				if (move.getY() >= mainHall.getIconHeight() - player.getIconHeight() -50)
				{
					down = false;
				}
			}	
			else if (background == classroomimg)
			{
				if (move.getY() >= classroomimg.getIconHeight() - player.getIconHeight())
				{
					down = false;
				}
			}
			else if (background == library)
			{
				if (move.getY() >= library.getIconHeight() - player.getIconHeight() -10)
				{
					down = false;
				}
			}
			else if(background == forest)
			{
				if (move.getY() >= 400)
				{
					down = false;
				}
			
			}

		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			// calls move method to have spell move to avatar position
			spell1x = move.getX();
			// increment the position of spell
			attempt1 += 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_1)
		{
			// calls move method to have spell move to avatar position
			spellx2 = move.getX();
			// increment the position of spell
			attempt2 += 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_2)
		{
			// calls move method to have spell move to avatar position 
			spellx3 = move.getX();
			// increments the position of spell
			attempt3 += 1;
		}
		// repaint 
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			// set boolean variable to false to stop avatar movement 
			left = false;
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			// set boolean variable to false to stop avatar movement 
			right = false;
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			// set boolean variable to false to stop avatar movement 
			up = false;
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			// set boolean variable to false to stop avatar movement 
			down = false;
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

		}
		repaint();
		
		
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == battletimer)
		{

			// setting background

			background = forest;
			frame.setSize(background.getIconWidth(), background.getIconHeight());

			
			// setting condition so JOtion pane only appears once
			if(spelldolxcount == 1)
			JOptionPane.showMessageDialog(this, "You found umbridge!! \n Hit her with your newlt learnt spells to rid her forever. \n HINT: Some spells are more efficent than others.", "LOSER", JOptionPane.INFORMATION_MESSAGE);

			//spell couter and moving the spells x position
			spelldolxcount += 1;
			spelldolx -= 30;
			spelldolx2 -= 60;
			
			// moving umbridge up and down
			if(umbridge.getY() <= 0 || umbridge.getY() >= frame.getHeight() -50)
			{
				dirumbridge = dirumbridge * -1;
			}
			
			// in these intrevals the spells x Pos will be hte same xPos as umbridge 
			if(spelldolxcount % 20 == 0)
			{
				spelldolx = 600;
			}
			
			if(spelldolxcount % 13 == 0)
			{
				spelldolx2 = 600;
			}
			
			
			umbridge.move(dirumbridge);
		// updating all the masls 
			Maskum = new Rectangle2D.Double(600, umbridge.getY(), delores.getIconWidth(), delores.getIconHeight());
			maskspell1 = new Rectangle2D.Double(spell1x, move.getY(), spells.getimg1().getIconWidth(), spells.getimg1().getIconHeight());
			maskspell2 = new Rectangle2D.Double(spellx2, move.getY(), spells.getimg2().getIconWidth(), spells.getimg2().getIconHeight());
			maskspell3 = new Rectangle2D.Double(spellx3, move.getY(), spells.getimg3().getIconWidth(), spells.getimg3().getIconHeight());
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
			spellum = new Rectangle2D.Double(spelldolx, umbridge.getY(), spells.getimg4().getIconWidth(), spells.getimg4().getIconHeight());
			spellum2 = new Rectangle2D.Double(spelldolx2, umbridge.getY(), spells.getimg5().getIconWidth(), spells.getimg5().getIconHeight());

			//moving the spells
			spell1x += 30;
			spellx2 += 30;
			spellx3 += 30;
			

			if(spellum.intersects(maskAvatar) || spellum2.intersects(maskAvatar))
			{
				mylives -= 1;
				spelldolx= -1500;
				spelldolx2 = -1500;

			}
			
			if(maskspell1.intersects(Maskum))
			{
				umlives -= 2;
				spell1x = +10000;

			}
			if(maskspell2.intersects(Maskum))
			{
				umlives -= 1;
				spellx2 = +10000;

			}
			if(maskspell3.intersects(Maskum))
			{
				umlives -= 3;

				spellx3 = +10000;

			}
			
			// if my lives equal 0, I loose the game, resetting all varibles to 0, and going back to mian screen
			if(mylives == 0)
			{
				up = false;
				right = false;
				left = false;
				down = false;
				JOptionPane.showMessageDialog(this, "You loose! Hogwarts will forever be ruined thanks to you", "LOSER", JOptionPane.INFORMATION_MESSAGE);
				background = introscreen;
				frame.setSize(background.getIconWidth(), background.getIconHeight());
				choice = 3;
				count = 0;
				count2 = 0;
				move.setX(350);
				move.setY(650);
				mylives = 6;
				umlives = 12;
				maskHat4 = new Rectangle2D.Double(350, 150, sortingHat.getIconWidth(), sortingHat.getIconHeight());
				maskSnape = new Rectangle2D.Double(300, 180, snape.getIconWidth(), snape.getIconHeight());
				dracocounter = 0;
				dracomask = new Rectangle2D.Double(200, 600, draco.getIconWidth(), draco.getIconHeight());
				point1 = 0;
				point2 = 0;
				point3 = 0;
				attempt1 = 0;
				attempt2 = 0;
				attempt3 = 0;
				mysum = 0;
				dracosum = 0;
				spell = 0;
				targetcounter = 0;
				eny = 20;
				battletimer.stop();
				

			}
			// if umbridges lives equal 0, I win the game, resetting all varibles to 0, and going back to mian screen

			if(umlives == 0)
			{
				JOptionPane.showMessageDialog(this, "YOU SAVED HOGWARTS", "WINNER", JOptionPane.INFORMATION_MESSAGE);
				timerintroscreen.start();
				background = introscreen;
				choice = 3;
				spell = 0;
				targetcounter = 0;
				count2 = 0;
				maskHat4 = new Rectangle2D.Double(350, 150, sortingHat.getIconWidth(), sortingHat.getIconHeight());
				maskSnape = new Rectangle2D.Double(300, 180, snape.getIconWidth(), snape.getIconHeight());
				dracomask = new Rectangle2D.Double(200, 600, draco.getIconWidth(), draco.getIconHeight());
				dracocounter = 0;
				mylives = 6;
				umlives = 12;
				point1 = 0;
				point2 = 0;
				point3 = 0;
				move.setX(350);
				move.setY(650);
				attempt1 = 0;
				attempt2 = 0;
				attempt3 = 0;
				mysum = 0;
				dracosum = 0;
				frame.setSize(background.getIconWidth(), background.getIconHeight());
				battletimer.stop();
				eny = 20;
				count = 0;
			}

		}
		
		if(e.getSource() == game3timer)
		{

			// when source is game3timer: change background image to forest image, change frame size to new image width and height, increase counter,
			// and update mask
			dracocounter += 1;
			background = library;
			frame.setSize(background.getIconWidth(), background.getIconHeight());
			maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

			// when timer runs for 5 seconds, message shows user what they are supposed to do
			if(dracocounter == 10)
			{
				JOptionPane.showMessageDialog(null, "Bump into draco. He probably has info on deloros.", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
			}
			// if avatar intersects draco dialog begins and dicegame method is called 
			if(maskAvatar.intersects(dracomask))
			{
				up = false;
				down = false;
				left = false;
				right = false;
				move.setX(190);
				move.setY(450);
				JOptionPane.showMessageDialog(null, "Ughh you must be " + charName + ". \nIf you want info on Delores you're going to have to play a little game of chace with me", "WELCOME", JOptionPane.PLAIN_MESSAGE, draco);
				JOptionPane.showMessageDialog(null, "There will be 10 rounds of rice rolled.\n Which ever one of you has the higgest sum of dice rolled by the end will win!", "WELCOME", JOptionPane.PLAIN_MESSAGE, draco);
				game = true;
			}
			
			if(game == true)
			{
				
				dicegame();
			}
		}
		
		if(e.getSource() == game2timer)
		{
			// if source is game2timer: change background to classroom and increase counters 
			up = false;
			right = false;
			left = false;
			down = false;
			spell += 1;
			targetcounter += 1;
			background = classroomimg;
			frame.setSize(background.getIconWidth(), background.getIconHeight());
			
			// when y pos is less then 50 snape is drawn (transition from game 1 to game 2)
			if(move.getY() < -50)
			{
				drawsnape = true;
				move.setY(500);
			}
			// move method called to move avatar
			if(move.getY() > 450)
			{
				up = true;
				move.move(up, left, right, down);
			} 
			// is y pos of avatar is greater than or equal to 450, boolean set to false
			if(move.getY() <= 450)
			{
				up = false;
			}
			if(spell == 10)
			JOptionPane.showMessageDialog(null, "Now it's time to learn your first spells! Bump into Snape to start :)", "WELCOME", JOptionPane.INFORMATION_MESSAGE);

			// if avatar insects snape all movement boolean variables are set to false and avatar returns to original position
			if(maskAvatar.intersects(maskSnape))
			{
				left = false;
				up = false;
				right = false;
				left = false;
				move.setX(20);
				move.setY(400);
				maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());
				JOptionPane.showMessageDialog(null, "Welcome to Defense against the dark arts. In this class I expect no more then EXCELLENCE!! You will be taught 3 spells today. LETS GO!", "SPELLS", JOptionPane.INFORMATION_MESSAGE, snape);

				JOptionPane.showMessageDialog(null, "Hit the space bar to do the first spell", "Snape", JOptionPane.PLAIN_MESSAGE);
			
			}
			// if spell is launched print dialog JOptionPane
			if(attempt1 == 1 && spell1x > 1500)
			{
			JOptionPane.showMessageDialog(null, "I haven't seen a more pathetic attempt in my life but whatever. On to the next spell. Press the number 1 key to do the second spell", "SPELLS", JOptionPane.PLAIN_MESSAGE, snape);
			attempt1 = 2;
			}
			// if spell is launched print dialog JOptionPane
			if(attempt2 == 1 && spellx2 > 1500 )
			{
			JOptionPane.showMessageDialog(null, "That wasn't any better. Hit the number 2 key to learn the final spell", "SPELLS", JOptionPane.PLAIN_MESSAGE, snape);
			attempt2 = 2;
			}
			// if spell is launched print dialog JOptionPane 
			if(attempt3 == 1 && spellx3 > 1500)
			{
			JOptionPane.showMessageDialog(null, "Sadly, this is the bext you can get now time for a challenge\n To test your knowledge, hit the the target at least once with each spell", "SPELLS", JOptionPane.PLAIN_MESSAGE, snape);
			attempt3 = 2;
			// stop drawing snap
			drawsnape = false;
			// mask is set to 0 for x,y,width, and height
			maskSnape = new Rectangle2D.Double(0, 0, 0, 0);
			// target appears 
			targetappear = true;
			targetx = 700;

			}
			spell1x += 30;
			spellx2 += 30;
			spellx3 += 30;
			maskspell1 = new Rectangle2D.Double(spell1x, move.getY(), spells.getimg1().getIconWidth(), spells.getimg1().getIconHeight());
			maskspell2 = new Rectangle2D.Double(spellx2, move.getY(), spells.getimg2().getIconWidth(), spells.getimg2().getIconHeight());
			maskspell3 = new Rectangle2D.Double(spellx3, move.getY(), spells.getimg3().getIconWidth(), spells.getimg3().getIconHeight());
			
			// if any point counters equal 0..
			if(point1 == 0 || point2 == 0 || point3 == 0 )
			{
				// move target. if spell intersects target, increase point counter 
				Targetmove();
				if(maskspell1.intersects(maskTarget))
				{
					point1 = 1;
				}				
				// move target. if spell intersects target, increase point counter 
				if(maskspell2.intersects(maskTarget))
				{
					point2 = 1;
				}
				// move target. if spell intersects target, increase point counter 
				if(maskspell3.intersects(maskTarget))
				{
					point3 = 1;
				}
			}
			
			// if all spells intersect the target at least once stop drawing target
			if(point1 + point2 + point3 == 3)
			{
				targetappear = false;
				// dialog prompted and stop timers when game is over 
				if(spell1x > 1500 && spellx2 >1500 && spellx3 > 1500)
				{
					JOptionPane.showMessageDialog(null,"You passed. Now I can leave. Bye", "CONGRATS", JOptionPane.PLAIN_MESSAGE, snape);
					JOptionPane.showMessageDialog(null, "ALERT ALERT! Deloris Umbridge is back!! \n You must find away to defeat her or else the whole school will be in trouble", "ALERT", JOptionPane.PLAIN_MESSAGE, siren);
					up = false;
					right = false;
					left = false;
					down = false;
					game2timer.stop();
					move.setX(140);
					move.setY(200);
					game3timer.start();
				}
			}
		}
		if (e.getSource() == hatTimer)
		{
			// call method to move hats
			hat.move(dir1, dir2, dir3);
			// update mask positions 
			maskHat1 = new Rectangle2D.Double(hat.getX1(), hat.getY1(), hat.getHat1().getIconWidth(), hat.getHat1().getIconHeight());
			maskHat2 = new Rectangle2D.Double(hat.getX2(), hat.getY2(), hat.getHat2().getIconWidth(), hat.getHat2().getIconHeight());
			maskHat3 = new Rectangle2D.Double(hat.getX3(), hat.getY3(), hat.getHat3().getIconWidth(), hat.getHat3().getIconHeight());
			
			// if mask avatar intersects mask hat return hat and avatar to original position 
			if (maskHat1.intersects(maskAvatar))
			{
				hat.setX1(525);
				hat.setY1(580);
				
				move.setX(350);
				move.setY(650);
				maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

			}
			// set boundary for hat
			if(hat.getX1()<=40 || hat.getX1()>= 530)
			{
				dir1 = dir1 * -1;
			}
			// if mask avatar intersects mask hat return hat and avatar to original position 
			if (maskHat2.intersects(maskAvatar))
			{
				hat.setX2(150);
				hat.setY2(480);
				
				move.setX(350);
				move.setY(650);
				maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

			}
			// set boundary for hat
			if(hat.getX2()<=40 || hat.getX2()>= 530)
			{
				dir2 = dir2 * -1;
			}
			// if mask avatar intersects mask hat return hat and avatar to original position 
			if (maskHat3.intersects(maskAvatar))
			{
				hat.setX3(525);
				hat.setY3(380);
				
				move.setX(350);
				move.setY(650);
				maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

			}
			// set boundary for hat 
			if(hat.getX3()<=40 || hat.getX3()>= 530)
			{
				dir3 = dir3 * -1;
			}
		}
		if(e.getSource() == game1timer)
		{
			// if source equals game1timer set background image to mainHall image 
			background = mainHall;
			// change frame size to fit new background height and width
			frame.setSize(background.getIconWidth(), background.getIconHeight());
			// stop drawing images from previous background 
			drawcontinue = false;
			
			// when counter is less than 2 ask user for code matching the one given after the letter 
			if(count2 < 2)
			{
				while (true)
				{
					code = JOptionPane.showInputDialog(null, "Please enter the code matching the pattern given to you in your letter:", "ENTER CODE", JOptionPane.QUESTION_MESSAGE);
					if (code.matches(codePattern))
					{
						break;
					}
					// output error message if code is incorrect 
					else if (!code.matches(codePattern))
					{
						JOptionPane.showMessageDialog(null, "Incorrect. Try again.", "INCORRECT", JOptionPane.ERROR_MESSAGE);
						continue;
					}
				}
				// ask user for name and output message to show the objective 
				while(true)
				{
					charName = JOptionPane.showInputDialog(null, "Enter your username:", "NAME", JOptionPane.QUESTION_MESSAGE);
					if(charName == null)
					{
						charName = JOptionPane.showInputDialog(null, "Enter your username:", "NAME", JOptionPane.QUESTION_MESSAGE);
	
					}
					
					else
						break;
					}
				JOptionPane.showMessageDialog(null, "Welcome to Hogwarts " + charName + " " + surname + "! dodge the wizard hats to make it to the sorting hat. Good Luck!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);;
				hatTimer.start();
			}
			count2 = 5;
		}
		if (e.getSource() == avatarTimer)
		{
			// if source equals avatarTimer call move method 
			move.move(up, left, right, down);
			
			// if mask of sorting hat intersects avatar stop hatTimer and sort player into randomly generated house 
			if (maskHat4.intersects(maskAvatar))
			{

				hatTimer.stop();
				
				option1 = JOptionPane.showOptionDialog(null, "hmm...time to get sorted "  + charName + " " + surname + "\n are you ready?", "SORTING", JOptionPane.YES_NO_OPTION, 0, sortingHat, null, null);
				if(option1 == 1)
				{
					JOptionPane.showMessageDialog(null, "Well too bad... you're going to set sorted anyways. " + house2.toUpperCase(), "HOUSE", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					// outputs the player house 
					JOptionPane.showMessageDialog(null, house2.toUpperCase(), "HOUSE", JOptionPane.INFORMATION_MESSAGE);
				}
				// set x and y for move method for transition from this background to the next 
				move.setX(50);
				move.setY(50);
				// set move boolean to false to stop movement 
				left = false;
				right = false;
				down = false;
				up = true;
				// update mask position 
				maskAvatar = new Rectangle2D.Double(move.getX(), move.getY(), player.getIconWidth(), player.getIconHeight());

			}
			if(move.getY() < -100)
			{
				// stop game timers 
				game1timer.stop();
				maskHat4 = new Rectangle2D.Double(0, 0, 0, 0);
				game2timer.start();
			}
			

		}
		if(e.getSource() == infotimer)
		{
			// if source equals infrotimer set background to infoback image, set size of frame, set boolean to true 
			background = infoback;
			frame.setSize(1400, 300);

		}
		
		if(e.getSource() == controltimer)
		{
			// if source equals controltimer set background to controlback image, set size of frame  
			background = controlback;
			frame.setSize(600, 600);
			
		}
		// if soruce equals pickcharactertimer change background to pickcharacter image and set boolean to draw plain avatars 
		if(e.getSource() == pickcharactertimer)
		{
			background = pickcharacter;
			frame.setSize(background.getIconWidth(), background.getIconHeight());

			
		}
		if(e.getSource() == storytimer)
		{
				
				frame.setSize(background.getIconWidth(), background.getIconHeight());
				if(count == 120)
				{
					// output regex statement pattern to user 
					JOptionPane.showMessageDialog(null, "You hogwarts code pattern is X#X#-23. It will be REQUIRED during entrance at hogwarts.\nX = upper or lowercase letter\n# = number", "HOGWARTS CODE PATTERN", JOptionPane.INFORMATION_MESSAGE);
					// stop drawing letters 
					drawletter= false;
					drawletter2 = false;
					// stop timers 
					lettertimer.stop();
					storytimer.stop();
					// start timer for new background 
					pickcharactertimer.start();
				}
				else
				{
					count += 1;
					lettertimer.start();
					drawletter = true;
				}
			
		}
		// if source equals lettertimer background image is changed to privetdrive image 
		if(e.getSource() == lettertimer)
		{
			
			background = privetdrive;
			if(eny<475)
			{
				// move dropping letter 
				eny += 10;
			}
			else
			{
				// stop move letter if drawletter boolean is true 
				drawletter2 = true;
				eny += 0;
			}
			
		}
		repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background.getImage(), 0, 0, this);
		
		if (background == introscreen)
		{
			// set color for font and set font. draw game title 
			g2.setColor(darkB);
			g2.setFont(f);
			g2.drawString("Hogwarts Adventure", 100, 150);
			
			// set button color 
			g2.setColor(darkB);
			// set border for button
			g2.setStroke(new BasicStroke(4));
			// draw rectangle and fill
			g2.draw(playButt);
			g2.setColor(brown);
			g2.fill(playButt);
			g2.setColor(darkB);
			g2.setFont(f2);
			// draw string for button 
			g2.drawString("Play", 920, 350);
			
			// set color for font and set font
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			// draw reactangle, fill, and draw string over reactangle 
			g2.draw(controlButt);
			g2.setColor(brown);
			g2.fill(controlButt);
			g2.setColor(darkB);
			g2.setFont(f2);
			g2.drawString("Controls", 920, 450);
			
			// set color for font and set font 
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			// draw rectangle, fill, and draw sting over rectangle 
			g2.draw(infoButt);
			g2.setColor(brown);
			g2.fill(infoButt);
			g2.setColor(darkB);
			g2.setFont(f2);
			g2.drawString("Info", 920, 550);
			
		}
		
		if(drawletter == true)
		{
			// if boolean is true draw letter 
			g2.drawImage(envelope.getImage(), enx, eny, this);
		}
		if(drawletter2 == true)
		{
			// if boolean is set to true draw letter 
			g2.drawImage(letter.getImage(), 200, 20, this);
		}

		if(background == pickcharacter)
		{
			// draw plain girl and guy for player selection. draw string to indicate prompt 
			g2.drawImage(girl.getImage(), 0, 0, this);
			g2.drawImage(boy.getImage(), 435, 0, this);
			g2.setColor(darkB);
			g2.setFont(f3);
			g2.drawString("Pick Your Character", 190, 120);
		}
		
		if(drawcontinue == true)
		{
			// if boolean is set to true draw button and string 
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			g2.draw(continuee);
			g2.setColor(brown);
			g2.fill(continuee);
			g2.setColor(darkB);
			g2.setFont(f2);
			g2.drawString("Continue", 250, 400);
		}
		
		if(background == controlback)
		{
			// if background image is controlback image, draw string indicating controls 
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			g2.draw(ins);
			g2.setColor(brown);
			g2.fill(ins);
			g2.setColor(darkB);
			g2.setFont(f4);
			g2.drawString("UP arrow moves you forward", 180, 400);
			g2.drawString("DOWN arrow moves you down", 180, 420);
			g2.drawString("LEFT arrow moves you to the left", 180, 440);
			g2.drawString("RIGHT arrow moves you to the right", 180, 460);
			
			g2.drawImage(arrows.getImage() , 100, 100, this);

			// draw back button
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			g2.draw(homescreen);
			g2.setColor(brown);
			g2.fill(homescreen);
			g2.setColor(darkB);
			g2.setFont(f2);
			g2.drawString("Back", 230, 530);	
		
		}
		
		if(background == infoback)
		{
			// if boolean is set to true draw game instructions and back button 
			Rectangle2D backbutt = new Rectangle2D.Double(650, 300, 80, 30);
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			g2.draw(message);
			g2.setColor(brown);
			g2.fill(message);
			g2.setColor(darkB);
			g2.setFont(f2);
			
			g2.setColor(darkB);
			g2.setStroke(new BasicStroke(4));
			g2.draw(backbutt);
			g2.setColor(brown);
			g2.fill(backbutt);
			g2.setColor(darkB);
			g2.setFont(f2);
			g2.drawString("Back", 600, 250);
			g2.drawString("Welcome to Hogwarts and Congradulations for being ond of the lucky ones!", 10, 50);
			g2.drawString("At hogwarts we demand excellence so you will be challenged and your boundries", 10, 100);
			g2.drawString("will be tested. The school is in jepordy and it's on you to save everyone.", 10, 150);
			g2.drawString("You will be given 4 challenges, each required completion inroder to save the school", 10, 200);
		}
		
		if(background == mainHall)
		{
			// draw avatar, hats and thier respective masks 
			g2.drawImage(avatar.getImage(choice).getImage(), move.getX(), move.getY(), this);
			g2.draw(maskAvatar);
			g2.drawImage(hat.getHat1().getImage(), hat.getX1(), hat.getY1(), this);
			g2.draw(maskHat1);
			g2.drawImage(hat.getHat2().getImage(), hat.getX2(), hat.getY2(), this);
			g2.draw(maskHat2);
			g2.drawImage(hat.getHat3().getImage(), hat.getX3(), hat.getY3(), this);
			g2.draw(maskHat3);
			g2.drawImage(sortingHat.getImage(), 350, 150, this);
			g2.draw(maskHat4);
		}
		if(background == classroomimg)	
		{
			// draw avatar class uniform image and draw mask 
			g2.drawImage(avatar.getRealImage(house).getImage(), move.getX(), move.getY(), this);
			g2.draw(maskAvatar);
			
			// draw snape if boolean is true 
			if(drawsnape == true)
			{
			g2.drawImage(snape.getImage(), 300, 180, this);
			}
			g2.drawImage(spells.getimg1().getImage(), spell1x, move.getY(), this);
			g2.drawImage(spells.getimg2().getImage(), spellx2, move.getY(), this);
			g2.drawImage(spells.getimg3().getImage(), spellx3, move.getY(), this);
			
	
			
			if(targetappear == true)
			{
				g2.setColor(darkB);
				g2.setFont(f4);
				g2.drawString("Points " + totalpoints + " / 3", 200, 20);
			}
			



		}
		
		if(targetappear == true)
		{
			// draw target image 
			g2.drawImage(target.getImage(), targetx, targety, this);
		}
		
		if(background == library)
		{
			// draw draco image and avatar image 
			g2.drawImage(draco.getImage(), 200, 600, this);
			g2.drawImage(avatar.getRealImage(house).getImage(), move.getX(), move.getY(), this);
			
		}
		
		if(background == forest)
		{
			g2.drawImage(avatar.getRealImage(house).getImage(), move.getX(), move.getY(), this);
			g2.drawImage(delores.getImage(), 600, umbridge.getY(), this);
			g2.drawImage(spells.getimg4().getImage(), spelldolx, umbridge.getY(), this);
			g2.drawImage(spells.getimg4().getImage(), spelldolx2, umbridge.getY(), this);
			g2.drawImage(spells.getimg1().getImage(), spell1x, move.getY(), this);
			g2.drawImage(spells.getimg2().getImage(), spellx2, move.getY(), this);
			g2.drawImage(spells.getimg3().getImage(), spellx3, move.getY(), this);
			
			g2.setFont(f4);
			g2.setColor(brown);
			g2.drawString("Your Lives " + mylives, 20, 20);
			g2.drawString("Umbridges Lives " + umlives, 300, 20);

		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
		if(background == introscreen)
		{
			// if player clicks on buttons start timer for thier respective images
			if(e.getY() > 300 && e.getY() < 375 && e.getX() > 900 && e.getX() < 1200)
			{
				storytimer.start();
				timerintroscreen.stop();
	
			}
			if(e.getY() > 400 &&  e.getY() < 475 && e.getX() > 900 && e.getX() < 1200)
			{
				controltimer.start();
				
			}
			if(e.getY() > 500 &&  e.getY() < 575 && e.getX() > 900 && e.getX() < 1200)
			{
				timerintroscreen.stop();
				infotimer.start();
			}
		}
		
		if(background == pickcharacter)
		{
			// if player clicks girl set choice to 1 
			if(e.getY() > 0 && e.getY() < girl.getIconHeight() && e.getX() > 0 && e.getX() < girl.getIconWidth())
			{
			  if(choice == 3 )
			  {
				choice = 1;
			  	drawcontinue = true;
			  	avatar.getImage(choice);
			  }
			}
			// if player clicks boy set choice to 0 
			if(e.getY() > 0 && e.getY() < boy.getIconHeight() && e.getX() > 435 && e.getX() < 453 +boy.getIconWidth())
			{
			  if(choice ==3)
			  {
				  choice = 0;
				  avatar.getImage(choice);
				  drawcontinue = true;
			  }
			}
			// if player clicks continue stop timer and start timer for game1
			if (e.getY() > 363  && e.getY() < 438 && e.getX() > 100 && e.getX() < 400)
			{
				game1timer.start();
				avatarTimer.start();
				pickcharactertimer.stop();
			}
		}
		
		if(background == controlback)
		{
			// if player clicks back retrun to homescreen 
			if(e.getX() > 220 && e.getX() < 320 && e.getY() > 490 && e.getY() < 540)
			{
				controltimer.stop();
				background = introscreen;
				frame.setSize(background.getIconWidth(), background.getIconHeight());
				
			}
		}
		
		// if player clicks back return to homescreen 
		if(background == infoback)
		{
			if(e.getX() > 600 && e.getX() < 680 && e.getY() > 200 && e.getY() < 250)
			{
				infotimer.stop();
				background = introscreen;
				frame.setSize(background.getIconWidth(), background.getIconHeight());
				
			}
		}
		repaint();
	}

	
	public void mousePressed(MouseEvent e) 
	{
		
		
	}



	public void mouseReleased(MouseEvent e) 
	{
		
		
	}

	
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		
	}

}