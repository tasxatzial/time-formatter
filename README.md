# Time period formatter

A small class that can be used for printing time periods represented by long numbers into a human readable format. The printed number will always be an approximation of the time period. This can be useful when we want a rough estimate of the time passed but don't really care about the exact numbers.

## Usage

A typical usage consists of the following code sequence

```java
long startTime = System.nanoTime();
...
...
long endTime = System.nanoTime();
System.out.println(new Time(endTime - startTime));

```

## Examples

* A time period of 423423 will be printed as 0.42ms
* A time period of 3423423 will be printed as 3.4ms
* A time period of 53423423 will be printed as 53ms
* A time period of 7653623423 will be printed as 7.7s
* A time period of 47653623423 will be printed as 48s
* A time period of 447653623423 will be printed as 7m 28s
* A time period of 5447653623423 will be printed as 1h 31m

## Compile

Include the provided java file in your project and import the class as usual.
