//Name: Haider Khan
//Date 3-22-14
//Class: Advanced Special Topics Graph Theory
//Instructor: Dr. Patterson
//"I pledge I have acted honorably" - Haider Khan

package Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalGraph extends Graph {
	
	//Calling the super class and importing methods from Graph.
	public GraphicalGraph(int vertices) {
		super(vertices);
		//Create a panel to add to the frame.
		GraphicalProgram panel = new GraphicalProgram();
		
		// Create a new frame object.
		JFrame frame = new JFrame();

		// Set the parameters for the frame object (size, title, operations,
		// etc.)
		frame.setSize(500, 500);
		frame.setTitle("Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class GraphicalProgram extends JPanel {
		// Draw method which will draw the shape of the graph.
		public void draw(Graphics g) {

			// Define parameters to draw the graph in. Example taken from
			// http://www.zetcode.com/gfx/java2d/basicdrawing/
			Dimension size = getSize();
			Insets insets = getInsets();

			int w = size.width - insets.left - insets.right;
			int h = size.height - insets.top - insets.bottom;

			// Parameters for vertices, to be used later to draw vertices.
			int xOfVertex = 0;
			int yOfVertex = 0;

			// Extend to Graphics 2D
			Graphics2D graph = (Graphics2D) g;

			//Create lists that will store the points coordinates.
			List<Integer> pointsAtX = new ArrayList<Integer>();
			List<Integer> pointsAtY = new ArrayList<Integer>();

			// Set preferences. This cleans up edges.
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			// Generate vertices as random points on JPanel.
			// I realized that everytime I resize the window new random points
			// are created, so we set the parameter to 0.
			Random r = new Random(0);

			// Draw random points on JPanel for the vertices.
			for (int l = 0; l < adjMatrixEdges.length; l++) {
				String str = Integer.toHexString(l);
				// Define where a specific vertex will fall on a x, y coordinate
				// inside the container.
				xOfVertex = Math.abs(r.nextInt()) % w;
				yOfVertex = Math.abs(r.nextInt()) % h;
				
				//Store the coordinates of the points to be accessed later.
				pointsAtX.add(xOfVertex);
				pointsAtY.add(yOfVertex);
				
				//Initialize a node graphics object to represent vertices.
				Graphics2D node = (Graphics2D)g;
				node.fillOval(xOfVertex, yOfVertex, 7, 7);
				graph.drawString(str, xOfVertex, yOfVertex + 20);
			}
			
			//Create a nested for loop to check if there is an edge between two nodes.
			//If there is an edge draw a line.
			for (int i = 0; i < adjMatrixEdges.length; i++){
				for (int j = 0; j < adjMatrixEdges[i].length; j++){
					if (adjMatrixEdges[i][j]){
						
						//Grab coordinates from the list.
						int xOfI = pointsAtX.get(i);
						int yOfI = pointsAtY.get(i);
						int xOfJ = pointsAtX.get(j);
						int yOfJ = pointsAtY.get(j);
						graph.drawLine(xOfI, yOfI, xOfJ, yOfJ);
					}
				}
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			draw(g);
			
			
		}
	}
	
	public static void main(String[] args){
		
		GraphicalGraph k5_graph = new GraphicalGraph(5);
		
		//Create a K5 connected graph.
		k5_graph.addEdge(0,  1);
		k5_graph.addEdge(0,  2);
		k5_graph.addEdge(0,  3);
		k5_graph.addEdge(0,  4);
		k5_graph.addEdge(1,  2);
		k5_graph.addEdge(1,  3);
		k5_graph.addEdge(1,  4);
		k5_graph.addEdge(2,  3);
		k5_graph.addEdge(2,  4);
		k5_graph.addEdge(3,  4);
		
		//Test a big graph.
//		GraphicalGraph bigGraph = new GraphicalGraph(1000);
//		
//		for (int i = 0; i < bigGraph.numVertices; i++){
//			bigGraph.addEdge(i, i + 1);
//		}
		
		
	}
	
}
