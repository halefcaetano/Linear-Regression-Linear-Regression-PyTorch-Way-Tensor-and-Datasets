import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;

public class PiCalculation {
    public static double calculate() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // List to store results from each thread
        List<Future<Long>> results = new ArrayList<>();

        // Total points to simulate
        long totalPoints = 10_000_000;

        // Split points among 4 threads
        long pointsPerThread = totalPoints / 4;

        // Submit simulation tasks to the executor
        for (int i = 0; i < 4; i++) {
            Simulation simulation = new Simulation(pointsPerThread);
            Future<Long> future = executor.submit(simulation);
            results.add(future);
        }

        // Collect results and calculate pi
        long totalPointsInsideCircle = 0;
        for (Future<Long> future : results) {
            totalPointsInsideCircle += future.get();
        }

        // Shutdown the executor
        executor.shutdown();

        double pi = (double) totalPointsInsideCircle / totalPoints * 4;
        return pi;
    }


    public static void main(String[] args) throws Exception {
        // run this to test your code!
        System.out.println(calculate());
    }
}