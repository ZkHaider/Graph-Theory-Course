/*
 * Name: Haider Khan
 * Date: 5/3/14
 * "I pledge that I have acted honorably" - Haider Khan
 * Homework 9
 */

package Graph;

import java.util.List;

/*
 * New class called independence that will have 3 data parts to it:
 * (i) Two indices (attributes we're looking at)
 * (ii) set of indices (for the attributes to condition upon)
 */



@SuppressWarnings("unused")
public class Independence {

	/*
	 * Setup private variables
	 */
	private int indice1;
	private int indice2;
	private int[] conditioningSet;
	private int index;
	private int conditional;
	
	public Independence(int indice1, int indice2, int[] conditioningSet){
		
		this.indice1 = indice1;
		this.indice2 = indice2;
		this.conditioningSet = conditioningSet;
		
	}
	
	public int getIndice1() {
		return indice1;
	}
	

	public int getIndice2() {
		return indice2;
	}

	public int[] getConditioningSet() {
		return conditioningSet;
	}
	

	public int getIndex() {
		return index;
	}
	
	public int getConditional() {
		return conditional;
	}
	
}
