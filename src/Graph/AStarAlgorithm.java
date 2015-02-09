package Graph;

import java.util.ArrayList;

//Haider Khan
//AST Graph Theory
//4-2-14
//"I pledge that I have acted honorably"

public class AStarAlgorithm {

	private WeightedEdge[][] adjMatrixGraph;
	protected int[] distance;
	protected int[] visited;
	
	//Constructor
	public AStarAlgorithm(int v){
		
		//Create a very simple graph of WeightedEdges
		adjMatrixGraph = new WeightedEdge[v][v];
		
		//Create random weighted edges from start vertex to end vertex
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				int startVertex = i;
				int endVertex = j;
				adjMatrixGraph[i][j] = new WeightedEdge(startVertex, endVertex,
						(Math.random() * 1));
			}
		}
		
	}
	
	// This method will get the heuristic value
		public double getHeuristic(int endVertex, int startVertex) {

			// substract the heuristic from the start node to the goal node
			double weight;
			// Setup function
			weight = Math.abs((adjMatrixGraph[endVertex][0].getWeight()
					- adjMatrixGraph[startVertex][0].getWeight()));
			// return weight
			System.out.println(weight);
			return weight;
		}

		public double getMovement(int targetVertex, int startingVertex) {
			// substract the heuristic from the start node to the goal node
			double weight;
			// Setup function
			weight = Math.abs(adjMatrixGraph[targetVertex][startingVertex]
					.getWeight()
					- adjMatrixGraph[startingVertex][targetVertex].getWeight());
			// return weight
			return weight;
		}

//		public double aStarFunction() {
//
//			int startingVertex;
//			int endingVertex;
//
//			double totalPath;
//
//			for (int i = 0; i < adjMatrixGraph.length; i++) {
//				for (int j = 0; j < adjMatrixGraph[i].length; j++) {
//					totalPath += adjMatrixGraph[i][0].getHeuristic(i, 0);
//					
//				}
//			}
//
//		}
//	
	
	
	
	public static void main(String[] args){
		
		//Create a 10 by 10 weighted edge graph
		AStarAlgorithm testGraph = new AStarAlgorithm(10);
		//Works
		testGraph.getHeuristic(4, 3);
		
	}
	
}
