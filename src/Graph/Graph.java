package Graph;

//Name: Haider Khan
//Date: 1-23-13
//Class: Advanced Special Topics: Graph Theory
//Instructor: Dr. Brian Patterson
//Approach: Adjacency Matrix Approach
//Reference: (2002-2010 Robert Sedgewick and Kevin Wayne, Algorithms 4th Edition), and CS Berkeley University

import java.util.*;

public class Graph {

	// Setup privately modified variables which will define the graph

	// These two parameters are storage variables for edges and vertices
	// These variables were changed from Vertex and Edge to numVertices and
	// numEdges.
	protected int numVertices;

	// This will be the adjacency matrix to represent our graph, this will
	// represent edges.
	// adj_Matrix_Edges was previously static meaning it did not have access to
	// multiple graphs, onyl one graph.
	protected boolean[][] adjMatrixEdges;

	// Setup some variables that will represent the variables used in the DFS
	// algorithm for the simpleColouring method.
	// Represents the number of colours possible in a graph
	private int[] color;

	// Setup a degree list for the Depth First Search
	protected List<Integer> degreeList = new ArrayList<Integer>();
	
	/*
	 * Int array to be initialized for the sssp() method
	 */
	protected int[][] ssspArray;
	
	/*
	 * Graph that is returned from sssp() method
	 */
	protected Graph ssspGraph;
	
	/*
	 * Boolean for chvatal's test
	 */
	protected boolean hasHamiltonian;
	
	/*
	 * Setup value for ramseyCheck
	 */
	protected int r;

	// first step will be to setup the graph, using this constructor
	public Graph(int vertices) {

		numVertices = vertices;

		if (numVertices < 0) {
			throw new RuntimeException(
					"Number of vertices cannot be a nonnegative value");
		}

		System.out.println("There are now " + numVertices
				+ " vertices in the graph.");

		// A graph is created based on the specifications, N X N or (n^2)
		// graph.
		adjMatrixEdges = new boolean[numVertices][numVertices];
	}

	// This method validates whether or not two vertices are adjacent, returns
	// true if adjacent false otherwise.
	public boolean adjacent(int i, int j) {

		// The benefit of having a 2-d boolean array is that you can just call
		// the vertices and have it return true or false.
		// System.out.println("Vertex " + i + " and Vertex " + j +
		// " are adjacent."); //Test code
		return adjMatrixEdges[i][j];
	}

