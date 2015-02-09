//Name: Haider Khan
//Date: 2-21-14
//Class: AST: Graph Theory
//Instructor: Dr. Patterson
//"I pledge that I have acted honorably" - Haider Khan

package Graph;

public class Perceptron {
	
	//We want to create a variable which will represent the number of weighted edges
	//in the 2-dimensional array.
	protected int num_weighted_Edges;

	//Inside this class we want to create a data field which is a 
	//2-D array of WeightedEdges. Since the weightedEdges will be in 
	//double data type, we will create a double type 2-dimensional
	//array.
	protected WeightedEdge[][] weightedEdges; 
	
	
	//We set a double field named eta equal to 0.05.
	protected double eta = 0.05;

	//We initialize a constructor which only takes a parameter int n.
	public Perceptron(int n){
		
		//We want to create a new graph which will have n + 1 vertices
		//, where we also want vertex 0 to act like the output node
		//as in a neural network.
		this.num_weighted_Edges = n;
		
		//First we need to verify that n is a positive real number
		if (num_weighted_Edges < 0){
			throw new RuntimeException("You cannot have a perceptron of negative value");
		}
		else {
			//Test code for testing if this code works.
			System.out.println("A perceptron of " + num_weighted_Edges + " input nodes, and 1 output node was created");
		}
		
		//Now we create a graph object with "n = 1" number of vertices.
		weightedEdges = new WeightedEdge[num_weighted_Edges + 1][num_weighted_Edges + 1];
		
		//Create a for loop that will iterate the weightedEdges array.
		//We want to create the weighted edges from vertex 1 and not vertex 0
		//since vertex 0 will be the output node, so we set i = 1.
		for (int i = 0; i < weightedEdges.length; i++){
			for (int j = 0; j < weightedEdges[i].length; j++){
				//This will create a weighted edge in between [1][0]...[2][0]...[3][0]
				//The weighted edge will have a random value between -1 and 1 assigned to it.
				weightedEdges[i][0] = new WeightedEdge(i, 0, 1);
			}
		}
		
	}
	
	
	//This method will take the input nodes, do a quick verification check on it and
	//sum up the weights using the simple threshold function described in class to return
	//either a 1 or -1. 1 meaning fire, and -1 not firing. 
	public int run(int[] arrayXi){
		//So this method will act like the summation function. It will take the int parameters
		//you put into the parameter field and multiply it times the input nodes in the
		//weighted edge 2 d array.
		
		//Setup a summation counter.
		int sum = 0;
		
		if (arrayXi.length != (weightedEdges.length)){
			throw new RuntimeException("Array coming in has to equal the number of input nodes");
		}
		else {
			
				//Create a nested for loop which will iterate over the input nodes
			
				//We iterate the weights array and use the sum counter to sum up weights.
		for (int i = 1; i < arrayXi.length; i++){
		//This takes the weights and multiplies it times the value in the 
		//input nodes. The sum should equal greater than 0 or less than 0.
			sum += (int) ((weightedEdges[i][0].getWeight()) * arrayXi[i - 1]);
			//The above line of code takes the sum and adds it to the next value.
			}
		}
					
				
		
		//Test code to see what I am printing out.
		//System.out.println("The sum of the threshold function is " + sum);
		
		//If the sum is greater than 0, we fire the neuron by returning 1.
		if (sum > 0){
			//System.out.println(1); //test code
			return 1;
		}
		//Else we don't fire and return -1.
		else {
			//System.out.println(-1); //test code
			return -1;
		}
		
	}
	
	
	//This will be a train method which will train the nodes to update.
	public void train(int[][] trainArray){
		
		
		//Setup a variable which will represent actual values for run(), and
		//that will do the updates as well
		int actual;
		int theoretical;
		double updateFunction;
		double updatedWeight;
		
		
		
		//First we have to verify if the incoming parameter array has the same number of 
		//nodes as the weighted_edges array.
		for (int m = 0; m < trainArray.length; m++){
		if (trainArray[m].length != (weightedEdges.length)){
			throw new RuntimeException("This array should have the same number of n nodes as the parameter of the constructor");
		}}
		for (int i = 0; i < trainArray.length; i++){
			//Execute run method on each row.
			actual = run(trainArray[i]); //Run method working properly
			//System.out.println(actual); //Test code to see if the 2-d int array trainingSet is working
			for (int j = 0; j < trainArray[i].length; j++){
				//System.out.println(trainArray[i][j]); //Shows correct numbers
				//Here we take the first value in each row of the 2-Dimensional array
				//to be the output value to be used.
				theoretical = trainArray[i][0];
				updateFunction = ((double)(weightedEdges[i + 1][0].getWeight()) + 
						(eta * (theoretical - actual)*trainArray[i][j]));
				updatedWeight = weightedEdges[i + 1][0].getWeight() + updateFunction;
				//System.out.println("Updated weight for node " + j + " is " + updatedWeight); //Test Code, works fine.
				
				//Now we put the updated weights into the new array.
				weightedEdges[i][j] = new WeightedEdge(i, j, updatedWeight);
				//System.out.println(weightedEdges[i][j].getWeight()); test code works fine.
			}
		}
	}
	
