package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static int threads = 1;
    static int counterMinimum = 1;
    static int counterMaximum = 100000;
    static List<Callable<Result>> counters = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(threads);
        for (int i=0;i<threads;i++){
            counters.add(new DivisorCounter(i + counterMinimum, counterMaximum - (threads-1-i),threads));
        }
        System.out.println("Looking for the best result...");
        es.invokeAll(counters);

        // Fetches the end time of the method.
        Instant end = Instant.now();

        // Find the highest result
        Result result = DivisorCounter.getBestResult();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
