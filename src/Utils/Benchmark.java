package Utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Benchmark {
    public static void Run(Supplier<Object, Object> func) {
        long startTime = System.currentTimeMillis();
        Object result = func.(2);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / (double)1000;
        System.out.println("Result: {" + result + "}, Duration: {" + time + "} seconds");
    }
}
