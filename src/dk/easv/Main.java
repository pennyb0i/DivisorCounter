package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(1);
        DivisorCouter task1 = new DivisorCouter(1, 100000);
        es.invokeAll(Arrays.asList(task1));

        // Fetches the end time of the method.
        Instant end = Instant.now();

        // Find the highest result
        Result result = DivisorCouter.getBestResult();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
