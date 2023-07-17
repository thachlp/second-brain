JMH is short for Java Microbenchmark Harness. Using JMH for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.

1. Set up project
    ```groovy
    plugins {
        id 'java-library'
        id "me.champeau.jmh" version "0.7.0"
    }
   dependencies {
        implementation 'org.openjdk.jmh:jmh-core:1.36'
        implementation 'org.openjdk.jmh:jmh-generator-annprocess:1.33'
    }
   jmh {
        warmupIterations = 2
        iterations = 5
        fork = 1
   }
   ```
2. Implementing
   ```java
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void wellHelloThere() {
        // this method was intentionally left blank.
    } 
   ```

3. Run the sample: `./gradlew jmh`

Reference:
- [jmh-sample](https://github.com/openjdk/jmh/tree/master/jmh-samples)
- [jmh-gradle-plugin](https://github.com/melix/jmh-gradle-plugin)
