package Graph;

public class Activation {

	//Sigmoid activation function
		public static double sigmoid(double x) {
			return (1.0 / (1 + Math.pow(Math.E, -x)));
		}
		
		// Hyperbolic Tan Activation Function
		public static double hyperTanFunction(double x) {
			return (Math.pow(Math.E, x) - Math.pow(Math.E, -x)) / (Math.pow(Math.E, x) + Math.pow(Math.E, -x));
		}
	
	public static void main(String[] args){
		
		double x = 0.7;
		
		System.out.println(hyperTanFunction(x));
		
	}
	
}
