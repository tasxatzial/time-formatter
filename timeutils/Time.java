package timeutils;

/**
 * This class creates an object from a long number representing a time period in nanoseconds
 * and provides functions that extract the hours, minutes, seconds, milliseconds.
 *
 * The toString() method returns a human readable format of the time represented
 * by this object. It will be an approximation of the actual time and can be used
 * when we need a rough estimate but don't need the exact value.
 *
 * Periods > 1 hour are formatted as Xh Ym (e.g. 1h 12m)
 * Periods > 1 min are formatted as Xm Ys (e.g. 1m 12s)
 * Periods > 20 sec are formatted as Xs (e.g. 22s)
 * Periods > 2 sec are formatted as X.Ys (e.g. 5.4s)
 * Periods > 100 ms are formatted as 0.YYs (e.g. 0.73s)
 * Periods > 20 ms are formatted as XXms (e.g. 34ms)
 * Periods > 2 ms are formatted as X.Yms (e.g. 2.6ms)
 * Periods <= 2 ms are formatted as X.YYms (e.g. 1.65ms)
 */
public class Time {
    private long _longValue;

    public Time(long value) {
        _longValue = value;
    }

    /**
     * Returns a string representation of this Time
     * @return
     */
    public String toString() {
        double msec = toMsec(_longValue);
        if (msec > toMsec(fromHr(1))) {
            double hours = toHr(_longValue);
            int intHours = countHour(_longValue);
            double mins = toMin(fromHr(hours) - fromHr(intHours));
            return intHours + "h " + roundToInt(mins) + "m";
        }
        if (msec > toMsec(fromMin(1))) {
            double mins = toMin(_longValue);
            int intMins = countMin(_longValue);
            double secs = toSec(fromMin(mins) - fromMin(intMins));
            return intMins + "m " + roundToInt(secs) + "s";
        }
        if (msec > toMsec(fromSec(20))) {
            double secs = toSec(_longValue);
            return roundToInt(secs) + "s";
        }
        if (msec > toMsec(fromSec(2))) {
            double secs = toSec(_longValue);
            return roundToDecimal(secs, 1) + "s";
        }
        if (msec > 100) {
            double secs = toSec(_longValue);
            return roundToDecimal(secs, 2) + "s";
        }
        if (msec > 20) {
            return roundToInt(msec) + "ms";
        }
        if (msec > 2) {
            return roundToDecimal(msec, 1) + "ms";
        }
        return roundToDecimal(msec, 2) + "ms";
    }

    /**
     * Rounds the specified value to the closest decimal that has digits decimal digits
     * @param value
     * @param digits
     * @return
     */
    public static double roundToDecimal(double value, int digits) {
        double pow = Math.pow(10, digits);
        return Math.round(value * pow) / pow;
    }

    /**
     * Rounds the specified value to the nearest integer
     * @param value
     * @return
     */
    public static int roundToInt(double value) {
        return (int) Math.round(value);
    }

    /**
     * Returns the hours (int) from the specified long value
     * @param value
     * @return
     */
    public static int countHour(long value) {
        return (int) Math.floor(toHr(value));
    }

    /**
     * Returns the minutes (int) from the specified long value
     * @param value
     * @return
     */
    public static int countMin(long value) {
        return (int) Math.floor(toMin(value));
    }

    /**
     * Returns the seconds (int) from the specified long value
     * @param value
     * @return
     */
    public static int countSec(long value) {
        return (int) Math.floor(toSec(value));
    }

    /**
     * Returns the mseconds (int) from the specified long value
     * @param value
     * @return
     */
    public static int countMsec(long value) {
        return (int) Math.floor(toMsec(value));
    }

    /**
     * Returns the long value from a given time in hours
     * @param value
     * @return
     */
    public static long fromHr(double value) {
        return (long) (value * 3.6e12);
    }

    /**
     * Returns the long value from a given time in minutes
     * @param value
     * @return
     */
    public static long fromMin(double value) {
        return (long) (value * 6e10);
    }

    /**
     * Returns the long value from a given time in seconds
     * @param value
     * @return
     */
    public static long fromSec(double value) {
        return (long) (value * 1e9);
    }

    /**
     * Returns the long value from a given time in mseconds
     * @param value
     * @return
     */
    public static long fromMsec(double value) {
        return (long) (value * 1e6);
    }

    /**
     * Returns the time in mseconds (decimal) from the specified long value
     * @param value
     * @return
     */
    public static double toMsec(long value) {
        return value / 1e6;
    }

    /**
     * Returns the time in seconds (decimal) from the specified long value
     * @param value
     * @return
     */
    public static double toSec(long value) {
        return value / 1e9;
    }

    /**
     * Returns the time in minutes (decimal) from the specified long value
     * @param value
     * @return
     */
    public static double toMin(long value) {
        return value / 6e10;
    }

    /**
     * Returns the time in hours (decimal) from the specified long value
     * @param value
     * @return
     */
    public static double toHr(long value) {
        return value / 3.6e12;
    }

    /**
     * Adds the specified time to this Time.
     * @param time
     */
    public void addTime(Time time) {
        _longValue += time.getValue();
    }

    /**
     * Returns the long value associated with this Time
     * @return
     */
    public long getValue() {
        return _longValue;
    }
}