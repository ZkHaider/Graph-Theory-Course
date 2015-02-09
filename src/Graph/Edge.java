package Graph;

//New class Edge is created
	public class Edge{
		
		//Set two int data field variables inside the edge class
		protected int start_Vertex;
		protected int end_Vertex;
		
		//A constructor created to return 2 data fields which are the starting and end points of an edge.
		public Edge(int v1, int v2){
			start_Vertex = v1;
			end_Vertex = v2;
		}
		
		//A get method which will return the starting vertex of an edge
		public int getStart(){
			return start_Vertex;
		}
		
		//Another get method which will return the end vertex of an edge selected
		public int getEnd(){
			return end_Vertex;
		}
		
	}
