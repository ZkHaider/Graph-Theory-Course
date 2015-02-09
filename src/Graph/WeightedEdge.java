//Name: Haider Khan
//Date: 2-21-14
//Class: AST: Graph Theory
//Instructor: Dr. Patterson
//"I pledge that I have acted honorably" - Haider Khan

package Graph;

//To create the weighted edge class we have to import the Edge class and extend this class to 
//inherit the methods inside the Edge class. 

public class WeightedEdge extends Edge{
	
	//Create a protected variable for the edgeweight.
	protected double edgeWeight;
	

	//Constructor
	public WeightedEdge(int v1, int v2, double weight) {
		super(v1, v2);
		
		//Now we want to devise a way to store the weight of the edge.
		//We want to set the protected double type field equal to the double parameter here in 
		//the constructor.
		//So simply we set the double type field equal to weight.
		edgeWeight = weight;
	}

	
	//Create a getter method
	public double getWeight(){
		//This method must have a double return type so we can get the
		//weight for the edge which is stores in the field outside
		//the method. 
		return edgeWeight;
	}
	
	//Create a setter method
	public void setWeight(double edgeWeight){
		
		//With this we set the double field outside this method to equal the 
		//parameter value in the method.
		this.edgeWeight = edgeWeight;
	}


	public double getHeuristic(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

}
