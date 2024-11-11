import java.util.Random;

class Main {
	public static void main (String args[]) {
		int[] max = {50,100,1000,5000,10000,50000,100000,500000,1000000};
		// *** Begin Simulation ************************
		Random generator = new Random();
		for (int i = 1; i < max.length; i++) {
			double s = 0;
			for (int n = 1; n < max[i]; n++) { // Generate max[i] runs.
				double r = generator.nextDouble();
				s = s + Math.exp(r); 
			}
			double Ivalue = s / (double) max[i]; // Approximate integral
			System.out.println(Ivalue);
		}
		// *** End Simulation ************************
	}
}
