import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class Win {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    

    // Constructor: runs when you make a new Duck object
    public Win() { 
    	
        img = getImage("/imgs/Win.GIF"); // Load the image file
        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 2.5;
        scaleY = 2.5;
        x = 300;
        y = 300;


        init(x-20, y+20); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public Win(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Win(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }

    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
   
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        tx.scale(scaleX, scaleY);//<-- original down or up scale
        g2.drawImage(img, tx, null);      // Actually draw the duck image

        init(x,y);
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
            URL imageURL = Win.class.getResource(path);
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
   
    public boolean checkPlayAgain(int mX, int mY) {
    	
    	//represent mouse as rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 150, 150);
    	
    	//represent this object as a rectangle
    	Rectangle winRect = new Rectangle((int)x,(int)y,500,175);
    	
    	//use this built-in method for Rectangle to check if they intersect
    	//aka Collision

    	if(mouse.intersects(winRect)) {
    		//logic if colliding
    		return true;
    	}else {
    		return false;
    	}
    	
    }  
    
    
} //<--closing bracket for class dont delete- add above!
