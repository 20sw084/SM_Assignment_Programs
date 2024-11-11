import java.util.Random;

class ChiSquare {
    public static void main (String args[]) {
        Random generator = new Random();
        int n = 1000; int k = 10; double chiSquare = 0;
        int [] count = new int[k];

        for (int i = 0; i < n; i++) {
            // Generate and partition 1000 random numbers
            int subinterval = generator.nextInt(k);
            count[subinterval]++;
        }

        for (int i = 0; i < k; i++) {
            // Form chi-square statistic
            chiSquare = chiSquare + (count[i] - n/k) * (count[i] - n/k);
        }
        chiSquare = chiSquare / (n / k);

        for (int i = 0; i < k; i++) {
            // Print results
            System.out.println("Count[" + i + "] = " + count[i]);
        }
        System.out.println("chi-Square = " + chiSquare);
    }
}
