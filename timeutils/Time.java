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
        double hours = this.toHr();
        if (hours > 1) {
            int intHours = this.countHour();
            double mins = toMin(fromHr(hours) - fromHr(intHours));
            return intHours + "h " + roundToInt(mins) + "m";
        }
        double mins = this.toMin();
        if (mins > 1) {
            int intMins = this.countMin();
            double secs = toSec(fromMin(mins) - fromMin(intMins));
            return intMins + "m " + roundToInt(secs) + "s";
        }
        double secs = this.toSec();
        if (secs > 20) {
            return roundToInt(secs) + "s";
        }
        if (secs > 2) {
            return roundToDecimal(secs, 1) + "s";
        }
        double msec = this.toMilliSec();
        if (msec > 100) {
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
     * Returns the hours (int) contained in the specified nanoseconds (long)
     * @param value
     * @return
     */
    public static int countHour(long value) {
        return (int) Math.floor(toHr(value));
    }

    /**
     * Returns the hours (int) contained in this Time
     * @return
     */
    public int countHour() {
        return Time.countHour(_longValue);
    }

    /**
     * Returns the minutes (int) contained in the specified nanoseconds (long)
     * @param value
     * @return
     */
    public static int countMin(long value) {
        return (int) Math.floor(toMin(value));
    }

    /**
     * Returns the minutes (int) contained in this Time
     * @return
     */
    public int countMin() {
        return Time.countMin(_longValue);
    }

    /**
     * Returns the seconds (int) contained in the specified nanoseconds (long)
     * @param value
     * @return
     */
    public static int countSec(long value) {
        return (int) Math.floor(toSec(value));
    }

    /**
     * Returns the seconds (int) contained in this Time
     * @return
     */
    public int countSec() {
        return Time.countSec(_longValue);
    }

    /**
     * Returns the milli secs (int) contained in the specified nanoseconds (long)
     * @param value
     * @return
     */
    public static int countMilliSec(long value) {
        return (int) Math.floor(toMilliSec(value));
    }

    /**
     * Returns the milli secs (int) contained in this Time
     * @return
     */
    public int countMilliSec() {
        return Time.countMilliSec(_longValue);
    }

    /**
     * Returns the micro secs (int) contained in the specified nanoseconds (long)
     * @param value
     * @return
     */
    public static int countMicroSec(long value) {
        return (int) Math.floor(toMicroSec(value));
    }

    /**
     * Returns the micro secs (int) contained in this Time
     * @return
     */
    public int countMicroSec() {
        return Time.countMicroSec(_longValue);
    }

    /**
     * Returns the nanoseconds (long) from a given time in hours (double)
     * @param value
     * @return
     */
    public static long fromHr(double value) {
        return (long) (value * 3.6e12);
    }

    /**
     * Returns the nanoseconds (long) from a given time in minutes (double)
     * @param value
     * @return
     */
    public static long fromMin(double value) {
        return (long) (value * 6e10);
    }

    /**
     * Returns the nanoseconds (long) from a given time in seconds (double)
     * @param value
     * @return
     */
    public static long fromSec(double value) {
        return (long) (value * 1e9);
    }

    /**
     * Returns the nanoseconds (long) from a given time in milli secs (double)
     * @param value
     * @return
     */
    public static long fromMilliSec(double value) {
        return (long) (value * 1e6);
    }

    /**
     * Returns the nanoseconds (long) from a given time in micro secs (double)
     * @param value
     * @return
     */
    public static long fromMicroSec(double value) {
        return (long) (value * 1e3);
    }

    /**
     * Converts the specified time in nanoseconds (long) to hours (double)
     * @param value
     * @return
     */
    public static double toHr(long value) {
        return value / 3.6e12;
    }

    /**
     * Returns the time in hours (double)
     * @return
     */
    public double toHr() {
        return Time.toHr(_longValue);
    }

    /**
     * Converts the specified time in nanoseconds (long) to minutes (double)
     * @param value
     * @return
     */
    public static double toMin(long value) {
        return value / 6e10;
    }

    /**
     * Returns the time in minutes (double)
     * @return
     */
    public double toMin() {
        return Time.toMin(_longValue);
    }

    /**
     * Converts the specified time in nanoseconds (long) to seconds (double)
     * @param value
     * @return
     */
    public static double toSec(long value) {
        return value / 1e9;
    }

    /**
     * Returns the time in seconds (double)
     * @return
     */
    public double toSec() {
        return Time.toSec(_longValue);
    }

    /**
     * Converts the specified time in nanoseconds (long) to milli secs (double)
     * @param value
     * @return
     */
    public static double toMilliSec(long value) {
        return value / 1e6;
    }

    /**
     * Returns the time in milli secs (double)
     * @return
     */
    public double toMilliSec() {
        return Time.toMilliSec(_longValue);
    }

    /**
     * Converts the specified time in nanoseconds (long) to micro secs (double)
     * @param value
     * @return
     */
    public static double toMicroSec(long value) {
        return value / 1e3;
    }

    /**
     * Returns the time in micro secs (double)
     * @return
     */
    public double toMicroSec() {
        return Time.toMicroSec(_longValue);
    }

    /**
     * Adds the specified time to this Time
     * @param time
     */
    public void addTime(Time time) {
        _longValue += time.getValue();
    }

    /**
     * Returns the time in nanoseconds (long)
     * @return
     */
    public long getValue() {
        return _longValue;
    }
}
