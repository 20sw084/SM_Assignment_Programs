import java.util.Random;

public class JoeColledgeSimulation {

    public static void main(String[] args) {
        double pEnter = 0.40; // Probability that Joe enters a game
        double pNotEnter = 1 - pEnter; // Probability that Joe does not enter a game

        // Part (a) - Probability that the first game Joe enters is the fourth game
        double probabilityFirstInFourthGame = Math.pow(pNotEnter, 3) * pEnter;
        System.out.println("Probability that Joe enters the first time in the fourth game: " + probabilityFirstInFourthGame);

        // Part (b) - Probability that Joe plays in no more than two of the first five games
        int nGames = 5;
        int maxPlays = 2;
        double probabilityNoMoreThanTwoGames = 0;

        // Calculate binomial probability for playing in 0, 1, or 2 games
        for (int k = 0; k <= maxPlays; k++) {
            probabilityNoMoreThanTwoGames += binomialProbability(nGames, k, pEnter);
        }
        
        System.out.println("Probability that Joe plays in no more than two of the first five games: " + probabilityNoMoreThanTwoGames);
    }

    // Function to calculate binomial probability
    public static double binomialProbability(int n, int k, double p) {
        return combination(n, k) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    // Function to calculate combinations (n choose k)
    public static long combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // Function to calculate factorial
    public static long factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
