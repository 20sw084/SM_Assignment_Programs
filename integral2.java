import java.util.Random;

class Main {
	public static void main (String args[]) {
		int[] max = {50,100,1000,5000,10000,50000,100000,500000,1000000};
		// *** Begin Simulation ************************
		Random generator = new Random();
		for (int i = 1; i < max.length; i++) {
			double s = 0;
			double a = 1;
			double b = 3;
			for (int n = 1; n < max[i]; n++) { // Generate max[i] runs.
				double r = generator.nextDouble();
				double y = a + (b - a) * r;
				s = s + Math.exp(y); // g(y) = exp(y)
			}
			double Ivalue = (b - a) * s / (double) max[i]; // Approximate integral
			System.out.println(Ivalue);
		}
		// *** End Simulation ************************
	}
}


/*
Example 16.2: To use this approach to obtain approximations to the integral 
∫
1
3
𝑒
𝑥
 
𝑑
𝑥
∫ 
1
3
​
 e 
x
 dx using the previous Java program, it suffices to modify the program as follows.

This means that the code is a modification of a Monte Carlo method to estimate the integral 
∫
1
3
𝑒
𝑥
 
𝑑
𝑥
∫ 
1
3
​
 e 
x
 dx. The approach involves generating random values within the range [1, 3], evaluating the function 
𝑒
𝑥
e 
x
  at those points, and using the average value to approximate the integral.
*/
