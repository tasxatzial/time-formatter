package timeutils;

/**
 * Creates an object from a long number representing a time period in nanoseconds.
 * Additionally, it provides functions to convert the time period into hours, minutes, seconds,
 * milliseconds, and microseconds, and to convert these units back to nanoseconds.
 *
 * The toString() method returns an approximation of the time period in a human-readable format.
 *
 * Periods > 1 hour are formatted as Xh Ymin (e.g. 1h 12min)
 * Periods > 1 min are formatted as Xmin Ys (e.g. 1min 12s)
 * Periods > 10 sec are formatted as Xs (e.g. 22s)
 * Periods > 1 sec are formatted as X.Ys (e.g. 5.4s)
 * Periods > 10 milli sec are formatted as Xms (e.g. 34ms)
 * Periods > 1 milli sec are formatted as X.Yms (e.g. 3.4ms)
 * Periods > 10 micro sec are formatted as Xμs (e.g. 12μs)
 * Periods > 1 micro sec are formatted as X.Yμs (e.g. 1.2μs)
 * Else periods are formatted as Xns (e.g. 12ns)
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
        return Time.toString(_longValue);
    }

    /**
     * Returns a string representation of the given time period
     * @return
     */
    public static String toString(long period) {
        double hours = Time.toHr(period);
        if (hours > 1) {
            int intHours = Time.countHour(period);
            long intMins = Math.round(toMin(fromHr(hours) - fromHr(intHours)));
            if (intMins == 60) {
                return (intHours + 1) + "h";
            } else if (intMins == 0) {
                return intHours + "h";
            }
            return intHours + "h " + intMins + "min";
        }
        double mins = Time.toMin(period);
        if (mins > 1) {
            int intMins = Time.countMin(period);
            long intSecs = Math.round(toSec(fromMin(mins) - fromMin(intMins)));
            if (intSecs == 60) {
                if (intMins == 59) {
                    return "1h";
                }
                return (intMins + 1) + "min";
            } else if (intSecs == 0) {
                if (intMins == 60) {
                    return "1h";
                }
                return intMins + "min";
            }
            return intMins + "min " + intSecs + "s";
        }
        double secs = Time.toSec(period);
        if (secs > 10) {
            long intSecs = Math.round(secs);
            if (intSecs == 60) {
                return "1min";
            }
            return intSecs + "s";
        }
        if (secs > 1) {
            return roundToDecimal(secs, 1) + "s";
        }
        double milliSec = Time.toMilliSec(period);
        if (milliSec > 10) {
            long intMilliSec = Math.round(milliSec);
            if (intMilliSec == 1000) {
                return "1s";
            }
            return intMilliSec + "ms";
        }
        if (milliSec > 1) {
            return roundToDecimal(milliSec, 1) + "ms";
        }
        double microSec = Time.toMicroSec(period);
        if (microSec > 10) {
            long intMicroSec = Math.round(microSec);
            if (intMicroSec == 1000) {
                return "1ms";
            }
            return intMicroSec + "μs";
        }
        if (microSec > 1) {
            return roundToDecimal(microSec, 1) + "μs";
        }
        return period + "ns";
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
