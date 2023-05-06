# Time formatter

A simple class that can be used to return an approximation of the elapsed time in nanoseconds in a human-readable format.

It also converts time periods between nanoseconds, hours, minutes, seconds, milliseconds, and microseconds.

## Usage

```java
import timeutils.Time;

long startTime = System.nanoTime();
...
long endTime = System.nanoTime();

// Create a Time instance
System.out.println(new Time(endTime - startTime));

// Do not create a Time instance, use the static method instead
System.out.println(Time.toString(endTime - startTime));
```

## Examples

| Period         | Format   | Approx. Percent Error |
|----------------|----------|-------|
| 423423         | 423μs    | 0.1%  |
| 3423423        | 3.4ms    | 0.7%  |
| 53423423       | 53ms     | 0.8%  |
| 7653623423L    | 7.7s     | 0.6%  |
| 47653623423L   | 48s      | 0.7%  |
| 447653623423L  | 7min 28s | 0.1%  |
| 5447653623423L | 1h 31min | 0.2%  |
