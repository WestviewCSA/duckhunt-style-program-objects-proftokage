import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.JFrame;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class Fish {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    
    /*ORIGINAL!!!!!
    private Image normal;
    private Image dead;
    */
    
    private Image dead;
    private Tokage2 newTokage2 = new Tokage2("tokage_5.gif");
    
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    
    //variables for speed
    private double vx;
    private double vy;
    
    //variables for random values
    private double vxmin;
    private double vxmax;
    private double vymin;
    private double vymax;
    
    //debugging variable
    public boolean debugging = true;
    
    //score variable
    public int score = 0;
    
    //missed catches variable
    public int missedCatches = 0;

    // Constructor: runs when you make a new Duck object
    public Fish() {
        /*ORIGINAL!!!!!
         * normal = getImage("/imgs/fish.gif"); //Load the image 
         */
        dead = getImage("/imgs/caught_fish_2.png"); //Load dead fish image
        
        /*ORIGINAL!!!!!
         * img = normal;
         */
        img = getImage("/imgs/fish.gif");
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 3.5;
        scaleY = 3.5;
        x = 200;
        y = 450;
        vx = 0;
        vy = 0;
        
        //Define the max and min velocities
       
        /* ORIGINAL!!!!!
        vxmin = 5; 
        vxmax = 15; 
        vymin = 1;
        vymax = 5;
        */
        
        vxmin = 5; 
        vxmax = 10; 
        vymin = 5;
        vymax = 10;
        
        //initialize the vx and vy variables with non-zero values
        vx  = vxmin + (int)(Math.random()*(vxmax-vxmin));
        vy = vymin + (int)(Math.random()*(vymax-vymin));

        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public Fish(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Fish(int x, int y, int scaleX, int scaleY, double vx, double vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setScore(int score) {
    	this.score = score;
    }
    
    public void setMissedCatches(int missedCatches) {
    	this.missedCatches = missedCatches;
    }
    
    public void setVelocityVariables(double vxmin, double vxmax, double vymin, double vymax) {
    	this.vxmin = vxmin;
    	this.vxmax = vxmax;
    	this.vymin = vymin;
    	this.vymax = vymax;
    }
    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy
    public void update() {
    	
    	//Modify the x velocity and values
    	x = x+vx;
    	/*ORIGINAL!!!!!
    	if(x>=1800)||x<=0) {
    		if(vx<0) {
    			vx  = vxmin + (int)(Math.random()*(vxmax-vxmin));
    		}else {
    			vx = -vxmin+(int)(Math.random()*(-vxmax+vxmin));
    		}
    		
    	}
    	*/
    	
    	if(x>=1800) {
    		vx = -vxmin+(int)(Math.random()*(-vxmax+vxmin));
    	}
    	
    	if(x<=0) {
    		vx = vxmin+(int)(Math.random()*(vxmax-vxmin));
    	}
    	
    	
    	//Modify the y velocity and values
    	/*ORIGINAL!!!!!
    	if(y>=980||y<=0) {
    		if(vy<0) {
    			vy  = vymin + (int)(Math.random()*(vymax-vymin));
    		}else {
    			vy = -vymin +(int)(Math.random()*(-vymax+vymin));
    		}
    	}
    	*/
    	y = y+vy;
    	if(y>=980) {
    		vy = -vymin+(int)(Math.random()*(-vymax+vymin));
    	}
    	
    	if(y<=0) {
    		vy = vymin+(int)(Math.random()*(vymax-vymin));
    	}
    	
    	
    	//respawn - if falling from the sky respawn at the bottom
    	
    	//Code for when fish falls
    	
    	/*ORIGINAL!!!!!
    	if(vx==0 && vy==20 && y>=900) {
    		vx = (int)(Math.random()*(8)+3);
    			
    		//50% of time, vx is negative
    		if(Math.random()<0.5) {
    				vx*=-1;
    		}
    		
    		vy = -(int)(Math.random()*8+3);
    			
    		img = normal;
    		}
    		*/
    	
    	if(vx==0 && vy==20 && y>=900) {
    		vx = (int)(Math.random()*(2)+1);
    		if(Math.random()<0.5) {
    			vx*=-1;
    		}
    		vy = -(int)(Math.random()*1+1);
    		
    		img = getImage("/imgs/fish.gif");
    	}
    }
    
    public boolean checkFall() {
    	if(vx==0 && vy==20 && y>=900) {
    		return true;
    	}else {
    		return false;
    	}
	}
    
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        tx.scale(1, 1); //original up or down scale
        if(vx<0) {
        	//Flip around duck's center
        	tx.scale(-1, 1); 
        	//not change size, flip
        	
        	tx.translate(-img.getWidth(null), 0); 
        	//move so it stays in right place after flipping
        }
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        
        //tokage2.paint(g);
        
        update();
        init(x,y);
        
        //Cover hits box with a rectangle
        Color hitBlue = new Color(135,184,215);
        g.setColor(hitBlue);
        g.fillRect(1270, 60, 110, 40);
        
        //create a green hitbox
        g.setColor(Color.red);
        g.drawOval((int)x+35, (int)y-25, 160, 160);
        
        //create levels boxes
        //level 1
        Color level1 = new Color(240,237,201);
        g.setColor(level1);
        g.fillRect(0, 0, 100, 100);
        
        //level 2
        Color level2 = new Color(200, 224, 197);
        g.setColor(level2);
        g.fillRect(100, 0, 100, 100);
        
        //level 3
        Color level3 = new Color(94, 159, 171);
        g.setColor(level3);
        g.fillRect(200, 0, 100, 100);
        
        newTokage2.paint(g);
        
    }
    
    
    // Setup method: places the duck at (a, b) and scales it
    private void init(double a, double b) {
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Fish.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }
    
   
    //Collision and collision logic
    
    public int checkLevels(int mX, int mY) {
    	Rectangle mouse = new Rectangle (mX, mY, 50,50);
    	
    	Rectangle level1 = new Rectangle (0,0,100,100);
    	
    	Rectangle level2 = new Rectangle (100,0,100,100);
    	
    	Rectangle level3 = new Rectangle (200,0,100,100);
    	
    	if(mouse.intersects(level1)) {
    		return 1;
    	}else if (mouse.intersects(level2)) {
    		return 2;
    	}else if (mouse.intersects(level3)) {
    		return 3;
    	}else {
    		return 0;
    	}
    }
    
    public boolean checkCollision(int mX, int mY) {
    	//represent mouse as rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 150, 150);
    	
    	//represent this object as a rectangle
    	Rectangle fishRect = new Rectangle((int)x,(int)y,250,109);
    	
    	//use this built-in method for Rectangle to check if they intersect
    	//aka Collision
    	if(mouse.intersects(fishRect)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public int checkmissedCatches(int mX, int mY) {
    	//represent mouse as rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 150, 150);
    	
    	//represent this object as a rectangle
    	Rectangle fishRect = new Rectangle((int)x,(int)y,250,109);
    	
    	//use this built-in method for Rectangle to check if they intersect
    	//aka Collision
    	/*if(mouse.intersects(fishRect)) {
    		return missedCatches;
    	}
    	*/
    	if(!mouse.intersects(fishRect)&&checkLevels(mX,mY)==0) {
    		missedCatches = missedCatches+1;
    		return missedCatches;
    	}else {
    		return missedCatches;
    	}
    }
    
    public int checkNumberCollision(int mX, int mY) {
    	
    	//represent mouse as rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 150, 150);
    	
    	//represent this object as a rectangle
    	Rectangle fishRect = new Rectangle((int)x,(int)y,250,109);
    	
    	//use this built-in method for Rectangle to check if they intersect
    	//aka Collision
    	if(mouse.intersects(fishRect)) {
    		//logic if colliding
    		
    		//object fall from sky
    		vx = 0; //turn off vx to fall from sky
    		vy = 20; //all y or gravity
    		
    		//change sprite to the alternate skin
    		img = dead;//fish is dead
    		
    		newTokage2.setLocation(x, 300);
    		newTokage2.setVelocityVariables(0, -3);
    		
    		score = score+1;
    		return score;
    	}else {
    		return score;
    	}
    	//ORIGINALLY had an else return -1;
    }
    
    
}
