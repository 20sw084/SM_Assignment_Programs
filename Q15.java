import java.util.Random;

public class Q15 {
    public static void main(String[] args) {
        double lambda = 0.5; // Mean of the Poisson distribution
        int simulations = 100000; // Number of simulations
        Random random = new Random();

        int countLessThan2 = 0;
        int countMoreThan2 = 0;
        int countNoAccidentsIn3Weeks = 0;

        for (int i = 0; i < simulations; i++) {
            // Simulate number of accidents in one week
            int accidentsInWeek = simulatePoisson(lambda, random);

            // Part (a)(i): Check for less than 2 accidents
            if (accidentsInWeek < 2) {
                countLessThan2++;
            }

            // Part (a)(ii): Check for more than 2 accidents
            if (accidentsInWeek > 2) {
                countMoreThan2++;
            }

            // Part (b): Simulate accidents over a 3-week period
            int totalAccidentsIn3Weeks = 0;
            for (int j = 0; j < 3; j++) {
                totalAccidentsIn3Weeks += simulatePoisson(lambda, random);
            }
            if (totalAccidentsIn3Weeks == 0) {
                countNoAccidentsIn3Weeks++;
            }
        }

        // Calculate probabilities
        double probabilityLessThan2 = (double) countLessThan2 / simulations;
        double probabilityMoreThan2 = (double) countMoreThan2 / simulations;
        double probabilityNoAccidentsIn3Weeks = (double) countNoAccidentsIn3Weeks / simulations;

        // Print results
        System.out.println("Probability of less than 2 accidents in a week: " + probabilityLessThan2);
        System.out.println("Probability of more than 2 accidents in a week: " + probabilityMoreThan2);
        System.out.println("Probability of no accidents in a 3-week period: " + probabilityNoAccidentsIn3Weeks);
    }

    // Function to simulate a Poisson random variable
    private static int simulatePoisson(double lambda, Random random) {
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);

        while (p > expLambda) {
            k++;
            p *= random.nextDouble();
        }

        return k - 1;
    }
}
