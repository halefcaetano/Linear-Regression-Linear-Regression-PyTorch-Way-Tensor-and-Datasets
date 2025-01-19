import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This task generates the given number of points and
 * counts how many of them are inside the circle.
 */
public class Simulation implements Callable<Long> {
    private final long points;

    public Simulation(long points) {
        this.points = points;
    }

    @Override
    public Long call() {
        long pointsInsideCircle = 0;
        for (long i = 0; i < points; i++) {
            // Randomly generate a point in the [0, 2] x [0, 2] square
            double x = ThreadLocalRandom.current().nextDouble(0, 2);
            double y = ThreadLocalRandom.current().nextDouble(0, 2);
            // Check if the point is inside the unit circle
            if (Math.sqrt((x - 1) * (x - 1) + (y - 1) * (y - 1)) <= 1) {
                pointsInsideCircle++;
            }
        }
        return pointsInsideCircle;
    }
}
