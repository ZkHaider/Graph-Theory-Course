/*
 * Name: Haider Khan
 * Date: 4-19-14
 * Class: AST: Graph Theory
 * "I pledge that I have acted honorably" - Haider Khan
 */

package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class FlowGraph extends DirectedGraph {

	/*
	 * Create a weighted edge array to store the capacities
	 */
	private int[][] capacityMatrix;

	/*
	 * Create variable that represents the capacities
	 */
	private int capacity;

	/*
	 * Setup variables for sink and source
	 */
	protected int source;
	protected int sink;

	/*
	 * Variable for the flow of the flow graph
	 */
	@SuppressWarnings("unused")
	private int flow;

	/*
	 * Variable to be returned by the ford fulkerson algorithm
	 */
	private int[][] flowMatrix;

	/*
	 * Setup boolean array for DFS
	 */
	private boolean[] visited;
	
	/*
	 * Parent int[] array for storing augmenting paths
	 */
	private int[] parent;
	
	/*
	 * Create a variable for maxFlow, to be used in maxFlow() this will store
	 * the max flow value for us if we ever need it
	 */
	@SuppressWarnings("unused")
	private int maxFlow;

	public FlowGraph(int vertices) {
		super(vertices);
		// TODO Auto-generated constructor stub

	}

	
	/*
	 * Set the capacities on each edge
	 */
	public void setCapacities(int vertex1, int vertex2, int tempCapacity){
		
		this.capacity = tempCapacity;
		
		capacityMatrix = new int[numVertices][numVertices];
		
		capacityMatrix[vertex1][vertex2] = capacity;
		
	}
	
	public boolean findPath(FlowGraph g, int s, int t, int[] tempParent){
		
		this.parent = tempParent;
		this.source = s;
		this.sink = t;
				
		//Reinitialize parent everytime findPath() is executed
		for (int j = 0; j < parent.length; j++){
			parent[j] = -1;
		}
		
		visited = new boolean[parent.length];

		/*
		 * Had to look up queue class in order to implement this
		 */
		Queue<Integer> queueList = new LinkedList<Integer>();
		queueList.add(source);
		parent[source] = -1;
		visited[source] = true;
		
		while (!queueList.isEmpty()){
			int topInt = queueList.poll();
			
			for (int i = 0; i < parent.length; i++){
				if (!visited[i] && flowMatrix[topInt][i] > 0){
					visited[i] = true;
					parent[i] = topInt;
					
					if (i == topInt){
						return true;
					}
					
					//Add i to the list
					queueList.add(i);
				}
			}
		}
		
		//If sink is reached return true.
		return visited[sink];
		//return largest capacity
		
	}

	/*
	 * Ford Fulkerson Algorithm which will be utlilized in maxFlow()
	 */
	public int[][] maxFlow(int source, int sink, FlowGraph g) {

		this.source = source;
		this.sink = sink;

		// Mark this node as visited
		visited = new boolean[g.numVertices];
		visited[source] = true;

		flowMatrix = new int[g.numVertices][g.numVertices];
		
		/*
		 * Initialize residual graph
		 */
		int u, v;
		for (u = 0; u < g.numVertices; u++){
			for (v = 0; v < g.numVertices; v++){
				if (g.adjMatrixEdges[u][v])
					flowMatrix[u][v] = 1;
				else
					flowMatrix[u][v] = 0;
				if (g.adjMatrixEdges[v][u])
					flowMatrix[v][u] = 1;
				else
					flowMatrix[v][u] = 0;
			}
		}
		
		
		parent = new int[g.numVertices];
		
		maxFlow = 0;
		//System.out.println(maxFlow); //Test Code just to see if this method is executed this far.
		
		//while there is a path
		while (findPath(g, source, sink, parent)){
			
			int edgeFlow = Integer.MAX_VALUE;//set equal to infinity initially
			
			int tempSink = sink;
			
			//goes through and finds lowest capacity. Just go over entire path 
			for (v = sink; v != source; v = parent[v]){
				u = parent[v];
				edgeFlow = Math.min(edgeFlow, flowMatrix[parent[tempSink]][tempSink]);
				tempSink = parent[tempSink];
			}
			
			//We have generated maxFlow if we ever need to call it.
			maxFlow += edgeFlow;
			
			//second would all about updating lowest capacity.
			
			tempSink = sink;
			while (tempSink != source){
				flowMatrix[parent[tempSink]][tempSink] -= edgeFlow; //Update capacities
				//System.out.println(flowMatrix[parent[tempSink]][tempSink]); //Test Code
				flowMatrix[tempSink][parent[tempSink]] += edgeFlow; //Update capacities
				//System.out.println(flowMatrix[tempSink][parent[tempSink]]); //Test Code
				tempSink = parent[tempSink];
			}
			
		}
		
		//System.out.println(maxFlow); //Works and returns max flow for graph
		
		return flowMatrix;

	}

	public static void main(String[] args) {

		/*
		 * Create a simple flow graph object
		 */
		FlowGraph networkGraph = new FlowGraph(4);

		/*
		 * Create directed edges with capacities
		 */
		networkGraph.addEdge(0, 1);
		networkGraph.addEdge(0, 2);
		networkGraph.addEdge(1, 2);
		networkGraph.addEdge(1, 3);
		networkGraph.addEdge(2, 3);
		
		/*
		 * Initialize maximum capacities
		 */
		networkGraph.setCapacities(0, 1, 2);
		networkGraph.setCapacities(0, 2, 2);
		networkGraph.setCapacities(1, 3, 4);
		networkGraph.setCapacities(2, 3, 2);


		/*
		 * maxFlow on networkGraph
		 */
		networkGraph.maxFlow(0, 3, networkGraph);

	}
}
