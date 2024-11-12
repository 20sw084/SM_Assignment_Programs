public class Main {
    static final double LAMBDA = 1.0;
    static final double MU = 2.5;
    static final int N = 50;

    public static void main(String[] args) {
        int na = 0, nd = 0;
        double n = 0, ta = generateExponential(LAMBDA), td = Double.MAX_VALUE;
        double totTi = 0, totTu = 0, totalWaitTime = 0;

        while (nd < N) {
            if (ta < td) {
                // Arrival event
                n = ta;
                na++;
                totTi += ta;
                ta = n + generateExponential(LAMBDA);
                if (nd == na - 1) {
                    td = n + generateExponential(MU);
                    totTu += td - n;
                }
            } else {
                // Departure event
                n = td;
                nd++;
                if (na > nd) {
                    td = n + generateExponential(MU);
                    totTu += td - n;
                    totalWaitTime += td - n;
                } else {
                    td = Double.MAX_VALUE;
                }
            }
        }

        double meanInterarrival = totTi / na;
        double meanService = totTu / nd;
        double meanWait = totalWaitTime / nd;

        System.out.println("Mean Interarrival Time: " + meanInterarrival);
        System.out.println("Mean Service Time: " + meanService);
        System.out.println("Mean Waiting Time: " + meanWait);
    }

    public static double generateExponential(double rate) {
        return -Math.log(1 - Math.random()) / rate;
    }
}
