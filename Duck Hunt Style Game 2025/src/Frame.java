import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.*;


public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 1880, screenHeight = 1080; //change this to match your background shape
	private String title = "Fish Hunt";
	
	public int checkCollisionNumber;
	public int missedCatches;
	public boolean checkCollision;
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
		//playSound("Typical_music.wav");
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}
	
	//FROM ONLINE!!!!!
	/*ORIGINAL!!!!!
	 * public void playSound(String soundFileName) {
		try {
			//code looks inside sound folder (made new folder under imgs)
			File soundFile = new File("sounds/"+soundFileName);
			
			//this converts the .wav file into a stream of sounds
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			
			//a clip is made; a clip is a short sound that can be replayed easily
			Clip clip = AudioSystem.getClip();
			
			//this opens the clip
			clip.open(audioInputStream);
			
			//this starts the clip so it can be used
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	Music mouseClickSound = new Music("Water_Audio.wav", false);
	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		System.out.println(mouse.getX()+":"+mouse.getY());
		
		//If the fish was caught, change Tokage to eating fish or else change him to normal Tokage
		checkCollision = fishObject.checkCollision(mouse.getX()-25,mouse.getY()-50);
		checkCollisionNumber = fishObject.checkNumberCollision(mouse.getX()-25,mouse.getY()-50);
		missedCatches = fishObject.checkmissedCatches(mouse.getX()-25, mouse.getY()-50);
		
		if(checkCollision==true) {
			tokageObject.changePicture("tokage_5.GIF");
			this.mouseClickSound.play();
		}else {
			tokageObject.changePicture("tokage_normal_3.png");
			
		}
		
		//If one of the level boxes is clicked, the fish's velocity changes accordingly
		int level = fishObject.checkLevels(mouse.getX()-25,mouse.getY()-50);
		
		if(level==0) {
			fishObject.setVelocityVariables(0.5, 1, 0.5, 1);
		}else if(level==1) {
			fishObject.setVelocityVariables(0.5, 1, 0.5, 1);
			fishObject.changePicture("fish.gif");
		}else if (level==2) {
			fishObject.setVelocityVariables(3, 4, 3, 4);
			fishObject.changePicture("fish.gif");
		}else if (level ==3) {
			fishObject.setVelocityVariables(5, 7, 5, 7);
			fishObject.changePicture("fish.gif");
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		System.out.println("from keyPressed method:"+key.getKeyCode());
		
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		
		
	}
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	private Fish fishObject = new Fish();
	private Tokage tokageObject = new Tokage();
	//private Tokage tokage2 = new Tokage();
	private Background myBackground= new Background();
	private MyCursor cursor = new MyCursor();
	private Foreground myForeground = new Foreground();
	private Scoretables myScoretables = new Scoretables();
	
	//Fish scores printed
	private FishScore myFishScore1 = new FishScore(1570,85,0.7,0.7);
	private FishScore myFishScore2 = new FishScore(1605,85,0.7,0.7);
	private FishScore myFishScore3 = new FishScore(1640,85,0.7,0.7);
	private FishScore myFishScore4 = new FishScore(1675,85,0.7,0.7);
	private FishScore myFishScore5 = new FishScore(1710,85,0.7,0.7);
	private FishScore myFishScore6 = new FishScore(1745,85,0.7,0.7);
	private FishScore myFishScore7 = new FishScore(1780,85,0.7,0.7);
	private FishScore myFishScore8 = new FishScore(1815,85,0.7,0.7);
	
	//Fill missed catches box
	private MissedCatches myMissedCatches1 = new MissedCatches(1275,65,0.5,0.5);
	private MissedCatches myMissedCatches2 = new MissedCatches(1305,65,0.5,0.5);
	private MissedCatches myMissedCatches3 = new MissedCatches(1335,65,0.5,0.5);
	
	//Win or Lose screen
	private Win_Lose_Screen screen1 = new Win_Lose_Screen()
	
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
	 
		//background should be drawn before the object
		//or based on how you want to LAYER.
		myBackground.paint(pen);
		
		myScoretables.paint(pen);
		
			if(checkCollisionNumber>=1) {
			myFishScore1.paint(pen);
			}
		
			if(checkCollisionNumber>=2) {
			myFishScore2.paint(pen);
			}
		
			if(checkCollisionNumber>=3) {
			myFishScore3.paint(pen);
			}
		
			if(checkCollisionNumber>=4) {
			myFishScore4.paint(pen);
			}
		
			if(checkCollisionNumber>=5) {
			myFishScore5.paint(pen);
			}
		
			if(checkCollisionNumber>=6) {
			myFishScore6.paint(pen);
			}
		
			if(checkCollisionNumber>=7) {
			myFishScore7.paint(pen);
			}
		
			if(checkCollisionNumber>=8) {
			myFishScore8.paint(pen);
			//playSound("Victory_music.wav");
			}
		
			if(checkCollisionNumber==9){
			fishObject.setScore(0);
			
			}
		
			
		/*ORIGINAL!!!!!+home
		myFishScore1.paint(pen);
		myFishScore2.paint(pen);
		myFishScore3.paint(pen);
		myFishScore4.paint(pen);
		myFishScore5.paint(pen);
		myFishScore6.paint(pen);
		myFishScore7.paint(pen);
		myFishScore8.paint(pen);
		*/
		
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		
		myForeground.paint(pen);
		
		
		fishObject.paint(pen);
		//Show the nets if there are any missed catches
		if(missedCatches>=1) {
			myMissedCatches1.paint(pen);
		}
		
		if(missedCatches>=2) {
			myMissedCatches2.paint(pen);
		}
		
		if(missedCatches>=3) {
			myMissedCatches3.paint(pen);
			//playSound("Losing_music.wav");
		}
		
		if(missedCatches==4) {
			fishObject.setMissedCatches(0);
			fishObject.setScore(0);
		}
		
		pen.setColor(Color.WHITE);
		pen.drawString("Level 1", 40, 60);
		
		tokageObject.paint(pen);
		
		cursor.paint(pen);
		//tokage2.paint(pen);
		
		
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image image= toolkit.getImage("net.png");
		
		Cursor a = toolkit.createCustomCursor(image,new Point(this.getX(),this.getY()),"");
		this.setCursor(a);
		
	}
	
	

}
