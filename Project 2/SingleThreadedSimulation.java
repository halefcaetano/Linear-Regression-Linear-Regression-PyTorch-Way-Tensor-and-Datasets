import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;


public class SingleThreadedSimulation {

    public static void main(String[] args) {
        System.out.println("pi=" + calculate());
    }

    public static double calculate() {
        long totalPoints = 10_000_000;
        long pointsInsideCircle = simulationTask(totalPoints);
        double pi = pointsInsideCircle / (double) totalPoints * 4;
        return pi;
    }

    public static long simulationTask(long points) {
        long pointsInsideCircle = 0;
        for (long i = 0; i < points; i++) {
            // randomly generate a point but ensure it is inside the square.
            double x = ThreadLocalRandom.current().nextDouble(0, 2);
            double y = ThreadLocalRandom.current().nextDouble(0, 2);
            if (Math.sqrt((x - 1) * (x - 1) + (y - 1) * (y - 1)) <= 1) {
                // this point lands inside the circle
                pointsInsideCircle++;
            }
        }
        return pointsInsideCircle;

    }
}