	// I needed a review of this class so I had to read about ArrayList class,
	// but it allows
	// you to iterate the columns in the adjacency matrix.
	// It also allows you to print out integers values instead of booleans
	// The for loop, loops over a column you would select, and then the if
	// statement checks for an incident in that column.
	public List<Integer> neighbors(int vertex) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < adjMatrixEdges.length; i++) {

			// The if statement here does not need an equality sign since
			// booleans are in the 2-d matrix.
			if (adjMatrixEdges[vertex][i]) {
				// adds that vertex i to the list
				neighbors.add(i);
			}
		}
		// System.out.println(neighbors); //Test code
		return neighbors;
	}

	// This method will count the number of neighbors for a specific vertex and
	// then add up the sum and then divide by number of vertices.
	public double averageDegree() {

		// create a variable for the count, and initialize the counter to 0.
		double edgeCounter = 0;

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				// this if statement scans the specific vertex for true
				// statements in the boolean array
				if (adjMatrixEdges[i][j]) {
					// this logical expression adds up the count of the true
					// statements, in graph theory this is adding up the
					// degree of that specific vertex.
					edgeCounter++;
				}
			}
		}
		// Here neighbor count is the number of edges as well.
		edgeCounter = 2 * (edgeCounter - 1) / numVertices;
		System.out.println(edgeCounter);
		return edgeCounter;
	}

	public boolean[][] addVertex() {

		// add an extra vertex to the graph.
		numVertices++;

		// secondly we have to copy over the contents of the old array into a
		// new array.

		// Initialize a new array

		boolean[][] newAdjMatrixEdges = adjMatrixEdges;

		// setup a for loop which sets up new values for

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges.length; j++) {
				adjMatrixEdges[i][j] = newAdjMatrixEdges[i + 1][j + 1];
			}
		}
		return newAdjMatrixEdges;

	}

	public void removeVertex(int vertex) {

		// set a local variable.
		int vertex_Selected = vertex;

		// create a new 2-d array where you can copy the old one over.
		boolean[][] new_adj_Matrix_Edges = new boolean[adjMatrixEdges.length - 1][adjMatrixEdges.length - 1];

		// create a for loop setup to copy over all data from old array to the
		// new array.
		for (int g = 0; g < vertex_Selected; g++) {
			for (int h = 0; h < vertex_Selected; h++) {
				new_adj_Matrix_Edges[g][h] = adjMatrixEdges[g][h];
			}
		}

		// now that we resize the new array with removed vertex.

		for (int i = vertex_Selected + 1; i < new_adj_Matrix_Edges.length; i++) {
			for (int j = vertex_Selected + 1; j < new_adj_Matrix_Edges[i].length; j++) {
				new_adj_Matrix_Edges[i][j] = adjMatrixEdges[i][j];
			}
		}
	}

	public void addEdge(int vertex_add_1, int vertex_add_2) {

		// We set a if statement checking to see if the following code does not
		// contain a self loop
		// and that there isn't already an edge between the two vertices picked.
		if (vertex_add_1 > numVertices || vertex_add_2 > numVertices) {
			System.out
					.println("You cannot add an edge to a vertex which doesn't exist.");
		}
		if (adjMatrixEdges[vertex_add_1][vertex_add_2] == false
				&& vertex_add_1 != vertex_add_2) {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
			adjMatrixEdges[vertex_add_2][vertex_add_1] = true;
			// System.out.println("There is now an edge between vertex " +
			// vertex_add_1 + " and vertex " + vertex_add_2); //Test code to see
			// outputs.
		} else {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
			adjMatrixEdges[vertex_add_2][vertex_add_1] = true;
		}
	}

	// This method removes an edge if the two int values in the 2-d boolean
	// array are true, converts to false, otherwise it stays false if no edge
	// present
	public void removeEdge(int vertex_remove_1, int vertex_remove_2) {

		if (vertex_remove_1 > numVertices || vertex_remove_2 > numVertices) {
			System.out
					.println("You cannot remove an edge to a vertex which doesn't exist.");
		}
		if (adjMatrixEdges[vertex_remove_1][vertex_remove_2] == true
				&& vertex_remove_1 != vertex_remove_2) {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
			adjMatrixEdges[vertex_remove_2][vertex_remove_1] = false;
			// System.out.println("The between vertex " + vertex_remove_1 +
			// " and vertex "
			// + vertex_remove_2 + " was removed."); //Test Code
		} else {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
			adjMatrixEdges[vertex_remove_2][vertex_remove_1] = false;
		}
	}

	// setup a method which finds the shortest cycle in the graph.
	// We want to set the method to return an int value, because the girth of
	// the graph will represent an integer value
	// which cannot be a negative value.

	// if we find the diameter of a graph then the shortest cycle will be the
	// girth, which will be the
	// 2diam(G) + 1.
	public int girth() {

		int vertexCounter = 0;

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				if (adjMatrixEdges[i][j] == adjMatrixEdges[j][i]) {
					vertexCounter++;
				}
			}
		}

		return vertexCounter;

	}

	// The diamter of the graph will be the largest number of true elements in a
	// row.
	// So for example in a 3 vertex graph, if row 0 has 1 true value, and row 1
	// has 2 true values, and row 2 has 1
	// true value, then the diameter is the maximum number of true values in row
	// 1.
	public int diameter() {

		// Setup a counter which will count the number of true elements in a row
		// of the 2-d array
		int true_Counter = 0;

		// Setup a diameter variable.
		int diameter;

		// setup a for loop system to iterate through the array.

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				if (adjMatrixEdges[i][j] == true) {
					true_Counter++;
				}
			}
		}

		diameter = true_Counter / 2;
		return diameter;

	}

	// This method will get the number of edges using similar code to neighbors
	// method.
	// In detail, it will count the total neighbors for each vertex and then
	// divide by 2.
	public int numOfEdges() {
		// Initialize an edge counter.
		int numOfEdges = 0;

		List<Integer> neighbors = new ArrayList<Integer>();
		// Loop over the 2-d array and add neighbors to arraylist.
		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				// The if statement here does not need an equality sign since
				// booleans are in the 2-d matrix.
				if (adjMatrixEdges[i][j]) {
					// adds that vertex j to the list if adjacent to vertex i.
					neighbors.add(j);
					numOfEdges = neighbors.size();
				}
			}
		}
		// System.out.println("The number of edges in your graph are " +
		// numOfEdges/2); //Test code
		return numOfEdges / 2;
	}

	// This method uses Euler's relationship to determine whether or not a graph
	// is planar.
	public boolean isPlanar(Graph graph) {

		if (numVertices >= 3 && graph.numOfEdges() > ((3 * numVertices) - 6)) {
			System.out.println("Graph is not planar."); // Test Code
			return false;
		}

		System.out.println("Graph is planar."); // Test Code
		return true;

	}

	// Depth First Search Method which searches through graph
	// and marks vertices if there is a colour
	private List<Integer> sortDegreeSequence(Graph g) {

		// Setup an arraylist which will sort all the vertices
		// from highest to lowest degree

		for (int i = 0; i < g.adjMatrixEdges.length; i++) {
			degreeList.add(i);
		}

		// Run a for loop through the adj matrix edges and use the collections
		// class to swap out the vertices if the degree of j is higher than k.
		for (int k = 1; k < g.adjMatrixEdges.length; k++) {
			for (int j = 0; j < g.adjMatrixEdges.length; j++) {
				if (g.neighbors(j).size() > g.neighbors(k).size()) {
					Collections.swap(degreeList, k, j);
				}
			}
		}

		Collections.reverse(degreeList);

		// Test code to see what's coming out.
		//System.out.println(degreeList);
		return degreeList;
	}

	public boolean simpleColouring(int k, Graph g) {

		// Initialize the colors 1-d array to the size of degreeList
		color = new int[degreeList.size()];

		// Initialized a dummy arraylist to store neighbors in.
		List<Integer> tempDegreeList = new ArrayList<Integer>();

		// Have a loop that takes first index out of out of degree list and
		// checks its
		// neighbors
		for (int i = 0; i < degreeList.size(); i++) {
			color[i] = 1;
			tempDegreeList = g.neighbors(i);

			// Setup conditional
			for (int j = 0; j < tempDegreeList.size(); j++) {
				if (degreeList.get(i) != tempDegreeList.get(j)) {
					color[j] = 0;
				}
			}

		}

		// Setup counter value
		int counter = 0;

		for (int k1 = 0; k1 < color.length; k1++) {
			for (int l1 = 0; l1 < k1; l1++) {
				if (color[k1] != color[l1]) {
					counter += 1;
				}
			}
		}

		System.out.println(counter);

		if (counter <= k) {
			return true;
		}
		return false;

	}

	public int[][] turnBooleanToInt(){
		
		/*
		 * initialize sssp array to adjMatrixEdges
		 */
		ssspArray = new int[numVertices][numVertices];
		
		//This method will turn the 2-d Boolean array to an 
		for (int i = 0; i < adjMatrixEdges.length; i++){
			for (int j = 0; j < adjMatrixEdges[i].length; j++){
				if (adjMatrixEdges[i][j] == true){
					ssspArray[i][j] = 1;
					//System.out.println(ssspArray[i][j]);
				}
				else 
					ssspArray[i][j] = 0;
					//System.out.println(ssspArray[i][j]);
			}
		}
		
		
		return ssspArray;
		
	}
	
	/*
	 * Dijsktra's Algorithm using Depth First Search
	 */
	public Graph sssp(Graph g) {

		// Setup an arraylist which will sort all the vertices
		// from highest to lowest degree

		for (int i = 0; i < g.adjMatrixEdges.length; i++) {
			degreeList.add(i);
		}

		// Run a for loop through the adj matrix edges and use the collections
		// class to swap out the vertices if the degree of j is higher than k.
		for (int k = 1; k < g.adjMatrixEdges.length; k++) {
			for (int j = 0; j < g.adjMatrixEdges.length; j++) {
				if (g.neighbors(j).size() > g.neighbors(k).size()) {
					Collections.swap(degreeList, k, j);
				}
			}
		}

		Collections.reverse(degreeList);
		
		//Initialize ssspGraph to the degreelist size
		ssspGraph = new Graph(degreeList.size());
		
		//Create edges from the vertices with the highest degree using the degree list.
		for (int i = 0; i < ssspGraph.adjMatrixEdges.length; i++){
			for (int j = 0; j < ssspGraph.adjMatrixEdges[i].length; j++){
				ssspGraph.adjMatrixEdges[degreeList.get(i)][degreeList.get(j)] = true;
				ssspGraph.adjMatrixEdges[degreeList.get(j)][degreeList.get(i)] = true;
			}
		}
		// Test code to see what's coming out.
		//System.out.println(degreeList);
		return ssspGraph;
	}
	
	/*
	 * Implement Chvatal's test for testing hamiltonian cycles with this method
	 */
	public boolean hamCycle(Graph g){
		
		/*
		 * Use degree sequence method to sort out degree and then do chvatal's test
		 */
		sortDegreeSequence(g);
		
		int tempSize = degreeList.size();
		//System.out.println(tempSize); //test code
		
		/*
		 * Use degreeList variable to verify chvatal's test
		 */
		for (int i = 0; i < degreeList.size(); i++){
			/*
			 * if a(i) <= i implies a(n - i) >= (n - i) == true then return true
			 */
			if (degreeList.get(i) <= i && ((degreeList.get(tempSize - i)) >= (tempSize - i))){
				System.out.println("The graph has a hamiltonian cycle");
				hasHamiltonian = true;
			}
			else {
				System.out.println("Graph does not have a hamiltonian cycle");
				hasHamiltonian = false;
				break;
			}
		}
		
		return hasHamiltonian;
	}
	
	public List<Integer> nonNeighbors(int vertex) {
		List<Integer> nonNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < adjMatrixEdges.length; i++) {

			// The if statement here does not need an equality sign since
			// booleans are in the 2-d matrix.
			if (!adjMatrixEdges[vertex][i]) {
				// adds that vertex i to the list
				nonNeighbors.add(i);
			}
		}
		System.out.println(nonNeighbors); //Test code
		return nonNeighbors;
	}
	
	public List<Integer> ramseyCheck(int r, Graph g){
		
		this.r = r;
		
		/*
		 * Create a int list that will be returned later.
		 */
		List<Integer> ramseyList = new ArrayList<Integer>();
		
		
		
		if (r < 2){
			throw new RuntimeException("The r value has to be greater than or equal to 2");
		}
		
		/*
		 * Generate 2^2r-3 and store in a variable
		 */
		int size = (int) Math.pow(2, ((2 * r) - 3));
		
		/*
		 * Verify if the graph is 2^(2r - 3)
		 */
		if (adjMatrixEdges.length < size){
			throw new RuntimeException("The size of the graph must be at least 2^(2r-3).");
		}
		
		int vertices; //The vertices that will be picked from the graph.
		
		/*
		 * Create an array that will hold all the small v's of each big V
		 */
		int[] smallV; 
		
		/*
		 * Run a loop over the graph 
		 */
		for (int i = 1; i < size; i++){
			vertices = (int) Math.pow(2, (2 * r) - 2 - i); //This is the big V set size
			smallV = new int[vertices]; //Array that will contain each small v
			/*
			 * For loop to set each indice in smallV equal to j
			 */
			for (int j = 0; j < vertices; j++){
				smallV[j] = j;
				/*
				 * Test code to see what is stored inside the smallV array
				 */
			}
			

			
			/*
			 * Verify if the neighbors arraylist returned is greater than r
			 * if not then call nonNeighbors method
			 * Set smallV equal to the neighbors
			 */
			if (g.neighbors(i - 1).size() == r){
				for (int p = 0; p < g.neighbors(i - 1).size(); p++){
					ramseyList.set(p, g.neighbors(i - 1).get(p));
				}
			}
			
			if(g.neighbors(smallV[i - 1]).size() < r){
				for (int l = 0; l < g.neighbors(l).size(); l++){
					smallV[l] = g.nonNeighbors(smallV[l]).get(l);
				}
			}
			for (int l = 0; l < g.neighbors(i - 1).size(); l++){
				smallV = new int[g.neighbors(l).size()];
				smallV[l] = g.nonNeighbors(smallV[l]).get(l);
			}
			
			
			
		}
		
		/*
		 * Test code to see what is being stored in ramseyList
		 */
		//System.out.println(ramseyList);
		
		/*
		 * return the ramsey counter
		 */
		return ramseyList;
		
		
	}

	public static void main(String[] args) {

		// Make an arbritary graph with 5 vertices.
		Graph k5_graph = new Graph(5);

		// Create a K5 connected graph.
		k5_graph.addEdge(0, 1);
		k5_graph.addEdge(0, 2);
		k5_graph.addEdge(0, 3);
		k5_graph.addEdge(0, 4);
		k5_graph.addEdge(1, 2);
		k5_graph.addEdge(1, 3);
		k5_graph.addEdge(1, 4);
		k5_graph.addEdge(2, 3);
		k5_graph.addEdge(2, 4);
		k5_graph.addEdge(3, 4);

		// Works fine for adjacent methods and returns true.
		k5_graph.adjacent(0, 1);
		k5_graph.adjacent(0, 2);
		k5_graph.adjacent(0, 3);
		k5_graph.adjacent(0, 4);
		k5_graph.adjacent(1, 2);
		k5_graph.adjacent(1, 3);
		k5_graph.adjacent(1, 4);
		k5_graph.adjacent(2, 3);
		k5_graph.adjacent(2, 4);
		k5_graph.adjacent(3, 4);

		// Works fine, returns neighbors of selected vertex.
		k5_graph.neighbors(4);

		// Works fine, returns num of edges.
		k5_graph.numOfEdges();

		// isPlanar method works on k5 connected graph.
		k5_graph.isPlanar(k5_graph);

		// Empty graph object.
		Graph empty_Graph = new Graph(0);

		// isPlanar works on an empty graph
		empty_Graph.isPlanar(empty_Graph);

		// Normal graph object
		Graph normal_Graph = new Graph(5);

		// Add edges
		normal_Graph.addEdge(0, 1);
		normal_Graph.addEdge(1, 2);
		normal_Graph.addEdge(2, 3);
		normal_Graph.addEdge(3, 4);

		// isPlanar works on a normal graph.
		normal_Graph.isPlanar(normal_Graph);

		// Test the dfs method.
		normal_Graph.sortDegreeSequence(normal_Graph);

		// Test code for simple colouring
		normal_Graph.simpleColouring(2, normal_Graph);
		
		//Implement turnBooleanToInt method
		k5_graph.turnBooleanToInt(); //Works
		
		/*
		 * Test code to see if hamCycle runs
		 */
		//k5_graph.hamCycle(k5_graph);
		//normal_Graph.hamCycle(normal_Graph);
		
		/*
		 * Test code for sssp()
		 */
		k5_graph.sssp(k5_graph);
		
		/*
		 * Create a K6 graph to test ramseyCheck(int r)
		 */
		Graph k8Graph = new Graph(8);
		
		/*
		 * Complete k8 graph edges
		 */
		k8Graph.addEdge(0, 1);
		k8Graph.addEdge(0, 2);
		k8Graph.addEdge(0, 3);
		k8Graph.addEdge(0, 4);
		k8Graph.addEdge(0, 5);
		k8Graph.addEdge(1, 2);
		k8Graph.addEdge(1, 3);
		k8Graph.addEdge(1, 4);
		k8Graph.addEdge(1, 5);
		k8Graph.addEdge(2, 3);
		k8Graph.addEdge(2, 4);
		k8Graph.addEdge(2, 5);
		k8Graph.addEdge(3, 4);
		k8Graph.addEdge(3, 5);
		k8Graph.addEdge(4, 5);
		k8Graph.addEdge(0, 6);
		k8Graph.addEdge(0, 7);
		k8Graph.addEdge(1, 6);
		k8Graph.addEdge(1, 7);
		k8Graph.addEdge(2, 6);
		k8Graph.addEdge(2, 7);
		k8Graph.addEdge(3, 6);
		k8Graph.addEdge(3, 7);
		k8Graph.addEdge(4, 6);
		k8Graph.addEdge(4, 7);
		k8Graph.addEdge(5, 6);
		k8Graph.addEdge(5, 7);
		k8Graph.addEdge(6, 7);
		
		/*
		 * Test ramseyCheck(int r) on k6 graph, should return 3 vertices that form a monochromatic triangle
		 * Specifically here we want to check and see if R(3,3) returns 6 vertices.
		 */
		//k8Graph.ramseyCheck(3, k8Graph);
		
		/*
		 * Test code for non neighbors method
		 */
//		k8Graph.neighbors(0);
//		k8Graph.nonNeighbors(0);
	}
}