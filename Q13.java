import java.util.Random;

public class Q13 {
    public static void main(String[] args) {
        double passRate = 0.7; // Probability of passing each subject/practical
        int totalSubjects = 5; // Total number of subjects and practicals
        int simulations = 100000; // Number of simulations for accuracy
        Random random = new Random();

        int countPassMoreThanFail = 0;

        for (int i = 0; i < simulations; i++) {
            int passCount = 0;

            // Simulate passing/failing each subject/practical
            for (int j = 0; j < totalSubjects; j++) {
                if (random.nextDouble() < passRate) {
                    passCount++;
                }
            }

            // Check if the number of passed subjects/practicals is more than failed
            if (passCount > totalSubjects / 2) {
                countPassMoreThanFail++;
            }
        }

        // Calculate the probability
        double probabilityPassMoreThanFail = (double) countPassMoreThanFail / simulations;

        // Print the result
        System.out.println("Probability that 19SW will pass more subjects/practicals than they fail: " + probabilityPassMoreThanFail);
    }
}
