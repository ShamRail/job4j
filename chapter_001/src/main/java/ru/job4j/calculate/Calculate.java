package ru.job4j.calculate; 
 
/**
	* Calculate
	* @version 1.0
	* @since 05.07.2018
	* @author Rail Shamsemuhametov.
*/
 public class Calculate {
     /** Contain result of math operation*/
     private double result;

	/**
	* add.
	* @param first - first , second - second.
	* @return sum of two digits .
	*/

	public  void add(double first, double second) {
		this.result = first + second;
	}

    /**
     * sub.
     * @param first - first , second - second.
     * @return result of subtraction of two digits.
     */

	public  void sub(double first, double second) {
		this.result = first - second;
	}

    /**
     * mult.
     * @param first - first , second - second.
     * @return result of multiplication of two digits.
     */

	public  void mult(double first, double second) {
		this.result = first * second;
	}


    /**
     * div.
     * @param first - first , second - second.
     * @return result of division of two digits.
     */

	public  void div(double first, double second) {
		this.result = first / second;
	}

    /**
     *getResult.
     *@return
     */

	public double getResult() {
		return this.result;
	}
}