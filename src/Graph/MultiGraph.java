//Name: Haider Khan
//Date: 2-9-14
//Class: AST Graph Theory
//Homework 2

package Graph;

public class MultiGraph extends Graph{
	
	//In the multigraph we need to override the addEdge, and removeEdge methods in order to allow self edges between 
	//the same vertices. So to allow self loops between adj_Matrix_Edges[i][i] or between adj_Matrix_Edges[j][j]. 
	
	//Since we are doing this we do not need to alter the addVertex or removeVertex. Those will stay the same,
	//however we do need to alter the addEdge(), removeEdge(), adjacent(), averageDegree(), and diameter() methods.
	
	public MultiGraph(int vertices) {
		super(vertices);
		
		
		//Since we want to alter the underlying structures of the 2-D array we have built in the Graph class
		//we have to use an acceptable way to convert the boolean values to int values.
		//to do this we have to iterate over the 2-dimensional array and then for each boolean value 
		//we have to convert to an int value.
		
		for (int i = 0; i < adjMatrixEdges.length; i++){
			for (int j = 0; j < adjMatrixEdges[i].length; j++){
				//Converts the boolean value to either a 1 for true or 0 for false.
				int myInt = adjMatrixEdges[i][j] ? 1:0;
			}
		}
		
		//Now we have a 2-dimensional int array for the boolean 2-dimensional adjacency we setup earlier. 
	}
	
	public boolean adjacent(int i, int j) {

		if (adjMatrixEdges[i][j] == true || adjMatrixEdges[j][i] == true ||
				adjMatrixEdges[i][i] == true || adjMatrixEdges[j][j] == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addEdge(int vertex_add_1, int vertex_add_2) {

		//Here we override the addEdge method to allow edges between two vertices even they are the same vertex.
		if (adjMatrixEdges[vertex_add_1][vertex_add_2] == false) {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
			adjMatrixEdges[vertex_add_2][vertex_add_1] = true;
		} else {
			adjMatrixEdges[vertex_add_1][vertex_add_2] = true;
			adjMatrixEdges[vertex_add_2][vertex_add_1] = true;
		}
	}
	
	public void removeEdge(int vertex_remove_1, int vertex_remove_2) {

		//Here we override the removeEdge method to allow edges to be removed between two vertices
		//even if those two vertices are self loops in the multi graph class. 
		if (adjMatrixEdges[vertex_remove_1][vertex_remove_2] == true) {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
			adjMatrixEdges[vertex_remove_2][vertex_remove_1] = false;
		} else {
			adjMatrixEdges[vertex_remove_1][vertex_remove_2] = false;
			adjMatrixEdges[vertex_remove_2][vertex_remove_1] = false;
		}
	}
	
	//Include self loops in the averageDegree method. 
	public double averageDegree() {

		double edgeCounter = 0;

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				// *** We include self loops for the multigraph by adding these extra conditions ***
				if (adjMatrixEdges[i][j] || adjMatrixEdges[i][i] || adjMatrixEdges[j][j]) {
					// this logical expression adds up the count of the true
					// statements, in graph theory this is adding up the
					// degree of that specific vertex.
					edgeCounter++;
				}
			}
		}
		//Here neighbor count is the number of edges as well.
		edgeCounter = 2 * (edgeCounter - 1) / numVertices;
		return edgeCounter;
	}
	
	
	
	//We override the diameter() method to return the diameter of the multi graph class. 
	public int diameter() {

		// Setup a counter which will count the number of true elements in a row
		// of the 2-d array
		int true_Counter = 0;

		// Setup a diameter variable.
		int diameter;

		// setup a for loop system to iterate through the array.

		for (int i = 0; i < adjMatrixEdges.length; i++) {
			for (int j = 0; j < adjMatrixEdges[i].length; j++) {
				//*** We include self loop conditions here ***
				if (adjMatrixEdges[i][j] == true || adjMatrixEdges[j][i] == true ||
						adjMatrixEdges[i][i] == true || adjMatrixEdges[j][j] == true) {
					true_Counter++;
				}
			}
		}

		diameter = true_Counter / 2;
		return diameter;

	}
	
	
	//We create a new method in the multi graph class called to toGraph() to convert the multi graph
	//to a simple graph. Therefore we need to make sure that there can be no hyperedges in the graph.
	//or self loops. 
	public Graph toGraph(){
		
		//We define a new graph with the set number of vertices.
		Graph simple_Graph = new Graph(numVertices);
		
		//Now that we have defined a new graph which will be the simple graph,
		//we want to modify the adjacency matrix to not include self edges. 
		//This can be done by creating a nested for loop which will iterate inside the 2-D
		//array to be modified.
		
		for (int i = 0; i < adjMatrixEdges.length; i ++){
			for (int j = 0; j < adjMatrixEdges[i].length; j++){
				//This if statement indicates that if there is a self loop then
				//it should be set to false, or that, that specific edge should be removed. 
				if (adjMatrixEdges[i][i] == true || adjMatrixEdges[j][j] == true){
					adjMatrixEdges[i][i] = false;
					adjMatrixEdges[j][j] = false;
				}
			}
		}
		
		//Set the new graph which will be a simple graph equal to the modified adj_Matrix_Edges 2-D array.
		simple_Graph.equals(adjMatrixEdges);
		
		return simple_Graph;
	}

}
