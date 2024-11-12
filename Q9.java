public class Q9 {
    static final double LAMBDA = 1.0;
    static final double MU = 2.5;
    static final int N = 50;

    public static void main(String[] args) {
        double currentTime = 0; // Internal clock time
        int numberOfArrivals = 0; // Count of arrivals
        int numberOfDepartures = 0; // Count of departures
        double nextArrivalTime = generateExponential(LAMBDA); // Time of the next arrival
        double nextDepartureTime = Double.MAX_VALUE; // Time of the next departure (initially infinity)
        double totalInterarrivalTime = 0; // Running total of interarrival times
        double totalServiceTime = 0; // Running total of service times
        double totalWaitTime = 0; // Total time spent waiting in the queue

        // Run the simulation until all N customers are served
        while (numberOfDepartures < N) {
            if (nextArrivalTime <= nextDepartureTime) {
                // Process arrival event
                currentTime = nextArrivalTime;
                numberOfArrivals++;
                totalInterarrivalTime += nextArrivalTime - (numberOfArrivals > 1 ? currentTime : 0);

                // Schedule the next arrival
                nextArrivalTime = currentTime + generateExponential(LAMBDA);

                // If the server is idle, schedule a departure
                if (numberOfArrivals - numberOfDepartures == 1) {
                    double serviceTime = generateExponential(MU);
                    totalServiceTime += serviceTime;
                    nextDepartureTime = currentTime + serviceTime;
                }
            } else {
                // Process departure event
                currentTime = nextDepartureTime;
                numberOfDepartures++;

                // Schedule the next departure if there are customers in the queue
                if (numberOfArrivals > numberOfDepartures) {
                    double serviceTime = generateExponential(MU);
                    totalServiceTime += serviceTime;
                    nextDepartureTime = currentTime + serviceTime;
                } else {
                    nextDepartureTime = Double.MAX_VALUE; // No more departures if the queue is empty
                }

                // Update the total wait time
                totalWaitTime += currentTime - (numberOfDepartures <= 1 ? 0 : nextDepartureTime);
            }
        }

        // Calculate and display terminal statistics
        double meanInterarrivalTime = totalInterarrivalTime / numberOfArrivals;
        double meanServiceTime = totalServiceTime / numberOfDepartures;
        double meanWaitTime = totalWaitTime / numberOfDepartures;

        System.out.println("Mean Interarrival Time: " + meanInterarrivalTime);
        System.out.println("Mean Service Time: " + meanServiceTime);
        System.out.println("Mean Time Spent in Queue: " + meanWaitTime);
    }

    // Function to generate exponentially distributed random times
    public static double generateExponential(double rate) {
        return -Math.log(1 - Math.random()) / rate;
    }
}
