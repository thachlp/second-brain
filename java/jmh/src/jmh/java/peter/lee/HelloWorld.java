package peter.lee;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class HelloWorld {
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }
}
