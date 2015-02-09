//Name: Haider Khan
//Date: 2-9-14
//Class: AST Graph Theory
//Homework 2

package Graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph extends Graph {

	// variables created to be accessed throughout graph.
	// THESE NEED TO BE DECLARED INSIDE OF NEEDED METHODS
	int vertex_1;
	int vertex_2;

	// Directed graph constructor
	public DirectedGraph(int vertices) {
		super(vertices);

	}

	public boolean adjacent(int i, int j) {

		// This method will check to see if there exists an edge between i --> j
		// regardless of whether or not there
		// does not exist an edge between j --> i.

		if (adjMatrixEdges[i][j] == true) {
			return adjMatrixEdges[i][j] = true;
		} else {
			return false;
		}

	}

	// This method adds an edge from vertex i to j, but not vice versa
	public void addEdge(int vertex_add_1, int vertex_add_2) {

		if (adjMatrixEdges[vertex_add_1][vertex_add_2] == false) {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
		} else {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
		}
	}

	// This method removes and edge from vertex i to j, but not from j to i.
	public void removeEdge(int vertex_remove_1, int vertex_remove_2) {

		if (adjMatrixEdges[vertex_remove_1][vertex_remove_2] == true) {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
		} else {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
		}
	}

	// A method init() that takes a parameter Edge and returns the start vertex
	// of that edge.
	public int init(Edge e) {

		// Calls on the getStart vertex method of the Edge class.
		e.getStart();

		return e.getStart();
	}

	// A method ter() which takes a parameter Edge and returns the end vertex of
	// that edge.
	public int ter(Edge f) {

		// Calls on the getEnd vertex method of the Edge class
		f.getEnd();
		return f.getEnd();
	}

	/*
	 * findIndependence method which will use d-Separation to find independences
	 * in a graph
	 */
	public List<Independence> findIndependence() {

		/*
		 * Independence Array which will be returned
		 */
		List<Independence> independentSets = new ArrayList<Independence>();

		/*
		 * Will store paths.
		 */
		List<ArrayList<int[]>> listOfArrays = new ArrayList<ArrayList<int[]>>();

		/*
		 * Represents paths
		 */
		ArrayList<int[]> paths = new ArrayList<int[]>();

		int[] path;

		int counter = 0;

		int destination;
		
		int startingIndex = 0;

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			counter = 0;
			
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				if (adjMatrixEdges[i][j]) {
					path = new int[adjMatrixEdges[i].length];
					path[startingIndex] = i;
					destination = j;
					paths.add(counter, path);
					counter++;
					if (destination == j) {
						listOfArrays.add(j, paths); // Add paths
					}
				}
			}
		}

		/*
		 * Independence variable
		 */
		Independence independent;

		/*
		 * Figure out if there is a path from i to j, if not then its
		 * independent
		 */
		for (int k = 0; k < listOfArrays.size(); k++) {
			for (int m = 0; m < paths.size(); m++) {
				for (int n = 0; n < paths.get(m).length; k++) {
					if (adjMatrixEdges[m][n] == false) {
						independent = new Independence(m, n, listOfArrays.get(m).get(m));
						independentSets.add(k, independent);
					}
				}
			}
		}

		System.out.println(independentSets);
		return independentSets;

	}

	public static void main(String[] args) {

		// Test graph
		DirectedGraph dGraph = new DirectedGraph(4);

		// Make a very simple directed graph
		dGraph.addEdge(0, 1);
		dGraph.addEdge(1, 2);
		dGraph.addEdge(2, 0);

		/*
		 * Test Graph for findIndependence() method
		 */
		DirectedGraph newGraph = new DirectedGraph(3);

		newGraph.addEdge(0, 1);
		newGraph.addEdge(2, 1);

		/*
		 * Find all the independence sets on newGraph
		 */
		newGraph.findIndependence();

	}

}
