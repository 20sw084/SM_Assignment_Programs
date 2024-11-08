import java.util.Random;

public class InventorySimulation {
    public static void main(String[] args) {
        int beginningInventory = 3000; // Starting inventory
        int currentInventory = beginningInventory;
        int maxReplenishmentLevel = 4000; // Maximum level to replenish up to
        int orderCost = 50; // Cost of placing an order
        int shortageCostPerThousand = 10; // Shortage cost per thousand units
        int weeks = 20; // Simulation period in weeks

        // Demand and lead time probability data
        int[] demandOptions = {0, 1000, 2000, 3000}; // Demand in thousands
        double[] demandProbabilities = {0.2, 0.4, 0.3, 0.1}; // Probability for each demand
        int[] leadTimeOptions = {2, 3, 4}; // Lead time in weeks
        double[] leadTimeProbabilities = {0.3, 0.4, 0.3}; // Probability for each lead time

        // Random digits for demand and lead time
        int[] demandRandomDigits = {31, 70, 53, 86, 32, 78, 26, 64, 45, 12, 99, 52, 43, 84, 38, 40, 19, 87, 83, 73};
        int[] leadTimeRandomDigits = {29, 83, 58, 41, 13};

        int totalOrderingCost = 0;
        int totalShortageCost = 0;
        int totalEndingInventory = 0;
        int leadTimeIndex = 0;
        int replenishmentWeek = -1; // When an order will arrive (-1 means no order is in transit)

        // Loop for 20 weeks
        for (int week = 0; week < weeks; week++) {
            // Get demand for the current week
            int demand = getRandomValue(demandRandomDigits[week], demandOptions, demandProbabilities);
            currentInventory -= demand;

            // Check if we need to place an order
            if (currentInventory <= 2000 && replenishmentWeek == -1) {
                int orderAmount = maxReplenishmentLevel - currentInventory;
                currentInventory += orderAmount; // Immediately place an order to bring inventory to max level
                totalOrderingCost += orderCost;
                int leadTime = getRandomValue(leadTimeRandomDigits[leadTimeIndex++], leadTimeOptions, leadTimeProbabilities);
                replenishmentWeek = week + leadTime; // Set the week the order will arrive
            }

            // Replenish inventory if an order arrives this week
            if (week == replenishmentWeek) {
                replenishmentWeek = -1; // Reset the replenishment status
            }

            // Calculate shortage cost if inventory is below 0
            if (currentInventory < 0) {
                totalShortageCost += Math.abs(currentInventory) / 1000 * shortageCostPerThousand;
                currentInventory = 0; // Inventory cannot be negative
            }

            // Add current week's ending inventory to total
            totalEndingInventory += currentInventory;
        }

        // Calculate averages
        double averageEndingInventory = (double) totalEndingInventory / weeks;
        double totalAverageWeeklyCost = totalOrderingCost + totalShortageCost;

        // Print results
        System.out.println("Average ending inventory: " + averageEndingInventory);
        System.out.println("Total average weekly cost: Rs. " + totalAverageWeeklyCost);
    }

    // Function to get a random value based on given probabilities
    private static int getRandomValue(int randomDigit, int[] options, double[] probabilities) {
        double cumulativeProbability = 0.0;
        for (int i = 0; i < options.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomDigit < cumulativeProbability * 100) {
                return options[i];
            }
        }
        return options[options.length - 1]; // Return the last option as a fallback
    }
}
