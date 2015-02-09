package Graph;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings ("serial")

public class GraphicalProgram extends JFrame {
	
	
	public GraphicalProgram(){
		
		this.setSize(500, 500);
		this.setTitle("Graph");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GraphLayout(), BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	private class GraphLayout extends JComponent{
		
		//The graphics class allows us to draw on our component which is GraphicLayout
		public void paint(Graphics g){
			
			Graphics2D graph2 = (Graphics2D)g;
			
			//Set preferences of rendering.
			//This sets up anti-aliasing which cleans up the edges of whatever you draw on screen.
			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//Let's practice by drawing a line, to do this we have to create a new line object shape.
			Shape drawLine = new Line2D.Float(20, 90, 55, 250);
											//Here the first two values represent
											//the starting point of the line in the x, y dimension
											//The last two values represent the end point of the
											//line.
			
			//Drawing a arc now.
			//First two values are where the starting point is on the x, y axis
			//Next two are the height and width of the arc
			//The last three are the starting degree of the arc, here we picked 45 degrees
			//The second last value is how much of an angle do you want your arc to have
			//	we pick 180, we want the arc to go around 180 degrees from the 45 degree point
			//The last value is what type of arc do you want to draw, we picked the basic
			//Arc2D. There are three options for this type, open, chord, and pie
					//Open is if you don't want the end points to meet
					//Chord is if you want a straight line from the end points
					//Pi is if you want to draw a pi from the endpoints.
//			Shape drawArc2D = new Arc2D.Double(5, 150, 100, 100, 45, 180, Arc2D.OPEN);
//			Shape drawArc2D_2 = new Arc2D.Double(5, 200, 100, 100, 45, 45, Arc2D.CHORD);
//			Shape drawArc2D_3 = new Arc2D.Double(5, 250, 100, 100, 45, 45, Arc2D.PIE);
			
			//Next we will define a color for the objects used inside your program
			graph2.setPaint(Color.BLACK);
			
			//Next we call the draw method on graph 2 and tell it what to draw.
			graph2.draw(drawLine);
//			graph2.draw(drawArc2D);
//			graph2.draw(drawArc2D_2);
//			graph2.draw(drawArc2D_3);


		}
		
	}
	
	public static void main(String[] args){
		
		new GraphicalProgram();
	
	}
		
}
	