import java.awt.Color;
import java.util.*;
import uwcse.io.*;
import uwcse.graphics.*;
import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 */

public class GraphicsElements {

	/** Maximum number of disks in a pile of disks */
	public static final int MAXIMUM_NUMBER_OF_DISKS = 100;

	/** Maximum number of rows (or columns) in a square checkered board */
	public static final int MAXIMUM_NUMBER_OF_ROWS = 50;

	/** Maximum number of points in a Sierpinski triangle */
	public static final int MAXIMUM_NUMBER_OF_POINTS = 10000;

	/** Width of the window (from ViewWindow) */
	public static final int WIDTH = ViewWindow.WINDOW_WIDTH;

	/** Height of the window (from ViewWindow) */
	public static final int HEIGHT = ViewWindow.WINDOW_HEIGHT;

	// Put your other instance fields here (if you need any)

	/**
	 * Create a top view of a pile of disks of decreasing diameters (from bottom
	 * to top). Use filled circles. The color of each disk is random. The pile
	 * should fill the window. <br>
	 * Store the circles in an ArrayList and return that ArrayList (the disk at
	 * the bottom should be the first element of the ArrayList)<br>
	 * The number of disks is given by the user (use a dialog box). If that
	 * number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_DISKS, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Oval> createAPileOfDisks() {
		
		Input input = new Input();
		int numDisks = 0;
		
		// get our number of disks from the user
		do {
			numDisks = input.readIntDialog("How many disks? (max of " + MAXIMUM_NUMBER_OF_DISKS + "):");
			if (numDisks <= 0 || numDisks > MAXIMUM_NUMBER_OF_DISKS){
				JOptionPane.showMessageDialog(null, "Sorry, that number is out of range.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		} while (numDisks <= 0 || numDisks > MAXIMUM_NUMBER_OF_DISKS);
		
		// setup
		// initialize the array list to return
		ArrayList<Oval> pile = new ArrayList<Oval>(numDisks);
		int maxDiameter = WIDTH;
		int minDiameter = 10;
		int steppingSize = (maxDiameter - minDiameter) / numDisks;
		
		
		// create the disks
		for (int i = 0; i < numDisks; i ++){
			Oval disk = new Oval(
					i*steppingSize/2,
					i*steppingSize/2, 
					maxDiameter - (steppingSize * i), 
					maxDiameter - (steppingSize * i),
					randomColor(),
					true);
			pile.add(disk);
		}
		
		return pile;
	}
	
	private Color randomColor(){
		Random gen = new Random();
		Color random = new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255));
		return random;
	}

	/**
	 * Create a square checkered board. Create a Rectangle for each square on
	 * the board. Store the Rectangles in an ArrayList and return that
	 * ArrayList. Use two colors only to paint the squares.<br>
	 * The board should cover most of the window. The number of rows (=number of
	 * columns) is given by the user (use a dialog box). If that number is less
	 * than or equal to 0 or greater than MAXIMUM_NUMBER_OF_ROWS, display an
	 * error message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList createACheckeredBoard() {
		boolean valid = false;
		int rows = 0;
		Color[] colors = {new Color(0,85,178), new Color(255,162,0)};
		boolean colorSwitch = true;
		while(!valid){
			rows = Integer.parseInt(JOptionPane.showInputDialog(
					null,
					"Please enter a value between 1 and " + MAXIMUM_NUMBER_OF_ROWS,
					JOptionPane.INFORMATION_MESSAGE));
			if(rows > 0 && rows <= MAXIMUM_NUMBER_OF_ROWS) valid = true;
		}
		ArrayList<Rectangle> squares = new ArrayList<>();
		int size = (WIDTH > HEIGHT)?HEIGHT/rows:WIDTH/rows;
		int center = (WIDTH - HEIGHT)/2;
		for (int i = 0; i <= rows; i++) {
			for (int j = 0; j <= rows; j++) {
				squares.add(new Rectangle(i * size, j * size,size,size,colors[(colorSwitch?0:1)],true));
				colorSwitch = !colorSwitch;
			}
			if(!(rows % 2 == 0))colorSwitch = !colorSwitch;
		}
		System.out.print(center);
		return squares;
	}

	/**
	 * Create a Sierpinski triangle. Create a filled Oval (circle of radius 1)
	 * for each point of the triangle. Store the Ovals in an ArrayList and
	 * return that ArrayList. Use one color only to paint the Ovals.<br>
	 * The triangle should cover most of the window.<br>
	 * The number of points is given by the user (use a dialog box). If that
	 * number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_POINTS, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList createASierpinskiTriangle() {
		
		// get the number of points from the user

	     int pointCount;
		do {

	        Input input;
			pointCount = input.readIntDialog("How many points in the triangle?");
	        if (pointCount <= 0 || pointCount > MAXIMUM_NUMBER_OF_POINTS){

	                JOptionPane.showMessageDialog(null, "Sorry, that number is out of range.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	            }

	        } while (pointCount <= 0 || pointCount > MAXIMUM_NUMBER_OF_POINTS);


	        ArrayList<Oval> points = new ArrayList<Oval>();

	        Oval p1 = new Oval(WIDTH / 2, 0, 2, 2, Color.blue, true);

	        Oval p2 = new Oval(0, HEIGHT - 1, 2, 2, Color.blue, true);

	        Oval p3 = new Oval(WIDTH - 1, HEIGHT - 1, 2, 2, Color.blue, true);

	        Random randomGenerator = new Random();

	        Oval p = p1;

	        for (int i = 0; i < pointCount; i++) {

	            int choice = randomGenerator.nextInt(3) + 1;

	            int x;

	            int y;

	            if (choice == 1) {

	                x = p1.getX();

	                y = p1.getY();

	            } else if (choice == 2) {

	                x = p2.getX();

	                y = p2.getY();

	            } else if (choice == 3) {

	                x = p3.getX();

	                y = p3.getY();

	            }

	            int midX = (p.getX() + x) / 2;

	            int midY = (p.getY() + y) / 2;

	            Oval q = new Oval(midX - 1, midY - 1, 2, 2, Color.blue, true);

	            points.add(q);

	            p = q;
	        }
	        return points;
	    }
	/**
	 * Rotate the colors in the pile of disks. Set the color of each disk to the
	 * color of the disk just above it. For the top disk, set its color to the
	 * color of the bottom disk (e.g. with 3 disks, if the colors are from
	 * bottom to top, red, blue, yellow, the new colors of the disks are from
	 * bottom to top, blue, yellow, red).<br>
	 * Precondition: graphicsList describes a pile of disks
	 */
	public ArrayList<Oval> rotateColorsInPileOfDisks(ArrayList<Oval> graphicsList) {
		// iterate through our list
		// store first disks color
		Color firstDiskColor = graphicsList.get(0).getColor();
		for (int i = 0; i < graphicsList.size(); i++){
			// current disk in loop
			Oval disk = graphicsList.get(i);
			// use the next disk, if last disk use first ones color
			if (i < graphicsList.size()-1){
				// replace the color with next disk
				disk.setColor(graphicsList.get(i + 1).getColor());
			} else {
				disk.setColor(firstDiskColor);
			}
			
			// save the disk
			graphicsList.set(i, disk);
		}
		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the checkboard<br>
	 * Precondition: graphicsList describes a checkered board
	 */
	public ArrayList flipColorsInCheckeredBoard(ArrayList graphicsList) {
		return null;
	}

	/**
	 * Change the color of the Sierpinski triangle (all circles should change to
	 * the same color). Switch between 3 colors (e.g. blue->red->green, if the
	 * triangle was blue, make it red, if it was red, make it green, if it was
	 * green make it blue).<br>
	 * Precondition: graphicsList describes a Sierpinski triangle
	 */
	public ArrayList changeColorsInSierpinskiTriangle(ArrayList graphicsList) {

		        Color newColor;

		        if (!graphicsList.isEmpty()) {

		            Color oldColor = graphicsList.get(0).getColor();

		            if (oldColor == Color.blue) {

		                newColor = Color.red;

		            } else if (oldColor == Color.red) {

		                newColor = Color.green;

		            } else if (oldColor == Color.green) {

		                newColor = Color.blue;
		            }
		        }    

		        for (Oval o : graphicsList) {

		            o.setColor(newColor);
		        }
		         return graphicsList;
		    }

	/**
	 * Return the color at location (x,y) in the pile of disks. If (x,y) is not
	 * part of the pile of disks, return null.<br>
	 * Precondition: graphicsList describes a pile of disks
	 */
	public Color getColorInPileOfDisks(int x, int y, ArrayList<Oval> graphicsList) {
		// iterate through list, starting from end
		// we start from the end since those discs are on top,
		// and going to be the one clicked
		for (int i = graphicsList.size()-1; i >=0; i--){
			Oval disk = graphicsList.get(i);
			
			int radius = (disk.getWidth()/2);
			int distanceX = x - disk.getCenterX();
			int distanceY = y - disk.getCenterY();
			
			// pythagoras rocks!  
			// leave these squared for easy comparison
			int distance = distanceX*distanceX + distanceY*distanceY;
			radius *= radius;
			
			if (distance < radius){
				// click was within this disks radius!
				// return this color, breaking out of the loop.
				return disk.getColor();
			}
		}
		// not within any disc at this point
		return null;
	}

	/**
	 * Return the color at location (x,y) in the checkered board. If (x,y) is
	 * not part of the board, return null.<br>
	 * Precondition: graphicsList describes a checkered board
	 */
	public Color getColorInCheckeredBoard(int x, int y, ArrayList graphicsList) {
		return null;
	}

	/**
	 * Return the color at location (x,y) in the Sierpinski triangle. If (x,y)
	 * is not part of the pile of disks, return null.<br>
	 * Precondition: graphicsList describes a Sierpinski triangle
	 */
	public Color getColorInSierpinskiTriangle(int x, int y,ArrayList graphicsList) {

	        for (Oval o : graphicsList) {

	            int centerX = o.getCenterX();

	            int centerY = o.getCenterY();

	            double diffX = x - centerX;

	            double diffY = y - centerY;

	            double dist = (x * x) + (y * y);


	            if (dist <= 1.0) {

	                return o.getColor();

	            }

	        }
	        return null;
	}