	//A method which will take a 2-d array parameter. It will return a double value representing
	//the sum of the error. It should function exactly as the train method but should NOT update
	//the weights of the network.
	public double errorOn(int[][] errorArray){
		
		//Setup a variable to represent the double return type.
		double error = 0;
		double actual;
		int theoretical;
		
		if (errorArray.length != (weightedEdges.length - 1)){
			throw new RuntimeException("Incoming Parameter has to have same" +
					" number of nodes as contructor array.");
		}
		
		//Going to use Arctan Error here instead of weighted sum error.
		for (int k = 0; k < errorArray.length; k++){
			//Setup actual value again.
			actual = run(errorArray[k]);
			for (int l = 0; l < errorArray[k].length; l++){
				//This will set the theoretical value to the first index value.
				theoretical = errorArray[k][0];
				error += (theoretical - actual) * (theoretical - actual);
				//System.out.println(error); //Test code to see what is going on inside here
				}
			}
		System.out.println(error); //Test code to see if error method is working.
		
		return error;
	}
	
	
	//Test code to see what the values in my 2-dimensional weighted edge array is.
	public void printWeights(){
			
		WeightedEdge weights = new WeightedEdge(0, 0, 0);
		
			for (int i = 0; i < weightedEdges.length; i++){
				for (int j = 0; j < weightedEdges[i].length; j++){
					weightedEdges[i][0] = weights;
				}
			}
			
			System.out.println(weights.toString());
		
			
			
	}
	
	//Main method which will stimulate the artificial neuron (perceptron, which is the
	//simplest type of neuron in an artificial network). 
	public static void main(String[] args){
		
		//Create a test perceptron with a user defined set number of input nodes.
		Perceptron perceptron = new Perceptron(7);
		
		//Create a weight object that creates an edge between vertices 1 and 2
		//with a weight of 1.5
		WeightedEdge weight = new WeightedEdge(1, 2, 1.5);
		
		//These methods work fine.
		weight.getStart();
		weight.getEnd();
		weight.setWeight(2.0);
		
		//Test to see if the run class works. (Previously was giving a null pointer, but
		//fixed now)
		int[] test_Xi_Array = {1, 1, 1, 1, -1, -1, 1, 1};
		
		//Tested and works to return output of 1 or -1. Also catches exceptions.
		//Prints the sum.
		perceptron.run(test_Xi_Array); //This works fine and returns an output of 1
		
		//New testing array for run method to see if it prints out -1.
		int[] test_Xi_Array_2 = {1, -1, -1, 1, -1, 1, 1, 1};
		
		//Test out run method to see if it prints out negative one.
		perceptron.run(test_Xi_Array_2); //This works and run method prints out -1.
		
		
		//Testing a 2-d array to see if the train method works.
		int[][] trainingSet = {{1, -1, -1, -1, -1, -1, -1, 1}, {1, -1, -1, 1, -1, 1, 1, 1}, 
				{-1, 1, 1, -1, -1, 1, -1, 1}, {1, -1, -1, -1, 1, -1, 1, 1}, 
				{1, 1, 1, 1, 1, -1, 1, 1}, {1, -1, 1, 1, 1, -1, 1, 1}, 
				{1, 1, 1, 1, 1, -1, -1, 1}};
		
		//Works and catches exceptions.
		perceptron.train(trainingSet);
		
		//Test code to find error in array.
		//Go back in and create a for loop and then terminate. 
		perceptron.errorOn(trainingSet);
		
		//This loop sets a condition that if the error on the neural network
		//is greater than 0.1, it will train the neural network to set the error
		//less than 0.1.
		while (perceptron.errorOn(trainingSet) >= 0.1){
			if (perceptron.errorOn(trainingSet) < 0.1){
				System.out.println("Machine learned");
			}
			perceptron.train(trainingSet);
		}
		
		
	}
	
}
