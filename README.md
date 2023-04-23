# Time formatter

A simple class that can be used to return an approximation of the elapsed time in nanoseconds in a human-readable format.

It also converts time periods between nanoseconds, hours, minutes, seconds, milliseconds, and microseconds.

## Usage

```java
import timeutils.Time;

long startTime = System.nanoTime();
...
long endTime = System.nanoTime();
System.out.println(new Time(endTime - startTime));
```

## Examples

| Period         | Format   |
|----------------|----------|
| 423423         | 423μs    |
| 3423423        | 3.4ms    |
| 53423423       | 53ms     |
| 7653623423L    | 7.7s     |
| 47653623423L   | 48s      |
| 447653623423L  | 7min 28s |
| 5447653623423L | 1h 31min |
