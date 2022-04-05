# Time formatter

Prints a time period represented by a long number (nanoseconds) in a human readable format. The printed string is an approximation of that period.

This can be useful when we want a rough estimate but don't really care about the exact value.

## Usage

```java
long startTime = System.nanoTime();
...
long endTime = System.nanoTime();
long period = endTime - startTime;
System.out.println(new Time(period));

```

## Examples

* A time period of 423423 is formatted as 0.42ms
* A time period of 3423423 is formatted as 3.4ms
* A time period of 53423423 is formatted as 53ms
* A time period of 7653623423L is formatted as 7.7s
* A time period of 47653623423L is formatted as 48s
* A time period of 447653623423L is formatted as 7m 28s
* A time period of 5447653623423L is formatted as 1h 31m

## Compile

Include the provided package in your project and import the class as usual.
