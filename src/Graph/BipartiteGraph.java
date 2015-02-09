//Name: Haider Khan
//Date: 2-9-14
//Class: AST Graph Theory

package Graph;

import java.util.ArrayList;

public class BipartiteGraph extends Graph{
	
	//create a new 2-d array of booleans for partitions.
	public int[][] preferencesArray;
	
	//Constructor of BipartiteGraph class
	public BipartiteGraph(int vertices) {
		super(vertices);
	}
	
	
	//We override the existing addEdge method to allocate that we cannot add edges from vertices in the same partition,
	// for this specific bipartite graph. 
	public void addEdge(int vertex_add_1, int vertex_add_2){
		
		//Define an if statement indicating that if vertex_
		if ((vertex_add_1%2 == 0 && vertex_add_2%2 != 0) || (vertex_add_1%2 != 0 && vertex_add_2%2 == 0)){
			adjMatrixEdges[vertex_add_1 - 1][vertex_add_2 - 1] = true;
			adjMatrixEdges[vertex_add_2 - 1][vertex_add_1 - 1] = true;
		}
		else {
			throw new RuntimeException("You cannot create an edge between vertices in the same partition.");
		}
		
	}
	
	public int[] setPreferences(int vertex, int[] preferences){
		
		//go through preference array and make sure that each number 
		//use if statements.
		
		for (int i = 0; i < preferences.length; i++){
			if (preferences[i] > preferencesArray.length){
				throw new RuntimeException("Cannot enter vertices greater than vertices in Bipartite Graph");
			}
			//If the if condition above is
			//not fulfilled then store the vertices into the preferencesArray and then be able to recall them. 
			preferencesArray[vertex] = preferences;

		}
		
		//Recall the preferences array of stored preferences
		return preferences;
	}
	
	public ArrayList<Integer> stableMatching(){
		//Define a stable matching array list that will return a pair of ints for the bipartite graph which is a 
		//stable matching for the vertices in the bipartite graph.
		ArrayList<Integer> stable_Matching = new ArrayList<Integer>();
		
		//Now we want to initialize all edges to false, so we can then create a stable matching. 
		//This will require a nested for loop to iterate through the 2-D array to set the edges to
		//false.
		
		for (int i = 0; i < adjMatrixEdges.length; i++){
			for (int j = 0; j < adjMatrixEdges[i].length; j++){
				adjMatrixEdges[i][j] = false;
			}
		}
		
		//Now that we have initialized the array to false, all vertices are open to being matched. 
		//We do the following by using a while loop
		//The while loop is a loop for when a vertex is free in the list then we iterate the following code.
		//We will imagine (in our minds) that the even numbered vertices are men, and the odd numbered vertices 
		//are female (really they can be anything).
		//So while a an even numbered is free if the setPreferences method is called upon then the while loop is initiated. 
		
		
		//To begin we would need to iterate through the adj_Matrix_Edges 2-d boolean array
		//and execute the following code iff there is an even numbered vertex which is free
		//and that, that specific even numbered vertex's preferences list is non empty preferences list.
		for (int i = 0; i < adjMatrixEdges.length; i++){
			for (int j = 0; j < adjMatrixEdges[i].length; j++){
				while (adjMatrixEdges[i][j] == false || adjMatrixEdges[i][j] == true && preferencesArray[i] != null){
					
					//Now we need to define a variable which will be the first priority vertex of
					//a specific vertex.
					int first_Priority = preferencesArray[i][0];
					
					//Define even and odd vertices.
					int even_Vertices = 0;
					int odd_Vertices = 0;
					
					//Now run a for loop to add increments into the even and odd vertices which represent the
					//different partitions for bipartite graph.
					for (int k = 0; k < numVertices; k++){
						if (numVertices%2 == 0){
							even_Vertices++;
						}
						else {
							odd_Vertices++;
						}
					}
					
					//Now we need to define another int variable that could could have an edge to first_Priority
					//but is preferred less than i.
					if (j != i){
						adjMatrixEdges[i][first_Priority] = false;
					}
					
					//Assign those two specific edges for the best stable matching for the set of preferences.
					addEdge(i, first_Priority);
					
				}
			}
			
		}
		return stable_Matching;
	}
}
